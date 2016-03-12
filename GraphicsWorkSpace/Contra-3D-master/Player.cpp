class Player {
  private:
  Cube cube;
  GLfloat length;
  GLfloat breadth;
  GLfloat height;
  GLfloat x_position;
  GLfloat y_position;
  GLfloat z_position;

  public:
  void initialize(GLfloat l, GLfloat h, GLfloat b) {
    length = l;
    height = h;
    breadth = b;
    x_position = -100;
    y_position = 20;
    z_position = 470;
    int colors[] = {8, 8, 3, 3, 2, 1};
    cube.initialize(colors, l, h, b);
  }

  void drawPlayer(glm::mat4 VP) {
    cube.drawCube(VP, x_position, y_position, z_position);
  }

  void move_left() {
    x_position -= 5.0f;
  }

  void move_right() {
    x_position += 5.0f;
  }

  void move_forward() {
    z_position -= 5.0f;
  }

  void move_backward() {
    z_position += 5.0f;
  }
};
