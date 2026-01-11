package quadtree;

import java.util.Arrays;
import java.util.function.Consumer;

public class QuadNodeChildren {

    private final QuadNode[] nodes;
    private boolean empty;

    public QuadNodeChildren() {
        this.nodes = new QuadNode[4];
        this.empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }


    public QuadNode get(Quadrant quadrant) {
        final int index = quadrant.ordinal();
        return nodes[index];
    }

    private void set(Quadrant quadrant, QuadNode node) {
        final int index = quadrant.ordinal();
        nodes[index] = node;
    }

    public void set(QuadNode rightUp, QuadNode leftUp, QuadNode leftDown, QuadNode rightDown) {
        if(rightUp == null || leftUp == null || leftDown == null || rightDown == null)
            throw new IllegalArgumentException("All nodes could not be null");

        if(rightUp.getQuadrant() != Quadrant.RIGHT_UP)
            throw new IllegalArgumentException("Argument 'rightUp' has wrong quadrant");
        if(leftUp.getQuadrant() != Quadrant.LEFT_UP)
            throw new IllegalArgumentException("Argument 'leftUp' has wrong quadrant");
        if(leftDown.getQuadrant() != Quadrant.LEFT_DOWN)
            throw new IllegalArgumentException("Argument 'leftDown' has wrong quadrant");
        if(rightDown.getQuadrant() != Quadrant.RIGHT_DOWN)
            throw new IllegalArgumentException("Argument 'rightDown' has wrong quadrant");

        this.set(Quadrant.RIGHT_UP, rightUp);
        this.set(Quadrant.LEFT_UP, leftUp);
        this.set(Quadrant.LEFT_DOWN, leftDown);
        this.set(Quadrant.RIGHT_DOWN, rightDown);

        empty = false;
    }

    public void clear() {
        Arrays.fill(nodes, null);
        empty = true;
    }


    public void forEach(Consumer<QuadNode> node) {
        if(empty)
            return;

        for(QuadNode quadNode : nodes)
            node.accept(quadNode);
    }

}
