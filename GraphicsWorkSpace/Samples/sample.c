#include <GL/glut.h> // include point types and operations
#include <stdlib.h> //includes random number generator
typedef vec2 point2; //defines a point2 type identical to a vec2
void init()
{
const int NumPoints = 5000;
point2 points[NumPoints];
// A triangle in the plane z= 0
point2 vertices[3]={point2(-1.0,-1.0), point2(0.0,1.0),
point2(1.0,-1.0)};
// An arbitrary initial point inside the triangle
points[0] = point2(0.25, 0.50);
// compute and store NumPoints-1 new points
for(int k = 1; k < NumPoints; k++)
{
int j = rand() % 3; // pick a vertex at random
// Compute the point halfway between selected
// vertex and previous point
points[k] = (points[k-1]+vertices[j])/2.0;
}
}
