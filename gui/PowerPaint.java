/*
* TCSS 305 - Autumn 2015
* Assignment 5 - Power Paint
*/

package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tools.Paint2D;
import tools.PaintLine;
import tools.PaintPencil;

/**
 * PowerPaint is a GUI for drawing.
 * 
 * @author Jake Knowles
 * @version 20 November 2015
 */
public class PowerPaint extends JPanel {
    
    /** The color for the unselected button. */
    private static final Color DESELECTED_COLOR = new Color(236, 236, 236); 
    
    /** The color for the selected button. */
    private static final Color SELECTED_COLOR = new Color(111, 157, 183); 
    
    /** A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 1L;
    
    /** A initial frame width. */
    private static final int FRAME_WIDTH = 400;
    
    /** A initial frame height. */
    private static final int FRAME_HEIGHT = 300;
    
    /** A initial grid size. */
    private static final int GRID_SIZE = 10;
    
    /** Slider tick size. */
    private static final int TICK_SPACING = 20;
    
    /** Set color for color icon on menu bar. */
    private static final ColorIcon CUSTOM_ICON = new ColorIcon(10, Color.black);
    
    /** Set slider with tick spacing. */
    private static JSlider mySlider = new JSlider(0, TICK_SPACING, 1);
    
    /** Grid mode -- grid on panel. */
    private boolean myGridMode;
    
    /** The thickness of the line selected by user. */
    private int myThicknessMode;
    
    /** Paint mode. */
    private String myPaintMode;
    
    /** Selected paint color. */
    private Color myPaintColor;
    
    /** Object used for painting. */
    private PaintObject myCurrentObject;
    
    /** Object used for keeping track of items drawn (for undoing, redoing). */
    private List<PaintObject> myItemsDrawn = new ArrayList<>();
    
    /** Keeps track of current index. */
    private Integer myIndex;
       
    /** A list of objects used for painting. */
    private List<PaintObject> myPaintObjects = new LinkedList<PaintObject>();
    
    /** String Constant. */
    private final String myUndoStr = "Undo All Changes";
    
    /** String Constant. */
    private final String myAppStr = "PowerPaint";
    
    /** String Constant. */
    private final String myPencilStr = "Pencil";
    
    /** String Constant. */
    private final String myLineStr = "Line";
    
    /** String Constant. */
    private final String myRectangleStr = "Rectangle";
    
    /** String Constant. */
    private final String myEllipseStr = "Ellipse";
    
    /** String Constant. */
    private final String myAppIcon = "images/w.gif"; 
    
    /** String Constant. */
    private final String myUndoIcon = "images/undoicon.png";
    
    /** String Constant. */
    private final String myPencilIconWhite = "images/pencil_bw.gif";
    
    /** String Constant. */
    private final String myPencilIcon = "images/pencil.gif";
   
    /** String Constant. */
    private final String myLineIconWhite = "images/line_bw.gif";
    
    /** String Constant. */
    private final String myLineIcon = "images/line.gif";  
    
    /** String Constant. */
    private final String myRectangleIconWhite = "images/rectangle_bw.gif";
   
    /** String Constant. */
    private final String myRectangleIcon = "images/rectangle.gif";
    
    /** String Constant. */
    private final String myEllipseIconWhite = "images/ellipse_bw.gif";
    
    /** String Constant. */
    private final String myEllipseIcon = "images/ellipse.gif";
   
    /** Initial JFrame. */
    private JFrame myFrame;
    
    /** Undo button with icon. */
    private JMenuItem myUndoItem = new JMenuItem(myUndoStr, new ImageIcon(myUndoIcon));

