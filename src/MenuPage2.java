import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
/*Menu page for students*/
public class MenuPage2 extends JFrame {

	private JFrame frame;

	private JRadioButton rbtnViewC;
	private JRadioButton rbtnViewAs;
    private JRadioButton rbtnViewAn;
    private JRadioButton rbtnMsg;
    public MenuPage2(Connection con,Student student) 
	{
		
		frame = new JFrame();
		frame.setBounds(100, 100, 453, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMenu = new JLabel("STUDENT MENU");
		lblMenu.setBounds(123, 30, 112, 14);
		frame.getContentPane().add(lblMenu);
		
	
		rbtnViewC = new JRadioButton("View courses");
		rbtnViewC.setBounds(60, 77, 175, 23);
		frame.getContentPane().add(rbtnViewC);
		
		rbtnViewAs = new JRadioButton("View assignments");
		rbtnViewAs.setBounds(60, 105, 175, 23);
		frame.getContentPane().add(rbtnViewAs);
		
		rbtnViewAn = new JRadioButton("View announcements");
		rbtnViewAn.setBounds(60, 133, 186, 23);
		frame.getContentPane().add(rbtnViewAn);

		rbtnMsg = new JRadioButton("Messages");
		rbtnMsg.setBounds(60, 161, 109, 23);
		frame.getContentPane().add(rbtnMsg);


		
		ButtonGroup btnG = new ButtonGroup();
		btnG.add(rbtnViewC);
		btnG.add(rbtnViewAs);
		btnG.add(rbtnViewAn);
		btnG.add(rbtnMsg);

		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(rbtnViewC.isSelected())		//open cources
				{
					StudentDisplayCourses sdc = new StudentDisplayCourses(student);
				}
				else if(rbtnViewAs.isSelected())	//open assignments
				{
					StudentDisplayAssignments sda= new StudentDisplayAssignments(student);
				}
				else if(rbtnViewAn.isSelected())	//open announcements
				{
					StudentDisplayAnnouncements sdann= new StudentDisplayAnnouncements(student);
				}
				else if(rbtnMsg.isSelected())		//open messages
				{
					DisplayMessagesS m = new DisplayMessagesS(con,student);
				}

			}
		});
		btnOk.setBounds(294, 308, 89, 23);
		frame.getContentPane().add(btnOk);
		
	}


	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
