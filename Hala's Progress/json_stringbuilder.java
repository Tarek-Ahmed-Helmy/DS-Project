	public static void printJSON(Node node) 
	{
		StringBuilder json_str = new StringBuilder();
		//XML_Viewer gui = new XML_Viewer();
		json_str.append("\t"+"\"" + node.getTag() + "\"" + ":");
		//System.out.print("\t"+"\"" + node.getTag() + "\"" + ":");

		if (node.getData() == "")
		{
			json_str.append("[\n\t");
			json_str.append("\t\t{\n\t");
		//	System.out.print("[\n\t");
			//System.out.print("\t\t{\n\t");
		}
		else
		{
			json_str.append("\"" + node.getData() + "\"");
			//System.out.print("\"" + node.getData() + "\"");

		}


		for (int i = 0; i < node.getChildren().size(); i++)
		{


			if (node.getChildren().get(i) != null && i<node.getChildren().size()-1)
			{
				json_str.append(" \t");
				//System.out.print(" \t");
				printJSON(node.getChildren().get(i));
				json_str.append(",\n\t");
				//System.out.print(",\n\t");
			}
			if( i==node.getChildren().size()-1)
			{
				printJSON(node.getChildren().get(i));
				json_str.append("\n\t\t}\n");
				json_str.append("\t]");
				//System.out.println("\n\t\t}");
				//System.out.print("\t]");

			}

		}
		System.out.println(json_str.toString());
	}
