package kioskScreens;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class KioskResultsScreen implements ActionListener
{
	private JFrame frame;
	private String srcPath;
	private JPanel main,center,top,centerTop,footer,resultWindow;
	private JScrollPane scrollPane;
	private JLabel resultsHeading,logoLabel;
	private JButton home;
	private ImageIcon hm,logo;
	private GridBagConstraints gc;

	public KioskResultsScreen()
	{
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(1200,800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);

		srcPath = "src/resources/kioskFiles/images/";
		gc = new GridBagConstraints();
		hm = new ImageIcon(srcPath+"home.png");
		logo = new ImageIcon(srcPath+"logo3.png");

		main = new JPanel(new BorderLayout());
		main.setBackground(Color.WHITE);
		main.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		frame.add(main);

		top = new JPanel(new BorderLayout());
		top.setBackground(new Color(0,0,0,0));
		home = new JButton(hm);
		home.setBackground(Color.WHITE);
		home.setFont(new Font("Calibri",Font.BOLD,25));
		home.setVerticalTextPosition(SwingConstants.BOTTOM );
		home.setHorizontalTextPosition(SwingConstants.CENTER);
		home.setBorder(null);
		home.addActionListener(this);
		top.add(home, BorderLayout.WEST);
		
		main.add(top,BorderLayout.NORTH);

		//add center JPanel to main frame
		center = new JPanel(new BorderLayout());
		center.setBackground(new Color(0,0,0,0));
		center.setBorder(BorderFactory.createEmptyBorder(0,120,0,120));
		main.add(center,BorderLayout.CENTER);
		
		centerTop = new JPanel(new GridBagLayout());
		centerTop.setBackground(new Color(0,0,0,0));
		resultsHeading = new JLabel("Displaying Results for... ");
		resultsHeading.setFont(new Font("Calibri",Font.PLAIN,40));
		centerTop.add(resultsHeading);
		center.add(centerTop,BorderLayout.NORTH);
		
		
		resultWindow = new JPanel(new GridBagLayout());
		scrollPane = new JScrollPane(resultWindow);
		scrollPane.getVerticalScrollBar().setUnitIncrement(25);
		center.add(scrollPane,BorderLayout.CENTER);

		footer = new JPanel();
		logoLabel = new JLabel(logo);
		footer.add(logoLabel);
		frame.add(footer,BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.requestFocus();
	}

	public void setHeading(String s)
	{
		resultsHeading.setText(resultsHeading.getText()+s);
	}
	
	//Adds product result to results panel
	public void addResult(String img,String desc,int y,double price)
	{
		Result rslt = new Result(img,desc,price);
		JPanel r = rslt.getResult();
		/*if (y % 2 == 0)
		{
			r.setBackground(new Color(204,229,255));
		}*/
		gc.gridx =0;
		gc.gridy =y;
		gc.weightx=1;
		gc.weighty=1;
		r.setBorder(BorderFactory.createMatteBorder(20,50,10,20, frame.getContentPane().getBackground()));
		gc.anchor = GridBagConstraints.NORTHWEST;
		resultWindow.add(r,gc);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home)
		{
			frame.dispose();
		}
	}
	
	//*********Inner Class to construct a product result***********
		private class Result
		{
			private ImageIcon productImage;
			private String imageFile;
			private JLabel image,description,salePrice;
			//private String salePrice;
			private JPanel resultPanel;
			private String srcPath;
			
			Result(String img,String desc,double price)
			{
				imageFile = img;
				salePrice = new JLabel("�"+price);
				salePrice.setFont(new Font("Calibri",Font.BOLD,20));
				description = new JLabel("  "+desc);
				description.setFont(new Font("Calibri",Font.PLAIN,25));
				resultPanel = new JPanel(new BorderLayout());
				srcPath = "src/resources/kioskFiles/productImages/thumbs/";
				productImage = new ImageIcon(srcPath+imageFile);
				image = new JLabel(productImage);
				resultPanel.add(image,BorderLayout.WEST);
				resultPanel.add(description,BorderLayout.CENTER);
				resultPanel.add(salePrice,BorderLayout.SOUTH);
			}
			public JPanel getResult()
			{
				return resultPanel;
			}
		}
}
