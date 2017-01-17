package tools;

import gui.PaintObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;


/**
 * PaintLine is a class for drawing a line.
 * 
 * @author Jake Knowles
 * @version 20 November 2015
 */
public class PaintLine extends PaintObject { 
    
    /** setEnd sets the end point of the line. */
    @Override
    public void setEnd(final Point theEnd) {
        this.myEnd = theEnd;
    }

    /** draw draws a line. */
    @Override
    public void draw(final Graphics theGraphic) {
        final Graphics2D g = (Graphics2D) theGraphic;
        g.setStroke(new BasicStroke(myThickness));
        theGraphic.setColor(myColor);
        
        if (myThickness == 0) {
            theGraphic.setColor(Color.WHITE);
        }
        
        theGraphic.drawLine(myStart.x, myStart.y, myEnd.x, myEnd.y);
    }
}
