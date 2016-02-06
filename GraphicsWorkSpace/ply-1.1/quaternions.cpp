#ifndef QUATERNION_H
#define QUATERNION_H

#include <iostream>
#include <math.h>
#include <stdlib.h>

class Quaternion {
    double mData[4];
   static const double PI=3.1415926535898;

public:

    Quaternion() {
        mData[0] = mData[1] = mData[2] = 0;
        mData[3] = 1;
    }

    Quaternion(const double* array) {

        for (int i = 0; i < 4; i++) {
            mData[i] = array[i];
        }
    }
    Quaternion(double angle, double axisv[]) {
        angle=(angle*PI/360);//angle/2
        double sin_val=sin(angle);
        double mag=sqrt(axisv[0]*axisv[0]+axisv[1]*axisv[1]+axisv[2]*axisv[2]);
        mData[0] = (axisv[0]/mag)*sin_val;
        mData[1] = (axisv[1]/mag)*sin_val;
        mData[2] = (axisv[2]/mag)*sin_val;
        mData[3] = cos(angle);
    }

    Quaternion(double x, double y, double z, double w) {
        mData[0] = x;
        mData[1] = y;
        mData[2] = z;
        mData[3] = w;
    }

    double x() const { return mData[0]; }
    double y() const { return mData[1]; }
    double z() const { return mData[2]; }
    double w() const { return real(); }

    double real() const { return mData[3]; }
    void real(double r) { mData[3] = r; }

    Quaternion conjugate(void) const {
        return Quaternion(-x(),-y(),-z(), real());
    }

    Quaternion inverse(void) const {
        return conjugate() / norm();
    }



    Quaternion product(const Quaternion& rhs) const {
        return Quaternion(y()*rhs.z() - z()*rhs.y() + x()*rhs.w() + w()*rhs.x(),
                          z()*rhs.x() - x()*rhs.z() + y()*rhs.w() + w()*rhs.y(),
                          x()*rhs.y() - y()*rhs.x() + z()*rhs.w() + w()*rhs.z(),
                          w()*rhs.w() - x()*rhs.x() - y()*rhs.y() - z()*rhs.z());
    }


    Quaternion operator*(const Quaternion& rhs) const {
        return product(rhs);
    }

    Quaternion operator*(double s) const {
        return Quaternion(x()*s,y()*s,z()*s, real()*s);
    }

    Quaternion operator+(const Quaternion& rhs) const {
        return Quaternion(x()+rhs.x(), y()+rhs.y(), z()+rhs.z(), w()+rhs.w());
    }


    Quaternion operator-(const Quaternion& rhs) const {
        return Quaternion(x()-rhs.x(), y()-rhs.y(), z()-rhs.z(), w()-rhs.w());
    }


    Quaternion operator-() const {
        return Quaternion(-x(), -y(), -z(), -w());
    }


    Quaternion operator/(double s) const {
        if (s == 0) std::clog << "Dividing quaternion by 0." << std::endl;
        return Quaternion(x()/s,y()/s,z()/s, real()/s);
    }


    double norm() const { return sqrt(mData[0]*mData[0]+mData[1]*mData[1]+mData[2]*mData[2]+mData[3]*mData[3]); }


    double* rotationMatrix() const {

        double * mat=(double *)calloc(16,sizeof(double));

        mat[0]=1-2*y()*y()-2*z()*z();mat[1]= 2*x()*y() - 2*z()*w();mat[2]= 2*x()*z() + 2*y()*w();
        mat[4]= 2*x()*y() + 2*z()*w();mat[5]=1-2*x()*x()-2*z()*z();mat[6]=2*y()*z() - 2*x()*w();
        mat[8]= 2*x()*z() - 2*y()*w();mat[9]=2*y()*z() + 2*x()*w();mat[10]= 1-2*x()*x()-2*y()*y();
        mat[15]=1;

        return mat;
    }


};

Quaternion operator*(double s, const Quaternion& q);


#endif /* QUATERNION_H */


