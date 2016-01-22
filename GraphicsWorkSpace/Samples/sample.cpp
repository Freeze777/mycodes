#include <cstdlib>
#include <GL/glut.h>
#include <iostream>

#include "Vec2.h"

Vec2 pos(0.0, 0.0);
Vec2 go(1.0, 1.0);

float mouseX;
float mouseY;
float angle = 0.0f;

void changeSize(int w, int h) {

    // Prevent a divide by zero, when window is too short
    // (you cant make a window of zero width).
    if (h == 0)
        h = 1;

    float ratio =  w * 1.0 / h;

    // Use the Projection Matrix
    glMatrixMode(GL_PROJECTION);

    // Reset Matrix
    glLoadIdentity();

    // Set the viewport to be the entire window
    glViewport(0, 0, w, h);

    // Set the correct perspective.
    gluPerspective(45.0f, ratio, 0.1f, 100.0f);

    // Get Back to the Modelview
    glMatrixMode(GL_MODELVIEW);
}

void renderScene(void) {

    // Clear Color and Depth Buffers
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // Reset transformations
    glLoadIdentity();
    // Set the camera
    gluLookAt(  0.0f, 0.0f, 10.0f,
                0.0f, 0.0f,  0.0f,
                0.0f, 1.0f,  0.0f);



    glPushMatrix();
    glRotatef(angle, 0.0f, 0.0f, 1.0f);
    glTranslatef(0.0, 0.0, 0.0);
    glBegin(GL_LINE_LOOP);
        glVertex3f( 0.0f, 2.0f, 0.0f);
        glVertex3f( -1.0f, -1.0f, 0.0f);
        glVertex3f( 1.0f, -1.0f, 0.0f);
    glEnd();
    glPopMatrix();

    //angle+=0.01f;

    glutSwapBuffers();
}

void mouseMove(int x, int y)
{
    mouseX = -1.0 + 2.0 * x / 320 ;
    mouseY = 1.0 - 2.0 * y / 320 ;

    angle = 90 + atan2(pos.y-mouseY, pos.x-mouseX) * 180 / 3.1415926;

    //std::cout << mouseX << ", " << mouseY << std::endl;
    //std::cout << x << ", " << y << std::endl;
    std::cout << angle << std::endl;
}

int main(int argc, char **argv) {

    // init GLUT and create window
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DEPTH | GLUT_DOUBLE | GLUT_RGBA);
    glutInitWindowPosition(100,100);
    glutInitWindowSize(320,320);
    glutCreateWindow("Lighthouse3D- GLUT Tutorial");

    // register callbacks
    glutDisplayFunc(renderScene);
    glutReshapeFunc(changeSize);
    glutIdleFunc(renderScene);

    glutMotionFunc(mouseMove);

    // enter GLUT event processing cycle
    glutMainLoop();

    return 1;
}
