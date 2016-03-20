#ifndef view_h
#define view_h

#include <GL/glu.h>
#include <GL/gl.h>
#include <GL/glut.h>


class View{

private:
  float screenWidth;
  float screenHeight;



public:
    
    View(float width,float height);
    void createWindow(char *windowName);
    void initialize(double dim,int fov);
    void set_width_height(float width,float height);

};


#endif
