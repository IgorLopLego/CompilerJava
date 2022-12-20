package parser.node.statement;

import parser.node.expression.Expression;

public class ShoveStatement extends Statement{

    public Expression expression;
    public ShoveStatement(Expression expression){
        this.expression = expression;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
