package coretests;

import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.context.input.Key;
import jpize.context.input.MouseBtn;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.opengl.gl.GL;
import jpize.opengl.shader.Shader;
import jpize.util.font.Charset;
import jpize.util.font.Font;
import jpize.util.font.FontLoadOptions;
import jpize.util.mesh.TextureBatch;

/** @author ChatGPT */
public final class MizuStudio extends JpizeApplication {

    private static final float TOP = 48;
    private static final float LEFT = 218;
    private static final float RIGHT = 276;
    private static final float TIMELINE = 174;

    private static final int BG = 0xFF17191F;
    private static final int PANEL = 0xFF20232B;
    private static final int BORDER = 0xFF343946;
    private static final int TEXT = 0xFFE3E7F0;
    private static final int MUTED = 0xFF969EAF;
    private static final int ACCENT = 0xFF8C7CFF;
    private static final int CYAN = 0xFF59D6D1;

    private TextureBatch batch;
    private Font font;
    private Shader deformer;

    private float bend = 0.25F;
    private float breathing = 0.35F;
    private float blink = 0.08F;
    private float zoom = 1F;
    private float time;

    private boolean showGrid = true;
    private boolean showMesh = true;
    private boolean playing = true;
    private boolean deformTool;
    private int selectedLayer;
    private int draggingSlider = -1;
    private boolean draggingModel;

    private final String[] layers = {
        "Head",
        "  Hair · Back",
        "  Face",
        "  Eyes",
        "  Mouth",
        "  Hair · Front"
    };

    @Override
    public void init() {
        batch = new TextureBatch();

        final FontLoadOptions fontLoadOptions = new FontLoadOptions()
            .size(16)
            .charset(new Charset(Charset.DEFAULT.getStringOf() + "＋▾·✓▶Ⅱ·"));
        font = new Font().loadDefault(fontLoadOptions);

        deformer = new Shader(VERTEX, FRAGMENT);
    }

    @Override
    public void update() {
        float dt = Math.min(Jpize.getDeltaTime(), 0.05F);
        if (playing)
            time += dt;

        float w = Jpize.getWidth();
        float h = Jpize.getHeight();
        float mx = Jpize.getX();
        float my = Jpize.getY();

        if (draggingSlider >= 0 && MouseBtn.LEFT.pressed())
            setSlider(draggingSlider, mx, w);

        if (draggingModel && MouseBtn.LEFT.pressed()) {
            float center = LEFT + (w - LEFT - RIGHT) * 0.5F;
            bend = clamp((mx - center) / 120F, -1F, 1F);
        }

        if (MouseBtn.LEFT.up()) {
            draggingSlider = -1;
            draggingModel = false;
        }

        if (MouseBtn.LEFT.down()) {
            if (my >= h - TOP) {
                if (hit(mx, my, 16, h - 40, 96, 28))
                    deformTool = false;
                else if (hit(mx, my, 120, h - 40, 96, 28))
                    deformTool = true;
                else if (hit(mx, my, 224, h - 40, 96, 28))
                    showGrid = !showGrid;
                else if (hit(mx, my, 328, h - 40, 96, 28))
                    showMesh = !showMesh;
                else if (hit(mx, my, w - 112, h - 40, 96, 28))
                    playing = !playing;
            }

            if (mx >= w - RIGHT && my > TIMELINE && my < h - TOP) {
                int slider = findSlider(mx, my, w, h);
                if (slider >= 0) {
                    draggingSlider = slider;
                    setSlider(slider, mx, w);
                }
            }

            if (mx < LEFT && my > TIMELINE && my < h - TOP) {
                int row = (int) ((h - TOP - 94 - my) / 31F);
                if (row >= 0 && row < layers.length)
                    selectedLayer = row;
            }

            if (mx >= LEFT && mx < w - RIGHT
                && my > TIMELINE && my < h - TOP && deformTool) {
                draggingModel = true;
            }
        }

        if (Math.abs(Jpize.getScroll()) > 0.01F
            && hit(mx, my, LEFT, TIMELINE, w - LEFT - RIGHT, h - TOP - TIMELINE)) {
            zoom = clamp(zoom + Jpize.getScroll() * 0.08F, 0.55F, 1.7F);
        }

        if (Key.SPACE.down())
            playing = !playing;
        if (Key.ESCAPE.down())
            Jpize.exit();
    }

