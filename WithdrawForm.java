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

public class WithdrawForm {
	private static String accno = null;
	private JFrame frmWithdrawMoney;
	private JTextField txtWithdraw;

	/**
	 * Launch the application.
	 */
	public static void main(String accNo) {
		 accno=accNo;
		EventQueue.invokeLater(new Runnable() {
			 
			public void run() {
				try {
					WithdrawForm window = new WithdrawForm(accno);
					window.frmWithdrawMoney.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WithdrawForm(String MSG) {
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
        		System.out.println(newline[i]);
        		System.out.println(m);
    		}
        	
        	else if(m==4){
        		lines.add(newline[i]);
        		String[] arr = lines.toArray(new String[0]);
        		//Account_data.put(AccNo, arr);
        		m++;
        		System.out.println(newline[i]);
        		System.out.println("aaaa"+m);
        	}
        	else if(m==5){
        		AccNo=newline[i];
        		lines.add(newline[i]);
        		m=0;
        		System.out.println(newline[i]);
        		System.out.println(m);
        	}
    		else{
    			lines.add(newline[i]);
    			m++;
    			System.out.println(newline[i]);
    			System.out.println(m);
    		}
        	
        	
        	
        }
		return lines;
        }
	
	public boolean writeToFile(ArrayList<String> list) {
	      try {
	         File file = new File("C:\\Users\\Admin\\workspace\\ATM\\src\\AccountInformation.txt");

	        
	         if (!file.exists()) {
	            file.createNewFile();
	         }

	         FileWriter fw = new FileWriter(file.getAbsoluteFile());
	         BufferedWriter bw = new BufferedWriter(fw);

	       
	      
	         for (int i = 0; i < list.size(); i++) {
	          
	            bw.write(list.get(i));
	            bw.newLine();
	            
	         }
	         

	         bw.close();
	         return true;

	      } catch (IOException e) {
	         e.printStackTrace();
	         return false;
	      }
	   }
	
	
	
	private void initialize() {
		frmWithdrawMoney = new JFrame();
		frmWithdrawMoney.setTitle("Withdraw Money");
		frmWithdrawMoney.setBounds(100, 100, 450, 300);
		frmWithdrawMoney.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWithdrawMoney.getContentPane().setLayout(null);
		
		JLabel lblAccountNumber = new JLabel("Account Number ");
		lblAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccountNumber.setBounds(61, 59, 141, 22);
		frmWithdrawMoney.getContentPane().add(lblAccountNumber);
		
		JLabel lblNewLabel = new JLabel("Withdraw Amount");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(61, 119, 141, 22);
		frmWithdrawMoney.getContentPane().add(lblNewLabel);
		
		JLabel lblAccNo = new JLabel(accno);
		lblAccNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccNo.setBounds(212, 59, 141, 17);
		frmWithdrawMoney.getContentPane().add(lblAccNo);
		
		txtWithdraw = new JTextField();
		txtWithdraw.setBounds(211, 121, 162, 20);
		frmWithdrawMoney.getContentPane().add(txtWithdraw);
		txtWithdraw.setColumns(10);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String WithAmount=txtWithdraw.getText();
				
				List<String> data =getData();
				int bal = data.indexOf(accno)+3;
				String balance=data.get(bal);
				
				if(Double.parseDouble(balance)>=Double.parseDouble(WithAmount)){
					
					double value = Double.parseDouble(balance)-Double.parseDouble(WithAmount);
					
					String newBal=String.valueOf(value);
							
					
					System.out.println("Withdraw Amount is"+"---"+newBal);
					
					data.set( bal, newBal );
					
					boolean status=writeToFile((ArrayList<String>) data);
					if(status){
						JOptionPane.showMessageDialog(null, "Money Withdraw Successfully");
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Insufficent Balance");
					
				}
				
				
				
		        
		        for(int i=0;i<data.size();i++){
		        	System.out.println(i+"---"+data.get(i));
		        }
				
				
			}
		});
		btnWithdraw.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnWithdraw.setBounds(212, 172, 93, 23);
		frmWithdrawMoney.getContentPane().add(btnWithdraw);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmWithdrawMoney.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(315, 173, 63, 23);
		frmWithdrawMoney.getContentPane().add(btnNewButton);
	}

}
