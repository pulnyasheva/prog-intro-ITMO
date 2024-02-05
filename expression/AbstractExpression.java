package expression;


import java.util.Objects;

public abstract class AbstractExpression implements Expression, TripleExpression {
    private String sign = new String();
    protected AbstractExpression union1, union2;

    public AbstractExpression(String sign, AbstractExpression union1, AbstractExpression union2) {
        this.sign = sign;
        this.union1 = union1;
        this.union2 = union2;
    }


    @Override
    public String toString() {
        return "(" + union1.toString() + " " + sign + " " + union2.toString() + ")";
    }

    @Override
    public int evaluate(int number) {
        int element1 = union1.evaluate(number);
        int element2 = union2.evaluate(number);
        return count(element1, element2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int element1 = union1.evaluate(x, y, z);
        int element2 = 0;
        if (union2 != null) {
            element2 = union2.evaluate(x, y, z);
        }
        return count(element1, element2);
    }

    protected abstract int count(int element1, int element2);

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof AbstractExpression)) return false;
        AbstractExpression that = (AbstractExpression) o;
        return Objects.equals(sign, that.sign) && Objects.equals(union1, that.union1) && Objects.equals(union2, that.union2);
    }

    @Override
    public int hashCode() {
        return ((Objects.hash(sign) * 3 + Objects.hash(union1)) * 3 + Objects.hash(union2)) * 3;
    }

    @Override
    public String toMiniString() {
        return "";
    }
}
