import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckBalance {
	private static String accno = null;
	private JFrame frmCheckBalance;

	/**
	 * Launch the application.
	 */
	public static void main(String accNo) {
		accno=accNo;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckBalance window = new CheckBalance(accno);
				
					window.frmCheckBalance.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CheckBalance(String MSG) {
	
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
//        		System.out.println(newline[i]);
//        		System.out.println(m);
        	}
    		else{
    			lines.add(newline[i]);
    			m++;
//    			System.out.println(newline[i]);
//    			System.out.println(m);
    		}
        	
        	
        	
        }
		return lines;
        }
	
	public void writeToFile(ArrayList<String> list) {
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

	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	
	
	private void initialize() {
		frmCheckBalance = new JFrame();
		frmCheckBalance.setTitle("Check Balance");
		frmCheckBalance.setBounds(100, 100, 450, 300);
		frmCheckBalance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCheckBalance.getContentPane().setLayout(null);
		
		
		
		
		
		
		
		
		
		JLabel lblAccountNumber = new JLabel("Account Number ");
		lblAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccountNumber.setBounds(59, 51, 141, 26);
		frmCheckBalance.getContentPane().add(lblAccountNumber);
		
		JLabel lblBalancek = new JLabel("Balance");
		lblBalancek.setVisible(false);
		lblBalancek.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBalancek.setBounds(59, 99, 141, 26);
		frmCheckBalance.getContentPane().add(lblBalancek);
		
		JLabel lblAccNo = new JLabel(accno);
		lblAccNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccNo.setBounds(236, 51, 141, 26);
		frmCheckBalance.getContentPane().add(lblAccNo);
		
		JLabel lblBalance = new JLabel("");
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBalance.setBounds(236, 99, 129, 21);
		frmCheckBalance.getContentPane().add(lblBalance);
		
		JButton btnCheckBalance = new JButton("Check Balance");
		btnCheckBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> data =getData();
				int bal = data.indexOf(accno)+3;
				String balance=data.get(bal);
				lblBalance.setText(balance);
				lblBalancek.setVisible(true);
			}
		});
		btnCheckBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCheckBalance.setBounds(174, 155, 123, 24);
		frmCheckBalance.getContentPane().add(btnCheckBalance);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCheckBalance.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(304, 156, 89, 23);
		frmCheckBalance.getContentPane().add(btnExit);
	}

}
