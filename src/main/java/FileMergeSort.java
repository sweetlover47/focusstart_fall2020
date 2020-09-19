import java.io.*;
import java.util.ArrayList;

public class FileMergeSort implements IMergeSort {
    private ArrayList<BufferedReader> readers = new ArrayList<>();
    private FileWriter writer;
    private SortParameter sortParameter = SortParameter.NO_TYPE;

    public void init(String outputFileName, ArrayList<String> inputFileNames, SortParameter sortParameter) {
        try {
            writer = new FileWriter(outputFileName);
            for (String cur : inputFileNames) {
                BufferedReader tmpReader = new BufferedReader(new FileReader(new File(cur)));
                readers.add(tmpReader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sortParameter = sortParameter;
    }

    public void sort() {

        ArrayList<String> lastLines = new ArrayList<>(); //required to match the string and file
        ArrayList<String> sortingList = new ArrayList<>();

        try {
            // Initialize lists with the first lines of all files. If the file is empty, remove it
            for (BufferedReader reader : readers) {
                String line = reader.readLine();
                if (line == null) {
                    readers.remove(reader);
                    continue;
                }
                lastLines.add(line);
                sortingList.add(line);
            }
            while (readers.size() > 0) { //while there is at least one file
                sortingList.sort((o1, o2) -> { //sorting
                    if (sortParameter.getType().equals("-s")) {
                        if (sortParameter.getMode().equals("-i"))
                            return o1.compareTo(o2);
                        else return o2.compareTo(o1);
                    } else {
                        if (Integer.parseInt(o1) == Integer.parseInt(o2))
                            return 0;
                        boolean res;
                        if (sortParameter.getMode().equals("-i")) {
                            res = Integer.parseInt(o1) < Integer.parseInt(o2);
                            return res ? -1 : 1;
                        } else {
                            res = Integer.parseInt(o1) > Integer.parseInt(o2);
                            return res ? -1 : 1;
                        }
                    }
                });
                String line = sortingList.get(0);   //in sortingList gets line(answer for iteration) and write to output file
                writer.write(line + "\n");
                writer.flush();
                int index = lastLines.indexOf(line); // find index of reader, which consist the line
                lastLines.remove(index);
                sortingList.remove(0);
                String updatedLine = readers.get(index).readLine();
                if (updatedLine == null) {  //if file is empty, remove it
                    readers.remove(index);
                    continue;
                }
                lastLines.add(index, updatedLine);  // else add new line to lists
                sortingList.add(0, updatedLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {       //try to close all streams
                writer.close();
                for (BufferedReader reader : readers)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
