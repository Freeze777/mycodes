#ifndef CUSTOM_STRUCT_FOR_ANGRY_BIRD_GAME_H
#define CUSTOM_STRUCT_FOR_ANGRY_BIRD_GAME_H
struct VAO {
    GLuint VertexArrayID;
    GLuint VertexBuffer;
    GLuint ColorBuffer;

    GLuint TextureBuffer;
	  GLuint TextureID;

    GLenum PrimitiveMode;
    GLenum FillMode;
    int NumVertices;
};
struct GLMatrices {
	glm::mat4 projection;
	glm::mat4 model;
	glm::mat4 view;
	GLuint MatrixID;
  GLuint TexMatrixID;
} Matrices;

/*struct FTGLFont {
	FTFont* font;
	GLuint fontMatrixID;
	GLuint fontColorID;
} GL3Font;*/

typedef struct VAO VAO;

#endif
