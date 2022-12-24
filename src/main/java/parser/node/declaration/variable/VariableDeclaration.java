package parser.node.declaration.variable;

import parser.node.declaration.Declaration;
import parser.node.expression.Expression;
import parser.node.terminal.Identifier;
import viewer.Visitor;

import java.util.Optional;

public class VariableDeclaration extends Declaration {
    public Identifier id;
    public VariableType type;
    public Optional<Expression> expression;

    public VariableDeclaration(Identifier id, VariableType type, Expression expression) {
        this.id = id;
        this.type = type;
        this.expression = Optional.ofNullable(expression);
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
