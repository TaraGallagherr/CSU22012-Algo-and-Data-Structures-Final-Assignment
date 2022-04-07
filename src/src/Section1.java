

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;


    /*Finding shortest paths between 2 bus stops (as input by the user),
    returning the list of stops en route as well as the associated “cost”.
    Stops are listed in stops.txt and connections (edges) between them come from stop_times.txt
    and transfers.txt files.
    All lines in transfers.txt are edges (directed),
    while in stop_times.txt an edge should be added only between 2 consecutive stops with the same trip_id.

    eg first 3 entries in stop_times.txt are
    9017927, 5:25:00, 5:25:00,646,1,,0,0,
    9017927, 5:25:50, 5:25:50,378,2,,0,0,0.3300
    9017927, 5:26:28, 5:26:28,379,3,,0,0,0.5780

    This should add a directed edge from 646 to 378, and a directed edge from 378 to 379
    (as they’re on the same trip id 9017927).

    Cost associated with edges should be as follows:

    1 if it comes from stop_times.txt,
    2 if it comes from transfers.txt with transfer type 0 (which is immediate transfer possible),
    and for transfer type 2 the cost is the minimum transfer time divided by 100.
*/


/* In this class I want to have a temporary main to test part 1 of the project and then eventually add it to the
main in full project which will call methods in all part 1 ,2 and 3 classes
this class should use the shortest path class to find the shortest path between 2 bus stops, the stops en route
 and the cost of the journey
 */

public class Section1 {

        public static ArrayList<String> startStops = new ArrayList<String>();
        public static ArrayList<String> finalStops = new ArrayList<String>();
        //private static double cost;
    public ArrayList<Edge> edges = new ArrayList<>();
        public static ArrayList<Double> costs = new ArrayList<Double>();
        public static HashMap<String,ConnectionNode> ConnectionNodesMap = new HashMap<>();
        public ArrayList<String[]> tripIdStops = new ArrayList<>();
        public static Double cost; //needed for when finding the shortest path


    String stopsPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stops.txt";
    String stopTimesPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stop_times.txt";
    String transfersPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\transfers.txt";



    //this constructor method reads through the three files passed in (stop, transfers and stoptimes)
    //it creates array lists of the start stops, the end stops and the journey weights
    //it then takes these three arrays and
        public Section1(File stopsFile, File transfersFile, File stopTimesFile) throws FileNotFoundException
        {
            Scanner sc1 = new Scanner(stopsFile);


            while(sc1.hasNextLine())
            {
                String stop = sc1.nextLine();
                String stopsArr[] = stop.split(",");
                ConnectionNode node = new ConnectionNode(stopsArr[0]);  //names the current connection node the start stop given in the stops file
                ConnectionNodesMap.put(stopsArr[0], node);
            }

            sc1.close();


            //in the transfers file, all lines are edges so all lines can be added
            Scanner sc2 = new Scanner(transfersFile);


            while(sc2.hasNextLine())
            {
                String edge = sc2.nextLine();
                String edgeArr[] = edge.split(",");


                    startStops.add(edgeArr[0]);

                    finalStops.add(edgeArr[1]);

                    //now adding cost based on transfer type
                    if (edgeArr[2].equals("0")) {

                        costs.add(2.0);
                    }

                    //the cost for transfer type 2 is min time/100 --> min_time stored at edgeArr[3]
                    else if (edgeArr[2].equals("2")) {
                        costs.add(Double.parseDouble(edgeArr[3]) / 100);
                    }

                    else {
                        costs.add(1.0);
                    }
                }



            sc2.close();

            for(int i = 1;i < startStops.size();i++) {
                ConnectionNode node1 = ConnectionNodesMap.get(startStops.get(i));
                ConnectionNode node2 = ConnectionNodesMap.get(finalStops.get(i));
                Double edgeWeight = costs.get(i);

                if(node1 != null) {

                    node1.addEdgeBeside(new Edge(edgeWeight, node1, node2));
                    ConnectionNodesMap.put(startStops.get(i), node1);
                }

            }

            Scanner sc3 = new Scanner(stopTimesFile);

            while(sc3.hasNextLine()) {

                String a = sc3.nextLine();
                String stopTimesArr[] = a.split(",");   //splitting the string into an array with an element
                                                                //at each comma

                String tripID = stopTimesArr[0];

                String stopID = stopTimesArr[3];
                String TripIDandStop[] = {tripID,stopID}; //array to be used in each element of the array list
                // details the trip id being referred to and the stop where the trip ends

                tripIdStops.add(TripIDandStop);  //adding tripId and stop from current line to the tripIdStops and array

            }

            String TripID = tripIdStops.get(1)[0];
            String StopID = tripIdStops.get(1)[1];
            ConnectionNode node1 = ConnectionNodesMap.get(StopID);
            for(int j = 2; j < tripIdStops.size(); j++) {

                String TripID2 = tripIdStops.get(j)[0];
                String StopID2 = tripIdStops.get(j)[1];


                if(TripID.equals(TripID2)) {
                    //only forming an edge when two consecutive lines in stop_times file have the same trip ID
                    ConnectionNode node2 = ConnectionNodesMap.get(StopID2);
                    node1.addEdgeBeside(new Edge(1.0,node1,node2));

                    ConnectionNodesMap.put(node1.name, node1);
                    //node1.name = node2;
                    node1 = node2;

                }

                else {
                    TripID =TripID2;
                    ConnectionNode node2 = ConnectionNodesMap.get(StopID2);
                    node1 = node2;
                }

            }

        }  //end of constructor method Section1(files as parameters)

