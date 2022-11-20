package parser.node.terminal;

public class Operator extends Terminal {
    public Operator(String spelling) {
        this.spelling = spelling;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + spelling;
    }
}
