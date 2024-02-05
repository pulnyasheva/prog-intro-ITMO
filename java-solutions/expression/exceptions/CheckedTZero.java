package expression.exceptions;

import expression.AbstractExpression;

public class CheckedTZero extends AbstractExpression {
    CheckedTZero(AbstractExpression union1) {
        super("t0", union1, null);
    }

    @Override
    public String toString() {
        return "t0(" + union1.toString() + ")";
    }

    @Override
    protected int count(int element1, int element2) {

        return Integer.numberOfTrailingZeros(element1);
    }
}