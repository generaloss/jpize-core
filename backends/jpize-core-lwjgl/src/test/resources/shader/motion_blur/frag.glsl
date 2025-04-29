#version 300 es
precision mediump float;

in vec2 f_uv;

out vec4 fragColor;

uniform sampler2D u_frame;
uniform sampler2D u_backFrame;

void main() {
    fragColor = mix(texture(u_frame, f_uv), texture(u_backFrame, f_uv), 0.6);
}