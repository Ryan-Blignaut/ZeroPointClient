#version 430 compatibility

precision highp float;

layout(location = 0) uniform vec2 centerPos;
layout(location = 1) uniform vec2 radius;

smooth in vec2 position;
smooth in vec4 vertexColor;

out vec4 fragColor;


void main() {
    if (vertexColor.a == 0.0) {
        discard;
    }

    //Distance from position to center
    float v = length(position - centerPos);
    //1 - interpelated value from radius - feather to radius where v is source
    float alpha = 1.0 - smoothstep(radius.x - radius.y, radius.x, v);

    fragColor = vertexColor * vec4(1.0, 1.0, 1.0, alpha);
}