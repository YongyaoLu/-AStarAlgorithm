package map;

public class Node {
	// 方格类型
	public static final int EMPTY = 0;
	public static final int START = 1;
	public static final int END = 2;
	public static final int WALL = 3;
	public static final int PATH = 4;
	public static final int OPEN = 5;
	public static final int CLOSED = 6;
	
	
	public static final int MAX = 10;
	
	private int type;
	private int g;
	private int h;
	private int f;
	
	// x，y记录位置
	private int x;
	private int y;
	
	// 便于记录最短路径
	Node fatherNode;
	
	public Node(int x,int y) {
		this.x = x;
		this.y = y;
		this.type = EMPTY;
		this.g = 0;
		this.h = MAX;
		this.f = g+h;
	}
	
	public Node(int type,int x,int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.g = 0;
		this.h = MAX;
		this.f = g+h;
	}
	
	public Node(int type,int x,int y,int g,int h) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.g = g;
		this.h = h;
		this.f = g+h;
	}
	
	public int getType() {
		return type;
	}
	
	public int getG() {
		return g;
	}
	
	public int getH() {
		return h;
	}
	
	public int getF() {
		return f;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Node getFatherNode() {
		return fatherNode;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public void setF(int f) {
		this.f = f;
	}
	
	public void setH(int h) {
		this.h = h;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setFatherNode(Node fatherNode) {
		this.fatherNode = fatherNode;
	}
}
