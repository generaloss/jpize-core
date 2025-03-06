#version 330

in vec2 v_pos;
in vec2 v_uv;

out vec2 f_uv;

uniform mat4 u_proj;

void main() {
    gl_Position = u_proj * vec4(v_pos, 0.0, 1.0);
    f_uv = v_uv;
}