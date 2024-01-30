package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class XFrame extends JFrame {
	private Container center;
	private JPanel left,right;
	private JTextField value1,value2;
	private JLabel help1,help2,result;
	private JButton calculate;
	private SurfaceCalculator calculator;
	private Border blackline;
	public XFrame() {
		init();
		mountComponents();
		setVisible(true);
		addListeners();
		pack();
		this.setSize(new Dimension(400,200));
		this.setLocationRelativeTo(null);
	}
	private void init() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("Measurements");
		help1=new JLabel("Value 1 ex: 10 cm, 3 mm");
		help2=new JLabel("Value 2 ex: 10 cm, 3 mm");
		value1=new JTextField();
		value2=new JTextField();
		result=new JLabel();
		result.setText("No result");
		result.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		calculate=new JButton("Calculate");
		center=new Container();
		left=new JPanel();
		right=new JPanel();
	}
	private void mountComponents() {
		//left
		left.setLayout(new GridLayout(5,1));
		
		left.add(help1);
		left.add(value1);
		left.add(help2);
		left.add(value2);
		left.add(calculate);
		left.setBorder(blackline);
		//left.add(Box.createHorizontalStrut(10));
		//right
		right.setLayout(new BorderLayout());
		right.add(result);
		blackline=BorderFactory.createLineBorder(Color.BLACK);
		//right.add(Box.createHorizontalStrut(10));
		right.setBorder(blackline);
		
		//center
		center.setLayout(new GridLayout(1,2));
		center.add(left);
		center.add(right);
		
		
		//Global
		add(center,BorderLayout.CENTER);
	}
	
	private void addListeners() {
		calculate.addActionListener(new ActionListener() {
			//Double fieldValue1=null,fieldValue2=null;
			@Override
			public void actionPerformed(ActionEvent e) {
				String val1=value1.getText(),val2=value2.getText();
				if(!val1.isBlank()&&!val2.isBlank()) {
					try {
						calculator=new SurfaceCalculator(val1,val2);				
						result.setText("The surface is "+calculator.getSurface()+"m²");

					} catch (SexyException e1) {
						//value1.setText("");
						//value2.setText("");
						result.setText("<html>"+e1.getMessage());
						
					}
				}
			}
			
		});
	}
}
