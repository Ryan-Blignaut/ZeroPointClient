#version 430 compatibility

precision highp float;

layout(location = 0) uniform float u_Radius;
layout(location = 1) uniform vec2 u_CenterPos;

in vec2 f_Position;

out vec4 fragColor;

void main() {

    float v = length(f_Position - u_CenterPos);

    float a = 1.0 - smoothstep(u_Radius.x - 1.0, u_Radius.x, v);

    fragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);
}

vec4 erf(vec4 x) {
    vec4 s = sign(x), a = abs(x);
    x = 1.0 + (0.278393 + (0.230389 + 0.078108 * (a * a)) * a) * a;
    x *= x;
    return s - s / (x * x);
}

// Return the mask for the shadow of a box from lower to upper
float boxShadow(vec2 lower, vec2 upper, vec2 point, float sigma) {
    vec4 query = vec4(point - lower, upper - point);
    vec4 integral = 0.5 + 0.5 * erf(query * (sqrt(0.5) / sigma));
    return (integral.z - integral.x) * (integral.w - integral.y);
}
