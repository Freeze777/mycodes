#include <iostream>
#include <stdlib.h>
#include <GL/glu.h>
#include <GL/gl.h>
#include <GL/glut.h>
#include <math.h>

using namespace std;

//Point class for taking the points
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

};

Point points[20];
int clicks = 0;

Point bezier[50];
Point lagrange[51];

int SCREEN_WIDTH = 1000;
int SCREEN_HEIGHT = 650;

/* 0-bezier 1-lagrange 2-transalation */
int MODE=0;


float binomial_coff(float n,float k) {
    if(k == 0)
        return 1;
    if(k > n/2)
        return binomial_coff(n,n-k);
    return (n * binomial_coff(n-1,k-1)) / k;
}



void myInit() {
    glClearColor(1.0,1.0,1.0,0.0);//white background canvas
    glColor3f(0.0,0.0,0.0);//use black for further draws
    glPointSize(4);
    glLineWidth(3);
    glMatrixMode(GL_PROJECTION);// choose projection matrix
    glLoadIdentity();//clear projection matrix
    gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);//use orthographic projection->projection matrix will be updated

}

void drawDot(int x, int y) {
    glBegin(GL_POINTS);
    glVertex2i(x,y);
    glEnd();
    glFlush();
}

void drawLine(Point p1, Point p2) {
    glBegin(GL_LINES);
    glVertex2f(p1.x, p1.y);
    glVertex2f(p2.x, p2.y);
    glEnd();
    glFlush();

}




//Calculate the bezier point [generalized]


Point getBezierCurvePoints(Point PT[], double t) {
    Point P;
    P.x = 0; P.y = 0;
    for (int i = 0; i<clicks; i++)
    {
        P.x = P.x + binomial_coff((float)(clicks - 1), (float)i) * pow(t, (double)i) * pow((1 - t), (clicks - 1 - i)) * PT[i].x;
        P.y = P.y + binomial_coff((float)(clicks - 1), (float)i) * pow(t, (double)i) * pow((1 - t), (clicks - 1 - i)) * PT[i].y;
    }

    return P;
}
void displayBezierCurve()
{
    glColor3f(0.2,1.0,0.0);
    // Drawing the control lines
    for(int k=0;k<clicks-1;k++)
        drawLine(points[k], points[k+1]);
    glColor3f(0.0,0.0,0.0);
    Point prev = points[0];
    /* Draw each segment of the curve.Make t increment in smaller amounts for a more detailed curve.*/
    int i=1;
    bezier[0]=points[0];
    for(double t = 0.0;t <= 1.0; t += 0.02)
    {
        Point p2 = getBezierCurvePoints(points,t);
        bezier[i++]=p2;
        drawLine(prev, p2);
        prev = p2;
    }
    glColor3f(0.0,0.0,0.0);


}

void displayLagrangeCurve()
{
    glColor3f(0.2,1.0,0.1);
    for(int k=0;k<clicks-1;k++)
        drawLine(points[k], points[k+1]);
    glColor3f(0.0,0.0,0.0);
    float stepSize=(points[clicks-1].x-points[0].x)/50.0;
    Point prev=points[0];
    lagrange[0]=points[0];
    int i=1;
    for(float x=points[0].x;x<=points[clicks-1].x;x+=stepSize)
    {

        float y=0;
        for(int j=0;j<clicks;j++)
        {
            float l=1;
            for(int m=0;m<clicks;m++)
            {
                if(m!=j)
                {
                    l*=(x-points[m].x)/(points[j].x -points[m].x);
                }

            }
            y+=l*points[j].y;
        }
        Point p;
        p.x=x;
        p.y=y;
        lagrange[i++]=p;
        drawLine(prev,p);
        prev=p;
    }

}


