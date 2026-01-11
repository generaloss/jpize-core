package quadtree;

import generaloss.spatialmath.axisaligned.AARect;
import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.opengl.gl.GL;
import jpize.util.color.Color;
import jpize.util.mesh.TextureBatch;

public class QuadTreeTest extends JpizeApplication {

    final QuadTree tree = new QuadTree(1000F);
    final TextureBatch batch = new TextureBatch();
    QuadNode selected;

    @Override
    public void init() {
        tree.update();
    }

    @Override
    public void render() {
        GL.clearColorBuffer();
        batch.setup();

        tree.forEachNodePre(node -> {
            if(!node.isLeaf())
                return;

            final AARect bounds = node.getBounds();
            final float x = bounds.min().x + 3;
            final float y = bounds.min().y + 3;
            final float width = bounds.getWidth() - 6;
            final float height = bounds.getHeight() - 6;


            if(Jpize.input.isCursorInRect(x + 500, y + 500, width, height))
                selected = node;

            final Color color = (node == selected) ? new Color(1F, 1F, 1F) : new Color(0.7F, 0.7F, 0.7F);
            this.drawNode(node, color);
        });

        if(selected != null) {
            selected.getNeighbors().clear();
            selected.calculateNeighbors();

            final QuadNodeNeighbors neighbors = selected.getNeighbors();
            for(QuadNode neighbor : neighbors.getNeighbors(Direction.UP)) {
                this.drawNode(neighbor, new Color(1F, 0F, 1F, 0.5F));
            }
            for(QuadNode neighbor : neighbors.getNeighbors(Direction.LEFT)) {
                this.drawNode(neighbor, new Color(1F, 1F, 0F, 0.5F));
            }
            for(QuadNode neighbor : neighbors.getNeighbors(Direction.DOWN)) {
                this.drawNode(neighbor, new Color(0F, 1F, 1F, 0.5F));
            }
            for(QuadNode neighbor : neighbors.getNeighbors(Direction.RIGHT)) {
                this.drawNode(neighbor, new Color(0F, 1F, 0F, 0.5F));
            }
        }

        batch.render();
    }

    private void drawNode(QuadNode node, Color color) {
        final AARect bounds = node.getBounds();

        final float x = bounds.min().x + 3;
        final float y = bounds.min().y + 3;
        final float width = bounds.getWidth() - 6;
        final float height = bounds.getHeight() - 6;

        batch.drawRect(
            x + 500,
            y + 500,
            width,
            height,
            color
        );
    }

    public static void main(String[] args) {
        GlfwContextBuilder.create(1280, 1280, "QuadTree")
            .build()
            .setApp(new QuadTreeTest());

        GlfwContextManager.run();
    }

}
