# [Core Module](https://github.com/generaloss/jpize-core)
![jpize](logo.svg)

---

## Getting Started

### Maven
```xml
<!-- jpize-core -->
<dependency>
    <groupId>io.github.generaloss</groupId>
    <artifactId>jpize-core</artifactId>
    <version>24.2.2</version>
</dependency>
```
### Gradle (Kotlin)
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.generaloss:jpize-core:24.2.2")
}
```

---

## Examples
#### 1. Application
``` java
public class MyApp extends JpizeApplication {

    public static void main(String[] args) {
        // create window context
        Jpize.create(1280, 720, "Window Title")
            .icon("/icon.png").build()
            .setApp(new MyApp());
        
        // run created contexts
        Jpize.run();
    }
    
    public MyApp() { } // constructor calls before init()
    
    public void init() { } // init() calls after Jpize.run();
    
    public void update() { } // update loop
    
    public void render() { } // render loop
    
    public void resize(int width, int height) { } // calls when window resizes
    
    public void dispose() { } // exit app
    
}
```

#### 2. 2D Graphics:
``` java
TextureBatch batch = new TextureBatch(); // canvas for textures
GlTexture2D texture = new GlTexture2D("/texture.png");

Gl.clearColorBuffer();
batch.begin();

// rotate, shear and scale for subsequent textures
batch.rotate(angle);
batch.shear(angle_x, angle_y);
batch.scale(scale);
// draw texture
batch.draw(texture, x, y, width, height);
// draw rectangle
batch.drawRect(r, g, b, a,  x, y,  width, height);
batch.drawRect(a,  x, y,  width, height);

batch.end();
```

#### 3. Fonts:
``` java
// load
Font font = FontLoader.getDefault();

Font font = FontLoader.loadFnt(path_or_resource);

Font font = FontLoader.loadTrueType(path_or_resource, size);
Font font = FontLoader.loadTrueType(path_or_resource, size, charset);

// options
font.options.scale = 1.5F;
font.options.rotation = 45;
font.options.italic = true;
font.options.invLineWrap = true;

// bounds
float width = font.getTextWidth(line);
float height = font.getTextHeight(text);
Vec2f bounds = font.getBounds(text);

// render
font.drawText(batch, text, x, y)
```

#### 4. Input:
``` java
// mouse
Jpize.getX()  // position
Jpize.getY()

Jpize.isTouched()    // touch
Jpize.isTouchDown()
Jpize.isTouchReleased()

Jpize.input().getScroll()  // scroll

Btn.LEFT.isDown()     // buttons
Btn.LEFT.isPressed()
Btn.LEFT.isReleased()

// keyboard
Key.ENTER.isPressed()
Key.ESCAPE.isDown()
Key.SPACE.isReleased()

// window
Jpize.getWidth()
Jpize.getHeight()
Jpize.getAspect()

// FPS & Delta Time
Jpize.getFPS()
Jpize.getDt()
```

---

## Used libs:
* *[LWJGL3](https://github.com/LWJGL/lwjgl3)* (GLFW, OpenGL, STB)
* *[Jpize-Utils](https://github.com/generaloss/jpize-utils)*


---

## Bugs and Feedback
For bugs, questions and discussions please use the [GitHub Issues](https://github.com/generaloss/jpize-engine/issues).
