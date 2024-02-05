package expression.exceptions;

import expression.AbstractExpression;
import expression.Divide;

public class CheckedDivide extends Divide {
    public CheckedDivide(AbstractExpression union1, AbstractExpression union2) {
        super(union1, union2);
    }

    @Override
    protected int count(int element1, int element2) {
        if (element2 == 0) {
            throw new DivisionByZeroArithmeticException("Divizion by zero");
        }
        if (element1 == Integer.MIN_VALUE && element2 == -1) {
            throw new OverflowArithmeticException("Overflow divide");
        }
        return element1 / element2;
    }
}
