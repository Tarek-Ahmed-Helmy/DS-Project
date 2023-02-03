# Data Structure and Algorithms Project

We will work on developing a GUI based program to parse and visualize an XML file which will represents users in a social network. Each user has an unique id, name, list of posts and list of followers. Each post has body and list of topics. Then, we apply some basic operations like:( Checking the consistency, showing the errors (if any), solving these errors, formatting the xml file, converting the xml file to JSON file, minifying, compressing and decompressing the xml file. Finally, we use the graph data structure to represent the users, and we can extract important details about our users, and know who is the most influencer and most active user. In addition to, we can know the mutual followers between two users and suggest some followers to a specific user, and we can perform a post search to know where a specific word or topic is mentioned.

## Implementation Details
### Class: Node
It is a class that represents the xml tag node. It holds the tag value, data value and the list of tag children.
###	Class: Tree
It is a class that converts the xml tags into a tree of tags, those tags are the nodes.
####	Method: getRoot()
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
####	Method: consistency (String str)
This method is used to check if the xml file has errors or is error free. It has one parameter which is the buffered reader object of the xml file we are working on. It returns a Boolean value true means it is consistent and false means it is inconsistent.
####	Method: showError (String str)
It is a void static method that we use to show the errors in a given xml file. It takes a buffered reader object as a parameter.
####	Method: solveError (String str, BufferedWriter bw)
It is a void static method that is used to solve the errors in a given xml file. It has two parameters: a buffered reader object to read the xml file and a buffered writer object to write in another xml file.
###	Class: JsonConversion
####	Method: printJSON (Node node)
It is a method that is used to convert from the xml format to the JSON format. It takes the root node as input and prints out the JSON formatted file.
###	Class: Formatting
####	Method: formatXML (final String unformattedXML)
The function iterates through the characters in the string and adding newline and indentation characters based on the structure of the XML. The algorithm keeps track of the indentation level by counting the number of opened XML tags and decreasing the indentation level when a closing tag is encountered.
####	Method: minify (String xml)
It is a method that minifies the xml file string by removing the additional white spaces and indentations. It takes the xml as string input and returns the minified string.
###	Class: Compression
####	Method: compress (String str, BufferedWriter bw)
This method uses LZW encoding in which the XML characters are added to an array list and then looping on every character if it’s present in the list, we replace the character with its index in the list and then we keep concatenating characters until the given string is not present in the list and adding it to the list.
####	Method: decompress (BufferedReader br)
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
###	GUI
####	Main Window “XML_View”














#####	Open File button
-	Opens file chooser.
-	Reads XML file.
-	Converts XML file to String.
-	Displays XML on text area.



#####	Convert to JSON button
-	Creates XML tree.
-	Converts XML to JSON format.
-	Displays “XML converted to JSON”
-	Shows JSON format on text area.







#####	Check Consistency button
-	Checks if XML file has errors or not.
-	If file contains errors:
o	Displays “The XML File Is Not Consistent”.
-	If file has no errors:
o	Displays “The XML File Is Consistent”.






#####	Show Errors button
-	If XML file is not consistent: Shows errors and their locations in the file.
-	If XML file is consistent: Displays “There are no errors”.





#####	Solve Errors button
-	Solves errors in XML file.
-	Writes corrected XML in file “sample-input-corrected.xml”.
-	Reads the corrected file and converts it to string.
-	Displays “The errors are corrected”
-	Shows the XML file after correction.


#####	Format button 
-	Formats XML file.
-	Displays formatted XML on text area.
-	Displays “XML is formatted”


#####	Minify button
-	Minifies XML file.
-	Displays minified XML on text area.
-	Displays “XML minified”.



#####	Compress button
-	Opens file chooser.
-	Reads file to be compressed.
-	Compresses XML.
-	Writes Compression of XML in file “compressed.xml”.
-	Displays “XML file is compressed”. 
#####	Decompress button
-	Opens file chooser.
-	Reads file to be decompressed.
-	Decompresses XML file.
-	Displays decompressed file on text area.
-	Displays “XML file is decompressed”.
-	If compressed file does not exist:
o	Catches exception.
o	Displays “There is no compressed file to be decompressed”.

#####	Search Graph button
-	Creates a new window of class “SearchWindow”. 
-	Sends XML file to the new window.


#####	Most Influencer button
-	Creates XML tree.
-	Creates array of users.
-	Displays the most influencer user’s name and ID. 

#####	Mutual Followers button
-	Creates a new window from class “MutualFollowers”.
-	Sends XML file to the new window. 

#####	Most Active button
-	Creates XML tree.
-	Creates a list of users.
-	Constructs a graph of users.
-	Displays the most active user. 

#####	Suggestion Followers button
-	Creates a new window from class “SuggestionFollowers”.
-	Sends XML file to the new window.
 

#####	Visualize Graph button
-	Checks if XML file not consistent: 
o	Display “Please solve the errors before Graph Visualization”.
-	If XML file consistent:
o	Creates XML tree.
o	Creates list of users.
o	Generates Graph.
o	Opens New Window of class “GraphImage”. 

####	 “SuggestionFollowers” window
 

#####	Find button
-	Takes user ID as input from user in “User Number” text field. 
-	Creates XML tree. 
-	Checks if ID does not exist.
o	Displays “User does not exist”. 
-	Displays suggestion followers for that user. 
#####	Done button
-	Closes the suggestion followers window.

####	“MutualFollowers” window
   
#####	Find button
-	Takes 2 user IDs as input from user in text fields. 
-	Creates tree. 
-	Creates List of users. 
-	Constructs graph 
-	Checks if first user does not exist 
o	Displays “The first user does not exist” 
-	 Checks if second user does not exist 
o	Displays “The second user does not exist”
-	If both users don’t exist: 
o	Displays “Those users do not exist”.
-	If users list is empty 
o	Displays “file has no users”. 
-	If mutual followers list is empty 
o	Displays “These users have no mutual followers”. 
-	Displays mutual followers.
#####	Done button
-	Closes the Mutual Followers window.

####	“SearchWindow” window
 

#####	Search button
-	Takes word to be searched as input from user in text field 
-	Displays the posts in which the word exists in text area 
-	If a word does not exist, it displays “The word does not exist”.  
#####	Done button
-	Closes the Search window.






####	“GraphImage” window
 

#####	Show Graph button
-	Displays the image of the graph. 
#####	Done button
-	Closes the GraphImage window.