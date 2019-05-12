## Note: I'm using Maven to manage the dependence.
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
##### 10 Questions Junit Test class: com.hsbc.pathsearch.AnswerTestCases
##### You can run below methods to validate the result.

    JUnit Test Cases
    1. The distance of the route A-B-C.  
        question1Test()
    2. The distance of the route A-D.
        question2Test()
    3. The distance of the route A-D-C.
        question3Test()
    4. The distance of the route A-E-B-C-D.
        question4Test()
    5. The distance of the route A-E-D.
        question5Test()
    6. The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
        question6Test()
    7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
        question7Test()
    8. The length of the shortest route (in terms of distance to travel) from A to C.
        question8Test()
    9. The length of the shortest route (in terms of distance to travel) from B to B.
        question9Test()
    10. The number of different routes from C to C with a distance of less than 30.  In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
        question10Test()
##### Functional JUnit Test Cases Classes:
    com.hsbc.pathsearch.stack.LinkedListStackTest
    com.hsbc.pathsearch.graph.ReporterTest
    com.hsbc.pathsearch.graph.DirectedGraphTest