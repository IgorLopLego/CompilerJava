package parser.node.terminal;

public class BooleanLiteral extends Terminal {
    public BooleanLiteral(String spelling) {
        this.spelling = spelling;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + spelling;
    }
}
