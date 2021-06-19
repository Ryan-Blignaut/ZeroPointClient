#version 450 core

layout(location = 0) uniform sampler2D u_Sampler;
layout(location = 1) uniform float Saturation;
in vec2 texCoord;
in vec2 oneTexel;
in vec2 size;


out vec4 fragColor;
const vec3 luminance = vec3(0.2126, 0.7152, 0.0722);
const  float Pi = 6.28318530718;// Pi*2
const float Directions = 16.0;// BLUR DIRECTIONS (Default 16.0 - More is better but slower)
const float Quality = 4.0;// BLUR QUALITY (Default 4.0 - More is better but slower)
const float Size = 32.0;// BLUR SIZE (Radius)

void main() {
    vec2 Radius = Size/size;
    // Pixel colour
    vec4 Color = texture(u_Sampler, texCoord);
    // Blur calculations
    for (float d=0.0; d<Pi; d+=Pi/Directions)
    {
        for (float i= 1.0 / Quality; i <= 1.0; i += 1.0 / Quality)
        {
            Color += texture(u_Sampler, texCoord + vec2(cos(d), sin(d)) * Radius * i);
        }
    }

    // Output to screen
    Color /= Quality * Directions - 15.0;
    fragColor =  Color;


}