package jpize.gl.buffer;

import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;

public class VertexBuffer extends GlVertexBuffer {

    private int vertexSize, vertexBytes;

    public VertexBuffer() {
        bind();
    }


    public void enableAttributes(GlVertAttr... attributes) {
        for(GlVertAttr attribute: attributes){
            vertexSize += attribute.getCount();
            vertexBytes += attribute.getCount() * attribute.getType().size;
        }

        int pointer = 0;
        for(byte i = 0; i < attributes.length; i++){
            final GlVertAttr attribute = attributes[i];
            
            final int count = attribute.getCount();
            final GlType type = attribute.getType();
            
            super.vertexAttribPointer(i, count, type, attribute.isNormalize(), vertexSize * type.size, pointer);
            super.enableVertexAttribArray(i);

            pointer += count * type.size;
        }
    }

    public int getVertexSize() {
        return vertexSize;
    }
    
    public int getVertexBytes() {
        return vertexBytes;
    }

    public int getVertexCount() {
        return getSize() / vertexSize;
    }

}