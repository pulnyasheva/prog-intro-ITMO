package markup;

import java.lang.StringBuilder;

public interface MarkDownText {
    void toMarkdown(StringBuilder sb);

    void toHtml(StringBuilder sb);
}
