import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepositForm {

	private static String accno = null;
	private JFrame frmDepositMoney;
	private JTextField txtDeposit;

	/**
	 * Launch the application.
	 */
	public void main(String accNo) {
		 accno=accNo;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositForm window = new DepositForm(accno);
					window.frmDepositMoney.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DepositForm(String MSg) {
		
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
	
	
	
	
	
	private void createFile(String file, ArrayList<String> arrData)
            throws IOException {
        FileWriter writer = new FileWriter(file + ".txt");
        int size = arrData.size();
        for (int i=0;i<size;i++) {
            String str = arrData.get(i).toString();
            writer.write(str+"\n");
           // writer.write("\n");
            if(i < size-1)//This prevent creating a blank like at the end of the file**
                writer.write("\n");
        }
        writer.close();
    }
	
	
	
	private void initialize() {
		frmDepositMoney = new JFrame();
		frmDepositMoney.setTitle("Deposit Money");
		frmDepositMoney.setBounds(100, 100, 450, 300);
		frmDepositMoney.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDepositMoney.getContentPane().setLayout(null);
		
		JLabel lblAccountNo = new JLabel("Account No:");
		lblAccountNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccountNo.setBounds(51, 54, 134, 35);
		frmDepositMoney.getContentPane().add(lblAccountNo);
		
		JLabel lblNewLabel = new JLabel("Deposit Amount");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(51, 121, 134, 26);
		frmDepositMoney.getContentPane().add(lblNewLabel);
		
		txtDeposit = new JTextField();
		txtDeposit.setBounds(202, 125, 171, 20);
		frmDepositMoney.getContentPane().add(txtDeposit);
		txtDeposit.setColumns(10);
		
		JLabel lblAccNo = new JLabel(accno);
		lblAccNo.setBounds(203, 65, 170, 20);
		frmDepositMoney.getContentPane().add(lblAccNo);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String DepAmount=txtDeposit.getText();
				
				List<String> data =getData();
				int bal = data.indexOf(accno)+3;
				String balance=data.get(bal);
				
				double value = Double.parseDouble(balance)+Double.parseDouble(DepAmount);
				
				String newBal=String.valueOf(value);
						
				
				System.out.println("Deposited new balance is"+"---"+newBal);
				
				data.set( bal, newBal );
				
				boolean status=writeToFile((ArrayList<String>) data);
				if(status){
					JOptionPane.showMessageDialog(null, "Money Deposited Successfully");
				}
				
		        for(int i=0;i<data.size();i++){
//		        	System.out.println(i+"---"+data.get(i));
		        }
				
				
				
			}
		});
		btnDeposit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDeposit.setBounds(202, 174, 89, 23);
		frmDepositMoney.getContentPane().add(btnDeposit);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDepositMoney.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(303, 175, 70, 20);
		frmDepositMoney.getContentPane().add(btnNewButton);
	}
}
