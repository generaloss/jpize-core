#version 300 es
precision mediump float;

layout(location = 0) in vec3 v_pos;
layout(location = 1) in vec2 v_uv;
layout(location = 2) in vec4 v_color;

out vec2 f_uv;
out vec4 f_color;

uniform mat4 u_combined;

void main(){
    gl_Position = u_combined * vec4(v_pos, 1.0);
    f_uv = v_uv;
    f_color = v_color;
}