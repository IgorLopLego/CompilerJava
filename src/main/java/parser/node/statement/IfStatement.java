package parser.node.statement;

import parser.node.Block;
import parser.node.expression.Expression;

public class IfStatement extends Statement {
    public Expression expression;
    public Block ifSection;
    public Block elseSection;

    public IfStatement(
            Expression expression,
            Block ifSection,
            Block elseSection
    ) {
        this.expression = expression;
        this.ifSection = ifSection;
        this.elseSection = elseSection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
