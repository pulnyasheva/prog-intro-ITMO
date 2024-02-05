package md2html;

import java.util.List;

public class Strong extends AbstractText implements HtmlText {
    Strong(List<HtmlText> ht) {
        super(ht);
    }

    public void toHtml(StringBuilder sb) {
        super.toHtml(sb, "strong");
    }
}
