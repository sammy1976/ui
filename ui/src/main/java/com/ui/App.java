package com.ui;

public class App implements result{
	
	static Login l;
	static Signup s;

	public static void main( String[] args )
    {
		App a = new App();
		l = new Login(a);
		s = new Signup(a);
		
		
		boolean flag = true;
		l.display(flag);
		s.display(!flag);
    }

	public void getResult(boolean res) {
		l.display(res);
		s.display(!res);
	}

	public void home() {
		// TODO Auto-generated method stub
		
	}

	public void login() {
		s.display(false);
		l.display(true);
	}

	public void signUp() {
		s.display(true);
		l.display(false);
		
	}
	
}
