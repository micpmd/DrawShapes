package drawshapes.impl;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class TestShapes
{

    @Test
    public void testSquaresIntersect() {
        Shape s1 = new Square(Color.RED, 50, 50, 50);
        Shape s2 = new Square(Color.RED, 80, 80, 100);
        assertTrue(s1.intersects(s2));
    }

}
