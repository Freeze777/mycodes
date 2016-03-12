// Class makes the circle
#include "custom_struct.h"
#include "create_draw.h"

class Circle {
  private:
  VAO *triangle;
  float center_x, center_y;

  public:
  void initialize(float x, float y, float radius, bool fill = true) {
    center_x = x;
    center_y = y;
    createTriangle(radius, fill);
  }

  private:
  void createTriangle (float radius = 1.0f, bool fill = true) {
    const GLfloat vertex_buffer_data [] = {
      0, 0, 0, // vertex 0
      radius, 0,0, // vertex 1
      0,radius ,0, // vertex 2
    };

    const GLfloat color_buffer_data [] = {
      1,1,1, // color 0
      1,1,1, // color 1
      1,1,1, // color 2
    };
    if (fill) {
      // create3DObject creates and returns a handle to a VAO that can be used later
      triangle = create3DObject(GL_TRIANGLES, 3, vertex_buffer_data, color_buffer_data, GL_FILL);
    } else {
      triangle = create3DObject(GL_TRIANGLES, 3, vertex_buffer_data, color_buffer_data, GL_LINE);
    }
  }

  public:
  void makeCircle(glm::mat4 VP, float x = -3.5f, float y = -3.5f) {
    glm::mat4 MVP;
    for(int i=0; i<360; i++) {
      Matrices.model = glm::mat4(1.0f);
      //glm::mat4 scaleTriangle = glm::mat4(1.0f);
      float rotAngle = (float)(i * M_PI/180);

      glm::mat4 translateTriangle = glm::translate (glm::vec3(x, y, 0.0f)); // glTranslatef
      glm::mat4 rotateTriangle = glm::rotate(rotAngle, glm::vec3(0,0,1));  // rotate about vector (0,0,1) Rotating about z-axis

      Matrices.model *= (translateTriangle * rotateTriangle);// * scaleTriangle);
      MVP = VP * Matrices.model; // MVP = p * V * M
      glUniformMatrix4fv(Matrices.MatrixID, 1, GL_FALSE, &MVP[0][0]);
      draw3DObject(triangle);
    }
  }
};
