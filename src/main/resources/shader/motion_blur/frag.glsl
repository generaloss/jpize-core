#version 330

in vec2 f_uv;
uniform sampler2D u_frame;
uniform sampler2D u_backFrame;

void main() {
    gl_FragColor = mix(texture2D(u_frame, f_uv), texture2D(u_backFrame, f_uv), 0.6);
}