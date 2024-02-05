package expression.parser;

import expression.*;

public class ExpressionParser implements Parser {
    public AbstractExpression parse(final String source) {
        return parse(new StringSource(source));
    }

    public static AbstractExpression parse(final CharSource source) {
        return new ParserImpl(source).parseExpression();
    }

    private static class ParserImpl extends BaseParser {
        public ParserImpl(final CharSource source) {
            super(source);
        }

        public AbstractExpression parseExpression() {
            final AbstractExpression result = parseMinAndMax();
            return result;
        }

        private AbstractExpression parseMinAndMax() {
            AbstractExpression element1 = parseAddAndSub();
            skipWhitespace();
            while (take('m')) {
                if (take('i')) {
                    // :NOTE: проверять на n
                    take();
                    skipWhitespace();
                    AbstractExpression element2 = parseAddAndSub();
                    element1 = new Min(element1, element2);
                } else if (take('a')) {
                    // :NOTE: проверять на x
                    take();
                    skipWhitespace();
                    AbstractExpression element2 = parseAddAndSub();
                    element1 = new Max(element1, element2);
                }
                skipWhitespace();
            }
            return element1;
        }

        private AbstractExpression parseAddAndSub() {
            skipWhitespace();
            AbstractExpression element1 = parseMulAndDiv();
            skipWhitespace();
            while (getCh() == '+' | getCh() == '-') {
                if (take('+')) {
                    skipWhitespace();
                    AbstractExpression element2 = parseMulAndDiv();
                    element1 = new Add(element1, element2);
                } else if (take('-')) {
                    skipWhitespace();
                    AbstractExpression element2 = parseMulAndDiv();
                    element1 = new Subtract(element1, element2);
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
                    skipWhitespace();
                    AbstractExpression element2 = parseConsAndExp();
                    element1 = new Multiply(element1, element2);
                } else if (take('/')) {
                    skipWhitespace();
                    AbstractExpression element2 = parseConsAndExp();
                    element1 = new Divide(element1, element2);
                }
                skipWhitespace();
            }
            return element1;
        }

        private AbstractExpression parseConsAndExp() {
            skipWhitespace();
            if (between('0', '9')) {
                return takeInteger(false);
            } else if (take('-')) {
                if (between('1', '9')) {
                    return takeInteger(true);
                } else {
                    return new UnaryMinus(parseConsAndExp());
                }
            } else if (between('x', 'z')) {
                return new Variable(String.valueOf(take()));
            } else if (take('(')) {
                AbstractExpression result = parseMinAndMax();
                if (take(')')) {
                    return result;
                }
            }
            throw error("Incorrect input");
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
                return new Const(0);
            }

            if (between('1', '9')) {
                takeDigits(sb);
            } else {
                throw error("Invalid number");
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
