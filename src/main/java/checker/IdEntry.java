package checker;

import parser.node.declaration.Declaration;

public class IdEntry {
    public int level;
    public String id;
    public Declaration attribute;

    public IdEntry(int level, String id, Declaration attribute) {
        this.level = level;
        this.id = id;
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "IdEntry{" +
                "level=" + level +
                ", id='" + id + '\'' +
                '}';
    }
}
