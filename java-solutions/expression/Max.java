package expression;

public class Max extends AbstractExpression {
    public Max(AbstractExpression union1, AbstractExpression union2) {
        super("max", union1, union2);
    }

    protected int count(int element1, int element2) {
        return Math.max(element1, element2);
    }
}
