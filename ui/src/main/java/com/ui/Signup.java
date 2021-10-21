package com.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class Signup extends JFrame {
	
	private static final long serialVersionUID = 1L;

	result cllbck;
	
	JFrame frame = new JFrame("Sign up");

	JLabel nameL = new JLabel("Name");
	JLabel emailL = new JLabel("Email");
	JLabel contactL = new JLabel("Contact");
	JLabel passwordL = new JLabel("Password");
	JLabel confirmPasswordL = new JLabel("Confirm password");
	
	JTextField nameT = new JTextField();
	JTextField emailT = new JTextField();
	String countryCodes[] = {"+91","+53"};
	JComboBox<String> countryCode = new JComboBox<String>(countryCodes);
	JTextField contactT = new JTextField();
	JPasswordField passwordT = new JPasswordField();
	JPasswordField confirmPasswordT = new JPasswordField();
	
	JButton submit = new JButton("Sign up");
	
	ActionListener signUp = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			String name = nameT.getText();
			String email = emailT.getText();
			String contact = contactT.getText();
			String code = countryCode.getSelectedItem().toString();
			String password = String.valueOf(passwordT.getPassword());
			String confirmPassword = String.valueOf(confirmPasswordT.getPassword());
			
			if(name.isEmpty()) {
				showMessage("Enter name");
			}else if(email.isEmpty()) {
				showMessage("Enter email id");
			}else if(parseEmail(email)) {
				showMessage("Invalid email id");
			}else if(contact.length() != 10) {
				showMessage("Invalid number");
			}else if(password.isEmpty()){
				showMessage("Enter password");
			}else if(confirmPassword.isEmpty()){
				showMessage("Renter password");
			}else if(!password.equals(confirmPassword)) {
				showMessage("Password mismatch");
			}else {
				signUpUser(name,email,code,contact,password);
			}
			
		}
		
	};
	
	Signup(result clbk){
		
		this.cllbck = clbk;
		
		nameL.setBounds(41, 10, 300, 30);
		nameT.setBounds(41, 40, 300, 30);
		
		emailL.setBounds(41, 80, 300, 30);
		emailT.setBounds(41, 110, 300, 30);
		
		contactL.setBounds(41, 120, 300, 30);
		countryCode.setBounds(41, 150, 55, 30);
		contactT.setBounds(96, 150, 250, 30);
		
		passwordL.setBounds(41, 190, 300, 30);
		passwordT.setBounds(41,220, 300, 30);
		
		confirmPasswordL.setBounds(41, 260, 300, 30);
		confirmPasswordT.setBounds(41, 290, 300, 30);
		
		submit.setBounds(41, 340, 300, 30);
		
		submit.addActionListener(signUp);
		
		frame.add(emailL);
		frame.add(emailT);
		
		frame.add(nameL);
		frame.add(nameT);
	
		frame.add(contactL);
		frame.add(countryCode);
		frame.add(contactT);
		
		frame.add(passwordL);		
		frame.add(passwordT);
		
		frame.add(confirmPasswordL);		
		frame.add(confirmPasswordT);
		
		frame.add(submit);
		
		frame.setSize(400,500);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
	    frame.setLayout(null);
		
		
		
	}
	
	protected void display(boolean flag) {
		frame.setVisible(flag);
	}
	
	
	private boolean parseEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return !matcher.matches();
	}
	
	private void signUpUser(String name,String email, String code,String contact,String password) {
		System.out.printf("%-10s : %s\n","Name",name);
		System.out.printf("%-10s : %s\n","Email",email);
		System.out.printf("%-10s : %s : %s\n","Contact",code,contact);
		System.out.printf("%-10s : %s\n\n","Password",password);
		Database db = Database.getInstance();
		if(db.addUser(name, email, code, contact, password))
			cllbck.login();
		
	}
	
	private void showMessage(String msg) {
		JOptionPane.showMessageDialog(frame,msg,"Alert",JOptionPane.WARNING_MESSAGE);
	}
	
}
