
#include "Image.h"

void Image::makeCheckImage(void){
  int i, j, c;
  for (i = 0; i < checkImageWidth; i++) {
      for (j = 0; j < checkImageHeight; j++) {
          c = ((((i&0x8)==0)^((j&0x8)==0)))*255;
          checkImage[i][j][0] = (GLubyte) c;
          checkImage[i][j][1] = (GLubyte) c;
          checkImage[i][j][2] = (GLubyte) c;
      }
  }


}

int Image::ImageLoad(char *filename, Image *image) {
  FILE *file;
  unsigned long size; // size of the image in bytes.
  unsigned long i; // standard counter.
  unsigned short int planes; // number of planes in image (must be 1)
  unsigned short int bpp; // number of bits per pixel (must be 24)
  char temp; // temporary color storage for bgr-rgb conversion.
  // make sure the file is there.
  if ((file = fopen(filename, "rb"))==NULL){
      printf("File Not Found : %s\n",filename);
      return 0;
  }
  // seek through the bmp header, up to the width/height:
  fseek(file, 18, SEEK_CUR);
  // read the width
  if ((i = fread(&image->sizeX, 4, 1, file)) != 1) {
      printf("Error reading width from %s.\n", filename);
      return 0;
  }
  //printf("Width of %s: %lu\n", filename, image->sizeX);
  // read the height
  if ((i = fread(&image->sizeY, 4, 1, file)) != 1) {
      printf("Error reading height from %s.\n", filename);
      return 0;
  }
  //printf("Height of %s: %lu\n", filename, image->sizeY);
  // calculate the size (assuming 24 bits or 3 bytes per pixel).
  size = image->sizeX * image->sizeY * 3;
  // read the planes
  if ((fread(&planes, 2, 1, file)) != 1) {
      printf("Error reading planes from %s.\n", filename);
      return 0;
  }
  if (planes != 1) {
      printf("Planes from %s is not 1: %u\n", filename, planes);
      return 0;
  }
  // read the bitsperpixel
  if ((i = fread(&bpp, 2, 1, file)) != 1) {
      printf("Error reading bpp from %s.\n", filename);
      return 0;
  }
  if (bpp != 24) {
      printf("Bpp from %s is not 24: %u\n", filename, bpp);
      return 0;
  }
  // seek past the rest of the bitmap header.
  fseek(file, 24, SEEK_CUR);
  // read the data.
  image->data = (char *) malloc(size);
  if (image->data == NULL) {
      printf("Error allocating memory for color-corrected image data");
      return 0;
  }
  if ((i = fread(image->data, size, 1, file)) != 1) {
      printf("Error reading image data from %s.\n", filename);
      return 0;
  }
  for (i=0;i<size;i+=3) { // reverse all of the colors. (bgr -> rgb)
      temp = image->data[i];
      image->data[i] = image->data[i+2];
      image->data[i+2] = temp;
  }
  // we're done.
  return 1;


}

void Image::print(){
for(unsigned int i=0;i < sizeX*sizeY;i++)
printf("%lu ",data[i]);

}

Image * Image::loadTexture(char *filename){
  Image *image1;
  // allocate space for texture
  image1 = (Image *) malloc(sizeof(Image));
  if (image1 == NULL) {
      printf("Error allocating space for image");
      exit(0);
  }
  if (!ImageLoad(filename, image1)) {
      exit(1);
  }
  makeCheckImage();
  return image1;


}



