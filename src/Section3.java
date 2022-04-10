
/*
    Searching for all trips with a given arrival time,
    returning full details of all trips matching the criteria (zero, one or more), sorted by trip id

    Arrival time should be provided by the user as hh:mm:ss.
    When reading in stop_times.txt file you will need to remove all invalid times,
    e.g., there are times in the file that start at 27/28 hours, so are clearly invalid.
    Maximum time allowed is 23:59:59.
     */

import java.io.*;
import java.util.*;

public class Section3 {

    //public static ArrayList<String> stopTimes = new ArrayList<String>();
    public ArrayList<TripInfo> validTrips = new ArrayList<TripInfo>(); //arraylist of TripInfo objects

    //making a constructor method to read all lines of file (which re valid) into an array list
    //in FinalProject i handle errors and make sure the time entered by user is valid
    //it then makes an array list of all matching times to the string time inputted
    //this array list is then ordered by trip_id and printed out for the user
    //Section3 is only executed if the time input is correct
    public Section3(File stopTimesFile) throws IOException {

        //public ArrayList<TripInfo> validTrips; //arraylist of TripInfo objects
         String userTimeEntry = "";

        validTrips = new ArrayList<>(); //makes array list if no issues reading file

       // ArrayList<String> results = new ArrayList();

        Scanner input = new Scanner(System.in);
        boolean properTime = false;
        //boolean exit = true;

        do {
            System.out.println("Enter an arrival time in the format HH:MM:SS: ");
           // boolean validT = false;

            userTimeEntry = input.next();

            properTime = validTime(userTimeEntry);

            //else {
            if (properTime == false) {
                System.out.println("Your input is not a valid time. Please have the right format and do not exceed" +
                        " 23:59:59");
            }

        }
        while(properTime == false);

        if(properTime == true) {
            try {

                //System.out.println(matchingTimes(userTimeEntry));


                //calling readStopTimesFile method
                //readStopTimesFile(stopTimesFile);

                //checking to see if array list was made --> for main
                //unordered and no parsing of matching times
                //System.out.print(validTrips);

                //method call to print out matching times arrayList
                //printing out the results array
                //System.out.println(matchingTimes(userTimeEntry));
                    readStopTimesFile(stopTimesFile);

                 if(matchingTimes(userTimeEntry) == null) {
                    System.out.println("Arrival Time Could not be found");
                }

                 else{
                     System.out.println(matchingTimes(userTimeEntry));
                 }
            } catch (Exception e) {
                System.out.println("Not a valid input.");
            }
        }
    }

        public void readStopTimesFile(File stopTimesFile) throws IOException {

            int lineCount = 0;
            BufferedReader br = new BufferedReader(new FileReader(stopTimesFile));
            //int listCount = 0;
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
                        String arrivalTime ;
                        String departureTime ;

                        String[] currentLine = str.split(",");
                        //String currentLine = scanner.nextLine();
                        //String stopTimesArr[] = currentLine.split(",");
                        //stopTimes.add(stopTimesArr);

                         arrivalTime = currentLine[1];
                         departureTime = currentLine[2];

                        if (!currentLine[0].equals("")) {
                            tripID = Integer.parseInt(currentLine[0]);
                        }
                        if (!currentLine[3].equals("")) {
                            stopID = Integer.parseInt(currentLine[3]);
                        }
                        if (!currentLine[4].equals("")) {
                            stopSequence = Integer.parseInt(currentLine[4]);
                        }
                        if (!currentLine[5].equals("")) {
                            stopHeadsign = Integer.parseInt(currentLine[5]);
                        }
                        if (!currentLine[6].equals("")) {
                            pickupType = Integer.parseInt(currentLine[6]);
                        }
                        if (!currentLine[7].equals("")) {
                            dropOffType = Integer.parseInt(currentLine[7]);
                        }
                        if ((currentLine.length == 9) && !currentLine[8].equals("")) {
                            shapeDistTraveled = Float.parseFloat(currentLine[8]);
                        }

                        //checking if time is valid and only creating a trip info object if it is
                        if (validTime(arrivalTime) && validTime(departureTime)) {
                            validTrips.add(new TripInfo(tripID, arrivalTime, departureTime, stopID, stopSequence,
                                    stopHeadsign, pickupType, dropOffType, shapeDistTraveled));
                               // listCount ++;
                    }

                        }

                        lineCount++;
                    }
                //System.out.println(validTrips);
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

                //public ArrayList<TripInfo> matchingTimes(String time, File stopTimesFile) throws IOException {
                public ArrayList<TripInfo> matchingTimes(String time) {

                        // create array list of all valid trips
                        // readStopTimesFile(stopTimesFile);

                          ArrayList<TripInfo> results = new ArrayList<>();
                          String currentTime = "";

                          TripInfo currentTrip = new TripInfo(0,"", "", 0, 0, 0, 0, 0, 0.0 );
                         for(int i = 0; i < validTrips.size(); i++){

                             currentTrip = validTrips.get(i);
                             currentTime = validTrips.get(i).getTime();
                             currentTime = currentTime.replaceAll("\\s", "");

                             if(currentTime.equals(time)){

                                 //adding currentTrip to results array list if the arrival time matches
                                 results.add(currentTrip);
                             }
                         }

                        return orderResults(results);  //return the results in order
                        //return results;
                }

                public ArrayList<TripInfo> orderResults(ArrayList<TripInfo> results){

                    Collections.sort(results, Comparator.comparingInt(Section3::TripIDInt));

                    return results;
                }

                 static int TripIDInt(TripInfo t) {
                     return Integer.parseInt(t.getTripID());
                 }

            }


