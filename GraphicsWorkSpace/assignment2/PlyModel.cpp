#include "PlyModel.h"

void PlyModel::draw(PlyUtility *ply)
{


    glMatrixMode(GL_MODELVIEW);
    glPushMatrix();

    glScalef(10,10,10);
    glTranslatef(-centroid[0],-centroid[1],-centroid[2]);

    //glColor3f(0.75,0.68,0.204);

    glColor3f(1,1,1);
    int fcount=ply->getFaceCount();
    PlyUtility::Face ** fl=ply->getFaceList();
    PlyUtility::Vertex ** vl=ply->getVertexList();
    for (int var = 0; var < fcount ; var++) {

        if(fl[var]->nverts==3)
        glBegin(GL_TRIANGLES);
        else if(fl[var]->nverts==4)
        glBegin(GL_QUADS);

        glNormal3d(normals[var].x(),normals[var].y(),normals[var].z());

        for (int t = 0; t < fl[var]->nverts; t++)
        {
            int vIndex=fl[var]->verts[t];
            glVertex3f(vl[vIndex]->x,vl[vIndex]->y,vl[vIndex]->z);
        }

        glEnd();
    }



    glPopMatrix();



}


void PlyModel::computeNormal(PlyUtility *ply){


    int fcount=ply->getFaceCount();
    PlyUtility::Face ** fl=ply->getFaceList();
    PlyUtility::Vertex ** vl=ply->getVertexList();

    normals=(Vector *)malloc(sizeof(Vector)*fcount);
    for (int var = 0; var < fcount; ++var) {

        PlyUtility:: Vertex v1=*vl[fl[var]->verts[0]];
        PlyUtility::Vertex v2=*vl[fl[var]->verts[1]];//automatic storage i.e stack..! since new() is not used
        PlyUtility:: Vertex v3=*vl[fl[var]->verts[2]];

        Vector vec21(v2.x-v1.x,v2.y-v1.y,v2.z-v1.z);
        Vector vec31(v3.x-v2.x,v3.y-v2.y,v3.z-v2.z);
        Vector normv=cross(vec31,vec21);
        normv.normalize();
        normals[var]=normv;

    }
}
void PlyModel::computeCentroid(PlyUtility *ply){

centroid[0]=(ply->vx_max+ply->vx_min)/2.0;
centroid[1]=(ply->vy_max+ply->vy_min)/2.0;
centroid[2]=(ply->vz_max+ply->vz_min)/2.0;


}