    @Override
    public void render() {
        float w = Jpize.getWidth();
        float h = Jpize.getHeight();

        GL.clearAllBuffers();
        batch.setup();

        rect(0, 0, w, h, BG);
        rect(0, h - TOP, w, TOP, PANEL);
        rect(0, TIMELINE, LEFT, h - TOP - TIMELINE, PANEL);
        rect(w - RIGHT, TIMELINE, RIGHT, h - TOP - TIMELINE, PANEL);
        rect(0, 0, w, TIMELINE, PANEL);

        rect(LEFT, TIMELINE, 1, h - TOP - TIMELINE, BORDER);
        rect(w - RIGHT, TIMELINE, 1, h - TOP - TIMELINE, BORDER);
        rect(0, TIMELINE, w, 1, BORDER);

        drawToolbar(w, h);
        drawLayers(h);
        drawInspector(w, h);
        drawTimeline(w);
        drawViewport(w, h);

        batch.render();
    }

    private void drawToolbar(float w, float h) {
        text("MIZU", 16, h - 31, ACCENT, 19);
        text("STUDIO", 70, h - 29, TEXT, 13);
        text("File     Edit     Model     Animation", 170, h - 29, MUTED, 12);

        button(16, h - 40, "Select", !deformTool);
        button(120, h - 40, "Deform", deformTool);
        button(224, h - 40, showGrid ? "Grid ✓" : "Grid", showGrid);
        button(328, h - 40, showMesh ? "Mesh ✓" : "Mesh", showMesh);
        button(w - 112, h - 40, playing ? "Ⅱ Pause" : "▶ Play", playing);
    }

    private void drawLayers(float h) {
        text("HIERARCHY", 16, h - TOP - 28, MUTED, 11);
        text("▾  Mao · Character", 16, h - TOP - 62, TEXT, 13);

        float y = h - TOP - 94;
        for (int i = 0; i < layers.length; i++) {
            if (i == selectedLayer)
                rect(8, y - 7, LEFT - 16, 26, 0xFF39344F);

            rect(17, y, 8, 8, i == selectedLayer ? ACCENT : 0xFF687184);
            text(layers[i], 34, y - 1,
                i == selectedLayer ? TEXT : MUTED, 12);
            y -= 31;
        }

        rect(12, TIMELINE + 48, LEFT - 24, 1, BORDER);
        text("ASSETS", 16, TIMELINE + 26, MUTED, 11);
        text("+ Import texture", 16, TIMELINE + 5, CYAN, 12);
    }

    private void drawInspector(float w, float h) {
        float x = w - RIGHT + 16;
        float width = RIGHT - 32;

        text("INSPECTOR", x, h - TOP - 28, MUTED, 11);
        text(layers[selectedLayer].trim(), x, h - TOP - 64, TEXT, 15);
        text("Deformer properties", x, h - TOP - 88, MUTED, 11);

        slider(x, h - TOP - 137, width, "Bend", bend, 0);
        slider(x, h - TOP - 194, width, "Breathing", breathing, 1);
        slider(x, h - TOP - 251, width, "Blink", blink, 2);

        rect(x - 4, h - TOP - 290, width + 8, 1, BORDER);
        text("GPU SHADER DEFORMER", x, h - TOP - 315, CYAN, 10);
        text("UV bend + breathing warp", x, h - TOP - 338, MUTED, 11);
        text("Zoom  " + (int) (zoom * 100) + "%", x, h - TOP - 377, TEXT, 12);
    }

    private void drawTimeline(float w) {
        text("TIMELINE", 16, TIMELINE - 23, MUTED, 11);

        float left = LEFT + 76;
        float trackW = Math.max(20, w - LEFT - RIGHT - 96);

        for (int i = 0; i < 3; i++) {
            float y = TIMELINE - 56 - i * 34;
            text(new String[]{"Bend", "Breathing", "Blink"}[i],
                16, y + 2, i == 0 ? ACCENT : MUTED, 12);

            rect(left, y, trackW, 1, BORDER);

            for (int k = 0; k < 7; k++) {
                float kx = left + trackW * k / 6F;
                rect(kx - 4, y - 4, 8, 8, k == 0 ? ACCENT : 0xFF687184);
            }
        }

        float playhead = left + (time % 6F) / 6F * trackW;
        rect(playhead, TIMELINE - 142, 1, 104, 0xFFFFB86B);
        text("＋ Keyframe", w - RIGHT - 104, TIMELINE - 23, CYAN, 11);
    }

