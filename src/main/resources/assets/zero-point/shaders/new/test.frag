#version 450 core

layout(location = 0) uniform sampler2D u_Sampler;
layout(location = 1) uniform float radius;
layout(location = 2) uniform vec2 dir;

in vec2 texCoord;
in vec2 oneTexel;

out vec4 fragColor;

void main() {
    vec4 blurred = vec4(0.0);
    float totalStrength = 0.0;
    float totalAlpha = 0.0;
    float totalSamples = 0.0;
    for (float r = -radius; r <= radius; r += 1.0) {
        vec4 sampleValue = texture(u_Sampler, texCoord + oneTexel * r * dir);

        // Accumulate average alpha
        totalAlpha = totalAlpha + sampleValue.a;
        totalSamples = totalSamples + 1.0;

        // Accumulate smoothed blur
        float strength = 1.0 - abs(r / radius);
        totalStrength = totalStrength + strength;
        blurred = blurred + sampleValue;
    }
    fragColor = vec4(blurred.rgb / (5 * 2.0 + 1.0), totalAlpha);
}