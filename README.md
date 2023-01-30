# Data Structure and Algorithms Project

We will work on developing a GUI based program to parse and visualize an XML file which will represents users in a social network. Each user has an unique id, name, list of posts and list of followers. Each post has body and list of topics. Then, we apply some basic operations like:( Checking the consistency, showing the errors (if any), solving these errors, formatting the xml file, converting the xml file to JSON file, minifying, compressing and decompressing the xml file. Finally, we use the graph data structure to represent the users, and we can extract important details about our users, and know who is the most influencer and most active user. In addition to, we can know the mutual followers between two users and suggest some followers to a specific user, and we can perform a post search to know where a specific word or topic is mentioned.

## Implementation Details
### Class: Node
It is a class that represents the xml tag node. It holds the tag value, data value and the list of tag children.
###	Class: Tree
It is a class that converts the xml tags into a tree of tags, those tags are the nodes.
####	Method: getRoot
This method has a very simple job: it just returns the root node.
####	Method: fillTree (String file)
This method constructs the tree of tags. It takes the xml file as a string.
###	Class: Graph
This is a class for creating a graph with a given number of vertices. It has two arrays, inDegree and outDegree, which track the number of incoming and outgoing edges for each vertex. It also has a two-dimensional array, graph, which stores the edges between the vertices.
####	Constructor: Graph (int n)
The constructor gets the number of vertices, creates inDegree and outDegree arrays with size n, creates two-dimensional array graph with size n*n, and initializes all values to 0.
####	Method: addEdge (int start, int end)
It is a method that adds an edge between two vertices (start, end).
###	Class: GraphNode
It is a class representing the node used in the graph as it has the needed attributes: id, name list of posts, list of topics and list of followersIds.
###	Class: TagError
It is a class representing tag errors which holds two details: the line where the error occurred and the tag value of the tag.
###	Class: ErrorHandling
####	Method: consistency (BufferedReader br)
This method is used to check if the xml file has errors or is error free. It has one parameter which is the buffered reader object of the xml file we are working on. It returns a Boolean value true means it is consistent and false means it is inconsistent.
####	Method: showError (BufferedReader br)
It is a void static method that we use to show the errors in a given xml file. It takes a buffered reader object as a parameter.
####	Method: solveError (BufferedReader br, BufferedWriter bw)
It is a void static method that is used to solve the errors in a given xml file. It has two parameters: a buffered reader object to read the xml file and a buffered writer object to write in another xml file.
###	Class: JsonConversion
####	Method: printJSON (Node node)
It is a method that is used to convert from the xml format to the JSON format. It takes the root node as input and prints out the JSON formatted file.
###	Class: Formatting
####	Method: generateTabs (int tabLevel)
It is a method to generate tabs by taking the tab level as a parameter and returns a string.
####	Method: formatXML (String code)
It is a method that uses the GenerateTabs function to inject some tabs according to the tag level. It takes the xml file as a string input and returns the formatted xml.
####	Method: minify (String x)
It is a method that minifies the xml file string by removing the additional white spaces and indentations. It takes the xml as string input and returns the minified string.
###	Class: Compression
####	Method: compress (BufferedReader br, BufferedWriter bw)
This method uses LZW encoding in which the XML characters are added to an array list and then looping on every character if it’s present in the list, we replace the character with its index in the list and then we keep concatenating characters until the given string is not present in the list and adding it to the list.
####	Method: decompress (String s)
This method uses LZW decoding in which the function takes the compressed code and loops over the code with array of chars of the compressed file and keep adding chars to the list and replace the number code in the compressed file to its original format.
###	Class: GraphConstruction
####	Method: treeToUsersArray (Node users)
It is a method that makes a users’ list from the users nodes. To be clearer it makes a list of graph nodes, each graph node has 5 attributes: (Id, Name, posts (as bodies) list, followersIds list, topics list), and then it’s much easier to deal with when creating the graph as it has easy access.
####	Method: construct (List<GraphNode> graphNodeList)
This method takes the list of graph nodes as a parameter and constructs the graph needed. And returns that graph.
####	Method: search (List<GraphNode> graphNodeList, String word)
It is a method with two parameters: the users list and the word we want to search for. If it finds that word in a post body or a topic it prints the whole post or topic.
###	Class: NetworkAnalysis
####	Method: mostInfluencer (Graph graph, List<GraphNode> graphNodeList)
This function takes two inputs: the graph constructed and the graph nodes list and returns a graph node representing the most influencer user by getting the maximum followers a user can have in the users list.
####	Method: mostActive (Graph graph, List<GraphNode> graphNodeList)
This method takes two inputs: the graph constructed and the graph nodes list and returns a graph node representing the most active user.
####	Method: mutualFollowers (Graph graph, List<GraphNode> graphNodeList, GraphNode A, GraphNode B)
It is a method with 4 parameters: the graph object, users list and the two graph nodes objects. It checks if there is a mutual follower between two users if so, it adds it to the mutual followers list and at the end it returns that list.
####	Method: suggestions (List<GraphNode> graphNodeList, GraphNode A)
It is a method with 2 parameters: the users list and the user node that we want to suggest followers for. It goes to the followers of that user and adds the followers of those followers in a list that is called suggestionsList and returns it.
###	Class: Main
Here we call the methods we need to make the operations we talk about in the background above. It has one method: the main method.
####	Method: main
We first create the buffered readers, buffered writers and the string builder needed in our basic operations. We solve the errors of a file so that we can use it directly to create the graph needed. We create the tree from the xml file and then we use it to have our users list. We use the users list to construct the graph. We then do a while true with a switch case. Every case represents some function. We will discuss them now.
* Case 1: checking the consistency.
* Case 2: showing the errors.
* Case 3: solving all errors.
* Case 4: formatting (prettifying) the xml.
* Case 5: printing the JSON format of the xml file.
* Case 6: minifying the xml file.
* Case 7: compressing the xml file.
* Case 8: decompressing the compressed file of the xml file.
* Case 9: knowing the most influencer.
* Case 10: getting mutual followers between any two users.
* Case 11: knowing the most active user.
* Case 12: suggesting some followers for the given user.
* Case 13: searching for a specific word in a topic or a post.
* Default Case: printing for the client (“invalid entry”)