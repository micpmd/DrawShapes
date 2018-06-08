package drawshapes.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circle implements Shape {
	private Point center;
	private Color color;
	private int diameter;
	private boolean isSelected;

	private BoundingBox bounds;

	public Circle(Color color, Point center, int diameter) {
		this.color = color;
		this.center = center;
		this.diameter = diameter;
		this.bounds = new BoundingBox((int) center.getX() - diameter / 2,
				(int) center.getX() + diameter / 2, (int) center.getY()
						- diameter / 2, (int) center.getY() + diameter / 2);
	}

	@Override
	public void draw(Graphics g) {
		if (!isSelected) {
			g.setColor(color);
		} else {
			g.setColor(Color.gray);
		}
		g.fillOval((int) center.getX() - diameter / 2, (int) center.getY()
				- diameter / 2, diameter, diameter);
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
		this.diameter = this.diameter * a;
	}
}
