#version 300 es
precision mediump float;

in vec2 v_pos;
in vec4 v_color;

out vec4 f_color;

uniform mat4 u_combined;

void main() {
    gl_Position = u_combined * vec4(v_pos, 0.0, 1.0);
    f_color = v_color;
}