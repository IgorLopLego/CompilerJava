package parser.node.declaration;

import parser.node.Block;
import parser.node.expression.Expression;
import parser.node.terminal.Identifier;

public class DeclarationFunctionAssign extends Declaration {
    public Identifier name;
    public Declarations parameters;
    public Block block;
    public Expression returnExpression;

    public DeclarationFunctionAssign(
            Identifier name,
            Declarations parameters,
            Block block,
            Expression returnExpression
    ) {
        this.name = name;
        this.parameters = parameters;
        this.block = block;
        this.returnExpression = returnExpression;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
