package quadtree;

import generaloss.spatialmath.vector.Vec2f;

import java.util.function.Consumer;

public class QuadTree {

    private final QuadNode root;

    public QuadTree(float size) {
        this.root = new QuadNode(this, null, null, size, 0);
    }

    public QuadNode getRoot() {
        return root;
    }


    public void update() {
        this.forEachNodePost(QuadNode::reset);

        this.forEachNodePre(node -> {
            final float x = 150;
            final float y = 280;

            final float dist = Vec2f.dst(node.getCenter(), x, y);

            if(dist < 100 && node.getLevel() < 5) {
                node.split();
            }else if(dist < 150 && node.getLevel() < 4) {
                node.split();
            }else if(dist < 300 && node.getLevel() < 3) {
                node.split();
            }else if(dist < 850 && node.getLevel() < 2) {
                node.split();
            }else if(dist < 1350 && node.getLevel() < 2) {
                node.split();
            }
        });

        // this.forEachNodePost(QuadNode::calculateNeighbors);
    }


    public void forEachNodePre(Consumer<QuadNode> consumer) {
        consumer.accept(root);
        forEachNodePre(root, consumer);
    }

    public void forEachNodePost(Consumer<QuadNode> consumer) {
        forEachNodePost(root, consumer);
        consumer.accept(root);
    }

    private static void forEachNodePre(QuadNode node, Consumer<QuadNode> consumer) {
        node.getChildren().forEach(child -> {
            consumer.accept(child);
            forEachNodePre(child, consumer);
        });
    }

    private static void forEachNodePost(QuadNode node, Consumer<QuadNode> consumer) {
        node.getChildren().forEach(child -> {
            forEachNodePost(child, consumer);
            consumer.accept(child);
        });
    }

}
