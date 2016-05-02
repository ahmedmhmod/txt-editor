import java.awt.event.ActionEvent;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Editor extends JFrame {
	
	private JTextArea textArea = new JTextArea(20,60);
	private JFileChooser fc =new JFileChooser();
	
	public Editor(){
		JScrollPane scrollPane =new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS	);
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
		fc.setFileFilter(txtFilter);
		
		add(scrollPane);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar); 
		JMenu file =new JMenu("file");
		menuBar.add(file);
		
		file.add(Open);
		file.add(Save);
		file.addSeparator();
		file.add(Exit);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
	Action Open= new AbstractAction("Open file"){	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(fc.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
				openFile(fc.getSelectedFile().getAbsolutePath());
				
			}		
		}		
	};
	Action Save =new AbstractAction("Save file"){
		public void actionPerformed(ActionEvent e){
			saveFile();
			
		}
	};
	Action Exit =new AbstractAction("Exit"){
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	};
	
	public void openFile(String fileName){
		FileReader fr=null;
		try{
		 fr=new FileReader(fileName);
		textArea.read(fr, null);
		fr.close();
		setTitle(fileName);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void saveFile(){
		if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
			FileWriter fw =null;
			try{
				fw=new FileWriter(fc.getSelectedFile().getAbsolutePath()+ ".txt");
				textArea.write(fw);
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	 
	public static void main(String[] args) {
	
		Editor nwtxt= new Editor();
	}
}


