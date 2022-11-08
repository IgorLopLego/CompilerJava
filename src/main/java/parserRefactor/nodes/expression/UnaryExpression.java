package parserRefactor.nodes.expression;

import parserRefactor.nodes.terminal.Operator;

public class UnaryExpression extends Expression {
    public Operator operator;
    public Expression operand;

    public UnaryExpression(Operator operator, Expression operand) {
        this.operator = operator;
        this.operand = operand;
    }
}
