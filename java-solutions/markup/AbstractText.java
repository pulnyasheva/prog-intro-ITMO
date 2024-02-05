package markup;

import java.util.List;


public abstract class AbstractText implements MarkDownText {
    List<MarkDownText> list;

    AbstractText(List<MarkDownText> list) {
        this.list = list;
    }

    public void toMarkdown(StringBuilder sb) {
        for (MarkDownText element : list) {
            element.toMarkdown(sb);
        }
    }

    public void toHtml(StringBuilder sb) {
        for (MarkDownText element : list) {
            element.toHtml(sb);
        }
    }

}

