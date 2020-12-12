package search_algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import map.Node;

public class HeuristicSearch {
	public static final int EXECUTING = 1;
	public static final int SUCESS = 0;
	public static final int CONTINUE = 2;
	public static final int FAILURE = 3;
	private ArrayList<Node> openList = new ArrayList<>();
	private ArrayList<Node> closedList = new ArrayList<>();
	private Node[][] map;
	private Node startNode;
	private Node endNode;
	
	private int steps=0;//����
	
	public HeuristicSearch(Node[][] map,Node start,Node end) {
		this.map = map;
		openList.add(start);
		this.startNode = start;
		this.endNode = end;
	}
	
	/**
	 * Ϊ�˸���ÿһ���Ľڵ���Ϣ��ʾ��UI���÷����൱��A*�㷨ִ��һ��ѭ��
	 * @return ִ�н��
	 */
	public int execute() {
		System.out.println("siez:"+openList.size());
		if (openList.size()>0) {
			Node currentNode = openList.get(0);
			System.out.println(currentNode);
			if(currentNode.getType() == Node.END) {
				return SUCESS;
			}
			ArrayList<Node> childList = new ArrayList<>();
			int x = currentNode.getX();
			int y = currentNode.getY();
			int mapLengthY = map[0].length;
			int mapLengthX = map.length;
			System.out.println(x+" "+y+" "+mapLengthX+" "+mapLengthY);
			int flag = 0; // ��״̬����
			// ������״̬
			if ( x-1>=0 ) {
				Node childLeft = map[x-1][y];
				if (childLeft.getType() != Node.WALL) {
					System.out.println("left");
					childList.add(childLeft);
					flag++;
				}
			}
			if ( y-1>=0 ) {
				Node childUp = map[x][y-1];
				if (childUp.getType() != Node.WALL) {
					System.out.println("up");
					childList.add(childUp);
					flag++;
				}
			}
			if ( x+1<mapLengthX ) {
				Node childRight = map[x+1][y];
				if (childRight.getType() != Node.WALL) {
					System.out.println("right");
					childList.add(childRight);
					flag++;
				}
			}
			if ( y+1<mapLengthY ) {
				Node childDown = map[x][y+1];
				if (childDown.getType() != Node.WALL) {
					System.out.println("down");
					childList.add(childDown);
					flag++;
				}
			}
			if (flag==0) {// û����״̬
				return CONTINUE;
			}
			
			for(int i=0; i<childList.size(); i++) {
				Node childNode = childList.get(i);
				if( (openList.indexOf(childNode)==-1) && (closedList.indexOf(childNode)==-1) ) {
					childNode.setG(currentNode.getG()+1);
					childNode.setH( Math.abs(childNode.getX()-endNode.getX()) + Math.abs(childNode.getY()-endNode.getY()) );
					childNode.setF( childNode.getG()+childNode.getH() );
					childNode.setFatherNode(currentNode);
					if (childNode.getType()!=Node.END) { // ��ֹ��Ŀ�Ľڵ���ΪOpenӰ�����Ч��
						childNode.setType(Node.OPEN);
					}
					openList.add(childNode);
				}
				else if (openList.indexOf(childNode)!=-1) {
					int g = currentNode.getG()+1;
					if (g<childNode.getG()) {
						childNode.setFatherNode(currentNode);
						childNode.setG(g);
						childNode.setF( childNode.getG()+childNode.getH() );
					}
				}
				else if (closedList.indexOf(childNode)!=-1) {
					int g = currentNode.getG()+1;
					if (g<childNode.getG()) {
						closedList.remove(childNode);
						openList.add(childNode);
						childNode.setType(Node.OPEN);
						childNode.setFatherNode(currentNode);
						childNode.setG(g);
						childNode.setF( childNode.getG()+childNode.getH() );
					}
				}
				openList.remove(currentNode);
				closedList.add(currentNode);
				if (currentNode.getType()!=Node.START) { // ��ֹ�󽫿�ʼ�������λCLOSED״̬Ӱ�����Ч��
					currentNode.setType(Node.CLOSED);
				}
				// ���Ȱ�fֵ����f��ͬ��h����
				Collections.sort(openList,new Comparator<Node>() {
					@Override
					public int compare(Node o1, Node o2) {
						int flag = o1.getF()-o2.getF();
						int newFlag = flag;// ���java8 labmda���ʽ�����ϱ���"Local variable flag defined in an enclosing scope must be final or effectively final"
						if (newFlag==0) {
							newFlag = o1.getH()-o2.getH();
						}
						return newFlag;
					}
					
				});
			}
			return CONTINUE;
		}else {
			return FAILURE;
		}
	}
	
	/**
	 * ��ִ�н��λSUCCESSʱ��ִ�и÷�����ȡ���·��
	 */
	public int getShortestPath() {
		Node pathNode = endNode.getFatherNode();
		while(pathNode!=startNode) {
			steps++;
			pathNode.setType(Node.PATH);
			pathNode = pathNode.getFatherNode();
		}
		return steps+1;
	}

}
