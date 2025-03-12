#version 300 es
precision mediump float;

in vec3 f_pos;

out vec4 fragColor;

uniform samplerCube u_cubemap;
uniform vec4 u_color;

void main() {
    fragColor = texture(u_cubemap, f_pos) * u_color;
}