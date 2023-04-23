import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JLocaleChooser;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

/*YeeShin Development project
 * 1 variable section
 * 2 Main method
 * 3 Connect method (Connecting the database)
 * 4 loadTable method (Loading the table)
 * 5 allProduct method (Loading all product table)
 * 6 autoUpdateAllProductTable method (For updating the total amount and number of a product category)
 * 7 Construct the Main frame 
 */
public class MainFrame {
	//Variable for connecting SQL
	private Connection con; 
	private Statement st;
	private PreparedStatement pst;
	
	//Variable for table
	private String currentTable = ""; //Table name for loading SQL table
	
	//Variable for total number and total amount of product
	int totalCalculationCycle = 0;
	int totalNumberCycle = 0;
	int totalCalculationKitchen = 0;
	int totalNumberKitchen = 0;
	int totalCalculationBed = 0;
	int totalNumberBed = 0;
	int totalCalculationSafe = 0;
	int totalNumberSafe = 0;
	int totalCalculationWardrobe = 0;
	int totalNumberWardrobe = 0;
	int totalCalculationCCTV = 0;
	int totalNumberCCTV = 0;
	int totalCalculationLaptop = 0;
	int totalNumberLaptop = 0;
	int totalCalculationWatch = 0;
	int totalNumberWatch = 0;
	int totalCalculationTV = 0;
	int totalNumberTV = 0;
	int totalCalculationWM = 0;
	int totalNumberWM = 0;
	int totalCalculationFridge = 0;
	int totalNumberFridge = 0;
	int totalCalculationFreezer = 0;
	int totalNumberFreezer = 0;
	int totalCalculationAC = 0;
	int totalNumberAC = 0;
	int totalCalculationPhone = 0;
	int totalNumberPhone = 0;
	int totalCalculationWC = 0;
	int totalNumberWC = 0;
	int totalCalculationSB = 0;
	int totalNumberSB = 0;
	int totalCalculationG = 0;
	int totalNumberG = 0;
	int totalCalculationTVP = 0;
	int totalNumberTVP = 0;
	int totalCalculationProjector = 0;
	int totalNumberProjector = 0;
	int totalCalculationBattery = 0;
	int totalNumberBattery = 0;
	int totalCalculationBath = 0;
	int totalNumberBath = 0;
	int menuCalculation = 0;
	int menuNumber = 0;
	
	
	//Variable for building project MainFrame 
	public JFrame frmYeeShinProduct;
	public JLabel AlarmLabel;
	private JTable mainTable;
	private JTextField addID;
	private JTextField addName;
	private JTextField addModel;
	private JTextField addQuantity;
	private JTextField addDate;
	private JTextField addPrice;
	private JTextField TotalNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmYeeShinProduct.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//Connecting database
	private void connect() {
		//Driver 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Connecting Database with JDBC
		try {
			con = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12592806", "sql12592806", "YeeShin527845");
			st = con.createStatement();
			
			//Success in connecting database 
			try {
				successClip();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Connecting Database");
		} catch (SQLException e) {
			
		//Fain in connecting database
		try {
			alertClip();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Connection fail");
		e.printStackTrace();
		}
	}
	
	// Loading Table
	public void loadTable(String tableName, int totalCalculationProduct, int totalNumberProduct) {
		try {
			mainTable.setModel(new DefaultTableModel());
			String query = "select * from " + tableName; //" ORDER BY Product_Name ASC;"
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) mainTable.getModel();
			
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i = 0; i < cols; i++) {
				colName[i] = rsmd.getColumnName(i + 1);
				model.setColumnIdentifiers(colName);
				String id, name, pmodel, quantity, date, price, totalAmount;
				while (rs.next()) {
					id = rs.getString(1);
					name = rs.getString(2);
					pmodel = rs.getString(3);
					quantity = rs.getString(4);
					date = rs.getString(5);
					price = rs.getString(6);
					totalAmount = rs.getString(7);

					// Setting text on total value and total number
					totalCalculationProduct += Integer.valueOf(totalAmount);
					totalNumberProduct += Integer.valueOf(quantity);
					TotalNumber.setText(Integer.toString(totalNumberProduct));
					TotalAmount.setText(String.valueOf(totalCalculationProduct));

					// Adding rows in table
					String[] row = { id, name, pmodel, quantity, date, price, totalAmount };
					model.addRow(row);
				}
			}

			pst = con.prepareStatement("insert into All_Products (Product_type, Quantity_Stock, Total_amount) value (?,?,?)");
			pst.setString(1, tableName);
			pst.setString(2, Integer.toString(totalNumberProduct));
			pst.setString(3, Integer.toString(totalCalculationProduct));
			pst.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	// All products table
	public void allProduct() {
		int totalCalculation = 0;
		int totalNumber = 0;
		mainTable.setModel(new DefaultTableModel());
		try {
			String query = "select * from All_Products";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) mainTable.getModel();

			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i = 0; i < cols; i++) {
				colName[i] = rsmd.getColumnName(i + 1);
				model.setColumnIdentifiers(colName);
				String productType, stock, totalAmount;
				while (rs.next()) {
					productType = rs.getString(1);
					stock = rs.getString(2);
					totalAmount = rs.getString(3);

					// Setting text on total value and total number
					totalCalculation += Integer.valueOf(totalAmount);
					totalNumber += Integer.valueOf(stock);
					TotalNumber.setText(Integer.toString(totalNumber));
					TotalAmount.setText(Integer.toString(totalCalculation));
					
					String[] row = { productType, stock, totalAmount};
					model.addRow(row);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	//for menu select with Name
	public void loadMenuTable(String tableName, String menu, int totalCalculationProduct, int totalNumberProduct) {
		try {
			mainTable.setModel(new DefaultTableModel());
			String query = "select * from " + tableName + " WHERE Product_Name LIKE '"+ menu + "%';";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) mainTable.getModel();
			
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i = 0; i < cols; i++) {
				colName[i] = rsmd.getColumnName(i + 1);
				model.setColumnIdentifiers(colName);
				String id, name, pmodel, quantity, date, price, totalAmount;
				while (rs.next()) {
					id = rs.getString(1);
					name = rs.getString(2);
					pmodel = rs.getString(3);
					quantity = rs.getString(4);
					date = rs.getString(5);
					price = rs.getString(6);
					totalAmount = rs.getString(7);
					
					// Setting text on total value and total number
					totalCalculationProduct += Integer.valueOf(totalAmount);
					totalNumberProduct += Integer.valueOf(quantity);
					TotalNumber.setText(Integer.toString(totalNumberProduct));
					TotalAmount.setText(String.valueOf(totalCalculationProduct));
					
					// Adding rows in table
					String[] row = { id, name, pmodel, quantity, date, price, totalAmount };
					model.addRow(row);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	//For menu select with Model (ONE WHERE statement)
	public void loadMenuTableModel(String tableName, String menu, int totalCalculationProduct, int totalNumberProduct) {
		try {
			mainTable.setModel(new DefaultTableModel());
			String query = "select * from " + tableName + " WHERE Product_Model LIKE '"+ menu + "%';";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) mainTable.getModel();
			
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i = 0; i < cols; i++) {
				colName[i] = rsmd.getColumnName(i + 1);
				model.setColumnIdentifiers(colName);
				String id, name, pmodel, quantity, date, price, totalAmount;
				while (rs.next()) {
					id = rs.getString(1);
					name = rs.getString(2);
					pmodel = rs.getString(3);
					quantity = rs.getString(4);
					date = rs.getString(5);
					price = rs.getString(6);
					totalAmount = rs.getString(7);
					
					// Setting text on total value and total number
					totalCalculationProduct += Integer.valueOf(totalAmount);
					totalNumberProduct += Integer.valueOf(quantity);
					TotalNumber.setText(Integer.toString(totalNumberProduct));
					TotalAmount.setText(String.valueOf(totalCalculationProduct));
					
					// Adding rows in table
					String[] row = { id, name, pmodel, quantity, date, price, totalAmount };
					model.addRow(row);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	//For menu select with Model (TWO WHERE statement)
		public void loadMenuTableTwoWhere(String tableName, String productName, String productModel, int totalCalculationProduct, int totalNumberProduct) {
			try {
				mainTable.setModel(new DefaultTableModel());
				String query = "select * from " + tableName + " WHERE (Product_Name LIKE '"+ productName + "%' AND Product_Model LIKE '" + productModel + "%');";
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				DefaultTableModel model = (DefaultTableModel) mainTable.getModel();
				
				int cols = rsmd.getColumnCount();
				String[] colName = new String[cols];
				for (int i = 0; i < cols; i++) {
					colName[i] = rsmd.getColumnName(i + 1);
					model.setColumnIdentifiers(colName);
					String id, name, pmodel, quantity, date, price, totalAmount;
					while (rs.next()) {
						id = rs.getString(1);
						name = rs.getString(2);
						pmodel = rs.getString(3);
						quantity = rs.getString(4);
						date = rs.getString(5);
						price = rs.getString(6);
						totalAmount = rs.getString(7);
						
						// Setting text on total value and total number
						totalCalculationProduct += Integer.valueOf(totalAmount);
						totalNumberProduct += Integer.valueOf(quantity);
						TotalNumber.setText(Integer.toString(totalNumberProduct));
						TotalAmount.setText(String.valueOf(totalCalculationProduct));
						
						// Adding rows in table
						String[] row = { id, name, pmodel, quantity, date, price, totalAmount };
						model.addRow(row);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		
	//Alert loading (0 quantity items are described)
	public void alertLoading(String tableName) {
		try {
			quantityZeroTable.setModel(new DefaultTableModel());
			String query = "select id, Product_Name, Product_Model from " + tableName + " WHERE Quantity_Stock = 0";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) quantityZeroTable.getModel();
			
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i = 0; i < cols; i++) {
				colName[i] = rsmd.getColumnName(i + 1);
				model.setColumnIdentifiers(colName);
				String id, name, pmodel;
				while (rs.next()) {
					id = rs.getString(1);
					name = rs.getString(2);
					pmodel = rs.getString(3);
					
					// Adding rows in table
					String[] row = { id, name, pmodel};
					model.addRow(row);
					
					JOptionPane.showMessageDialog(null,
							 "စာရင်းထဲတွင်လက်ကျန်မရှိသောပစ္စည်းရှိပါသည်။\n\n"
							+ "ညာဘက်ခြမ်းရှိဇယားတွင်ပစ္စည်းအရေးတွက် 0 ဖြစ်သောစာရင်းကြည့်ပါ။");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	//Update for all products total amount and total number
	public void autoUpdateAllProductTable(String tableName) {
		try {
			pst = con.prepareStatement("update All_Products set Product_type = ?, Quantity_Stock = ?, Total_amount = ?  where Product_type = ?");
			pst.setString(1, tableName);
			pst.setString(2, TotalNumber.getText());
			pst.setString(3, TotalAmount.getText());
			pst.setString(4, tableName);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Setting URL for sound file
	URL clickSoundURL, alertSoundURL, successSoundURL, noStockURL;
	private JTextField TotalAmount;
	private JMenuItem thomeFridge;
	private JTable quantityZeroTable;
	private JTextField searchID;
	public void setURL(URL fileName) {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(fileName);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	// Button Click Sound effect
	public void clickClip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		clickSoundURL = getClass().getResource("mouse.wav");
		setURL(clickSoundURL);
	}

	// Alert Sound effect
	public void alertClip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		alertSoundURL = getClass().getResource("alert.wav");
		setURL(alertSoundURL);
	}

	// Success Sound effect
	public void successClip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		successSoundURL = getClass().getResource("win.wav");
		setURL(successSoundURL);
	}
	
	// No quantity alarm Sound effect
	public void noStockAlarm() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		noStockURL = getClass().getResource("NostockAlarm.wav");
		setURL(noStockURL);
	}
	
	
	//Clean text for clear and refresh button
	public void cleanText() {
		TotalNumber.setText("");
		TotalAmount.setText("");
		addID.setText("");
		addName.setText("");
		addModel.setText("");
		addDate.setText("");
		addQuantity.setText("");
		addPrice.setText("");
		searchID.setText("");
	}


	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		connect();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmYeeShinProduct = new JFrame();
		frmYeeShinProduct.setTitle("Yee Shin Product Management System (version 1.7)");
		frmYeeShinProduct.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frmYeeShinProduct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYeeShinProduct.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Header Section
		JPanel header = new JPanel();
		header.setBackground(new Color(255, 255, 255));
		frmYeeShinProduct.getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JMenuBar firstMenuBar = new JMenuBar();
		header.add(firstMenuBar, BorderLayout.NORTH);
		
		JMenu allProductMenu = new JMenu("All Product");
		firstMenuBar.add(allProductMenu);
		
		JMenuItem allProduct = new JMenuItem("All Product");
		allProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//Loading all product
				allProduct();
			}
		});
		allProductMenu.add(allProduct);
		
		JMenu airConMenu = new JMenu("Air Con & Cooler");
		firstMenuBar.add(airConMenu);
		
		JMenuItem allProductACACR = new JMenuItem("All Products");
		allProductACACR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "AirCon_AirCooler";
				loadTable(currentTable, totalCalculationAC, totalNumberAC);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		airConMenu.add(allProductACACR);
		
		JMenu airconSubMenu = new JMenu("Air Con");
		airConMenu.add(airconSubMenu);
		
		JMenuItem allProductAC = new JMenuItem("All Products");
		allProductAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "AirCon_AirCooler";
				loadMenuTableModel(currentTable, "Aircon" ,totalCalculationAC, totalCalculationAC);
				autoUpdateAllProductTable(currentTable);
			}
		});
		airconSubMenu.add(allProductAC);
		
		JMenuItem panasonicAC = new JMenuItem("Panasonic");
		panasonicAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "Panasonic", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(panasonicAC);
		
		JMenuItem misubishiAC = new JMenuItem("Misubishi");
		misubishiAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "Misubishi", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(misubishiAC);
		
		JMenuItem samsungAC = new JMenuItem("Samsung");
		samsungAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "Samsung", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(samsungAC);
		
		JMenuItem tclAC = new JMenuItem("TCL");
		tclAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "TCL", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(tclAC);
		
		JMenuItem skyworthAC = new JMenuItem("Skyworth");
		skyworthAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "Skyworth", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(skyworthAC);
		
		JMenuItem chigoAC = new JMenuItem("Chigo");
		chigoAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "Chigo", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(chigoAC);
		
		JMenuItem mideaAC = new JMenuItem("Midea");
		mideaAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "Midea", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(mideaAC);
		
		JMenuItem aufitAC = new JMenuItem("AUFIT");
		aufitAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "AUFIT", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(aufitAC);
		
		JMenuItem greeAC = new JMenuItem("GREE");
		greeAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "GREE", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(greeAC);
		
		JMenuItem thomeAC = new JMenuItem("T-Home");
		thomeAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "T-Home", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(thomeAC);
		
		JMenuItem sharpAC = new JMenuItem("Sharp");
		sharpAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "Sharp", "Aircon", menuCalculation, menuNumber);
			}
		});
		airconSubMenu.add(sharpAC);
		
		JMenu airCoolerMenu = new JMenu("Air Cooler ");
		airConMenu.add(airCoolerMenu);
		
		JMenuItem allProductACR = new JMenuItem("All Products");
		allProductACR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "AirCon_AirCooler";
				loadMenuTableModel(currentTable, "AirCooler" ,totalCalculationAC, totalCalculationAC);
				autoUpdateAllProductTable(currentTable);
			}
		});
		airCoolerMenu.add(allProductACR);
		
