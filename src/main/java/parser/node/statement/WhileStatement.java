package parser.node.statement;

import parser.node.expression.Expression;

public class WhileStatement extends Statement {
    public Expression expression;
    public Statements statements;

    public WhileStatement(Expression expression, Statements statements) {
        this.expression = expression;
        this.statements = statements;
    }
}
