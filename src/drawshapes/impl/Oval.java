package drawshapes.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Oval implements Shape {

	private Point center;
	private Color color;
	private int lengthX;
	private int lengthY;
	private boolean isSelected;

	private BoundingBox bounds;

	public Oval(Color color, Point center, int lengthX, int lengthY) {
		this.color = color;
		this.center = center;
		this.lengthX = lengthX;
		this.lengthY = lengthY;
		this.bounds = new BoundingBox((int) center.getX() - lengthX / 2,
				(int) center.getX() + lengthX / 2, (int) center.getY()
						- lengthY / 2, (int) center.getY() + lengthY / 2);
	}

	@Override
	public void draw(Graphics g) {
		if (!isSelected) {
			g.setColor(color);
		} else {
			g.setColor(Color.gray);
		}
		g.fillOval((int) center.getX() - lengthX / 2, (int) center.getY()
				- lengthY / 2, lengthX, lengthY);
	}

	@Override
	public boolean intersects(Shape other) {
		return this.bounds.intersects(other.boundingBox());
	}

	@Override
	public boolean contains(Point point) {
		return this.bounds.contains(point);
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean isSelected() {
		return this.isSelected;
	}

	@Override
	public void setSelected(boolean b) {
		this.isSelected = b;
	}

	@Override
	public Point getAnchorPoint() {
		return this.center;
	}

	@Override
	public void setAnchorPoint(Point p) {
		this.center = p;
	}

	@Override
	public BoundingBox boundingBox() {
		return this.bounds;
	}

	@Override
	public void resize(int a) {
		this.lengthX = this.lengthX * a;
		this.lengthY = this.lengthY * a;
	}
}
