#ifndef QUATERNION_H
#define QUATERNION_H

#include <iostream>
#include <math.h>
#include <stdlib.h>

class Quaternion {
    double mData[4];//0-x 1-y 2-z 3-w
    static const double PI=3.1415926535898;

public:
    Quaternion();
    Quaternion(const double* array) ;
    Quaternion(double angle, double axisv[]);
    Quaternion(double x, double y, double z, double w) ;
    Quaternion  conjugate(void);
    Quaternion  inverse(void);
    double  norm();
    double*  rotationMatrix();
    Quaternion  product(const Quaternion& rhs);

	~Quaternion(void)
	{
	   
	}

    double x() const { return mData[0]; }
    double y() const { return mData[1]; }
    double z() const { return mData[2]; }
    double w() const { return real(); }

    double real() const { return mData[3]; }
    void real(double r) { mData[3] = r; }

    Quaternion operator*(const Quaternion& rhs) {
        return product(rhs);
    }

    Quaternion operator*(double s) {
        return Quaternion(x()*s,y()*s,z()*s, real()*s);
    }

    Quaternion operator+(const Quaternion& rhs)  {
        return Quaternion(x()+rhs.x(), y()+rhs.y(), z()+rhs.z(), w()+rhs.w());
    }


    Quaternion operator-(const Quaternion& rhs) {
        return Quaternion(x()-rhs.x(), y()-rhs.y(), z()-rhs.z(), w()-rhs.w());
    }


    Quaternion operator-()  {
        return Quaternion(-x(), -y(), -z(), -w());
    }


    Quaternion operator/(double s)  {
        if (s == 0) std::cout<< "Dividing quaternion by 0." << std::endl;
        return Quaternion(x()/s,y()/s,z()/s, real()/s);
    }
};

Quaternion operator*(double s, const Quaternion& q);


#endif /* QUATERNION_H */


