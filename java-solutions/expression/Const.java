package expression;

import java.util.Objects;

public class Const extends AbstractExpression {
    private int number;

    public Const(int number) {
        super("", null, null);
        this.number = number;
    }

    @Override
    public int evaluate(int number) {
        return this.number;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.number;
    }

    @Override
    protected int count(int element1, int element2) {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return number == aConst.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
