#version 430 compatibility

in vec3 Position;
in vec4 Color;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

smooth out vec2 position;
smooth out vec4 vertexColor;

void main() {

    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    position = Position.xy;
    vertexColor = Color;
}