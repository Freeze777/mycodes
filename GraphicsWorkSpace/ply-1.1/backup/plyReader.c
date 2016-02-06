#include <GL/glu.h>
#include <GL/gl.h>
#include <GL/glut.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <string.h>
#include "ply.h"
void read_test(void);
void drawBunny(void);
void drawBoundingBox(void);
void myReshape(int w, int h);
void myDisplay(void);
void windowKey(unsigned char key, int x, int y);
typedef struct Vertex {
    float x,y,z;             /* the usual 3-space position of a vertex */
} Vertex;

typedef struct Face {
    unsigned char intensity; /* this user attaches intensity to faces */
    unsigned char nverts;    /* number of vertex indices in list */
    int *verts;              /* vertex index list */
} Face;

char *elem_names[] = { /* list of the kinds of elements in the user's object */
                       "vertex", "face"
                     };

PlyProperty vert_props[] = { /* list of property information for a vertex */
                             {"x", PLY_FLOAT, PLY_FLOAT, offsetof(Vertex,x), 0, 0, 0, 0},
                             {"y", PLY_FLOAT, PLY_FLOAT, offsetof(Vertex,y), 0, 0, 0, 0},
                             {"z", PLY_FLOAT, PLY_FLOAT, offsetof(Vertex,z), 0, 0, 0, 0},
                           };

PlyProperty face_props[] = { /* list of property information for a vertex */
                             {"intensity", PLY_UCHAR, PLY_UCHAR, offsetof(Face,intensity), 0, 0, 0, 0},
                             {"vertex_indices", PLY_INT, PLY_INT, offsetof(Face,verts),
                              1, PLY_UCHAR, PLY_UCHAR, offsetof(Face,nverts)},
                           };

float SCREEN_WIDTH = 1000,SCREEN_HEIGHT = 650;
GLfloat origin[3] = {1.0, 0.0, 0.0};
int vertexCount=0;
int faceCount=0;
PlyFile *ply;
int nelems;
char **elist;
int file_type;
float version;
int nprops;
int num_elems;
PlyProperty **plist;
Vertex **vlist;
Face **flist;
char *elem_name;
int num_comments;
char **comments;
int num_obj_info;
char **obj_info;

GLfloat v[8][3];


void myDisplay() {



    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
    /*  Enable Z-buffering in OpenGL */
    glEnable(GL_DEPTH_TEST);


    drawBoundingBox();
    drawBunny();
    glFlush();
    glutSwapBuffers();
}


void myInit() {
    glViewport(0,0, SCREEN_WIDTH,SCREEN_HEIGHT);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective( /* field of view in degree */ 60.0,/* aspect ratio */ SCREEN_WIDTH/SCREEN_HEIGHT,/* Z near */ -0.5, /* Z far */ 1.0);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    gluLookAt(0,0,1.5,0,0,0,0,1,0);

}
void drawBoundingBox(void){
    glMatrixMode(GL_MODELVIEW);
    glPushMatrix();
    glLoadIdentity();
    glColor3f(1.0,1.0,1.0);
    gluLookAt(0.0, 0.0, 5.0,  /* eye is at (0,0,5) */
              0.0, 0.0, 0.0,      /* center is at (0,0,0) */
              0.0, 1.0, 0.);
    glTranslatef(0.0, 0.0, -1.0);
    glRotatef(60, 1.0, 0.0, 0.0);
    glRotatef(-20, 0.0, 0.0, 1.0);

    for (int i = 0; i < 4 ; i++) {
        glBegin(GL_LINES);

        glVertex3f(v[i][0],v[i][1],v[i][2]);
        glVertex3f(v[(i+1)%4][0],v[(i+1)%4][1],v[(i+1)%4][2]);

        glVertex3f(v[i][0],v[i][1],v[i][2]);
        glVertex3f(v[i+4][0],v[i+4][1],v[i+4][2]);

        glVertex3f(v[i+4][0],v[i+4][1],v[i+4][2]);
        if((i+5)%8!=0)
            glVertex3f(v[(i+5)%8][0],v[(i+5)%8][1],v[(i+5)%8][2]);
        else
            glVertex3f(v[4][0],v[4][1],v[4][2]);

        glEnd();
    }
    glPopMatrix();


}
void drawBunny(void)
{
    glColor3f(0.0,1.0,1.0);
    for (int var = 0; var < faceCount; var++) {
        glBegin(GL_TRIANGLES);

        for (int t = 0; t < flist[var]->nverts; t++)
        {
            int vIndex=flist[var]->verts[t];
            glVertex3f(vlist[vIndex]->x,vlist[vIndex]->y,vlist[vIndex]->z);
        }

        glEnd();
    }
    glRasterPos3fv(origin);
    glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,'B');

    glFlush();
}



