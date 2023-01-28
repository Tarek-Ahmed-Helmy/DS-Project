import java.io.*;
import java.util.*;
public class testPrintingTagsHala{
	
		public static void printTree(Node n,int indent) {
		StringBuilder ind = new StringBuilder();
		for(int i = 0; i<indent;i++)
			ind.append(' ');
		System.out.println(ind+n.getTag());
		for(Node child : n.getChildren()) {
				printTree(child,indent+1);
			}
		}
		public static List<GraphNode> treeToUsersArray(Node users){
			//  we have the root of the tree i.e users 
			// we should get the user user user
			List<GraphNode> graphNodeList=new ArrayList<GraphNode>();
			List<Node> usersList=users.getChildren();
			for(Node user:usersList) {
				GraphNode simpleOne=new GraphNode();
				List<Node> userData=user.getChildren();
				for(Node x:userData) {
					if(x.getTag().equals("id")) simpleOne.id=x.getData();
					if(x.getTag().equals("name")) simpleOne.name=x.getData();
					if(x.getTag().equals("posts")) {
						List<Node> posts=x.getChildren();
						for(Node y:posts) {
							Node body=y.getChildren().get(0);
							simpleOne.posts.add(body.getData());
						}
					}
					if(x.getTag().equals("followers")) {
						List<Node> followers=x.getChildren();
						for(Node follower:followers) {
							Node id=follower.getChildren().get(0);
							simpleOne.followersIds.add(id.getData());
						}
					}
				}
				graphNodeList.add(simpleOne);
			}
			return graphNodeList;
		}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("/home/ismail939/DS Project Clone 1/DS-Project/sample-input.xml");
        Reader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        /*int i;
        while((i=br.read())!=-1) {
        	System.out.print((char)i);
        }*/
        StringBuilder sb = new StringBuilder();
        
        String line = br.readLine();
     
        
        while(line !=null){
            sb.append(line).append("\n");
            line = br.readLine();
        }
        String xml2string = sb.toString();
   
        Tree xmltree = new Tree();
        
        xmltree.fillTree(xml2string);
        List<GraphNode> userss=treeToUsersArray(xmltree.getRoot());
        for(GraphNode user:userss) {
        	System.out.println("user coming !!! yeeah");
        	System.out.println(user.name);
        	System.out.println(user.id);
        	System.out.println(user.posts);
        	System.out.println(user.followersIds);
        	System.out.println();
        }
        
        br.close();
        fr.close();

	}

}