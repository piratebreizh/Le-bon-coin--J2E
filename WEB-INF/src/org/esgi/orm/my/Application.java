package org.esgi.orm.my;

import java.util.Arrays;

import org.esgi.orm.my.model.User;



public class Application {
	public static void main(String[] args) {
		
		
		User u = new User("Alex","123"), u2;

		System.out.println(Arrays.toString(User.class.getAnnotations()));
		
		u = (User) ORM.save(u);
		
		
		
		System.out.println(u);
		/*u.password = "TOTO";
		ORM.save(u);
		u2 = (User) ORM.load(User.class, u.id);*/
		//System.out.println(u2); // Display toto as password.
		
		//System.out.println("FALSE "+(u == u2));
		
		//System.out.println(Arrays.toString(User.class.getDeclaredFields()));
		
	}
}
