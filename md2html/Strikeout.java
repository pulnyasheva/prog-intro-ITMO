package md2html;

import java.util.List;

public class Strikeout extends AbstractText implements HtmlText {
    Strikeout(List<HtmlText> ht) {
        super(ht);
    }

    public void toHtml(StringBuilder sb) {
        super.toHtml(sb, "s");
    }
}