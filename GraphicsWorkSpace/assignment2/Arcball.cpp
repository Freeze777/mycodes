#include "Arcball.h"

Arcball::Arcball(const Arcball& obj)
{

this->windowWidth=obj.windowWidth;
this->windowHeight=obj.windowHeight;
this->rollSpeed=obj.rollSpeed;

}

Arcball::Arcball( float SCREEN_WIDTH, float SCREEN_HEIGHT, GLfloat roll_speed) {
    this->windowWidth  = SCREEN_WIDTH;
    this->windowHeight = SCREEN_HEIGHT;
    
    this->rollSpeed  = roll_speed;
    
  
}

 void Arcball::set_current_xy(int x,int y){
cur_mx=x;
cur_my=y;
}

 void Arcball::set_last_xy(int x,int y){
last_mx=x;
last_my=y;
}

void Arcball::set_width_height(float SCREEN_WIDTH,float SCREEN_HEIGHT){
 this->windowWidth  = SCREEN_WIDTH;
    this->windowHeight = SCREEN_HEIGHT;

}

void Arcball::rotateModelvthMouse(float dim){
    double * rotMatrix;
    if (cur_mx != last_mx || cur_my != last_my) {

        Vector va = get_arcball_vector(last_mx, last_my,dim);
        Vector vb = get_arcball_vector( cur_mx,  cur_my,dim);
        double angle = (180.0 / Quaternion::PI )*acos(std::min(1.0, dot(va, vb)));//in degrees
        angle=rollSpeed*angle;
        Vector axis_in_camera_coord = cross(vb, va);


        double axis_in_object_coord[3]={axis_in_camera_coord.x(),axis_in_camera_coord.y(),axis_in_camera_coord.z()};

        Quaternion quat(angle,axis_in_object_coord);

        rotMatrix=quat.rotationMatrix();
        glMultMatrixd(rotMatrix);
        free(rotMatrix);
        quat.~Quaternion();
        last_mx = cur_mx;
        last_my = cur_my;

    }
}

Vector Arcball::get_arcball_vector(int x, int y,float dim) {

    Vector P = Vector((dim/2)*x/windowWidth*2 - dim/2,
                      (dim/2)*y/windowHeight*2 - dim/2,
                      0);
    P.y(- P.y());

    float OP_squared = P.x() * P.x() + P.y() * P.y();

    if (OP_squared <= (dim/2)*(dim/2))
        P.z(sqrt((dim/2)*(dim/2)- OP_squared));  // Pythagore
    else
        P.normalize();
    return P;
}
