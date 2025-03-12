#version 300 es
precision mediump float;

in vec2 f_uv;

out vec4 fragColor;

uniform sampler2D u_frame1;
uniform sampler2D u_frame2;

uniform float u_bloom;
uniform float u_exposure;
uniform float u_gamma;

void main() {
    vec4 color = texture(u_frame1, f_uv);
    color.rgb += texture(u_frame2, f_uv).rgb * u_bloom;

    // tone mapping
    color.rgb = vec3(1.0) - exp( -color.rgb * u_exposure);
    // gamma correction
    color.rgb = pow(color.rgb, vec3(1.0 / u_gamma));

    fragColor = color;
}