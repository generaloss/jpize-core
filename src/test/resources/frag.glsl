#version 330

in vec2 f_uv;

layout (std140) uniform Textures {
    sampler2D u_texture;
} textures;

void main() {
    gl_FragColor = texture(textures.u_texture, f_uv);
}