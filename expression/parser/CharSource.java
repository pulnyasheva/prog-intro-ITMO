package expression.parser;

import expression.exceptions.ExpressionException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();

    char next();

    ExpressionException error(final String message);
}
