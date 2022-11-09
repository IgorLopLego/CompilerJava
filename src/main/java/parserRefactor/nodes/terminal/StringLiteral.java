package parserRefactor.nodes.terminal;

public class StringLiteral extends Terminal {
    public StringLiteral(String spelling) {
        this.spelling = spelling;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + spelling;
    }
}
