#ifndef plyutils_h
#define plyutils_h

#include <string.h>
#include <stdlib.h>
#include "ply.h"




class PlyUtility{

public:
    typedef struct Vertex {
        float x,y,z;
    } Vertex;

    typedef struct Face {
        unsigned char intensity;
        unsigned char nverts;    /* number of vertex indices in list */
        int *verts;              /* vertex index list */
    } Face;

    char * elem_names[2] = {
        "vertex", "face"
    };

    PlyProperty vert_props[3] = { /* list of property information for a vertex */
                                 {"x", PLY_FLOAT, PLY_FLOAT, offsetof(Vertex,x), 0, 0, 0, 0},
                                 {"y", PLY_FLOAT, PLY_FLOAT, offsetof(Vertex,y), 0, 0, 0, 0},
                                 {"z", PLY_FLOAT, PLY_FLOAT, offsetof(Vertex,z), 0, 0, 0, 0},
                               };

    PlyProperty face_props[2] = { /* list of property information for a vertex */
                                 {"intensity", PLY_UCHAR, PLY_UCHAR, offsetof(Face,intensity), 0, 0, 0, 0},
                                 {"vertex_indices", PLY_INT, PLY_INT, offsetof(Face,verts),1, PLY_UCHAR, PLY_UCHAR, offsetof(Face,nverts)},
                               };
void readPlyFile(char *);
int getFaceCount(void);
Face ** getFaceList(void);
Vertex ** getVertexList(void);

private:
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


};


#endif





