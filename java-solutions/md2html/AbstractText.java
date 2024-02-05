package md2html;

import java.util.List;

public abstract class AbstractText implements HtmlText {
    private StringBuilder sb = new StringBuilder();

    AbstractText(String s) {
        this.sb.append(s);
    }

    AbstractText(List<HtmlText> list) {
        for (HtmlText element : list) {
            element.toHtml(this.sb);
        }
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(this.sb);
    }

    public void toHtml(StringBuilder sb, String str) {
        sb.append("<" + str + ">");
        sb.append(this.sb);
        sb.append("</" + str + ">");
    }
}