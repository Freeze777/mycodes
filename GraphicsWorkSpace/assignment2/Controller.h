#ifndef controller_h
#define controller_h
#include <stdio.h>
#include <iostream>
#include <math.h>
#include <string.h>
#include "PlyUtility.h"
#include "View.h"
#include "PlyModel.h"
#include "BoundingBox.h"
#include "Quaternion.h"
#include "Vector.h"
#include "Arcball.h"
#include "Model.h"

class Controller
{
    Arcball *arcball;
    View *view;
    Model *model;
  

public:

    float rollspeed=1.5;
    float zoomfactor=1.0;
    float th = 0;
    float ph = 0;
    int arcball_on=false;
    float asp = 1;
    
    float SCREEN_WIDTH,SCREEN_HEIGHT;
    double dim=2.0;
    int fov =45;
    



    Controller(View *v,Model *m) {
        arcball=new Arcball(SCREEN_WIDTH,SCREEN_HEIGHT,1.5);
        view=v;
        model=m;
   }

    ~Controller(){
        delete arcball;
    }
    void reshape_callback(int w, int h);
    void keyboard_callback(unsigned char key,int x, int y);
    void mouse_callback(int button, int state, int x, int y);
    void display_callback(void);
    void keyboard_special_callback(int key,int x,int y);
    void mouse_motion_callback(int x, int y) ;

};


#endif
