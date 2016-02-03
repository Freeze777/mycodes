#include "display_utility.h"

#define PI 3.1415926535898
#define Cos(th) cos(PI/180*(th))
#define Sin(th) sin(PI/180*(th))

void zoom(float scale,Point pivot);
void translate(float offset);
void rotate(float angle,Point pivot);
