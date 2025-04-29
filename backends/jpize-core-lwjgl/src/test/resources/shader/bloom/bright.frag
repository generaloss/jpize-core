#version 300 es
precision mediump float;

in vec2 f_uv;

out vec4 fragColor;

uniform sampler2D u_frame;
uniform float u_brightness;

void main() {
    vec4 color = texture(u_frame, f_uv);
    float brightness = dot(color.rgb, vec3(0.2126, 0.7152, 0.0722));

    if(brightness > u_brightness){
        fragColor = color;
    }else{
        fragColor = vec4(0.0, 0.0, 0.0, 1.0);
    }
}