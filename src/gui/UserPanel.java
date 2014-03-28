package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import db.AdminOperations;
import model.Employee;
import model.EmployeeList;

public class UserPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	private JButton addUser, addNewUser, editUser, removeUser, next, previous, exit, back;
	private JLabel userDetails;
	private JTextField forenameBx, surenamebx, line1Bx, line2Bx, Line3Bx,
			staffIDBx, pinBx, PPSBx, manager;
	private JPanel editUserBtnsPanel, userDetailsPanel, editNewUserBtnsPanel;

	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);
	private Frame frame;
	private ImageIcon close;
	private int counter = 0;
	private EmployeeList employeeList;
	private AdminOperations adminOperations;

	public UserPanel(Frame frame, AdminOperations ao, EmployeeList el) {

		this.employeeList = el;
		this.adminOperations = ao;
		this.frame = frame;
		this.setLayout(new BorderLayout());

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		close = new ImageIcon("src/resources/kioskFiles/images/close.png");
		exit = new JButton("Close", close);
		exit.setBackground(new Color(238, 238, 238));
		exit.setPreferredSize(new Dimension(100, 50));
		exit.addActionListener(this);
		exit.setBorder(new EmptyBorder(10, 0, 0, 0));
		userDetails = new JLabel("User Details");
		userDetails.setBorder(new EmptyBorder(10, 450, 0, 110));
		userDetails.setFont(font);

		top.add(userDetails);
		top.add(exit);
		this.add(top, BorderLayout.NORTH);

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
				PPSBx = new JTextField(), manager = new JTextField() };

		JLabel[] userDetailLb = { new JLabel(" Forename"),
				new JLabel(" Surename"), new JLabel(" Line 1"),
				new JLabel(" Line 2"), new JLabel(" Line 3"),
				new JLabel(" Staff ID"), new JLabel(" Pin"),
				new JLabel(" PPS"), new JLabel(" Manager") };
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
		editNewUserBtnsPanel = new JPanel();
		editNewUserBtnsPanel.add(addNewUser = new JButton("Add New User"));
		editNewUserBtnsPanel.add(back = new JButton("Back"));
		addNewUser.addActionListener(this);
		back.addActionListener(this);

		editUserBtnsPanel = new JPanel();
		editUserBtnsPanel.setLayout(new GridBagLayout());
		editUserBtnsPanel.setPreferredSize(new Dimension(250, 50));

		// Adding buttons to the button panel inside the edit user panel
		JButton[] editUserBtnsArray = { addUser = new JButton("Add User"),
				removeUser = new JButton("Remove User"),
				editUser = new JButton("Update User"), next = new JButton(">"),
				previous = new JButton("<"),

		};

		for (int i = 0; i < editUserBtnsArray.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 0.0;
			gc.insets = new Insets(10, 0, 0, 0);
			editUserBtnsArray[i].setIcon(new ImageIcon(
					"src/resources/blueButton.png"));
			editUserBtnsArray[i].setFont(new Font("sansserif", Font.BOLD, 16));
			editUserBtnsArray[i].setPreferredSize(new Dimension(180, 50));
			editUserBtnsArray[i].setHorizontalTextPosition(JButton.CENTER);
			editUserBtnsArray[i].setVerticalTextPosition(JButton.CENTER);
			editUserBtnsArray[i].addActionListener(this);
			editUserBtnsPanel.add(editUserBtnsArray[i], gc);
		}
		this.add(editUserBtnsPanel, BorderLayout.WEST);

		setFirst();
	}

	public void setFirst() {
		int pos = 0; // set to 0 and used below to retrieve 1st element from
						// array list
		counter = 0; // This is the counter used for the forward and back
						// buttons
		Employee e = employeeList.getEmployee(pos);
		this.displayEmployee(e);
	}

	public void addNew() {
		staffIDBx.setText(String.valueOf(adminOperations.getId()));
		forenameBx.setText("");
		surenamebx.setText("");
		line1Bx.setText("");
		line2Bx.setText("");
		Line3Bx.setText("");
		line2Bx.setText("");
		pinBx.setText("");
		PPSBx.setText("");
		manager.setText("");
		editUserBtnsPanel.setVisible(false);
		this.add(editNewUserBtnsPanel);
		
	}

	public Employee newEmployee() {

		Employee e = new Employee(Integer.parseInt(staffIDBx.getText()),
				forenameBx.getText(), surenamebx.getText(), line1Bx.getText(),
				line2Bx.getText(), Line3Bx.getText(), line2Bx.getText(),
				PPSBx.getText(), Integer.parseInt(pinBx.getText()),
				manager.getText());

		return e;
	}

	public void displayEmployee(Employee e) {
		staffIDBx.setText(Integer.toString(e.getEmpID()));
		forenameBx.setText(e.getfName());
		surenamebx.setText(e.getlName());
		line1Bx.setText(e.getHouseNum());
		line2Bx.setText(e.getStreet());
		Line3Bx.setText(e.getTown());
		line2Bx.setText(e.getCity());
		pinBx.setText(Integer.toString(e.getPin()));
		PPSBx.setText(e.getPPS());
		manager.setText(e.getManager());
	}

	public void actionPerformed(ActionEvent e) {

		int totalContacts = employeeList.getNumEmployee();

		if (e.getSource().equals(next)) {
			if ((counter + 1) < totalContacts) {
				counter++;
				this.displayEmployee(employeeList.getEmployee(counter));
			}
		} else if (e.getSource().equals(previous)) {
			if ((counter - 1) >= 0) {
				counter--;
				this.displayEmployee(employeeList.getEmployee(counter));
			}
		} else if (e.getSource() == exit) {
			frame.setVisible(false);
			frame.dispose();
		} else if (e.getSource().equals(addUser)) {
			userDetails.setText("Enter new user details");
			addNew();
		} else if (e.getSource().equals(addNewUser)) {
			
			adminOperations.addEmployee(newEmployee());
			employeeList.addContact();
			employeeList.refreshList();
			editUserBtnsPanel.setVisible(true);
		}
		else if(e.getSource().equals(back))
		{
			userDetails.setText("User details");
			editUserBtnsPanel.setVisible(true);
		}
	}
}
