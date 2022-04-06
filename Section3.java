
/*
    Searching for all trips with a given arrival time,
    returning full details of all trips matching the criteria (zero, one or more), sorted by trip id

    Arrival time should be provided by the user as hh:mm:ss.
    When reading in stop_times.txt file you will need to remove all invalid times,
    e.g., there are times in the file that start at 27/28 hours, so are clearly invalid.
    Maximum time allowed is 23:59:59.
     */

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Section3 {

    //public static ArrayList<String> stopTimes = new ArrayList<String>();
    public static ArrayList<TripInfo> validTrips; //arraylist of TripInfo objects

    //making a constructor method to read all lines of file into an array list
    public Section3(File stopTimesFile) throws IOException {



           // public ArrayList<TripInfo> validTrips; //arraylist of TripInfo objects

               // validTrips = new ArrayList<>(); //makes array list if no issues reading file

                //calling readStopTimesFile method
                readStopTimesFile(stopTimesFile);

                //checking to see if array list was made --> for main
                System.out.print(validTrips);
            }





        public void readStopTimesFile(File stopTimesFile) throws IOException {

            int lineCount = 0;
            int lineSize = 9; //---> represents how many elements of trip info in a line

            //Scanner scanner = new Scanner(stopTimesFile);
            //ArrayList<String[]> stopTimes = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader(stopTimesFile));
            String str;
            try {
                while ((str = br.readLine()) != null) {


                    //initialising all trip info
                    if (lineCount >= 1) {  //first currentLine is the titles of the trip information
                        int tripID = 0;
                        int stopID = 0;
                        int stopSequence = 0;
                        int stopHeadsign = 0;
                        int pickupType = 0;
                        int dropOffType = 0;
                        double shapeDistTraveled = 0;
                        String arrivalTime = "";
                        String departureTime = "";

                        String[] currentLine = str.split(",");
                        //String currentLine = scanner.nextLine();
                        //String stopTimesArr[] = currentLine.split(",");
                        //stopTimes.add(stopTimesArr);

                        for (int i = 0; i < lineSize; i++) {

                            if (!currentLine[i].equals("") && i == 0) {

                                tripID = Integer.parseInt(currentLine[i]);
                            }

                    if(!currentLine[i].equals("") && i == 1){

                        arrivalTime = currentLine[i];
                    }

                    if(!currentLine[i].equals("") && i == 2){

                        departureTime = currentLine[i];
                    }



                            if (!currentLine[i].equals("") && i == 3) {

                                stopID = Integer.parseInt(currentLine[i]);
                            }

                            if (!currentLine[i].equals("") && i == 4) {

                                stopSequence = Integer.parseInt(currentLine[i]);
                            }

                            if (!currentLine[i].equals("") && i == 5) {

                                stopHeadsign = Integer.parseInt(currentLine[i]);
                            }

                            if (!currentLine[i].equals("") && i == 6) {

                                pickupType = Integer.parseInt(currentLine[i]);
                            }

                            if (!currentLine[i].equals("") && i == 7) {

                                dropOffType = Integer.parseInt(currentLine[i]);
                            }

                            if (!currentLine[i].equals("") && i == 8) {

                                shapeDistTraveled = Double.parseDouble(currentLine[i]);
                            }

                        }

                        //checking if time is valid and only creating a trip info object if it is
                        if (validTime(arrivalTime) && validTime(departureTime)) {
                            validTrips.add(new TripInfo(tripID, arrivalTime, departureTime, stopID, stopSequence,
                                    stopHeadsign, pickupType, dropOffType, shapeDistTraveled));
                        }

                    }

                }
            
        } catch (Exception e) {
                System.out.println(e);
            }
        }
                
                

                //this method checks to see if the time is valid and returns a boolean with the result
                // needed for part 3 but also wont add a
                public boolean validTime(String time){

                    final int MAX_HRS = 23;
                    final int MAX_MIN = 59;
                    final int MAX_SEC = 59;

                    //taking out all white space characters from string
                    String time_without_space = time.replaceAll("\\s", "");


                    //creating a string array that splits up the time into hours, minutes and seconds
                    String[] individualParts = time_without_space.split(":");

                    //initialising int variables for time to 0
                    int currentHours = 0;
                    int currentMinutes = 0;
                    int currentSeconds = 0;

                    try {
                        //turning each section of string into integers and updating the variables
                        currentHours = Integer.parseInt(individualParts[0]);
                        currentMinutes = Integer.parseInt(individualParts[1]);
                        currentSeconds = Integer.parseInt(individualParts[2]);
                    } catch (Exception e) {
                        // System.err.println(e);
                        return false;
                    }


                    //checking to see if the time is valid and returning a boolean
                    //comparing current variables with the max times
                    if((currentHours <= MAX_HRS) && (currentMinutes <= MAX_MIN) && (currentSeconds <= MAX_SEC))
                        return true;

                    else
                        return false;  //invalid time
                }

                public ArrayList<TripInfo> matchingTimes(String time, File stopTimesFile) throws IOException {

                        // create array list of all valid trips
                         readStopTimesFile(stopTimesFile);

                          ArrayList<TripInfo> results = new ArrayList<TripInfo>();

                          TripInfo currentTrip = new TripInfo();
                         for(int i = 0; i < validTrips.size(); i++){

                             currentTrip = validTrips.get(i);

                             if(currentTrip.arrival_time == time){

                                 //adding currentTrip to results array list if the arrival time matches
                                 results.add(currentTrip);
                             }

                         }

                        return results;
                }

                public ArrayList<TripInfo> orderResults(ArrayList<TripInfo> results){

                    //Collections.sort(results, (stopID) -> parseId(a) - parseId(b));

                    return results;
                }


                static int parseId(String line) {
                     return Integer.parseInt(line.split(",")[0]);
                }


}
