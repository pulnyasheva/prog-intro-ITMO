package markup;

import java.util.List;

public class Strong extends AbstractText implements MarkDownText {
    Strong(List<MarkDownText> mdt) {
        super(mdt);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("__");
        super.toMarkdown(sb);
        sb.append("__");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<strong>");
        super.toHtml(sb);
        sb.append("</strong>");
    }
}
