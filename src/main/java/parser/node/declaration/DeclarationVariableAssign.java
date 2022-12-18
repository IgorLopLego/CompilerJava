package parser.node.declaration;

import parser.node.expression.Expression;
import parser.node.terminal.Identifier;

import java.util.Optional;

public class DeclarationVariableAssign extends Declaration {
    public Identifier id;
    public Optional<Expression> expression;

    public DeclarationVariableAssign(Identifier id) {
        this.id = id;
        expression = Optional.empty();
    }

    public DeclarationVariableAssign(Identifier id, Expression expression) {
        this.id = id;
        this.expression = Optional.ofNullable(expression);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
