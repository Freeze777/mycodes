CC=g++
CFLAGS=-c -Wall

all: assgn

assgn: assignment2.o display_utility.o curves.o transformations.o rply.o
	$(CC) assignment2.o display_utility.o curves.o transformations.o rply.o -o assgn -lGL -lGLU -lglut

assignment2.o: assignment2.cpp
	$(CC) $(CFLAGS) assignment2.cpp

display_utility.o: display_utility.cpp
	$(CC) $(CFLAGS) display_utility.cpp

curves.o: curves.cpp
	$(CC) $(CFLAGS) curves.cpp

transformations.o: transformations.cpp
	$(CC) $(CFLAGS) transformations.cpp

clean:
	rm -rf *.o assgn
