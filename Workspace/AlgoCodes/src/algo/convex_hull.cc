#include <cmath>
#include <vector>
#include <stdlib.h>
#include <map>
#include <set>
#include <ctime>
#include <cmath>
#include <queue>
#include <stack>
#include <bitset>
#include <vector>
#include <cstdio>
#include <string>
#include <cassert>
#include <cstring>
#include <numeric>
#include <sstream>
#include <iterator>
#include <iostream>
#include <algorithm>
using namespace std;
typedef long long LL;
#define MM(a,x) memset(a, x, sizeof(a))
#define P(x) cout<<#x<<" = "<<x<<endl;
#define P2(x,y) cout<<#x<<" = "<<x<<", "<<#y<<" = "<<y<<endl;
#define PV(a,n) for(int i=0;i<n;i++) cout<<#a<<"[" << i <<"] = "<<a[i]<<endl;
#define TM(a,b) cout<<#a<<"->"<<#b<<": "<<1.*(b-a)/CLOCKS_PER_SEC<<"s\n";


class Point
{
    public:
    int x, y;
    bool operator<(const Point& src)const
    {
        if (this->x==src.x) return (this->y < src.y);
        else return (this->x < src.x);
    }
};

Point p0;
 
// A utility function to find next to top in a stack
Point nextToTop(stack<Point> &S)
{
    Point p = S.top();
    S.pop();
    Point res = S.top();
    S.push(p);
    return res;
}
 
// A utility function to swap two points
void swap(Point &p1, Point &p2)
{
    Point temp = p1;
    p1 = p2;
    p2 = temp;
}

int distSq(Point p1, Point p2)
{
    return (p1.x - p2.x)*(p1.x - p2.x) +
          (p1.y - p2.y)*(p1.y - p2.y);
}
 

int orientation(Point p, Point q, Point r)
{
    int val = (q.y - p.y) * (r.x - q.x) -
              (q.x - p.x) * (r.y - q.y);
 
    if (val == 0) return 0;  // colinear
    return (val > 0)? 1: 2; // clock or counterclock wise
}
 
// A function used by library function qsort() to sort an array of
// points with respect to the first point
int compare(const void *vp1, const void *vp2)
{
   Point *p1 = (Point *)vp1;
   Point *p2 = (Point *)vp2;
 
   // Find orientation
   int o = orientation(p0, *p1, *p2);
   if (o == 0)
     return (distSq(p0, *p2) >= distSq(p0, *p1))? -1 : 1;
 
   return (o == 2)? -1: 1;
}
 
// Prints convex hull of a set of n points.
void convexHull(Point points[], int n, map<Point,int> &sconvx,vector<Point> &vconvx)
{
   // Find the bottommost point
   int ymin = points[0].y, min = 0;
   for (int i = 1; i < n; i++)
   {
     int y = points[i].y;
 
     // Pick the bottom-most or chose the left
     // most point in case of tie
     if ((y < ymin) || (ymin == y &&
         points[i].x < points[min].x))
        ymin = points[i].y, min = i;
   }
 
   // Place the bottom-most point at first position
   swap(points[0], points[min]);
 
  
   p0 = points[0];
   qsort(&points[1], n-1, sizeof(Point), compare);
 
   
   int m = 1; // Initialize size of modified array
   for (int i=1; i<n; i++)
   {
       // Keep removing i while angle of i and i+1 is same
       // with respect to p0
       while (i < n-1 && orientation(p0, points[i],
                                    points[i+1]) == 0)
          i++;
 
 
       points[m] = points[i];
       m++;  // Update size of modified array
   }
 
   if (m < 3) return;
 
   stack<Point> S;
   S.push(points[0]);
   S.push(points[1]);
   S.push(points[2]);
 
   for (int i = 3; i < m; i++)
   {
 
      while (orientation(nextToTop(S), S.top(), points[i]) != 2)
         S.pop();
      S.push(points[i]);
   }
   int i=0;
   while (!S.empty())
   {
       Point p = S.top();
       sconvx[p]=i++;
       vconvx.push_back(p);
       S.pop();
   }
}
 
Point points[500]; 
void printmap(map<Point,int> &m){
   map<Point,int>::iterator it= m.begin();
   for(; it != m.end(); it++) {
    cout<< it->first.x <<" "<<it->first.y<<" "<< it->second<< endl;
   }
}
void printvect( vector<Point> &vconvx){
  vector<Point>::iterator iter1=vconvx.begin();
   for(;iter1!=vconvx.end();++iter1)
    {
        cout<<iter1->x<<" "<<iter1->y<<endl;
    }
}
double dist(Point &p1,Point &p2){
  double dx=(double)(p1.x-p2.x);
  double dy=(double)(p1.y-p2.y);
  return sqrt(dx*dx+dy*dy);
}
int main()
{   
    ios::sync_with_stdio(false);
    cin.tie(0);
    map<Point,int> p;
    int n,aa,bb;cin>>n>>aa>>bb;
    //int a=aa>bb?bb:aa;
    //int b=aa<bb?bb:aa;
    //a<b always
   int a=aa,b=bb;
    
    //polygon vertices are clockwise or anticlockwise
    for(int i=0;i<n;i++){
      cin>>points[i].x>>points[i].y;
      p[points[i]]==i;
    }
    bool clockwise=(points[0].x!=points[1].x)?(points[0].x>points[1].x):(points[n-1].x>points[0].x);
    if(!clockwise){
      for(int i=0,j=n-1;i<j;i++,j--){
        Point p=points[i];
        points[i]=points[j];
        points[j]=p;
      }
      a=n-a-1;
      b=n-b-1;
    }

    map<Point,int> sconvx;
    //clockwise hull
    vector<Point> vconvx;
    convexHull(points, n,sconvx,vconvx);
    //cout<<convx;
   //printmap(sconvx);printvect(vconvx);
   int src=a;
   int sz=vconvx.size();
   double ans=0.0,temp=0.0;
   //go clockwise a-->b forward
   while(src!=b){
    if ( sconvx.find(points[src]) != sconvx.end() ) {
      int v=sconvx[points[src]];
      Point nxt=vconvx[(v+1)%sz];
      if(p[nxt]>b){
        temp+=dist(points[src],points[(src+1)%n]);
        src=(src+1)%n;
      }else{
        temp+=dist(points[src],points[p[nxt]]);
        src=p[nxt];
      }  
    } else {
      temp+=dist(points[src],points[(src+1)%n]);
      src=(src+1)%n;
    }
   }
    ans=temp;/*
    src=a;
    while(src!=b){
    if ( sconvx.find(points[src]) != sconvx.end() ) {
      int v=sconvx[points[src]];
      Point nxt=vconvx[(v-1)%sz];
      if(p[nxt]<b){
        temp+=dist(points[src],points[(src-1)%n]);
        src=(src-1)%n;
      }else{
        temp+=dist(points[src],points[p[nxt]]);
        src=p[nxt];
      }  
    } else {
      temp+=dist(points[src],points[(src-1)%n]);
      src=(src-1)%n;
    }
    if (temp<ans)
    {
      ans=temp;
    }   

  }*/
   cout<<ans;
    return 0;
}