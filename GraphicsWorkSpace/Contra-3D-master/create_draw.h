#ifndef DRAWING_AND_CREATE_FOR_CONTRA_3D_GAME_H
#define DRAWING_AND_CREATE_FOR_CONTRA_3D_GAME_H

struct VAO* create3DObject (GLenum primitive_mode, int numVertices,
  const GLfloat* vertex_buffer_data, const GLfloat* color_buffer_data, GLenum fill_mode);
struct VAO* create3DObject (GLenum primitive_mode, int numVertices,
  const GLfloat* vertex_buffer_data, const GLfloat red, const GLfloat green, const GLfloat blue, GLenum fill_mode);
void draw3DObject (struct VAO* vao);
#include "create_draw.cpp"
#endif
