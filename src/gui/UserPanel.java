package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class UserPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	private JButton addUser, editUser, removeUser;
	private JLabel userDetails;
	private JTextField forenameBx, surenamebx, line1Bx, line2Bx, Line3Bx,
			staffIDBx, pinBx, PPSBx;
	private JPanel editUserBtnsPanel, userDetailsPanel;

	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	public UserPanel() {

		this.setLayout(new BorderLayout());
		userDetails = new JLabel("User Details");
		userDetails.setBorder(new EmptyBorder(10, 500, 0, 0));
		userDetails.setFont(font);
		this.add(userDetails, BorderLayout.NORTH);

		// user detail panel, inside the Edit user panel
		userDetailsPanel = new JPanel();
		userDetailsPanel.setLayout(new GridBagLayout());
		userDetailsPanel.setPreferredSize(new Dimension(550, 600));
		userDetailsPanel.setBorder(border);

		// Adding labels and textbox to the user details panel
		JTextField[] userDetailBx = { forenameBx = new JTextField(),
				surenamebx = new JTextField(), line1Bx = new JTextField(),
				line2Bx = new JTextField(), Line3Bx = new JTextField(),
				staffIDBx = new JTextField(), pinBx = new JTextField(),
				PPSBx = new JTextField(), };

		JLabel[] userDetailLb = { new JLabel(" Forename"),
				new JLabel(" Surename"), new JLabel(" Line 1"),
				new JLabel(" Line 2"), new JLabel(" Line 3"),
				new JLabel(" Staff ID"), new JLabel(" Pin"), new JLabel(" PPS") };
		for (int i = 0; i < userDetailLb.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.1;
			gc.weightx = 10.0;
			gc.anchor = GridBagConstraints.WEST;
			userDetailLb[i].setFont(font);
			userDetailsPanel.add(userDetailLb[i], gc);

			gc.gridx = 1;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.2;
			gc.weightx = 10.0;
			userDetailBx[i].setPreferredSize(new Dimension(350, 30));
			userDetailsPanel.add(userDetailBx[i], gc);
		}

		this.add(userDetailsPanel, BorderLayout.EAST);

		// button panel inside edit user panel
		editUserBtnsPanel = new JPanel();
		editUserBtnsPanel.setLayout(new GridBagLayout());
		editUserBtnsPanel.setPreferredSize(new Dimension(250, 50));

		// Adding buttons to the button panel inside the edit user panel
		JButton[] editUserBtnsArray = { addUser = new JButton("Add User"),
				removeUser = new JButton("Remove User"),
				editUser = new JButton("Update User"),

		};

		for (int i = 0; i < editUserBtnsArray.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 0.0;
			gc.insets = new Insets(10, 0, 0, 0);
			editUserBtnsArray[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			editUserBtnsArray[i].setFont(new Font("sansserif", Font.BOLD, 16));
			editUserBtnsArray[i].setPreferredSize(new Dimension(180, 50));
			editUserBtnsArray[i].setHorizontalTextPosition(JButton.CENTER);
			editUserBtnsArray[i].setVerticalTextPosition(JButton.CENTER);
			editUserBtnsArray[i].addActionListener(this);
			editUserBtnsPanel.add(editUserBtnsArray[i], gc);
		}
		this.add(editUserBtnsPanel, BorderLayout.WEST);
	}

	public void actionPerformed(ActionEvent e) {

	}
}
