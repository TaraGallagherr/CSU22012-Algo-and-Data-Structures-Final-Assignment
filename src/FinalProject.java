import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

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

    public static void main(String[] args) throws FileNotFoundException {

        String stopsPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stops.txt";
        String stopTimesPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\stop_times.txt";
        String transfersPath = "C:\\Users\\tarag\\OneDrive\\Documents\\SecondYear\\Semester2\\Algo +Data 2\\src\\transfers.txt";

        //turning paths into files
        new FinalProject(stopsPath, transfersPath, stopTimesPath);

        new Section1(STOPS, STOP_TIMES, TRANSFERS);

        //testing out calling the Section1 class in the final project file.


            //System.out.print(Section1.ConnectionNodesMap);
        System.out.println(Collections.singletonList(Section1.ConnectionNodesMap));
        System.out.println(Section1.costs);
        System.out.println(Section1.finalStops);
        System.out.println(Section1.startStops);





    }

}
