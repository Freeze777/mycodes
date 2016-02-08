#ifndef VECTOR_H
#define VECTOR_H
#include <stdlib.h>
#include <math.h>
#include <strings.h>
class	Vector {

private :

    double		_vx, _vy, _vz ;

public :


    Vector () ;

    Vector ( const double, const double = 0.0, const double = 0.0 ) ;

    //  Copy Constructor

    Vector ( const Vector& ) ;

    //  Destructor

    virtual ~Vector () ;

    //  Assignment

    Vector& operator= ( const Vector& ) ;



    //  Comparison

    friend int operator== ( const Vector&, const Vector& ) ;
    friend int operator!= ( const Vector&, const Vector& ) ;

    //  Sum, Difference, Scalar Product

    friend Vector operator+ ( const Vector&, const Vector& ) ;
    friend Vector operator- ( const Vector&, const Vector& ) ;
    friend Vector operator- ( const Vector& ) ;
    friend Vector operator* ( const double&, const Vector& ) ;
    friend Vector operator* ( const Vector&, const double& ) ;
    friend Vector operator/ ( const Vector&, const double& ) ;

    //  Immediate Sum, Difference, Scalar Product

    Vector& operator+= ( const Vector& ) ;
    Vector& operator-= ( const Vector& ) ;
    Vector& operator*= ( const double& ) ;
    Vector& operator/= ( const double& ) ;

    //  Member Functions
    //  Dot Product

    friend double dot ( const Vector&, const Vector& ) ;

    //  Cross Product

    friend Vector cross ( const Vector&, const Vector& ) ;

    //  Normalization, Make it a unit Vector

    void normalize () ;

    //  Vector Length

    double length () const ;

    //  Access Functions to get
    //    x-coordinate, y-coordinate or
    //    z-coordinate of the vector

    double x () const { return _vx ; }
    double y () const { return _vy ; }
    double z () const { return _vz ; }

    void x (double x)  { _vx=x ; }
    void y (double y)  { _vy=y ; }
    void z (double z)  { _vz=z ; }

} ;

//  The global constant Vectors

const Vector VZero ( 0.0, 0.0, 0.0 ) ;
const Vector VX    ( 1.0, 0.0, 0.0 ) ;
const Vector VY    ( 0.0, 1.0, 0.0 ) ;
const Vector VZ    ( 0.0, 0.0, 1.0 ) ;

#endif



