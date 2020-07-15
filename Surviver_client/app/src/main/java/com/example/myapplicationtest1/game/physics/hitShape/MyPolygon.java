package com.example.myapplicationtest1.game.physics.hitShape;

import android.graphics.Paint;

import com.example.myapplicationtest1.game.core.ErrorCounter;
import com.example.myapplicationtest1.game.physics.HasPoint;
import com.example.myapplicationtest1.game.physics.Point;

import java.util.Collection;

//TODO: polygon in android

public class MyPolygon extends HitShape {
	private static final long serialVersionUID = 421774100779183845L;

	//protected Polygon polygon;
	private Point points[];
	
	private static final Object/*Polygon*/ setPolygon(Point[] points) {
		final int[] px = new int[points.length], py = new int[points.length];
		for(int i = 0;i < points.length;++i) {
			px[i] = points[i].intX();
			py[i] = points[i].intY();
		}
		//return new Polygon(px, py, points.length);
		return null;
	}
	public MyPolygon(HasPoint owner, Point[] points) {
		super(owner);
		//polygon = setPolygon(this.points = points);
	}
	public MyPolygon(HasPoint owner, Collection<Point> points) {
		super(owner);
		//polygon = setPolygon(this.points = points.toArray(new Point.IntPoint[0]));
	}
	public MyPolygon(HasPoint owner, int[] pointX, int[] pointY) {
		super(owner);
		if(pointX.length != pointY.length)
			ErrorCounter.put("Terrain's constructer called Illegally: pointX.length = " + pointX.length + ", pointY.length = " + pointY.length);
		//polygon = new Polygon(pointX, pointY, Math.min(pointX.length, pointY.length));
		points = new Point[pointX.length];
		for(int i = 0;i < pointX.length;++i)
			points[i] = new Point.IntPoint(pointX[i], pointY[i]);
	}
	@Override
	public boolean intersects(HitShape shape) {
		return super.boundingBoxIntersects(shape);
//		final Point P2 = shape.point();
//		if(shape instanceof RectShape) {
//			final RectShape RECT = (RectShape)shape;
//			return polygon.intersects(P2.intX(), P2.intY(), RECT.width, RECT.height);
//		}else if(shape instanceof Square) {
//			final int SIDE = ((Square)shape).side;
//			return polygon.intersects(P2.intX(), P2.intY(), SIDE, SIDE);
//		}else if(shape instanceof Circle) {
//			final int SIDE = ((Circle)shape).radius*2;
//			return polygon.intersects(P2.intX(), P2.intY(), SIDE, SIDE);
//		}else {
//			System.out.println("unhandled type: " + this.getClass().getName() + " against " + shape.getClass().getName());
//		}
//		return false;
	}
	@Override
	public boolean intersectsDot(int x, int y) {
		return boundingBoxIntersectsDot(x, y);
		//return polygon.contains(x - point().intX(), y - point().intY());
	}
	@Override
	public boolean intersectsLine(int x1, int y1, int x2, int y2) {
		return boundingBoxIntersectsLine(x1, y1, x2, y2);
//		final Line2D line = new Line2D.Double(x1 - point().intX(), y1 - point().intY(), x2 - point().intX(), y2 - point().intY());
//		if(line.intersectsLine(points[points.length - 1].intX(), points[points.length - 1].intY(), points[0].intX(), points[0].intY()))
//			return true;
//		for(int i = 0;i < points.length - 1;i++) {
//			if(line.intersectsLine(points[i].intX(), points[i].intY(), points[i + 1].intX(), points[i + 1].intY()))
//				return true;
//		}
//		return false;
	}
	@Override
	public void draw(Paint paint) {
//		final Graphics2D G2 = GHQ.getG2D(color, stroke);
//		final int TX = point().intX(), TY = point().intY();
//		G2.translate(TX, TY);
//		G2.draw(polygon);
//		G2.translate(-TX, -TY);
	}
	//information
	@Override
	public HitShape clone(HasPoint newOwner) {
		return new MyPolygon(newOwner, points);
	}
	public Point[] getPoints() {
		return points;
	}
	@Override
	public int width() {
		return 0;
		//return polygon.getBounds().width;
	}
	@Override
	public int height() {
		return 0;
		//return polygon.getBounds().height;
	}
}