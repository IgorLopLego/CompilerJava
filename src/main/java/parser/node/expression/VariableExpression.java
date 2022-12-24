package parser.node.expression;

import parser.node.declaration.variable.VariableDeclaration;
import parser.node.terminal.Identifier;
import viewer.Visitor;

public class VariableExpression extends Expression {
    public Identifier name;
    public VariableDeclaration declaration;

    public VariableExpression(Identifier name) {
        this.name = name;
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
