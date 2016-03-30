
#include "Vector.h"

#define		TRUE		1 
#define		FALSE		0

Vector :: Vector ()

   {
      _vx = 0.0 ;
      _vy = 0.0 ;
      _vz = 0.0 ;
   }

Vector :: Vector ( const double x, const double y, const double z )

   {
      _vx = x ;
      _vy = y ;
      _vz = z ;
   }

Vector :: Vector ( const Vector& v ) 

   {
      _vx = v._vx ;
      _vy = v._vy ;
      _vz = v._vz ;
   }

Vector :: ~Vector () { }
      
Vector& Vector :: operator= ( const Vector& v ) 

   {
      if ( this == &v ) return ( *this ) ;

      _vx = v._vx ;
      _vy = v._vy ;
      _vz = v._vz ;

      return ( *this ) ;
   }


Vector operator+ ( const Vector& v1, const Vector& v2 ) 

   {
      Vector vv ;

      vv._vx = v1._vx + v2._vx ;
      vv._vy = v1._vy + v2._vy ;
      vv._vz = v1._vz + v2._vz ;

      return ( vv ) ;
   }

Vector operator- ( const Vector& v1, const Vector& v2 ) 

   {
      Vector vv ;

      vv._vx = v1._vx - v2._vx ;
      vv._vy = v1._vy - v2._vy ;
      vv._vz = v1._vz - v2._vz ;

      return ( vv ) ;
   }

Vector operator- ( const Vector& v ) 

   {
      Vector vv ;

      vv._vx = - v._vx ;
      vv._vy = - v._vy ;
      vv._vz = - v._vz ;

      return ( vv ) ;
   }

Vector operator* ( const double& c, const Vector& v ) 

   {
      Vector vv ;

      vv._vx = c * v._vx ;
      vv._vy = c * v._vy ;
      vv._vz = c * v._vz ;

      return ( vv ) ;
   }

Vector operator* ( const Vector& v, const double& c ) 

   {
      Vector vv ;

      vv._vx = c * v._vx ;
      vv._vy = c * v._vy ;
      vv._vz = c * v._vz ;

      return ( vv ) ;
   }

Vector operator/ ( const Vector& v, const double& c ) 

   {
      Vector vv ;

      vv._vx = v._vx / c ;
      vv._vy = v._vy / c ;
      vv._vz = v._vz / c ;

      return ( vv ) ;
   }

Vector& Vector :: operator+= ( const Vector& v ) 

   {
      _vx += v._vx ;
      _vy += v._vy ;
      _vz += v._vz ;

      return *this ;
   }

Vector& Vector :: operator-= ( const Vector& v ) 

   {
      _vx -= v._vx ;
      _vy -= v._vy ;
      _vz -= v._vz ;

      return *this ;
   }

Vector& Vector :: operator*= ( const double& c ) 

   {
      _vx *= c ;
      _vy *= c ;
      _vz *= c ;

      return *this ;
   }

Vector& Vector :: operator/= ( const double& c ) 

   {
      _vx /= c ;
      _vy /= c ;
      _vz /= c ;

      return *this ;
   }


void Vector :: normalize ()  

   {
      double l =  length () ;

      _vx = _vx / l ;
      _vy = _vy / l ;
      _vz = _vz / l ;
   }


double Vector :: length ()  const

   {
      double		l ;

      l =  sqrt ( _vx * _vx + _vy * _vy + _vz * _vz ) ;
      return ( l ) ;
   }


double dot ( const Vector& v1, const Vector& v2 ) 

   {
      double		d ;

      d =  v1._vx * v2._vx + v1._vy * v2._vy + v1._vz * v2._vz ;

      return ( d ) ;
   }


Vector cross ( const Vector& v1, const Vector& v2 ) 

   {
      Vector vv ;

      vv._vx = v1._vy * v2._vz - v1._vz * v2._vy ;
      vv._vy = - v1._vx * v2._vz + v1._vz * v2._vx ;
      vv._vz = v1._vx * v2._vy - v1._vy * v2._vx ;

      return ( vv ) ;
   }
 
