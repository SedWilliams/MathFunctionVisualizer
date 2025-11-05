package com.desmosclone.mathFunctions;

import com.desmosclone.interfaces.*;

public abstract class AbstractBaseFunction implements Drawable {
    public abstract void drawObject();
    public abstract void updateObject();
    public abstract void domain();
    public abstract float calculation();
    public abstract void range();
}