    /** Pencil button with icon. */
    private JButton myPencilButton = new JButton(myPencilStr, 
                                       new ImageIcon(myPencilIconWhite));
    /** Line button with icon. */
    private JButton myLineButton = new JButton(myLineStr, 
                                                 new ImageIcon(myLineIconWhite));
    /** Rectangle button with icon. */
    private JButton myRectangleButton = new JButton(myRectangleStr, 
                                                 new ImageIcon(myRectangleIconWhite));
    /** Ellipse button with icon. */
    private JButton myEllipseButton = new JButton(myEllipseStr, 
                                                 new ImageIcon(myEllipseIconWhite));
    /** Pencil radio button with icon. */
    private JRadioButtonMenuItem myMenuPencilItem = new JRadioButtonMenuItem(myPencilStr, 
                                                    new ImageIcon(myPencilIconWhite));
    /** Line radio button with icon. */
    private JRadioButtonMenuItem myMenuLineItem = new JRadioButtonMenuItem(myLineStr,
                                                  new ImageIcon(myLineIconWhite));
    /** Rectangle radio button with icon. */
    private JRadioButtonMenuItem myMenuRectangleItem = new JRadioButtonMenuItem(myRectangleStr,
                                                       new ImageIcon(myRectangleIconWhite));
    /** Ellipse radio button with icon. */
    private JRadioButtonMenuItem myMenuEllipseItem = new JRadioButtonMenuItem(myEllipseStr,
                                                     new ImageIcon(myEllipseIconWhite));   
    /** Main menu bar. */
    private JMenuBar myMenuBar = new JMenuBar();
    
    /** Main tool bar. */
    private JToolBar myToolBar = new JToolBar();
    
    /** Constructor. */
    public PowerPaint() {
        myGridMode = false;
        myThicknessMode = 1;
        myPaintMode = myPencilStr;
        myPaintColor = Color.BLACK;
        myCurrentObject = null;
        setupMenu();
        setupToolBar();
        setupFrame();
        myIndex = 0;
        helperMethod();
    }
    
    /** Helper method to eliminate check-style error. */
    private void helperMethod() {
        Cursor cursor = Cursor.getDefaultCursor();
        cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
        setCursor(cursor); 
    }
    
    
    /** Set up method that sets up the power paint.*/
    private void setupMenu() {
        final JMenu fileMenu = setupFileMenu();
        final JMenu optionsMenu = setupOptionsMenu();
        final JMenu toolMenu = setupToolsMenu();
 
        /* "About" menu */
        final JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(new AboutListener());
        
        /* "Help" menu */
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.add(aboutItem);
          
        myMenuBar.add(fileMenu);
        myMenuBar.add(optionsMenu);
        myMenuBar.add(toolMenu);
        myMenuBar.add(helpMenu);   
       
    }
    
    /** Sets up draggable tool bar.*/
    private void setupToolBar() {
        myPencilButton.setSelectedIcon(new ImageIcon(myPencilIcon));
        myMenuPencilItem.setSelectedIcon(new ImageIcon(myPencilIcon));
        enableButton(myPencilButton, myMenuPencilItem);
        myUndoItem.setEnabled(false);
        myPencilButton.setSelected(true);
        myPencilButton.setMnemonic(KeyEvent.VK_P);

        myLineButton.setMnemonic(KeyEvent.VK_L);
        myLineButton.setSelectedIcon(new ImageIcon(myLineIcon));
        myMenuLineItem.setSelectedIcon(new ImageIcon(myLineIcon));
        myLineButton.setSelected(false);
        
        myRectangleButton.setMnemonic(KeyEvent.VK_R);
        myRectangleButton.setSelectedIcon(new ImageIcon(myRectangleIcon));
        myMenuRectangleItem.setSelectedIcon(new ImageIcon(myRectangleIcon));
        myRectangleButton.setSelected(false);
      
        myEllipseButton.setMnemonic(KeyEvent.VK_E);
        myEllipseButton.setSelectedIcon(new ImageIcon(myEllipseIcon));
        myMenuEllipseItem.setSelectedIcon(new ImageIcon(myEllipseIcon));
        myEllipseButton.setSelected(false);
        
        final ButtonListener buttonListener = new ButtonListener();
        myPencilButton.addActionListener(buttonListener);
        myLineButton.addActionListener(buttonListener);
        myRectangleButton.addActionListener(buttonListener);
        myEllipseButton.addActionListener(buttonListener);   

        myToolBar.add(myPencilButton);      
        myToolBar.add(myLineButton);
        myToolBar.add(myRectangleButton);
        myToolBar.add(myEllipseButton);
    }
    
