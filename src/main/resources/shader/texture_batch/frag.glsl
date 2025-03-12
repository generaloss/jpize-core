#version 300

in vec2 f_uv;
in vec4 f_color;

uniform sampler2D u_texture;

void main() {
    gl_FragColor = f_color * texture2D(u_texture, f_uv);
}
