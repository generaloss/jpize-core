package jpize.app;

import jpize.gl.Gl;
import jpize.gl.glenum.GlCompareFunc;
import jpize.gl.glenum.GlTarget;
import jpize.gl.texture.GlBlendFactor;
import jpize.glfw.Glfw;
import jpize.glfw.GlfwImage;
import jpize.glfw.init.GlfwPlatform;
import jpize.glfw.monitor.GlfwMonitor;
import jpize.glfw.window.GlfwWindow;
import jpize.glfw.window.GlfwWindowHint;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.res.Resource;

public class ContextBuilder {

    private final int width;
    private final int height;
    private final String title;

    protected ContextBuilder(String title, int width, int height) {
        // waiting for wayland fix in lwjgl 3.4.0
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
        // window
        final GlfwWindow window = new GlfwWindow(width, height, title, monitor, shared);
        // icon
        if(icons != null)
            window.setIcon(icons);

        // context
        final Context context = new Context(window);
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

    public ContextBuilder monitor(GlfwMonitor monitor) {
        this.monitor = monitor;
        return this;
    }

    private GlfwWindow shared;

    public ContextBuilder shared(GlfwWindow shared) {
        this.shared = shared;
        return this;
    }

    private GlfwImage[] icons;

    public ContextBuilder icon(GlfwImage... icons) {
        if(icons == null || icons.length == 0)
            return this;
        this.icons = icons;
        return this;
    }

    public ContextBuilder icon(PixmapRGBA... pixmaps) {
        if(pixmaps == null || pixmaps.length == 0)
            return this;
        this.icons = GlfwImage.makeArray(pixmaps);
        return this;
    }

    public ContextBuilder icon(Resource... resources) {
        if(resources == null || resources.length == 0)
            return this;
        this.icons = GlfwImage.loadArray(resources);
        return this;
    }

    public ContextBuilder icon(String... internalPaths) {
        if(internalPaths == null || internalPaths.length == 0)
            return this;
        this.icons = GlfwImage.loadArray(internalPaths);
        return this;
    }

    private boolean decorated = true;

    public ContextBuilder decorated(boolean decorated) {
        this.decorated = decorated;
        return this;
    }

    private boolean resizable = true;

    public ContextBuilder resizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    private int samples = 0;

    public ContextBuilder samples(int samples) {
        this.samples = samples;
        return this;
    }

    private boolean focusOnShow = false;

    public ContextBuilder focusOnShow(boolean focusOnShow) {
        this.focusOnShow = focusOnShow;
        return this;
    }

}