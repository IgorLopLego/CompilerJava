package parserRefactor.nodes;

public class Program extends Node {
    public Block block;

    public Program(Block block) {
        this.block = block;
    }
}