#version 300

in vec3 f_pos;

uniform samplerCube u_cubemap;
uniform vec4 u_color;

void main() {
    gl_FragColor = texture(u_cubemap, f_pos) * u_color;
}