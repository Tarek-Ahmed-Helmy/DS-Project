<<<<<<< HEAD
||||||| c4e7c9b
package xmlproj;

=======

>>>>>>> 6db8bc5bceb09ce664f9e30ad3b2cf7bd5cd8a32
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JScrollPane;

public class GraphImage extends JFrame {
	String xmlstring;
	private final JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GraphImage frame = new GraphImage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	public void sendXML(String str) {
		xmlstring = str;
}
	JLabel label = new JLabel();
	public GraphImage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JButton btnNewButton = new JButton("Show Graph");
		btnNewButton.addActionListener(e -> {
			Tree xmlTree = new Tree();
			xmlTree.fillTree(xmlstring);
			List<GraphNode> users=GraphConstruction.treeToUsersArray(xmlTree.getRoot());
			try {
				GraphVisualization.dotGen(users);
				Runtime.getRuntime().exec("dot -Tpng graph.dot -o graph.png");
				BufferedImage img = ImageIO.read(new File("..\\graph.png"));
				 ImageIcon icon= new ImageIcon(img);
				label.setIcon(icon);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		JButton btnNewButton_1 = new JButton("Done");
		btnNewButton_1.addActionListener(e -> dispose());
		btnNewButton_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 11));
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)))
					.addContainerGap())
		);
		scrollPane.setViewportView(label);
		contentPane.setLayout(gl_contentPane);
	}
}