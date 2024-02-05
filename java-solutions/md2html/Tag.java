package md2html;

public class Tag {
    private String tag;
    private int count = 0;

    Tag(String st) {
        this.tag = st;
    }

    public void addOne() {
        count++;
    }

    public int getCount() {
        return this.count;
    }

    public String getTag() {
        return this.tag;
    }
}
