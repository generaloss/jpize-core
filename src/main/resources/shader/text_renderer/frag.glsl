#version 300 es
precision mediump float;

in vec2 f_uv;
in vec4 f_color;

out vec4 fragColor;

uniform sampler2D u_texture;

void main() {
    vec4 color = texture2D(u_texture, f_uv) * f_color;
    if(color.a <= 0.0)
        discard;
    fragColor = color;
}