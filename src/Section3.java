
/*
    Searching for all trips with a given arrival time,
    returning full details of all trips matching the criteria (zero, one or more), sorted by trip id

    Arrival time should be provided by the user as hh:mm:ss.
    When reading in stop_times.txt file you will need to remove all invalid times,
    e.g., there are times in the file that start at 27/28 hours, so are clearly invalid.
    Maximum time allowed is 23:59:59.
     */

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Section3 {

    public ArrayList<String[]> readInFile(File stopTimesFile) throws FileNotFoundException {

        Scanner scanner = new Scanner(stopTimesFile);
        ArrayList<String[]> stopTimes = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            String stopTimesArr[] = currentLine.split(",");
            stopTimes.add(stopTimesArr);


        }

            return stopTimes;
    }

}
