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
Point shift[2];
int cnt=0;
int clicks = 0;

Point bezier[50];
Point lagrange[51];

int SCREEN_WIDTH = 1000;
int SCREEN_HEIGHT = 650;

/* 0-bezier/lagrange*/
int MODE=0;

float dist=0.0;

float binomial_coff(float n,float k) {
    if(k == 0)
        return 1;
    if(k > n/2)
        return binomial_coff(n,n-k);
    return (n * binomial_coff(n-1,k-1)) / k;
}


void myInit() {

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

void drawAxis()
{
    float x1=SCREEN_WIDTH/8.0;
    float y1=SCREEN_HEIGHT/8.0;
    float x2=SCREEN_WIDTH/4.0;
    float y2=SCREEN_HEIGHT/4.0;
    glPushMatrix();
    glLoadIdentity();
    gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);

    glBegin(GL_LINES);
    // draw line for x axis
    glColor3f(1.0, 0.0, 0.0);
    glVertex3f(x1,y1, 0.0);
    glVertex3f(x1,y2, 0.0);
    // draw line for y axis
    glColor3f(0.0, 1.0, 0.0);
    glVertex3f(x1,y1, 0.0);
    glVertex3f(x2,y1, 0.0);

    glEnd();

    glPopMatrix();
    glFlush();
    glColor3f(0.0,0.0,0.0);
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
    glColor3f(1.0,1.0,0.0);
    // Drawing the control lines
    for(int k=0;k<clicks-1;k++)
        drawLine(points[k], points[k+1]);
    glColor3f(0.0,0.0,1.0);
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



}

void displayLagrangeCurve()
{
    glColor3f(1.0,1.0,0.0);
    for(int k=0;k<clicks-1;k++)
        drawLine(points[k], points[k+1]);
    glColor3f(1.0,0.0,1.0);
    float stepSize=(points[clicks-1].x-points[0].x)/50.0;
    Point prev=points[0];
    lagrange[0]=points[0];
    int i=1;
    for(float x=points[0].x;i<=50;x+=stepSize)
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

void myReshape(int w,int h)
{
    SCREEN_WIDTH=w;
    SCREEN_HEIGHT=h;

    glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    glMatrixMode(GL_PROJECTION);// choose projection matrix
    glLoadIdentity();//clear projection matrix
    gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);//use orthographic projection->projection matrix will be updated

}

void zoom(int flag)
{
    glClear(GL_COLOR_BUFFER_BIT);
    glTranslatef(bezier[0].x,bezier[0].y, 0);
    glScalef(pow(2.0,flag),pow(2.0,flag),1);
    glTranslatef(-bezier[0].x,-bezier[0].y, 0);

    glColor3f(0.0,0.0,0.0);
    for(int k=0;k<clicks;k++)
        drawDot(points[k].x,points[k].y);

    glColor3f(1.0,1.0,0.0);
    for(int k=0;k<clicks-1;k++)
        drawLine(points[k], points[k+1]);


    glColor3f(0.0,0.0,1.0);
    for(int k=0;k<50;k++)
        drawLine(bezier[k],bezier[k+1]);


    glColor3f(1.0,0.0,1.0);
    for(int k=0;k<50;k++)
        drawLine(lagrange[k], lagrange[k+1]);


    glFlush();

}
void rotate(int flag)
{


    glClear(GL_COLOR_BUFFER_BIT);


    glTranslatef(bezier[0].x,bezier[0].y, 0);
    glRotatef((flag)*90.0,0.0,0.0,1.0);
    glTranslatef(-bezier[0].x,-bezier[0].y, 0);

    glColor3f(0.0,0.0,0.0);
    for(int k=0;k<clicks;k++)
        drawDot(points[k].x,points[k].y);

    glColor3f(1.0,1.0,0.0);
    for(int k=0;k<clicks-1;k++)
        drawLine(points[k], points[k+1]);

    glColor3f(0.0,0.0,1.0);
    for(int k=0;k<50;k++)
        drawLine(bezier[k],bezier[k+1]);


    glColor3f(1.0,0.0,1.0);
    for(int k=0;k<50;k++)
        drawLine(lagrange[k], lagrange[k+1]);


    glFlush();



}
void translate()
{

    glClear(GL_COLOR_BUFFER_BIT);

    glTranslatef(dist,0,0);

    // Draw your shape



    glColor3f(0.0,0.0,0.0);
    for(int k=0;k<clicks;k++)
        drawDot(points[k].x,points[k].y);

    glColor3f(1.0,1.0,0.0);
    for(int k=0;k<clicks-1;k++)
        drawLine(points[k], points[k+1]);

    glColor3f(0.0,0.0,1.0);
    for(int k=0;k<50;k++)
        drawLine(bezier[k],bezier[k+1]);


    glColor3f(1.0,0.0,1.0);
    for(int k=0;k<50;k++)
        drawLine(lagrange[k], lagrange[k+1]);



    glFlush();


}

