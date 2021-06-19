#version 450 core

layout(location = 0) uniform sampler2D diffuseSampler;

in vec2 texCoord;
in vec2 oneTexel;

out vec4 fragColor;

void main() {

    float radius = 1.2;

    vec4 centerCol = texture2D(diffuseSamper, texCoord);
    if(centerCol.a != 0.0F) return;

    for(float x = -radius; x <= radius; x++){
        for(float y = -radius; y <= radius; y++){
            vec4 currentColor = texture2D(diffuseSamper, texCoord + vec2(oneTexel.x * x, oneTexel.y * y));
            if(currentColor.a != 0.0F){
                fragColor = vec4(1, 0, 0, 1);
            }
        }
    }

}