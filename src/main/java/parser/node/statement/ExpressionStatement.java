package parser.node.statement;

import parser.node.expression.Expression;

public class ExpressionStatement extends Statement {
    public Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
