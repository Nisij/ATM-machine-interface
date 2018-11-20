import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class LoginForm {
	private static String accno = null;
	private JFrame frmLoginAuthantication;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmLoginAuthantication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	public List<String> getData(){
		File file=new File("C:\\Users\\Admin\\workspace\\ATM\\src\\AccountInformation.txt");
        Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        sc.useDelimiter("\\Z");
        String str = sc.next();
        String[] newline=str.split("[\r\n]+");
        
        
        List<String> lines = new ArrayList<String>();
        
        
       
        String AccNo="";
        int m=0;
        for(int i=0;i<newline.length;i++){
        	
        	//System.out.println(newline[i]);
        	
        	if(i==0){
    			AccNo=newline[i];
        		lines.add(newline[i]);
        		m=0;
//        		System.out.println(newline[i]);
//        		System.out.println(m);
    		}
        	
        	else if(m==4){
        		lines.add(newline[i]);
        		String[] arr = lines.toArray(new String[0]);
        		//Account_data.put(AccNo, arr);
        		m++;
//        		System.out.println(newline[i]);
//        		System.out.println("aaaa"+m);
        	}
        	else if(m==5){
        		AccNo=newline[i];
        		lines.add(newline[i]);
        		m=0;
//        		
        	}
    		else{
    			lines.add(newline[i]);
    			m++;
//    			
    		}
        	
        	
        	
        }
		return lines;
        }
	
	
	
	private void initialize() {
		frmLoginAuthantication = new JFrame();
		frmLoginAuthantication.setTitle("Login Authantication");
		frmLoginAuthantication.setBounds(100, 100, 544, 338);
		frmLoginAuthantication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginAuthantication.getContentPane().setLayout(null);
		
		JLabel lblLoginAuthantication = new JLabel("Login Authantication");
		lblLoginAuthantication.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoginAuthantication.setBounds(183, 23, 204, 27);
		frmLoginAuthantication.getContentPane().add(lblLoginAuthantication);
		
		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccountNumber.setBounds(65, 122, 121, 14);
		frmLoginAuthantication.getContentPane().add(lblAccountNumber);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(65, 161, 121, 14);
		frmLoginAuthantication.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(208, 120, 204, 20);
		frmLoginAuthantication.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(208, 159, 204, 20);
		frmLoginAuthantication.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File loginf = new File("C:\\Users\\Admin\\workspace\\ATM\\src\\Password.txt");
					
				File file=new File("C:\\Users\\Admin\\workspace\\ATM\\src\\Password.txt");
		        Scanner sc = null;
				try {
					sc = new Scanner(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        sc.useDelimiter("\\Z");
		        String str = sc.next();
		        String[] newline=str.split("[\r\n]+");
		        
		        String user="";
		        String Pass="";
		        boolean login = false;
		        boolean active=false;
		        
		        List<String> data =getData();
				int isActive = data.indexOf(accno)+5;
				String status=data.get(isActive);
				
				
		        
		        
		        
		       for(int i=0;i<newline.length;i++){
		    	   
		    		 String userpass=newline[i];
		    		   
		    		 String upaArray[]= userpass.split("\\s+");
		    		  
		    		  
		    		  
		    		  user=upaArray[0];
		    		  Pass=upaArray[1];
		    		  
		    		  if(textField.getText().equals(user) && textField_1.getText().equals(Pass) ){
		    			  
		    			
		    			if(status.equals("Desable")){
		    				JOptionPane.showMessageDialog(null, "ATM Access Not Active");
		    				
		    			}
		    			
		    			else{
		    				accno=user;
			    			login = true;

			  				  
			    			  active=true;
		                      break; 
		    				
		    			}
		  				
		  				

		    		  }
		    		  
		    		  
		    		  
		    	   
		    		  
		    	   
		       }
		       
		       if(login==true){
                   //MainForm.main(null);
		    	   
		    	  
		    	    MainForm dp = new MainForm(accno);
		    	    dp.main(accno);
		    	    frmLoginAuthantication.dispose();
    		  }
		       
		      
                else {
                   JOptionPane.showMessageDialog(null, "Incorrect username or password");
                   textField.setText("");
                   textField_1.setText("");
                }

            sc.close();
		       
		        
		       

	       }
				
				
		
				
			
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(208, 200, 89, 23);
		frmLoginAuthantication.getContentPane().add(btnLogin);
	}
}
