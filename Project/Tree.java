import java.util.*;
public class Tree{
	private Node root;
	public Tree(){
		root = null;
	}
	Node getRoot() {
		return root;
	}
	public void fillTree(String file){
		String[] words;
		Stack<Node> nodes = new Stack<Node>();
		Scanner scanner = new Scanner(file);
		// while not at file end
		while(scanner.hasNextLine()){
			// read first line
			String line1 = scanner.nextLine();
			//line = reader.readLine();
			String line =line1.trim();
			if(line=="") continue;
			// if tag
			if(line.charAt(0) == '<'){
				// empty strings
				String tag = "";
				String data = "";
				//if opening tag
				if(line.charAt(1) != '/'){
					//split line into words
					words = line.split("[<>]+");
					tag = words[1];
					// if tag contains data, store it
					if(words.length == 4){
						data = words[2];
					}
					Node n = new Node(tag,data);
					// if root
					if(nodes.size() == 0){
						root = n;
						nodes.push(n);
					}
					else{
						Node temp = nodes.peek();
						temp.addChild(n);
						nodes.push(n);
					}
					// if has a closing tag
					if(words.length == 4){
						nodes.pop();
					}
				}
				//if closing tag
				else{
					nodes.pop();
				}
			}
			// if data
			else{
				Node temp = nodes.peek();
				temp.setData(line);
			}
		}
		scanner.close();
	}
}