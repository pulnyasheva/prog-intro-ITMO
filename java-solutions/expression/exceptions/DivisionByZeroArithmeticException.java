package expression.exceptions;

public class DivisionByZeroArithmeticException extends ArithmeticException{
    public DivisionByZeroArithmeticException (final String message) {
        super(message);
    }
}
