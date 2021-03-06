import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FinalProject {

    public static File STOPS;
    public static File STOP_TIMES;
    public static File TRANSFERS;

    //constructor method for loading file paths into  static variable names
    public FinalProject(String stopsPath, String transfersPath, String stopTimesPath){

        STOPS = new File(stopsPath);
        STOP_TIMES = new File(stopTimesPath);
        TRANSFERS = new File(transfersPath);
    }

    public static void userInteraction() throws IOException {

        String stopsPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stops.txt";
        String stopTimesPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stop_times.txt";
        String transfersPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\transfers.txt";

        //turning paths into files
        new FinalProject(stopsPath, transfersPath, stopTimesPath);

        Section1 Section1 = new Section1(STOPS, STOP_TIMES, TRANSFERS);

        boolean quit = false;

        do {

            Scanner userInput = new Scanner(System.in);

            System.out.println("Please select one of the following options by entering 1,2,3 or 0 to exit the program");
            System.out.println("1 = Find the shortest path between 2 bus stops " + "\n"
                    + "2 = Search for a particular bus stop " + "\n"
                    + "3 = Search for an arrival time");

            if (userInput.hasNextInt()) {

                int input = userInput.nextInt();
                userInput.nextLine();
                if (input >= 0 && input <= 3) {

                    if (input == 0) {
                        System.out.println("Exiting the program");
                        quit = true;
                    } else if (input == 1) {
                        String startInput;
                        String destinationInput = "";
                        //making a hashMap for the stops for dijkstra
                        HashMap<String, ConnectionNode> stops = Section1.ConnectionNodesMap;

                        boolean valid = false;
                        do {

                            System.out.println("PLease enter a starting bus stop ID:");
                            //USER input
                            String tempStart = userInput.nextLine();
                            startInput = tempStart;

                            System.out.println("Please enter a destination bus stop ID: ");
                            //user input
                            String tempDest;
                            tempDest = userInput.nextLine();
                            destinationInput = tempDest;

                            //error handling
                            // calling isNumeric method to make the stop IDs entered are check
                            if (isNumeric(startInput) == false || isNumeric(destinationInput) == false) {
                                System.out.println("Your start and destination stop ID should only contain digits and should not be null");

                            }

                            //also need to make sure the bus stop IDs entered exist

                            else if (Section1.validStops(startInput, destinationInput) == false) {
                                System.out.println("none or one of the stops entered does not exist. try again");
                            }

                            //otherwise the stops are valid and can continue onto answering part 1
                            else {
                                //setting to false if they are valid so it will exit hte while loop
                                valid = true;
                            }

                        } while (!valid);

                        valid = false;
                        ConnectionNode source = stops.get(startInput); //  ConnectionNode of the start stop
                        ConnectionNode destination = stops.get(destinationInput);// ConnectionNode object of the destination stop

                        //working out shortest path and then printing out the path
                        System.out.println("The Shortest Path of bus stops from " + source.name + " to " + destination.name + " is "
                                + Section1.shortestPath(source, destination));

                        //Section1.cost ---> give the cost of the SP calculated--> print it out
                        System.out.println("Cost of the Path: " + Section1.cost + "\n");

                    } else if (input == 2) {

                        System.out.print(
                                "Please enter the bus stop's full name or by the first few characters in the name: ");
                        String stopInput = userInput.nextLine();

                        new Section2(STOPS, stopInput);


                    } else if (input == 3) {
                        //String userTimeInput = " ";
                        // System.out.print("Please enter an arrival time: ");

                        //will error handle inside the
                        new Section3(STOP_TIMES);
                    }

                }


            //number > 3 or < 1
            else {
                System.out.println("Please check your number entered");
            }
        }

            //a letter/ symbol entered
            else{
                System.out.println("Please enter numbers only" );
            }
        }
        while(!quit);

    }

    //method to check if the stop ID entered is numeric and not null. returns false if it is either
    public static boolean isNumeric(String strEntry){
        if (strEntry == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strEntry);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }



    public static void main(String[] args) throws IOException {

        String stopsPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stops.txt";
        String stopTimesPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stop_times.txt";
        String transfersPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\transfers.txt";

        //turning paths into files
        new FinalProject(stopsPath, transfersPath, stopTimesPath);

        //new Section1(STOPS, TRANSFERS, STOP_TIMES);

        //new Section3(STOP_TIMES);

        userInteraction();

        //testing out calling the Section1 class in the final project file.


            //System.out.print(Section1.ConnectionNodesMap);
        /*
        System.out.println(Collections.singletonList(Section1.ConnectionNodesMap));
        System.out.println(Section1.costs);
        System.out.println(Section1.finalStops);
        System.out.println(Section1.startStops);
        */

        //testing out shortest path method
        //ConnectionNode start = new ConnectionNode("1888");
        //ConnectionNode end = new ConnectionNode("11259");

        //System.out.print(Section1.shortestPath(start, end));

        //FinalProject.userInteraction();
    }



}
