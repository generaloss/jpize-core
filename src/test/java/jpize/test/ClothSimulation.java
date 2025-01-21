package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.gl.glenum.GlTarget;
import jpize.gl.tesselation.GlPrimitive;
import jpize.glfw.input.MouseBtn;
import jpize.util.camera.OrthographicCameraCentered;
import jpize.util.color.Color;
import jpize.util.math.geometry.Intersector;
import jpize.util.math.Mathc;
import jpize.util.math.vector.Vec2f;
import jpize.util.mesh.VertexBatch;

import java.util.*;

public class ClothSimulation extends JpizeApplication {

    private static final float PARTICLE_RADIUS = 3F;
    private static final float CONSTRAINT_WIDTH = 3F;
    private static final float GRAVITY = -10F;
    private static final float TIME_STEP = 0.2F;
    private static final int ROW = 15;
    private static final int COL = 15;
    private static final float REST_DISTANCE = 30F;
    private static final float CLICK_TOLERANCE = 10F;

    private final OrthographicCameraCentered camera;
    private float cinematic_scale;
    private final Vec2f prev_grab;

    private final VertexBatch point_batch;
    private final VertexBatch line_batch;

    private final List<Particle> particles;
    private final List<Constraint> constraints;

    public ClothSimulation() {
        this.camera = new OrthographicCameraCentered();
        this.camera.position().set(Jpize.getWidth() / 2F, Jpize.getHeight() / 2F);
        this.cinematic_scale = 1F;
        this.prev_grab = new Vec2f();

        this.point_batch = new VertexBatch(GlPrimitive.POINTS);
        this.line_batch = new VertexBatch(GlPrimitive.LINES);

        this.particles = new ArrayList<>();
        this.constraints = new ArrayList<>();

        // Initialize particles
        for(int row = 0; row < ROW; row++){
            final boolean is_pinned = (row == ROW - 1);
            for(int col = 0; col < COL; col++){
                final float x = (col * REST_DISTANCE + Jpize.getWidth() / 3F);
                final float y = (row * REST_DISTANCE + Jpize.getHeight() / 3F);
                final Particle particle = new Particle(x, y, is_pinned);
                //particle.previous_position.sub(Maths.random(-10, 10), Maths.random(-10, 10));
                this.particles.add(particle);
            }
        }

        // Initialize constraints
        for(int row = 0; row < ROW; row++){
            for(int col = 0; col < COL; col++){
                if(col < COL - 1){
                    // Horizontal constraint
                    this.constraints.add(new Constraint(particles.get(row * COL + col), particles.get(row * COL + (col + 1))));
                }
                if(row < ROW - 1){
                    // Vertical constraint
                    this.constraints.add(new Constraint(particles.get(row * COL + col), particles.get((row + 1) * COL + col)));
                }
                if(col < COL - 1 && row < ROW - 1 && row > ROW / 2){
                    this.constraints.add(new Constraint(particles.get(row * COL + col), particles.get((row + 1) * COL + (col + 1))));
                }
            }
        }
    }

    @Override
    public void init() {
        Gl.pointSize(PARTICLE_RADIUS);
        Gl.enable(GlTarget.POINT_SMOOTH);
        Gl.lineWidth(CONSTRAINT_WIDTH);
    }

    @Override
    public void update() {
        // Input camera scale
        cinematic_scale *= Mathc.pow(1.25, Jpize.getScroll());
        camera.setScale(camera.getScale() + (cinematic_scale - camera.getScale()) / 10);
        // Input camera position
        if(MouseBtn.MIDDLE.pressed()){
            if(MouseBtn.MIDDLE.down())
                Jpize.input().getCursorPos(prev_grab);
            camera.position().add(prev_grab.sub(Jpize.input().getCursorPos(new Vec2f())).div(camera.getScale()));
            Jpize.input().getCursorPos(prev_grab);
        }
        camera.update();

        // Tear
        if(MouseBtn.LEFT.pressed()){
            this.tear_cloth();
        }

        // Apply gravity and update particles
        for(Particle particle: particles){
            particle.apply_force(new Vec2f(0, GRAVITY));
            particle.update(TIME_STEP);
            particle.constrain_to_bounds(Jpize.getWidth(), Jpize.getHeight());
        }

        for(int i = 0; i < 50; i++){
            for(Constraint constraint: constraints){
                constraint.satisfy();
            }
        }
    }

