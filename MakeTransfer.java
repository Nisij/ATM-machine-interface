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

public class MakeTransfer {
	private static String accno = null;
	private JFrame frmMakeTransfer;
	private JTextField txtTransferAcc;
	private JTextField txtAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String accNo){
		accno=accNo;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeTransfer window = new MakeTransfer(accno);
					window.frmMakeTransfer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MakeTransfer(String MSG) {
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
	
	
	
	
	private void initialize() {
		frmMakeTransfer = new JFrame();
		frmMakeTransfer.setTitle("Make Transfer");
		frmMakeTransfer.setBounds(100, 100, 450, 300);
		frmMakeTransfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMakeTransfer.getContentPane().setLayout(null);
		
		JLabel lblAc = new JLabel("Account Number");
		lblAc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAc.setBounds(39, 40, 131, 23);
		frmMakeTransfer.getContentPane().add(lblAc);
		
		JLabel lblTransferAccountNo = new JLabel("Transfer Account No");
		lblTransferAccountNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTransferAccountNo.setBounds(39, 98, 136, 14);
		frmMakeTransfer.getContentPane().add(lblTransferAccountNo);
		
		txtTransferAcc = new JTextField();
		txtTransferAcc.setBounds(190, 96, 184, 20);
		frmMakeTransfer.getContentPane().add(txtTransferAcc);
		txtTransferAcc.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(203, 70, 160, 23);
		frmMakeTransfer.getContentPane().add(lblNewLabel);
		
		JLabel lblTransferAmount = new JLabel("Transfer Amount");
		lblTransferAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTransferAmount.setBounds(39, 151, 136, 14);
		frmMakeTransfer.getContentPane().add(lblTransferAmount);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(190, 149, 184, 20);
		frmMakeTransfer.getContentPane().add(txtAmount);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Amount=txtAmount.getText();
				String TAccNo=txtTransferAcc.getText();
				
				List<String> data =getData();
				int bal = data.indexOf(accno)+3;
				String balance=data.get(bal);
				
				int TAccbal = data.indexOf(TAccNo)+3;
				String Tbalance=data.get(TAccbal);
				
				
				if(Double.parseDouble(balance)>=Double.parseDouble(Amount)){
					
					double value = Double.parseDouble(balance)-Double.parseDouble(Amount);
					
					double Tvalue = Double.parseDouble(Tbalance)+Double.parseDouble(Amount);
					
					String value1=String.valueOf(value);
					String value2=String.valueOf(Tvalue);	
					
					
					
					data.set( bal, value1 );
					data.set( TAccbal, value2);
					
					boolean status=writeToFile((ArrayList<String>) data);
					if(status){
						JOptionPane.showMessageDialog(null, "Money Transfered Successfully");
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Insufficent Balance To Transfer");
					
				}
				
				
			}
		});
		btnTransfer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTransfer.setBounds(191, 197, 83, 23);
		frmMakeTransfer.getContentPane().add(btnTransfer);
		
		JLabel lblAccountNumber = new JLabel(accno);
		lblAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccountNumber.setBounds(191, 45, 159, 23);
		frmMakeTransfer.getContentPane().add(lblAccountNumber);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMakeTransfer.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(285, 198, 89, 22);
		frmMakeTransfer.getContentPane().add(btnExit);
	}

}
