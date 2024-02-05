package expression;

public class Min extends AbstractExpression {
    public Min(AbstractExpression union1, AbstractExpression union2) {
        super("min", union1, union2);
    }

    protected int count(int element1, int element2) {
        return Math.min(element1, element2);
    }
}
