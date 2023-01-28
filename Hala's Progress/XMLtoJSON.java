package xmlproj;
import java.io.*;
import java.util.*;
public class XMLtoJSON{
	
	public static void printXML(Node node)
	{
		System.out.print("<"+node.getTag()+">");
		System.out.print(node.getData());
		for(int i = 0; i < node.getChildren().size(); i++)
		{
			if(node.getChildren().get(i) !=null)
				printXML(node.getChildren().get(i));
		}
		System.out.print("</"+node.getTag()+">");
	}
	
	public static void printJSON(Node node) 
	{
		System.out.print("\t"+"\"" + node.getTag() + "\"" + ":");

		if (node.getData() == "")
		{

			System.out.print("[\n\t");
			System.out.print("\t\t{\n\t");
		}
		else
		{

			System.out.print("\"" + node.getData() + "\"");

		}


		for (int i = 0; i < node.getChildren().size(); i++)
		{


			if (node.getChildren().get(i) != null && i<node.getChildren().size()-1)
			{
				System.out.print(" \t");
				printJSON(node.getChildren().get(i));

				System.out.print(",\n\t");
			}
			if( i==node.getChildren().size()-1)
			{
				printJSON(node.getChildren().get(i));
				System.out.println("\n\t\t}");
				System.out.print("\t]");

			}

		}
	}
	public static void printJSON2(Node node) 
	{
		System.out.print("\"" + node.getTag() + "\"" + ":");

		if (node.getData() == "")
		{

			System.out.print("[\n\t");
			System.out.print("\t{\n\t");
		}
		else
		{

			System.out.print("\"" + node.getData() + "\"");

		}


		for (int i = 0; i < node.getChildren().size(); i++)
		{


			if (node.getChildren().get(i) != null && i<node.getChildren().size()-1)
			{
				System.out.print(" \t");
				printJSON(node.getChildren().get(i));

				System.out.print(",\n\t");
			}
			if( i==node.getChildren().size()-1)
			{
				printJSON(node.getChildren().get(i));
				System.out.println("\n\t}");
				System.out.print("\t]");

			}

		}
	}
	public static void printTree(Node n,int indent) {
		StringBuilder ind = new StringBuilder();
		for(int i = 0; i<indent;i++)
			ind.append(' ');
		System.out.println(ind+n.getTag());
		for(Node child : n.getChildren()) {
				printTree(child,indent+1);
			}
		}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\Hala\\Desktop\\sample.xml");
        Reader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        /*int i;
        while((i=br.read())!=-1) {
        	System.out.print((char)i);
        }*/
        StringBuilder sb = new StringBuilder();
        
        String line = br.readLine();
     //   char[] line_arr = line.toCharArray();
       // line.trim();
      //  System.out.println(line);
    //    System.out.println(line_arr.length);
    //    String word = extractTag(line,'<','>');
     //   String word = extractTag(line,'<','>');
  //      System.out.println(word);
   /*     String line2 = br.readLine();
        System.out.println(line2);
        String line3 = br.readLine();
        line3.trim();
        System.out.println(line3);
        char[] line2arr = line2.toCharArray();
        String[] words = line2.split("[<>]+");
        System.out.println(words.length);
        System.out.println("first element: "+words[0]);
   /*     for(int i=0;i<words.length;i++) {
        	System.out.print(words[i]);
        }*//*
        for(int i=0;i<3;i++) {
        	line = br.readLine();
        }
        System.out.println(line);
        String trimline = line.trim();
        System.out.println(trimline);
        String[] splitline = trimline.split("[<>]+");
        System.out.println(splitline.length);
        for(int j =0;j<splitline.length;j++) {
        	System.out.println("\""+splitline[j]+"\"");
        }*/
    //    System.out.println(line2.length());
       // String[] words = line.
       // System.out.println(words[1]);
        
        while(line !=null){
            sb.append(line).append("\n");
            line = br.readLine();
        }
        String xml2string = sb.toString();
    //    Scanner sc = new Scanner(xml2string);
        //System.out.println(sc.nextLine());
        
       /* 
        while(sc.hasNextLine()) {
        	String linex = sc.nextLine();
        	linex.trim();
        	System.out.println(linex);
        }
        sc.close();*/
      //  System.out.println("XML to string: ");
       
        System.out.println(xml2string);
   //     System.out.println(line);
      //  Tree xmltree = new Tree();
        
    //    xmltree.fillTree(xml2string);
     //   System.out.println(xmltree.getRoot().getTag());
       
       // xmltree.print(xmltree.getRoot());
      //  printXML(xmltree.getRoot());
     //   printTree(xmltree.getRoot(),0);
   //     System.out.println(xmltree.getRoot().getTag());
     //   printJSON(xmltree.getRoot());
        /*******************************/
         
         /***********************************/
        br.close();
        fr.close();

	}

}