void myKeyBoard(unsigned char key,int x, int y){
    cout<<(int)key<<"\n";
    switch ( key )
    {
    case 13: // Enter key

        if(MODE==0)
            displayBezierCurve();
        else if(MODE==1)
            displayLagrangeCurve();


        clicks=0;
        break;

    case 98: // B key
        MODE=0;
        clicks=0;
        break;
    case 108: // L key
        MODE=1;
        clicks=0;
        break;
    case 116: // T key
        MODE=2;
        clicks=0;
        break;


    case 27: // Escape key
        glutDestroyWindow (glutGetWindow());
        exit (0);
        break;
    }


}

void myDisplay() {
    cout<<"Display called\n";
   glClear(GL_COLOR_BUFFER_BIT);
    glFlush();
}

void myReshape(int w,int h)
{
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();

    SCREEN_WIDTH=w;
    SCREEN_HEIGHT=h;

    glViewport(0, 0, w, h);
    gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);


}
void rotateLeft()
{ cout<<"heere";
   // glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
    glPushMatrix();
     glTranslatef(-100,-100, 0);
     glPushMatrix();
    glRotatef(90.0, 0.0, 0.0, 1.0);
    // Draw your shape
    for(int k=0;k<49;k++)
    {  drawLine(bezier[k],bezier[k+1]);
        drawLine(lagrange[k], lagrange[k+1]);
    }
    glPopMatrix();

  //  glFlush();



}
void rotateRight()
{cout<<"heere1";
 //   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  glMatrixMode(GL_MODELVIEW);
   glLoadIdentity();
    glPushMatrix();
    glTranslatef(-100,-100, 0);
    glPushMatrix();
    glRotatef(270.0, 0.0, 0.0, 1.0);
    // Draw your shape
    for(int k=0;k<49;k++)
    {  drawLine(bezier[k],bezier[k+1]);
        drawLine(lagrange[k], lagrange[k+1]);
    }
    glPopMatrix();

    //glFlush();



}
void translate(float distance)
{
    // while(clicks!=2);//execution get stuck here// the thread handling UI inputs is here at the while
    glMatrixMode(GL_MODELVIEW);
     glLoadIdentity();
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glPushMatrix();
    glTranslatef(distance,distance, 0);
    // Draw your shape
    for(int k=0;k<49;k++)
    {  drawLine(bezier[k],bezier[k+1]);
        drawLine(lagrange[k], lagrange[k+1]);
    }
    glPopMatrix();

    glFlush();

    clicks=0;
}

void myMouse(int button, int state, int x, int y) {
    // If left button was clicked
    if(button == GLUT_LEFT_BUTTON && state == GLUT_DOWN) {
        // Store where mouse was clicked, Y is backwards.
        points[clicks].setxy((float)x,(float)(SCREEN_HEIGHT - y));
        clicks++;

        // Draw the red  dot.
        drawDot(x, SCREEN_HEIGHT - y);

    }
    if(MODE==2 && clicks==2)
    {    drawLine(points[0],points[1]);
        float distance=sqrt(pow(points[0].x-points[1].x,2)+(pow(points[0].y-points[1].y,2)));
        translate(distance);

    }
}
void mySpecialInput(int key, int x, int y)
{   cout<<key<<"\n";
    switch(key)
    {
    /*case GLUT_KEY_UP:

        break;
    case GLUT_KEY_DOWN:

        break;*/
    case GLUT_KEY_LEFT:
        rotateLeft();
        break;
    case GLUT_KEY_RIGHT:
        rotateRight();
        break;
    }
}
int main(int argc, char *argv[]) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);//use single buffer and RGB color schemes
    glutInitWindowSize(SCREEN_WIDTH,SCREEN_HEIGHT);
     glutInitWindowPosition(0,0);
    glutCreateWindow("Curves");
//register callbacks
    glutMouseFunc(myMouse);
    glutKeyboardFunc(myKeyBoard);
    glutDisplayFunc(myDisplay);
    glutReshapeFunc(myReshape);
    glutSpecialFunc(mySpecialInput);
    myInit();
    glutMainLoop();

    return 0;
}
