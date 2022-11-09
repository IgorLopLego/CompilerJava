package parserRefactor.nodes.expression;

import parserRefactor.nodes.terminal.Operator;

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
}
