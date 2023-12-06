package a_Introductory;


import org.example.a_Introductory.Point;
import org.junit.Before;

import java.util.Optional;

import static org.junit.Assert.*;

public class PointTest {
	Point p1, p2, p3;
	
	@Before
	public void setUp() throws Exception {
		p1 = new Point(7, 9);
		p2 = new Point(-3, -30);
		p3 = new Point(-10, 3);
	}
	
	public void testAdd() {
		Point res1 = p1.add(p2);
		Point res2 = p1.add(p3);
		
		assertEquals(Optional.of(4), res1.x);
		assertEquals(Optional.of(-21), res1.y);
		assertEquals(Optional.of(-3), res2.x);
		assertEquals(Optional.of(12), res2.x);
	}
	
	public void testSub() {
		Point res1 = p1.sub(p2);
		Point res2 = p1.sub(p3);
		
		assertEquals(Optional.of(4), res1.x);
		assertEquals(Optional.of(-21), res1.y);
		assertEquals(Optional.of(-3), res2.x);
		assertEquals(Optional.of(12), res2.x);
	}

}
