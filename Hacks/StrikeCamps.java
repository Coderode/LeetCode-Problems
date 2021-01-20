import java.util.*;
//representing the camp center
class Point {
	//center coordinate with radius of the camp
	int x, y, radius;

	Point(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius; /* some data*/ 
	}
}
/* Using two points of Rectangular (Top,Left) & (Bottom , Right)*/
class Boundry {
	public int getxMin() {
		return xMin;
	}

	public int getyMin() {
		return yMin;
	}

	public int getxMax() {
		return xMax;
	}

	public int getyMax() {
		return yMax;
	}
	public int getLength(){
		return xMax-xMin;
	}
	public int getCenterX(){
		return (xMax-xMin)/2;
	}
	public int getCenterY(){
		return (yMax-yMin)/2;
	}

	public Boundry(int xMin, int yMin, int xMax, int yMax) {
		super();
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
	}

	public boolean inRange(int x, int y) {
		return (x >= this.getxMin() && x <= this.getxMax()
				&& y >= this.getyMin() && y <= this.getyMax());
	}

	int xMin, yMin, xMax, yMax;

}

class QuadTree {
	int level = 0;
	ArrayList<Point> points;
	QuadTree upperLeft = null;
	QuadTree upperRight = null;
	QuadTree lowerLeft = null;
	QuadTree lowerRight = null;
	Boundry boundry;

	QuadTree(int level, Boundry boundry) {
		this.level = level;
		points = new ArrayList<Point>();
		this.boundry = boundry;
	}

	/* Traveling the Graph using Depth First Search*/
	static void dfs(QuadTree tree) {
		if (tree == null)
			return;

		System.out.printf("\nLevel = %d [X1=%d Y1=%d] \t[X2=%d Y2=%d] ",
				tree.level, tree.boundry.getxMin(), tree.boundry.getyMin(),
				tree.boundry.getxMax(), tree.boundry.getyMax());

		for (Point Point : tree.points) {
			System.out.printf(" \n\t  x=%d y=%d", Point.x, Point.y);
		}
		System.out.println("\nStriking point: ");
		System.out.println(tree.boundry.getCenterX()+","+tree.boundry.getCenterY());
		System.out.println("NO of camps destroyed : "+tree.points.size());
		
		int d1=tree.upperLeft.points.size();
		int d2=tree.upperRight.points.size();
		int d3=tree.lowerLeft.points.size();
		int d4=tree.lowerRight.points.size();
		
		int max = d1 > d2 ? d1 > d3 ? d1 : d3 : d2 > d3 ? d2 : d3;
		if(max < d4){
			max = d4;
		}
		if(max == d1){
			dfs(tree.upperLeft);
		}
		else if(max == d2)
			dfs(tree.upperRight );
		else if(max== d3)
			dfs(tree.lowerLeft );
		else if(max==d4)
			dfs(tree.lowerRight);
		
		

	}

	void split() {
		int xOffset = this.boundry.getxMin()
				+ (this.boundry.getxMax() - this.boundry.getxMin()) / 2;
		int yOffset = this.boundry.getyMin()
				+ (this.boundry.getyMax() - this.boundry.getyMin()) / 2;

		this.upperLeft = new QuadTree(this.level + 1, new Boundry(
				this.boundry.getxMin(), this.boundry.getyMin(), xOffset,
				yOffset));
		this.upperRight = new QuadTree(this.level + 1, new Boundry(xOffset,
				this.boundry.getyMin(), xOffset, yOffset));
		this.lowerLeft = new QuadTree(this.level + 1, new Boundry(
				this.boundry.getxMin(), xOffset, xOffset,
				this.boundry.getyMax()));
		this.lowerRight = new QuadTree(this.level + 1, new Boundry(xOffset, yOffset,
				this.boundry.getxMax(), this.boundry.getyMax()));

	}

	void insert(ArrayList<Point> list) {
		for(Point p: list){
			if(boundry.inRange(p.x,p.y)){
				this.points.add(p);
			}
		}
		
		// Exceeded the capacity so split it in FOUR
		if(boundry.getLength()<15){
			return;
		}
		split();
		// Check coordinates belongs to which partition 
		
		this.upperLeft.insert(this.points);
	
		this.upperRight.insert(this.points);
	
		this.lowerLeft.insert(this.points);
	
		this.lowerRight.insert(this.points);
	}
}
class StrikeCamps{
	public static void main(String args[]) {
		QuadTree space = new QuadTree(1, new Boundry(0, 0, 100, 100));
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(5,5,1));
		points.add(new Point(5,6,1));
		points.add(new Point(50,70,1));
		points.add(new Point(6,5,1));
		points.add(new Point(7,5,1));
		points.add(new Point(5,7,1));
		points.add(new Point(4,5,1));
		points.add(new Point(5,4,1));
		space.insert(points);
		//Traveling the graph
		QuadTree.dfs(space);
	}
}
