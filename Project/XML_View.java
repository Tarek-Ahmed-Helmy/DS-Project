import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class XML_View extends JFrame {
    String xml2string;
	File file;
    File file_C;
	Reader fr;
	String correctedxml="";
    public XML_View() {
        setTitle("XML Editor");
        initComponents();
    }
    private void initComponents() {
        openFilebtn = new javax.swing.JButton();
        convertJSONbtn = new javax.swing.JButton();
        consistencybtn = new javax.swing.JButton();
        showErrorbtn = new javax.swing.JButton();
        solveErrorbtn = new javax.swing.JButton();
        searchGraphbtn = new javax.swing.JButton();
        formatbtn = new javax.swing.JButton();
        minifybtn = new javax.swing.JButton();
        compressbtn = new javax.swing.JButton();
        decompressbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        mostInflbtn = new javax.swing.JButton();
        mutualFlwrbtn = new javax.swing.JButton();
        mostActivebtn = new javax.swing.JButton();
        suggestFlwrbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        openFilebtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        openFilebtn.setText("Open File");
        openFilebtn.addActionListener(this::openFilebtnActionPerformed);

        convertJSONbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        convertJSONbtn.setText("Convert to Json");
        convertJSONbtn.addActionListener(this::convertJSONbtnActionPerformed);

        consistencybtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        consistencybtn.setText("Check Consistency");
        consistencybtn.addActionListener(this::consistencybtnActionPerformed);

        showErrorbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        showErrorbtn.setText("Show Errors");
        showErrorbtn.addActionListener(this::showErrorbtnActionPerformed);

        solveErrorbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        solveErrorbtn.setText("Correct Errors");
        solveErrorbtn.addActionListener(this::solveErrorbtnActionPerformed);

        searchGraphbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchGraphbtn.setText("Search Graph");
        searchGraphbtn.addActionListener(this::searchGraphbtnActionPerformed);

        formatbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        formatbtn.setText("Format");
        formatbtn.addActionListener(this::formatbtnActionPerformed);

        minifybtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        minifybtn.setText("Minify");
        minifybtn.addActionListener(this::minifybtnActionPerformed);

        compressbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        compressbtn.setText("Compress");
        compressbtn.addActionListener(this::compressbtnActionPerformed);

        decompressbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        decompressbtn.setText("Decompress");
        decompressbtn.addActionListener(this::decompressbtnActionPerformed);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        mostInflbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mostInflbtn.setText("Most Influencer");
        mostInflbtn.addActionListener(this::mostInflbtnActionPerformed);

        mutualFlwrbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mutualFlwrbtn.setText("Mutual Followers");
        mutualFlwrbtn.addActionListener(this::mutualFlwrbtnActionPerformed);

        mostActivebtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mostActivebtn.setText("Most Active");
        mostActivebtn.addActionListener(this::mostActivebtnActionPerformed);

        suggestFlwrbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        suggestFlwrbtn.setText("Suggestion Followers");
        suggestFlwrbtn.addActionListener(this::suggestFlwrbtnActionPerformed);
        
        JButton graphvisbtn = new JButton();
        graphvisbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Tree xmlTree = new Tree();
                xmlTree.fillTree(xml2string);
        		List<GraphNode> users=GraphConstruction.treeToUsersArray(xmlTree.getRoot());
        		try {
					GraphVisualization.dotGen(users);
					Runtime.getRuntime().exec("dot -Tpng graph.dot -o graph.png");
					GraphImage image = new GraphImage();
	        		image.sendXML(xml2string);
	        		image.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
                
        	}
        });
        graphvisbtn.setText("Visualize Graph");
        graphvisbtn.setFont(new Font("Segoe UI", Font.BOLD, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        					.addComponent(consistencybtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(solveErrorbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(showErrorbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(convertJSONbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(openFilebtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(searchGraphbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(formatbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(minifybtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(compressbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(decompressbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(mostInflbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(suggestFlwrbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(mostActivebtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(mutualFlwrbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addComponent(graphvisbtn, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 612, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(openFilebtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(convertJSONbtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(consistencybtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(showErrorbtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(solveErrorbtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(formatbtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(minifybtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(compressbtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(decompressbtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(searchGraphbtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(mostInflbtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(mutualFlwrbtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(mostActivebtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(suggestFlwrbtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(graphvisbtn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(26, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
        pack();
    }

    private void searchGraphbtnActionPerformed(java.awt.event.ActionEvent evt) {
        SearchWindow window = new SearchWindow();
        window.sendXML(xml2string);
        window.setVisible(true);
    }

    private void minifybtnActionPerformed(java.awt.event.ActionEvent evt) {
        String xml = jTextArea1.getText();
        jTextArea1.setText(Formatting.minify(xml));
        jLabel1.setText("XML Minified");
    }

    private void openFilebtnActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(chooser);
        chooser.setVisible(true);
        file = new File(chooser.getSelectedFile().toString());
	    try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line !=null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            xml2string = sb.toString();
            jTextArea1.setText(xml2string);
            br.close();
            } catch (IOException e1) {
		        e1.printStackTrace();
            }
    }

    private void convertJSONbtnActionPerformed(java.awt.event.ActionEvent evt) {
        jTextArea1.setText(null);
	    Tree xmltree = new Tree();
	    xmltree.fillTree(xml2string);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        JsonConversion.printJSON(xmltree.getRoot());
        System.out.flush();
        System.setOut(old);
        String json = baos.toString();
        jTextArea1.insert(json,0);
        jLabel1.setText("XML converted to JSON");
    }

    private void formatbtnActionPerformed(java.awt.event.ActionEvent evt) {
        String xml = jTextArea1.getText();
        jTextArea1.setText(Formatting.formatXML(xml));
        jLabel1.setText("XML formatted");
    }

    private void consistencybtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);
            if(ErrorHandling.consistency(xml2string)){
                System.out.println("The XML File Is Consistent");
            }else {
                System.out.println("The XML File Is Not Consistent");
            }
            System.out.flush();
            System.setOut(old);
            String consistency = baos.toString();
            jLabel1.setText(consistency);
            br.close();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }

    private void compressbtnActionPerformed(java.awt.event.ActionEvent evt) {
        FileWriter fw3;
        try {
            fw3 = new FileWriter("output\\compress.xml");
            file_C = new File("output\\compress.xml");
            BufferedWriter bw3=new BufferedWriter(fw3);
            jTextArea1.setText(Compression.compress(Formatting.formatXML(xml2string),bw3));
            jLabel1.setText("XML file is compressed");
        } catch (IOException ex) {
            Logger.getLogger(XML_View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void decompressbtnActionPerformed(java.awt.event.ActionEvent evt) {
        Reader fr_c;
        try {
            fr_c = new FileReader(file_C);
            BufferedReader br = new BufferedReader(fr_c);
            jTextArea1.setText(Compression.decompress(br));
            jLabel1.setText("XML file is decompressed");
        } catch (IOException ex) {
            Logger.getLogger(XML_View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showErrorbtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);
            ErrorHandling.showError(xml2string);
            br.close();
            System.out.flush();
            System.setOut(old);
            String error = baos.toString();
            JFrame showError = new JFrame();
            JPanel panel = new JPanel();
            showError.setBounds(200, 150, 300, 300);
            String[] errors_lines = error.split("\n");
            for (String errors_line : errors_lines) {
                JLabel errors_name = new JLabel();
                errors_name.setBounds(30, 30, 180, 160);
                errors_name.setText(errors_line);
                panel.add(errors_name);
            }
            panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
            panel.setLayout(new GridLayout(0,1));
            showError.getContentPane().add(panel, BorderLayout.CENTER);
            showError.setTitle("XML Errors");
            showError.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void solveErrorbtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            fr = new FileReader(file);
            FileWriter fw=new FileWriter("output\\sample-input-error-corrected.xml");
	        BufferedWriter bw=new BufferedWriter(fw);
            BufferedReader br = new BufferedReader(fr);
            ErrorHandling.solveError(xml2string,bw);
            br.close();
            bw.close();
            FileReader fr2 = new FileReader("output\\sample-input-error-corrected.xml");
            BufferedReader br2 = new BufferedReader(fr2);
            StringBuilder sb2 = new StringBuilder();
            String line2 = br2.readLine();
            while(line2 !=null){
                sb2.append(line2).append("\n");
                line2 = br2.readLine();
            }
            correctedxml = sb2.toString();
            xml2string = sb2.toString();
            jTextArea1.setText(Formatting.formatXML(correctedxml));
            jLabel1.setText("The errors are corrected");
            br2.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void mostInflbtnActionPerformed(java.awt.event.ActionEvent evt) {
        Tree xmlTree = new Tree();
        xmlTree.fillTree(xml2string);
        List<GraphNode> users=GraphConstruction.treeToUsersArray(xmlTree.getRoot());
        Graph constructedGraph = GraphConstruction.construct(users);
        GraphNode mostInfluencer = NetworkAnalysis.mostInfluencer(constructedGraph, users);
        jLabel1.setText("Most Influencer is " + mostInfluencer.name + ": " + mostInfluencer.id);
    }

    private void mutualFlwrbtnActionPerformed(java.awt.event.ActionEvent evt) {
        MutualFollowers mf = new MutualFollowers();
        mf.sendXML(xml2string);
        mf.setVisible(true);
    }

    private void mostActivebtnActionPerformed(java.awt.event.ActionEvent evt) {
        Tree xmlTree = new Tree();
        xmlTree.fillTree(xml2string);
        List<GraphNode> users=GraphConstruction.treeToUsersArray(xmlTree.getRoot());
        Graph constructedGraph = GraphConstruction.construct(users);
	    GraphNode mostActive = NetworkAnalysis.mostActive(constructedGraph, users);
        jLabel1.setText("Most Active is " + mostActive.name + ": " + mostActive.id);
    }

    private void suggestFlwrbtnActionPerformed(java.awt.event.ActionEvent evt) {
        SuggestionFollowers sf = new SuggestionFollowers();
        sf.sendXML(xml2string);
        sf.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(XML_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XML_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XML_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XML_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new XML_View().setVisible(true));
    }

    private javax.swing.JButton compressbtn;
    private javax.swing.JButton consistencybtn;
    private javax.swing.JButton convertJSONbtn;
    private javax.swing.JButton decompressbtn;
    private javax.swing.JButton formatbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton minifybtn;
    private javax.swing.JButton mostActivebtn;
    private javax.swing.JButton mostInflbtn;
    private javax.swing.JButton mutualFlwrbtn;
    private javax.swing.JButton openFilebtn;
    private javax.swing.JButton searchGraphbtn;
    private javax.swing.JButton showErrorbtn;
    private javax.swing.JButton solveErrorbtn;
    private javax.swing.JButton suggestFlwrbtn;
}
