package md2html;

import java.util.List;

public class Quote extends AbstractText implements HtmlText {
    Quote(List<HtmlText> ht) {
        super(ht);
    }

    public void toHtml(StringBuilder sb) {
        super.toHtml(sb, "q");
    }
}
