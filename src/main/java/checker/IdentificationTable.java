package checker;

import exception.SemanticError;
import parser.node.declaration.Declaration;

import java.util.Optional;
import java.util.Vector;

public class IdentificationTable {
    private final Vector<IdEntry> table;
    private int level = 0;

    public IdentificationTable() {
        table = new Vector<>();
    }

    public void enter(String id, Declaration attribute) {
        var entry = find(id);

        if (entry.isPresent() && entry.get().level == level) {
            throw new SemanticError("Id of: " + id + " - is declared twice.");
        }

        table.add(new IdEntry(level, id, attribute));
    }

    public Optional<Declaration> retrieve(String id) {
        var entry = find(id);

        if (entry.isPresent()) return Optional.of(entry.get().attribute);

        return Optional.empty();
    }

    public void openScope() {
        ++level;
    }

    public void popScope() {
        var position = table.size() - 1;

        while (position >= 0 && table.get(position).level == level) {
            table.remove(position);
            position--;
        }

        level--;
    }

    private Optional<IdEntry> find(String id) {
        for (int i = table.size() - 1; i >= 0; i--) {
            if (table.get(i).id.equals(id)) {
                return Optional.of(table.get(i));
            }
        }

        return Optional.empty();
    }
}
