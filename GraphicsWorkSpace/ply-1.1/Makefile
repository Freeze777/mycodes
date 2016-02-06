CC=g++
CFLAGS=-c -Wall

all: test.out

test.out: plyReader.o plyfile.o
	$(CC) plyReader.o plyfile.o -o test.out -lGL -lGLU -lglut

plyReader.o: plyReader.c
	$(CC) $(CFLAGS) plyReader.c

plyfile.o: plyfile.c
	gcc $(CFLAGS) plyfile.c
clean:
	rm -rf *.o assgn
