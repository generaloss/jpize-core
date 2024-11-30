#version 330

in vec3 pos;

uniform samplerCube u_cubemap;
uniform vec4 u_color;

void main() {
    gl_FragColor = texture(u_cubemap, pos) * u_color;
}