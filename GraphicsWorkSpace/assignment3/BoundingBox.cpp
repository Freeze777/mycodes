#include "BoundingBox.h"

void BoundingBox::draw(float dim)
{

    v[0][0] = v[1][0] = v[2][0] = v[3][0] = -dim/2;
    v[4][0] = v[5][0] = v[6][0] = v[7][0] = dim/2;
    v[0][1] = v[1][1] = v[4][1] = v[5][1] = -dim/2;
    v[2][1] = v[3][1] = v[6][1] = v[7][1] = dim/2;
    v[0][2] = v[3][2] = v[4][2] = v[7][2] = dim/2;
    v[1][2] = v[2][2] = v[5][2] = v[6][2] = -dim/2;

    glMatrixMode(GL_MODELVIEW);

    glColor3f(1.0,1.0,1.0);


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


}
