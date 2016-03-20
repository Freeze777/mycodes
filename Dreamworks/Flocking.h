#pragma once
#include "Boid.h"
#include "Scene.h"
#include "PathFinder.h"

class Flocking {
public:
	int update();
	void addBoid();
	void addBoid(int x, int y);
    void removeBoid(int x, int y, int radius);
    void setBounds(int xbound=100, int ybound=100);
    int flockSize();
    void setDestination(int x, int y,float area);
    void setDestination(Vec2f dest,float area);
    void setSceneMap(Scene* scene);
    void useCollisionSDF(bool val);
    vector<Boid> getBoids();
    Vec2f** calculatePartialDerivaties();

    void setSimulationParameters(
    		int 	mboundaryPadding 	,
            float 	mmaxSpeed 			,
            float 	mmaxForce 			,
          	float 	mflockSepWeight 	,
          	float 	mflockAliWeight 	,
          	float 	mflockCohWeight 	,
          	float 	mcollisionWeight 	,
          	float 	mflockSepRadius 	,
          	float 	mflockAliRadius 	,
          	float 	mflockCohRadius 	,
          	float 	mdestWeight 		);
           			
    


    int x_bound,y_bound;
    Vec2f destination;
    float destinationArea;
    Vec2f destinationSeek;
    Scene* sceneMap;
    bool useCollisionFromSDF;
    float** collisionSDF;
    Vec2f** partialDerivaties;
    PathFinder pathFinder;

    vector<Boid> boids;

    //Simulation args
    int 	boundaryPadding ;
    float 	maxSpeed 		;
    float 	maxForce 		;
	float 	flockSepWeight ;
	float 	flockAliWeight ;
	float 	flockCohWeight ;
	float 	collisionWeight; 
	float 	flockSepRadius ;
	float 	flockAliRadius ;
	float 	flockCohRadius ;
   	float 	destWeight 	;

};
