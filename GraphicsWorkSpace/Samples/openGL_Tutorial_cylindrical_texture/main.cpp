/*
######################################################################
#								     #
# OpenGL Cylinder Texture Sample				     #
#								     #
# Copyright (C) 2012  Lorenzo Belli				     #
#								     #
# This program is free software; you can redistribute it and/or	     #
# modify it under the terms of the GNU General Public License	     #
# as published by the Free Software Foundation; either version 2     #
# of the License, or (at your option) any later version.	     #
#								     #
# This program is distributed in the hope that it will be useful,    #
# but WITHOUT ANY WARRANTY; without even the implied warranty of     #
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the	     #
# GNU General Public License for more details.		 	     #
#								     #
# http://www.gnu.org/copyleft/gpl.html				     #
######################################################################
 */
/************************
* How to apply 2D texture to a cylynder surface with OpenGL.
* SDL is used ONLY for loading the image
* We set up camera to allow rotating view with mouse
*
*************************/


#include <GL/gl.h>
#include <GL/glut.h>
#include <SDL/SDL.h>
#include <SDL/SDL_image.h>
#include <math.h>

void DrawAxis();

/* Screen ID*/
GLuint screen;
/* Drawing surface */
SDL_Surface *s;
/* Storage For One Texture ID */
GLuint texture[1];
/* Screen dimensions */
unsigned int scrH=500, scrW=800;
/* Angles which define view direction */
float viewAlpha=-30, viewBeta=40;
/* Mouse position */
int oldx=0, oldy=0;
/* We still have not used the mouse? */
bool first=true;

/* Number of side faces the cylinder have */
int faces = 360;
/* Cylynder radius*/
double r = 5.0;
/* Cylynder height */
double height = 4.0;

/*Load image.
* Can load .jpg files */
bool LoadTexture(GLuint textbind, char *filename)
{
	SDL_Surface *s = IMG_Load(filename);

	if (!s) return false;

	glBindTexture(GL_TEXTURE_2D, textbind);

	glTexImage2D( GL_TEXTURE_2D,
		0, 3,
		s->w, s->h,
		0, GL_BGR,
		GL_UNSIGNED_BYTE, s->pixels );

	/* Auto-produce mip-mapping */
	gluBuild2DMipmaps(
		GL_TEXTURE_2D,
		GL_RGB,
		s->w, s->h,
		GL_RGB,
		GL_UNSIGNED_BYTE,
		s->pixels
	);
	
	glTexParameteri(
		GL_TEXTURE_2D,
		GL_TEXTURE_MAG_FILTER,
		GL_LINEAR
	);

	glTexParameteri(
		GL_TEXTURE_2D,
		GL_TEXTURE_MIN_FILTER,
		GL_LINEAR_MIPMAP_LINEAR
	);
	return true;
}

/* Draw a cylinder with the height parallel to Y axis
*/
void DrawCylinder ()
{
	glPushMatrix();
		/* Choose neutral color (white)*/
		glColor3d(1,1,1);
		/* Enable 2D Texture*/
		glEnable(GL_TEXTURE_2D);
		/* set current working texture */
		glBindTexture(GL_TEXTURE_2D, texture[0]);
		
		/* Disabling these is not necessary in this example,
		* BUT if you have previously enabled GL_TEXTURE_GEN_
		* for other textures,then you need these lines
		*/
		glDisable(GL_TEXTURE_GEN_S);
		glDisable(GL_TEXTURE_GEN_T);

		/* We draw a quad strip */
		glBegin(GL_QUAD_STRIP);
		double x, y, z;
		y=height;
		for (int i =0; i <= faces; i++) {
			double u = i/ (double) faces;
			x = r*cos(2*M_PI*u);
			z = r*sin(2*M_PI*u);
			/* Bottom vertex*/
			glTexCoord2d(u, 1.0); glVertex3d( x, 0, z );
			/* Top vertex*/
			glTexCoord2d(u, 0.0); glVertex3d( x, y, z );
		}
		glEnd();
	glPopMatrix();
}

