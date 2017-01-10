package ie.gmit.sw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.JComponent;

//- Not needed right now.

/**
 * A Custom swing GUI control.
 * 
 * Not currently used or changed from base code.
 */
public class CustomControl extends JComponent implements MouseMotionListener, ActionListener{
	private static final long serialVersionUID = 777L;
	private static final int MAX_POINTS = 50;
	private Deque<Point> points = new LinkedList<Point>();
	

	public CustomControl(Dimension size) {
		super();
		setSize(size.width, size.height);
		enableInputMethods(true);   
		addMouseMotionListener(this);
		setFocusable(true);
	}//- End of CustomControl()

	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}//- End of getPreferredSize

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}//- End of getMaximumSize

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}//- End of getMinimumSize
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
	        
        for (Point p : points){
        	g.setColor(new Color((int)(Math.random() * 0x1000000)));
        	g.fillOval(p.getX(), p.getY(), Point.POINT_WIDTH, Point.POINT_HEIGHT);
        }//- End of for
        repaint();
	}//- End of paintComponent

	
	public void mouseMoved(MouseEvent e) {
		if (points.size() == MAX_POINTS) points.removeFirst();
		points.add(new Point(e.getX(), e.getY()));
	}//- End of mouseMoved
	
	public void mouseDragged(MouseEvent e) {
		
	}//- End of mouseDragged
	
	public void actionPerformed(ActionEvent ev){

	}//- End of actionPerformed

	private class Point{
		private static final int POINT_WIDTH = 7;
		private static final int POINT_HEIGHT = 7;
		private int x;
		private int y;

		private Point(int x, int y) {
			this.x = x;
			this.y = y;
		}//- End of Point()

		public int getX() {
			return x;
		}//- End of getX

		public int getY() {
			return y;
		}//- End of getY
	}//- End of Point
}//- End of CustomControl
