package parser.node.declaration;

import parser.node.Block;
import parser.node.expression.Expression;

import parser.node.terminal.DeclarationParameter;
import parser.node.terminal.Identifier;

import java.util.Optional;
import java.util.Vector;

public class DeclarationFunctionAssign extends Declaration {
    public Identifier name;
    public String returnType;
    public Vector<DeclarationParameter> parameters;
    public Block block;
    public Optional<Expression> returnExpression;

    public DeclarationFunctionAssign(
            Identifier name,
            String returnType,
            Vector<DeclarationParameter> parameters,
            Block block,
            Expression returnExpression
    ) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
        this.block = block;
        this.returnExpression = Optional.ofNullable(returnExpression);
    }

    public DeclarationFunctionAssign(
            Identifier name,
            String returnType,
            Vector<DeclarationParameter> parameters,
            Block block
    ) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
        this.block = block;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
