package parser.node.terminal;

import parser.interfaces.Visitable;
import parser.node.Node;

public abstract class Terminal extends Node implements Visitable {
    public String spelling;
}