        //STILL TO ADD
            // to add methods on finding the SP and then returning an array list of all intermediate stops included on the SP

            //this mehtod computes SP from startStop to all nodes
            // it returns the path of the SP betwenn start and destination node
            // the path as an ArrayList
            public static List<ConnectionNode> shortestPath(ConnectionNode startStop, ConnectionNode endStop){

            //initialise minDist in ConnectionNode
                startStop.setMinDist(0);

                //creating empty priority queue of connectionNodes
                // will access the weights to compare for SP
                PriorityQueue<ConnectionNode > priorityQueue = new PriorityQueue<>();
                //inserting the source node into the PQ
                priorityQueue.add(startStop);

                //while the PQ isnt empty, the min dist node from the PQ
                while(!priorityQueue.isEmpty()){

                    //extracting min dist node from the PQ and removing
                    ConnectionNode node = priorityQueue.peek();
                    priorityQueue.remove();

                    //for(Node n : List l)
                    //for(Node n = l.head; n.next != null; n = n.next)

                    //looping through all adjacent of node
                    for(Edge edge : node.getEdgesList()){

                        ConnectionNode n = edge.getGoalNode();

                        double dist = edge.getWeight();
                        double minDist = node.getMinDist() + dist;

                        //doing the following for every ConnectionNode n
                        //if there is a shorter path to n through node
                        //update distance of current n to minDist
                        //insert n into the priority queue
                        if (minDist < n.getMinDist()) {
                            priorityQueue.remove(node);
                            n.setPrevNode(node);
                            n.setMinDist(minDist);
                            priorityQueue.add(n);
                        }

                    }

                }

                //need to add eroor handling --> to only return shortest path if the path names are valid etc
                // --> t be done in front end
                List<ConnectionNode> shortestPath = new ArrayList<>();

                cost = endStop.getMinDist();

                //initialising start noed to end and working backwards to find al nodes on the SP found
                for (ConnectionNode nodeOnShortestPath = endStop; nodeOnShortestPath != null; nodeOnShortestPath = nodeOnShortestPath.getPrevNode()) {
                    shortestPath.add(nodeOnShortestPath);
                }

                //reversing the list to give the stops on the SP from the start to the destination, not hte destination to the stop
                for (int k = 0; k < shortestPath.size() -1; k++)
                {
                    shortestPath.add(k, shortestPath.remove(shortestPath.size() -1));
                }


                return shortestPath;

            }
    }




