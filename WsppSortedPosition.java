import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class WsppSortedPosition {
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
                Map<String, ArrayList<Integer>> words = new TreeMap<String, ArrayList<Integer>>();
                int indexStr = 1;
                while (in.myHasNextLine()) {
                    int index = 1;
                    while (in.myHasNextWordInLine()) {
                        String key = in.myNextWord().toLowerCase();
                        if (!words.containsKey(key)) {
                            ArrayList<Integer> indexs = new ArrayList<>();
                            indexs.add(indexStr);
                            indexs.add(index);
                            words.put(key, indexs);
                        } else {
                            words.get(key).add(indexStr);
                            words.get(key).add(index);
                        }
                        index++;
                    }
                    indexStr++;
                }
                try {
                    for (String key : words.keySet()) {
                        int k = words.get(key).size() / 2;
                        out.write(key + " " + k);
                        for (int i = 0; i < words.get(key).size(); i += 2) {
                            out.write(" " + words.get(key).get(i));
                            out.write(":" + words.get(key).get(i + 1));
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
