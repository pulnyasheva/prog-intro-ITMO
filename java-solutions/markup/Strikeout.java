package markup;

import java.util.List;

public class Strikeout extends AbstractText implements MarkDownText {
    Strikeout(List<MarkDownText> mdt) {
        super(mdt);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append('~');
        super.toMarkdown(sb);
        sb.append('~');
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<s>");
        super.toHtml(sb);
        sb.append("</s>");
    }
}