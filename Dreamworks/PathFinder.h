#pragma once

#include <vector>
#include "Scene.h"
#include <set>

#ifdef __LINUX_COMPILE
#include <limits>
#endif

using namespace math;

struct SearchNode
{
	int x;
	int y;
	int id;
	SearchNode* parent;
	double movementCost;
	double hueristicCost;

	SearchNode(int x, int y, int id, SearchNode* parent = nullptr)
		: parent(parent), x(x), y(y), id(id), movementCost(0), hueristicCost(0) {}

	double getExpectedCost() const
	{
		return movementCost + hueristicCost;
	}

	void calculateHeuristicCost(SearchNode* endNode)
	{
		hueristicCost = std::abs(x - endNode->x) + std::abs(y - endNode->y);
	}

};

class PathFinder
{
public:
	std::vector<Vec2f> getPath(Scene* scene);
	std::vector<Vec2f> getPath(Scene* scene, Vec2f currentPosition);
	bool foundPath() const
	{
		return mFoundPath;
	}
	~PathFinder()
	{
		cleanup();
	}
private:
	SearchNode* getNextNode();
	void searchPath();
	void searchNode(unsigned int x, unsigned int y, double cost, SearchNode* parent);
	void cleanup();

	std::vector<SearchNode*> mOpenList;
	std::vector<SearchNode*> mClosedList;
	std::vector<Vec2f> mPath;
	SearchNode* mStartNode;
	SearchNode* mEndNode;
	bool mFoundPath;
	Scene* mScene;
};
