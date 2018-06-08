package drawshapes.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Square implements Shape {
	private Point anchor;
	private int length;
	private Color color;
	private boolean selected;
	protected BoundingBox bounds;

	public Square(Color color, int centerX, int centerY, int length) {
		this.length = length;
		this.anchor = new Point(centerX - length / 2, centerY - length / 2);
		this.selected = false;
		this.color = color;
		this.bounds = new BoundingBox(centerX - length / 2, centerX + length
				/ 2, centerY - length / 2, centerY + length / 2);
	}

	@Override
	public void draw(Graphics g) {
		if (!selected) {
			g.setColor(color);
		} else {
			g.setColor(Color.gray);
		}
		g.fillRect((int) anchor.x, (int) anchor.y, length, length);
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
		this.length = this.length * a;
	}
}
