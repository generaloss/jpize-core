package jpize.lwjgl.glfw.context;

import jpize.opengl.gl.GL;
import jpize.opengl.glenum.GLTarget;
import jpize.lwjgl.glfw.Glfw;
import jpize.lwjgl.glfw.GlfwImage;
import jpize.lwjgl.glfw.monitor.GlfwMonitor;
import jpize.lwjgl.glfw.window.GlfwWindow;
import jpize.lwjgl.glfw.window.GlfwWindowHint;
import jpize.util.pixmap.PixmapRGBA;
import resourceflow.resource.Resource;
import org.lwjgl.glfw.GLFW;

public class GlfwContextBuilder {

    public static GlfwContextBuilder create(int width, int height, String title) {
        return new GlfwContextBuilder(title, width, height);
    }

    public static GlfwContextBuilder create(String title, int width, int height) {
        return new GlfwContextBuilder(title, width, height);
    }

    private final int width;
    private final int height;
    private final String title;

    private GlfwContextBuilder(String title, int width, int height) {
        // noinspection ResultOfMethodCallIgnored (lazy glfw init)
        GlfwContextManager.instance();
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public GlfwContext build() {
        // window hints
        Glfw.defaultWindowHints();
        Glfw.windowHint(GlfwWindowHint.VISIBLE, false);
        Glfw.windowHint(GlfwWindowHint.DECORATED, decorated);
        Glfw.windowHint(GlfwWindowHint.RESIZABLE, resizable);
        Glfw.windowHint(GlfwWindowHint.SAMPLES, samples);
        Glfw.windowHint(GlfwWindowHint.FOCUS_ON_SHOW, focusOnShow);
        Glfw.windowHint(GlfwWindowHint.TRANSPARENT_FRAMEBUFFER, transparentFramebuffer);
        // macos
        final String osname = System.getProperty("os.name").toLowerCase();
        if(osname.contains("mac")) {
            Glfw.windowHint(GlfwWindowHint.OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
            Glfw.windowHint(GlfwWindowHint.OPENGL_FORWARD_COMPAT, true);
            Glfw.windowHint(GlfwWindowHint.OPENGL_FORWARD_COMPAT, true);
            Glfw.windowHint(GlfwWindowHint.CONTEXT_VERSION_MAJOR, 4);
            Glfw.windowHint(GlfwWindowHint.CONTEXT_VERSION_MINOR, 1);
        }

        // window
        final GlfwWindow window = new GlfwWindow(width, height, title, monitor, shared);
        // icon
        if(icons != null)
            window.setIcon(icons);
        // context
        final GlfwContext context = new GlfwContext(window);
        // multisample
        if(samples > 0)
            GL.enable(GLTarget.MULTISAMPLE);

        return context;
    }


    private GlfwMonitor monitor;

    public GlfwContextBuilder monitor(GlfwMonitor monitor) {
        this.monitor = monitor;
        return this;
    }

    private GlfwWindow shared;

    public GlfwContextBuilder shared(GlfwWindow shared) {
        this.shared = shared;
        return this;
    }

    private GlfwImage[] icons;

    public GlfwContextBuilder icon(GlfwImage... icons) {
        if(icons == null || icons.length == 0)
            return this;
        this.icons = icons;
        return this;
    }

    public GlfwContextBuilder icon(PixmapRGBA... pixmaps) {
        if(pixmaps == null || pixmaps.length == 0)
            return this;
        this.icons = GlfwImage.makeArray(pixmaps);
        return this;
    }

    public GlfwContextBuilder icon(Resource... resources) {
        if(resources == null || resources.length == 0)
            return this;
        this.icons = GlfwImage.loadArray(resources);
        return this;
    }

    public GlfwContextBuilder icon(String... internalPaths) {
        if(internalPaths == null || internalPaths.length == 0)
            return this;
        this.icons = GlfwImage.loadArray(internalPaths);
        return this;
    }

    private boolean decorated = true;

    public GlfwContextBuilder decorated(boolean decorated) {
        this.decorated = decorated;
        return this;
    }

    private boolean resizable = true;

    public GlfwContextBuilder resizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    private int samples = 0;

    public GlfwContextBuilder samples(int samples) {
        this.samples = samples;
        return this;
    }

    private boolean focusOnShow = false;

    public GlfwContextBuilder focusOnShow() {
        this.focusOnShow = true;
        return this;
    }

    private boolean transparentFramebuffer = false;

    public GlfwContextBuilder transparentFramebuffer() {
        this.transparentFramebuffer = true;
        return this;
    }

}