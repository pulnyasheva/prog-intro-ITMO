package markup;

public class Text implements MarkDownText {
    private String s;

    Text(String s) {
        this.s = s;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(s);
    }

    public void toHtml(StringBuilder sb) {
        sb.append(s);
    }
}