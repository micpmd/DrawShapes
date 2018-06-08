package drawshapes.impl;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A scene of shapes.  Uses the Model-View-Controller (MVC) design pattern,
 * though note that model knows something about the view, as the draw() 
 * method both in Scene and in Shape uses the Graphics object. That's kind of sloppy,
 * but it also helps keep things simple.
 * 
 * @author jspacco
 *
 */
public class Scene implements Iterable<Shape>
{		
    private List<Shape> shapeList=new LinkedList<Shape>();
    
	private Rectangle selectionArea;
	
	private boolean showSelectionArea = false;
    
    /**
     * Draw all the shapes in the scene using the given Graphics object.
     * @param g
     */
    public void draw(Graphics g) {
    	
        for (Shape s : shapeList) {
            s.draw(g);
        }
        
        if(showSelectionArea){
    		selectionArea.draw(g);
    	}
    }
    
    public void selectArea(Rectangle r){
    	selectionArea = r;
    }
    
    public void showRectangle(boolean b){
    	this.showSelectionArea = b;
    }
    
    public Iterator<Shape> iterator() {
        return shapeList.iterator();
    }
    
    /**
     * Return a list of shapes that contain the given point.
     * @param point The point
     * @return A list of shapes that contain the given point.
     */
    public List<Shape> select(Point point)
    {
    	List<Shape> res = new LinkedList<Shape>();
    	for(Shape s: this.shapeList){
    		if(s.contains(point)){
    			res.add(s);
    		}
    	}
        return res;
    }
    
    /**
     * Return a list of shapes in the scene that intersect the given shape.
     * @param s The shape
     * @return A list of shapes intersecting the given shape.
     */
    public List<Shape> select(Shape s)
    {
    	List<Shape> res = new LinkedList<Shape>();
    	for(Shape i: this.shapeList){
    		if(s.intersects(i) || i.intersects(s)){
    			res.add(i);
    		}
    	}
        return res;
    }
    
    /**
     * Add a shape to the scene.  It will be rendered next time
     * the draw() method is invoked.
     * @param s
     */
    public void addShape(Shape s) {
        shapeList.add(s);
    }
    
    /**
     * Remove a given shape from the scene so that it will not be
     * rendered the next time the draw() method is invoked.
     * @param s
     */
    public void removeShape(Shape s){
        shapeList.remove(s);
    }
    
    /**
     * Remove a list of shapes from the given scene.
     * @param shapesToRemove
     */
    public void removeShapes(Collection<Shape> shapesToRemove) {
        shapeList.removeAll(shapesToRemove);
    }
    
    
}
