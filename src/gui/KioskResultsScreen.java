package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import model.KioskResult;
import operations.KioskOperations;

public class KioskResultsScreen extends JFrame implements ActionListener, ItemListener
{
	private static final long serialVersionUID = 1L;
	private String srcPath;
	private static JPanel main;
	private JPanel center,top,centerTop,footer,resultWindow,r;
	private JScrollPane scrollPane;
	private JLabel resultsHeading,logoLabel;
	private JButton home,view;
	private ImageIcon hm,logo;
	private ArrayList<KioskResult> resultList;
	private ArrayList<JButton> viewButtons;
	private ArrayList<Integer> yPos;
	private ButtonGroup bg;
	private ArrayList<JRadioButton> consoleSelection;
	private JRadioButton platform;
	private JRadioButton allGames;
	private KioskResultsScreen krs;
	private KioskProductDisplay pd;
	private GridBagConstraints gc;
	private KioskOperations kq;

	/*
	 * Kiosk results screen displays a list of results based on the chosen category
	 * or from the search input
	 */
	public KioskResultsScreen(KioskOperations kq)
	{
		this.kq = kq;

		srcPath = "/resources/kioskFiles/images/";
		r = new JPanel();
		viewButtons = new ArrayList<JButton>();
		yPos = new ArrayList<Integer>();
		resultList = new ArrayList<KioskResult>();
		gc = new GridBagConstraints();
		hm = new ImageIcon(this.getClass().getResource(srcPath+"home.png"));
		logo = new ImageIcon(this.getClass().getResource(srcPath+"logo3.png"));

		main = new JPanel(new BorderLayout());
		main.setBackground(Color.WHITE);

		top = new JPanel(new BorderLayout());
		top.setBackground(new Color(0,0,0,0));
		home = new JButton(hm);
		home.setBackground(Color.WHITE);
		home.setFont(new Font("Calibri",Font.BOLD,25));
		home.setVerticalTextPosition(SwingConstants.BOTTOM );
		home.setHorizontalTextPosition(SwingConstants.CENTER);
		home.setBorder(null);
		home.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		home.addActionListener(this);
		top.add(home, BorderLayout.WEST);

		main.add(top,BorderLayout.NORTH);

		//add center JPanel to main frame
		center = new JPanel(new BorderLayout());
		center.setBackground(new Color(0,0,0,0));
		center.setBorder(BorderFactory.createEmptyBorder(0,120,20,120));
		main.add(center,BorderLayout.CENTER);

		centerTop = new JPanel(new GridBagLayout());
		centerTop.setBackground(new Color(0,0,0,0));
		resultsHeading = new JLabel("Displaying Results for... ");
		resultsHeading.setFont(new Font("Calibri",Font.PLAIN,40));
		centerTop.add(resultsHeading);
		center.add(centerTop,BorderLayout.NORTH);

		//add JPanel to display results from DB
		resultWindow = new JPanel();
		resultWindow.setLayout(new GridBagLayout());
		scrollPane = new JScrollPane(resultWindow);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		center.add(scrollPane,BorderLayout.CENTER);

		footer = new JPanel();
		logoLabel = new JLabel(logo);
		footer.add(logoLabel);
		main.add(footer,BorderLayout.SOUTH);
		
		main.requestFocus();
	}

	public void passKioskResultsScreenObject(KioskResultsScreen krs)
	{
		this.krs = krs;
	}

	public void setHeading(String s)
	{
		resultsHeading.setText(resultsHeading.getText()+s);
	}

	// Creates a result object and adds it to the result arrayList
	public void addResult(String img,String desc,int y,double price, String prodID)
	{
		KioskResult rslt = new KioskResult(img,desc,price,prodID);
		resultList.add(rslt);
		yPos.add(y);

		// Add a "view product details" button to each result in the arrayList
		view = new JButton("View Product Details");
		view.setPreferredSize(new Dimension(180,50));
		view.setBackground(new Color(33,106,206));
		view.setForeground(Color.WHITE);
		view.setFont(new Font("Calibri",Font.BOLD,15));
		view.addActionListener(this);
		viewButtons.add(view);
	}

