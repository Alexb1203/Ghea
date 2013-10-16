import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import edu.smu.tspell.wordnet.WordNetDatabase;

//Copyright 2012 Alexander Bedrossian

//VS4E -- DO NOT REMOVE THIS LINE!
public class GUI extends JFrame implements KeyListener {

	String mykey = "jn5yztduqrvuj6uhenyh5m35";
	private static final long serialVersionUID = 1L;
	private JTextField jTextField0;
	private JButton jButton1;
	public JTable jTable0;
	public JScrollPane jScrollPane0;
	public JTable jTable1;
	public JScrollPane jScrollPane1;
	// /my variables
	public static String textbox1="";
	public static int totalWordCount = 0;
	public static int positive_Normal = 0;
	public static int positive_Negated = 0;
	public static int positive_DoubleNegated = 0;
	public static int nuetral_Normal = 0;
	public static int nuetral_Negated = 0;
	public static int nuetral_DoubleNegated = 0;
	public static int negative_Normal = 0;
	public static int negative_Negated = 0;
	public static int negative_DoubleNegated = 0;
	public static float totalPolarity = 0;
	public static float wieghtedPolarity = 0;
	private JButton jButton0;
	DefaultListModel listModel = new DefaultListModel();
	DefaultListModel listModel1 = new DefaultListModel();
	ArrayList<Float> listModel1_polarity = new ArrayList<Float>();

