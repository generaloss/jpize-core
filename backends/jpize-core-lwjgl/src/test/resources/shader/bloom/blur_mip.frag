#version 300 es
precision mediump float;

in vec2 f_uv;
out vec4 fragColor;

uniform sampler2D u_frame;
uniform vec2 u_texelSize; // 1.0 / textureSize(u_frame, 0)
uniform vec2 u_direction; // (1, 0) or (0, 1)

void main() {
    vec4 result = vec4(0.0);
    float weights[5] = float[](0.227027, 0.1945946, 0.1216216, 0.054054, 0.016216);

    result += texture(u_frame, f_uv) * weights[0];
    for (int i = 1; i < 5; ++i) {
        vec2 offset = float(i) * u_direction * u_texelSize;
        result += texture(u_frame, f_uv + offset) * weights[i];
        result += texture(u_frame, f_uv - offset) * weights[i];
    }

    fragColor = result;
}