package expression;

import java.util.Objects;

public class Variable extends AbstractExpression {
    private String var;

    public Variable(String var) {
        super("", null, null);
        this.var = var;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public int evaluate(int number) {
        return number;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (this.var) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> 0;//throw exeption
        };
    }

    @Override
    protected int count(int element1, int element2) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(var, variable.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }
}
