package parserRefactor.nodes.terminal;

public class Identifier extends Terminal {
    public Identifier(String spelling) {
        this.spelling = spelling;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + spelling;
    }
}
