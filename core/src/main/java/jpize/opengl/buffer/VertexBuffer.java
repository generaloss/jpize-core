package jpize.opengl.buffer;

import jpize.opengl.type.GlType;
import jpize.opengl.vertex.GlVertAttr;

public class VertexBuffer extends GlVertexBuffer {

    private int vertexSize, vertexBytes;

    public VertexBuffer() {
        super.bind();
    }


    public void enableAttributes(GlVertAttr... attributes) {
        if(attributes.length == 0)
            throw new IllegalArgumentException("Attributes must not be empty");

        for(GlVertAttr attribute: attributes){
            vertexSize += attribute.getCount();
            vertexBytes += (attribute.getCount() * attribute.getType().size);
        }

        int pointer = 0;
        for(byte i = 0; i < attributes.length; i++){
            final GlVertAttr attribute = attributes[i];
            
            final int count = attribute.getCount();
            if(count < 1)
                throw new IllegalArgumentException("Attribute 'count' must not be at least 1");

            final GlType type = attribute.getType();
            
            super.vertexAttribPointer(i, count, type, attribute.isNormalize(), vertexSize * type.size, pointer);
            super.enableVertexAttribArray(i);

            pointer += (count * type.size);
        }
    }

    public int getVertexSize() {
        return vertexSize;
    }
    
    public int getVertexBytes() {
        return vertexBytes;
    }

    public int getVertexCount() {
        return (super.getSize() / vertexSize);
    }

}