package parser.node.expression;

import parser.node.terminal.Operator;
import viewer.Visitor;

public class UnaryExpression extends Expression {
    public Operator operator;
    public Expression operand;

    public UnaryExpression(Operator operator, Expression operand) {
        this.operator = operator;
        this.operand = operand;
    }

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return null;
    }
}
