#include "PlyUtility.h"

int PlyUtility::getFaceCount(void){
    return faceCount;
}
PlyUtility::Face ** PlyUtility::getFaceList(void){
    return flist;
}

PlyUtility::Vertex ** PlyUtility::getVertexList(void){
    return vlist;
}
void PlyUtility::readPlyFile(char *filename)
{
    int i,j;

    /* open a PLY file for reading */
    ply = ply_open_for_reading(filename, &nelems, &elist, &file_type, &version);


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
                if(vx_max<vlist[j]->x)vx_max=vlist[j]->x;
                if(vy_max<vlist[j]->y)vy_max=vlist[j]->y;
                if(vz_max<vlist[j]->z)vz_max=vlist[j]->z;

                if(vx_min>vlist[j]->x)vx_min=vlist[j]->x;
                if(vy_min>vlist[j]->y)vy_min=vlist[j]->y;
                if(vz_min>vlist[j]->z)vz_min=vlist[j]->z;



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