void myKeyBoard(unsigned char key,int x, int y){
    cout<<(int)key<<"\n";
    switch ( key )
    {
    case 13: // Enter key


        glClear(GL_COLOR_BUFFER_BIT);

        displayBezierCurve();
        displayLagrangeCurve();
        glColor3f(0.0,0.0,0.0);
        for(int k=0;k<clicks;k++)
            drawDot(points[k].x,points[k].y);


        drawAxis();



        break;


    case 43:
        zoom(1);
        drawAxis();
        break;

    case 45:
        zoom(-1);
        drawAxis();
        break;

    case 114:
        MODE=1;
        glClear(GL_COLOR_BUFFER_BIT);
        glMatrixMode(GL_PROJECTION);// choose projection matrix
        glLoadIdentity();//clear projection matrix
        gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);//use orthographic projection->projection matrix will be updated
        drawAxis();
        for(int k=0;k<clicks;k++)
            drawDot(points[k].x,points[k].y);

        glFlush();
        break;

    case 27: // Escape key
        glutDestroyWindow (glutGetWindow());
        exit (0);
        break;
    }


}

void myDisplay() {

    glClear(GL_COLOR_BUFFER_BIT);
    drawAxis();
    glFlush();
}
void myMouse(int button, int state, int x, int y) {
    // If left button was clicked

    if(button == GLUT_LEFT_BUTTON && state == GLUT_DOWN) {
        // Store where mouse was clicked, Y is backwards.

        if(MODE==1)
        {

            Point newp;
           int index=-1;
            newp.setxy((float)x,(float)(SCREEN_HEIGHT - y));
            float min=-1.0;
            for(int k=0;k<clicks;k++)
            {
                float currDist=sqrt(pow(points[k].x-newp.x,2)+(pow(points[k].y-newp.y,2)));

                if(currDist<=min)
                {
                    min=currDist;
                    index=k;

                }
            }


        }
        points[clicks].setxy((float)x,(float)(SCREEN_HEIGHT - y));
        clicks++;

        drawDot(x, SCREEN_HEIGHT - y);


    }
    if(button == GLUT_RIGHT_BUTTON && state == GLUT_DOWN) {

        glPushMatrix();
        glLoadIdentity();
        glColor3f(0.0,0.0,0.0);
        gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);
        drawDot(x, SCREEN_HEIGHT - y);
        glPopMatrix();

        shift[cnt].setxy((float)x,(float)(SCREEN_HEIGHT - y));
        cnt++;
        if(cnt==2)
        {   drawLine(shift[0],shift[1]);
            dist=sqrt(pow(shift[0].x-shift[1].x,2)+(pow(shift[0].y-shift[1].y,2)));
            translate();
            drawAxis();
            cnt=0;
        }


    }

}
void mySpecialInput(int key, int x, int y)
{
    switch(key)
    {
    /*case GLUT_KEY_UP:

        break;
    case GLUT_KEY_DOWN:

        break;*/
    case GLUT_KEY_LEFT:
        rotate(1);
        drawAxis();
        break;
    case GLUT_KEY_RIGHT:
        rotate(-1);
        drawAxis();
        break;
    }
}
int main(int argc, char *argv[]) {
    glutInit(&argc, argv);
    myInit();
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
    glClearColor(1.0,1.0,1.0,0.0);//white background canvas
    glColor3f(0.0,0.0,0.0);//use black for further draws
    glPointSize(4);
    glLineWidth(1.5);
    glutMainLoop();

    return 0;
}