	public GUI() {
		initComponents();
	}
	
	
	//TextTransfer textTransfer;
	private void initComponents() {
		//textTransfer = new TextTransfer();
		setTitle("Sentiment Polarity Calculator");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Leading(12, 186, 10, 10), new Bilateral(12, 12, 0)));
		add(getJPanel0(), new Constraints(new Leading(210, 720, 12, 12), new Leading(12, 611, 12, 12)));
		add(getJPanel2(), new Constraints(new Leading(942, 186, 12, 12), new Leading(12, 611, 12, 12)));
		setSize(1140, 635);
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Rotten Tomatoe: 0.0");
		}
		return jLabel2;
	}


	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setText("Lexicon Editor");
			jButton7.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton7MouseMouseClicked(event);
				}
			});
		}
		return jButton7;
	}


	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton6MouseMouseClicked(event);
				}
			});
		}
		return jButton6;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("100");
			jButton5.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton5MouseMouseClicked(event);
				}
			});
		}
		return jButton5;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("50");
			jButton4.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton4MouseMouseClicked(event);
				}
			});
		}
		return jButton4;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("10");
			jButton3.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton3MouseMouseClicked(event);
				}
			});
		}
		return jButton3;
	}

	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane4.setViewportView(getJList1());
		}
		return jScrollPane4;
	}

	private JList getJList1() {
		if (jList1 == null) {
			jList1 = new JList();
			//DefaultListModel listModel = new DefaultListModel();
			//listModel.addElement("yippie");
			jList1.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent evt) {
			        JList list = (JList)evt.getSource();
			        if (evt.getClickCount() == 2) {
			            try {
							tweetDoubleClicked(list.locationToIndex(evt.getPoint()));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			    }
			});
			jList1.setModel(listModel);
		}
		return jList1;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		}
		return jTextField2;
	}

	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getJList0());
		}
		return jScrollPane3;
	}

	private JList getJList0() {
		if (jList0 == null) {
			jList0 = new JList();
			jList0.setModel(listModel1);
			jList0.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent evt) {
			        JList list = (JList)evt.getSource();
			        if (evt.getClickCount() == 2) {
			            oldtweetDoubleClicked(list.locationToIndex(evt.getPoint()));
			        }
			    }
			});
		}
		return jList0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Average Polarity: 0.0");
		}
		return jLabel1;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Clear List");
			jButton2.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton2MouseMouseClicked(event);
				}
			});
		}
		return jButton2;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(new LineBorder(Color.black, 1, false));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJScrollPane4(), new Constraints(new Leading(6, 172, 10, 10), new Leading(37, 531, 10, 10)));
			jPanel2.add(getJButton3(), new Constraints(new Leading(6, 12, 12), new Trailing(12, 71, 580)));
			jPanel2.add(getJButton4(), new Constraints(new Leading(60, 48, 12, 12), new Trailing(12, 71, 580)));
			jPanel2.add(getJButton5(), new Constraints(new Leading(114, 64, 12, 12), new Trailing(12, 71, 580)));
			jPanel2.add(getJTextField2(), new Constraints(new Leading(6, 146, 10, 10), new Leading(12, 12, 12)));
			jPanel2.add(getJButton6(), new Constraints(new Leading(158, 20, 12, 12), new Leading(12, 19, 50, 50)));
		}
		return jPanel2;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(new LineBorder(Color.black, 1, false));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJButton2(), new Constraints(new Bilateral(12, 12, 88), new Leading(12, 12, 12)));
			jPanel1.add(getJLabel1(), new Constraints(new Bilateral(4, 4, 127), new Trailing(6, 78, 540)));
			jPanel1.add(getJLabel2(), new Constraints(new Bilateral(4, 4, 126), new Trailing(27, 78, 540)));
			jPanel1.add(getJScrollPane3(), new Constraints(new Bilateral(4, 4, 22), new Bilateral(44, 48, 22)));
		}
		return jPanel1;
	}


	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJButton1(), new Constraints(new Leading(12, 12, 12), new Trailing(12, 301, 301)));
			jPanel0.add(getJButton0(), new Constraints(new Trailing(12, 176, 176), new Trailing(12, 407, 461)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(12, 12, 12), new Leading(38, 50, 50)));
			jPanel0.add(getJTextField1(), new Constraints(new Leading(70, 116, 12, 12), new Leading(36, 50, 50)));
			jPanel0.add(getJTextField0(), new Constraints(new Bilateral(12, 12, 4), new Leading(12, 50, 50)));
			jPanel0.add(getJScrollPane0(), new Constraints(new Bilateral(12, 12, 7), new Leading(60, 65, 72, 69)));
			jPanel0.add(getJScrollPane1(), new Constraints(new Bilateral(12, 12, 7), new Leading(137, 65, 72, 69)));
			jPanel0.add(getJScrollPane2(), new Constraints(new Bilateral(13, 12, 22), new Bilateral(208, 44, 22)));
			jPanel0.add(getJButton7(), new Constraints(new Bilateral(292, 291, 122), new Trailing(12, 160, 214)));
		}
		return jPanel0;
	}


	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTextArea0());
		}
		return jScrollPane2;
	}

	private JTextArea getJTextArea0() {
		if (jTextArea0 == null) {
			jTextArea0 = new JTextArea();
			jTextArea0.setText("PP: Prepositional Phrase\tIP: Irrealis Phrase\n\nA prepositional phrase is a phrase that begins with a preposition and ends with a noun.\n\nAn Irrealis phrase is a phrase that poses a question. It starts with \"if, is, are, can, would... etc.\" and \nends with a comma. Or it begins with a comma and ends with the words.");
		}
		return jTextArea0;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setEnabled(false);
		}
		return jTextField1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Subject:");
		}
		return jLabel0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Load Text File");
			jButton0.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return jButton0;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Analyze Sample Text");
			jButton1.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton1MouseMouseClicked(event);
				}
			});
		}
		return jButton1;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			jScrollPane1.setVerifyInputWhenFocusTarget(false);
			jScrollPane1.setEnabled(false);
			jScrollPane1.setFocusable(false);
			jScrollPane1.setRequestFocusEnabled(false);
			jScrollPane1.setWheelScrollingEnabled(false);
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	private JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
			jTable1.setModel(new DefaultTableModel(new Object[][] { { "Total Polarity", 0.0f, }, { "Wieghted Polarity", 0.0 + "%", }, { "Total word Count", 0, }, },
					new String[] { "", "Value", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, String.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
			jTable1.setAutoscrolls(false);
			jTable1.setFocusable(false);
			jTable1.setEnabled(false);
			jTable1.setRequestFocusEnabled(false);
		}
		return jTable1;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBorder(null);
			jScrollPane0.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane0.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			jScrollPane0.setVerifyInputWhenFocusTarget(false);
			jScrollPane0.setEnabled(false);
			jScrollPane0.setFocusable(false);
			jScrollPane0.setRequestFocusEnabled(false);
			jScrollPane0.setWheelScrollingEnabled(false);
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			jTable0.setModel(new DefaultTableModel(new Object[][] { { "Positive", 0, 0, 0, }, { "Nuetral", 0, 0, 0, }, { "Negative", 0, 0, 0, }, }, new String[] {
					"", "Normal", "Double Negations", "Negation", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, String.class, String.class, String.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
			jTable0.setAutoscrolls(false);
			jTable0.setFocusable(false);
			jTable0.setEnabled(false);
			jTable0.setRequestFocusEnabled(false);
		}
		return jTable0;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			//jTextField0.setText("The big, hungry, green Martian grabbed a student from the back row.");
			jTextField0.addKeyListener(new KeyAdapter() {
	
				public void keyPressed(KeyEvent event) {
					jTextField0KeyKeyPressed(event);
				}
			});
		}
		return jTextField0;
	}


	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			//if (lnfClassname == null)
			//	lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class. Note: This class is only created so that you can
	 * easily preview the result at runtime. It is not expected to be managed by
	 * the designer. You can modify it as you like.
	 */
	public static void main(String[] args) {
		ClassLoader classLoader = GUI.class.getClassLoader();
	    File classpathRoot = new File(classLoader.getResource("").getPath());
		 try {
			System.setProperty("wordnet.database.dir", classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "dict" + File.separator);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 //WordNetDatabase database = WordNetDatabase.getFileInstance(); 
		 
		 
		 
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI frame = new GUI();
				frame.setDefaultCloseOperation(GUI.EXIT_ON_CLOSE);
				frame.setTitle("Sentiment Polarity Calculator © Alexander Bedrossian");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frameEmotion = new EmotionEditor();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		try {
			
		    //JOptionPane.showMessageDialog(null, classpathRoot.getCanonicalPath().replaceAll("%20", " "), "File Path", JOptionPane.INFORMATION_MESSAGE);
		    
			Driver.posFile = LoadFiles
					.LoadFile(classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "dataFiles" + File.separator + "Positive.eto");
					//.LoadFile("." + File.separator + "src" + File.separator + "dataFiles" + File.separator + "Positive.eto");
			Driver.nutFile = LoadFiles
					.LoadFile(classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "dataFiles" + File.separator + "Nuetral.eto");
					//.LoadFile("." + File.separator + "src" + File.separator + "dataFiles" + File.separator + "Nuetral.eto");
			Driver.negFile = LoadFiles
					.LoadFile(classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "dataFiles" + File.separator + "Negative.eto");
					//.LoadFile("." + File.separator + "src" + File.separator + "dataFiles" + File.separator + "Negative.eto");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(), "File Path", JOptionPane.INFORMATION_MESSAGE);
		    e.printStackTrace();
		}
	}

	private void jButton1MouseMouseClicked(MouseEvent event) {
		if (!jTextField0.getText().equals(""))
			Driver.maindisplay(Driver.StringtoStringArray(jTextField0.getText()));
		update();
	}
	
	
	
	public void tempUpdate(){
		jTextArea0.setText(textbox1);
		jTable1.setModel(new DefaultTableModel(new Object[][] {
				{ "Total Polarity", totalPolarity, },
				{ "Wieghted Polarity", wieghtedPolarity + "%", },
				{ "Total word Count", totalWordCount, }, }, new String[] { "",
				"Value", }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Object.class, String.class, };
			
			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		jTable0.setModel(new DefaultTableModel(new Object[][] {
				{ "Positive", positive_Normal, positive_DoubleNegated,
						positive_Negated, },
				{ "Nuetral", nuetral_Normal, nuetral_DoubleNegated,
						nuetral_Negated, },
				{ "Negative", negative_Normal, negative_DoubleNegated,
						negative_Negated, }, }, new String[] { "", "Normal",
				"Double Negations", "Negation", }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Object.class, String.class,
					String.class, String.class, };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
	}
	
	public void update() {
		jTextArea0.setText(textbox1);
		//df.format(((GUI.totalPolarity * 100) / totalWordCount)) + "%";
		listModel1_polarity.add(wieghtedPolarity/*totalPolarity*/);
		float average = 0;
		for(float x : listModel1_polarity){
			average+=x;
		}
		
		jLabel1.setText("Average Polarity: " + average/listModel1_polarity.size());
		jTable1.setModel(new DefaultTableModel(new Object[][] {
				{ "Total Polarity", totalPolarity, },
				{ "Wieghted Polarity", wieghtedPolarity + "%", },
				{ "Total word Count", totalWordCount, }, }, new String[] { "",
				"Value", }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Object.class, String.class, };
			
			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		jTable0.setModel(new DefaultTableModel(new Object[][] {
				{ "Positive", positive_Normal, positive_DoubleNegated,
						positive_Negated, },
				{ "Nuetral", nuetral_Normal, nuetral_DoubleNegated,
						nuetral_Negated, },
				{ "Negative", negative_Normal, negative_DoubleNegated,
						negative_Negated, }, }, new String[] { "", "Normal",
				"Double Negations", "Negation", }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class<?>[] { Object.class, String.class,
					String.class, String.class, };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
	}

	final JFileChooser fc = new JFileChooser();
	private JLabel jLabel0;
	private JTextField jTextField1;
	private JTextArea jTextArea0;
	private JScrollPane jScrollPane2;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JButton jButton2;
	private JLabel jLabel1;
	private JList jList0;
	private JScrollPane jScrollPane3;
	private JTextField jTextField2;
	private JList jList1;
	private JScrollPane jScrollPane4;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JButton jButton7;
	private void jButton0MouseMouseClicked(MouseEvent event) {
		try {
			JFileChooser chooser = new JFileChooser();
			// Note: source for ExampleFileFilter can be found in
			// FileChooserDemo,
			// under the demo/jfc directory in the Java 2 SDK, Standard Edition.
			int returnVal = chooser.showOpenDialog(getParent());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				Driver.maindisplay(LoadFiles.LoadFile(chooser.getSelectedFile()
						.getPath()));
				update();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void getFc() {
	}

	private void jTextField0KeyKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			if (!jTextField0.getText().equals("")){
				Driver.maindisplay(Driver.StringtoStringArray(jTextField0.getText()));
				listModel1.addElement(jTextField0.getText());
				update();
			}
		}
	}
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	  JSONArray jarry;
	  public void TomatoeTest(String search) throws IOException, JSONException {
	    //JSONObject json = readJsonFromUrl("http://api.rottentomatoes.com/api/public/v1.0.json?apikey=jn5yztduqrvuj6uhenyh5m35");
	    //System.out.println(json.toString());
	    //JSONObject ay = (JSONObject) json.get("links");
	    //System.out.println(ay.toString());
	    //System.out.println(ay.get("lists"));//http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=[your_api_key]
		
		//String Search = "770677122"; //770672122 (Toy Story)
		String theURL = ("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + mykey + "&q=" + search.replaceAll(" ", "%20"));
		JSONObject json = readJsonFromUrl(theURL);
		listModel.clear();
		
		jarry = json.getJSONArray("movies");
		System.out.println(json);
		
		JSONObject temp;
	    for(int i = 0;i<jarry.length();i++){
	    	temp = jarry.getJSONObject(i);
	    	listModel.addElement(temp.get("title"));
	    }
	    
	    
	    
	    
	    //JSONObject ay = (JSONObject) json2.get("links");
	    //System.out.println(ay.toString());
	    //String ay2 = (String) ay.get("movies");
	    //System.out.println(ay2.toString());
	    //JSONObject json3 = readJsonFromUrl(ay2.toString());
	    //System.out.println(json3.toString());
	    //System.out.println(ay.get("lists"));
	  }
	  
	  double parse(String ratio) {
		  
		    if (ratio.contains("/")) {
		        String[] rat = ratio.split("/");
		        return Double.parseDouble(rat[0]) / Double.parseDouble(rat[1]);
		    } else {
		    	switch(ratio.charAt(0))
	            {
	            	case 'a': case 'A':
	            		return 5/5;
	            	case 'b': case 'B':
	            		return 4/5;
	            	case 'c': case 'C':
	            		return 3/5;
	            	case 'd': case 'D':
	            		return 2/5;
	            	case 'f': case 'F':
	            		return 1/5;
	            	default:
	            		return Double.parseDouble(ratio);
	            }
		    }
		}
	
	
	private void jButton6MouseMouseClicked(MouseEvent event) {
		try {
			TomatoeTest(jTextField2.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*listModel.clear();
		Twitter twitter = new TwitterFactory().getInstance();
		Query query = new Query(jTextField2.getText());
		query.setRpp(100);
		QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Tweet tweet : result.getTweets()) {
			listModel.addElement(tweet.getText());
			//System.out.println(tweet.getFromUser() + ":" + tweet.getText());
		}*/
	}
	private void tweetDoubleClicked(int index) throws JSONException, IOException{
		/*
		jList1.setSelectedIndex(index);
		String sentence = (String)jList1.getSelectedValue();
		jTextField0.setText(sentence);
	    listModel.removeElementAt(index);
		Driver.maindisplay(Driver.StringtoStringArray(jTextField0.getText()));
		listModel1.addElement(jTextField0.getText());
		update();
		*/
		jTextField0.setText("");
		
		listModel1.clear();
		jLabel1.setText("Average Polarity: 0.0");
		listModel1_polarity.clear();
		
		//listModel1.addElement(jTextField0.getText());
		
		JSONObject movie = jarry.getJSONObject(index);
		listModel.removeElementAt(index);
		jarry.remove(index);
		
		String theURL2 = "http://api.rottentomatoes.com/api/public/v1.0/movies/" + movie.getInt("id") +"/reviews.json?apikey=" + mykey;
		
	    JSONObject json2 = readJsonFromUrl(theURL2);
	    //System.out.println("Total Reviews: " +json2.get("total").toString());
	    JSONArray JA = json2.getJSONArray("reviews");
	    JSONObject review;
	    int totalReviews = 0;
	    float rottenTotalPolaritty = 0;
	    for(int i=0;i<JA.length();i++){
	    	review=JA.getJSONObject(i);
	    	//System.out.println(review);
	    	if(review.has("quote")&&review.has("original_score")){
	    		//if (!jTextField0.getText().equals("")){
	    			//System.out.println("found one");
	    			Driver.maindisplay(Driver.StringtoStringArray(JA.getJSONObject(i).get("quote").toString()));
	    			update();
	    			totalReviews++;
	    			rottenTotalPolaritty += parse(JA.getJSONObject(i).get("original_score").toString());
	    			System.out.println(JA.getJSONObject(i).get("original_score"));
	    			System.out.println(totalPolarity);
	    			System.out.println((totalPolarity/parse(JA.getJSONObject(i).get("original_score").toString()))*100);
	    			System.out.println();
	    			listModel1.addElement(JA.getJSONObject(i).get("quote").toString());
	    			
	    		//}
	    		//System.out.println(JA.getJSONObject(i).get("original_score"));
	    		//System.out.println(JA.getJSONObject(i).get("quote"));
	    		//System.out.println();
	    	}
	    }
	    jLabel2.setText("Rotten Tomatoe: " + rottenTotalPolaritty/totalReviews);
	    //System.out.println("RottenTotalScore: " + rottenTotalPolaritty/totalReviews);
		update();
	}
	private void oldtweetDoubleClicked(int index){
		jList0.setSelectedIndex(index);
		String sentence = (String)jList0.getSelectedValue();
		jTextField0.setText(sentence);
		Driver.maindisplay(Driver.StringtoStringArray(jTextField0.getText()));
		tempUpdate();
	}

	private void jButton3MouseMouseClicked(MouseEvent event) {
		for(int i = 0;i<10;i++){
			if(listModel.size()>0)
				try {
					tweetDoubleClicked(0);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private void jButton2MouseMouseClicked(MouseEvent event) {
		listModel1.clear();
		jLabel1.setText("Average Polarity: 0.0");
		listModel1_polarity.clear();
	}

	private void jButton5MouseMouseClicked(MouseEvent event) {
		for(int i = 0;i<100;i++){
			if(listModel.size()>0)
				try {
					tweetDoubleClicked(0);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private void jButton4MouseMouseClicked(MouseEvent event) {
		for(int i = 0;i<50;i++){
			if(listModel.size()>0)
				try {
					tweetDoubleClicked(0);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}



	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("key hit1");
	}



	@Override
	public void keyReleased(KeyEvent e) { 
		// TODO Auto-generated method stub
		System.out.println("key hit2");
	    //if(e.getKeyCode() == KeyEvent.VK_PASTE)
	        //jTextField0.setText(jTextField0.getText().substring(jTextField0.getCaretPosition())+ textTransfer.getClipboardContents() + jTextField0.getText().substring(jTextField0.getCaretPosition(),jTextField0.getText().length()));
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("key hit3");
	}

	//final JFrame frame;
	static EmotionEditor frameEmotion;
	private JLabel jLabel2;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private void jButton7MouseMouseClicked(MouseEvent event) {
		//1. Create the frame.
		//EmotionEditor frame = new EmotionEditor();
		frameEmotion.toFront();
		//2. Optional: What happens when the frame closes?
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		//...create emptyLabel...
		//frame.getContentPane().add("emptyLabel, BorderLayout.CENTER);

		//4. Size the frame.
		//frame.pack();

		//5. Show it.
		frameEmotion.setLocationRelativeTo(jTable0);
		frameEmotion.setSize(322, 104);
		frameEmotion.setVisible(true);
	}
}
