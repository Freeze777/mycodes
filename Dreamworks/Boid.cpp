#include "Boid.h"

/* Define the functions below */

Boid::Boid(int x, int y, int xbound, int ybound,
        int     mboundaryPadding    ,
        float   mmaxSpeed           ,
        float   mmaxForce           ,
        float   mflockSepWeight     ,
        float   mflockAliWeight     ,
        float   mflockCohWeight     ,
        float   mflockSepRadius     ,
        float   mflockAliRadius     ,
        float   mflockCohRadius     )
{

    

}

// Method to update location
void Boid::update(vector<Boid> &boids) {

	

}

void Boid::seek(Vec2f target,float weight) {
}

void Boid::avoid(Vec2f target,float weight) {
}

void Boid::boundCheck(int padding) {

  


}


// A method that calculates a steering vector towards a target
Vec2f Boid::steer(Vec2f target) {
   
}


void Boid::flock(vector<Boid> &boids) {
	
}

bool Boid::isHit(int x, int y, int radius) {
    
}

// Separation
// Method checks for nearby boids and steers away
Vec2f Boid::separate(vector<Boid> &boids) {
    
}

// Alignment
// For every nearby boid in the system, calculate the average velocity
Vec2f Boid::align(vector<Boid> &boids) {
    
}

// Cohesion
// For the average location (i.e. center) of all nearby boids, calculate steering vector towards that location
Vec2f Boid::cohesion(vector<Boid> &boids) {
   
}


float Boid::dist(Vec2f v1,Vec2f v2)
{
}

float Boid::clamp(float val, float minval, float maxval)
{
    
}
