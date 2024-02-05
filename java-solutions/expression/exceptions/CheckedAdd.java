package expression.exceptions;

import expression.AbstractExpression;
import expression.Add;

public class CheckedAdd extends Add {
    public CheckedAdd(AbstractExpression union1, AbstractExpression union2) {
        super(union1, union2);
    }

    @Override
    protected int count(int element1, int element2) {
        if (element1 > 0 && element2 > 0 && Integer.MIN_VALUE + element1 + element2 >= 0
        || element1 < 0 && element2 < 0 && Integer.MAX_VALUE + element1 + element2 + 1  < 0) {
            throw new OverflowArithmeticException("Overflow add");
        }
        return element1 + element2;
    }
}
