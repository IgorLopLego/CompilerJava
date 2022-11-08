package parserRefactor.nodes.declaration;

import parserRefactor.nodes.Block;
import parserRefactor.nodes.expression.Expression;
import parserRefactor.nodes.terminal.Identifier;

public class FunctionDeclaration extends Declaration {
    public Identifier name;
    public Declarations parameters;
    public Block block;
    public Expression expression;

    public FunctionDeclaration(
            Identifier name,
            Declarations parameters,
            Block block,
            Expression expression
    ) {
        this.name = name;
        this.parameters = parameters;
        this.block = block;
        this.expression = expression;
    }
}
