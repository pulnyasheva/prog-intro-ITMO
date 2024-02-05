package md2html;

import java.util.List;

public class Emphasis extends AbstractText implements HtmlText {
    Emphasis(List<HtmlText> ht) {
        super(ht);
    }

    public void toHtml(StringBuilder sb) {
        super.toHtml(sb, "em");
    }
}
