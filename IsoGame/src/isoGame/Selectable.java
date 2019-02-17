package isoGame;

import java.awt.Image;

public interface Selectable {
	
	abstract Image getSelectImage();

	abstract void setSelected(boolean selected);
	
	abstract boolean isSelected();
}