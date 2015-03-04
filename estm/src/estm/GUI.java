package estm;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfLehrer;
	private JTextField tfZeitpunkt;
	private JTable table;
	private Verbindung verbindung;
	private Person user;
	private JButton btnWuenschen;
	private JButton btnUpdate;
	private ESTM estm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI(Verbindung verbindung, Person user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		this.verbindung = verbindung;
		this.user = user;
		estm = new ESTM(verbindung, user);
		
		tfLehrer = new JTextField();
		tfLehrer.setColumns(10);

		tfZeitpunkt = new JTextField();
		tfZeitpunkt.setColumns(10);

		btnWuenschen = new JButton("W\u00FCnschen");
		btnWuenschen.addActionListener(this);

		JLabel lblNewLabel = new JLabel("Lehrer");

		JLabel lblNewLabel_1 = new JLabel("Zeitpunkt");

		table = new JTable();

		btnUpdate = new JButton("update");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								gl_contentPane
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												tfLehrer,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												tfZeitpunkt,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												lblNewLabel_1)
																										.addComponent(
																												btnWuenschen))
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												lblNewLabel)
																										.addGap(55)))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				table,
																				GroupLayout.DEFAULT_SIZE,
																				449,
																				Short.MAX_VALUE))
														.addComponent(
																btnUpdate,
																Alignment.TRAILING))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				btnUpdate)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				table,
																				GroupLayout.DEFAULT_SIZE,
																				295,
																				Short.MAX_VALUE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(79)
																		.addComponent(
																				lblNewLabel)
																		.addGap(5)
																		.addComponent(
																				tfLehrer,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(7)
																		.addComponent(
																				lblNewLabel_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				tfZeitpunkt,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(26)
																		.addComponent(
																				btnWuenschen)))
										.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnWuenschen)) {
			verbindung = new Verbindung();
			String lehrer = tfLehrer.getText();
			String zeitpunkt = tfZeitpunkt.getText();
			String elterID = "" + user.getID();
			String lehrerID = "";
			ResultSet rs = verbindung
					.query("SELECT COUNT(*) FROM personen WHERE `name`="
							+ lehrer + " AND status='L'");
			try {
				if (rs.getInt("COUNT(*)") > 1) {
					verbindung.closeStatement();
					// TODO dialog zur spezifizierung des Lehreres ˆffnen!
				} else {
					verbindung.closeStatement();
					lehrerID = ""
							+ verbindung.query(
									"SELECT `ID` FROM personen WHERE `name`="
											+ lehrer + " AND status='L'")
									.getInt("ID");
					verbindung.closeStatement();
				}
				rs = verbindung
						.query("SELECT COUNT(*) FROM terminwunsch WHERE (`person1`="
								+ lehrerID
								+ " AND `person2`="
								+ elterID
								+ ")OR(`person2`="
								+ lehrerID
								+ " AND `person1`=" + elterID + ")");
				if (rs.getInt("COUNT(*)") > 0) {
					verbindung.closeStatement();
					System.out.println("USER-ERROR: Sie sind scheiﬂe!");
				} else {
					verbindung.closeStatement();
					estm.insertTerminwunschIntoDatabase(new Termin(Integer.parseInt(zeitpunkt),user,estm.getLehrer(Integer.parseInt(lehrerID))));
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
