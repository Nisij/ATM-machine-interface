import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MainForm {
	private static String accno = null;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	Map<String, Object> Account_info        = new HashMap<String, Object>();
    Map<String, String[]> Account_data       = new HashMap<String, String[]>();
	public void main(String accNo) {
		 accno=accNo;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm(accno);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param accno 
	 */
	public MainForm(String MSg) {
		
		initialize(accno);
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
        		Account_data.put(AccNo, arr);
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
        
        
       

//       
//        int j = lines.indexOf("111859621")+1;
//        System.out.println(j+"---");
//		
//        lines.set( 2, "New" );
//        
//        for(int i=0;i<lines.size();i++){
//        	System.out.println(i+"---"+lines.get(i));
//        }
        return lines;
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String acc_no) {
		frame = new JFrame();
		frame.setBounds(100, 100, 580, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			private DepositForm dp;

			public void actionPerformed(ActionEvent e) {
				List<String> data =getData();
				
				int j = data.indexOf("111859621")+1;
				System.out.println(j+"---");
				
				data.set( 2, "New" );
		        
		        for(int i=0;i<data.size();i++){
		        	System.out.println(i+"---"+data.get(i));
		        }
		        
		        //String AccNo="111859621";
		        
		       dp = new DepositForm(acc_no);
		       dp.main(acc_no);
		       
				
				
			}

			
		});
		btnDeposit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDeposit.setBounds(60, 44, 180, 76);
		frame.getContentPane().add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  WithdrawForm dp = new WithdrawForm(acc_no);
			       WithdrawForm.main(acc_no);
			}
		});
		btnWithdraw.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnWithdraw.setBounds(323, 44, 180, 76);
		frame.getContentPane().add(btnWithdraw);
		
		JButton btnCheckBalance = new JButton("Check Balance");
		btnCheckBalance.addActionListener(new ActionListener() {
			private CheckBalance dp;

			public void actionPerformed(ActionEvent arg0) {
				 dp = new CheckBalance(acc_no);
				 CheckBalance.main(acc_no);
				
				
				
			}
		});
		btnCheckBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCheckBalance.setBounds(60, 170, 180, 82);
		frame.getContentPane().add(btnCheckBalance);
		
		JButton btnMakeTransfer = new JButton("Make A Transfer");
		btnMakeTransfer.addActionListener(new ActionListener() {
			private MakeTransfer mk;

			public void actionPerformed(ActionEvent e) {
				mk = new MakeTransfer(acc_no);
				MakeTransfer.main(acc_no);
				
			}
		});
		btnMakeTransfer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMakeTransfer.setBounds(323, 170, 180, 76);
		frame.getContentPane().add(btnMakeTransfer);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassword cp = new ChangePassword(acc_no);
				ChangePassword.main(acc_no);
			}
		});
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChangePassword.setBounds(60, 290, 180, 76);
		frame.getContentPane().add(btnChangePassword);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogOut.setBounds(323, 290, 180, 76);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblNewLabel = new JLabel("Acc.No :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(323, 0, 96, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAccountNo = new JLabel(accno);
		lblAccountNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccountNo.setBounds(429, 4, 125, 17);
		frame.getContentPane().add(lblAccountNo);
	}
}
