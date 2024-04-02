import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import javax.swing.JScrollPane;
/*Course stream for student*/	
public class StudentStream {

	private JFrame frame;
	private ArrayList<JLabel> post_labels;
	private ArrayList<JLabel> comm_labels;
	private ArrayList<JTextField> texts;
	private ArrayList<Post> post;
	private ArrayList<Comment> comment;
	private Map<String,String> person;
	private int post_index;
	private int comm_index;
	private int text_index;
	private int count=0;
	private String name;
	private JTextField textField;
	
	
	public StudentStream(Course c, Student s) {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(18f));
		lblNewLabel.setBounds(26, 31, 350, 50);
		lblNewLabel.setText(c.getCode()+" "+c.getName());
		lblNewLabel.setForeground(Color.blue);
		frame.getContentPane().add(lblNewLabel);
		


		
		
		post_labels = new ArrayList<JLabel>();
		comm_labels = new ArrayList<JLabel>();
		texts  = new ArrayList<JTextField>();
		person=Main.db.uni.getPersons();
		Set< Map.Entry< String,String> > id_name = person.entrySet();
		post = Main.db.uni.getPost();
		comment= Main.db.uni.getComments();
		post_index=0;
		comm_index=0;
		text_index=0;
		
		for(int i=0;i<post.size();i++)
		{
			if(post.get(i).getCourse_code().equals(c.getCode()))		//finding post belong this course
			{
				for (Entry<String, String> entry:id_name) 				//finding the name of share post.
			    { 
					if(entry.getKey().equals(post.get(i).getTeacher_id()))
					{
						name=entry.getValue();
						break;
					}
			    }
				post_labels.add(new JLabel(post.get(i).getText()));
				post_labels.get(post_index).setHorizontalAlignment(SwingConstants.LEFT);
				post_labels.get(post_index).setBounds(10, 80+(50*count), 600, 50);
				post_labels.get(post_index).setText(name+": "+post.get(i).getText());
				post_labels.get(post_index).setForeground(Color.GREEN);
				post_labels.get(post_index).setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
				frame.getContentPane().add(post_labels.get(post_index));
				frame.getContentPane().add(Box.createRigidArea(new Dimension(0,20)));
				post_index++;
				count++;
				for(int j=0;j<comment.size();j++)						//finding the comments that belong the post
				{
					if(comment.get(j).getPost_id() == post.get(i).getPost_id())
					{
						for (Entry<String, String> entry:id_name) 		//find the name of share comment
					    { 
							if(entry.getKey().equals(comment.get(j).getUser_id()))
							{
								name=entry.getValue();
								break;
							}
					    }
						
						
						comm_labels.add(new JLabel(comment.get(j).getText()));
						comm_labels.get(comm_index).setHorizontalAlignment(SwingConstants.LEFT);
						comm_labels.get(comm_index).setBounds(50, 80+(50*count), 560, 50);
						comm_labels.get(comm_index).setText(name+": "+comment.get(j).getText());
						comm_labels.get(comm_index).setForeground(Color.PINK);
						comm_labels.get(comm_index).setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
						frame.getContentPane().add(comm_labels.get(comm_index));
						frame.getContentPane().add(Box.createRigidArea(new Dimension(0,20)));
						comm_index++;
						count++;
					}
				}
				texts.add(new JTextField());
				texts.get(text_index).setBounds(50, 80+(50*count), 560, 50);
				frame.getContentPane().add(texts.get(text_index));
				frame.getContentPane().add(Box.createRigidArea(new Dimension(0,20)));
				count++;
				text_index++;
				
			}
		}
		
		JButton save = new JButton("SAVE CHANGES");			
		save.setBounds(525, 615, 145, 25);
		frame.getContentPane().add(save);
		save.addActionListener(new ActionListener() {			
			
			@Override
			public void actionPerformed(ActionEvent e) {			//adding comment to the selected comment
				for(int i=0;i<texts.size();i++)
				{
					if(texts.get(i).getText()!=null)
					{
						Main.db.uni.add("comment", new Comment(comment.size()+1,post.get(i).getPost_id(),s.getStudent_number(),texts.get(i).getText()));
					}
				}
				
			}
		});
		
		
		
		
		frame.setVisible(true);
	}
}
