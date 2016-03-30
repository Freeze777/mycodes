#ifndef model_h
#define model_h

#include "PlyUtility.h"
#include "PlyModel.h"
#include "BoundingBox.h"

class Model{
GLfloat origin[3] = {0.0, 0.0, 0.0};

public:
PlyModel *plymodel1;
PlyModel *plymodel2;
PlyUtility *ply1;
PlyUtility *ply2;

Model();
~Model();

void readModelsFromFile(char *filename1,char *filename2);
void setShifts(GLfloat *s1,GLfloat *s2);
void drawScene(float dim);
void drawAxis(float dim);
void markPoints(float dim);
void setScaleFactor(float dim); 

};



#endif
