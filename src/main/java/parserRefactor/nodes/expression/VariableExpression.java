package parserRefactor.nodes.expression;

import parserRefactor.nodes.terminal.Identifier;

public class VariableExpression extends Expression {
    public Identifier name;

    public VariableExpression(Identifier name) {
        this.name = name;
    }
}
