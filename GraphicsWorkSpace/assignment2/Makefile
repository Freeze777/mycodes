CC=g++
CFLAGS=-c -Wall

all: test.out

test.out: main.o plyfile.o View.o PlyUtility.o PlyModel.o BoundingBox.o Quaternions.o Vector.o
	$(CC) main.o plyfile.o View.o PlyUtility.o PlyModel.o BoundingBox.o Quaternions.o Vector.o -o test.out -lGL -lGLU -lglut

main.o: main.cpp
	$(CC) $(CFLAGS) main.cpp

plyfile.o: plyfile.c
	gcc $(CFLAGS) plyfile.c

View.o: View.cpp
	$(CC) $(CFLAGS) View.cpp

PlyUtility.o: PlyUtility.cpp
	$(CC) $(CFLAGS) PlyUtility.cpp

PlyModel.o: PlyModel.cpp
	$(CC) $(CFLAGS) PlyModel.cpp

BoundingBox.o: BoundingBox.cpp
	$(CC) $(CFLAGS) BoundingBox.cpp

Quaternions.o: Quaternions.cpp
	$(CC) $(CFLAGS) Quaternions.cpp

Vector.o: Vector.cpp
	$(CC) $(CFLAGS) Vector.cpp

clean:
	rm -rf *.o assgn
