#version 300 es
precision mediump float;

in vec2 f_uv;
out vec4 fragColor;

uniform sampler2D u_frame1; // base scene
uniform sampler2D u_frame2;
uniform sampler2D u_frame3;
uniform sampler2D u_frame4;
uniform sampler2D u_frame5;
uniform sampler2D u_frame6;

uniform float u_bloom;
uniform float u_exposure;
uniform float u_gamma;

void main() {
    vec3 bloomColor = texture(u_frame2, f_uv).rgb +
                      texture(u_frame3, f_uv).rgb +
                      texture(u_frame4, f_uv).rgb +
                      texture(u_frame5, f_uv).rgb +
                      texture(u_frame6, f_uv).rgb;

    vec3 color = texture(u_frame1, f_uv).rgb + bloomColor * u_bloom;

    color = vec3(1.0) - exp(-color * u_exposure);   // tone mapping
    color = pow(color, vec3(1.0 / u_gamma));        // gamma correction

    fragColor = vec4(color, 1.0);
}