package expression.exceptions;

import expression.AbstractExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(AbstractExpression union1, AbstractExpression union2) {
        super(union1, union2);
    }

    @Override
    public int count(int element1, int element2) {
        if ((element1 > 0 && element2 < 0 && Integer.MIN_VALUE + element1 - element2 >= 0)
        || (element1 < 0 && element2 > 0 && Integer.MAX_VALUE + element1 - element2 < -1)
        || (element1 == 0 && element2 == Integer.MIN_VALUE)) {
            throw new OverflowArithmeticException("Overflow: subtract");
        }
        return element1 - element2;
    }
}
