import java.util.*;
public class Node {

	private String tag;
    private String data;
    private List<Node> children = new ArrayList<>();
    
    public Node(String t, String d)
    {
        this.tag = t;
        this.data =d;
    }
 
    public void addChild(Node n)
    {
    
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

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Node> getChildren() {
        return children;
    }
 
}
