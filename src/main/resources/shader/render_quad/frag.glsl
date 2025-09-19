#version 300 es
precision mediump float;

in vec2 f_uv;

out vec4 fragColor;

uniform sampler2D u_texture;

void main() {
    vec4 color = texture(u_texture, f_uv);
    if(color.a <= 0.0)
        discard;
    fragColor = color;
}