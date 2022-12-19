package parser.node.declaration;

import parser.node.Block;
import parser.node.expression.Expression;
import parser.node.terminal.Identifier;

import java.util.Optional;

public class FunctionDeclaration extends Declaration {
    public Identifier name;
    public Declarations parameters;
    public Block block;
    public Optional<Expression> returnExpression;

    public FunctionDeclaration(
            Identifier name,
            Declarations parameters,
            Block block,
            Expression returnExpression
    ) {
        this.name = name;
        this.parameters = parameters;
        this.block = block;
        this.returnExpression = Optional.ofNullable(returnExpression);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