    private void drawViewport(float w, float h) {
        float x = LEFT;
        float y = TIMELINE;
        float vw = w - LEFT - RIGHT;
        float vh = h - TOP - TIMELINE;

        rect(x, y, vw, vh, 0xFF191C23);

        if (showGrid) {
            for (float gx = x; gx < x + vw; gx += 32)
                rect(gx, y, 1, vh, 0xFF242832);
            for (float gy = y; gy < y + vh; gy += 32)
                rect(x, gy, vw, 1, 0xFF242832);

            rect(x + vw * 0.5F, y, 1, vh, 0xFF343A47);
            rect(x, y + vh * 0.5F, vw, 1, 0xFF343A47);
        }

        float mw = Math.min(vw * 0.50F, 340) * zoom;
        float mh = Math.min(vh * 0.79F, 510) * zoom;
        float mx = x + vw * 0.5F - mw * 0.5F;
        float my = y + vh * 0.5F - mh * 0.5F - 12;

        // Switch to the custom shader for the procedural character.
        batch.setShader(deformer);
        deformer.bind();
        deformer.uniform("u_bend", bend);
        deformer.uniform("u_breath", breathing);
        deformer.uniform("u_blink", blink);
        deformer.uniform("u_time", time);
        deformer.uniform("u_mesh", showMesh ? 1F : 0F);

        batch.drawRect(mx, my, mw, mh, 1, 1, 1, 1);
        batch.render();

        // Return to the normal TextureBatch shader for the UI.
        batch.setShader(null);
        batch.setup();

        rect(mx - 2, my - 2, mw + 4, 1, ACCENT);
        rect(mx - 2, my + mh + 1, mw + 4, 1, ACCENT);
        rect(mx - 2, my - 2, 1, mh + 4, ACCENT);
        rect(mx + mw + 1, my - 2, 1, mh + 4, ACCENT);

        text("VIEWPORT / " + (deformTool ? "DEFORM" : "SELECT"),
            x + 14, y + vh - 23, MUTED, 10);
        text("GPU DEFORMER ACTIVE", x + vw - 166, y + vh - 23, CYAN, 10);
    }

    private void slider(float x, float y, float width, String name,
                        float value, int index) {
        text(name, x, y + 14, TEXT, 12);
        text(String.format(java.util.Locale.ROOT, "%.2f", value),
            x + width - 42, y + 14, CYAN, 11);

        float t = index == 0 ? (value + 1F) * 0.5F : value;
        float ty = y - 7;

        rect(x, ty, width, 4, 0xFF3A3F4B);
        rect(x, ty, width * t, 4, ACCENT);
        rect(x + width * t - 5, ty - 4, 10, 12, TEXT);
    }

    private int findSlider(float mx, float my, float w, float h) {
        float[] ys = {
            h - TOP - 144,
            h - TOP - 201,
            h - TOP - 258
        };

        for (int i = 0; i < ys.length; i++) {
            if (hit(mx, my, w - RIGHT + 12, ys[i] - 12, RIGHT - 24, 32))
                return i;
        }
        return -1;
    }

    private void setSlider(int index, float mx, float w) {
        float x = w - RIGHT + 16;
        float t = clamp((mx - x) / (RIGHT - 32), 0, 1);

        if (index == 0)
            bend = t * 2F - 1F;
        else if (index == 1)
            breathing = t;
        else if (index == 2)
            blink = t;
    }

    private void button(float x, float y, String name, boolean active) {
        rect(x, y, 96, 28, active ? 0xFF39344F : 0xFF282C36);
        rect(x, y + 27, 96, 1, active ? ACCENT : BORDER);
        text(name, x + 10, y + 9, active ? TEXT : MUTED, 12);
    }

    private void text(String value, float x, float y, int color, int size) {
        font.getOptions().scale().set(size / 14F, size / 14F);
        batch.setColori(
            (color >> 16) & 255,
            (color >> 8) & 255,
            color & 255,
            (color >>> 24) & 255
        );
        font.drawText(batch, value, x, y);
        batch.resetColor();
    }

