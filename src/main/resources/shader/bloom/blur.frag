#version 300 es
precision mediump float;

in vec2 f_uv;

out vec4 fragColor;

uniform sampler2D u_frame;
uniform float u_radius;

void main() {
    float pi2 = 3.1415926535 * 2;

    float quality = 8;
    float directions = 32;

    vec2 radius = u_radius / textureSize(u_frame, 0.0);
    vec4 color = vec4(0.0);

    for(float i = 1.0 / quality; i <= 1.0; i += 1.0 / quality)
        for(float d = 0.0; d < pi2; d += pi2 / directions)
            color += texture(u_frame, f_uv + vec2(cos(d), sin(d)) * radius * i);

    color /= quality * directions - 15.0;
    fragColor = color;
}