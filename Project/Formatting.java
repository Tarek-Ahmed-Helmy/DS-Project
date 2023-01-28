import static java.util.Arrays.fill;
public class Formatting {
    public static String GenerateTabs(int tabLevel) {
        char[] tabs = new char[tabLevel * 2];
        fill(tabs, ' ');

        return new String(tabs);
    }

    public static String FormatXML(String code) {

        String[] splitLines = code.split("\\n", 0);

        int tabLevel = 0;


        for (int lineNum = 0; lineNum < splitLines.length; ++lineNum) {
            String currentLine = splitLines[lineNum];

            if (currentLine.trim().isEmpty()) {
                splitLines[lineNum] = "";
            } else if (currentLine.matches(".*<[^/!][^<>]+?(?<!/)>?")) {
                splitLines[lineNum] = GenerateTabs(tabLevel) + splitLines[lineNum];

                ++tabLevel;
            } else if (currentLine.matches(".*</[^<>]+?>")) {
                --tabLevel;

                if (tabLevel < 0) {
                    tabLevel = 0;
                }

                splitLines[lineNum] = GenerateTabs(tabLevel) + splitLines[lineNum];
            } else if (currentLine.matches("[^<>]*?/>")) {
                splitLines[lineNum] = GenerateTabs(tabLevel) + splitLines[lineNum];

                --tabLevel;

                if (tabLevel < 0) {
                    tabLevel = 0;
                }
            } else {
                splitLines[lineNum] = GenerateTabs(tabLevel) + splitLines[lineNum];
            }
        }

        return String.join("\n", splitLines);
    }


    public static String Minify(String x)
    {
        String regex = "[\\n\\s]+(\\<[^/])";
        String updatedXml = x.replaceAll( regex, "$1" );


        regex = "(\\</[a-zA-Z0-9-_\\.:]+\\>)[\\s]+";
        updatedXml = updatedXml.replaceAll( regex, "$1" );

        regex = "(/\\>)[\\s]+";
        updatedXml = updatedXml.replaceAll( regex, "$1" );
        return updatedXml;

    }

}