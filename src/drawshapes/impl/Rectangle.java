package drawshapes.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import drawshapes.impl.Shape;

public class Rectangle implements Shape {
	private Point anchor;
	private int lengthX;
	private int lengthY;
	private Color color;
	private boolean selected;
	private BoundingBox bounds;

	public Rectangle(Color color, int centerX, int centerY, int lengthX,
			int lengthY) {
		this.lengthX = lengthX;
		this.lengthY = lengthY;
		this.anchor = new Point(centerX - lengthX / 2, centerY - lengthY / 2);
		this.selected = false;
		this.color = color;
		this.bounds = new BoundingBox(centerX - lengthX / 2, centerX + lengthX
				/ 2, centerY - lengthY / 2, centerY + lengthY / 2);
	}

	public Rectangle(Color color, Point topLeft, int lengthX, int lengthY) {
		this.lengthX = lengthX;
		this.lengthY = lengthY;
		this.anchor = topLeft;

		this.selected = false;
		this.color = color;
		this.bounds = new BoundingBox((int) topLeft.getX(),
				(int) topLeft.getX() + lengthX, (int) topLeft.getY(),
				(int) topLeft.getY() + lengthY);
	}

	@Override
	public void draw(Graphics g) {
		if (!selected) {
			g.setColor(color);
		} else {
			g.setColor(Color.gray);
		}
		g.fillRect((int) anchor.x, (int) anchor.y, lengthX, lengthY);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean contains(Point point) {
		return this.bounds.contains(point);
	}

	@Override
	public boolean intersects(Shape other) {
		return this.bounds.intersects(other.boundingBox());
	}

	@Override
	public boolean isSelected() {
		return this.selected;
	}

	@Override
	public void setSelected(boolean b) {
		this.selected = b;
	}

	@Override
	public Point getAnchorPoint() {
		return this.anchor;
	}

	@Override
	public void setAnchorPoint(Point p) {
		this.anchor = p;
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
