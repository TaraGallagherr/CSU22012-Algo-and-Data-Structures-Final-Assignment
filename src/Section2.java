import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

//https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/TST.java.html -->to help with implementation of TST
//https://beginnersbook.com/2013/12/java-string-substring-method-example/ --> for how to move part of string to end
//https://www.simplilearn.com/tutorials/java-tutorial/stringbuilder-in-java#:~:text=StringBuilder%20in%20Java%20is%20a,an%20immutable%20succession%20of%20characters.
// --> to put str elem together


/*
    Searching for a bus stop by full name or by the first few characters in the name,
    using a ternary search tree (TST),
    returning the full stop information for each stop matching the
    search criteria (which can be zero, one or more stops)
    In order for this to provide meaningful search functionality
    please move keywords flagstop, wb, nb, sb, eb from start of the names to the end of the names of the stops
    when reading the file into a TST
    (eg “WB HASTINGS ST FS HOLDOM AVE” becomes “HASTINGS ST FS HOLDOM AVE WB”)
     */

public class Section2 {

        private HashMap<String, String> map;
        private int size; // size
        private Node<String> root; // root of TST (node with no parents)


        public class Node<String>
    {
        char c; // character
         Node<String> left, mid, right; // left, middle, and right subtries
         String val; // String associated with string
    }


        //from princeton
        /**
         * Does this symbol table contain the given key?
         *
         * @param key the key
         * @return {@code true} if this symbol table contains {@code key} and
         *         {@code false} otherwise
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public boolean contains(String key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to contains() is null");
            }
            return get(key) != null;
        }

        //from princeton
        /**
         * Returns the String associated with the given key.
         *
         * @param key the key
         * @return the String associated with the given key if the key is in the symbol
         *         table and {@code null} if the key is not in the symbol table
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public String get(String key) {
            if (key == null) {
                throw new IllegalArgumentException("calls get() with null argument");
            }
            if (key.length() == 0)
                throw new IllegalArgumentException("key must have length >= 1");
            Node<String> x = get(root, key, 0);
            if (x == null)
                return null;
            return x.val;
        }

        //from princeton
        // return subtrie corresponding to given key
        private Node<String> get(Node<String> x, String key, int d) {
            if (x == null)
                return null;
            if (key.length() == 0)
                throw new IllegalArgumentException("key must have length >= 1");
            char c = key.charAt(d);
            if (c < x.c)
                return get(x.left, key, d);
            else if (c > x.c)
                return get(x.right, key, d);
            else if (d < key.length() - 1)
                return get(x.mid, key, d + 1);
            else
                return x;
        }

        /**
         * Inserts the key-String pair into the symbol table, overwriting the old String
         * with the new String if the key is already in the symbol table. If the String
         * is {@code null}, this effectively deletes the key from the symbol table.
         *
         * @param key the key
         * @param val the String
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void put(String key, String val) {
            if (key == null) {
                throw new IllegalArgumentException("calls put() with null key");
            }
            if (!contains(key))
                size++;
            else if (val == null)
                size--; // delete existing key
            root = put(root, key, val, 0);
        }

        private Node<String> put(Node<String> x, String key, String val, int d) {
            char c = key.charAt(d);
            if (x == null) {
                x = new Node<String>();
                x.c = c;
            }
            if (c < x.c)
                x.left = put(x.left, key, val, d);
            else if (c > x.c)
                x.right = put(x.right, key, val, d);
            else if (d < key.length() - 1)
                x.mid = put(x.mid, key, val, d + 1);
            else
                x.val = val;
            return x;
        }

        //princeton
        /**
         * Returns all of the keys in the set that start with {@code prefix}.
         *
         * @param prefix the prefix
         * @return all of the keys in the set that start with {@code prefix}, as an
         *         iterable
         * @throws IllegalArgumentException if {@code prefix} is {@code null}
         */
        public Iterable<String> keysWithPrefix(String prefix) {
            if (prefix == null) {
                throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
            }
            Queue<String> queue = new LinkedList<String>();
            Node<String> x = get(root, prefix, 0);
            if (x == null)
                return queue;
            if (x.val != null)
                queue.add(prefix);
            collect(x.mid, new StringBuilder(prefix), queue);
            return queue;
        }

