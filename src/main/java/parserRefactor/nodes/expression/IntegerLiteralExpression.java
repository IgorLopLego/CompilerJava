package parserRefactor.nodes.expression;

import parserRefactor.nodes.terminal.IntegerLiteral;

public class IntegerLiteralExpression extends Expression {
    public IntegerLiteral literal;

    public IntegerLiteralExpression(IntegerLiteral literal) {
        this.literal = literal;
    }
}
