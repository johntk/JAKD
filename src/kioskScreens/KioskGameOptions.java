package kioskScreens;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class KioskGameOptions extends JFrame implements ActionListener
{

	private JFrame frame;
	private String srcPath;
	private JPanel main,center,top;
	private JLabel heading;
	private JButton home,xbox,ps4,wii;
	private ImageIcon hm,xb,ps,wi;
	private GridBagConstraints gc;
	
	public KioskGameOptions()
	{
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(1000,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setUndecorated(true);
		
		srcPath = "src/resources/kioskFiles/images/";
		gc = new GridBagConstraints();
		hm = new ImageIcon(srcPath+"home.png");
		xb = new ImageIcon(srcPath+"xbox.png");
		ps = new ImageIcon(srcPath+"ps4.png");
		wi = new ImageIcon(srcPath+"wii.png");
		
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
		home.setPreferredSize(new Dimension(100,100));
		home.addActionListener(this);
		top.add(home, BorderLayout.WEST);
		main.add(top,BorderLayout.NORTH);
		
		//add center JPanel to main frame
		center = new JPanel(new GridBagLayout());
		center.setBackground(new Color(0,0,0,0));
		main.add(center,BorderLayout.CENTER);
		
		//add buttons and heading to center panel
		heading = new JLabel("Choose a Platform...");
		heading.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
		heading.setFont(new Font("Calibri",Font.PLAIN,50));
		gc.gridx = 1;
		gc.gridy = 0;
		center.add(heading,gc);
		xbox = new JButton(xb);
		xbox.setBackground(Color.WHITE);
		xbox.setBorder(null);
		xbox.addActionListener(this);
		gc.gridx = 0;
		gc.gridy = 1;
		center.add(xbox,gc);
		ps4 = new JButton(ps);
		ps4.setBackground(Color.WHITE);
		ps4.setBorder(null);
		ps4.addActionListener(this);
		gc.gridx = 1;
		gc.gridy = 1;
		center.add(ps4,gc);
		wii = new JButton(wi);
		wii.setBackground(Color.WHITE);
		wii.setBorder(null);
		wii.addActionListener(this);
		gc.gridx = 2;
		gc.gridy = 1;
		center.add(wii,gc);
		
		
		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.requestFocus();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home)
		{
			frame.dispose();
		}
	}
}
