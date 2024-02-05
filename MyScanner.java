import java.io.*;

public class MyScanner {
    private Reader reader;
    private int index;

    public MyScanner() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in, "utf-8"), 512);
        index = reader.read();
    }

    public MyScanner(String s) throws IOException {
        reader = new BufferedReader(new StringReader(s), 512);
        index = reader.read();
    }

    public MyScanner(File file) throws IOException {
        reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "utf-8"
                )
        );
        index = reader.read();
    }

    private boolean isNeededChar(char index) {
        return !(index == '\r' | index == '\n' | String.valueOf(index).equals(System.lineSeparator()));
    }

    private boolean isNeededNumber(char index) {
        return Character.isAlphabetic(index) | Character.isDigit(index) | index == '-';
    }

    private boolean isNeededLetter(char index) {
        return (Character.isLetter(index) | Character.getType(index) == Character.DASH_PUNCTUATION | index == '\'');
    }

    public boolean myHasNextLine() throws IOException {
        return index != -1;
    }

    public boolean myHasNextInt() throws IOException {
        while (!isNeededNumber((char) index) && index != -1) {
            index = reader.read();
        }
        return index != -1;
    }

    public boolean myHasNextWord() throws IOException {
        while (!isNeededLetter((char) index) && index != -1) {
            index = reader.read();
        }
        return index != -1;
    }

    public boolean myHasNextWordInLine() throws IOException {
        while (!isNeededLetter((char) index) && index != -1
                && (char) index != '\r'
                && (char) index != '\n'
                && !(String.valueOf((char) index).equals(System.lineSeparator()))) {
            index = reader.read();
        }
        if (index != -1) {
            if ((char) index == '\r') {
                index = reader.read();
                if ((char) index == '\n') {
                    index = reader.read();
                    return false;
                }
                return false;
            } else if ((String.valueOf((char) index).equals(System.lineSeparator()))) {
                index = reader.read();
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public String myNextLine() throws IOException {
        StringBuilder newLine = new StringBuilder();
        while (isNeededChar((char) index) && index != -1) {
            newLine.append((char) index);
            index = reader.read();
        }
        if (index != -1) {
            if ((char) index == '\r') {
                index = reader.read();
                if ((char) index == '\n') {
                    index = reader.read();
                }
            } else if ((char) index == '\n') {
                index = reader.read();
            }
        }
        return newLine.toString();
    }

    public int myNextInt(int number) throws IOException {
        StringBuilder newNumber = new StringBuilder();
        while (isNeededNumber((char) index) && index != -1) {
            newNumber.append((char) index);
            index = reader.read();
        }
        if (number == 16) {
            return Integer.parseUnsignedInt(newNumber.toString(), 16);
        }
        return Integer.parseInt(newNumber.toString());

    }

    public String myNextWord() throws IOException {
        StringBuilder newWord = new StringBuilder();
        while (isNeededLetter((char) index) && index != -1) {
            newWord.append((char) index);
            index = reader.read();
        }
        return newWord.toString();
    }

    public void myClose() throws IOException {
        reader.close();
    }
}