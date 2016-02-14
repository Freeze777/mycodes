#include "stdafx.h"
#include "ChildView.h"

namespace Liver {
#include "liver3d.cpp"
}

void CChildView::CreateLiver(void)
{
    m_liverDL = Liver::Gen3DObjectList();
}
