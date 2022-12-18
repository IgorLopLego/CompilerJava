package parser.node.terminal;

import scanner.token.TokenKind;

import javax.swing.tree.DefaultMutableTreeNode;

public class DeclarationParameter extends Terminal {
    public Identifier id;
    public TokenKind type;


    public DeclarationParameter(Identifier id, TokenKind tokenKind) {
        this.id = id;
        this.type = tokenKind;
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + " " +  this.id.spelling  + " " + this.type.getSpelling();
    }
}
