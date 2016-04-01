#include "PlyModel.h"

void PlyModel::draw()
{
 //readTexture2Buffer("textures/floor.bmp",64,64);
  //glEnable(GL_LIGHTING);
  // glEnable( GL_TEXTURE_2D );
  GLuint txture;
  glGenTextures(1,&txture);
  glBindTexture(GL_TEXTURE_2D, txture);
  glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR); //scale linearly when image bigger than texture
  glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR); //scale linearly when image smalled than texture
 // texture->print();
  glTexImage2D(GL_TEXTURE_2D, 0, 3, texture->sizeX, texture->sizeY, 0,
  GL_RGB, GL_UNSIGNED_BYTE, texture->data);
  glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);

  glMatrixMode(GL_MODELVIEW);
  glPushMatrix();

  glScalef(scale_factor,scale_factor,scale_factor);
    //glTranslatef(-centroid[0]-shift[0],-centroid[1]-shift[1],-centroid[2]-shift[2]);
  glTranslatef(-centroid[0]+shift[0],-centroid[1]+shift[1],-centroid[2]+shift[2]);

    //glColor3f(0.75,0.68,0.204);

    glColor3f(1,1,1);
    int fcount=ply->getFaceCount();
    PlyUtility::Face ** fl=ply->getFaceList();
    PlyUtility::Vertex ** vl=ply->getVertexList();
    //double cyl_coords[]={0.0,0.0,0.0};
    double uvCoords[]={0.0,0.0};

    for (int var = 0; var < fcount ; var++) {

        if(fl[var]->nverts==3)
        glBegin(GL_TRIANGLES);
        else if(fl[var]->nverts==4)
        glBegin(GL_QUADS);

      // glNormal3d(normals[var].x(),normals[var].y(),normals[var].z());
        float sc=1.0f;
        for (int t = 0; t < fl[var]->nverts; t++)
        {
            int vIndex=fl[var]->verts[t];
	          glNormal3d(normals_vertex[vIndex].x(),normals_vertex[vIndex].y(),normals_vertex[vIndex].z());
              getUVCoords(vl[vIndex]->x,vl[vIndex]->y,vl[vIndex]->z,uvCoords,0,vIndex);
              glTexCoord2f(uvCoords[0],uvCoords[1]);
            glVertex3f(vl[vIndex]->x,vl[vIndex]->y,vl[vIndex]->z);
        }

        glEnd();
    }



    glPopMatrix();



}
void PlyModel::convert2Cylindrical( double x,double y,double z,double *res)

   {

         double rho=sqrt(x*x+y*y);
         double h=z;
         double phi=(rho==0)?0:asin(y/rho);
                   
           

         res[0]=rho;
         res[1]=phi;
         res[2]=h;
         //  res[2]=h;
   }
   void PlyModel::convert2Circular( double x,double y,double z,double *res)

   {

         double r=sqrt(x*x+y*y+z*z);
         double theta=acos(z/r);
         double phi=atan2(y,x);
        
         res[0]=r;
         res[1]=theta;
         res[2]=phi;
         //  res[2]=h;
   }
   void PlyModel::getUVCoords( double x,double y,double z,double *uv,int mode,int vIndex)

   {
        if(mode==0){ //cylindrical
            double res[]={0,0,0};
            convert2Cylindrical(x,y,z,res);
            uv[0]=0.5-(res[1]/M_PI);
            uv[1]=res[2]/ply->vz_max;
          }else if(mode==1){ //sphereical
            Vector norml(x-centroid[0],y-centroid[1],z-centroid[2]);

            norml.normalize();
            uv[1]=0.5+(atan2(norml.z(),norml.x())/(2*M_PI));
              //uv[1]=0.5+(atan2(norml.z(),norml.x())/(2*M_PI));
             //uv[0]=0.5+(atan(norml.z()/norml.x())/(2*M_PI));
           uv[0]=0.5-(asin(norml.y())/M_PI);
               //uv[0]=0.5+(atan2(norml.x(),norml.z())/(2*M_PI));
           //uv[1]=0.5*norml.y()+0.5;
             //  uv[1]=norml.y();
          }
         
   }

void PlyModel::computeNormal(){


    int fcount=ply->getFaceCount();
    int vcount=ply->getVertexCount();
    PlyUtility::Face ** fl=ply->getFaceList();
    PlyUtility::Vertex ** vl=ply->getVertexList();

  //  normals_face=(Vector *)malloc(sizeof(Vector)*fcount);
    normals_vertex=(Vector *)calloc(vcount,sizeof(Vector));

    for (int var = 0; var < fcount; ++var) {
        int v1_index=fl[var]->verts[0];
        int v2_index=fl[var]->verts[1];
        int v3_index=fl[var]->verts[2];
        int v4_index;
        PlyUtility:: Vertex v1=*vl[fl[var]->verts[0]];
        PlyUtility::Vertex v2=*vl[fl[var]->verts[1]];//automatic storage i.e stack..! since new() is not used
        PlyUtility:: Vertex v3=*vl[fl[var]->verts[2]];
        PlyUtility:: Vertex v4;
        if(fl[var]->nverts==4)
        {
          v4=*vl[fl[var]->verts[3]];
          v4_index=fl[var]->verts[3];
        }
        Vector vec21(v2.x-v1.x,v2.y-v1.y,v2.z-v1.z);
        Vector normv;
        if(fl[var]->nverts==3){
        Vector vec32(v3.x-v2.x,v3.y-v2.y,v3.z-v2.z);
       // normv=cross(vec32,vec21);
         normv=cross(vec21,vec32);
         normals_vertex[v1_index]+=normv;
         normals_vertex[v2_index]+=normv;
         normals_vertex[v3_index]+=normv;
        }else if(fl[var]->nverts==4){
        Vector vec41(v4.x-v1.x,v4.y-v1.y,v4.z-v1.z);
        normv=cross(vec21,vec41);
          normals_vertex[v1_index]+=normv;
          normals_vertex[v2_index]+=normv;
          normals_vertex[v3_index]+=normv;
          normals_vertex[v4_index]+=normv;

        }

      //  normals_face[var]=normv;

    }
}
void PlyModel::readTexture2Buffer(char *filename,int width,int height){

texture=texture->loadTexture(filename);

}



void PlyModel::setShift(GLfloat *s){

shift[0]=s[0];
shift[1]=s[1];
shift[2]=s[2];

}
void PlyModel::computeCentroid(){

centroid[0]=(ply->vx_max+ply->vx_min)/2.0;
centroid[1]=(ply->vy_max+ply->vy_min)/2.0;
centroid[2]=(ply->vz_max+ply->vz_min)/2.0;


}
void PlyModel::computeScaleFactor(float dim){
float maxDiff=0.0;
float dx=ply->vx_max-ply->vx_min;
float dy=ply->vy_max-ply->vy_min;
float dz=ply->vz_max-ply->vz_min;
if(dx>dy)
{
if(dx>dz)
maxDiff=dx;
else
maxDiff=dz;

}else{
if(dy>dz)
maxDiff=dy;
else
maxDiff=dz;

}

scale_factor=dim/maxDiff;

}

