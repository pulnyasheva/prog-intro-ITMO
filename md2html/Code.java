package md2html;

import java.util.List;

public class Code extends AbstractText implements HtmlText {
    Code(List<HtmlText> ht) {
        super(ht);
    }

    public void toHtml(StringBuilder sb) {
        super.toHtml(sb, "code");
    }
}