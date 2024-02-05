package markup;

import java.util.List;

public class Paragraph extends AbstractText implements MarkDownText {


    public Paragraph(List<MarkDownText> list) {
        super(list);
    }

    public void toMarkdown(StringBuilder sb) {
        super.toMarkdown(sb);
    }

    public void toHtml(StringBuilder sb) {
        super.toHtml(sb);
    }
}