        // all keys in subtrie rooted at x with given prefix
        private void collect(Node<String> x, StringBuilder prefix, Queue<String> queue) {
            if (x == null)
                return;
            collect(x.left, prefix, queue);
            if (x.val != null)
                queue.add(prefix.toString() + x.c);
            collect(x.mid, prefix.append(x.c), queue);
            prefix.deleteCharAt(prefix.length() - 1);
            collect(x.right, prefix, queue);
        }

        //used in main 
        public Section2(File file, String stopInput) {

            //initialise scanner
            Scanner sc = null;
            //testing file
            try {
                sc = new Scanner(file);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            sc.nextLine(); // remove the first line --> only columns 
            map = new HashMap<String, String>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] currentLine = line.split(",");

                //parsing stopID for the map
                String stopId = currentLine[0];

                this.put(formatName(currentLine), stopId);

                String stopInfo = formatStops(currentLine);
                map.put(stopId, stopInfo);
            }

            //printing out the stops with matching names to the user input
            getMatchingStopNames(stopInput).forEach((info) -> {
                System.out.println(info);
            });
        }

        //putting the prefixes NB, SB, WB, EB to the end of the bus stop name
        public String formatName(String [] currentLine){
            StringBuilder sb = new StringBuilder();
            sb.append(currentLine[2]);

            if (sb.substring(0, 8).equals("FLAGSTOP")) {
                //storing in a temp variable
                String temp = sb.substring(0, 11);
                //dleting from start
                sb.delete(0, 12);
                //adding temp to end
                sb.append(" " + temp);
            }
            else if (sb.substring(0, 2).equals("NB") || sb.substring(0, 2).equals("SB")
                    || sb.substring(0, 2).equals("WB") || sb.substring(0, 2).equals("EB")) {
                //store in temp
                String temp= sb.substring(0, 2);
                //delete from start
                sb.delete(0, 3);
                //add back to end 
                sb.append(" " + temp);
            }

            //stop name modified with the start letters to end
           return( sb.toString());
        }

        //putting all info on the stop into the stop name
        public String formatStops(String [] currentLine){

            //using stringBuilder to make a string of all bus stop in the stops file
            // returns the valid bus stops with a format
            StringBuilder strBuilderStops = new StringBuilder();
            strBuilderStops.append("stopID: " + currentLine[0] + "\n");
            strBuilderStops.append("stopCode: " + currentLine[1] + "\n");
            strBuilderStops.append("stopName: " + formatName(currentLine) + "\n");
            strBuilderStops.append("stopDesc: " + currentLine[3] + "\n");

            if(currentLine.length > 4) {
                strBuilderStops.append("stopLatitude: " + currentLine[4] + "\n");
            }
            if(currentLine.length > 5) {
                strBuilderStops.append("stopLongitude: " + currentLine[5] + "\n");
            }
            if(currentLine.length > 6) {
                strBuilderStops.append("zoneID: " + currentLine[6] + "\n");
            }
            if(currentLine.length > 8) {
                strBuilderStops.append("locationType: " + currentLine[8] + "\n");
            }

            return(strBuilderStops.toString());
        }

        public List<String> getMatchingStopNames(String input) {
            List<String> list = new LinkedList<>();
            this.keysWithPrefix(input).forEach((info) -> {
                list.add(map.get(this.get(info)));
            });
            if (list.isEmpty()) {
                list.add("The Bus stop entered doesnt exist \n");
            }
            else{
                System.out.println("Here are the results: ");
            }
            return list;
        }

    }






