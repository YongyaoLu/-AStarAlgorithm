package clientGUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import map.Node;
import search_algorithm.HeuristicSearch;

public class ClientGUI extends JFrame implements ActionListener{
	private Node[][] map;
	private Node start;
	private Node end;
	private NodeView[][] mapView;
	private JButton buttonNext;
	
	private HeuristicSearch heuristicSearch;
	
	public ClientGUI() {
		initMap();
		System.out.println("         "+start);
		heuristicSearch = new HeuristicSearch(map, start, end);
		
		buttonNext = new JButton("Next");
		buttonNext.addActionListener(this);
		
		int x = map.length;
		int y = map[0].length;
		setLayout(new BorderLayout());
		setTitle("+7nb");
		JPanel mapPanel = new JPanel(new GridLayout(x,y,2,2));
		add(mapPanel,BorderLayout.NORTH);
		add(buttonNext,BorderLayout.SOUTH);
		
		mapView = new NodeView[x][y];
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				NodeView nodeView = new NodeView();
				mapView[i][j] = nodeView;
				mapPanel.add(nodeView);
			}
		}
		update();
		// 设置窗口
		int windowLength = y*50+(y-1)*2;
		int windowWidth = x*50+(y-1)*2+100;
		setSize(windowLength,windowWidth);
		setVisible(true);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	/**
	 * 初始化地图
	 */
	public void initMap() {
		// 地图
		map = new Node[9][9];
		for(int i = 0;i<9;i++) {
			for(int j = 0;j<9;j++) {
				Node node = new Node(i,j);
				map[i][j] = node;
			}
		}
		Node node1 = new Node(Node.WALL,4,1);
		map[4][1] = node1;
		Node node2 = new Node(Node.WALL,2,2);
		map[2][2] = node2;
		Node node3 = new Node(Node.WALL,3,2);
		map[3][2] = node3;
		Node node4 = new Node(Node.WALL,4,2);
		map[4][2] = node4;
		Node node5 = new Node(Node.WALL,5,2);
		map[5][2] = node5;
		Node node6 = new Node(Node.WALL,2,3);
		map[2][3] = node6;
		Node node7 = new Node(Node.WALL,5,3);
		map[5][3] = node7;
		Node node8 = new Node(Node.WALL,2,4);
		map[2][4] = node8;
		Node node9 = new Node(Node.WALL,2,5);
		map[2][5] = node9;
		Node node0 = new Node(Node.WALL,2,6);
		map[2][6] = node0;
		
		
		start = new Node(Node.START,3,1,0,0);
		map[3][1] = start;
		end = new Node(Node.END,3,3);
		map[3][3] = end;
	}
	
	/**
	 * 刷新用户图形界面
	 * @param map
	 */
	public void update() {
		int x = map.length;
		int y = map[0].length;
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				Node node = map[i][j];
				NodeView nodeView = mapView[i][j];
				JLabel textG = nodeView.getTextG();
				JLabel textH = nodeView.getTextH();
				JLabel textF = nodeView.getTextF();
				int type = node.getType();
				switch (type) {
				case Node.EMPTY:
					nodeView.setBackground(Color.WHITE);
					textG.setText(" ");
					textH.setText(" ");
					textF.setText(" ");
					break;
					
				case Node.START:
					nodeView.setBackground(Color.BLUE);	
					textG.setText(" ");
					textH.setText(" ");
					textF.setText("A");
					break;
					
				case Node.END:
					nodeView.setBackground(Color.BLUE);
					textG.setText(" ");
					textH.setText(" ");
					textF.setText("B");
					break;
					
				case Node.PATH:
					nodeView.setBackground(Color.BLUE);
					textG.setText(" "+node.getG());
					textH.setText(node.getH()+" ");
					textF.setText(node.getF()+"");
					break;
					
				case Node.WALL:
					nodeView.setBackground(Color.BLACK);
					textG.setText(" ");
					textH.setText(" ");
					textF.setText(" ");
					break;
					
				case Node.OPEN:
					nodeView.setBackground(Color.GREEN);
					textG.setText(" "+node.getG());
					textH.setText(node.getH()+" ");
					textF.setText(node.getF()+"");
					break;
					
				case Node.CLOSED:
					nodeView.setBackground(Color.RED);
					textG.setText(" "+node.getG());
					textH.setText(node.getH()+" ");
					textF.setText(node.getF()+"");
					break;

				default:
					break;
				}
			}
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		int state = heuristicSearch.execute();
		switch (state) {
		case HeuristicSearch.CONTINUE:
			update();
			break;
			
		case HeuristicSearch.SUCESS:
			int steps = heuristicSearch.getShortestPath();
			buttonNext.setText("Success!  Steps:"+steps);
			update();
			break;
			
		case HeuristicSearch.FAILURE:
			update();
			buttonNext.setText("failure!");

		default:
			break;
		}
	}
}
