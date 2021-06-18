#version 450 core

layout(location = 0) uniform sampler2D u_Sampler;
layout(location = 1) uniform float Saturation;
in vec2 texCoord;
in vec2 oneTexel;

out vec4 fragColor;
const vec3 luminance = vec3(0.2126, 0.7152, 0.0722);

void main() {
    vec4 sampleValue = texture(u_Sampler, texCoord);
    vec3 gray = vec3(dot(sampleValue.rgb, luminance));
    fragColor = vec4(mix(gray, sampleValue.rgb, Saturation), sampleValue.a);
    
}