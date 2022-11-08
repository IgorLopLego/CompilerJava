package parserRefactor.nodes.statement;

import parserRefactor.nodes.expression.Expression;

public class ExpressionStatement extends Statement {
    public Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }
}
