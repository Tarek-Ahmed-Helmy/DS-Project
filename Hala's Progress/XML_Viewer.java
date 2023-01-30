package xmlproj;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class XML_Viewer extends JFrame {
	String xml2string;
	File file;
	Reader fr;
	//BufferedReader br;
	String correctedxml="";
	//BufferedWriter bw;
//	FileWriter fw;
 //   BufferedWriter bw=new BufferedWriter(fw);
	//try {
	//	fr= new FileReader(file);
	//	BufferedReader br = new BufferedReader(fr);
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XML_Viewer frame = new XML_Viewer();
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
	JTextArea textArea = new JTextArea();
	public JTextArea getTextArea() {
		return textArea;
	}
	public XML_Viewer() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		//JTextArea textArea = new JTextArea();
		JButton openFilebtn = new JButton("Open File");
		openFilebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
				//	chooser.setDialogType(chooser.OPEN_DIALOG);
					chooser.showOpenDialog(chooser);
					chooser.setVisible(true);
					
					/*****************************************************/
					file = new File(chooser.getSelectedFile().toString());
			      //  Reader fr;
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
				        textArea.setText(null);
				        textArea.insert(xml2string,0);
				        br.close();
				       // fr.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        
					/*******************************************/
			        
			}
		});
		
		JButton convertJSONbtn = new JButton("Convert to JSON");
		convertJSONbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				Tree xmltree = new Tree();
				xmltree.fillTree(xml2string);
				
				/****************************/
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				PrintStream old = System.out;
				System.setOut(ps);
				XMLtoJSON.printJSON(xmltree.getRoot());
				System.out.flush();
				System.setOut(old);
				
				String json = baos.toString();
				textArea.insert(json,0);
				/*******************************/
				
			}
		});
		
		JButton consistencybtn = new JButton("Check Consistency");
		consistencybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				JFileChooser chooser = new JFileChooser();
				//	chooser.setDialogType(chooser.OPEN_DIALOG);
				chooser.showOpenDialog(chooser);
				chooser.setVisible(true);
					
					/*****************************************************/
				file = new File(chooser.getSelectedFile().toString());
			      //  Reader fr;
				try {
				fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				//BufferedReader br = new BufferedReader(fr);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				PrintStream old = System.out;
				System.setOut(ps);
				
	                if(ErrorHandling.consistency(br)) 
	                	{System.out.println("The XML File Is Consistent");}
	                else {
	                	System.out.println("The XML File Is Not Consistent");}
					System.out.flush();
					System.setOut(old);
					
					String consistency = baos.toString();
					textArea.insert(consistency, 0);
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		JButton showErrorbtn = new JButton("Show Errors");
		showErrorbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				JFileChooser chooser = new JFileChooser();
				//	chooser.setDialogType(chooser.OPEN_DIALOG);
				chooser.showOpenDialog(chooser);
				chooser.setVisible(true);
					
					/*****************************************************/
				file = new File(chooser.getSelectedFile().toString());
			      //  Reader fr;
				try {
				fr = new FileReader(file);
				//BufferedReader br = new BufferedReader(fr);
				
					BufferedReader br = new BufferedReader(fr);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					PrintStream ps = new PrintStream(baos);
					PrintStream old = System.out;
					System.setOut(ps);
					ErrorHandling.showError(br);
					br.close();
					System.out.flush();
					System.setOut(old);
					
					String error = baos.toString();
					textArea.insert(error, 0);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton solveErrorbtn = new JButton("Correct Errors");
		solveErrorbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				//	chooser.setDialogType(chooser.OPEN_DIALOG);
				chooser.showOpenDialog(chooser);
				chooser.setVisible(true);
					
					/*****************************************************/
				file = new File(chooser.getSelectedFile().toString());
			      //  Reader fr;
				try {
					fr = new FileReader(file);
				//BufferedReader br = new BufferedReader(fr);
				
					//JFileChooser chooser = new JFileChooser();
					//	chooser.setDialogType(chooser.OPEN_DIALOG);
				//	chooser.showOpenDialog(chooser);
				//	chooser.setVisible(true);
						
						/*****************************************************/
					//file = new File(chooser.getSelectedFile().toString());
					 FileWriter fw=new FileWriter("..\\new-sample-input.xml");
	                 BufferedWriter bw=new BufferedWriter(fw);
	                 BufferedReader br = new BufferedReader(fr);
	                 ErrorHandling.solveError(br,bw);
	                 br.close();
					 bw.close();
	                 FileReader fr2 = new FileReader("..\\new-sample-input.xml");
				      BufferedReader br2 = new BufferedReader(fr2);
				       StringBuilder sb2 = new StringBuilder();
				        
				        String line2 = br2.readLine();
				   
				        
				        while(line2 !=null){
				            sb2.append(line2).append("\n");
				            line2 = br2.readLine();
				           // correctedxml +=line2;
				        }
	        
				        correctedxml = sb2.toString();
					//	System.out.println(correctedxml);
				        textArea.setText(null);
						textArea.insert(correctedxml,0);
						
						
						br2.close();
				/*	ByteArrayOutputStream baos = new ByteArrayOutputStream();
					PrintStream ps = new PrintStream(baos);
					PrintStream old = System.out;
					System.setOut(ps);
					
					System.out.println("Errors Solved Successfully");
					System.out.flush();
					System.setOut(old);
					String correction = baos.toString();
					*/
			        
					//textArea.insert(correction, 0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
			}
		});
		
		JButton SearchGraphbtn = new JButton("Search Graph");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(SearchGraphbtn, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(solveErrorbtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(showErrorbtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(consistencybtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(convertJSONbtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(openFilebtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
					.addGap(37))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(openFilebtn)
							.addGap(18)
							.addComponent(convertJSONbtn)
							.addGap(18)
							.addComponent(consistencybtn)
							.addGap(18)
							.addComponent(showErrorbtn)
							.addGap(18)
							.addComponent(solveErrorbtn)
							.addGap(18)
							.addComponent(SearchGraphbtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
		
		
		
	}
}
