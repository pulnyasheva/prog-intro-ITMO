package expression.exceptions;

import expression.AbstractExpression;
import expression.UnaryMinus;

public class CheckedNegate extends UnaryMinus {
    public CheckedNegate(AbstractExpression union1) {
        super(union1);
    }

    public int count(int element1, int element2) {
        if (element1 == Integer.MIN_VALUE) {
            throw new OverflowArithmeticException("Overflow number");
        }
        return -element1;
    }
}
