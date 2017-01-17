package tools;

import gui.PaintObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 * PaintPencil is a class for drawing a pencil.
 * 
 * @author Jake Knowles
 * @version 20 November 2015
 */
public class PaintPencil extends PaintObject {
    
    /** List to store the many points. */
    private List<Point> myPoints;
    
    /** Constructor. */
    public PaintPencil() {
        myPoints = new LinkedList<Point>();
    }
    
    /** setEnd sets the end point of the pencil line. */
    @Override
    public void setEnd(final Point theEnd) {
        myPoints.add(this.myEnd);
        this.myEnd = theEnd;
    }

    /** draw draws a bunch of small lines to make random "pencil" mode. */
    @Override
    public void draw(final Graphics theGraphic) {
        final Graphics2D g = (Graphics2D) theGraphic;
        g.setStroke(new BasicStroke(myThickness));
        theGraphic.setColor(myColor);
        
        if (myThickness == 0) {
            theGraphic.setColor(Color.WHITE);
        }
        
        if (myPoints.size() > 0) {
            theGraphic.drawLine(myStart.x, myStart.y, myPoints.get(0).x, myPoints.get(0).y);
            int i;
            for (i = 1; i < myPoints.size(); i++) {
                theGraphic.drawLine(myPoints.get(i - 1).x, myPoints.get(i - 1).y,
                    myPoints.get(i).x, myPoints.get(i).y);
            }
            theGraphic.drawLine(myPoints.get(i - 1).x, 
                                myPoints.get(i - 1).y, 
                                myEnd.x, myEnd.y);
        } else {
            theGraphic.drawLine(myStart.x, myStart.y, myEnd.x, myEnd.y);
        }
    }    
}