int main(int argc, char *argv[]) {
    v[0][0] = v[1][0] = v[2][0] = v[3][0] = -1;
    v[4][0] = v[5][0] = v[6][0] = v[7][0] = 1;
    v[0][1] = v[1][1] = v[4][1] = v[5][1] = -1;
    v[2][1] = v[3][1] = v[6][1] = v[7][1] = 1;
    v[0][2] = v[3][2] = v[4][2] = v[7][2] = 1;
    v[1][2] = v[2][2] = v[5][2] = v[6][2] = -1;

    read_test();
    glutInit(&argc, argv);
    myInit();
    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);//use single buffer and RGB color schemes
    glutInitWindowSize(SCREEN_WIDTH,SCREEN_HEIGHT);
    glutInitWindowPosition(0,0);
    glutCreateWindow("Curves");
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    glColor3f(0.0,1.0,1.0);
    //register callbacks
    glutDisplayFunc(myDisplay);
    glutReshapeFunc(myReshape);
    glutKeyboardFunc(windowKey);

    glutMainLoop();

    return 0;
}

void read_test(void)
{
    int i,j;

    /* open a PLY file for reading */
    ply = ply_open_for_reading("bunny", &nelems, &elist, &file_type, &version);


    /* go through each kind of element that we learned is in the file */
    /* and read them */

    for (i = 0; i < nelems; i++) {

        /* get the description of the first element */
        elem_name = elist[i];
        plist = ply_get_element_description (ply, elem_name, &num_elems, &nprops);

        /* if we're on vertex elements, read them in */
        if (equal_strings ("vertex", elem_name)) {
            vertexCount=num_elems;
            /* create a vertex list to hold all the vertices */
            vlist = (Vertex **) malloc (sizeof (Vertex *) * num_elems);

            /* set up for getting vertex elements */

            ply_get_property (ply, elem_name, &vert_props[0]);
            ply_get_property (ply, elem_name, &vert_props[1]);
            ply_get_property (ply, elem_name, &vert_props[2]);

            /* grab all the vertex elements */
            for (j = 0; j < num_elems; j++) {

                /* grab and element from the file */
                vlist[j] = (Vertex *) malloc (sizeof (Vertex));
                ply_get_element (ply, (void *) vlist[j]);


            }
        }

        /* if we're on face elements, read them in */
        if (equal_strings ("face", elem_name)) {
            faceCount=num_elems;
            /* create a list to hold all the face elements */
            flist = (Face **) malloc (sizeof (Face *) * num_elems);

            /* set up for getting face elements */

            ply_get_property (ply, elem_name, &face_props[0]);
            ply_get_property (ply, elem_name, &face_props[1]);

            /* grab all the face elements */
            for (j = 0; j < num_elems; j++) {

                /* grab and element from the file */
                flist[j] = (Face *) malloc (sizeof (Face));
                ply_get_element (ply, (void *) flist[j]);

            }
        }

    }

    /* grab and print out the comments in the file */
    comments = ply_get_comments (ply, &num_comments);


    /* grab and print out the object information */
    obj_info = ply_get_obj_info (ply, &num_obj_info);

    /* close the PLY file */
    ply_close (ply);
}

void windowKey(unsigned char key,int x,int y)
{
    /*  Exit on ESC */
    if (key == 27) exit(0);
}

void myReshape(int w,int h)
{
    SCREEN_WIDTH=w;
    SCREEN_HEIGHT=h;
    glViewport(0,0, SCREEN_WIDTH,SCREEN_HEIGHT);
    myInit();


}
