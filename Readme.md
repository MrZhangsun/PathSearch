### Trains Problem Coding Guide
#### classes introduction:
##### com.hsbc.pathsearch.graph.DirectedGraph 
    This is a data struct of directed graph. I used it to discrpte the relation ship of nodes(towns).
##### com.hsbc.pathsearch.graph.Reporter
    This class is used to store search result. You can get search details by call it's methods. such as:
    1. List<String> getPaths()
    2. String getMaxPath()
    3. String getMinPath()
##### com.hsbc.pathsearch.graph.Side
    This class is used to discripte the side information between two nodes. It including start node, end node and weight 
    between these two nodes.
##### com.hsbc.pathsearch.graph.Sides
    This class is used to store all relative sides of the node in the directed graph. It including the in-sides and out-sides.
##### com.hsbc.pathsearch.stack.LinkedList
    This is data struct of linked list. It has been used to implement the data struct of linked list stack. as below class.
##### com.hsbc.pathsearch.stack.Stack
    This is an interface including several common methods of the stack data struct.
##### com.hsbc.pathsearch.stack.LinkedListStack
    This implementor of stack interface. This class is used to track visited nodes.
    
#### Test Guide
    1. 
    2.
    3.
