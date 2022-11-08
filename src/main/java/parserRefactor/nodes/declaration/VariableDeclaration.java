package parserRefactor.nodes.declaration;

import parserRefactor.nodes.expression.Expression;
import parserRefactor.nodes.terminal.Identifier;

public class VariableDeclaration extends Declaration {
    public Identifier id;
    public Expression expression;

    public VariableDeclaration(Identifier id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }
}
