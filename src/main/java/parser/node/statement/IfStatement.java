package parser.node.statement;

import parser.node.expression.Expression;

public class IfStatement extends Statement {
    public Expression expression;
    public Statements thenSection;
    public Statements elseSection;

    public IfStatement(
            Expression expression,
            Statements thenSection,
            Statements elseSection
    ) {
        this.expression = expression;
        this.thenSection = thenSection;
        this.elseSection = elseSection;
    }
}
