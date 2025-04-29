#version 330

in vec2 f_uv;

uniform sampler2D u_texture;

void main(){
    gl_FragColor = vec4(0, 1, 0, 1);//texture2D(u_texture, f_uv);
}