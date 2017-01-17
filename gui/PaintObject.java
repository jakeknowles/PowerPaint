package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * PaintObject is an abstract class for all the paint classes.
 * 
 * @author Jake Knowles
 * @version 20 November 2015
 */
public abstract class PaintObject {
   
    /** A color for the user to select. */
    protected Color myColor;
    
    /** A thickness for the user to select. */
    protected int myThickness;
    
    /** The starting point. */
    protected Point myStart;
    
    /** The ending point. */
    protected Point myEnd;
    
    /**
     * The End takes in the end of the point from the drawing.
     * 
     * @param theEnd theEnd is the end of the line.
     */
    public abstract void setEnd(Point theEnd);
    
    /**
     * Draw calls the draw method to draw.
     * 
     * @param theGraphic theGraphic is the graphics passed in.
     */
    public abstract void draw(Graphics theGraphic);
}