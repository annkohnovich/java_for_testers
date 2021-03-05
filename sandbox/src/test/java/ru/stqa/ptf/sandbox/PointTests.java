package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class PointTests {
    @Test
    public void testDistance () {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(-3, -4);
        Assert.assertEquals(p1.distance(p2),7.211102550927978);
    }
    @Test
    public void testDistance1 () {
        Point p1 = new Point(2, -1);
        Point p2 = new Point(-3, 4);
        Assert.assertEquals(p2.distance(p1),7.0710678118654755);
    }
    @Test
    public void testDistance2 () {
        Point p1 = new Point(0, 1);
        Point p2 = new Point(4, 0);
        Assert.assertEquals(p2.distance(p1),4.123105625617661);
    }
    @Test
    public void testDistance3 () {
        Point p1 = new Point(-1, 0);
        Point p2 = new Point(0, -3);
        Assert.assertEquals(p2.distance(p1),3.1622776601683795);
    }

}
