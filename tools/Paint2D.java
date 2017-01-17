package tools;

import gui.PaintObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Paint2D allows the rectangle and ellipse to be drawn all directions.
 * 
 * @author Jake Knowles
 * @version 20 November 2015
 */
public class Paint2D extends PaintObject {
    
    /** The decided paint mode. */
    private String myPaintMode;
    
    /**
     * Constructor for Paint2D.
     * 
     * @param thePaintMode thePaintMode is the chosen paintMode.
     */
    public Paint2D(final String thePaintMode) {
        this.myPaintMode = thePaintMode;
    }
    
    /** setEnd sets the end point. */
    @Override
    public void setEnd(final Point theEnd) {
        this.myEnd = theEnd;
    }

    /** draw takes care of the Rectangle & Ellipse
     * functionality to draw all directions.
     * Line & Pencil don't need it.
     */
    @Override
    public void draw(final Graphics theGraphic) {
        final Graphics2D g = (Graphics2D) theGraphic;
        g.setStroke(new BasicStroke(myThickness));
        theGraphic.setColor(myColor);
        if (myThickness == 0) {
            theGraphic.setColor(Color.WHITE);
        }
        
        switch (myPaintMode) {
            case "Rectangle":
                theGraphic.drawRect(Math.min(myStart.x, myEnd.x), 
                                    Math.min(myStart.y, myEnd.y),
                                    // Width
                                    Math.abs(myStart.x - myEnd.x),
                                    // Height
                                    Math.abs(myStart.y - myEnd.y)); 
                break;
                
            case "Ellipse":
                theGraphic.drawOval(Math.min(myStart.x, myEnd.x), 
                                    Math.min(myStart.y, myEnd.y),
                                    // Width
                                    Math.abs(myStart.x - myEnd.x),
                                    // Height
                                    Math.abs(myStart.y - myEnd.y)); 
                break;
                
            default:
                return;
        }
    }
}