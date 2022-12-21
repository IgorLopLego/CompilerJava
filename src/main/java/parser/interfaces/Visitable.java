package parser.interfaces;

import viewer.Visitor;

public interface Visitable {
    Object accept(Visitor visitor, Object arguments);
}