	//Adds product result to results panel
	public void displayResult()
	{
		for(int i=0; i<resultList.size();i++)
		{
			JLabel number = new JLabel(yPos.get(i)+1+"");
			gc.gridx =0;
			gc.gridy =yPos.get(i);
			gc.weightx=1.0;
			gc.weighty=1.0;
			gc.anchor = GridBagConstraints.WEST;
			number.setBorder(BorderFactory.createEmptyBorder(0,4,0,0));
			resultWindow.add(number,gc);

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
		// Pad out the results panel if the number of results is less than 10
		if(resultList.size()<10)
		{
			int temp = 10-resultList.size();
			int tempY = resultList.size();
			for(int i=0;i<temp;i++)
			{
				JLabel b = new JLabel(" ");
				gc.gridx =0;
				gc.gridy = tempY;
				gc.weightx=1.0;
				gc.weighty=1.0;
				gc.fill = GridBagConstraints.HORIZONTAL;
				gc.anchor = GridBagConstraints.NORTHWEST;
				tempY++;
				resultWindow.add(b,gc);
			}
		}
	}

	public JPanel getPanel()
	{
		return main;
	}
	
	public static void switchToResultsPanel(JPanel panel)
	{
		if(main.isVisible()==false)
		{
			main.setVisible(true);
			panel.setVisible(false);
		}
		main.validate();
		main.repaint();
	}
	
//	Adds a list of games console JButtons to the results panel to filter game results by console
	public void displayGameOptions(ArrayList<String> consoleList)
	{
		JPanel radioButtonPanel = new JPanel();
		Font font = new Font("Calibri",Font.PLAIN,20);
		
		Box vb = Box.createVerticalBox();
		bg = new ButtonGroup();
		
		JLabel filter = new JLabel("Filter by Platform");
		filter.setFont(new Font("Calibri",Font.BOLD,25));
		filter.setForeground(new Color(20,120,230));
		filter.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		vb.add(filter);
		
		allGames = new JRadioButton("All Games");
		allGames.setBackground(main.getBackground());
		allGames.setForeground(new Color(20,120,230));
		allGames.setFont(font);
		allGames.addItemListener(this);
		bg.add(allGames);
		vb.add(allGames);
		
		radioButtonPanel.setBackground(main.getBackground());
		radioButtonPanel.setBorder(BorderFactory.createEmptyBorder(40,10,10,10));

		consoleSelection = new ArrayList<JRadioButton>();

		for(int i=0; i<consoleList.size(); i++)
		{
			platform = new JRadioButton(""+consoleList.get(i));
			platform.setBackground(main.getBackground());
			platform.setForeground(new Color(20,120,230));
			platform.setFont(font);
			platform.addItemListener(this);

			bg.add(platform);
			consoleSelection.add(platform);
			vb.add(platform);
		}
		radioButtonPanel.add(vb);
		main.add(radioButtonPanel, BorderLayout.WEST);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home)
		{
			KioskHomeScreen.switchToMainPanel(main);
		}

		if(e.getSource() instanceof JButton)
		{
			for(int i=0; i<viewButtons.size(); i++)
			{
				if(((JButton)e.getSource()) == viewButtons.get(i))
				{
					pd = new KioskProductDisplay();
					kq.queryProductInfo(resultList.get(i).getProdID(),pd);
					main.setVisible(false);
					KioskHomeScreen.addPanel(pd.getPanel());
				}
			}
		}
	}

	public void itemStateChanged(ItemEvent e)
	{
		for(int i=0; i<consoleSelection.size(); i++)
		{
			if(consoleSelection.get(i).isSelected())
			{
				resultList.clear();
				yPos.clear();
				
				resultWindow.removeAll();
				
				kq.queryGames(consoleSelection.get(i).getText(),krs);
				krs.displayResult();
				main.validate();
				main.repaint();
			}
		}
		if(allGames.isSelected())
		{
			resultList.clear();
			yPos.clear();
			
			resultWindow.removeAll();
			
			kq.queryGames("",krs);
			krs.displayResult();
			main.validate();
			main.repaint();
		}
	}
}
