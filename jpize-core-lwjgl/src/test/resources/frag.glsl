#version 330

in vec2 f_uv;

layout (std140) uniform State {
    mat4 matrix;
    float brightness[2];
};

uniform sampler2D u_texture;

void main() {
    gl_FragColor = texture(u_texture, f_uv) * brightness[1];
}