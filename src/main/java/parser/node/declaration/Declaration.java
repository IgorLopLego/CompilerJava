package parser.node.declaration;

import encoder.Address;
import parser.interfaces.HashCode;
import parser.node.Node;
import parser.interfaces.Visitable;

public abstract class Declaration extends Node implements Visitable {
    public Address address;
}
