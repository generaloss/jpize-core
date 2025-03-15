package jpize.util.atlas;

import jpize.util.math.geometry.Recti;
import jpize.util.pixmap.PixmapRGBA;

class AtlasNode {
    
    private PixmapRGBA imagePixmap;
    private final Recti rectangle;
    private AtlasNode child1, child2;

    public AtlasNode(int x, int y, int width, int height) {
        this.rectangle = new Recti(x, y, width, height);
    }
    
    
    public boolean hasImage() {
        return imagePixmap != null;
    }
    
    public int getX() {
        return rectangle.x;
    }
    
    public int getY() {
        return rectangle.y;
    }
    
    public int getWidth() {
        return rectangle.width;
    }
    
    public int getHeight() {
        return rectangle.height;
    }


    public AtlasNode insert(PixmapRGBA imagePixmap, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        final int x = this.getX();
        final int y = this.getY();
        final int width  = this.getWidth();
        final int height = this.getHeight();
        
        final int imageWidth  = imagePixmap.getWidth() + paddingRight;
        final int imageHeight = imagePixmap.getHeight() + paddingBottom;
        
        if(child1 == null && child2 == null){ // Is leaf
            if(this.hasImage()) // Occupied
                return null;

            final int diffWidth = width - imageWidth;
            final int diffHeight = height - imageHeight;
            
            if(imageWidth > width || imageHeight > height) // Does not fit
                return null;
            
            if(imageWidth == width && imageHeight == height){ // Perfect fit
                this.imagePixmap = imagePixmap;
                return this;
            }
            
            if(diffWidth > diffHeight){ // X
                child1 = new AtlasNode(
                    x,
                    y,
                    imageWidth,
                    height
                );
                
                child2 = new AtlasNode(
                    x + imageWidth + paddingLeft,
                    y,
                    width - imageWidth - paddingLeft,
                    height
                );
            }else{ // Y
                child1 = new AtlasNode(
                    x,
                    y,
                    width,
                    imageHeight
                );
                
                child2 = new AtlasNode(
                    x,
                    y + imageHeight + paddingTop,
                    width,
                    height - imageHeight - paddingTop
                );
            }

            return child1.insert(imagePixmap, paddingLeft, paddingTop, paddingRight, paddingBottom);
        }else{
            @SuppressWarnings("DataFlowIssue")
            final AtlasNode newNode = child1.insert(imagePixmap, paddingLeft, paddingTop, paddingRight, paddingBottom);
            if(newNode != null)
                return newNode;

            return child2.insert(imagePixmap, paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }

}
