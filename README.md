# [Core Module](https://github.com/generaloss/jpize-core)
![jpize](logo.svg)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.generaloss/jpize-core.svg)](https://mvnrepository.com/artifact/io.github.generaloss/jpize-core)

---

## Examples
#### 1. Application
``` java
public class MyApp extends JpizeApplication {

    public static void main(String[] args) {
        // create window context
        Jpize.create(1280, 720, "Window Title")
            .icon("/icon.png")
            .build().setApp(new MyApp());
        
        // run created contexts
        Jpize.run();
    }
    
    public MyApp() { } // constructor calls before init()
    
    @Override
    public void init() { } // init() calls after Jpize.run();
    
    @Override
    public void update() { } // update loop
    
    @Override
    public void render() { } // render loop
    
    @Override
    public void resize(int width, int height) { } // calls when window resizes
    
    @Override
    public void dispose() { } // exit app
    
}
```

#### 2. 2D Graphics:
``` java
TextureBatch batch = new TextureBatch(); // canvas for textures
Texture2D texture = new Texture2D("/texture.png");

GL.clearColorBuffer();
batch.setup();

// rotate, shear and scale for subsequent textures
batch.rotate(angle);
batch.shear(angle_x, angle_y);
batch.scale(scale);
// draw texture
batch.draw(texture, x, y, width, height);
// draw rectangle
batch.drawRect(x, y,  width, height,  color);
batch.drawRect(x, y,  width, height,  r, g, b, a);
batch.drawRect(x, y,  width, height,  r, g, b);
batch.drawRect(x, y,  width, height,  alpha);

batch.render();
```

#### 3. Fonts:
``` java
// load
Font font = new Font().loadDefault();
Font font = new Font().loadFnt(path_or_resource, linearFilter);
Font font = new Font().loadTrueType(path_or_resource, size, charset, linearFilter);

// options
FontRenderOptions options = font.getRenderOptions();

options.enableCullLines(0F, Jpize.getHeight());
options.color().set(0.95, 0.95, 0.93);
options.scale().set(1.5F);
options.setRotation(45F);
options.setItalicAngle(15F);
options.setInvLineWrap(true);

// bounds
float width = font.getTextWidth(line);
float height = font.getTextHeight(text);
Vec2f bounds = font.getTextBounds(text);

// render
font.drawText(text, x, y);
font.drawText(batch, text, x, y);
Iterable<GlyphSprite> iterable = font.iterable(text);
```

#### 4. Input:
``` java
// mouse position
Jpize.getX()  
Jpize.getY()

// scrolling
Jpize.getScroll() 

// mouse buttons
MouseBtn.LEFT.down()     
MouseBtn.RIGHT.pressed()
MouseBtn.MIDDLE.up()

// keys
Key.ENTER.pressed()
Key.ESCAPE.down()
Key.SPACE.up()

// window
Jpize.getWidth()
Jpize.getHeight()

// FPS & Delta Time
Jpize.getFPS()
Jpize.getDeltaTime()
```

---

## Used libs:
* *[LWJGL3](https://github.com/LWJGL/lwjgl3)* (GLFW, OpenGL, STB)
* *[Jpize-Utils](https://github.com/generaloss/jpize-utils)*


---

## Bugs and Feedback
For bugs, questions and discussions please use the [GitHub Issues](https://github.com/generaloss/jpize-core/issues).