		JMenuItem glacierACR = new JMenuItem("Glacier");
		glacierACR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "Glacier", "AirCooler", menuCalculation, menuNumber);
			}
		});
		airCoolerMenu.add(glacierACR);
		
		JMenuItem thomeACR = new JMenuItem("T-Home");
		thomeACR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "T-Home", "AirCooler", menuCalculation, menuNumber);
			}
		});
		airCoolerMenu.add(thomeACR);
		
		JMenuItem mcgACR = new JMenuItem("MCG");
		mcgACR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "MCG", "AirCooler", menuCalculation, menuNumber);
			}
		});
		airCoolerMenu.add(mcgACR);
		
		JMenuItem fujiACR = new JMenuItem("Fuji");
		fujiACR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "Fuji", "AirCooler", menuCalculation, menuNumber);
			}
		});
		airCoolerMenu.add(fujiACR);
		
		JMenuItem tr1ACR = new JMenuItem("TRI");
		tr1ACR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "AirCon_AirCooler";
				loadMenuTableTwoWhere(currentTable, "TRI", "AirCooler", menuCalculation, menuNumber);
			}
		});
		airCoolerMenu.add(tr1ACR);
		
		JMenu cctvmenu = new JMenu("CCTV");
		firstMenuBar.add(cctvmenu);
		
		JMenuItem allCCTV = new JMenuItem("All Products");
		allCCTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "CCTV";
				loadTable(currentTable, totalCalculationCCTV, totalNumberCCTV);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		cctvmenu.add(allCCTV);
		
		JMenuItem imouCCTV = new JMenuItem("Imou");
		cctvmenu.add(imouCCTV);
		
		JMenu laptopMenu = new JMenu("Computer");
		firstMenuBar.add(laptopMenu);
		
		JMenuItem allLaptops = new JMenuItem("All products");
		allLaptops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Laptop";
				loadTable(currentTable, totalCalculationLaptop, totalNumberLaptop);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		laptopMenu.add(allLaptops);
		
		
		JMenu laptopSubMenu = new JMenu("Laptop");
		laptopMenu.add(laptopSubMenu);
		
		JMenuItem allLaptop = new JMenuItem("All Products");
		allLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Laptop";
				loadMenuTableModel(currentTable, "Laptop" ,totalCalculationAC, totalCalculationAC);
				autoUpdateAllProductTable(currentTable);
			}
		});
		laptopSubMenu.add(allLaptop);
		
		JMenuItem acerLaptop = new JMenuItem("Acer");
		acerLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Laptop";
				loadMenuTable(currentTable, "Acer", menuCalculation, menuNumber);
			}
		});
		laptopSubMenu.add(acerLaptop);
		
		JMenuItem dellLaptop = new JMenuItem("Dell");
		dellLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Laptop";
				loadMenuTable(currentTable, "Dell", menuCalculation, menuNumber);
			}
		});
		laptopSubMenu.add(dellLaptop);
		
		JMenuItem hpLaptop = new JMenuItem("hp");
		hpLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Laptop";
				loadMenuTable(currentTable, "hp", menuCalculation, menuNumber);
			}
		});
		laptopSubMenu.add(hpLaptop);
		
		JMenuItem itelLaptop = new JMenuItem("itel");
		itelLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Laptop";
				loadMenuTable(currentTable, "itel", menuCalculation, menuNumber);
			}
		});
		laptopSubMenu.add(itelLaptop);
		
		JMenu computerAccessories = new JMenu("Accessories");
		laptopMenu.add(computerAccessories);
		
		JMenuItem allCA = new JMenuItem("All Products");
		computerAccessories.add(allCA);
		
		JMenuItem mouse = new JMenuItem("Mouse");
		computerAccessories.add(mouse);
		
		JMenuItem mousePad = new JMenuItem("Mouse Pad");
		computerAccessories.add(mousePad);
		
		JMenuItem keyBoard = new JMenuItem("Keyboard");
		computerAccessories.add(keyBoard);
		
		JMenu fridgeMenu = new JMenu("Refrigerator");
		firstMenuBar.add(fridgeMenu);
		
		JMenuItem allFridge = new JMenuItem("All products");
		allFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Refrigerator";
				loadTable(currentTable, totalCalculationFridge, totalNumberFridge);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		fridgeMenu.add(allFridge);
		
		
		
		JMenu fridgeSubMenu = new JMenu("ရေခဲသေတ္တာ");
		fridgeMenu.add(fridgeSubMenu);
		
		JMenuItem allproductFridge = new JMenuItem("All Products");
		allproductFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Refrigerator";
				loadMenuTableModel(currentTable, "Fridge" ,totalCalculationFreezer, totalNumberFreezer);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		fridgeSubMenu.add(allproductFridge);
		
		JMenuItem hitachiFridge = new JMenuItem("Hitachi");
		hitachiFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Hitachi", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(hitachiFridge);
		
		JMenuItem LGfridge = new JMenuItem("LG");
		LGfridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "LG", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(LGfridge);
		
		JMenuItem panasonicFridge = new JMenuItem("Panasonic");
		panasonicFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Panasonic", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(panasonicFridge);
		
		JMenuItem fujiFridge = new JMenuItem("Fuji");
		fujiFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Fuji", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(fujiFridge);
		
		JMenuItem acmaFridge = new JMenuItem("ACMA");
		acmaFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "ACMA", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(acmaFridge);
		
		JMenuItem nibbanFridge = new JMenuItem("Nibban");
		nibbanFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Nibban", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(nibbanFridge);
		
		JMenuItem nikokiFridge = new JMenuItem("Nikoki");
		nikokiFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Nikoki", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(nikokiFridge);
		
		JMenuItem haiexFridge = new JMenuItem("Haiex");
		haiexFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Haiex", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(haiexFridge);
		
		JMenuItem mideaFridge = new JMenuItem("Midea");
		mideaFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Midea", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(mideaFridge);
		
		JMenuItem glacierFridge = new JMenuItem("Glacier");
		glacierFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Glacier", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(glacierFridge);
		
		thomeFridge = new JMenuItem("T-Home");
		thomeFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "T-Home", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(thomeFridge);
		
		JMenuItem samsungFridge = new JMenuItem("Samsung");
		samsungFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Samsung", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(samsungFridge);
		
		JMenuItem toshibaFridge = new JMenuItem("Toshiba");
		toshibaFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Toshiba", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(toshibaFridge);
		
		JMenuItem sharpFridge = new JMenuItem("Sharp");
		sharpFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Sharp", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(sharpFridge);
		
		JMenuItem yukikoFridge = new JMenuItem("Yukiko");
		yukikoFridge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Yukiko", "Fridge", menuCalculation, menuNumber);
			}
		});
		fridgeSubMenu.add(yukikoFridge);
		
		JMenu freezerMenu = new JMenu("Freezer");
		fridgeMenu.add(freezerMenu);
		
		JMenuItem allFreezer = new JMenuItem("All Freezers");
		allFreezer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Refrigerator";
				loadMenuTableModel(currentTable, "Freezer" ,totalCalculationFreezer, totalNumberFreezer);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		freezerMenu.add(allFreezer);
		
		JMenuItem glacierFreezer = new JMenuItem("Glacier");
		glacierFreezer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Glacier", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(glacierFreezer);
		
		JMenuItem MEILLINGF = new JMenuItem("MEILLING");
		MEILLINGF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "MEILLING", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(MEILLINGF);
		
		JMenuItem acmaF = new JMenuItem("ACMA");
		acmaF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "ACMA", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(acmaF);
		
		JMenuItem nibbanF = new JMenuItem("Nibban");
		nibbanF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Nibban", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(nibbanF);
		
		JMenuItem mideaF = new JMenuItem("Midea");
		mideaF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Midea", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(mideaF);
		
		JMenuItem snowF = new JMenuItem("Snow");
		snowF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Snow", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(snowF);
		
		JMenuItem toshibaF = new JMenuItem("Toshiba");
		toshibaF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Toshiba", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(toshibaF);
		
		JMenuItem haierF = new JMenuItem("Haier");
		haierF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Haier", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(haierF);
		
		JMenuItem fujiF = new JMenuItem("Fuji");
		fujiF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Fuji", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(fujiF);
		
		JMenuItem nikokiF = new JMenuItem("Nikoki");
		nikokiF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Nikoki", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(nikokiF);
		
		JMenuItem thomeF = new JMenuItem("T-Home");
		thomeF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "T-Home", "Freezer", menuCalculation, menuNumber);
			}
		});
		freezerMenu.add(thomeF);
		
		JMenu showCaseMenu = new JMenu("Show Case");
		fridgeMenu.add(showCaseMenu);
		
		JMenuItem allShowCase = new JMenuItem("All Show Cases");
		allShowCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableModel(currentTable, "Showcase", menuCalculation, menuNumber);
			}
		});
		showCaseMenu.add(allShowCase);
		
		JMenuItem glacierS = new JMenuItem("Glacier");
		glacierS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Glacier", "Showcase", menuCalculation, menuNumber);
			}
		});
		showCaseMenu.add(glacierS);
		
		JMenuItem acmaS = new JMenuItem("ACMA");
		acmaS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "ACMA", "Showcase", menuCalculation, menuNumber);
			}
		});
		showCaseMenu.add(acmaS);
		
		JMenuItem mideaS = new JMenuItem("Midea");
		mideaS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Midea", "Showcase", menuCalculation, menuNumber);
			}
		});
		showCaseMenu.add(mideaS);
		
		JMenuItem snowS = new JMenuItem("Snow");
		snowS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Snow", "Showcase", menuCalculation, menuNumber);
			}
		});
		showCaseMenu.add(snowS);
		
		JMenuItem nikokiS = new JMenuItem("Nikoki");
		nikokiS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Nikoki", "Showcase", menuCalculation, menuNumber);
			}
		});
		showCaseMenu.add(nikokiS);
		
		JMenuItem nibbanS = new JMenuItem("Nibban");
		nibbanS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Refrigerator";
				loadMenuTableTwoWhere(currentTable, "Nibban", "Showcase", menuCalculation, menuNumber);
			}
		});
		showCaseMenu.add(nibbanS);
		
		JMenu TVMenu = new JMenu("TV & Projector");
		firstMenuBar.add(TVMenu);
		
		JMenuItem allTVProjector = new JMenuItem("All Products");
		allTVProjector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "TV";
				loadTable(currentTable, totalCalculationTV, totalNumberTV);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		TVMenu.add(allTVProjector);
		
		
		
		JMenu tvSubMenu = new JMenu("TV");
		TVMenu.add(tvSubMenu);
		
		JMenuItem allProductTV = new JMenuItem("All Products");
		allProductTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "TV";
				loadMenuTableModel(currentTable, "TV" ,totalCalculationTV, totalNumberTV);
				autoUpdateAllProductTable(currentTable);
			}
		});
		tvSubMenu.add(allProductTV);
		
		JMenuItem skyworthTV = new JMenuItem("Skyworth");
		skyworthTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "TV";
				loadMenuTable(currentTable, "Skyworth", menuCalculation, menuNumber);
			}
		});
		tvSubMenu.add(skyworthTV);
		
		JMenuItem samsungTV = new JMenuItem("Samsung");
		samsungTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "TV";
				loadMenuTable(currentTable, "Samsung", menuCalculation, menuNumber);
			}
		});
		tvSubMenu.add(samsungTV);
		
		JMenuItem ThomeTV = new JMenuItem("T-Home");
		ThomeTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "TV";
				loadMenuTable(currentTable, "T-Home", menuCalculation, menuNumber);
			}
		});
		tvSubMenu.add(ThomeTV);
		
		JMenuItem TCLTV = new JMenuItem("TCL");
		TCLTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "TV";
				loadMenuTable(currentTable, "TCL", menuCalculation, menuNumber);
			}
		});
		tvSubMenu.add(TCLTV);
		
		JMenuItem MITV = new JMenuItem("Mi ");
		MITV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "TV";
				loadMenuTable(currentTable, "Mi", menuCalculation, menuNumber);
			}
		});
		tvSubMenu.add(MITV);
		
		JMenuItem LGTV = new JMenuItem("LG ");
		LGTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "TV";
				loadMenuTable(currentTable, "LG", menuCalculation, menuNumber);
			}
		});
		tvSubMenu.add(LGTV);
		
		JMenuItem FujiTV = new JMenuItem("Fuji ");
		FujiTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "TV";
				loadMenuTable(currentTable, "Fuji", menuCalculation, menuNumber);
			}
		});
		tvSubMenu.add(FujiTV);
		
		JMenuItem NikokiTV = new JMenuItem("Nikoki");
		NikokiTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "TV";
				loadMenuTable(currentTable, "Nikoki", menuCalculation, menuNumber);
			}
		});
		tvSubMenu.add(NikokiTV);
		
		JMenuItem CoecoTV = new JMenuItem("Coeco");
		CoecoTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "TV";
				loadMenuTable(currentTable, "Coeco", menuCalculation, menuNumber);
			}
		});
		tvSubMenu.add(CoecoTV);
		
		JMenuItem itelTV = new JMenuItem("itel");
		itelTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "TV";
				loadMenuTable(currentTable, "itel", menuCalculation, menuNumber);
			}
		});
		tvSubMenu.add(itelTV);
		
		JMenu projectMenu = new JMenu("Projector");
		TVMenu.add(projectMenu);
		
		JMenuItem allproductProjector = new JMenuItem("All Products");
		allproductProjector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "TV";
				loadMenuTableModel(currentTable, "Projector" ,totalCalculationTV, totalNumberTV);
				autoUpdateAllProductTable(currentTable);
			}
		});
		projectMenu.add(allproductProjector);
		
		JMenu waterHeaterMenu = new JMenu("Water Heater & Cooler");
		firstMenuBar.add(waterHeaterMenu);
		
		JMenuItem allProductWHWC = new JMenuItem("All Products");
		allProductWHWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Water_Cooler_Heater";
				loadTable(currentTable, totalCalculationWC, totalNumberWC);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		waterHeaterMenu.add(allProductWHWC);
		
		JMenu waterHeaterSubMenu = new JMenu("Water Heater");
		waterHeaterMenu.add(waterHeaterSubMenu);
		
		JMenuItem allproductWH = new JMenuItem("All Products");
		allproductWH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Water_Cooler_Heater";
				loadMenuTableModel(currentTable, "Water Heater" ,totalCalculationAC, totalCalculationAC);
				autoUpdateAllProductTable(currentTable);
			}
		});
		waterHeaterSubMenu.add(allproductWH);
		
		JMenuItem aristonWH = new JMenuItem("ARISTON");
		aristonWH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "ARISTON", "Water Heater", menuCalculation, menuNumber);
			}
		});
		waterHeaterSubMenu.add(aristonWH);
		
		JMenuItem mideaWH = new JMenuItem("Midea");
		mideaWH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Midea", "Water Heater", menuCalculation, menuNumber);
			}
		});
		waterHeaterSubMenu.add(mideaWH);
		
		JMenuItem triWH = new JMenuItem("TRI");
		triWH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "TRI", "Water Heater", menuCalculation, menuNumber);
			}
		});
		waterHeaterSubMenu.add(triWH);
		
		JMenuItem toshibaWH = new JMenuItem("Toshiba");
		toshibaWH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Toshiba", "Water Heater", menuCalculation, menuNumber);
			}
		});
		waterHeaterSubMenu.add(toshibaWH);
		
		JMenuItem glacierWH = new JMenuItem("Glacier");
		glacierWH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Glacier", "Water Heater", menuCalculation, menuNumber);
			}
		});
		waterHeaterSubMenu.add(glacierWH);
		
		JMenuItem kangaroo = new JMenuItem("Kangaroo");
		kangaroo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Kangaroo", "Water Heater", menuCalculation, menuNumber);
			}
		});
		waterHeaterSubMenu.add(kangaroo);
		
		JMenuItem tekaWH = new JMenuItem("Teka");
		tekaWH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Teka", "Water Heater", menuCalculation, menuNumber);
			}
		});
		waterHeaterSubMenu.add(tekaWH);
		
		JMenuItem socialHeater = new JMenuItem("Solar Heater");
		socialHeater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "AYM", "Water Heater", menuCalculation, menuNumber);
			}
		});
		waterHeaterSubMenu.add(socialHeater);
		
		JMenu watercoolerSubMenu = new JMenu("Water Cooler");
		waterHeaterMenu.add(watercoolerSubMenu);
		
		JMenuItem allproductWC = new JMenuItem("All Products");
		allproductWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Water_Cooler_Heater";
				loadMenuTableModel(currentTable, "Water Cooler" ,totalCalculationAC, totalCalculationAC);
				autoUpdateAllProductTable(currentTable);
			}
		});
		watercoolerSubMenu.add(allproductWC);
		
		JMenuItem mideaWC = new JMenuItem("Midea");
		mideaWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Midea", "Water Cooler", menuCalculation, menuNumber);
			}
		});
		watercoolerSubMenu.add(mideaWC);
		
		JMenuItem glacierWC = new JMenuItem("Glacier");
		glacierWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Glacier", "Water Cooler", menuCalculation, menuNumber);
			}
		});
		watercoolerSubMenu.add(glacierWC);
		
		JMenuItem toshibaWC = new JMenuItem("Toshiba");
		toshibaWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Toshiba", "Water Cooler", menuCalculation, menuNumber);
			}
		});
		watercoolerSubMenu.add(toshibaWC);
		
		JMenuItem triWC = new JMenuItem("TRI");
		triWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "TRI", "Water Cooler", menuCalculation, menuNumber);
			}
		});
		watercoolerSubMenu.add(triWC);
		
		JMenuItem standardWC = new JMenuItem("Standard");
		standardWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Standard", "Water Cooler", menuCalculation, menuNumber);
			}
		});
		watercoolerSubMenu.add(standardWC);
		
		JMenuItem thomeWC = new JMenuItem("T-Home");
		thomeWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "T-Home", "Water Cooler", menuCalculation, menuNumber);
			}
		});
		watercoolerSubMenu.add(thomeWC);
		
		JMenuItem nibbanWC = new JMenuItem("Nibban");
		nibbanWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Nibban", "Water Cooler", menuCalculation, menuNumber);
			}
		});
		watercoolerSubMenu.add(nibbanWC);
		
		JMenuItem nikokiWC = new JMenuItem("Nikoki");
		nikokiWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Nikoki", "Water Cooler", menuCalculation, menuNumber);
			}
		});
		watercoolerSubMenu.add(nikokiWC);
		
		JMenuItem kangraooWC = new JMenuItem("Kangraoo");
		kangraooWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "Kangraoo", "Water Cooler", menuCalculation, menuNumber);
			}
		});
		watercoolerSubMenu.add(kangraooWC);
		
		JMenuItem chinaWC = new JMenuItem("China");
		chinaWC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Cooler_Heater";
				loadMenuTableTwoWhere(currentTable, "China", "Water Cooler", menuCalculation, menuNumber);
			}
		});
		watercoolerSubMenu.add(chinaWC);
		
		JMenu watchMenu = new JMenu("Watch");
		firstMenuBar.add(watchMenu);
		
		JMenuItem allWatch = new JMenuItem("All Products");
		allWatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Watch";
				loadTable(currentTable, totalCalculationWatch, totalNumberWatch);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		watchMenu.add(allWatch);
		
		JMenuItem topHillWatch = new JMenuItem("TopHill");
		watchMenu.add(topHillWatch);
		
		
		JMenu furnitureMenu = new JMenu("ခန်းဝင်ပစ္စည်း");
		firstMenuBar.add(furnitureMenu);
		
		JMenuItem allBed = new JMenuItem("All products");
		allBed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				currentTable = "Bed";
				loadTable(currentTable, totalCalculationBed, totalNumberBed);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		furnitureMenu.add(allBed);
		
		JMenu bedMenu = new JMenu("မွေ့ယာ");
		furnitureMenu.add(bedMenu);
		
		JMenuItem allProductsBed = new JMenuItem("All Products");
		allProductsBed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Bed";
				loadMenuTableModel(currentTable, "Mattress" ,totalCalculationBed, totalNumberBed);
				autoUpdateAllProductTable(currentTable);
			}
		});
		bedMenu.add(allProductsBed);
		
		JMenuItem sweetyBed = new JMenuItem("Sweety Home");
		sweetyBed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Bed";
				loadMenuTable(currentTable, "Sweety Home", menuCalculation, menuNumber);
			}
		});
		bedMenu.add(sweetyBed);
		
		JMenuItem dream = new JMenuItem("Dream");
		dream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Bed";
				loadMenuTable(currentTable, "Dream", menuCalculation, menuNumber);
			}
		});
		bedMenu.add(dream);
		
		JMenuItem thebest = new JMenuItem("The Best");
		thebest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Bed";
				loadMenuTable(currentTable, "The Best", menuCalculation, menuNumber);
			}
		});
		bedMenu.add(thebest);
		
		JMenuItem bedBed = new JMenuItem("ကုတင်");
		bedBed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Bed";
				loadMenuTable(currentTable, "bed", menuCalculation, menuNumber);
			}
		});
		
		JMenu bedAccessories = new JMenu("ခေါင်းအုံး၊ဖက်လုံး၊အိပ်ယာခင်း");
		furnitureMenu.add(bedAccessories);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("All Products");
		bedAccessories.add(mntmNewMenuItem_2);
		
		JMenu pillowSubMenu = new JMenu("ခေါင်းအုံး");
		bedAccessories.add(pillowSubMenu);
		
		JMenuItem allproductsPillow = new JMenuItem("All Products");
		pillowSubMenu.add(allproductsPillow);
		
		JMenu longPillowSubMenu = new JMenu("ဖက်လုံး");
		bedAccessories.add(longPillowSubMenu);
		
		JMenuItem allproductsLongPillow = new JMenuItem("All Products");
		longPillowSubMenu.add(allproductsLongPillow);
		
		JMenu shelterSubMenu = new JMenu("စောင်");
		bedAccessories.add(shelterSubMenu);
		
		JMenuItem allProductShelter = new JMenuItem("All Products");
		shelterSubMenu.add(allProductShelter);
		
		JMenu bedSheetSubMenu = new JMenu("အိပ်ယာခင်း");
		bedAccessories.add(bedSheetSubMenu);
		
		JMenuItem allProductBedSheet = new JMenuItem("All Products");
		bedSheetSubMenu.add(allProductBedSheet);
		furnitureMenu.add(bedBed);
		
		JMenuItem godTable = new JMenuItem("ဘုရားစင်");
		furnitureMenu.add(godTable);
		
		JMenuItem mirronSeat = new JMenuItem("မှန်တင်ခုံ");
		furnitureMenu.add(mirronSeat);
		
		JMenuItem sofa = new JMenuItem("ဆိုဖာ");
		furnitureMenu.add(sofa);
		
		JMenuItem counterTable = new JMenuItem("ကောင်တာ");
		furnitureMenu.add(counterTable);
		
		JMenuItem table = new JMenuItem("စားပွဲ");
		furnitureMenu.add(table);
		
		JMenuItem clotheStand = new JMenuItem("အဝတ်လှန်းစင်");
		furnitureMenu.add(clotheStand);
		
		JMenu cycleMenu = new JMenu("ဆိုင်ကယ်၊စက်ဘီး");
		firstMenuBar.add(cycleMenu);
		
		JMenuItem allCycle = new JMenuItem("All Cycles & Bicycles");
		allCycle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Cycle";
				loadTable(currentTable, totalCalculationCycle, totalNumberCycle);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		cycleMenu.add(allCycle);
		
		JMenuItem adixinCycle = new JMenuItem("Adxin");
		adixinCycle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Cycle";
				loadMenuTable(currentTable, "Adxin", menuCalculation, menuNumber);
			}
		});
		cycleMenu.add(adixinCycle);
		
		JMenuItem candaCycle = new JMenuItem("Canda");
		candaCycle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Cycle";
				loadMenuTable(currentTable, "Canda", menuCalculation, menuNumber);
			}
		});
		
		cycleMenu.add(candaCycle);
		
		JMenuItem kenboCycle = new JMenuItem("Kenbo");
		kenboCycle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Cycle";
				loadMenuTable(currentTable, "Kenbo", menuCalculation, menuNumber);
			}
		});
		cycleMenu.add(kenboCycle);
		
		JMenuItem zenziCycle = new JMenuItem("Zenzi");
		zenziCycle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Cycle";
				loadMenuTable(currentTable, "Zenzi", menuCalculation, menuNumber);
			}
		});
		cycleMenu.add(zenziCycle);
		
		JMenuItem batteryCycle = new JMenuItem("Battery Cycle");
		batteryCycle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Cycle";
				loadMenuTable(currentTable, "Battery", menuCalculation, menuNumber);
			}
		});
		cycleMenu.add(batteryCycle);
		
		JMenu safeMenu = new JMenu("မီးခံသေတ္တာ ");
		firstMenuBar.add(safeMenu);
		
		JMenuItem allSafe = new JMenuItem("All products");
		allSafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				currentTable = "Safe";
				loadTable(currentTable, totalCalculationSafe, totalNumberSafe);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		safeMenu.add(allSafe);
		
		JMenu washingMachineMenu = new JMenu("အဝတ်လျှော်စက် ");
		firstMenuBar.add(washingMachineMenu);
		
		JMenuItem allproductWM = new JMenuItem("All proudcts");
		allproductWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Washing_Machine";
				loadTable(currentTable, totalCalculationWM, totalNumberWM);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		washingMachineMenu.add(allproductWM);
		
		JMenuItem glacierWM = new JMenuItem("Glacier");
		glacierWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Glacier", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(glacierWM);
		
		JMenuItem mideaWM = new JMenuItem("Midea");
		mideaWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Midea", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(mideaWM);
		
		JMenuItem samsungWM = new JMenuItem("Samsung");
		samsungWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Samsung", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(samsungWM);
		
		JMenuItem LGWM = new JMenuItem("LG");
		LGWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "LG", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(LGWM);
		
		JMenuItem thomeWM = new JMenuItem("T-Home");
		thomeWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "T-Home", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(thomeWM);
		
		JMenuItem panasonicWM = new JMenuItem("Panasonic");
		panasonicWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Panasonic", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(panasonicWM);
		
		JMenuItem fujiWM = new JMenuItem("Fuji");
		fujiWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Fuji", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(fujiWM);
		
		JMenuItem hitachiWM = new JMenuItem("Hitachi");
		hitachiWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Hitachi", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(hitachiWM);
		
		JMenuItem toshibaWM = new JMenuItem("Toshiba");
		toshibaWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Toshiba", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(toshibaWM);
		
		JMenuItem skyworthWM = new JMenuItem("Skyworth");
		skyworthWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Skyworth", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(skyworthWM);
		
		JMenuItem chanhongWM = new JMenuItem("ChanHong");
		chanhongWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "ChanHong", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(chanhongWM);
		
		JMenuItem tclWM = new JMenuItem("TCL");
		tclWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "TCL", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(tclWM);
		
		JMenuItem nibbanWM = new JMenuItem("Nibban");
		nibbanWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Nibban", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(nibbanWM);
		
		JMenuItem sharpWM = new JMenuItem("Sharp");
		sharpWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Sharp", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(sharpWM);
		
		JMenuItem skyinixWM = new JMenuItem("Skyinix");
		skyinixWM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTable(currentTable, "Skyinix", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(skyinixWM);
		
		JMenuItem dryerWm = new JMenuItem("Dryer");
		dryerWm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Washing_Machine";
				loadMenuTableModel(currentTable, "Dryer", menuCalculation, menuNumber);
			}
		});
		washingMachineMenu.add(dryerWm);
		
		JMenu mnNewMenu = new JMenu("အဝတ်ဗီရို");
		firstMenuBar.add(mnNewMenu);
		
		JMenuItem allWardrobe = new JMenuItem("All products");
		allWardrobe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				currentTable = "Wardrobe";
				loadTable(currentTable, totalCalculationWardrobe, totalNumberWardrobe);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		mnNewMenu.add(allWardrobe);
		
		JMenuBar secondMenuBar = new JMenuBar();
		header.add(secondMenuBar, BorderLayout.SOUTH);
		
		JMenu phoneMenu = new JMenu("Phone & Power Bank");
		secondMenuBar.add(phoneMenu);
		
		JMenuItem allPhone = new JMenuItem("All Products");
		allPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Phone";
				loadTable(currentTable, totalCalculationPhone, totalNumberPhone);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		phoneMenu.add(allPhone);
		
		JMenu phoneSubMenu = new JMenu("Phone");
		phoneSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTable(currentTable, "iPhone", menuCalculation, menuNumber);
			}
		});
		phoneMenu.add(phoneSubMenu);
		
		JMenuItem allProductPhones = new JMenuItem("All Products");
		allProductPhones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Phone";
				loadMenuTableModel(currentTable, "Phone" ,totalCalculationPhone, totalNumberPhone);
				autoUpdateAllProductTable(currentTable);
			}
		});
		phoneSubMenu.add(allProductPhones);
		
		JMenuItem iphone = new JMenuItem("iPhone");
		iphone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableTwoWhere(currentTable, "iPhone", "Phone", menuCalculation, menuNumber);
			}
		});
		phoneSubMenu.add(iphone);
		
		JMenuItem oppoPhone = new JMenuItem("Oppo");
		oppoPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableTwoWhere(currentTable, "Oppo", "Phone", menuCalculation, menuNumber);
			}
		});
		phoneSubMenu.add(oppoPhone);
		
		JMenuItem vivoPhone = new JMenuItem("Vivo");
		vivoPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableTwoWhere(currentTable, "Vivo", "Phone", menuCalculation, menuNumber);
			}
		});
		phoneSubMenu.add(vivoPhone);
		
		JMenuItem samsungPhone = new JMenuItem("Samsung");
		samsungPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableTwoWhere(currentTable, "Samsung", "Phone", menuCalculation, menuNumber);
			}
		});
		phoneSubMenu.add(samsungPhone);
		
		JMenuItem realmePhone = new JMenuItem("Realme");
		realmePhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableTwoWhere(currentTable, "Realme", "Phone", menuCalculation, menuNumber);
			}
		});
		phoneSubMenu.add(realmePhone);
		
		

		JMenuItem huaweiPhone = new JMenuItem("Huawei");
		huaweiPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableTwoWhere(currentTable, "Huawei", "Phone", menuCalculation, menuNumber);
			}
		});
		phoneSubMenu.add(huaweiPhone);
		
		JMenuItem oneplusPhone = new JMenuItem("One plus");
		oneplusPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableTwoWhere(currentTable, "One plus", "Phone", menuCalculation, menuNumber);
			}
		});
		phoneSubMenu.add(oneplusPhone);
		
		JMenuItem itelPhone = new JMenuItem("itel");
		itelPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableTwoWhere(currentTable, "itel", "Phone", menuCalculation, menuNumber);
			}
		});
		phoneSubMenu.add(itelPhone);
		
		
		JMenu miPhone = new JMenu("Mi");
		miPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableModel(currentTable, "Mi", menuCalculation, menuNumber);
			}
		});
		phoneSubMenu.add(miPhone);
		
		JMenuItem xiaoMi = new JMenuItem("Xiaomi");
		xiaoMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableTwoWhere(currentTable, "Xiaomi", "Phone", menuCalculation, menuNumber);
			}
		});
		miPhone.add(xiaoMi);
		
		JMenuItem redMI = new JMenuItem("Redmi");
		redMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Phone";
				loadMenuTableTwoWhere(currentTable, "Redmi", "Phone", menuCalculation, menuNumber);
			}
		});
		miPhone.add(redMI);
		
		JMenuItem technoPhone = new JMenuItem("Techno");
		technoPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Phone";
				loadMenuTableModel(currentTable, "Phone" ,totalCalculationPhone, totalNumberPhone);
				autoUpdateAllProductTable(currentTable);
			}
		});
		phoneSubMenu.add(technoPhone);
		
		JMenu tabletMenu = new JMenu("Tablet");
		phoneMenu.add(tabletMenu);
		
		JMenuItem allTablets = new JMenuItem("All Products");
		allTablets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Phone";
				loadMenuTableModel(currentTable, "Tablet" ,totalCalculationPhone, totalNumberPhone);
				autoUpdateAllProductTable(currentTable);
			}
		});
		
		tabletMenu.add(allTablets);
		
		JMenu powerBankMenu = new JMenu("Power Bank");
		phoneMenu.add(powerBankMenu);
		
		JMenuItem allPowerBank = new JMenuItem("All Products");
		allPowerBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Phone";
				loadMenuTableModel(currentTable, "Power Bank" ,totalCalculationPhone, totalNumberPhone);
				autoUpdateAllProductTable(currentTable);
			}
		});
		powerBankMenu.add(allPowerBank);
		
		JMenu generatorMenu = new JMenu("Generator");
		secondMenuBar.add(generatorMenu);
		
		JMenuItem allGenerator = new JMenuItem("All Products");
		allGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Generator";
				loadTable(currentTable, totalCalculationG, totalNumberG);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		generatorMenu.add(allGenerator);
		glacierFreezer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Generator";
				loadMenuTableTwoWhere(currentTable, "Yamaishi", "Generator", menuCalculation, menuNumber);
			}
		});
		
		JMenu soundBox = new JMenu("Sound Box & Amplifier");
		secondMenuBar.add(soundBox);
		
		JMenuItem allsoundbox = new JMenuItem("All Products");
		allsoundbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "SoundBox";
				loadTable(currentTable, totalCalculationSB, totalNumberSB);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});

		soundBox.add(allsoundbox);
		
		JMenu soundBoxMenu = new JMenu("Sound Box");
		soundBox.add(soundBoxMenu);
		
		JMenuItem allproductSB = new JMenuItem("All Products");
		allproductSB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "SoundBox";
				loadMenuTableModel(currentTable, "Soundbox" ,totalCalculationSB, totalNumberSB);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		soundBoxMenu.add(allproductSB);
		
		JMenuItem TopSB = new JMenuItem("Top");
		TopSB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "Top", "Soundbox", menuCalculation, menuNumber);
			}
		});
		soundBoxMenu.add(TopSB);
		
		JMenuItem jblSB = new JMenuItem("JBL");
		jblSB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "JBL", "Soundbox", menuCalculation, menuNumber);
			}
		});
		soundBoxMenu.add(jblSB);
		
		JMenuItem paSB = new JMenuItem("PA");
		paSB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "PA", "Soundbox", menuCalculation, menuNumber);
			}
		});
		soundBoxMenu.add(paSB);
		
		JMenuItem nibbanSB = new JMenuItem("Nibban");
		nibbanSB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "Nibban", "Soundbox", menuCalculation, menuNumber);
			}
		});
		soundBoxMenu.add(nibbanSB);
		
		JMenuItem skgSB = new JMenuItem("SKG");
		skgSB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "SKG", "Soundbox", menuCalculation, menuNumber);
			}
		});
		soundBoxMenu.add(skgSB);
		
		JMenu amplifierMenu = new JMenu("Amplifier");
		soundBox.add(amplifierMenu);
		
		JMenuItem allproductAP = new JMenuItem("All Products");
		allproductAP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "SoundBox";
				loadMenuTableModel(currentTable, "Amplifier" ,totalCalculationSB, totalNumberSB);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		amplifierMenu.add(allproductAP);
		
		JMenuItem topA = new JMenuItem("Top");
		topA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "Top", "Amplifier", menuCalculation, menuNumber);
			}
		});
		amplifierMenu.add(topA);
		
		JMenuItem royalA = new JMenuItem("Royal");
		royalA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "Royal", "Amplifier", menuCalculation, menuNumber);
			}
		});
		amplifierMenu.add(royalA);
		
		JMenuItem kozaA = new JMenuItem("KOZA");
		kozaA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "KOZA", "Amplifier", menuCalculation, menuNumber);
			}
		});
		amplifierMenu.add(kozaA);
		
		JMenuItem nibbanA = new JMenuItem("Nibban");
		nibbanA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "Nibban", "Amplifier", menuCalculation, menuNumber);
			}
		});
		amplifierMenu.add(nibbanA);
		
		JMenuItem btA = new JMenuItem("BT");
		btA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "BT", "Amplifier", menuCalculation, menuNumber);
			}
		});
		amplifierMenu.add(btA);
		
		JMenuItem jarguarA = new JMenuItem("Jarguar");
		jarguarA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "Jarguar", "Amplifier", menuCalculation, menuNumber);
			}
		});
		amplifierMenu.add(jarguarA);
		
		JMenuItem villiobor = new JMenuItem("Vlliobor");
		villiobor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "Vlliobor", "Amplifier", menuCalculation, menuNumber);
			}
		});
		amplifierMenu.add(villiobor);
		
		JMenuItem dbxA = new JMenuItem("dbx");
		villiobor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "SoundBox";
				loadMenuTableTwoWhere(currentTable, "dbx", "Amplifier", menuCalculation, menuNumber);
			}
		});
		amplifierMenu.add(dbxA);
		
		JMenu waterPumpMenu = new JMenu("Water Pump");
		secondMenuBar.add(waterPumpMenu);
		
		JMenuItem allproductPump = new JMenuItem("All Products");
		allproductPump.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Water_Pump";
				loadMenuTableModel(currentTable, "Water pump" ,totalCalculationFreezer, totalNumberFreezer);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		waterPumpMenu.add(allproductPump);
		
		
		JMenuItem yamabishiWP = new JMenuItem("Yamabishi");
		yamabishiWP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Pump";
				loadMenuTableTwoWhere(currentTable, "Yamabishi", "Water pump", menuCalculation, menuNumber);
			}
		});
		waterPumpMenu.add(yamabishiWP);
		
		JMenuItem robinsonP = new JMenuItem("Robinson");
		robinsonP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Pump";
				loadMenuTableTwoWhere(currentTable, "Robinson", "Water pump", menuCalculation, menuNumber);
			}
		});
		waterPumpMenu.add(robinsonP);
		
		JMenuItem leoP = new JMenuItem("LEO");
		leoP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Pump";
				loadMenuTableTwoWhere(currentTable, "LEO", "Water pump", menuCalculation, menuNumber);
			}
		});
		waterPumpMenu.add(leoP);
		
		JMenuItem crocodileP = new JMenuItem("Crocodile");
		crocodileP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Pump";
				loadMenuTableTwoWhere(currentTable, "Crocodile", "Water pump", menuCalculation, menuNumber);
			}
		});
		waterPumpMenu.add(crocodileP);
		
		JMenuItem punaP = new JMenuItem("PUNA");
		punaP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Water_Pump";
				loadMenuTableTwoWhere(currentTable, "PUNA", "Water pump", menuCalculation, menuNumber);
			}
		});
		waterPumpMenu.add(punaP);
		
		JMenu kitchenMenu = new JMenu("မီးဖိုချောင်သုံးပစ္စည်း");
		secondMenuBar.add(kitchenMenu);
		
		JMenuItem allKitchen = new JMenuItem("All Products");
		allKitchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Kitchen";
				loadTable(currentTable, totalCalculationKitchen, totalNumberKitchen);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		
				kitchenMenu.add(allKitchen);
				
				JMenu cook = new JMenu("ထမင်းပေါင်းအိုး");
				kitchenMenu.add(cook);
				
				JMenuItem allproductCook = new JMenuItem("All Products");
				allproductCook.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Audio effect
						try {
							clickClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						currentTable = "Kitchen";
						loadMenuTableModel(currentTable, "Cook" ,totalCalculationKitchen, totalNumberKitchen);
						alertLoading(currentTable);
						autoUpdateAllProductTable(currentTable);
					}
				});
				cook.add(allproductCook);
				
				JMenu boiler = new JMenu("ရေနွေးအိုး");
				kitchenMenu.add(boiler);
				
				JMenuItem allProductsBoiler = new JMenuItem("All Products");
				allProductsBoiler.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Audio effect
						try {
							clickClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						currentTable = "Kitchen";
						loadMenuTableModel(currentTable, "Boiler" ,totalCalculationAC, totalCalculationAC);
						autoUpdateAllProductTable(currentTable);
					}
				});
				boiler.add(allProductsBoiler);
				
				JMenu microwaveOven = new JMenu("Microwave Oven");
				kitchenMenu.add(microwaveOven);
				
				JMenuItem allProductsMO = new JMenuItem("All Products");
				allProductsMO.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Audio effect
						try {
							clickClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						currentTable = "Kitchen";
						loadMenuTableModel(currentTable, "Microwave" ,totalCalculationKitchen, totalNumberKitchen);
						alertLoading(currentTable);
						autoUpdateAllProductTable(currentTable);
					}
				});
				microwaveOven.add(allProductsMO);
				
				JMenu coffeeMaker = new JMenu("ကော်ဖီနှပိစက်");
				kitchenMenu.add(coffeeMaker);
				
				JMenuItem allProductsCoffeeMaker = new JMenuItem("All Products");
				allProductsCoffeeMaker.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Audio effect
						try {
							clickClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						currentTable = "Kitchen";
						loadMenuTableModel(currentTable, "Coffee-Maker" ,totalCalculationKitchen, totalNumberKitchen);
						alertLoading(currentTable);
						autoUpdateAllProductTable(currentTable);
					}
				});
				coffeeMaker.add(allProductsCoffeeMaker);
				
				JMenu cooker = new JMenu("မီးဖို & ဟင်းကြော်အိုး");
				kitchenMenu.add(cooker);
				
				JMenu electronicCooker = new JMenu("လျှပ်စစ်ဟင်းကြော်အိုး");
				cooker.add(electronicCooker);
				
				JMenuItem AllElectronicFryer = new JMenuItem("All Products");
				AllElectronicFryer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Audio effect
						try {
							clickClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						currentTable = "Kitchen";
						loadMenuTableModel(currentTable, "Electronic-Fryer" ,totalCalculationKitchen, totalNumberKitchen);
						alertLoading(currentTable);
						autoUpdateAllProductTable(currentTable);
					}
				});
				electronicCooker.add(AllElectronicFryer);
				
				JMenu gasOven = new JMenu("ဂက်စ်မီးဖို");
				cooker.add(gasOven);
				
				JMenuItem allGasFryer = new JMenuItem("All Products");
				allGasFryer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Audio effect
						try {
							clickClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						currentTable = "Kitchen";
						loadMenuTableModel(currentTable, "Gas-Fryer" ,totalCalculationKitchen, totalNumberKitchen);
						alertLoading(currentTable);
						autoUpdateAllProductTable(currentTable);
					}
				});
				gasOven.add(allGasFryer);
				
				JMenu digitalOven = new JMenu("Digital မီးဖို ");
				cooker.add(digitalOven);
				
				JMenuItem allDigitalFryer = new JMenuItem("All Products");
				allDigitalFryer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Audio effect
						try {
							clickClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						currentTable = "Kitchen";
						loadMenuTableModel(currentTable, "Digital-Fryer" ,totalCalculationKitchen, totalNumberKitchen);
						alertLoading(currentTable);
						autoUpdateAllProductTable(currentTable);
					}
				});
				digitalOven.add(allDigitalFryer);
				
				JMenu grill = new JMenu("အသားကင်စက်");
				cooker.add(grill);
				
				JMenuItem allGrill = new JMenuItem("All Products");
				allGrill.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Audio effect
						try {
							clickClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						currentTable = "Kitchen";
						loadMenuTableModel(currentTable, "Grill" ,totalCalculationKitchen, totalNumberKitchen);
						alertLoading(currentTable);
						autoUpdateAllProductTable(currentTable);
					}
				});
				grill.add(allGrill);
				
				JMenu blenderSubMenu = new JMenu("ကြိတ်စက်");
				kitchenMenu.add(blenderSubMenu);
				
				JMenuItem allProductsBlender = new JMenuItem("All Products");
				allProductsBlender.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Audio effect
						try {
							clickClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						currentTable = "Kitchen";
						loadMenuTableModel(currentTable, "Blender" ,totalCalculationKitchen, totalNumberKitchen);
						alertLoading(currentTable);
						autoUpdateAllProductTable(currentTable);
					}
				});
				blenderSubMenu.add(allProductsBlender);
		
		JMenu bathMenu = new JMenu("ရေချိုးခန်းအိမ်သာပစ္စည်း");
		secondMenuBar.add(bathMenu);
		
		JMenuItem allBath = new JMenuItem("All Products");
		allBath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Bath_Products";
				loadTable(currentTable, totalCalculationBath, totalNumberBath);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		bathMenu.add(allBath);
		
		JMenu ToiletMenu = new JMenu("ဘိုထိုင်အိမ်သာ");
		bathMenu.add(ToiletMenu);
		
		JMenuItem allToilet = new JMenuItem("All Products");
		allToilet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Bath_Products";
				loadMenuTableModel(currentTable, "Toilet" ,totalCalculationBath, totalNumberBath);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		ToiletMenu.add(allToilet);
		
		JMenu BasinMenu = new JMenu("လက်ဆေးကန်/ ဘေစင်");
		bathMenu.add(BasinMenu);
		
		JMenuItem allBasin = new JMenuItem("All Products");
		allBasin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Bath_Products";
				loadMenuTableModel(currentTable, "Basin" ,totalCalculationBath, totalNumberBath);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		BasinMenu.add(allBasin);
		
		JMenu bathBox = new JMenu("ရေချိုးကန်");
		bathMenu.add(bathBox);
		
		JMenuItem allBathTub = new JMenuItem("All Products");
		allBathTub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Bath_Products";
				loadMenuTableModel(currentTable, "Bath-tub" ,totalCalculationBath, totalNumberBath);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		bathBox.add(allBathTub);
		
		JMenu shower = new JMenu("ရေပန်း ");
		bathMenu.add(shower);
		
		JMenuItem allShower = new JMenuItem("All Products");
		allShower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Bath_Products";
				loadMenuTableModel(currentTable, "Shower" ,totalCalculationBath, totalNumberBath);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		shower.add(allShower);
		
		JMenu batteryMenu = new JMenu("Battery");
		secondMenuBar.add(batteryMenu);
		
		JMenuItem allBattery = new JMenuItem("All Products");
		allBattery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				currentTable = "Battery";
				loadTable(currentTable, totalCalculationBattery, totalNumberBattery);
				alertLoading(currentTable);
				autoUpdateAllProductTable(currentTable);
			}
		});
		batteryMenu.add(allBattery);
		
		JMenu dryBattery = new JMenu("Dry Battery");
		batteryMenu.add(dryBattery);
		
		JMenuItem threekDry = new JMenuItem("3K");
		threekDry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Battery";
				loadMenuTableTwoWhere(currentTable, "3K", "Dry", menuCalculation, menuNumber);
			}
		});
		dryBattery.add(threekDry);
		
		JMenuItem globalDry = new JMenuItem("Global");
		globalDry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Battery";
				loadMenuTableTwoWhere(currentTable, "Global", "Dry", menuCalculation, menuNumber);
			}
		});
		dryBattery.add(globalDry);
		
		JMenuItem toyoDry = new JMenuItem("Toyo");
		toyoDry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Battery";
				loadMenuTableTwoWhere(currentTable, "Toyo", "Dry", menuCalculation, menuNumber);
			}
		});
		dryBattery.add(toyoDry);
		
		JMenuItem dynamDry = new JMenuItem("DYNAM");
		dynamDry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Battery";
				loadMenuTableTwoWhere(currentTable, "DYNAM", "Dry", menuCalculation, menuNumber);
			}
		});
		dryBattery.add(dynamDry);
		
		JMenu wetBattery = new JMenu("Wet Battery");
		batteryMenu.add(wetBattery);
		
		JMenuItem toyoWet = new JMenuItem("Toyo");
		toyoWet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Battery";
				loadMenuTableTwoWhere(currentTable, "Toyo", "Wet", menuCalculation, menuNumber);
			}
		});
		wetBattery.add(toyoWet);
		
		JMenuItem threekWet = new JMenuItem("3K");
		threekWet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Battery";
				loadMenuTableTwoWhere(currentTable, "3K", "Wet", menuCalculation, menuNumber);
			}
		});
		wetBattery.add(threekWet);
		
		JMenuItem gsWet = new JMenuItem("GS");
		gsWet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Battery";
				loadMenuTableTwoWhere(currentTable, "GS", "Wet", menuCalculation, menuNumber);
			}
		});
		wetBattery.add(gsWet);
		
		JMenuItem fujiWet = new JMenuItem("Fuji");
		fujiWet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Battery";
				loadMenuTableTwoWhere(currentTable, "Fuji", "Wet", menuCalculation, menuNumber);
			}
		});
		wetBattery.add(fujiWet);
		
		JMenuItem tataWet = new JMenuItem("TATA");
		tataWet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				currentTable = "Battery";
				loadMenuTableTwoWhere(currentTable, "TATA", "Wet", menuCalculation, menuNumber);
			}
		});
		wetBattery.add(tataWet);
		
		JMenu electronicMenu = new JMenu("မီးပစ္စည်း");
		secondMenuBar.add(electronicMenu);
		//End of header section
		
		
		//Menu section
		JPanel sidemenu = new JPanel();
		sidemenu.setBackground(new Color(255, 140, 0));
		frmYeeShinProduct.getContentPane().add(sidemenu, BorderLayout.WEST);
		sidemenu.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane menuScrollPane = new JScrollPane();
		sidemenu.add(menuScrollPane);
		
		JPanel menuItemPanel = new JPanel();
		menuScrollPane.setViewportView(menuItemPanel);
		menuItemPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//--Add button
		JButton add = new JButton("စာရင်းထည့်");
		menuItemPanel.add(add);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String iD, namE, modeL, stocK, pricE, date, totalAmountString;
				int totalAmount;
				iD = addID.getText();
				namE = addName.getText();
				modeL = addModel.getText();
				stocK = addQuantity.getText();
				pricE = addPrice.getText();
				date = addDate.getText();
				totalAmount = Integer.valueOf(stocK) * Integer.valueOf(pricE);
				totalAmountString = Integer.toString(totalAmount);
				String confirmation = JOptionPane
						.showInputDialog("Are you sure to add " + namE + " into "+ currentTable + "\n \n Yes or No");
				if (confirmation.toUpperCase().equals("YES")) {
					try {
						pst = con.prepareStatement("insert into " + currentTable + "(id, Product_Name, Product_Model, Quantity_Stock, Buy_Date, Price, Total_amount) value (?,?,?,?,?,?,?)");
						pst.setString(1, iD);
						pst.setString(2, namE);
						pst.setString(3, modeL);
						pst.setString(4, stocK);
						pst.setString(5, date);
						pst.setString(6, pricE);
						pst.setString(7, totalAmountString);
						pst.executeUpdate();
						
						try {
							successClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Record Added!!!!!");
						cleanText();
						mainTable.setModel(new DefaultTableModel());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Fail in adding item");
						e1.printStackTrace();
					}
				}
			}
		});
		//End of Add button
		
		//--Update button
		JButton update = new JButton("စာရင်းပြင်");
		menuItemPanel.add(update);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String iD, namE, modeL, stocK, pricE, datE, totalAmountString;
				int totalAmount;
				iD = addID.getText();
				namE = addName.getText();
				modeL = addModel.getText();
				stocK = addQuantity.getText();
				datE = addDate.getText();
				pricE = addPrice.getText();
				totalAmount = Integer.valueOf(stocK) * Integer.valueOf(pricE);
				totalAmountString = Integer.toString(totalAmount);
				
				String confirmation = JOptionPane
						.showInputDialog("Are you sure to make an update on " + namE + " " + modeL + "\n \n Yes or No");
				if (confirmation.toUpperCase().equals("YES")) {
					try {
						pst = con.prepareStatement("update " + currentTable + " set Product_Name = ?, Product_Model = ?, Quantity_Stock = ?, Buy_Date = ?, Price = ?, Total_amount = ?  where id = ?");
						pst.setString(1, namE);
						pst.setString(2, modeL);
						pst.setString(3, stocK);
						pst.setString(4, datE);
						pst.setString(5, pricE);
						pst.setString(6, totalAmountString);
						pst.setString(7, iD);
						pst.executeUpdate();
					
						// Sound effect
						try {
							successClip();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Record Updated!!!!!");
						
						cleanText();
						mainTable.setModel(new DefaultTableModel());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Update fail!!!!!");
						e1.printStackTrace();
					}
				}
			}
		});
		//End of update button
		
		//--Delete button
		JButton delete = new JButton("စာရင်းဖျက်");
		menuItemPanel.add(delete);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					alertClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String productID;
				productID = addID.getText();

				String confirmation = JOptionPane.showInputDialog(
						"Are you sure to delete the product"+ "\n \n Yes or No");
				if (productID.equals("")) {
					try {
						alertClip();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "No product is selected");
				} else {
					if (confirmation.toUpperCase().equals("YES")) {
						try {

							pst = con.prepareStatement("delete from " + currentTable + " where id = ?");
							pst.setString(1, productID);
							pst.executeUpdate();

							// Sound effect
							try {
								successClip();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
							cleanText();
							mainTable.setModel(new DefaultTableModel());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		//End of delete button
		
		//--Clear button
		JButton clear = new JButton("Clear");
		menuItemPanel.add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				cleanText();
			}
		});
		//End of Clear button
		
		//--Refresh button
		JButton refresh = new JButton("Refresh");
		menuItemPanel.add(refresh);
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainTable.setModel(new DefaultTableModel());
				quantityZeroTable.setModel(new DefaultTableModel());
				
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cleanText();
			}
		});
		//End of Refresh button
		
		//--Help button
		JButton help_1 = new JButton("Help");
		menuItemPanel.add(help_1);
		help_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				HelpScreen help = new HelpScreen();
				help.frame.setVisible(true);
			}
		});
	    //End of menu section
		
		
		//Footer section
		JPanel footer = new JPanel();
		frmYeeShinProduct.getContentPane().add(footer, BorderLayout.SOUTH);
		footer.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel calendarPanel = new JPanel();
		calendarPanel.setBackground(new Color(192, 192, 192));
		footer.add(calendarPanel);
		calendarPanel.setLayout(new BorderLayout(0, 0));
		
		JDayChooser dayChooser = new JDayChooser();
		calendarPanel.add(dayChooser, BorderLayout.CENTER);
		
		JMonthChooser monthChooser = new JMonthChooser();
		calendarPanel.add(monthChooser, BorderLayout.NORTH);
		
		JYearChooser yearChooser = new JYearChooser();
		calendarPanel.add(yearChooser, BorderLayout.WEST);
		
		
		//--Adding product section
		JPanel addPanel = new JPanel();
		addPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Add or Update or Delete Product", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		footer.add(addPanel);
		addPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("ID");
		addPanel.add(lblNewLabel_7);
		
		addID = new JTextField();
		addID.setColumns(10);
		addPanel.add(addID);
		
		JLabel lblNewLabel_1_2 = new JLabel("ပစ္စည်းနာမည်");
		addPanel.add(lblNewLabel_1_2);
		
		addName = new JTextField();
		addName.setColumns(10);
		addPanel.add(addName);
		
		JLabel lblNewLabel_2_2 = new JLabel("ပစ္စည်းမော်ဒယ်");
		addPanel.add(lblNewLabel_2_2);
		
		addModel = new JTextField();
		addModel.setColumns(10);
		addPanel.add(addModel);
		
		JLabel lblNewLabel_3_2 = new JLabel("အရေအတွက်\n");
		addPanel.add(lblNewLabel_3_2);
		
		addQuantity = new JTextField();
		addQuantity.setColumns(10);
		addPanel.add(addQuantity);
		
		JLabel lblNewLabel_4_2 = new JLabel("အရေအတွက်ပြောင်းသောနေ့");
		addPanel.add(lblNewLabel_4_2);
		
		addDate = new JTextField();
		addDate.setColumns(10);
		addPanel.add(addDate);
		
		JLabel lblNewLabel_5_2 = new JLabel("ပစ္စည်းစျေး");
		addPanel.add(lblNewLabel_5_2);
		
		addPrice = new JTextField();
		addPrice.setColumns(10);
		addPanel.add(addPrice);
		
		searchID = new JTextField();
		searchID.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		searchID.setBackground(new Color(255, 255, 0));
		searchID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String pID = searchID.getText();
					pst = con.prepareStatement(
							"select Product_Name, Product_Model, Quantity_Stock, Buy_Date, Price from " + currentTable
									+ " where id = ?");
					pst.setString(1, pID);
					ResultSet rs = pst.executeQuery();

					if (rs.next() == true) {
						String nameP = rs.getString(1);
						String modelP = rs.getString(2);
						String stockP = rs.getString(3);
						String dateP = rs.getString(4);
						String priceP = rs.getString(5);

						addID.setText(pID);
						addName.setText(nameP);
						addModel.setText(modelP);
						addQuantity.setText(stockP);
						addPrice.setText(priceP);
						addDate.setText(dateP);
					} else {
						addID.setText("");
						addName.setText("");
						addModel.setText("");
						addQuantity.setText("");
						addPrice.setText("");
						addDate.setText("");
					}
				} catch (SQLException ex) {

				}
			}
		});
		searchID.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		addPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
	
		
		JLabel lblNewLabel_1_2_2 = new JLabel("id ဖြင့်ပစ္စည်းရှာမယ်");
		lblNewLabel_1_2_2.setForeground(Color.RED);
		panel.add(lblNewLabel_1_2_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		addPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_1.add(searchID);
		//--End of adding section
		
		
		//--Start of Exit Panel Section
		JPanel exit = new JPanel();
		exit.setBackground(new Color(192, 192, 192));
		footer.add(exit);
		exit.setLayout(new GridLayout(2, 0, 0, 0));
		
		
		//Total value showing section
		JPanel totalPanel = new JPanel();
		exit.add(totalPanel);
		totalPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1_2_1 = new JLabel("In stock ပစ္စည်းအရေအတွက်");
		totalPanel.add(lblNewLabel_1_2_1);
		
		TotalNumber = new JTextField();
		TotalNumber.setColumns(10);
		totalPanel.add(TotalNumber);
		
		JLabel lblNewLabel_7_1 = new JLabel("စုစုပေါင်းမတည်ငွေ");
		totalPanel.add(lblNewLabel_7_1);
		
		TotalAmount = new JTextField();
		TotalAmount.setColumns(10);
		totalPanel.add(TotalAmount);
		
		JPanel exitPanel = new JPanel();
		exit.add(exitPanel);
		exitPanel.setLayout(null);
		//End of Total value showing section
		
		
		//Exit button
		JButton exitB = new JButton("Exit");
		exitB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Audio effect
				try {
					alertClip();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				frmYeeShinProduct = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmYeeShinProduct, "Confirm You Want to Exit", "YeeShin", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					// Audio effect
					try {
						clickClip();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					System.exit(0);
				}
			}
		});
		exitB.setBounds(345, 79, 117, 29);
		exitPanel.add(exitB);
		//End of exit button section
		//--End of Footer Section
		
		//Table section
		JPanel tablePanel = new JPanel();
		frmYeeShinProduct.getContentPane().add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		tablePanel.add(scrollPane);
		
		mainTable = new JTable();
		scrollPane.setViewportView(mainTable);
		
		JPanel EastPanel = new JPanel();
		frmYeeShinProduct.getContentPane().add(EastPanel, BorderLayout.EAST);
		EastPanel.setLayout(new BorderLayout(0, 0));
		 
		AlarmLabel = new JLabel("          No instock product          ");
		AlarmLabel.setBackground(new Color(0, 0, 255));
		AlarmLabel.setVerticalAlignment(SwingConstants.TOP);
		AlarmLabel.setForeground(new Color(255, 0, 0));
		AlarmLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		EastPanel.add(AlarmLabel, BorderLayout.NORTH);
		
		
		quantityZeroTable = new JTable();
		quantityZeroTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		EastPanel.add(quantityZeroTable, BorderLayout.CENTER);
		
		//End of table section
	}
}
