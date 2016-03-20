#include "MapLoader.h"
#include "Vector.h"
#include <cstring>
#include <openvdb/openvdb.h>
#include <stdlib.h>
#include <fstream>
using namespace std;

/************************ This loads the map from openVDB.
                          You can use the native viewer once you complete this.
                          There are comments for each snippet of code.
                          Hint: http://www.openvdb.org/documentation/doxygen/codeExamples.html **************/



bool**
MapLoader::loadVDBMap(char* filename)
{

   //Initialize the OpenVDB library.
	openvdb::initialize();
 
    // Create a VDB file object.
	openvdb::io::File file(filename);

    // Open the file. This reads the file header, but not any grids.
	file.open();


    //Loop over all grids in the file and retrieve a shared pointer to "SDF". Close file


		openvdb::GridBase::Ptr baseGrid;
	for (openvdb::io::File::NameIterator nameIter = file.beginName();
	    nameIter != file.endName(); ++nameIter)
	{
	    // Read in only the grid we are interested in.
	    if (nameIter.gridName() == "SDF") {
		baseGrid = file.readGrid(nameIter.gridName());
	    } else {
		std::cout << "skipping grid " << nameIter.gridName() << std::endl;
	    }
	}

	file.close();
    //cast the generic grid pointer to a FloatGrid pointer.
	openvdb::FloatGrid::Ptr grid = openvdb::gridPtrCast<openvdb::FloatGrid>(baseGrid);
    //Get metadata into strings, see .meta file 
for (openvdb::MetaMap::MetaIterator iter = grid->beginMeta();
    iter != grid->endMeta(); ++iter)
{
    const std::string& name = iter->first;
    openvdb::Metadata::Ptr value = iter->second;
    std::string valueAsString = value->str();
    std::cout << name << " = " << valueAsString << std::endl;
}

    //Convert strings to numbers

	//int i=stoi(s);

printf("here");
    // Create the map data - 2D map and SDF arrays. Allocate memory

    //Get an accessor for coordinate-based access to voxels.
    
    /* Iterate through SDF arrayusing the accessor to get SDF values. 
       Add true or false to map data according to the SDF value. */


    //Get values of Vectors posStart and posEnd

    //Change "loaded" flag

    //Return map data array
}

float**
MapLoader::getSDF(){

}

MapLoader::MapLoader() {

}

Vec2f
MapLoader::getStartPosition() {

}

Vec2f
MapLoader::getEndPosition() {

}

unsigned int
MapLoader::gety_boundary() {

}

unsigned int
MapLoader::getx_boundary() {

}

unsigned int
MapLoader::getStartRadius() {

}

unsigned int
MapLoader::getEndRadius() {

}
