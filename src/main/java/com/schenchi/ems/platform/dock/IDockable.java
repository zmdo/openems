package com.schenchi.ems.platform.dock;

import com.schenchi.ems.platform.utils.INodeContent;
import com.schenchi.ems.platform.utils.ISortable;
import com.tricolorfire.labfx.dockFX.DockNode;
import com.tricolorfire.labfx.dockFX.DockPos;

public interface IDockable extends INodeContent,ISortable {
	
	String getId();
	
	DockPos getPos();
	
	DockNode getContent();

}
