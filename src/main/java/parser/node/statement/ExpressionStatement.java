package parser.node.statement;

import parser.node.expression.Expression;
import viewer.Visitor;

public class ExpressionStatement extends Statement {
    public Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return visitor.visit(this, null);
    }
}
