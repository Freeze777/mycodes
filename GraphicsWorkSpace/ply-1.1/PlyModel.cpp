#include "PlyModel.h"

void PlyModel::draw(PlyUtility ply)
{


    glMatrixMode(GL_MODELVIEW);
    glPushMatrix();

    glScalef(10,10,10);
    glTranslatef(centroid[0],centroid[1],centroid[2]);

    glColor3f(0.75,0.68,0.204);

    int fcount=ply.getFaceCount();
    Face ** fl=ply.getFaceList();
    Vertex ** vl=ply.getVertexList();
    for (int var = 0; var < fcount ; var++) {
        glBegin(GL_TRIANGLES);

        for (int t = 0; t < fl[var]->nverts; t++)
        {
            int vIndex=fl[var]->verts[t];
            glVertex3f(vl[vIndex]->x,vl[vIndex]->y,vl[vIndex]->z);
        }

        glEnd();
    }



   glPopMatrix();



}


