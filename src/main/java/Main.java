import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws WrongArgsException {
        SortParameter sortParameter = SortParameter.NO_TYPE;
        String out = "";
        ArrayList<String> in = new ArrayList<>();
        try {
            for (String arg : args) {
                if (arg.contains("-")) { //if mode/type argument
                    if (arg.equals("-a") || arg.equals("-d"))
                        sortParameter.setMode(arg);
                    else if (arg.equals("-s") || arg.equals("-i"))
                        sortParameter.setType(arg);
                    else throw new IllegalArgumentException("Mode or type argument is incorrect");
                } else {
                    if (out.equals("")) out = arg;
                    else in.add(arg);
                }
            }
            sortParameter = sortParameter.getFinalSortParameter();
            if (sortParameter == SortParameter.NO_TYPE || out.equals("") || in.size() == 0) {
                throw new IllegalArgumentException("Not enough args");
            }
        } catch (IllegalArgumentException e) {
            throw new WrongArgsException(e);
        }
        IMergeSort mergeSort = new FileMergeSort();
        mergeSort.init(out, in, sortParameter);
        mergeSort.sort();
    }
}
