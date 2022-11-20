package parser.node.expression;

import parser.node.terminal.Identifier;

public class VariableExpression extends Expression {
    public Identifier name;

    public VariableExpression(Identifier name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
