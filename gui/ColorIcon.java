package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;

/**
 * ColorIcon class provides the given color and size for the icons.
 * 
 * @author Jake Knowles
 * @version 20 November 2015
 */
public class ColorIcon implements Icon {

    /** A color for the user to select. */
    protected Color myColor;
    
    /** A size for the user to select. */
    private int mySize;

    /**
     * ColorIcon constructor takes the users size and color and stores them.
     * 
     * @param theSize theSize for the icon.
     * @param theColor theColor for the icon. 
     */
    public ColorIcon(final int theSize, final Color theColor) {
        this.mySize = theSize;
        this.myColor = theColor;
    }

    /**
     * Paint Icon uses "Graphics" and allows the icon to change.
     * 
     * @param theComponent theComponent is the given component.
     * @param theGraphics theGraphics are the selected choice.
     * @param theX theX is the given x coordinate.
     * @param theY theY is the given y coordinate.
     */
    @Override
    public void paintIcon(final Component theComponent, 
                          final Graphics theGraphics, 
                          final int theX, 
                          final int theY) {
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(myColor);
        g2d.fillOval(theX, theY, mySize, mySize);
    }

    /**
     * getIconHeight returns the icon height.
     * 
     * @return mySize mySize is the passed in size.
     */
    @Override
    public int getIconHeight() {
        return mySize;
    }
    
    /**
     * getIconWidth returns the icon width.
     * 
     * @return mySize mySize is the passed in size.
     */
    @Override
    public int getIconWidth() {
        return mySize;
    }
}