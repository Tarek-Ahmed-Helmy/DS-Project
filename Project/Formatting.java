

public class Formatting {
    public static String formatXML(final String unformattedXML) {
        final int length = unformattedXML.length();
        int indentSpace= 5;
        final StringBuilder newString = new StringBuilder(length + length / 10);
        final char space = ' ';
        int i = 0;

        int indentCount = 0;
        char currentChar = unformattedXML.charAt(i++);
        char previousChar = currentChar;
        boolean nodeStarted = true;
        newString.append(currentChar);
        for (; i < length - 1;) {
            currentChar = unformattedXML.charAt(i++);
            if (((int) currentChar < 33) && !nodeStarted) {
                continue;
            }
            switch (currentChar) {
                case '<':
                    if ('>' == previousChar && '/' != unformattedXML.charAt(i - 1) && '/' != unformattedXML.charAt(i) && '!' != unformattedXML.charAt(i)) {
                        indentCount++;
                    }
                    newString.append('\n');
                    for (int j = indentCount * indentSpace; j > 0; j--) {
                        newString.append(space);
                    }
                    newString.append(currentChar);
                    nodeStarted = true;
                    break;
                case '>':
                    newString.append(currentChar);
                    nodeStarted = false;
                    break;
                case '/':
                    if ('<' == previousChar || '>' == unformattedXML.charAt(i)) {
                        indentCount--;
                    }
                    newString.append(currentChar);
                    break;
                default:
                    if(Character.isSpaceChar(unformattedXML.charAt(i))){
                        newString.append(currentChar);
                        newString.append(" ");
                    }else{
                        newString.append(currentChar);
                    }
            }
            previousChar = currentChar;
        }

        newString.append(unformattedXML.charAt(length - 1));
        return newString.toString();
    }



    public static String Minify(String xml)
    {
        xml = xml.replaceAll(">\\s+<", "><");
        xml = xml.replaceAll("\n", "");
        xml = xml.replaceAll("\r", "");
        xml = xml.replaceAll("  ", "");
        return xml.trim();

    }

}
