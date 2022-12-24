package parser.node.statement;

import parser.node.expression.Expression;
import viewer.Visitor;

public class ScreamStatement extends Statement {
    public Expression expression;

    public ScreamStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return null;
    }
}
