import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/*Display announcements that student enrolled classes.*/
public class StudentDisplayAnnouncements extends JFrame {
	
	private JFrame frame;
	private JScrollPane pane;
	private JTable jTable1;
	private JTableHeader header;

	private DefaultTableModel model;

	private static Statement st;
	private static ResultSet rs;
	private JPanel panel;

	private ArrayList<Announcement> lst;
	private ArrayList<AnnouncementStudent> lstStu;
	private ArrayList<Announcement> lstAnn;
	private JLabel label;
	
	public StudentDisplayAnnouncements( Student s) {
		panel = new JPanel();
		panel.setBounds(0, 0, 500, 500);
		lst = new ArrayList<Announcement>();
		lstStu = Main.db.uni.getAnnouncement_student();
		lstAnn = Main.db.uni.getAnnouncements();
		for (int i = 0; i < lstStu.size(); i++) {	
			if(lstStu.get(i).getStudent_id().equals(s.getStudent_number()))	//find student announcements from student number
			{
				for (int j = 0; j < lstAnn.size(); j++) {
					if(lstAnn.get(j).getID()==lstStu.get(i).getAnnounce_id())	//find announcement from announcement id in student announcement
					{
						lst.add(lstAnn.get(j));
					}
				}
			}
		}

		frame = new JFrame("All Announcements");
		frame.getContentPane().setLayout(null);

		String col[] = { "Announcement ID", "Name", "Info", "CourseID","TA ID" };

		model = new DefaultTableModel(col, 0);

		for (int i = 0; i < lst.size(); i++) {

			int id = lst.get(i).getID();
			String name = lst.get(i).getName();
			String info = lst.get(i).getInfo();
			String course_id = lst.get(i).getCourseID();
			String ta_id = lst.get(i).getTaID();

			Object[] row = { id, name, info, course_id,ta_id };
			model.addRow(row);
		}

		jTable1 = new JTable();

		
		jTable1.setModel(model);

		header = jTable1.getTableHeader();
		header.setBackground(Color.yellow);
		panel.setLayout(null);

		pane = new JScrollPane(jTable1);
		pane.setBounds(12, 12, 476, 458);
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		panel.add(pane);

		label = new JLabel("New label");
		pane.setColumnHeaderView(label);
		frame.getContentPane().add(panel);

		frame.setSize(500, 500);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frame.setVisible(true);
	}
	
	
}
