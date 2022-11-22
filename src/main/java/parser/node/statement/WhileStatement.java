package parser.node.statement;

import parser.node.Block;
import parser.node.expression.Expression;

public class WhileStatement extends Statement {
    public Expression expression;
    public Block block;

    public WhileStatement(Expression expression, Block block) {
        this.expression = expression;
        this.block = block;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
