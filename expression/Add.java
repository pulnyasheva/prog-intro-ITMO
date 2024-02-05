package expression;

public class Add extends AbstractExpression {
    public Add(AbstractExpression union1, AbstractExpression union2) {
        super("+", union1, union2);
    }

    protected int count(int element1, int element2) {
        return element1 + element2;
    }
}
