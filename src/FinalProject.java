import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class FinalProject {

    public static File STOPS;
    public static File STOP_TIMES;
    public static File TRANSFERS;

    //constructor method for loading file paths into  static variable names
    public FinalProject(String stopsPath, String stopTimesPath, String transfersPath){

        STOPS = new File(stopsPath);
        STOP_TIMES = new File(stopTimesPath);
        TRANSFERS = new File(transfersPath);
    }

    public void userInteraction() throws FileNotFoundException {

        String stopsPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stops.txt";
        String stopTimesPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stop_times.txt";
        String transfersPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\transfers.txt";

        //turning paths into files
        new FinalProject(stopsPath, transfersPath, stopTimesPath);

        new Section1(STOPS, STOP_TIMES, TRANSFERS);

        boolean quit = false;

        do{
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please select one of the following options by entering 1,2,3 or 0 to exit the program");
            System.out.println("1 = Find the shortest path between 2 bus stops" + "/n"
                    + "2 = Search for a particular bus stop" + "/n"
                    + "3 = Seach for an arrival time");

            if(userInput.hasNextInt()){

                int input = userInput.nextInt();

                if(input >= 0 && input <=3){

                    if(input == 0){
                        System.out.println("Exiting the program");
                        quit = true;
                    }

                    else if ( input == 1){

                        String startInput;
                        String destinationInput;
                        Double cost = Section1.cost;

                        //making a hashMap for the stops for dijkstra
                        HashMap<String, ConnectionNode> stops = Section1.ConnectionNodesMap;

                        System.out.print("PLease enter a starting bus stop ID:");
                         //USER input
                        startInput = userInput.nextLine();

                        System.out.print("Please enter a destination bus stop ID: ");
                         //user input
                         destinationInput = userInput.nextLine();

                         //error handling
                         if( isNumeric(startInput) == false || isNumeric(destinationInput) == false){
                             System.out.println("Your start and destination stop ID should only contain digits and should not be null");
                         }

                         else{

                         }

                    }


                    else if( input == 2){

                    }

                    else if (input == 3){

                    }

                }

                else{
                    System.out.println("Please enter a valid number");
                }

            }

            else{
                System.out.print("Please enter a valid number (1,2,3) or press 0 to exit the programme" );
            }




        }
        while(!quit);



    }

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

    public static void main(String[] args) throws FileNotFoundException {

        String stopsPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stops.txt";
        String stopTimesPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stop_times.txt";
        String transfersPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\transfers.txt";

        //turning paths into files
        new FinalProject(stopsPath, transfersPath, stopTimesPath);

        new Section1(STOPS, STOP_TIMES, TRANSFERS);

        //testing out calling the Section1 class in the final project file.


            //System.out.print(Section1.ConnectionNodesMap);
        /*
        System.out.println(Collections.singletonList(Section1.ConnectionNodesMap));
        System.out.println(Section1.costs);
        System.out.println(Section1.finalStops);
        System.out.println(Section1.startStops);
        */

        //testing out shortest path method
        ConnectionNode start = new ConnectionNode("1888");
        ConnectionNode end = new ConnectionNode("11259");

        System.out.print(Section1.shortestPath(start, end));





    }

}
