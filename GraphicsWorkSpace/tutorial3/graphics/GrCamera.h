// GrCamera.h: interface for the CGrCamera class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_GRCAMERA_H__4AA26B3C_6FD9_4573_9A4A_B677E9F852D0__INCLUDED_)
#define AFX_GRCAMERA_H__4AA26B3C_6FD9_4573_9A4A_B677E9F852D0__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include <GL/glu.h>

class CGrCamera  
{
public:
	CGrCamera();
	virtual ~CGrCamera();

	double CameraDistance();
	void Gravity(bool p_gravity);
	void DollyCenter(double x, double y, double z);
	void DollyCamera(double x, double y, double z);
	void Dolly(double x, double y, double z);
	void Pitch(double d);
	void Yaw(double d);
	void Roll(double d);
	void Tilt(double d);
	void Pan(double d);
	void Set3dv(const double *p_eye, const double *p_center, const double *p_up);
	void Set(double p_eyex, double p_eyey, double p_eyez, double p_cenx, double p_ceny, double p_cenz, double p_upx, double p_upy, double p_upz);

    void FieldOfView(double f) {m_fieldofview = f;}
    double FieldOfView() const {return m_fieldofview;}

    const double *Eye() const {return m_eye;}
    const double *Center() const {return m_center;}
    const double *Up() const {return m_up;}

    double EyeX() const {return m_eye[0];}
    double EyeY() const {return m_eye[1];}
    double EyeZ() const {return m_eye[2];}
    double CenterX() const {return m_center[0];}
    double CenterY() const {return m_center[1];}
    double CenterZ() const {return m_center[2];}
    double UpX() const {return m_up[0];}
    double UpY() const {return m_up[1];}
    double UpZ() const {return m_up[2];}

#if 0
    const CGrPoint ViewNormal() const {return Normalize3(m_center - m_eye);}
    const CGrPoint &CameraNormal() const {return m_camerax;}
    const CGrPoint &FrameX() const {return m_camerax;}
    const CGrPoint &FrameY() const {return m_cameray;}
    const CGrPoint &FrameZ() const {return m_cameraz;}
#endif

    bool Gravity() const {return m_gravity;}

    enum eMouseMode {PANTILT, ROLLMOVE, PITCHYAW, DOLLYXY};

    void MouseMode(eMouseMode m) {m_mousemode = m;}
    eMouseMode MouseMode() const {return m_mousemode;}
    void MouseDown(int x, int y) {m_mousex = x;  m_mousey = y;}
	void MouseMove(int x, int y);

    void gluLookAt() {::gluLookAt(m_eye[0], m_eye[1], m_eye[2], 
                                  m_center[0], m_center[1], m_center[2],
                                  m_up[0], m_up[1], m_up[2]);}


private:
	double          m_up[3];
	double          m_center[3];
	double          m_eye[3];

	double m_fieldofview;
	int m_mousey;
	int m_mousex;
    eMouseMode m_mousemode;
	void DollyHelper(double m[4][4], double x, double y, double z);
	void ComputeFrame();
    bool     m_gravity;

    // The camera frame.
    double          m_camerax[3];
    double          m_cameray[3];
    double          m_cameraz[3];

    void RotCamera(double m[4][4]);
    void UnRotCamera(double m[4][4]);
    void RotCameraX(double m[4][4], double a);
    void RotCameraY(double m[4][4], double a);
    void RotCameraZ(double m[4][4], double a);
};

#endif // !defined(AFX_GRCAMERA_H__4AA26B3C_6FD9_4573_9A4A_B677E9F852D0__INCLUDED_)
