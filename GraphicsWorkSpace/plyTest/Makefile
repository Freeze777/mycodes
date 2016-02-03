CC=g++
CFLAGS=-c -Wall

all: test.out

test.out: plyReader.o
	$(CC) plyReader.o -o test.out -lGL -lGLU -lglut

plyReader.o: plyReader.cpp
	$(CC) $(CFLAGS) plyReader.cpp

clean:
	rm -rf *.o assgn
