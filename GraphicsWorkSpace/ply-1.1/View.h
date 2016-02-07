#ifndef view_h
#define view_h

#include <GL/glu.h>
#include <GL/gl.h>
#include <GL/glut.h>

class View{

private:
    GLfloat origin[3] = {0.0, 0.0, 0.0};

public:

    void drawAxis(float dim);
    void originTest(void);
    View(){

    }

};


#endif
