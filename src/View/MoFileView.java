package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MoFileView  extends JFrame implements ActionListener{
	private JFileChooser chooser ;
	private JTextArea text_File;
	private FileReader fileReader;
	private BufferedReader bf;
	private ExecutorService executor;
	private MoFileView m;
	public MoFileView() {
		this.setTitle("Đọc File .txt");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		 text_File = new JTextArea();
		 JScrollPane pan =  new JScrollPane(text_File, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(pan, BorderLayout.CENTER);
		JPanel pn_SOuth = new JPanel();
		pn_SOuth.setLayout(new FlowLayout());
		JButton jButton_MoFile = new JButton("Đọc File");
		pn_SOuth.add(jButton_MoFile);
		jButton_MoFile.addActionListener(this);
		JButton jButton_QuayLai = new JButton("Quay lại");
		jButton_QuayLai.addActionListener(this);
		JButton jButton_tiepTuc = new JButton("Tiếp tục");
		jButton_tiepTuc.addActionListener(this);
		pn_SOuth.add(jButton_QuayLai);
		pn_SOuth.add(jButton_tiepTuc);
		this.add(pn_SOuth, BorderLayout.SOUTH);
		executor = Executors.newFixedThreadPool(2);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String a = e.getActionCommand();
		if (a.equals("Đọc File")) {
				executor.execute(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						chooser = new JFileChooser();
						chooser.setCurrentDirectory(new java.io.File("."));
						if(chooser.showOpenDialog(m) == JFileChooser.APPROVE_OPTION) {
							String filename = chooser.getSelectedFile().getAbsolutePath();
							readFile(filename);
						}
						while(true) {
							System.out.println("Mo File " );
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		}
		else if (a.equals("Quay lại")) {
			new TrangChuView();
		} else if (a.equals("Tiếp tục")) {
			new MoFileView();
		}
	}
	// Doc file
	private void readFile(String filename) {
		// TODO Auto-generated method stub	
		text_File.setText("");
		try {
			fileReader = new FileReader(filename);
			bf = new BufferedReader(fileReader);
			String s;
			try {
				while((s = bf.readLine())!= null) {
					text_File.append(s + "\n");
				}
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		new MoFileView();
	}
	
}
