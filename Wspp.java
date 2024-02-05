import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Wspp {

    public static void main(String[] args) {
        try {
            File fileIn = new File(args[0]);
            MyScanner in = new MyScanner(fileIn);
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1]), "utf-8"
                    )
            );
            try {
                Map<String, ArrayList<Integer>> words = new LinkedHashMap<String, ArrayList<Integer>>();
                int index = 1;
                while (in.myHasNextWord()) {
                    String key = in.myNextWord().toLowerCase();
                    if (!words.containsKey(key)) {
                        ArrayList<Integer> indexs = new ArrayList<>();
                        indexs.add(index);
                        words.put(key, indexs);
                    } else {
                        words.get(key).add(index);
                    }
                    index++;
                }
                try {
                    for (String key : words.keySet()) {
                        out.write(key + " " + words.get(key).size());
                        for (int i : words.get(key)) {
                            out.write(" " + i);
                        }
                        out.write("\n");
                    }
                } finally {
                    out.close();
                }
            } finally {
                in.myClose();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}