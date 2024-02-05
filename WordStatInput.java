import java.io.*;

public class WordStatInput {
    public static boolean isBeginWord(Character ch) {
        return (Character.isLetter(ch) | Character.getType(ch) == Character.DASH_PUNCTUATION | ch == '\'');
    }

    public static int indexWord(String[] str, String word) {
        int index = -1;
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(word)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static String[] add1(String[] str, String word) {
        String[] str1 = new String[str.length + 1];
        for (int i = 0; i < str.length; i++) {
            str1[i] = str[i];
        }
        str1[str.length] = word;
        return str1;
    }

    public static int[] add2(int[] amount) {
        int[] amount1 = new int[amount.length + 1];
        for (int i = 0; i < amount.length; i++) {
            amount1[i] = amount[i];
        }
        amount1[amount.length] = 1;
        return amount1;
    }

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(args[0]), "utf-8"
                    )
            );
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1]), "utf-8"
                    )
            );
            try {
                String str;
                int beginIndex;
                String[] words = new String[0];
                int[] count = new int[0];
                int indexWord = 0;
                while ((str = in.readLine()) != null) {
                    for (int i = 0; i < str.length(); i++) {
                        if (isBeginWord(str.charAt(i))) {
                            beginIndex = i;
                            i++;
                            while (i < str.length() && isBeginWord(str.charAt(i))) {
                                i++;
                            }
                            String thisword = str.substring(beginIndex, i).toLowerCase();
                            if (indexWord(words, thisword) == -1) {
                                words = add1(words, thisword);
                                count = add2(count);
                            } else {
                                count[indexWord(words, thisword)]++;
                            }
                        }
                    }
                }
                try {
                    for (int i = 0; i < words.length; i++) {
                        out.write(words[i] + " " + count[i] + "\n");
                    }
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}