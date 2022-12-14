package parser.node.expression;

import parser.node.terminal.Operator;
import viewer.Visitor;

public class BinaryExpression extends Expression {
    public Operator operator;
    public Expression leftOperand;
    public Expression rightOperand;

    public BinaryExpression(Operator operator, Expression leftOperand, Expression rightOperand) {
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
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
