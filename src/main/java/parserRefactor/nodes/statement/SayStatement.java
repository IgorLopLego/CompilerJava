package parserRefactor.nodes.statement;

import parserRefactor.nodes.expression.Expression;

public class SayStatement extends Statement {
    public Expression expression;

    public SayStatement(Expression expression) {
        this.expression = expression;
    }
}
