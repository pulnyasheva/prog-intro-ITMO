package markup;

import java.util.List;

public class Emphasis extends AbstractText implements MarkDownText {
    Emphasis(List<MarkDownText> mdt) {
        super(mdt);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append('*');
        super.toMarkdown(sb);
        sb.append('*');
    }

    public void toHtml(StringBuilder sb) {
        sb.append("<em>");
        super.toHtml(sb);
        sb.append("</em>");
    }
}