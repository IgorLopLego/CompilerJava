package parser.node.statement;

import parser.node.expression.Expression;

public class ScreamStatement extends Statement {
    public Expression expression;

    public ScreamStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
