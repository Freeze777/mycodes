#ifndef model_h
#define model_h

#include "PlyUtility.h"
#include "PlyModel.h"
#include "BoundingBox.h"

class Model{
BoundingBox box;
PlyModel *plymodel;
PlyUtility *ply;
GLfloat origin[3] = {0.0, 0.0, 0.0};
public:


Model();
~Model();

void readModelFromFile(char *filename);
void drawScene(float dim);
void drawAxis(float dim);
void markPoints(float dim);
   

};



#endif
