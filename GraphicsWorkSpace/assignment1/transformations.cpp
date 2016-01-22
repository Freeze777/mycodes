#include "transformations.h"
void zoom(float scale,Point pivot)
{
    glClear(GL_COLOR_BUFFER_BIT);

    glTranslatef(pivot.x,pivot.y, 0);
    glScalef(scale,scale,1);
    glTranslatef(-pivot.x,-pivot.y, 0);

    glutPostRedisplay();


}
void rotate(float angle,Point pivot)
{
    glClear(GL_COLOR_BUFFER_BIT);

    glTranslatef(pivot.x,pivot.y, 0);
    glRotatef(angle,0.0,0.0,1.0);
    glTranslatef(-pivot.x,-pivot.y, 0);

    glutPostRedisplay();





}
void translate(float offset)
{

    glClear(GL_COLOR_BUFFER_BIT);

    glTranslatef(offset,0,0);

    glutPostRedisplay();



}

