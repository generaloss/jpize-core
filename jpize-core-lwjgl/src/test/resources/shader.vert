#version 330

in vec3 v_pos;
in vec2 v_uv;

out vec2 f_uv;

uniform mat4 u_combined;

void main(){
    gl_Position = u_combined * vec4(v_pos, 1.0);
    f_uv = v_uv;
}