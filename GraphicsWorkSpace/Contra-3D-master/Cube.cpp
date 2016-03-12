// Class build cube
#include "custom_struct.h"
#include "create_draw.h"
#include "color.h"

class Cube {
  private:
  VAO* cube;
  GLfloat length;
  GLfloat height;
  GLfloat breadth;
  int color_scheme[6];

  public:
  void initialize(int colors[], GLfloat l = 1.0f, GLfloat h = 1.0f, GLfloat b = 1.0f) {
    length = l;
    height = h;
    breadth = b;
    for (int i=0; i<6; i++)
      color_scheme[i] = colors[i];
    createCube();
  }

  private:
  // Cube face will be identified using their face center
  void createCube() {
    //const int color_scheme[] = {11, 12, 13, 1, 3, 14};
    GLfloat cube_color_buffer[108];
    int start = 0;
    for (int i=0; i<6; i++) {
      if (color_scheme[i] == 1) {
        start = copy_color(start, BLACK_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 2) {
        start = copy_color(start, RED_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 3) {
        start = copy_color(start, GREEN_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 4) {
        start = copy_color(start, YELLOW_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 5) {
        start = copy_color(start, BLUE_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 6) {
        start = copy_color(start, MAGENTA_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 7) {
        start = copy_color(start, CYAN_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 8) {
        start = copy_color(start, DARK_GRAY_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 9) {
        start = copy_color(start, LIGHT_GRAY_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 10) {
        start = copy_color(start, BROWN_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 11) {
        start = copy_color(start, ORANGE_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 12) {
        start = copy_color(start, PINK_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 13) {
        start = copy_color(start, PURPLE_COLOR, cube_color_buffer);
      } else if (color_scheme[i] == 14) {
        start = copy_color(start, WHITE_COLOR, cube_color_buffer);
      }
    }

    const GLfloat cube_vertex_buffer[] = {
      // X = l/2, Y = h/2, Z = 0
      0, 0, 0,
      length, 0, 0,
      length, height, 0,
      0, 0, 0,
      0, height, 0,
      length, height, 0,

      // X = l/2, Y = h/2, Z = b
      0, 0, breadth,
      length, 0, breadth,
      length, height, breadth,
      0, 0, breadth,
      0, height, breadth,
      length, height, breadth,


      0, 0, 0,
      0, 0, breadth,
      0, height, breadth,
      0, 0, 0,
      0, height, 0,
      0, height, breadth,

      length, 0, 0,
      length, 0, breadth,
      length, height, breadth,

      length, 0, 0,
      length, height, 0,
      length, height, breadth,

      0, height, 0,
      length, height, 0,
      length, height, breadth,

      0, height, 0,
      0, height, breadth,
      length, height, breadth,

      0, 0, 0,
      length, 0, 0,
      length, 0, breadth,

      0, 0, 0,
      0, 0, breadth,
      length, 0, breadth,
    };
    cube = create3DObject(GL_TRIANGLES, 36, cube_vertex_buffer, cube_color_buffer, GL_FILL);
  }

  int copy_color(int start, const GLfloat *array, GLfloat color_array[]) {
    for (int i=0; i<18; i++) {
      color_array[start++] = array[i%3];
    }
    return start;
  }

  public:
  void drawCube(glm::mat4 VP, GLfloat x = 0.0f, GLfloat y = 0.0f, GLfloat z = 0.0f) {
    glm::mat4 MVP;
    Matrices.model = glm::mat4(1.0f);
    glm::mat4 translateCube = glm::translate(glm::vec3(x, y, z));
    Matrices.model *= (translateCube);
    MVP = VP * Matrices.model;
    glUniformMatrix4fv(Matrices.MatrixID, 1, GL_FALSE, &MVP[0][0]);
    draw3DObject(cube);
  }
};
