import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
/*Menu page for teacher*/
public class MenuPage {

	private JFrame frame;
	private JRadioButton rbtnDisplayAllC;
	private JRadioButton rbtnDisplayAllAs;
	private JRadioButton rbtnDisplaySubW;
    private JRadioButton rbtnDisplayAllAn;
    private JRadioButton rbtnMsg;
    private DisplayCourses dc;
    private DisplayAssignments da;
    private DisplaySubmittedWorks dsw;
    private DisplayAnnouncements dan;
    private DisplayMessagesT m;
    
	public MenuPage(Connection con, Teacher teacher)
	{

		frame = new JFrame();
		frame.setBounds(100, 100, 453, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMenu = new JLabel("TEACHER MENU");
		lblMenu.setBounds(123, 30, 112, 14);
		frame.getContentPane().add(lblMenu);
		

		rbtnDisplayAllC = new JRadioButton("Display all courses");
		rbtnDisplayAllC.setBounds(60, 77, 175, 23);
		frame.getContentPane().add(rbtnDisplayAllC);
		
		rbtnDisplayAllAs = new JRadioButton("Display all assignments");
		rbtnDisplayAllAs.setBounds(60, 103, 175, 23);
		frame.getContentPane().add(rbtnDisplayAllAs);
		
		rbtnDisplaySubW = new JRadioButton("Display submitted works");
		rbtnDisplaySubW.setBounds(60, 131, 175, 23);
		frame.getContentPane().add(rbtnDisplaySubW);
		
		rbtnDisplayAllAn = new JRadioButton("Display all announcements");
		rbtnDisplayAllAn.setBounds(60, 159, 186, 23);
		frame.getContentPane().add(rbtnDisplayAllAn);

		rbtnMsg = new JRadioButton("Messages");
		rbtnMsg.setBounds(60, 187, 109, 23);
		frame.getContentPane().add(rbtnMsg);

		
		ButtonGroup btnG = new ButtonGroup();
		btnG.add(rbtnDisplayAllC);
		btnG.add(rbtnDisplayAllAs);
		btnG.add(rbtnDisplaySubW);
		btnG.add(rbtnDisplayAllAn);
		btnG.add(rbtnMsg);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(rbtnDisplayAllC.isSelected())	//Open course
				{					
					dc = new DisplayCourses( teacher);		
				}
				else if(rbtnDisplayAllAs.isSelected())	//Open assignments
				{
					da = new DisplayAssignments(teacher);
				}
				else if(rbtnDisplaySubW.isSelected())	//Open Submitted works
				{
					dsw = new DisplaySubmittedWorks(con, teacher) ;
				}
				else if(rbtnDisplayAllAn.isSelected())	//Open announcements
				{
					dan = new DisplayAnnouncements(teacher);
				}
				else if(rbtnMsg.isSelected())	//Open messages
				{
					m = new DisplayMessagesT(con, teacher);
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
