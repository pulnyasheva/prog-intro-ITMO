package expression.exceptions;

import expression.AbstractExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(AbstractExpression union1, AbstractExpression union2) {
        super(union1, union2);
    }

    @Override
    public int count(int element1, int element2) {
        if (element1 > 0 && (element2 > 0 && Integer.MAX_VALUE / element1 < element2 ||
                element2 < 0 && Integer.MIN_VALUE / element1 > element2) ||
                element1 < 0 && (element2 > 0 && Integer.MIN_VALUE / element2 > element1
                        || element2 < 0 && Integer.MAX_VALUE / element2 > element1)) {
            throw new OverflowArithmeticException("Overflow multiply");
        }
        return element1*element2;
    }
}
