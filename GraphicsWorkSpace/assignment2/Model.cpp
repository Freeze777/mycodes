#include "Model.h"

Model::Model(){

ply=new PlyUtility();
plymodel=new PlyModel(ply);

}

Model::~Model(){


delete ply;

}

void Model::readModelFromFile(char *filename){

        ply->readPlyFile(filename);
	plymodel->computeNormal();
	plymodel->computeCentroid();


}

void Model::setScaleFactor(float dim){
plymodel->computeScaleFactor(dim);

}
void Model::drawScene(float dim){
	drawAxis(dim);
	markPoints(dim);
	box.draw(dim);
	plymodel->draw();
}

void Model::drawAxis(float dim){

    glMatrixMode(GL_MODELVIEW);
    glBegin(GL_LINES);
    // draw line for x axis
    glColor3f(1, 0.0, 0.0);
    glVertex3f(dim/2.0,0.0, 0.0);
    glVertex3f(-dim/2.0,0.0, 0.0);

    // draw line for y axis
    glColor3f(0.0,1, 0.0);
    glVertex3f(0.0,dim/2.0, 0.0);
    glVertex3f(0.0,-dim/2.0, 0.0);

    // draw line for z axis
    glColor3f(0.0,0.0,1);
    glVertex3f(0.0,0.0, dim/2.0);
    glVertex3f(0.0,0.0, -dim/2.0);

    glEnd();

    glColor3f(1.0,1.0,1.0);
}



void Model::markPoints(float dim){

        glColor3f(1.0,0.0,0.0);

        glRasterPos3fv(origin);
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,'O');

        glRasterPos3f(dim/2.0,0.0,0.0);
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,'X');
        glRasterPos3f(0.0,dim/2.0,0.0);
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,'Y');
        glRasterPos3f(0.0,0.0,dim/2.0);
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,'Z');

        glColor3f(1.0,1.0,1.0);
}



