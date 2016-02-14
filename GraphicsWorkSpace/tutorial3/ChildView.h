// ChildView.h : interface of the CChildView class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_CHILDVIEW_H__50C8628F_112A_40D0_9AB2_53368988C69B__INCLUDED_)
#define AFX_CHILDVIEW_H__50C8628F_112A_40D0_9AB2_53368988C69B__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "graphics/OpenGLWnd.h"
#include "graphics/GrCamera.h"

/////////////////////////////////////////////////////////////////////////////
// CChildView window

class CChildView : public COpenGLWnd
{
// Construction
public:
	CChildView();

// Attributes
public:

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CChildView)
	protected:
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	//}}AFX_VIRTUAL

// Implementation
public:
	void OnGLDraw(CDC *pDC);
	virtual ~CChildView();

	// Generated message map functions
protected:
	//{{AFX_MSG(CChildView)
	afx_msg void OnFileSavebmpfile();
	afx_msg void OnLabstuffSpin();
	afx_msg void OnUpdateLabstuffSpin(CCmdUI* pCmdUI);
	afx_msg void OnTimer(UINT nIDEvent);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
private:
    void CreateLiver(void);
    void DrawLiver(void);

	UINT m_spintimer;
	double m_spinangle; 
    int  m_liverDL;

    void Box(GLdouble p_x, GLdouble p_y, GLdouble p_z);
    CGrCamera m_camera;

public:
    afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
    afx_msg void OnMouseMove(UINT nFlags, CPoint point);
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_CHILDVIEW_H__50C8628F_112A_40D0_9AB2_53368988C69B__INCLUDED_)
