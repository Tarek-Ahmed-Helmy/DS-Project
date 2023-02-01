import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class SearchWindow extends JFrame {
	String xmlstring;
	private final JPanel contentPane;
	private final JTextField textField;
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				SearchWindow frame = new SearchWindow();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	public void sendXML(String str) {
		xmlstring = str;
	}
	JTextArea textArea = new JTextArea();
	public SearchWindow() {
		setTitle("Search Window");
		setBounds(100, 100, 557, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		textField = new JTextField();
		textField.setColumns(10);
		JLabel lblNewLabel = new JLabel("Enter word to be searched:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JScrollPane scrollPane = new JScrollPane();
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(e -> {
			String word = textField.getText();
			Tree xmlTree = new Tree();
			xmlTree.fillTree(xmlstring);
			List<GraphNode> users=GraphConstruction.treeToUsersArray(xmlTree.getRoot());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			PrintStream old = System.out;
			System.setOut(ps);
			GraphConstruction.search(users,word);
			System.out.flush();
			System.setOut(old);
			String search = baos.toString();
			textArea.setText(search);
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JButton btnNewButton_1 = new JButton("Done");
		btnNewButton_1.addActionListener(e -> dispose());
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(64)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(217)
							.addComponent(btnNewButton)))
					.addContainerGap(98, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(36))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
	}
}