package expression;

public class UnaryMinus extends AbstractExpression {
    public UnaryMinus(AbstractExpression union1) {
        super("-", union1, null);
    }

    @Override
    public String toString() {
        return "-(" + union1.toString() + ")";
    }

    @Override
    protected int count(int element1, int element2) {
        return -element1;
    }
}
