Data Structures Assignment 2 - Cities/Towns Map - Graph

--- Fritz Gerald Santos - 20071968 - Computer Forensics and Security (Y4 - 2018/2019)

Router Router Finder

I worked on this assignment by doing JUnit testing when doing each method.

Missing/Outstanding work:
1) Reading and writing data from a data store file.
- I planned to use a JSON file to store each Town Node in an array.

2) JavaFX GUI

3) Getting road names using Google Maps API.


What's included/created:

A Town node that takes in the name of the town. Properties of this object are a boolean flag and a value (used for dijsktra's algorithm).

A Graph class that creates the graph of all the towns and their connections by roads.
- used array to store town nodes.
- used a matrix to store distances between towns
- can retrieve all towns in the graph i.e. all towns that are connected by another town.
- can retrieve all towns and towns they are adjacent with.
- can get the distance between two towns 
- can get shortest route using dijkstra's algorithm.
- can get shortest route with option to avoid towns.


Used Stack that I had created from the original Data Structures module I did in 2017.

JUnit test for Graphs and Stack included.





