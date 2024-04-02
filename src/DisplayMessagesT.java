import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/*Display the messages that belong teacher*/
public class DisplayMessagesT extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JScrollPane pane;
	private JTable jTable1_1;
	private JTableHeader header;

	private DefaultTableModel model;

	private static Statement st;
	private static ResultSet rs;
	private JPanel panel;

	private JLabel label;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public DisplayMessagesT(Connection con, Teacher teacher) {
		UniOnline uni = Main.db.uni;
		String query = "select senderID, receiverID, msgtext from messages where senderID = ? or receiverID = ? ;";
		String col[] = { "SenderID", "	ReceiverID", "Message" };
		
		panel = new JPanel();
		panel.setBounds(0, 0, 500, 750);
		
		frame = new JFrame("All Messages");
		frame.getContentPane().setLayout(null);
	
		model = new DefaultTableModel(col, 0);
		
		
		
		try {
			st = con.createStatement();

			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, teacher.getTeacher_id());
			pst.setString(2, teacher.getTeacher_id());
			rs = pst.executeQuery();
			while (rs.next()) {
								String sender = rs.getString("senderID");
				String receiver = rs.getString("receiverID");
				String message = rs.getString("msgtext");


				Object[] row = { sender, receiver, message };
				model.addRow(row);
				

			}
			


		} catch (SQLException e) {

			e.printStackTrace();
		}


		
		

		
		jTable1_1 = new JTable();
		jTable1_1.setForeground(Color.BLACK);

		jTable1_1.setModel(model);
		header = jTable1_1.getTableHeader();
		header.setBackground(Color.YELLOW);
		panel.setLayout(null);

		pane = new JScrollPane(jTable1_1);
		pane.setBounds(12, 324, 476, 373);
		jTable1_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		panel.add(pane);

		label = new JLabel("New label");
		pane.setColumnHeaderView(label);
		frame.getContentPane().add(panel);

		JButton btnComposeEmal = new JButton("COMPOSE EMAIL");
		btnComposeEmal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!textField.getText().matches(teacher.getTeacher_id())) {
					JOptionPane.showMessageDialog(frame, "Wrong TA ID!");
				} else {
					if (!textField.getText().equals("") && !textField_1.getText().equals("")
							&& !textField_2.getText().equals("")) {
						model.addRow(
								new Object[] { textField.getText(), textField_1.getText(), textField_2.getText() });
						int id = model.getRowCount() + 1;
						Messages m = new Messages(id, textField_2.getText(), textField.getText(),
								textField_1.getText());

						uni.add("messages", m);

						JOptionPane.showMessageDialog(frame, "Successfully sent!");

						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");

					} else {
						JOptionPane.showMessageDialog(frame, "All texts must be filled!");
					}
				}
			}
		});

		btnComposeEmal.setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		btnComposeEmal.setBounds(333, 241, 155, 59);
		panel.add(btnComposeEmal);

		textField = new JTextField();
		textField.setBounds(12, 66, 116, 22);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(179, 66, 116, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(12, 113, 309, 187);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel = new JLabel("From:");
		lblNewLabel.setBounds(12, 37, 116, 16);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("To:");
		lblNewLabel_1.setBounds(179, 37, 116, 16);
		panel.add(lblNewLabel_1);

		frame.setSize(500, 750);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);

		frame.setVisible(true);

	}
}

