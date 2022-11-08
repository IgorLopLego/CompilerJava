package parserRefactor.nodes.expression;

import parserRefactor.nodes.terminal.Identifier;

public class CallExpression extends Expression {
    public Identifier name;
    public Expressions arguments;

    public CallExpression(Identifier name, Expressions arguments) {
        this.name = name;
        this.arguments = arguments;
    }
}
