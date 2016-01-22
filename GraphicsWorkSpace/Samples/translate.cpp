void setupScene(int clearColor[]) {
    glClearColor(clearColor[0], clearColor[1], clearColor[2], clearColor[3]);
    //glClearColor(250, 250, 250, 1.0);  //  Set the cleared screen colour to black.
    glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);  // This sets up the viewport so that the coordinates (0, 0) are at the top left of the window.

    // Set up the orthographic projection so that coordinates (0, 0) are in the top left.
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, -10, 10);

    // Back to the modelview so we can draw stuff.
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Clear the screen and depth buffer.
}

int a = 100;
int b = 200;
int x = 100;
int y = 100;

void drawScene() {
    setupScene((int[]){250, 250, 250, 1});

    triangle(210, WINDOW_WIDTH, WINDOW_HEIGHT);

    glPushMatrix();
    glTranslatef(x, y, 0);

    glBegin(GL_QUADS);
    glColor3f(RGB(80), RGB(80), RGB(80));

    glVertex2d(b, a);
    glVertex2d(a, a);
    glVertex2d(a, b);
    glVertex2d(b, b);

    glEnd();

    glPopMatrix();

    glutSwapBuffers();  // Send the scene to the screen.
}

void update(int value) {
    if (x != 400 && y != 200) {
        x += 4;
        y += 2;
    }
    glutPostRedisplay();  // Tell GLUT that the display has changed.
    glutTimerFunc(25, update, 0);  // Tell GLUT to call update again in 25 milliseconds.
}
