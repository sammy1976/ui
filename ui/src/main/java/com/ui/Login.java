package com.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	result s;

	JFrame frame = new JFrame("Login");
	
	JLabel emailL = new JLabel("Email");
	JLabel passwordL = new JLabel("Password");
	
	JTextField emailT = new JTextField();
	JPasswordField passwordT = new JPasswordField();
	
	JButton submit = new JButton("Login");
	JLabel signup = new JLabel("sign up");
	
	ActionListener login = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			
			Database db = Database.getInstance();
			String email = emailT.getText();
			String password = String.valueOf(passwordT.getPassword());
			if(db.login(email, password)) {
				JOptionPane.showMessageDialog(frame,"Logged in","Alert",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(frame,"Failed to login","Alert",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	};
	
	MouseListener signUp = new MouseListener() {

		public void mouseClicked(MouseEvent e) {
			s.getResult(false);
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	
	Login(result s){
		this.s = s;
		emailL.setBounds(41, 10, 300, 30);
		emailT.setBounds(41, 40, 300, 30);
		passwordL.setBounds(41, 90, 300, 30);
		passwordT.setBounds(41, 120, 300, 30);
		submit.setBounds(41, 180, 300, 30);
		signup.setBounds(41, 210,300,30);
		
		submit.addActionListener(login);
		signup.addMouseListener(signUp);
		
		frame.add(emailL);
		frame.add(emailT);
		frame.add(passwordL);
		frame.add(passwordT);
		frame.add(submit);
		frame.add(signup);
		
		frame.setSize(400,330);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
	    frame.setLayout(null);
	}
	
	
	
	protected void display(boolean flag) {
		frame.setVisible(flag);
	}
}
