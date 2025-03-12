#version 300 es
precision mediump float;

in vec3 v_pos;

uniform mat4 u_space;
uniform mat4 u_model;

void main() {
    gl_Position = u_space * u_model * vec4(v_pos, 1.0);
}