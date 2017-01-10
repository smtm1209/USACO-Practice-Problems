import java.util.*;
import java.io.*;

public class prob3 {
	static String filename = "lasers";
	public static void main(String[] args) throws Exception {
		Scanner fin = new Scanner(new File(filename + ".in"));
		PrintWriter fout = new PrintWriter(new FileWriter(filename + ".out"));
		int N = fin.nextInt();
		Line xL = new Line('x', fin.nextInt()), yL = new Line('y', fin.nextInt()), xB = new Line('x', fin.nextInt()), yB = new Line('y', fin.nextInt());
		Map<Integer, Line> xlines = new HashMap<Integer, Line>();
		Map<Integer, Line> ylines = new HashMap<Integer, Line>();

		//Set<Line> xlines = new HashSet<Line>(); 
		//Set<Line> ylines = new HashSet<Line>();
		Set<Point> points = new HashSet<Point>();
		//xlines.put(xL.val, new LinkedList<Line>());
		//xlines.get(xL.val).add(xL);
		//if(xlines.containsKey(xB.val)) xlines.get(xB.val).add(xB); else {xlines.put(xB.val, new LinkedList<Line>()); xlines.get(xB.val).add(xB);}
		//ylines.put(yL.val, new LinkedList<Line>());
		//ylines.get(yL.val).add(yL);
		//if(ylines.containsKey(yB.val)) ylines.get(yB.val).add(yB); else {ylines.put(yB.val, new LinkedList<Line>()); ylines.get(yL.val).add(yL);}
		xlines.put(xL.val, xL); if(!xlines.containsKey(xB.val)) xlines.put(xB.val, xB); ylines.put(yL.val, yL); if(!xlines.containsKey(yB.val)) ylines.put(yB.val, yB);

			
		//xlines.add(xL); ylines.add(yL); xlines.add(xB); ylines.add(yB);
		for(int i = 0; i < N; i++) {
			int x = fin.nextInt(), y = fin.nextInt();
			points.add(new Point(x, y));
			//System.out.println(new Line('x', x));
			//System.out.println((new Line('x', x)).hashCode());
			//System.out.println(new Line('y', y));
			//System.out.println((new Line('y', y)).hashCode());
			Line lx = new Line('x', x), ly = new Line('y', y);
			//xlines.add(new Line('x', x));
			//ylines.add(new Line('y', y));
			//if(xlines.containsKey(x)) xlines.get(x).add(lx); else {xlines.put(x, new LinkedList<Line>()); xlines.get(x).add(lx);}
			//if(ylines.containsKey(y)) ylines.get(y).add(ly); else {ylines.put(y, new LinkedList<Line>()); ylines.get(y).add(ly);}
			if(!xlines.containsKey(x)) xlines.put(x, lx);
			if(!ylines.containsKey(y)) ylines.put(y, ly);
			xlines.get(x).adj.add(ylines.get(y));
			ylines.get(y).adj.add(xlines.get(x));
		}
		//System.out.println(lines.toString());
		//System.out.println(points.toString());
		/*for(Point p : points) {
			if(!xlines.containsKey(p.x) || !ylines.containsKey(p.y)) continue;
			xlines.get(p.x).adj.add(ylines.get(p.y));
			ylines.get(p.y).adj.add(xlines.get(p.x));
		}
		*/
		/*
		for(Line l : xlines) {
			for(Line m : ylines) {
				if(l.ori == m.ori) continue;
				//System.out.println(new Point(m.val, l.val));
				//System.out.println((new Point(m.val, l.val)).hashCode());
				if(l.ori == 'x' && points.contains(new Point(l.val, m.val))) {
					//System.out.println(new Point(l.val, m.val));
					//System.out.println((new Point(l.val, m.val)).hashCode());
					//Point p = new Point(l.val, m.val);
					//System.out.println(points.contains(p));
					//if(points.contains(p)) {
					l.adj.add(m);
					m.adj.add(l);
					//}
				}
				else if(points.contains(new Point(m.val, l.val))) {
					l.adj.add(m);
					m.adj.add(l);
				}
			}
		}
		*//*
		for(Integer l : xlines.keySet()) {
			System.out.println(l + ": " + xlines.get(l).adj.toString());
		}
		for(Integer l : ylines.keySet()) {
			System.out.println(l + ": " + ylines.get(l).adj.toString());
		}
		*/
		Set<Line> visited = new HashSet<Line>();
		Queue<DFSLine> q = new LinkedList<DFSLine>();
		q.add(new DFSLine(xL, 0));
		q.add(new DFSLine(yL, 0));
		int ret = -1;
		while(!q.isEmpty()) {
			DFSLine l = q.remove();
			//System.out.println(l);
			if(l.equals(xB) || l.equals(yB)) {
				//found = true;
				ret = l.depth;
				break;
			}
			//System.out.println(l.adj);
			for(Line m : l.adj) {
				//System.out.println(m);
				//System.out.println(visited.contains(m));
				if(!visited.contains(m)) {q.add(new DFSLine(m, l.depth+1)); visited.add(m);}
			}
		}
		/*
		visited = new HashSet<Line>();
		q1 = new LinkedList<DFSLine>();
		q1.add(new DFSLine(yL, 0));
		while(!q1.isEmpty()) {
			DFSLine l = q1.remove();
			//System.out.println(l);
			if(l.equals(xB) || l.equals(yB)) {
				found = true;
				ret = Math.min(l.depth, ret);
				break;
			}
			//System.out.println(l.adj);
			for(Line m : l.adj) {
				//System.out.println(m);
				//System.out.println(visited.contains(m));
				if(!visited.contains(m)) q1.add(new DFSLine(m, l.depth+1));
			}
		}
		*/
		fout.println(ret);
		fout.close();
		
	}
	static class Line {
		char ori;
		int val;
		public List<Line> adj;
		public Line(char o, int v) {ori = o; val = v; adj = new LinkedList<Line>();}
		public int hashCode() {
			return this.toString().hashCode();
		}
		public boolean equals(Object o) {
			Line that = (Line) o;
			return this.ori == that.ori && this.val == that.val;
		}
		public String toString() {
			return ori + ": <" + val + ">";
		}
	}
	static class DFSLine extends Line {
		int depth;
		public DFSLine(Line l, int d) {
			super(l.ori, l.val);
			this.adj = l.adj;
			this.depth = d;
		}
	}
	static class Point {
		int x, y;
		public Point(int x, int y) {this.x = x; this.y = y;}
		public int hashCode() {
			return this.toString().hashCode();
		}
		public boolean equals(Object o) {
			Point that = (Point) o;
			return this.x == that.x && this.y == that.y;
		}	
		public String toString() {
			return "(" + this.x + ", " + this.y + ")";
		}
	}
}
