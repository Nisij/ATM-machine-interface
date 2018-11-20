import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class ChangePassword {
	private static String accno = null;
	private JFrame frmChangePassword;
	private JTextField txtNewPass;
	private JTextField txtConfirmPass;

	/**
	 * Launch the application.
	 */
	public static void main(String accNo) {
		accno=accNo;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword window = new ChangePassword(accno);
					window.frmChangePassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangePassword(String MSG) {
		initialize();
	}

	public List<String> getData(){
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
        
        
        List<String> lines = new ArrayList<String>();
        
        
       
        String AccNo="";
        int m=0;
        for(int i=0;i<newline.length;i++){
        	
        	System.out.println(newline[i]);
        	
        	String userpass=newline[i];
 		   
  		  	String upaArray[]= userpass.split("\\s+");
        	
        	
        	
        	
        	lines.add(upaArray[0]);
        	lines.add(upaArray[1]);
        	
        	
        	
        	
        }
		return lines;
        }
	
	public boolean writeToFile(ArrayList<String> list) {
	      try {
	         File file = new File("C:\\Users\\Admin\\workspace\\ATM\\src\\Password.txt");

	        
	         if (!file.exists()) {
	            file.createNewFile();
	         }

	         FileWriter fw = new FileWriter(file.getAbsoluteFile());
	         BufferedWriter bw = new BufferedWriter(fw);

	       
	      
	         for (int i = 0; i < list.size(); i++) {
	          
	            bw.write(list.get(i)+" "+list.get(i+1));
	            bw.newLine();
	            i++;
	         }
	         

	         bw.close();
	         return true;

	      } catch (IOException e) {
	         e.printStackTrace();
	         return false;
	      }
	   }
	
	
	
	private void initialize() {
		frmChangePassword = new JFrame();
		frmChangePassword.setTitle("Change Password");
		frmChangePassword.setBounds(100, 100, 450, 300);
		frmChangePassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChangePassword.getContentPane().setLayout(null);
		
		JLabel lblAccountNo = new JLabel("Account No :");
		lblAccountNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccountNo.setBounds(63, 39, 124, 23);
		frmChangePassword.getContentPane().add(lblAccountNo);
		
		JLabel lblAccNumber = new JLabel(accno);
		lblAccNumber.setBounds(200, 40, 134, 22);
		frmChangePassword.getContentPane().add(lblAccNumber);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewPassword.setBounds(63, 98, 124, 23);
		frmChangePassword.getContentPane().add(lblNewPassword);
		
		txtNewPass = new JTextField();
		txtNewPass.setBounds(200, 100, 156, 21);
		frmChangePassword.getContentPane().add(txtNewPass);
		txtNewPass.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfirmPassword.setBounds(63, 152, 124, 23);
		frmChangePassword.getContentPane().add(lblConfirmPassword);
		
		txtConfirmPass = new JTextField();
		txtConfirmPass.setColumns(10);
		txtConfirmPass.setBounds(200, 154, 156, 21);
		frmChangePassword.getContentPane().add(txtConfirmPass);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPass=txtNewPass.getText();
				String confirmPass=txtConfirmPass.getText();
				
				
				
				if(newPass.equals(confirmPass)){
					List<String> data =getData();
					int pass = data.indexOf(accno)+1;
					//String password=data.get(pass);
					
					data.set( pass,confirmPass);
					
					boolean status=writeToFile((ArrayList<String>) data);
					if(status){
						JOptionPane.showMessageDialog(null, "Password Changed Successfully");
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null, "You Must Enter The Same Password");
				}
				
			}
		});
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChange.setBounds(199, 205, 89, 23);
		frmChangePassword.getContentPane().add(btnChange);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmChangePassword.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(298, 206, 58, 22);
		frmChangePassword.getContentPane().add(btnExit);
	}
}
