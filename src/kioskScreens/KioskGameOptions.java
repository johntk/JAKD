package kioskScreens;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class KioskGameOptions extends JFrame implements ActionListener
{

	private JFrame frame;
	private String srcPath;
	private JPanel main,center,top;
	private JLabel logoLabel,empty1;
	private JTextField search;
	private JButton home,searchBtn;
	private Font f1;
	private ImageIcon hm,xb,ps,wii;
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
		
		srcPath = "src/kioskScreens/Resources/images/";
		gc = new GridBagConstraints();
		hm = new ImageIcon(srcPath+"home.png");
		xb = new ImageIcon(srcPath+"xbox.png");
		
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
		
		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home)
		{
			frame.dispose();
		}
	}
}
