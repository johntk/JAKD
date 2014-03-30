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
	private JButton addUser, updateBtn, updateUser, removeUser, next, previous,
			exit, back, searchUser;
	private JLabel userDetails;
	private JTextField spacer, forenameBx, surenamebx, line1Bx, line2Bx,
			Line3Bx, staffIDBx, pinBx, PPSBx, manager;
	private JPanel editUserBtnsPanel, userDetailsPanel, editNewUserBtnsPanel;

	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);
	private Frame frame;
	private ImageIcon close;
	private int counter = 0;
	private EmployeeList employeeList;
	private AdminOperations adminOperations;
	
	private JTextField[] userDetailBx = { forenameBx = new JTextField(),
			surenamebx = new JTextField(), spacer = new JTextField(),
			line1Bx = new JTextField(), line2Bx = new JTextField(),
			Line3Bx = new JTextField(), spacer = new JTextField(),
			staffIDBx = new JTextField(), pinBx = new JTextField(),
			PPSBx = new JTextField(), manager = new JTextField() };

	
	private JLabel[] userDetailLb = { new JLabel(" Forename:"),
			new JLabel(" Surename:"), new JLabel(" Address"),
			new JLabel(" House No:"), new JLabel(" Town:"),
			new JLabel(" City:"), new JLabel(""), new JLabel(" Staff ID:"),
			new JLabel(" Pin:"), new JLabel(" PPS:"),
			new JLabel(" Manager:") };
	
	private Color  cl2 ,cl3;
	
	public UserPanel(Frame frame, AdminOperations ao, EmployeeList el) {

		this.employeeList = el;
		this.adminOperations = ao;
		this.frame = frame;
		this.setLayout(new BorderLayout());

		cl2 = new Color(75,255,250);
		cl3 = new Color(255,230,0);
				
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
		for (int i = 0; i < userDetailLb.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.1;
			gc.weightx = 10.0;
			gc.insets = new Insets(10, 0, 0, 0);
			gc.anchor = GridBagConstraints.WEST;
			userDetailLb[i].setFont(font);
			userDetailsPanel.add(userDetailLb[i], gc);

			if (i != 2 && i != 6) {
				gc.gridx = 1;
				gc.gridy = i;
				gc.weighty = 0.0;
				userDetailBx[i].setPreferredSize(new Dimension(350, 30));
				userDetailBx[i].setEditable(false);
				if(userDetailLb[i].getText().equals(" Staff ID:"))
				{
					userDetailBx[i].setBackground(cl3);
				}
				else
				{
					userDetailBx[i].setBackground(cl2);
				}
				
				userDetailBx[i].getCaret().setBlinkRate(1000);
				userDetailsPanel.add(userDetailBx[i], gc);
			}
			if (i < 6 && i > 2) {
				gc.gridx = 0;
				gc.gridy = i;
				gc.anchor = GridBagConstraints.WEST;
				userDetailLb[i].setFont(font);
				userDetailsPanel.add(userDetailLb[i], gc);

				gc.gridx = 1;
				gc.gridy = i;
				userDetailBx[i].setPreferredSize(new Dimension(350, 30));
				userDetailBx[i].setEditable(false);
				userDetailsPanel.add(userDetailBx[i], gc);
			}
		}

		this.add(userDetailsPanel, BorderLayout.EAST);

		editNewUserBtnsPanel = new JPanel();
		editNewUserBtnsPanel.setLayout(new GridBagLayout());
		editNewUserBtnsPanel.setPreferredSize(new Dimension(250, 50));

		JButton[] addUserBtnsArray = { updateBtn = new JButton(""),
				back = new JButton("Back") };

		for (int i = 0; i < addUserBtnsArray.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 0.0;
			gc.insets = new Insets(10, 0, 0, 0);
			addUserBtnsArray[i].setPreferredSize(new Dimension(150, 40));
			addUserBtnsArray[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			addUserBtnsArray[i].setFont(new Font("sansserif", Font.BOLD, 16));
			addUserBtnsArray[i].setHorizontalTextPosition(JButton.CENTER);
			addUserBtnsArray[i].setVerticalTextPosition(JButton.CENTER);
			addUserBtnsArray[i].addActionListener(this);
			editNewUserBtnsPanel.add(addUserBtnsArray[i], gc);
		}

		// button panels inside edit user panel
		editUserBtnsPanel = new JPanel();
		editUserBtnsPanel.setLayout(new GridBagLayout());
		editUserBtnsPanel.setPreferredSize(new Dimension(250, 50));

		// Adding buttons to the button panel inside the edit user panel
		JButton[] editUserBtnsArray = {
				searchUser = new JButton("Search User"),
				addUser = new JButton("Add User"),
				removeUser = new JButton("Remove User"),
				updateUser = new JButton("Edit User"),
				previous = new JButton("<"), next = new JButton(">")

		};

		for (int i = 0; i < editUserBtnsArray.length; i++) {

			if (i < editUserBtnsArray.length - 2) {
				gc.gridx = 0;
				gc.gridy = i;
				gc.gridwidth = editUserBtnsArray.length;
				gc.gridheight = 1;
				gc.weighty = 0.0;
				gc.weightx = 0.0;
				gc.insets = new Insets(10, 0, 0, 0);
				editUserBtnsArray[i].setPreferredSize(new Dimension(150, 40));
			} else {
				gc.gridx = i;
				gc.gridy = editUserBtnsArray.length - 2;
				gc.gridwidth = 1;
				if (i == editUserBtnsArray.length - 1) {
					gc.insets = new Insets(10, 30, 0, 0);
				}
				editUserBtnsArray[i].setPreferredSize(new Dimension(60, 50));
			}

			editUserBtnsArray[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			editUserBtnsArray[i].setFont(new Font("sansserif", Font.BOLD, 16));
			editUserBtnsArray[i].setHorizontalTextPosition(JButton.CENTER);
			editUserBtnsArray[i].setVerticalTextPosition(JButton.CENTER);
			editUserBtnsArray[i].addActionListener(this);
			editUserBtnsPanel.add(editUserBtnsArray[i], gc);

		}

		this.add(editUserBtnsPanel, BorderLayout.WEST);

		setFirst();
	}

	public void setFirst() {
		int pos = 0;
		counter = 0;
		Employee e = employeeList.getEmployee(pos);
		this.displayEmployee(e);
	}

	public void setEditableOn() {
		for (int i = 0; i < userDetailBx.length; i++)
			if (userDetailLb[i].getText() !=" Staff ID:")
				userDetailBx[i].setEditable(true);
	}

	public void setEditableOff() {
		for (int i = 0; i < userDetailBx.length; i++)
			userDetailBx[i].setEditable(false);
	}

	public void addNew() {
		userDetails.setText("Enter New Details");
		userDetails.setBorder(new EmptyBorder(10, 390, 0, 110));
		updateBtn.setText("Add New User");
		for (int i = 0; i < userDetailBx.length; i++) {
			staffIDBx.setText(String.valueOf(adminOperations.getId()));
			staffIDBx.setEditable(false);
			if (i != 7) {
				userDetailBx[i].setText("");
				userDetailBx[i].setEditable(true);
			}
		}
		editUserBtnsPanel.setVisible(false);
		this.add(editNewUserBtnsPanel);
	}

	public void updateUser() {
		updateBtn.setText("Update User");
		userDetails.setText("Update User Details");
		userDetails.setBorder(new EmptyBorder(10, 372, 0, 110));
		editUserBtnsPanel.setVisible(false);
		this.add(editNewUserBtnsPanel);
	}

	public void deleteContact() {
		int numberOfDeleted = employeeList.removeEmployee(forenameBx.getText());
		JOptionPane.showMessageDialog(null, numberOfDeleted
				+ " Record(s) deleted.");
		setFirst();
	}

	public Employee newEmployee() {
		Employee e = new Employee(Integer.parseInt(staffIDBx.getText()),
				forenameBx.getText(), surenamebx.getText(), line1Bx.getText(),
				line2Bx.getText(), Line3Bx.getText(), line2Bx.getText(),
				PPSBx.getText(), Integer.parseInt(pinBx.getText()),
				manager.getText());

		return e;
	}

	public void updateEmployee() {
		Employee e = new Employee(Integer.parseInt(staffIDBx.getText()),
				forenameBx.getText(), surenamebx.getText(), line1Bx.getText(),
				line2Bx.getText(), Line3Bx.getText(), line2Bx.getText(),
				PPSBx.getText(), Integer.parseInt(pinBx.getText()),
				manager.getText());
		employeeList.updateEmployee(e);
		JOptionPane.showMessageDialog(null, "Employee " + forenameBx.getText()
				+ " Updated");
		userDetails.setText("User Details");
	}

	public void searchEmployee() {
		String employeeName = JOptionPane.showInputDialog(null,
				"Enter the name of an Employee: ",
				"Employee Manager", JOptionPane.QUESTION_MESSAGE);
		int index = employeeList.findEmployee(employeeName);
		if (index != -1) 
			displayEmployee(employeeList.getEmployee(index));
		 else 
			JOptionPane.showMessageDialog(null, " Employee not found");
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
		} 
		else if (e.getSource().equals(previous)) {
			if ((counter - 1) >= 0) {
				counter--;
				this.displayEmployee(employeeList.getEmployee(counter));
			}
		} 
		else if (e.getSource() == exit) {
			frame.setVisible(false);
			frame.dispose();
		} 
		else if (e.getSource().equals(addUser)) {
			userDetailBx[0].requestFocus();
			userDetailBx[0].getCaret().setBlinkRate(600);
			addNew();
		} 
		else if (e.getSource().equals(updateBtn)
				&& updateBtn.getText().equals("Add New User")) {
			
			adminOperations.addEmployee(newEmployee());
			employeeList.addContact();
			employeeList.refreshList();
			editUserBtnsPanel.setVisible(true);
			JOptionPane.showMessageDialog(null, forenameBx.getText() + " Saved");
			editUserBtnsPanel.setVisible(true);
			setEditableOff();
			setFirst();
		} 
		else if (e.getSource().equals(back)) {
			userDetails.setText("User Details");
			userDetails.setBorder(new EmptyBorder(10, 450, 0, 110));
			editUserBtnsPanel.setVisible(true);
			setEditableOff();
			setFirst();
		} 
		else if (e.getSource().equals(updateUser)) {
			userDetailBx[0].requestFocus();
			userDetailBx[0].getCaret().setBlinkRate(600);
			setEditableOn();
			updateUser();
		} 
		else if (e.getSource().equals(searchUser)) {
			searchEmployee();
		}
		else if (e.getSource().equals(updateBtn)
				&& updateBtn.getText().equals("Update User")) {
			updateEmployee();
			editUserBtnsPanel.setVisible(true);
			setEditableOff();
		}  
		else if (e.getSource().equals(removeUser)) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to remove\n" + "        "
					+ forenameBx.getText() + " from the system",
					"Warning", dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {
				deleteContact();
				setFirst();
			}
		}
	}
}
