import java.util.*;
public class Node{  // a node representing a tag with its data
	private final String tag;
    private String data;
    private final List<Node> children = new ArrayList<>();
    public Node(String t, String d){
        this.tag = t;
        this.data =d;
    }
    public void addChild(Node n){
        this.children.add(n);
    }
    public String getTag()
    {
        return this.tag;
    }
    public String getData()
    {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public List<Node> getChildren() {
        return children;
    }
}