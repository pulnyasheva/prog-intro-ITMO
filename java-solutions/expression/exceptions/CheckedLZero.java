package expression.exceptions;

import expression.AbstractExpression;

public class CheckedLZero extends AbstractExpression {
    CheckedLZero(AbstractExpression union1) {
        super("l0", union1, null);
    }

    @Override
    public String toString() {
        return "l0(" + union1.toString() + ")";
    }

    @Override
    protected int count(int element1, int element2) {
        return Integer.numberOfLeadingZeros(element1);
    }
}
