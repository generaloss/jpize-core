package jpize.lwjgl.app;

import jpize.app.Context;
import jpize.app.ContextManager;
import jpize.opengl.gl.Gl;
import jpize.opengl.glenum.GlCompareFunc;
import jpize.opengl.glenum.GlTarget;
import jpize.opengl.texture.GlBlendFactor;
import jpize.lwjgl.glfw.Glfw;
import jpize.lwjgl.glfw.GlfwImage;
import jpize.lwjgl.glfw.init.GlfwPlatform;
import jpize.lwjgl.glfw.monitor.GlfwMonitor;
import jpize.lwjgl.glfw.window.GlfwWindow;
import jpize.lwjgl.glfw.window.GlfwWindowHint;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.res.Resource;

public class GlfwContextBuilder {

    public static GlfwContextBuilder create(int width, int height, String title) {
        return new GlfwContextBuilder(title, width, height);
    }

    public static GlfwContextBuilder create(String title, int width, int height) {
        return new GlfwContextBuilder(title, width, height);
    }

    static {
        // init glfw
        Glfw.init();
        Glfw.swapInterval(1);
    }

    private final int width;
    private final int height;
    private final String title;

    private GlfwContextBuilder(String title, int width, int height) {
        // waiting for wayland fix in lwjgl
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11);

        ContextManager.instance(); // init
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public Context build() {
        // window hints
        Glfw.defaultWindowHints();
        Glfw.windowHint(GlfwWindowHint.VISIBLE, false);
        Glfw.windowHint(GlfwWindowHint.VISIBLE, false);
        Glfw.windowHint(GlfwWindowHint.DECORATED, decorated);
        Glfw.windowHint(GlfwWindowHint.RESIZABLE, resizable);
        Glfw.windowHint(GlfwWindowHint.SAMPLES, samples);
        Glfw.windowHint(GlfwWindowHint.FOCUS_ON_SHOW, focusOnShow);
        Glfw.windowHint(GlfwWindowHint.TRANSPARENT_FRAMEBUFFER, transparentFramebuffer);
        // window
        final GlfwWindow window = new GlfwWindow(width, height, title, monitor, shared);
        // icon
        if(icons != null)
            window.setIcon(icons);

        // context
        final GlfwContext context = new GlfwContext(window);
        // default blending options, enable cullface
        Gl.enable(GlTarget.BLEND, GlTarget.CULL_FACE);
        Gl.blendFunc(GlBlendFactor.SRC_ALPHA, GlBlendFactor.ONE_MINUS_SRC_ALPHA);
        // opengl left-handled coordinate system options
        Gl.depthFunc(GlCompareFunc.GEQUAL);
        Gl.clearDepth(0);
        // multisample
        if(samples > 0)
            Gl.enable(GlTarget.MULTISAMPLE);

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