    /**
     * Sets up the file menu.
     * 
     * @return fileMenu fileMenu is the created file menu.
     */
    private JMenu setupFileMenu() {
        myUndoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));
        myUndoItem.setMnemonic(KeyEvent.VK_U);
        myUndoItem.addActionListener(new UndoListener()); 
        
        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.addActionListener(new ExitListener());
        
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(myUndoItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        return fileMenu;
    }
    
    /**
     * Sets up option menu.
     * 
     * @return optionsMenu optionsMenu is the options menu. 
     */
    private JMenu setupOptionsMenu() {
        final JCheckBoxMenuItem gridItem = new JCheckBoxMenuItem("Grid");
        gridItem.setMnemonic(KeyEvent.VK_G);
        gridItem.setSelected(false);
        gridItem.addActionListener(new GridListener());
        
        mySlider.setMinorTickSpacing(1);
        mySlider.setMajorTickSpacing(TICK_SPACING);
        mySlider.setPaintTicks(true);
        mySlider.setSnapToTicks(true);
        mySlider.setPaintTrack(false);
        mySlider.setPaintLabels(true);
        mySlider.addChangeListener(new SliderListener());
        
        final JMenu thicknessMenu = new JMenu("Thickness");
        thicknessMenu.setMnemonic(KeyEvent.VK_T);
        thicknessMenu.add(mySlider);
        
        final JMenuItem colorButton = new JMenuItem("Color...", CUSTOM_ICON);
        colorButton.setMnemonic(KeyEvent.VK_C);
        colorButton.addActionListener(new ColorListener());
        
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        optionsMenu.add(gridItem);
        optionsMenu.addSeparator();
        optionsMenu.add(thicknessMenu);
        optionsMenu.addSeparator();
        optionsMenu.add(colorButton);
        
        return optionsMenu;
    }
    
    /**
     * Sets up tools menu.
     * 
     * @return toolsMenu toolsMenu is the tools menu.
     */
    final JMenu setupToolsMenu() {       
        myMenuPencilItem.setMnemonic(KeyEvent.VK_P);
        myMenuPencilItem.addActionListener(new PencilMenuItemListener()); 
        
        myMenuLineItem.setMnemonic(KeyEvent.VK_L);
        myMenuLineItem.addActionListener(new LineMenuItemListener());  
        
        myMenuRectangleItem.setMnemonic(KeyEvent.VK_R);
        myMenuRectangleItem.addActionListener(new RectangleMenuItemListener());  
        
        myMenuEllipseItem.setMnemonic(KeyEvent.VK_E);
        myMenuEllipseItem.addActionListener(new EllipseMenuItemListener());  
         
        final JMenu toolMenu = new JMenu("Tools");
        toolMenu.setMnemonic(KeyEvent.VK_T);
        toolMenu.add(myMenuPencilItem);
        toolMenu.add(myMenuLineItem);
        toolMenu.add(myMenuRectangleItem);
        toolMenu.add(myMenuEllipseItem);
                
        return toolMenu;
    }
    
    /** Sets up frame and all the frames starting requirements. */
    private void setupFrame() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(new PaintListener());
        this.addMouseMotionListener(new PaintMotionListener());
        myFrame = new JFrame(myAppStr);
        myFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        final ImageIcon img = new ImageIcon(myAppIcon);
        myFrame.setIconImage(img.getImage());
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setJMenuBar(myMenuBar);
        myFrame.getContentPane().setLayout(new BorderLayout());
        myFrame.getContentPane().add(myToolBar, BorderLayout.SOUTH);
        myToolBar.setFloatable(true);
        myFrame.add(this, BorderLayout.CENTER);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true); 
    }
    
    /**
     * Sets the button to link with the chosen color.
     * 
     * @param theButton theButton is the passed in button clicked.
     * @param theRadioButton theRadioButton is the passed in radio button clicked.
     */
    private void enableButton(final JButton theButton,
                              final JRadioButtonMenuItem theRadioButton) {
        theButton.setBackground(SELECTED_COLOR);
        theButton.setSelected(true);
        theRadioButton.setSelected(true);   
    }
    
    /** Disables drawing buttons. */
    private void disableAllButtons() {
        myPencilButton.setBackground(DESELECTED_COLOR);
        myPencilButton.setSelected(false);
        myMenuPencilItem.setSelected(false);
        myLineButton.setBackground(DESELECTED_COLOR);
        myLineButton.setSelected(false);
        myMenuLineItem.setSelected(false);
        myRectangleButton.setBackground(DESELECTED_COLOR);
        myRectangleButton.setSelected(false);
        myMenuRectangleItem.setSelected(false);
        myEllipseButton.setBackground(DESELECTED_COLOR); 
        myEllipseButton.setSelected(false);
        myMenuEllipseItem.setSelected(false);
    }
    
    /**
     * Overrides the paintComponent from JLabel which extends JComponent.
     * 
     * @param theGraphic theGraphic allows painting to exist.
     */
    @Override
    public void paintComponent(final Graphics theGraphic) {
        super.paintComponent(theGraphic);
        //to have the painting appear under the grid
        for (PaintObject p: myPaintObjects) {
            p.draw(theGraphic);
            myIndex = myIndex + 1;
            myItemsDrawn.add(p);
            if (myCurrentObject != null) {
                myCurrentObject.draw(theGraphic);
            }   
        }
        if (myGridMode) {
            final Dimension d = this.getSize();
            final Graphics2D g2 = (Graphics2D) theGraphic;
            g2.setStroke(new BasicStroke(1));
            theGraphic.setColor(Color.BLACK);
            //draw grid
            for (int x = GRID_SIZE - 1; x < d.width; x += GRID_SIZE) {
                theGraphic.drawLine(x, 0, x, d.height);
            }
            for (int y = GRID_SIZE - 1; y < d.height; y += GRID_SIZE) {
                theGraphic.drawLine(0, y, d.width, y);
            }
        }

    }
    
    /** 
     * Paint listener is an inner class that implements Mouse Listener.
     * Paint Listener listens to the paint tools.
     */
    private class PaintListener implements MouseListener {
        
        /** Mouse pressed is overridden to allow my tools to be listened to. */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            PaintObject paintObject;
            
            myUndoItem.setEnabled(true);
            switch (myPaintMode) {
                case myPencilStr:
                    paintObject = new PaintPencil();
                    break;
                case myLineStr:
                    paintObject = new PaintLine();
                    break;
                case myRectangleStr:
                case myEllipseStr:
                    paintObject = new Paint2D(myPaintMode);
                    break;
                default:
                    return;
            }
                        
            paintObject.myColor = myPaintColor;
            paintObject.myThickness = myThicknessMode;
            paintObject.myStart = new Point(theEvent.getX(), theEvent.getY());
            paintObject.myEnd = paintObject.myStart;
            myCurrentObject = paintObject;
            myPaintObjects.add(myCurrentObject);
            myItemsDrawn.add(myCurrentObject);
            repaint();
        }
        
        /** Mouse released is overridden to allow my tools to be listened to. */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (myCurrentObject != null) {
                myCurrentObject.setEnd(new Point(theEvent.getX(), theEvent.getY()));
                myPaintObjects.add(myCurrentObject);
                myItemsDrawn.add(myCurrentObject);
                myCurrentObject = null;
                repaint();
            }
        }
        /** Not needed. */
        @Override
        public void mouseClicked(final MouseEvent theEvent) { }
        /** Not needed. */       
        @Override
        public void mouseEntered(final MouseEvent theEvent) { }
        /** Not needed. */        
        @Override
        public void mouseExited(final MouseEvent theEvent) { }
        
    }
    
    /** PaintMotionListener is an inner class to keep track of the painting. */
    private class PaintMotionListener implements MouseMotionListener {
        
        /** Mouse dragged is overridden to allow my tools to be listened to. */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            if (myCurrentObject != null) {
                myCurrentObject.setEnd(new Point(theEvent.getX(), theEvent.getY()));
                repaint();
            }
        }
        /** Not needed. */
        @Override
        public void mouseMoved(final MouseEvent theEvent) { }
        
    }
    
    /** UndoListener is a class that allows undo to work. */
    private class UndoListener implements ActionListener {
       
        /** Action performed is overridden to allow undo to do something. */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            final int size = myItemsDrawn.size() - 1;
            myItemsDrawn.remove(size);

            myPaintObjects.clear();
            repaint();
            
          // if there are no more items to undo, disable menu item
            myUndoItem.setEnabled(false);
        }
    }

    /** ExitListener is a class that allows exit to work. */
    private class ExitListener implements ActionListener {
        
        /** Action performed is overridden to allow exit to exit the program. */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            System.exit(0);
        }
    }
    
    /** GridListener is a class that allows grid to work. */
    private class GridListener implements ActionListener {
        
        /** Action performed is overridden to allow grid to appear. */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myGridMode = !myGridMode;
            repaint();
        }
    }
    
    /** SliderListener is a class that listens to the sliders. */
    private class SliderListener implements ChangeListener {
        
        /** Action performed is overridden to allow sliders to do something. 
         * 
         * @param theEvent theEvent is the given change.
         */
        public void stateChanged(final ChangeEvent theEvent) {
            myThicknessMode = mySlider.getValue();
        }
    }
    
    /** AboutListener is a class that allows about to work. */
    private class AboutListener implements ActionListener {
        
        /** Action performed is overridden to allow about to have a pop-up. */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            JOptionPane.showMessageDialog(myFrame, "TCSS 305 PowerPaint, "
                            + "Autumn 2015 \n by Jake Knowles",
                "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /** ColorListener is a class that allows color to work. */
    private class ColorListener implements ActionListener {
        
        /** Action performed is overridden to allow color to do something. */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            final Color color = JColorChooser.showDialog(myFrame, "Pick Wisely!", 
                                                         myPaintColor);
            if (color != null) {
                myPaintColor = color;
                CUSTOM_ICON.myColor = color;
            }
        }
    }
    
    /** ButtonListener is a class that allows button to work. */
    private class ButtonListener implements ActionListener {
        
        /** Action performed is overridden to allow the buttons to work for each
         * of the tools, enabling and disabling.
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myPaintMode = theEvent.getActionCommand();
            
            switch (myPaintMode) {
                case myPencilStr:
                    disableAllButtons();
                    enableButton(myPencilButton, myMenuPencilItem);
                    break;
                case myLineStr:
                    disableAllButtons();
                    enableButton(myLineButton, myMenuLineItem);
                    break;
                case myRectangleStr:
                    disableAllButtons();
                    enableButton(myRectangleButton, myMenuRectangleItem);
                    break;
                case myEllipseStr:
                    disableAllButtons();
                    enableButton(myEllipseButton, myMenuEllipseItem);
                    break;
                default:
                    return;
            }
        }
    }
    
    /** PencilMenuItemListener is a class that allows the pencil item to work. */
    private class PencilMenuItemListener implements ActionListener {
        
        /** Action performed is overridden to allow pencil to do something. */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myPaintMode = myPencilStr;
            disableAllButtons();
            enableButton(myPencilButton, myMenuPencilItem);
        }
    }
    
    /** LineMenuListener is a class that allows line to draw. */
    private class LineMenuItemListener implements ActionListener {
        
        /** Action performed is overridden to allow line to do something. */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myPaintMode = myLineStr;
            disableAllButtons();
            enableButton(myLineButton, myMenuLineItem);
        }
    }
    
    /** RectangleMenuItemListener is a class that allows rectangle to work. */
    private class RectangleMenuItemListener implements ActionListener {
        
        /** Action performed is overridden to allow rectangle to do something. */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myPaintMode = myRectangleStr;
            disableAllButtons();
            enableButton(myRectangleButton, myMenuRectangleItem);
        }
    }
    
    /** EllipseMenuItemListener is a class that allows ellipse to work. */
    private class EllipseMenuItemListener implements ActionListener {
        
        /** Action performed is overridden to allow ellipse to do something. */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myPaintMode = myEllipseStr;
            disableAllButtons();
            enableButton(myEllipseButton, myMenuEllipseItem);
        }
    }
}