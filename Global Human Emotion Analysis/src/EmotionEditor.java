import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class EmotionEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField0;
	private JButton jButton0;
	private JRadioButton jRadioButton0;
	private JRadioButton jRadioButton2;
	private JRadioButton jRadioButton1;
	private JPanel jPanel0;
	public EmotionEditor() {
		initComponents();
	}
	ButtonGroup bg1;
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(215, 12, 95), new Leading(0, 74, 10, 10)));
		add(getJTextField0(), new Constraints(new Leading(12, 197, 113, 113), new Leading(12, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(12, 196, 12, 12), new Bilateral(37, 12, 25)));
		initBg1();
		setSize(322, 84);
	}

	private void initBg1() {
		bg1 = new ButtonGroup();
		bg1.add(getJRadioButton2());
		bg1.add(getJRadioButton1());
		bg1.add(getJRadioButton0());
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJRadioButton0(), new Constraints(new Leading(8, 8, 8), new Leading(8, 19, 8, 8)));
			jPanel0.add(getJRadioButton1(), new Constraints(new Leading(8, 8, 8), new Leading(31, 35, 35)));
			jPanel0.add(getJRadioButton2(), new Constraints(new Leading(8, 8, 8), new Leading(54, 8, 8)));
		}
		return jPanel0;
	}
	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setSelected(true);
			jRadioButton1.setText("Neutral");
		}
		return jRadioButton1;
	}

	private JRadioButton getJRadioButton2() {
		if (jRadioButton2 == null) {
			jRadioButton2 = new JRadioButton();
			jRadioButton2.setText("Negative");
		}
		return jRadioButton2;
	}

	private JRadioButton getJRadioButton0() {
		if (jRadioButton0 == null) {
			jRadioButton0 = new JRadioButton();
			jRadioButton0.setText("Positive");
		}
		return jRadioButton0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Add");
			jButton0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return jButton0;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				EmotionEditor frame = new EmotionEditor();
				frame.setDefaultCloseOperation(EmotionEditor.EXIT_ON_CLOSE);
				frame.setTitle("EmotionEditor");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		String word = jTextField0.getText();
		ClassLoader classLoader = GUI.class.getClassLoader();
	    File classpathRoot = new File(classLoader.getResource("").getPath());
		
		if(jRadioButton0.isSelected()){
			//Positive
			try {
				WriteFile(classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "dataFiles" + File.separator + "Positive.eto", word);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (jRadioButton1.isSelected()){
			//Neutral
			try {
				WriteFile(classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "dataFiles" + File.separator + "Nuetral.eto", word);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (jRadioButton2.isSelected()){
			//Negative
			try {
				WriteFile(classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "dataFiles" + File.separator + "Negative.eto", word);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	//char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public void WriteFile(String FileLocation, String word) throws IOException{
	    RandomAccessFile fs = new RandomAccessFile(FileLocation,"rw");//'r'=read   -    'rw'=read/write
	    //char letter=fs.readLine().charAt(0);
	    //while(indexOfAlphabet(letter)<indexOfAlphabet(word.charAt(0))){
	    //	try{
	    //		letter=fs.readLine().charAt(0);
	    //	}catch(Exception e){
	    //		System.out.println("Error Writing '" + word + "' to " + FileLocation);
	    //		break;
	    //	}
	    //}
	    fs.seek(fs.length());
	    fs.writeBytes(word+"\n");
	    fs.close();
	    JOptionPane.showMessageDialog(this, "Added Word");
	}
	
	/*public int indexOfAlphabet(char letter){
		for(int i=0;i<alphabet.length;i++){
			if(Character.toLowerCase(alphabet[i])==Character.toLowerCase(letter))
				return i;
		}
		return 0;
	}*/

}
