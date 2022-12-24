package parser.node.declaration;

import parser.node.terminal.Identifier;
import viewer.Visitor;

public class StructDeclaration extends Declaration {
    public Identifier name;
    public Declarations parameters;

    public StructDeclaration(Identifier name, Declarations parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
