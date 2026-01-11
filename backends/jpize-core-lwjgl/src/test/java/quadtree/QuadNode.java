package quadtree;

import generaloss.spatialmath.axisaligned.AARect;
import generaloss.spatialmath.vector.Vec2f;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuadNode {

    private final QuadTree tree;
    private final QuadNode parent;
    private final Quadrant quadrant;
    private final float size;
    private final int level;
    private final QuadNodeChildren children;
    private final AARect bounds;
    private final Vec2f center;
    private final QuadNodeNeighbors neighbors;

    public QuadNode(QuadTree tree, QuadNode parent, Quadrant quadrant, float size, int level) {
        this.tree = tree;
        this.parent = parent;
        this.quadrant = quadrant;
        this.size = size;
        this.level = level;
        this.children = new QuadNodeChildren();
        this.bounds = new AARect(0, 0, 0, 0);
        this.calculateBounds();
        this.center = bounds.getCenter(new Vec2f());
        this.neighbors = new QuadNodeNeighbors();
    }

    public QuadTree getTree() {
        return tree;
    }

    public QuadNode getParent() {
        return parent;
    }

    public Quadrant getQuadrant() {
        return quadrant;
    }

    public float getSize() {
        return size;
    }

    public int getLevel() {
        return level;
    }

    public QuadNodeChildren getChildren() {
        return children;
    }

    public QuadNode getChild(Quadrant quadrant) {
        return children.get(quadrant);
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public AARect getBounds() {
        return bounds;
    }

    public Vec2f getCenter() {
        return center;
    }

    public QuadNodeNeighbors getNeighbors() {
        return neighbors;
    }


    private void calculateBounds() {
        final float halfSize = (size * 0.5F);

        if(parent == null) {
            bounds.resize(-halfSize, -halfSize, halfSize, halfSize);
            return;
        }

        final Vec2f parentCenter = parent.getCenter();
        bounds.resize(
            parentCenter.x - halfSize, parentCenter.y - halfSize,
            parentCenter.x + halfSize, parentCenter.y + halfSize
        );

        final float shiftX = (halfSize * quadrant.signX);
        final float shiftY = (halfSize * quadrant.signY);
        bounds.shift(shiftX, shiftY);
    }


    public void split() {
        final float halfSize = (size * 0.5F);
        final int childrenLevel = (level + 1);

        final QuadNode rightUp = new QuadNode(tree, this, Quadrant.RIGHT_UP, halfSize, childrenLevel);
        final QuadNode leftUp = new QuadNode(tree, this, Quadrant.LEFT_UP, halfSize, childrenLevel);
        final QuadNode leftDown = new QuadNode(tree, this, Quadrant.LEFT_DOWN, halfSize, childrenLevel);
        final QuadNode rightDown = new QuadNode(tree, this, Quadrant.RIGHT_DOWN, halfSize, childrenLevel);

        children.set(rightUp, leftUp, leftDown, rightDown);
    }


    public boolean isOnEdge(Direction edgeDir) {
        return (
            (edgeDir.signX != 0 && edgeDir.signX == quadrant.signX) ||
            (edgeDir.signY != 0 && edgeDir.signY == quadrant.signY)
        );
    }


    public void calculateNeighbors() {
        if(parent == null)
            return;

        // Соседи на уровнях выше
        final Quadrant neighborXQuadrant = quadrant.opposite(true, false);
        final Quadrant neighborYQuadrant = quadrant.opposite(false, true);
        final QuadNode neighborX = parent.getChild(neighborXQuadrant);
        final QuadNode neighborY = parent.getChild(neighborYQuadrant);

        final Direction dirFromNeighborX = (neighborX.quadrant.signX > 0 ? Direction.RIGHT : Direction.LEFT);
        final Direction dirFromNeighborY = (neighborY.quadrant.signY > 0 ? Direction.UP : Direction.DOWN);
        final Collection<QuadNode> neighborsX = collectLeafNeighbors(neighborX, dirFromNeighborX);
        final Collection<QuadNode> neighborsY = collectLeafNeighbors(neighborY, dirFromNeighborY);
        neighbors.addNeighbors(dirFromNeighborX, neighborsX);
        neighbors.addNeighbors(dirFromNeighborY, neighborsY);

        // Соседи на уровнях ниже
        final int grandNeighborDirSignX = (parent.quadrant.signX / quadrant.signX);
        final int grandNeighborDirSignY = (parent.quadrant.signY / quadrant.signY);

        if(grandNeighborDirSignX < 0) {
            QuadNode grandparent = parent.getParent();
            if(grandparent == null)
                return;

            final Quadrant downNeighborQuadrantX = parent.quadrant.opposite(true, false);
            final QuadNode downNeighborX = grandparent.getChild(downNeighborQuadrantX);

            final Direction toQuadrantDir = Direction.bySign(-parent.quadrant.signX, 0);
            assert(toQuadrantDir != null);

            if(downNeighborX.isLeaf()){
                neighbors.addNeighbor(toQuadrantDir, downNeighborX);
            }else{
                final Collection<QuadNode> downNeighborsX = collectLeafNeighbors(downNeighborX, toQuadrantDir);
                neighbors.addNeighbors(toQuadrantDir, downNeighborsX);
            }
        }

        if(grandNeighborDirSignY < 0) {
            final QuadNode grandparent = parent.getParent();
            if(grandparent == null)
                return;

            final Quadrant downNeighborQuadrantY = parent.quadrant.opposite(false, true);
            final QuadNode downNeighborY = grandparent.getChild(downNeighborQuadrantY);

            final Direction dirFromQuadrant = Direction.bySign(0, -parent.quadrant.signY);
            assert(dirFromQuadrant != null);

            final Collection<QuadNode> downNeighborsY = collectLeafNeighbors(downNeighborY, dirFromQuadrant);
            neighbors.addNeighbors(dirFromQuadrant, downNeighborsY);
        }
    }

    private static Collection<QuadNode> collectLeafNeighbors(QuadNode targetNode, Direction snuggleDir) {
        final ArrayList<QuadNode> list = new ArrayList<>();
        collectLeafNeighbors(targetNode, snuggleDir.opposite(), list);
        return list;
    }

    private static void collectLeafNeighbors(QuadNode targetNode, Direction snuggleDir, List<QuadNode> output) {
        if(targetNode.isLeaf()) {
            output.add(targetNode);
            return;
        }

        targetNode.getChildren().forEach(child -> {
            final boolean matchDirX = (snuggleDir.signX != 0 && snuggleDir.signX == child.quadrant.signX);
            final boolean matchDirY = (snuggleDir.signY != 0 && snuggleDir.signY == child.quadrant.signY);
            if(matchDirX || matchDirY)
                collectLeafNeighbors(child, snuggleDir, output);
        });
    }


    private static QuadNode findNeighborUp(QuadNode node, Direction neighborDir) {
        return null;
    }


    public void reset() {
        children.clear();
        neighbors.clear();
    }

}
