#version 330

in vec3 pos;

uniform samplerCube u_cubemap;

void main(){
    gl_FragColor = texture(u_cubemap, pos);
}