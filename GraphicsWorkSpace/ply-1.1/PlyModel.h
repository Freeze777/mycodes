
#ifndef plymodel_h
#define plymodel_h
#include "PlyUtility.h"
#include <GL/glu.h>
#include <GL/gl.h>
#include <GL/glut.h>
class PlyModel{

private:
GLfloat centroid[3]={0.02,-0.09 ,-0.015};
   
public:
    void draw(PlyUtility ply);

};

#endif
