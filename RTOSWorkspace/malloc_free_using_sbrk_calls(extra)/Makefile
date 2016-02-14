CC=gcc
CFLAGS=-c -Wall

all: main

main: main.o functions.o
	$(CC) main.o functions.o -o main

main.o: main.c
	$(CC) $(CFLAGS) main.c

functions.o: functions.c
	$(CC) $(CFLAGS) functions.c

clean:
	rm -rf *.o main
