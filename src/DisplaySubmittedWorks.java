import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/*Display the submitted works that given assignments*/
public class DisplaySubmittedWorks extends JFrame {


	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JScrollPane pane;
	private JTable jTable1;
	private JTableHeader header;

	private DefaultTableModel model;

	private static Statement st;
	private static ResultSet rs;
	private JPanel panel;

	private JLabel label;

	public DisplaySubmittedWorks(Connection con, Teacher teacher) {
		panel = new JPanel();
		panel.setBounds(0, 0, 500, 750);
		String query = "select assignment.id, assignment.name, assignment.course_id, student.student_number, student.first_name, student.last_name, assignment_student.assn_text from assignment, student, assignment_student where assignment_student.student_id = student.student_number and  assignment.id =assignment_student.id and assignment_student.assn_status = \"completed\" and assignment.teacher_id = ?;";

		frame = new JFrame("All Assignments");
		frame.getContentPane().setLayout(null);

		String col[] = { "AssnID", "	Name", "CourseID", "St_Number", "St_Name", "St_Surname", "AssnText" };

		model = new DefaultTableModel(col, 0);

		try {
			st = con.createStatement();

			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, teacher.getTeacher_id());

			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("assignment.id");
				String name = rs.getString("assignment.name");
				String course_id = rs.getString("assignment.course_id");
				String st_no = rs.getString("student.student_number");
				String st_fn = rs.getString("student.first_name");
				String st_ln = rs.getString("student.last_name");
				String txt = rs.getString("assignment_student.assn_text");

				String ta_id = teacher.getTeacher_id();

				Object[] row = { id, name, course_id, st_no, st_fn, st_ln, txt };
				model.addRow(row);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		jTable1 = new JTable();

		jTable1.setModel(model);

		header = jTable1.getTableHeader();
		header.setBackground(Color.yellow);
		panel.setLayout(null);

		pane = new JScrollPane(jTable1);
		pane.setBounds(12, 13, 476, 724);
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		panel.add(pane);

		label = new JLabel("New label");
		pane.setColumnHeaderView(label);
		frame.getContentPane().add(panel);

		frame.setSize(500, 750);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frame.setVisible(true);
	}

}
