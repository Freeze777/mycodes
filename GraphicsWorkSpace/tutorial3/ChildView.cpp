// ChildView.cpp : implementation of the CChildView class
//

#include "stdafx.h"
#include "lab.h"
#include "ChildView.h"
#include <cmath>

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

const GLdouble RED[3] = {0.8, 0.0, 0.0};
const GLdouble BLU[3] = {0.0, 0.0, 0.8};

/////////////////////////////////////////////////////////////////////////////
// CChildView

CChildView::CChildView()
{
    m_spinangle = 0.;
    m_spintimer = 0;

    m_liverDL = 0;

    m_camera.Set(20, 10, 50, 0, 0, 0, 0, 1, 0);
    m_camera.MouseMode(CGrCamera::PITCHYAW);

    SetDoubleBuffer(true);
}

CChildView::~CChildView()
{
}


BEGIN_MESSAGE_MAP(CChildView,COpenGLWnd )
	//{{AFX_MSG_MAP(CChildView)
	ON_COMMAND(ID_FILE_SAVEBMPFILE, OnFileSavebmpfile)
	ON_COMMAND(ID_LABSTUFF_SPIN, OnLabstuffSpin)
	ON_UPDATE_COMMAND_UI(ID_LABSTUFF_SPIN, OnUpdateLabstuffSpin)
	ON_WM_TIMER()
	//}}AFX_MSG_MAP
    ON_WM_LBUTTONDOWN()
    ON_WM_MOUSEMOVE()
END_MESSAGE_MAP()


/////////////////////////////////////////////////////////////////////////////
// CChildView message handlers

BOOL CChildView::PreCreateWindow(CREATESTRUCT& cs) 
{
	if (!COpenGLWnd::PreCreateWindow(cs))
		return FALSE;

	cs.dwExStyle |= WS_EX_CLIENTEDGE;
	cs.style &= ~WS_BORDER;
	cs.lpszClass = AfxRegisterWndClass(CS_HREDRAW|CS_VREDRAW|CS_DBLCLKS, 
		::LoadCursor(NULL, IDC_ARROW), HBRUSH(COLOR_WINDOW+1), NULL);

	return TRUE;
}




void CChildView::OnGLDraw(CDC *pDC)
{
    glClearColor(0.0f, 0.0f, 0.0f, 0.0f) ;
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    //
    // Set up the camera
    //

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();

    // Determine the screen size so we can determine the aspect ratio
    int width, height;
    GetSize(width, height);
    GLdouble aspectratio = GLdouble(width) / GLdouble(height);

    // Set the camera parameters
    gluPerspective(25.,           // Vertical field of view in degrees.
                    aspectratio,   // The aspect ratio.
                    10.,           // Near clipping
                    200.);         // Far clipping

    // Set the camera location
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

    m_camera.gluLookAt();

    //
    // Some standard parameters
    //

    // Enable depth test
    glEnable(GL_DEPTH_TEST);

    // Cull backfacing polygons
    glCullFace(GL_BACK);
    glEnable(GL_CULL_FACE);

    glPushMatrix();

    glRotated(m_spinangle, 1, .5, .7);
    Box(5, 5, 5);

    glPopMatrix();

    glFlush();
}

void CChildView::OnFileSavebmpfile() 
{
   OnSaveImage();	
}


void CChildView::OnLabstuffSpin() 
{
   if(m_spintimer)
   {
      KillTimer(m_spintimer);
      m_spintimer = 0;
   }
   else
   {
      m_spintimer = SetTimer(1, 50, NULL);
   }
}

void CChildView::OnUpdateLabstuffSpin(CCmdUI* pCmdUI) 
{
   pCmdUI->SetCheck(m_spintimer != 0);	
}

void CChildView::OnTimer(UINT nIDEvent) 
{
	m_spinangle += 10;
    Invalidate();

	COpenGLWnd ::OnTimer(nIDEvent);
}

//
//        Name : Quad()
// Description : Inline function for drawing a quadralateral.
//

inline void Quad(GLdouble *v1, GLdouble *v2, GLdouble *v3, GLdouble *v4)
{
    glBegin(GL_QUADS);
    glVertex3dv(v1);
    glVertex3dv(v2);
    glVertex3dv(v3);
    glVertex3dv(v4);
    glEnd();
}

//
//        Name : CChildView::Box()
// Description : Draw an arbitrary size box. p_x, p_y, and 
//               p_z are the height of the box. We'll use this is a 
//               common primitive.
//      Origin : The back corner is at 0, 0, 0, and the box 
//               is entirely in the positive octant.
//

void CChildView::Box(GLdouble p_x, GLdouble p_y, GLdouble p_z)
{
    GLdouble a[] = {0., 0., p_z};
    GLdouble b[] = {p_x, 0., p_z};
    GLdouble c[] = {p_x, p_y, p_z};
    GLdouble d[] = {0., p_y, p_z};
    GLdouble e[] = {0., 0., 0.};
    GLdouble f[] = {p_x, 0., 0.};
    GLdouble g[] = {p_x, p_y, 0.};
    GLdouble h[] = {0., p_y, 0.};

    // Front
    glBegin(GL_QUADS);
        glVertex3dv(a);
        glVertex3dv(b);
        glVertex3dv(c);
        glVertex3dv(d);
    glEnd();

    // Right
    glBegin(GL_QUADS);
        glVertex3dv(c);
        glVertex3dv(b);
        glVertex3dv(f);
        glVertex3dv(g);
    glEnd();

    // Back
    glBegin(GL_QUADS);
        glVertex3dv(h);
        glVertex3dv(g);
        glVertex3dv(f);
        glVertex3dv(e);
    glEnd();

    // Left
    glBegin(GL_QUADS);
        glVertex3dv(d);
        glVertex3dv(h);
        glVertex3dv(e);
        glVertex3dv(a);
    glEnd();

    // Top
    glBegin(GL_QUADS);
        glVertex3dv(d);
        glVertex3dv(c);
        glVertex3dv(g);
        glVertex3dv(h);
    glEnd();

    // Bottom
    glBegin(GL_QUADS);
        glVertex3dv(e);
        glVertex3dv(f);
        glVertex3dv(b);
        glVertex3dv(a);
    glEnd();
}


void CChildView::DrawLiver(void)
{
    if(m_liverDL == 0)
        CreateLiver();

    glPushMatrix();
    glTranslated(0, 0, -36);
    glCallList(m_liverDL);
    glPopMatrix();
}

void CChildView::OnLButtonDown(UINT nFlags, CPoint point)
{
    m_camera.MouseDown(point.x, point.y);

    COpenGLWnd ::OnLButtonDown(nFlags, point);
}

void CChildView::OnMouseMove(UINT nFlags, CPoint point)
{
    if(nFlags & MK_LBUTTON)
    {
        m_camera.MouseMove(point.x, point.y);
        Invalidate();
    }

    COpenGLWnd ::OnMouseMove(nFlags, point);
}
