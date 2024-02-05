package expression.exceptions;


import expression.AbstractExpression;
import expression.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringSource;


// :NOTE: выводить позицию ошибки парсинга
public class ExpressionParser implements Parser {
    public AbstractExpression parse(final String source) {
        return parse(new StringSource(source));
    }

    public static AbstractExpression parse(final CharSource source) {
        return new ParserImpl(source).parseExpression();
    }

    private static class ParserImpl extends BaseParser {
        private char previousCh = 0xffff;

        @Override
        protected char take() {
            previousCh = getCh();
            return super.take();
        }

        public ParserImpl(final CharSource source) {
            super(source);
        }

        private boolean checkStartBinaryOperation() {
            return previousCh == ')' || Character.isWhitespace(previousCh);
        }

        private boolean checkEndBinaryOperation() {
            return getCh() == '(' || Character.isWhitespace(getCh()) || getCh() == '-' || endOfFile();
        }

        public AbstractExpression parseExpression() {
            final AbstractExpression result = parseMinAndMax();
            if (endOfFile()) {
                return result;
            }
            throw new ExpressionException("Expected end of Expression pos: " + getIndex());
        }

        private AbstractExpression parseMinAndMax() {
            AbstractExpression element1 = parseAddAndSub();
            skipWhitespace();
            while (checkStartBinaryOperation() && take('m')) {
                if (take('i') && take('n') && checkEndBinaryOperation()) {
                    AbstractExpression element2 = parseAddAndSub();
                    element1 = new Min(element1, element2);
                } else if (take('a') && take('x')  && checkEndBinaryOperation()) {
                    AbstractExpression element2 = parseAddAndSub();
                    element1 = new Max(element1, element2);
                } else {
                    throw new ExpressionException("Incorrect MinMax pos: " + getIndex());
                }
            }
            return element1;
        }

        private AbstractExpression parseAddAndSub() {
            AbstractExpression element1 = parseMulAndDiv();
            skipWhitespace();
            while (getCh() == '+' | getCh() == '-') {
                if (take('+')) {
                    AbstractExpression element2 = parseMulAndDiv();
                    element1 = new CheckedAdd(element1, element2);
                } else if (take('-')) {
                    AbstractExpression element2 = parseMulAndDiv();
                    element1 = new CheckedSubtract(element1, element2);
                }
                skipWhitespace();
            }
            return element1;
        }

        private AbstractExpression parseMulAndDiv() {
            AbstractExpression element1 = parseConsAndExp();
            skipWhitespace();
            while (getCh() == '*' | getCh() == '/') {
                if (take('*')) {
                    element1 = new CheckedMultiply(element1, parseConsAndExp());
                } else if (take('/')) {
                    element1 = new CheckedDivide(element1, parseConsAndExp());
                }
                skipWhitespace();
            }
            return element1;
        }

        private AbstractExpression parseConsAndExp() {
            skipWhitespace();
            if (take('l')) {
                if (take('0') && checkEndBinaryOperation()) {
                    return new CheckedLZero(parseConsAndExp());
                }
                throw new ExpressionException("Incorrect l0 pos: " + getIndex());
            } else if (take('t')) {
                if (take('0') && checkEndBinaryOperation()) {
                    return new CheckedTZero(parseConsAndExp());
                }
                throw new ExpressionException("Incorrect t0 pos: " + getIndex());
            } else if (between('0', '9')) {
                return takeInteger(false);
            } else if (take('-')) {
                if (between('1', '9')) {
                    return takeInteger(true);
                } else {
                    return new CheckedNegate(parseConsAndExp());
                }
            } else if (between('x', 'z')) {
                return new Variable(String.valueOf(take()));
            } else if (take('(')) {
                AbstractExpression result = parseMinAndMax();
                if (take(')')) {
                    return result;
                }
            }
            throw new ExpressionException("Incorrect inputpos: " + getIndex());
        }

        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }

        private Const takeInteger(boolean minus) {
            final StringBuilder sb = new StringBuilder();
            if (minus) {
                sb.append('-');
            } else if (take('0')) {
                sb.append('0');
            }
            if (getCh() == '0') {
                throw new ExpressionException("Incorrect zero" + getIndex());
            }
            if (between('1', '9')) {
                takeDigits(sb);
            } else if (sb.length() == 0) {
                throw error("Invalid number" + getIndex());
            }
            return new Const(Integer.parseInt(sb.toString()));
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(getCh())) {
                take();
            }
        }
    }
}
