#version 330

in vec2 v_pos;
in vec2 v_uv;

out vec2 f_uv;

layout (std140) uniform State {
    mat4 mat;
};

void main() {
    gl_Position = mat * vec4(v_pos, 0.0, 1.0);
    f_uv = v_uv;
}