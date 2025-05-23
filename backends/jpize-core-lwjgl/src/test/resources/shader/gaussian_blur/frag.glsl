#version 300 es
precision mediump float;

in vec2 f_uv;

out vec4 fragColor;

uniform sampler2D u_frame;
uniform int u_axis;
uniform float u_radius;

void main() {
    vec2 p = f_uv;
    ivec2 res = textureSize(u_frame, 0);

    float x,y, rr = u_radius * u_radius, d, w, w0;
    vec4 col = vec4(0.0);
    w0 = 0.5135 / pow(u_radius, 0.96);

    if(u_axis == 0) {
        for (d = 1.0 / float(res.x), x = -u_radius, p.x += x * d; x <= u_radius;  x++, p.x += d){
            w = w0 * exp((-x * x) / (2.0 * rr));
            col += texture(u_frame, p) * w;
        }
    }else {
        for (d = 1.0 / float(res.y), y = -u_radius, p.y += y * d;  y <= u_radius;  y++, p.y += d){
            w = w0 * exp((-y * y) / (2.0 * rr));
            col += texture(u_frame, p) * w;
        }
    }
    if(col.a <= 0.5)
        discard;

    fragColor = col;
}