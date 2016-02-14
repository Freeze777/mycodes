// lab.h : main header file for the LAB application
//

#if !defined(AFX_LAB_H__2D454DF8_B9AF_4540_8AAE_D01798004770__INCLUDED_)
#define AFX_LAB_H__2D454DF8_B9AF_4540_8AAE_D01798004770__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"       // main symbols

/////////////////////////////////////////////////////////////////////////////
// CLabApp:
// See lab.cpp for the implementation of this class
//

class CLabApp : public CWinApp
{
public:
	CLabApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CLabApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

public:
	//{{AFX_MSG(CLabApp)
	afx_msg void OnAppAbout();
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_LAB_H__2D454DF8_B9AF_4540_8AAE_D01798004770__INCLUDED_)
