package drawshapes.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.*;
import java.util.*;
import java.util.List;

@SuppressWarnings("serial")
public class DrawShapes extends JFrame {
	private enum ShapeType {
		SQUARE, CIRCLE, RECTANGLE, OVAL
	}

	private DrawShapesPanel shapePanel;
	private Scene scene;
	private ShapeType shapeType = ShapeType.SQUARE;
	private Color color = Color.RED;

	private boolean isPressed = false;
	private int x;
	private int y;
	private Point SelectionAnchor;

	private List<Shape> selectedShapes;

	public DrawShapes(int width, int height) {
		setTitle("Draw Shapes!");
		scene = new Scene();

		// create our canvas, add to this frame's content pane
		shapePanel = new DrawShapesPanel(width, height, scene);
		this.getContentPane().add(shapePanel, BorderLayout.CENTER);
		this.setResizable(false);
		this.pack();
		this.setLocation(100, 100);

		// Add key and mouse listeners to our canvas
		initializeMouseListener();
		initializeKeyListener();

		// initialize the menu options
		initializeMenu();

		// Handle closing the window.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private void initializeMouseListener() {

		shapePanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO implement this method

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.printf("mouse drag! (%d, %d)\n", e.getX(), e.getY());
				if (isPressed) {
					Rectangle selection = new Rectangle(
							new Color(1, 1, 1, 0.7f), SelectionAnchor, e.getX()-x, e.getY()-y);

					scene.selectArea(selection);

					scene.showRectangle(isPressed);

					selectedShapes = scene.select(selection);

					if (!selectedShapes.isEmpty()) {
						for (Shape s : selectedShapes) {
							s.setSelected(true);
						}
					}

					repaint();
				}
			}
		});

		shapePanel.addMouseListener(new MouseListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent
			 * )
			 */
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					System.out.printf("Left click at (%d, %d)\n", e.getX(),
							e.getY());
					if (shapeType == ShapeType.SQUARE) {
						scene.addShape(new Square(color, e.getX(), e.getY(),
								100));
					} else if (shapeType == ShapeType.CIRCLE) {
						scene.addShape(new Circle(color, e.getPoint(), 100));
					} else if (shapeType == ShapeType.RECTANGLE) {
						scene.addShape(new Rectangle(color, e.getX(), e.getY(),
								120, 80));
					} else if (shapeType == ShapeType.OVAL) {
						scene.addShape(new Oval(color, e.getPoint(), 120, 80));
					}

				} else if (e.getButton() == MouseEvent.BUTTON3) {
					// handle right-click
					// right-click is button #3, middle button (if there is one)
					// is button #2
					System.out.printf("Right click at (%d, %d)\n", e.getX(),
							e.getY());

					Point p = new Point(e.getX(), e.getY());

					selectedShapes = scene.select(p);

					if (!selectedShapes.isEmpty()) {
						for (Shape s : selectedShapes) {
							s.setSelected(true);
						}
					} else {
						for (Shape s : scene) {
							s.setSelected(false);
							s.setColor(color);
						}
					}

				} else if (e.getButton() == MouseEvent.BUTTON2) {
					System.out.printf("Middle click at (%d, %d)\n", e.getX(),
							e.getY());
				}
				repaint();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent
			 * )
			 */
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent
			 * )
			 */
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent
			 * )
			 */
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					SelectionAnchor = e.getPoint();
					x = e.getX();
					y = e.getY();
					isPressed = true;
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent
			 * )
			 */
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					isPressed = false;
					scene.showRectangle(isPressed);
					repaint();
				}
			}

		});
	}

	/**
	 * Initialize the menu options
	 */
	private void initializeMenu() {
		// menu bar
		JMenuBar menuBar = new JMenuBar();

		// file menu
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		// load
		JMenuItem loadItem = new JMenuItem("Load");
		fileMenu.add(loadItem);
		loadItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
			}
		});
		// save
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
			}
		});
		fileMenu.addSeparator();
		// edit
		JMenuItem itemExit = new JMenuItem("Exit");
		fileMenu.add(itemExit);
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = e.getActionCommand();
				System.out.println(text);
				System.exit(0);
			}
		});

		// color menu
		JMenu colorMenu = new JMenu("Color");
		menuBar.add(colorMenu);

		// red color
		JMenuItem redColorItem = new JMenuItem("Red");
		colorMenu.add(redColorItem);
		redColorItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = e.getActionCommand();
				System.out.println(text);
				// change the color instance variable to red
				color = Color.RED;
			}
		});

		// blue color
		JMenuItem blueColorItem = new JMenuItem("Blue");
		colorMenu.add(blueColorItem);
		blueColorItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = e.getActionCommand();
				System.out.println(text);
				// change the color instance variable to blue
				color = Color.BLUE;
			}
		});

		// shape menu
		JMenu shapeMenu = new JMenu("Shape");
		menuBar.add(shapeMenu);

		// square
		JMenuItem squareItem = new JMenuItem("Square");
		shapeMenu.add(squareItem);
		squareItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Square");
				shapeType = ShapeType.SQUARE;
			}
		});

		// circle
		JMenuItem circleItem = new JMenuItem("Circle");
		shapeMenu.add(circleItem);
		circleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Circle");
				shapeType = ShapeType.CIRCLE;
			}
		});

		// Rectangle
		JMenuItem rectangleItem = new JMenuItem("Rectangle");
		shapeMenu.add(rectangleItem);
		rectangleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Rectangle");
				shapeType = ShapeType.RECTANGLE;
			}
		});

		// Oval
		JMenuItem ovalItem = new JMenuItem("Oval");
		shapeMenu.add(ovalItem);
		ovalItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Oval");
				shapeType = ShapeType.OVAL;
			}
		});

		// operation mode menu
		JMenu operationModeMenu = new JMenu("Options");
		menuBar.add(operationModeMenu);

		JMenuItem colorItem = new JMenuItem("Change color");
		operationModeMenu.add(colorItem);
		colorItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = e.getActionCommand();
				System.out.println(text);
				for (Shape s : scene) {
					if (s.isSelected()) {
						s.setSelected(false);
						s.setColor(Color.GREEN);
					}
				}
				repaint();
			}
		});

		JMenuItem deleteItem = new JMenuItem("Delete shape");
		operationModeMenu.add(deleteItem);
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = e.getActionCommand();
				System.out.println(text);
				scene.removeShapes(selectedShapes);
				repaint();
			}
		});

		JMenuItem operation_3 = new JMenuItem("Increase size");
		operationModeMenu.add(operation_3);
		operation_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = e.getActionCommand();
				System.out.println(text);
				for (Shape s : scene) {
					if (s.isSelected()) {
						s.resize(2);
					}
				}
				repaint();
			}
		});

		// set the menu bar for this frame
		this.setJMenuBar(menuBar);
	}

	/**
	 * Initialize the keyboard listener.
	 */
	private void initializeKeyListener() {
		shapePanel.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				System.out.println("key typed: " + e.getKeyChar());
			}

			public void keyReleased(KeyEvent e) {
				// TODO implement this method if you need it
			}

			public void keyTyped(KeyEvent e) {
				// TODO implement this method if you need it
			}
		});
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DrawShapes shapes = new DrawShapes(700, 600);
		shapes.setVisible(true);
	}

}
