package md2html;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Parser {

    private final static Set<Character> tags = Set.of('*', '_');

    public static boolean isTagSymbol(Character ch) {
        // :NOTE: лучше использовать Set
        return ch.equals('*') || ch.equals('_') || ch.equals('-') || ch.equals('`') || ch.equals('\'');
    }

    public static boolean isTag(String s) {
        // :NOTE: лучше использовать Set
        return s.equals("*")
                || s.equals("_")
                || s.equals("**")
                || s.equals("`")
                || s.equals("''")
                || s.equals("**")
                || s.equals("__")
                || s.equals("--");
    }

    public static String replacer(Character ch) {
        return switch (ch) {
            case ('<') -> "&lt;";
            case ('>') -> "&gt;";
            case ('&') -> "&amp;";
            default -> ch.toString();
        };
    }

    public static HtmlText whatIsThis(String s, List<HtmlText> list) {
        return switch (s) {
            case ("*"), ("_") -> new Emphasis(list);
            case ("**"), ("__") -> new Strong(list);
            case ("--") -> new Strikeout(list);
            case ("`") -> new Code(list);
            case ("''") -> new Quote(list);
            default -> throw new AssertionError("Error");
        };
    }

    public static List<HtmlText> parser(BufferedReader in) throws IOException {
        List<HtmlText> ht = new ArrayList<>();
        List<Tag> tags = new ArrayList();
        tags.add(new Tag("-1"));
        String str;
        boolean laststr = true;
        StringBuilder text = new StringBuilder();
        String pokazatel = null;
        int countH;
        // :NOTE: тут лучше использовать char
        Character ch;
        int countS = 0;
        while ((str = in.readLine()) != null) {
            countS++;
            if (!laststr && str.isEmpty()) {
                text.append(pokazatel);
                text.append(System.lineSeparator());
            } else if (!laststr && countS != 1) {
                text.append(System.lineSeparator());
            }
            countH = 0;
            if (laststr && !str.isEmpty()) {
                ch = str.charAt(countH);
                StringBuilder resh = new StringBuilder();
                while (ch.equals('#')) {
                    resh.append('#');
                    ch = str.charAt(++countH);
                }
                if (countH == 0 || countH < str.length() && !Character.isWhitespace(str.charAt(countH))) {
                    text.append("<p>");
                    pokazatel = "</p>";
                    text.append(resh);
                } else {
                    text.append("<h" + countH + ">");
                    pokazatel = "</h" + countH + ">";
                    countH++;
                }
            }
            laststr = str.isEmpty();
            for (int i = countH; i < str.length(); i++) {
                String element = "";
                ch = str.charAt(i);
                if (ch.equals('\\') && i + 1 < str.length() && isTagSymbol(str.charAt(++i))) {
                    text.append(str.charAt(i));
                    continue;
                }
                if (isTagSymbol(ch)) {
                    element = ch.toString();
                    if (i + 1 < str.length() && ch.equals(str.charAt(i + 1))) {
                        element += ch.toString();
                        i++;
                    }
                } else {
                    text.append(replacer(ch));
                }
                if (isTag(element)) {
                    // :NOTE: лучше выделить tags.get(tags.size() - 1) в отдельную переменную
                    if (!text.isEmpty()) {
                        ht.add(new Text(text.toString()));
                        text = new StringBuilder();
                        tags.get(tags.size() - 1).addOne();

                    }
                    if (!element.equals(tags.get(tags.size() - 1).getTag())
                            && i > 0
                            && str.charAt(i - 1) == ' '
                            && (i + 1 < str.length()
                            && (str.charAt(i + 1) == ' ')
                            || i + 1 == str.length())) {
                        text.append(element);
                        continue;
                    }
                    if (element.equals(tags.get(tags.size() - 1).getTag())) {
                        List<HtmlText> thisText = new ArrayList<>();
                        for (int j = tags.get(tags.size() - 1).getCount(); j > 0; j--) {
                            thisText.add(ht.get(ht.size() - j));
                            ht.remove(ht.size() - j);
                        }
                        HtmlText newElement = whatIsThis(tags.get(tags.size() - 1).getTag(), thisText);
                        ht.add(newElement);
                        tags.remove(tags.size() - 1);
                        tags.get(tags.size() - 1).addOne();
                    } else {
                        tags.add(new Tag(element));
                    }
                } else if (!element.isEmpty()) {
                    text.append(element);
                }
            }
        }
        ht.add(new Text(text.toString()));
        ht.add(new Text(pokazatel + System.lineSeparator()));
        return ht;
    }
}