void render()
{
 	glLineWidth(3);
	glViewport(0,0,scrW, scrH);
	glClearColor(40.0, 20.0, 10.0, 1.0);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	/* Set up a perspective projection matrix */
	glMatrixMode( GL_PROJECTION );
	glLoadIdentity();
	gluPerspective(60, (double)scrW/scrH, 0.2, 100 );

	/* Set up camera mode */
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	
	/* Camera orientation */
	glRotatef(viewBeta,  1,0,0);
	glRotatef(viewAlpha, 0,1,0);
	/* Camera position
	* notice the signs are inverted: we translate the entire world!*/
	glTranslatef(-4, -4, -1);
	
	DrawAxis();
	DrawCylinder();

	/* Wait for all to be done before continue*/
	glFinish();
	glutSwapBuffers();
}

/* Callback function for resizing */
void main_resizing(int width,  int height)
{
	scrW=width;
	scrH=height;
	glutPostRedisplay();
}

/* Callback function for display */
void main_display()
{
	render();
}

/* Callback function for idle */
void main_idle()
{
	glutPostRedisplay();
}

/* Callback function for mouse motion */
void main_mouse_motion(int x, int y)
{
	if (!first){
		viewAlpha+=(float)(x-oldx)/2;
		viewBeta +=(float)(y-oldy)/2;
	}
	first=false;
	oldx=x;
	oldy=y;
}

/* Callback function for mouse click */
void main_mouse(int button, int state, int x, int y)
{
	if (state==GLUT_DOWN)
		switch(button) {
		case GLUT_LEFT_BUTTON:
			oldx=x;
			oldy=y;
			break;
		case GLUT_RIGHT_BUTTON:
			break;
		}
}

int main(int argc, char* argv[])
{
	glutInitDisplayMode(GLUT_RGB | GLUT_DEPTH | GLUT_DOUBLE);
	glutInitWindowSize(scrW, scrH);
	glutInitWindowPosition(50, 50);
	glutInit(&argc, argv);
	screen = glutCreateWindow("test");

	/* Register event handling callback function*/
	glutReshapeFunc(main_resizing);
	glutDisplayFunc(main_display);
	glutIdleFunc(main_idle);
	glutMotionFunc(main_mouse_motion);
	glutMouseFunc(main_mouse);

	glEnable(GL_DEPTH_TEST);

	/* Generate one Texture ID */
	glGenTextures( 1, &texture[0] );
	if (!LoadTexture(texture[0], (char *)"hawaii-beach.jpg")) {
		exit(-1);
	}

	glutMainLoop();
}

/* We can orientate better with axes */
void DrawAxis()
{
	glPushMatrix();
		//thick lines
		glLineWidth(3);
		glColor3f(0,0,0);
		glRasterPos3f(1, 0,0);
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, 'x');
		glRasterPos3f(0, 1,0);
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, 'y');
		glRasterPos3f(0, 0,1);
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, 'z');

		const float K=0.10f;
		glColor3f(0,0,1); //Blue
		glBegin(GL_LINES);
			glVertex3f( -1,0,0 );
			glVertex3f( +1,0,0 );

			glVertex3f( 0,-1,0 );
			glVertex3f( 0,+1,0 );

			glVertex3f( 0,0,-1 );
			glVertex3f( 0,0,+1 );
		glEnd();

		glBegin(GL_TRIANGLES);
			glVertex3f( 0,+1  ,0 );
			glVertex3f( K,+1-K,0 );
			glVertex3f(-K,+1-K,0 );

			glVertex3f( +1,   0, 0 );
			glVertex3f( +1-K,+K, 0 );
			glVertex3f( +1-K,-K, 0 );

			glVertex3f( 0, 0,+1 );
			glVertex3f( 0,+K,+1-K );
			glVertex3f( 0,-K,+1-K );
		glEnd();
	glPopMatrix();
}