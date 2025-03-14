#version 300 es
precision mediump float;

in vec2 f_uv;
in vec4 f_color;

out vec4 fragColor;

uniform sampler2D u_texture;

void main() {
    fragColor = f_color * texture(u_texture, f_uv);
}