    private void rect(float x, float y, float w, float h, int color) {
        batch.drawRectARGB(x, y, w, h, color);
    }

    private static boolean hit(float px, float py, float x, float y, float w, float h) {
        return px >= x && px <= x + w && py >= y && py <= y + h;
    }

    private static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    @Override
    public void dispose() {
        if (batch != null)
            batch.dispose();
        if (deformer != null)
            deformer.dispose();
        if (font != null)
            font.dispose();
    }

    private static final String VERTEX = """
        #version 300 es
        precision mediump float;

        layout(location = 0) in vec2 v_pos;
        layout(location = 1) in vec2 v_uv;
        layout(location = 2) in vec4 v_color;

        uniform mat4 u_combined;

        out vec2 f_uv;
        out vec4 f_color;

        void main() {
            gl_Position = u_combined * vec4(v_pos, 0.0, 1.0);
            f_uv = v_uv;
            f_color = v_color;
        }
        """;

    private static final String FRAGMENT = """
        #version 300 es
        precision highp float;

        in vec2 f_uv;
        in vec4 f_color;
        out vec4 fragColor;

        uniform float u_bend;
        uniform float u_breath;
        uniform float u_blink;
        uniform float u_time;
        uniform float u_mesh;

        float ellipse(vec2 p, vec2 c, vec2 r) {
            return length((p - c) / r);
        }

        void main() {
            vec2 uv = f_uv;

            // GPU-side UV deformer.
            float cy = uv.y - 0.5;
            uv.x += u_bend * cy * cy * 0.17;
            uv.y += sin(u_time * 2.0) * u_breath
                  * 0.008 * (1.0 - abs(cy));
            uv.x += sin(u_time * 1.3 + uv.y * 7.0)
                  * u_breath * 0.004;

            vec3 color = vec3(0.10, 0.115, 0.145);

            float hair = ellipse(uv, vec2(0.5, 0.56), vec2(0.31, 0.39));
            if (hair < 1.0)
                color = vec3(0.20, 0.13, 0.28);

            float face = ellipse(uv, vec2(0.5, 0.48), vec2(0.235, 0.315));
            if (face < 1.0)
                color = vec3(0.96, 0.73, 0.63);

            float fringe = ellipse(uv, vec2(0.43, 0.72), vec2(0.25, 0.16));
            if (fringe < 1.0 && uv.y > 0.65 + 0.09 * sin(uv.x * 17.0))
                color = vec3(0.24, 0.15, 0.34);

            float eyeH = max(0.035, 0.085 * (1.0 - u_blink));
            float le = ellipse(uv, vec2(0.405, 0.515), vec2(0.038, eyeH));
            float re = ellipse(uv, vec2(0.595, 0.515), vec2(0.038, eyeH));

            if (le < 1.0 || re < 1.0)
                color = vec3(0.18, 0.12, 0.20);
            if (le < 0.48 || re < 0.48)
                color = vec3(0.35, 0.70, 0.77);

            if (ellipse(uv, vec2(0.355, 0.425), vec2(0.045, 0.022)) < 1.0 ||
                ellipse(uv, vec2(0.645, 0.425), vec2(0.045, 0.022)) < 1.0)
                color = vec3(0.96, 0.46, 0.51);

            if (ellipse(uv, vec2(0.5, 0.365), vec2(0.045, 0.018)) < 1.0)
                color = vec3(0.47, 0.18, 0.25);

            if (u_mesh > 0.5) {
                vec2 cell = abs(fract(uv * 18.0) - 0.5);
                float line = min(cell.x, cell.y);
                if (line < 0.018 && face < 1.0)
                    color = mix(color, vec3(0.30, 0.78, 0.78), 0.42);
            }

            float edge = min(min(uv.x, 1.0 - uv.x),
                             min(uv.y, 1.0 - uv.y));
            float alpha = smoothstep(0.0, 0.018, edge);

            fragColor = vec4(color, alpha) * f_color;
        }
        """;

    public static void main(String[] args) {
        GlfwContextBuilder.create(1440, 1080, "Mizu Studio")
            .icon("/icon.png")
            .build()
            .setApp(new MizuStudio());

        GlfwContextManager.run();
    }

}
