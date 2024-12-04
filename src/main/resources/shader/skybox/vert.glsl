#version 330

in vec3 v_pos;
out vec3 f_pos;
uniform mat4 u_combined;

void main() {
    gl_Position = u_combined * vec4(v_pos, 1.0);
    f_pos = v_pos;
}