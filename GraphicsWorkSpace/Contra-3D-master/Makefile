all: contra3D

contra3D: Contra-3D.cpp glad.c Cube.cpp Player.cpp Tile.cpp Platform.cpp
	g++ -o contra3D Contra-3D.cpp glad.c -lGL -lglfw -ldl

clean:
	rm contra3D