    private void tear_cloth() {
        final float x = Jpize.getX() / camera.getScale() + camera.getX() - camera.getScaledHalfWidth();
        final float y = Jpize.getY() / camera.getScale() + camera.getY() - camera.getScaledHalfHeight();

        final Constraint nearest = this.find_nearest_constraint(x, y);
        if(nearest != null){
            nearest.deactivate();
        }
    }

    private Constraint find_nearest_constraint(float x, float y) {
        Constraint nearest_constraint = null;
        float min_distance = CLICK_TOLERANCE / camera.getScale();

        for(Constraint constraint: constraints){
            if(!constraint.active)
                continue;

            final float distance = Intersector.getPointToSegmentDistance(x, y, constraint.p1.position, constraint.p2.position);
            if(distance < min_distance){
                min_distance = distance;
                nearest_constraint = constraint;
            }
        }
        return nearest_constraint;
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();

        // Draw constraints as lines
        line_batch.setup(camera);
        for(Constraint constraint: constraints){
            if(!constraint.active)
                continue;

            line_batch.drawVertex(constraint.p1.position, 1F, 1F, 1F, 1F);
            line_batch.drawVertex(constraint.p2.position, 1F, 1F, 1F, 1F);
        }
        line_batch.render();

        // Draw particles as points
        // point_batch.setup(camera);
        // for(Particle particle: particles)
        //     point_batch.addVertex(particle.position, 1F, 0F, 1F, 1F);
        // point_batch.render();

        // Draw bounds
        final Color bounds_color = new Color(0.5F, 0.5F, 0.5F, 1F);

        line_batch.setup(camera);
        line_batch.drawVertex(0, 0, bounds_color);
        line_batch.drawVertex(Jpize.getWidth(), 0, bounds_color);
        line_batch.drawVertex(0, 0, bounds_color);
        line_batch.drawVertex(0, Jpize.getHeight(), bounds_color);
        line_batch.drawVertex(Jpize.getWidth(), 0, bounds_color);
        line_batch.drawVertex(Jpize.getWidth(), Jpize.getHeight(), bounds_color);
        line_batch.drawVertex(0, Jpize.getHeight(), bounds_color);
        line_batch.drawVertex(Jpize.getWidth(), Jpize.getHeight(), bounds_color);
        line_batch.render();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
    }

    @Override
    public void dispose() {
        point_batch.dispose();
        line_batch.dispose();
    }


    private static class Particle {

        public final Vec2f position;
        public final Vec2f previous_position;
        public final Vec2f acceleration;
        public final boolean is_pinned;

        public Particle(float x, float y, boolean is_pinned) {
            this.position = new Vec2f(x, y);
            this.previous_position = new Vec2f(x, y);
            this.acceleration = new Vec2f();
            this.is_pinned = is_pinned;
        }


        public void apply_force(Vec2f force) {
            if(is_pinned)
                return;
            acceleration.add(force);
        }

        public void update(float time_step) {
            if(is_pinned)
                return;

            // verlet integration
            final Vec2f velocity = new Vec2f(position).sub(previous_position);
            previous_position.set(position);
            acceleration.mul(time_step * time_step);
            position.add(velocity).add(acceleration);
            acceleration.zero();

            // reduce momentum
            previous_position.sub(position).mul(0.99).add(position);
        }

        public void constrain_to_bounds(float width, float height) {
            if(is_pinned)
                return;
            position.clamp(0F, 0F, width, height);
        }

    }

    private static class Constraint {

        public final Particle p1;
        public final Particle p2;
        public final float initial_length;
        public boolean active;

        public Constraint(Particle p1, Particle p2) {
            this.p1 = p1;
            this.p2 = p2;
            this.initial_length = Vec2f.dst(p1.position, p2.position);
            this.active = true;
        }


        public void deactivate() {
            active = false;
        }

        public void satisfy() {
            if(!active)
                return;

            final Vec2f delta = p2.position.copy().sub(p1.position);
            final float current_length = Vec2f.len(delta.x, delta.y);
            final float difference = (current_length == 0) ? 0 : (current_length - initial_length) / current_length;
            final Vec2f correction = delta.copy().mul(0.5F * difference);

            if(!p1.is_pinned) p1.position.add(correction);
            if(!p2.is_pinned) p2.position.sub(correction);
        }

    }


    public static void main(String[] args) {
        Jpize.create(1080, 720, "Cloth Simulation")
            .samples(8)
            .build().setApp(new ClothSimulation());

        Jpize.run();
    }

}
