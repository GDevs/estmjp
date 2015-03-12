package estm;

import java.awt.Choice;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Elter_Interface extends JFrame{
	
	private JButton speichern, update;
	private Choice choice_D1, choice_D2, choice_D3, choice_D4, choice_D5, choice_D6, choice_D7;
	private Choice choice_L1, choice_L2, choice_L3, choice_L4, choice_L5, choice_L6, choice_L7;
	private Choice[][] c ={{choice_D1, choice_D2, choice_D3, choice_D4, choice_D5, choice_D6, choice_D7},
	                      {choice_L1, choice_L2, choice_L3, choice_L4, choice_L5, choice_L6, choice_L7}};
	private JLabel username;
	public Elter_Interface() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setType(Type.UTILITY);
		setTitle("Elternsprechtags-Manager");
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 501, 284);
		
		choice_L1 = new Choice();
		choice_L1.setBounds(10, 66, 160, 20);
		getContentPane().add(choice_L1);
		
		choice_L2 = new Choice();
		choice_L2.setBounds(10, 92, 160, 20);
		getContentPane().add(choice_L2);
		
		choice_L3 = new Choice();
		choice_L3.setBounds(10, 118, 160, 20);
		getContentPane().add(choice_L3);
		
		choice_L4 = new Choice();
		choice_L4.setBounds(10, 144, 160, 20);
		getContentPane().add(choice_L4);
		
		choice_L5 = new Choice();
		choice_L5.setBounds(10, 170, 160, 20);
		getContentPane().add(choice_L5);
		
		choice_L6 = new Choice();
		choice_L6.setBounds(10, 196, 160, 20);
		getContentPane().add(choice_L6);
		
		choice_L7 = new Choice();
		choice_L7.setBounds(10, 222, 160, 20);
		getContentPane().add(choice_L7);
		
		choice_D1 = new Choice();
		choice_D1.setBounds(199, 66, 160, 20);
		getContentPane().add(choice_D1);
		
		choice_D2 = new Choice();
		choice_D2.setBounds(199, 92, 160, 20);
		getContentPane().add(choice_D2);
		
		choice_D3 = new Choice();
		choice_D3.setBounds(199, 118, 160, 20);
		getContentPane().add(choice_D3);
		
		choice_D4 = new Choice();
		choice_D4.setBounds(199, 144, 160, 20);
		getContentPane().add(choice_D4);
		
		choice_D5 = new Choice();
		choice_D5.setBounds(199, 170, 160, 20);
		getContentPane().add(choice_D5);
		
		choice_D6 = new Choice();
		choice_D6.setBounds(199, 196, 160, 20);
		getContentPane().add(choice_D6);
		
		choice_D7 = new Choice();
		choice_D7.setBounds(199, 222, 160, 20);
		getContentPane().add(choice_D7);
		
		JLabel lblNewLabel = new JLabel("Lehrer");
		lblNewLabel.setBounds(10, 46, 69, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Uhrzeit/Datum");
		lblNewLabel_1.setBounds(199, 46, 160, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("1");
		label.setBounds(365, 66, 20, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("2");
		label_1.setBounds(365, 92, 20, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("3");
		label_2.setBounds(365, 118, 20, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("4");
		label_3.setBounds(365, 144, 20, 14);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("5");
		label_4.setBounds(365, 170, 20, 14);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("6");
		label_5.setBounds(365, 196, 20, 14);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("7");
		label_6.setBounds(365, 222, 20, 14);
		getContentPane().add(label_6);
		
		speichern = new JButton("Speichern");
		speichern.setBounds(395, 219, 89, 23);
		getContentPane().add(speichern);
		
		update = new JButton("Update");
		update.setBounds(395, 193, 89, 23);
		getContentPane().add(update);
		
		username = new JLabel("New label");
		username.setBounds(10, 11, 200, 14);
		getContentPane().add(username);
	}
	
	public JButton getSpeichern(){ return speichern;}
	public JButton getUpdate(){ return update;}
	public Choice[][] getChoises(){ return c;}
	public void setUsername(String p){ username.setText(p);}
	
	
}
