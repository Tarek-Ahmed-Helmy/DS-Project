import java.util.Objects;

public static void printJSON(Node node) 
	{	
		System.out.print("\"" + node.getTag() + "\"" + ":");
		
		if (node.getData() == "")
		{
			System.out.print("[\n\t");
			System.out.print("{\n\t");
	
		}
		else{	
			System.out.print("\"" + node.getData() + "\"");	

		}

		for (int i = 0; i < node.getChildren().size(); i++)
		{

			if (node.getChildren().get(i) != null && i<node.getChildren().size()-1)
			{
			//	System.out.print("\t");
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
