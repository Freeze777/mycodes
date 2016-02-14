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

class Controller
{
    Arcball *arcball;
    View view;
    PlyUtility *ply;
    PlyModel *plymodel;
    BoundingBox box;

public:

    float rollspeed=1.5;
    float zoomfactor=1.0;
    //int last_mx = 0, last_my = 0, cur_mx = 0, cur_my = 0;
    float Eye[3]={0,0,5.0};
    float th = 0;
    float ph = 0;
    int arcball_on=false;
    float asp = 1;
    
    float SCREEN_WIDTH=1000,SCREEN_HEIGHT=650;
    double dim=2.0;
    int fov =45;
    



    Controller(PlyUtility *plyutils,PlyModel *plymdl) {
        arcball=new Arcball(SCREEN_WIDTH,SCREEN_HEIGHT,1.5);
	ply=plyutils;
	plymodel=plymdl;
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
