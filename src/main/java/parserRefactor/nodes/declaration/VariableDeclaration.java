package parserRefactor.nodes.declaration;

import parserRefactor.nodes.expression.Expression;
import parserRefactor.nodes.terminal.Identifier;

import java.util.Optional;

public class VariableDeclaration extends Declaration {
    public Identifier id;
    public Optional<Expression> expression;

    public VariableDeclaration(Identifier id) {
        this.id = id;
        expression = Optional.empty();
    }

    public VariableDeclaration(Identifier id, Expression expression) {
        this.id = id;
        this.expression = Optional.ofNullable(expression);
    }
}
