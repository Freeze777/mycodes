CC=gcc
CFLAGS=-c -Wall

all: test.out

test.out: plyReader.o
	$(CC) plyReader.o plyfile.o -o test.out -lGL -lGLU -lglut

plyReader.o: plyReader.c
	$(CC) $(CFLAGS) plyReader.c

clean:
	rm -rf *.o assgn
