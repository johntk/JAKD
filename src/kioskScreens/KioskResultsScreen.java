package kioskScreens;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import db.DBconnection;

public class KioskResultsScreen extends JFrame implements ActionListener
{
	private JFrame frame;
	private String srcPath;
	private JPanel main,center,top,centerTop,footer,resultWindow,r;
	private JScrollPane scrollPane;
	private JLabel resultsHeading,logoLabel;
	private JButton home,view;
	private ImageIcon hm,logo;
	private ArrayList<Result> resultList;
	private ArrayList<JButton> viewButtons;
	private ArrayList<Integer> yPos;
	private GridBagConstraints gc;

	public KioskResultsScreen()
	{
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(1200,800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setUndecorated(true);

		srcPath = "src/resources/kioskFiles/images/";
		r = new JPanel();
		viewButtons = new ArrayList<JButton>();
		yPos = new ArrayList<Integer>();
		resultList = new ArrayList<Result>();
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

		//add JPane to display results from DB
		resultWindow = new JPanel();
		resultWindow.setLayout(new GridBagLayout());
		scrollPane = new JScrollPane(resultWindow);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
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

	// Creates a result object and adds it to the result arrayList
	public void addResult(String img,String desc,int y,double price, String prodID)
	{
		view = new JButton("View Product Details");
		view.setPreferredSize(new Dimension(180,50));
		view.setBackground(new Color(33,106,206));
		view.setForeground(Color.WHITE);
		view.setFont(new Font("Calibri",Font.BOLD,15));
		view.addActionListener(this);
		viewButtons.add(view);

		Result rslt = new Result(img,desc,price,prodID);
		resultList.add(rslt);
		yPos.add(y);
	}

	//Adds product result to results panel
	public void displayResult()
	{
		for(int i=0; i<resultList.size();i++)
		{
			r = resultList.get(i).getResult();
			gc.gridx =0;
			gc.gridy =yPos.get(i);
			gc.weightx=1.0;
			gc.weighty=1.0;
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.anchor = GridBagConstraints.NORTHWEST;
			r.setBorder(BorderFactory.createEmptyBorder(10,20,10,160));
			// Set colour on the result JPanel background where grid = even number
			if (yPos.get(i) % 2 == 0)
			{
				r.setBackground(new Color(215,225,235));
			}
			resultWindow.add(r,gc);

			gc.gridx =2;
			gc.gridy =0;
			gc.weightx=0.0;
			gc.weighty=0.0;
			gc.anchor = GridBagConstraints.WEST;
			r.add(viewButtons.get(i),gc);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home)
		{
			frame.dispose();
		}

		if(e.getSource() instanceof JButton)
		{
			for(int i=0; i<viewButtons.size(); i++)
			{
				if(((JButton)e.getSource()) == viewButtons.get(i))
				{
					ProductDisplay pd = new ProductDisplay(resultList.get(i).getProdID());
				}
			}
		}
	}
}
