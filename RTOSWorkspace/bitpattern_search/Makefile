CC=gcc
CFLAGS=-c -Wall

all: main

main: main.o bits.o
	$(CC) main.o bits.o -o main

main.o: main.c
	$(CC) $(CFLAGS) main.c

bits.o: bits.c
	$(CC) $(CFLAGS) bits.c

clean:
	rm -rf *.o main
