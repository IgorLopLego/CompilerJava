package parserRefactor.nodes.terminal;

public class IntegerLiteral extends Terminal {
    public IntegerLiteral(String spelling) {
        this.spelling = spelling;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + spelling;
    }
}
