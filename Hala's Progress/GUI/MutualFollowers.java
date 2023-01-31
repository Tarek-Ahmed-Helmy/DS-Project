package xmlproj;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MutualFollowers extends JFrame {
	String xmlstring;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	//JFrame mainf;

	/**
	 * Launch the application.
	 */
	public void sendXML(String str) {
			xmlstring = str;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MutualFollowers frame = new MutualFollowers();
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
	
	public MutualFollowers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JTextArea textArea = new JTextArea();
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User ID 1");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("User ID 2");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnNewButton = new JButton("Find");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id1 = Integer.parseInt(textField.getText());
				int id2 = Integer.parseInt(textField_1.getText());
			//	textArea.append(xmlstring);
				//XML_Viewer mainf = new XML_Viewer();
				Tree xmlTree = new Tree();
                xmlTree.fillTree(xmlstring);
                List<GraphNode> users=GraphConstruction.treeToUsersArray(xmlTree.getRoot());
                Graph constructedGraph = GraphConstruction.construct(users);
				
                
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				PrintStream old = System.out;
				System.setOut(ps);

				if((id1>users.size()||id1<1)&&(id2<=users.size()&&id2>0)) {
		            System.out.println("The first user does not exist!");
		           
		        }
		        else if((id2>users.size()||id2<1)&&(id1<=users.size()&&id1>0)) {
		            System.out.println("The second user does not exist!");
		          
		        }
		        else if((id2>users.size()||id2<1)&&(id1>users.size()||id1<1))
		        {
		            System.out.println("Those users do not exist!");
		          
		        }
		        else {
		        	
		        	if(users.isEmpty()) {
		        		System.out.println("File has no users !");
		        	}
		        	List<GraphNode> mutualFollowers = NetworkAnalysis.mutualFollowers(constructedGraph, users, id1, id2);
		        	if(mutualFollowers.isEmpty()) {

		        		System.out.println("Users have no mutual followers");
		        	}
		        	for (GraphNode user:mutualFollowers){

		        		System.out.println(user.name + ": " + user.id);
		        	}
		        }
				System.out.flush();
				System.setOut(old);
				String mutual = baos.toString();
				//textArea.setText(null);
				textArea.setText(mutual);
               // mainf.setVisible(true);
              //  dispose();

				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		
		JButton btnNewButton_1 = new JButton("Done");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(70))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
							.addGap(25))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(186)
					.addComponent(btnNewButton_1)
					.addContainerGap(235, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(30))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
