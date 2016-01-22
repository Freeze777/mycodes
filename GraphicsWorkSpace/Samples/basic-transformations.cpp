#include <iostream>
#include <stdlib.h>
#include <GL/glu.h>
#include <GL/gl.h>
#include <GL/glut.h>
#include <math.h>
class Point {
public:
    float x, y;
    void setxy(float x2, float y2)
    {
        x = x2; y = y2;
    }
    //operator overloading for '=' sign
    const Point & operator=(const Point &rPoint)
    {
        x = rPoint.x;
        y = rPoint.y;
        return *this;
    }
    Point operator+(const Point &b)
    {
        Point sum;
        sum.x = this->x+ b.x;
        sum.y = this->y+ b.y;

        return sum;
    }

};
void specialInput(int, int , int );
void render(void);
void init(void);
void reshape(int ,int );
void keyboard(unsigned char , int , int );
void mouse(int , int , int , int );
void drawTriangle();
void zoom(int);
using namespace std;
int SCREEN_WIDTH=640;
int SCREEN_HEIGHT=480;
Point points[100];

int count=0;


int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DEPTH | GLUT_RGBA);
    glutInitWindowPosition(100, 100);
    glutInitWindowSize(640, 480);
    glutCreateWindow("Simple GLUT Application");

    glutReshapeFunc(reshape);
    glutDisplayFunc(render);
    glutKeyboardFunc(keyboard);
    glutMouseFunc(mouse);
    glutSpecialFunc(specialInput);

    init();
    glutMainLoop();
}

void keyboard(unsigned char key, int x, int y) {

    switch ( key )
    {
    case 13: // Enter key
        drawTriangle();
        break;
    case 43:
        zoom(+1);
        break;
    case 45:
        zoom(-1);
        break;
    case 27: // Escape key
        glutDestroyWindow (glutGetWindow());
        exit (0);
        break;
    }
}

void mouse(int button, int state, int x, int y) {
    //cd D*/Gr*/W*

    if (button == GLUT_LEFT_BUTTON && state==GLUT_DOWN) {
        //  cout << x <<","<< y <<"\n";
        glBegin(GL_POINTS);
        glColor3f(1.0, 1.0, 1.0);
        glVertex3f(x,y,1.0);//since projection matrix is defined to handele inverted y cordinates
        glEnd();
        glFlush();

        points[count].setxy(x,y);
        count++;

        if(count==100)
            count=0;

        if(count%3==0)
        {
            glBegin(GL_TRIANGLES);
            for(int i=0;i<count;i++)
                glVertex3f(points[i].x,points[i].y,0.0);
            glEnd();
            glFlush();

        }

    }
    if (button == GLUT_RIGHT_BUTTON) {
        exit(0);
    }

}
void drawTriangle(){

    cout<<"called";


    glPushMatrix(); //pushed because the opengl system accepts only pixel cordinates due to glOrtho()
    glLoadIdentity();   // now opengl cordinate systems is restored.. accepts values such that middle of the screen ins origin
    glBegin(GL_TRIANGLES);
    glColor3f(1, 0, 0);
    glVertex3f(0,1,0.0);
    glColor3f(0, 1, 0);
    glVertex3f(1,-1,0.0);
    glColor3f(0, 0, 1);
    glVertex3f(-1,-1,0.0);
    glEnd();

    glBegin(GL_POINTS);
    glColor3f(1.0, 1.0, 1.0);
    glVertex3f(0,0,0.0);
    glEnd();
    glPopMatrix();
    glFlush();// very important otherwise it wont be rendered
}

void zoom(int flag)
{   Point centroid;
    centroid.setxy(0.0,0.0);
    for(int i=0;i<3;i++)
        centroid=centroid+(points[i]);

    centroid.x=centroid.x/3.0;
    centroid.y=centroid.y/3.0;

    glClear(GL_COLOR_BUFFER_BIT);
    glTranslatef(centroid.x,centroid.y, 0);
    glScalef(pow(2,flag),pow(2,flag),1);
    glTranslatef(-centroid.x,-centroid.y, 0);
    glBegin(GL_TRIANGLES);
    for(int i=0;i<3;i++)
        glVertex3f(points[i].x,points[i].y,0.0);

    glEnd();


    glFlush();


}
void translate(int flag)
{

    glClear(GL_COLOR_BUFFER_BIT);

    glTranslatef(flag*10,flag*10,0);

    glBegin(GL_TRIANGLES);
    for(int i=0;i<3;i++)
        glVertex3f(points[i].x,points[i].y,0.0);

    glEnd();

    glFlush();

}

void rotate(int flag)
{
    glClear(GL_COLOR_BUFFER_BIT);


    glTranslatef(points[0].x,points[0].y, 0);
    glRotatef(flag*90.0,0.0f,0.0f,1.0f);
    glTranslatef(-points[0].x,-points[0].y, 0);

    glBegin(GL_TRIANGLES);
    for(int i=0;i<3;i++)
        glVertex3f(points[i].x,points[i].y,0.0);
    glEnd();


    glFlush();


}
void render(void) {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    glFlush();
}
void init()
{
    glViewport( 0,0,SCREEN_WIDTH,SCREEN_HEIGHT );
    glMatrixMode(GL_PROJECTION);// choose projection matrix
    glLoadIdentity();//clear projection matrix
    gluOrtho2D(0.0,SCREEN_WIDTH,SCREEN_HEIGHT,0);//use orthographic projection->projection matrix will be updated//note that y values are flipped ..to deal with the inverted screen cordinates
    glPointSize(4);
}
void reshape(int w,int h)
{
    SCREEN_WIDTH=w;
    SCREEN_HEIGHT=h;

    glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0,SCREEN_WIDTH,SCREEN_HEIGHT,0.0);
}
void specialInput(int key, int x, int y)
{
    switch(key)
    {
    case GLUT_KEY_UP:
        translate(-1);
        break;
    case GLUT_KEY_DOWN:
        translate(1);
        break;
    case GLUT_KEY_LEFT:
        rotate(-1);
        break;
    case GLUT_KEY_RIGHT:
        rotate(1);
        break;
    }
}

