// The Game Platform
#include "Cube.cpp"

class Platform {
  private:
  Cube cube;
  GLfloat platform_length;
  GLfloat platform_height;
  GLfloat platform_breadth;

  public:
  void initialize(GLfloat l, GLfloat h, GLfloat b) {
    platform_length = l;
    platform_height = h;
    platform_breadth = b;
    int colors[] = {8, 8, 8, 8, 9, 1};
    cube.initialize(colors, platform_length, platform_height, platform_breadth);
  }

  void drawPlatform(glm::mat4 VP, GLfloat xpos, GLfloat ypos, GLfloat zpos) {
    cube.drawCube(VP, xpos, ypos, zpos);
  }

};
