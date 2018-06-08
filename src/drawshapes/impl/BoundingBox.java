package drawshapes.impl;

import java.awt.Point;

public class BoundingBox {
	// check if we contain a point and if we intersect a shape
	private int left;
	private int right;
	private int top;
	private int bottom;
	private Point[] corners;

	public BoundingBox(int left, int right, int top, int bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		corners = new Point[4];
		corners[0] = new Point(left, top);
		corners[1] = new Point(right, top);
		corners[2] = new Point(right, bottom);
		corners[3] = new Point(left, bottom);
	}

	public boolean intersects(BoundingBox other) {
		for (Point p : this.corners) {
			if (other.contains(p)) {
				return true;
			}
		}
		for (Point p : other.corners) {
			if (this.contains(p)) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(Point p) {
		return this.left <= p.x && p.x <= this.right && this.top <= p.y
				&& p.y <= this.bottom;
	}
	
	public String toString(){
		return String.format("Left = %d, Right = %d, Top = %d, Bottom = %d", left, right, top, bottom);
	}

}
