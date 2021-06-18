#version 430 compatibility

precision highp float;

smooth in vec2 position;
smooth in vec4 vertexColor;

out vec4 fragColor;


void main() {
    fragColor = vertexColor;
}