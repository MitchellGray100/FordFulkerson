# FordFulkerson
FordFulkerson GUI <br>
MitchellGray100@gmail.com https://github.com/MitchellGray100/Chess<br>

JavaFX Ford-Fulkerson Calculator. Inspired by CS 4820. <br>

As a personal project, I created a representation of a Ford-Fulkerson Calculator in Java using JavaFX.
The calculator lets the user place nodes and connect each node with edges. The nodes automatically number themselves from:
<br> s->1->2->n...->t.<br> 
The capacities of every edge can be changed. Nodes and Edges can be removed. Nodes automatically decrement, if needed, to keep the numbered
numbered structured. Edges automatically remove themselves from removed nodes. All edges and capacities can be seen by clicking on Nodes.
The left panel is scrollable incase there is a large quantity of edges coming out of a node. Max Flow can be checked with the push of a button. 

___

</br>

# Play

<h3>
  To Play, just download the .exe file: https://github.com/MitchellGray100/FordFulkerson/raw/main/FordFulkerson.exe.
  


</br>
</br>

</br>

# Images

<h3>

The Program
![Image of the Program](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/Picture1.PNG)

Clicked Button

![Image of a Clicked Button](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/Picture2.PNG)

Smiley Face made of Nodes and Edges
  
![Image of Smiley Face made of Nodes and Edges](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/Picture3.PNG)
  
  
</br>

# GIFs

<h3>

Showing off how the buttons interact when clicked and hovered over. Buttons become orange when clicked and display help info in the top left.  <br>
![GIF of interactions](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/ShowButtonsGIF.gif)
  
Shows Nodes being added into the program. Number Ordering of the Nodes increases automatically and shows the first and last node as 's' and 't'. <br>
![GIF of nodes being added](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/AddNodesGIF.gif)
  
Shows Nodes being removed from the program. Number Ordering of the Nodes is changed automatically. <br>
![GIF of nodes being removed](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/DeleteNodesGIF.gif)
  
Shows Edges being added to Nodes. Edges can be added from one Node to itself. Edges can also go from node1 to node2 but can also go from node2 to node1. <br>
![GIF of edges being added](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/AddEdgesGIF.gif)
  
Shows Edges being remove from Nodes. Edges are removed by either clicking on the edge itself or by clicking on the first node of the edge and then the second node of the edge.
Edges connected to a node that was removed are also removed automatically.<br>
![GIF of edges being removed](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/RemoveEdgesGIF.gif)
  
Shows Capacities of the Edges being changed. Hovering over an edge changes the edge to orange and changes the mouse to a hand to make it easier for the user to click on the edge. <br>
![GIF of capacities being changed](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/ChangeCapacities.GIF.gif)
  
Shows Node Information being viewed by clicking each Node. Node Information shows the connected nodes and the corresponding capacities of the edges. <br>
![GIF of node info being shown](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/ViewNodeInfoGIF.gif)
  
Shows a very complicated graph that has 20 flow coming out of the Source Node. The Max Flow is calculated and revealed to be 20 aswell. <br>
![GIF of max flow calculation](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/MaxFlowGIF.gif)
  
Shows Node with many edges where the scroll bar needs to be used to view all edges. <br>
![GIF of Node info scroll bar being used](https://raw.githubusercontent.com/MitchellGray100/FordFulkerson/main/readMeImages/ScrollBarGIF.gif)
