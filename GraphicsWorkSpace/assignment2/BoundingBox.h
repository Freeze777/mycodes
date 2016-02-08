#ifndef bound_box
#define bound_box
#include <GL/glu.h>
#include <GL/gl.h>
#include <GL/glut.h>
class BoundingBox{

private:
float v[8][3];


public:
void draw(float dim);


};

#endif
