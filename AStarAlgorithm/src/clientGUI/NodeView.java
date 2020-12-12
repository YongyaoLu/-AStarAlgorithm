package clientGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NodeView extends JPanel{
	private JLabel textG=new JLabel(" ");
	private JLabel textH=new JLabel(" ");;
	private JLabel textF=new JLabel(" ",JLabel.CENTER);;
	
	public NodeView() {
		setLayout(new BorderLayout(2,2));
		add(textG,BorderLayout.WEST);
		add(textH,BorderLayout.EAST);
		Font fontF = new Font(getName(), Font.BOLD, 20);
		textF.setFont(fontF);
		add(textF,BorderLayout.SOUTH);
	}
	
	public JLabel getTextF() {
		return textF;
	}
	
	public JLabel getTextG() {
		return textG;
	}
	
	public JLabel getTextH() {
		return textH;
	}
	
	public void setTextF(JLabel textF) {
		this.textF = textF;
	}
	
	public void setTextG(JLabel textG) {
		this.textG = textG;
	}
	
	public void setTextH(JLabel textH) {
		this.textH = textH;
	}
}
