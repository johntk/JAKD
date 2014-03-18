package kioskScreens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProductDisplay extends JFrame implements ActionListener
{
	private JFrame frame;
	private String srcPath,prodID;
	private JPanel main,center,top,centerTop,footer,resultWindow;
	private JScrollPane scrollPane;
	private JButton home;
	private ImageIcon img;
	private GridBagConstraints gc;
	
	public ProductDisplay(String prodID)
	{
		this.prodID = prodID;
		srcPath = "src/resources/kioskFiles/productImages/";
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(new BorderLayout());
		frame.setSize(1200,800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
