class Tile {
  private:
  Cube tile_cube;
  GLfloat tile_length;
  GLfloat tile_height;
  GLfloat tile_breadth;
  GLfloat tile_x;
  GLfloat tile_y;
  GLfloat tile_z;

  public:
  void initialize(GLfloat l, GLfloat h, GLfloat b, GLfloat x_pos, GLfloat y_pos, GLfloat z_pos) {
    tile_length = l;
    tile_height = h;
    tile_breadth = b;
    tile_x = x_pos;
    tile_y = y_pos;
    tile_z = z_pos;
    int colors[] = {DARK_GRAY, DARK_GRAY, DARK_GRAY, DARK_GRAY, LIGHT_GRAY, BLACK};
    tile_cube.initialize(colors, tile_length, tile_height, tile_breadth);
  }

  void print(int y) {
    printf("%d%d%d%d\n", y,y,y,y);
    printf("%f %f %f\n", tile_x, tile_y, tile_z );
    printf("%d%d%d%d\n", y,y,y,y);
  }

  void drawTile(glm::mat4 VP) {
    //printf("%f %f %f\n", xpos, ypos, zpos );
    tile_cube.drawCube(VP, tile_x, tile_y, tile_z);
  }

  void move_up() {
    tile_y += 2.0f;
  }

  void move_down() {
    tile_y -= 2.0f;
  }
};
