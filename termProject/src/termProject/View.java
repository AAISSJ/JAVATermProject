package termProject;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.imageio.ImageIO;
import javax.swing.Icon;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JToolBar;


// class 1 View
public class View {

	//variables to use 
	private JFrame frame;
	private static String directoryName=Paths.get("").toAbsolutePath().toString();


	private JTextField txtUser;
	private JTextField txtBudget;
	public static ImagePanel designPanel;
	int currentCost=0;
	String mybudget="";
	private JButton btnGetImages;
	JLabel labelImage;
	Point startPoint;
	int cnt=0;
	int cntind=0;
	String imgPath[] = new String[16];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		String directoryName=View.class.getResource("").getPath();
		System.out.println(directoryName);

		//post an event at the end of Swings event list 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}


	// To get a screenShot of arranged furnitures
	public static BufferedImage getScreenShot(Component component) {
		
		BufferedImage screenShotget = new BufferedImage(component.getWidth(),component.getHeight(),BufferedImage.TYPE_INT_RGB);
		component.paint(screenShotget.getGraphics());
		return screenShotget;
		
	}
	
	// Save the screenShot in the current Directory
	public static void SaveScreenShot(Component component,String filename) throws Exception {
		
		BufferedImage screenShotsave=getScreenShot(component);
		ImageIO.write(screenShotsave, "png" ,new File (filename));
	} 
	

	// initialize view 
	private void initialize() {

//		String directoryName=this.getClass().getResource("").getPath();
		
		//make a frame
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setSize(801,801);
		
		//Settings for File I/O
		String filePath = directoryName+"Furnituredata.txt";
        File file = new File(filePath);

        
        /* related to welcomePanel*/
        
        
		ImagePanel welcomePanel=new ImagePanel(new ImageIcon(directoryName+"/image/welcome_2.jpg").getImage());
		welcomePanel.setBounds(0, 0, 789, 768);
		
		frame.getContentPane().add(welcomePanel);
		
		// label and textfield to  input user name
		JLabel lblUser = new JLabel("Your name:");
		lblUser.setBackground(Color.LIGHT_GRAY);
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Georgia", Font.PLAIN, 25));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBounds(290, 272, 202, 81);
		welcomePanel.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setToolTipText("what's your name?");
		txtUser.setHorizontalAlignment(SwingConstants.CENTER);
		txtUser.setForeground(Color.GRAY);
		txtUser.setBackground(SystemColor.text);
		txtUser.setFont(new Font("Georgia", Font.BOLD, 16));
		txtUser.setBounds(318, 346, 141, 42);
		txtUser.setColumns(10);
		welcomePanel.add(txtUser);
		
		// label and textField to input budget
		JLabel lblBudget = new JLabel("Your budget (KRW):");
		lblBudget.setToolTipText("");
		lblBudget.setForeground(SystemColor.text);
		lblBudget.setHorizontalAlignment(SwingConstants.CENTER);
		lblBudget.setFont(new Font("Georgia", Font.PLAIN, 25));
		lblBudget.setBounds(274, 413, 250, 81);
		
		welcomePanel.add(lblBudget);
		
		txtBudget = new JTextField();
		txtBudget.setForeground(Color.GRAY);
		txtBudget.setHorizontalAlignment(SwingConstants.CENTER);
		txtBudget.setToolTipText("how much money do you have? ");
		txtBudget.setFont(new Font("Georgia", Font.BOLD, 16));
		txtBudget.setBounds(318, 497, 141, 42);
		txtBudget.setColumns(10);
		welcomePanel.add(txtBudget);
		
		
		// label of title 
		JLabel lblTitle = new JLabel("Inside your own space");
		lblTitle.setForeground(new Color(102, 153, 204));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Georgia", Font.PLAIN, 38));
		lblTitle.setBounds(186, 123, 448, 103);
		welcomePanel.add(lblTitle);
        
		// define variables related selectPanel beforehand used in btnNext1  
		JPanel selectPanel = new JPanel();
		selectPanel.setVisible(false);
		JLabel lblMyBudget = new JLabel();
		JLabel lblHello = new JLabel("Select What You Want");
				
		
		//a button to go next selectPanel 
		JButton btnNext1 = new JButton("Design Your Space!");
		btnNext1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						if(txtUser.getText().length()>0&&txtBudget.getText().length()>0) {
							mybudget=txtBudget.getText();
							lblMyBudget.setText("My Budget: "+mybudget);
							lblHello.setText(" ' "+txtUser.getText()+" ' Hello! Select What You Want");
							welcomePanel.setVisible(false);	
							selectPanel.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "Please Fill in the blank!");
						}
					}
				});
				
		btnNext1.setForeground(SystemColor.textHighlightText);
		btnNext1.setBackground(SystemColor.activeCaption);
		btnNext1.setFont(new Font("Georgia", Font.PLAIN, 15));
		btnNext1.setBounds(303, 615, 189, 42);	
		welcomePanel.add(btnNext1);
		
		
		
        /* related to selectPanel*/        
        
		selectPanel.setBounds(0, 0,789, 768);
		selectPanel.setLayout(null);
		frame.getContentPane().add(selectPanel);
		
		
		//read a txt file about furnitures
		
        String[][] data1=new String[20][4];
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            Object[] lines = br.lines().toArray();
            
            for(int i = 0; i < lines.length; i++){
                String[] row1 = lines[i].toString().split(" ");
                data1[i][0]=row1[0];
                data1[i][1]=row1[1];
                data1[i][2]=row1[2];
                data1[i][3]=row1[3];
            }
            
        } catch (FileNotFoundException ex) {
            
        }
        
		// make a Jtable to show the data of furnitures (header, text, ImageCon)
		String[] headers=new String[] {"Name","Cost","Site URL","Image"};
		
		Object[][] rows=new Object[data1.length][4];
		for (int i=0;i<data1.length;i++) {
			rows[i][0]=data1[i][0];
			rows[i][1]=data1[i][1];
			rows[i][2]=data1[i][2];
			ImageIcon image= new ImageIcon(new ImageIcon(directoryName+data1[i][3]).getImage().getScaledInstance(150,120,Image.SCALE_SMOOTH));
			rows[i][3]=image;
		}
		
		TakeTable modelT=new TakeTable(rows,headers);
		
		
		// a label to show how much I have(=budget)
		lblMyBudget.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblMyBudget.setBounds(0, 717, 259, 36);
		lblMyBudget.setHorizontalAlignment(SwingConstants.CENTER);
		selectPanel.add(lblMyBudget);
		
		
		// a label to say hello to user
		lblHello.setBounds(0, 10, 789, 19);
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblHello.setFont(new Font("Georgia", Font.PLAIN, 20));
		lblHello.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectPanel.add(lblHello);
		
		
		// set a table to show information to users
		JTable table1=new JTable();
		table1.setModel(modelT);
		table1.setFont(new Font("Georgia", Font.PLAIN, 16));
		table1.setAlignmentX(0);
		table1.setRowHeight(120);
		table1.setSize(500,200);
		table1.setPreferredScrollableViewportSize(new Dimension(200, 400));
		JScrollPane scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(0, 39, 789, 280);
		selectPanel.add(scrollPane);
		
		
		// set a table to store the informations about the favorites
		JTable table2=new JTable(data1,headers);
		table2.setRowHeight(120);
		table2.setFont(new Font("Georgia", Font.PLAIN, 16));
		table2.setSize(500,200);
				
		table2.setPreferredScrollableViewportSize(new Dimension(200, 400));
		JScrollPane scrollPane_1 = new JScrollPane(table2);
		scrollPane_1.setBounds(0, 390, 789, 295);

		table2.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Name", "Cost", "Site URL", "Image"}) {
            public Class getColumnClass(int column)
            {
            	if(column == 3){
            		return Icon.class;
            	}
                return getValueAt(0, column).getClass();
            }
				});
		selectPanel.add(scrollPane_1);
													
		
		// a button to add a furniture to Table2
		JButton btnAdd = new JButton("ADD");
										
		
		// a label to show how much the current cost
		JLabel lblCurrentCost = new JLabel("Current Cost");
		lblCurrentCost.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblCurrentCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentCost.setBounds(271, 717, 237, 36);
		selectPanel.add(lblCurrentCost);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
																		
				TableModel model1=table1.getModel();
				int[] indexs=table1.getSelectedRows();	
				Object[] row=new Object[4];
				DefaultTableModel model2=(DefaultTableModel) table2.getModel();

				for (int i=0;i<indexs.length;i++) {
					row[0]=model1.getValueAt(indexs[i], 0);
					row[1]=model1.getValueAt(indexs[i], 1);
					row[2]=model1.getValueAt(indexs[i], 2);
					row[3]=model1.getValueAt(indexs[i], 3);									
					String s=(String)row[1];
					imgPath[cntind]=data1[indexs[i]][3];
				cntind++;
				currentCost+=Integer.parseInt(s);			
				model2.addRow(row);
				}
			String cost="Current Cost: "+Integer.toString(currentCost);
			lblCurrentCost.setText(cost);
			}
		});

		btnAdd.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnAdd.setForeground(SystemColor.text);
		btnAdd.setBackground(SystemColor.activeCaption);
		btnAdd.setBounds(271, 343, 91, 23);
		selectPanel.add(btnAdd);
		
		
		// a button to delete a furniture in the favorites
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(SystemColor.text);
		btnDelete.setBackground(SystemColor.activeCaption);
		btnDelete.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				DefaultTableModel model=(DefaultTableModel) table2.getModel();
				try {
					int SelectedRowIndex=table2.getSelectedRow();
					String s=(String)model.getValueAt(SelectedRowIndex, 1);
					String deleteimage=data1[SelectedRowIndex][3];
					currentCost-=Integer.parseInt(s);
					String cost="Current Cost: "+Integer.toString(currentCost);
					lblCurrentCost.setText(cost);

					model.removeRow(SelectedRowIndex);
				
							List<String> list = new ArrayList<String>(Arrays.asList(imgPath));
							list.remove(deleteimage);
							imgPath = list.toArray(new String[0]);

				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"No More Furniture to Delete");
				}					
			}
		});
		
		btnDelete.setBounds(404, 343, 91, 23);
		selectPanel.add(btnDelete);
		
		
		// define designPanel beforehand used in btnArrange
		designPanel=new ImagePanel(new ImageIcon(directoryName+"/image/empty_room4.png").getImage());
		designPanel.setVisible(false);
		
		// a button to go next designPanel to arrange
		JButton btnArrange = new JButton("Arrange Your Furniture");
		btnArrange.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			int budget=0;
			try {
				budget=Integer.parseInt(mybudget);
				}catch(Exception ee) {
					JOptionPane.showMessageDialog(null,"Too much money");
				}	
									
			if (currentCost==0) {
				JOptionPane.showMessageDialog(null,"Nothing You Select! \n Please select some furnitures");
				}
			else if (currentCost>budget) {
									
				JOptionPane.showMessageDialog(null,"You overspend your budget. \n Delete some furnitures to fit in the budget.");						
			}
			else {
				selectPanel.setVisible(false);		
				designPanel.setVisible(true);	
				}
			}
		});
		
		btnArrange.setForeground(SystemColor.text);
		btnArrange.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnArrange.setBackground(SystemColor.activeCaption);
		btnArrange.setBounds(507, 717, 282, 36);
		selectPanel.add(btnArrange);

		
		/* related to designPanel */
		
		
		designPanel.setBounds(0, 0, 789, 768);
		designPanel.setLayout(null);
		frame.getContentPane().add(designPanel);
		
		// a tool bar to collect two buttons 
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(210, 5, 368, 23);
		designPanel.add(toolBar);
		
		
		//a button to get Images
		btnGetImages = new JButton("Import Selected Images");
		btnGetImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
				        for (int i = 0; i < imgPath.length; i++) {
				        	String fileName = imgPath[i];
				        	if (fileName !=null) {
				        		addNewPhoto(fileName);
				        		System.out.println(fileName);
				        		}
				        	}
				        designPanel.repaint();
				        }
					});
				}
				});
		
		btnGetImages.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnGetImages.setForeground(SystemColor.text);
		btnGetImages.setBackground(SystemColor.inactiveCaption);
		toolBar.add(btnGetImages);
				
		
//				JButton btnDeleteImpImg = new JButton("Delete Imported image");
//				btnDeleteImpImg.setBackground(SystemColor.activeCaption);
//				btnDeleteImpImg.setForeground(SystemColor.text);
//				btnDeleteImpImg.setFont(new Font("Georgia", Font.PLAIN, 12));
//				toolBar.add(btnDeleteImpImg);
				
		
		//a button to take and save a screenshot
		JButton btnSaveImage = new JButton("Save this Screen");
		btnSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
							
					SaveScreenShot(designPanel,"MyRoomImage.png");
					JOptionPane.showMessageDialog(null,"Successfully Save!");
							
				}catch(Exception ex) {
							
					JOptionPane.showMessageDialog(null,"Cannot Save this image.");
					}
				}
			});
				
		btnSaveImage.setForeground(new Color(255, 255, 255));
		btnSaveImage.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnSaveImage.setBackground(new Color(173, 216, 230));
		toolBar.add(btnSaveImage);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	// methods to add images in designPanel
	public static void addNewPhoto(String fileName) {
		//Get resources from Directory or Jar file
		String directoryName=View.class.getResource("").getPath();
		Image imgk = Toolkit.getDefaultToolkit().createImage(directoryName+fileName);
    
		//Creates a draggableImageComponent and adds loaded image
		DraggableImageComponent photo = new DraggableImageComponent();
		designPanel.add(photo);
		photo.setImage(imgk);
		photo.setAutoSize(true);
		photo.setOverbearing(true);
		
		//A small randomization of object size/position
		int delta = designPanel.getWidth() / 4;
		int randomGrow = getRandom(getRandom(delta * 2));
		int cx = designPanel.getWidth() / 2;
		int cy = designPanel.getHeight() / 2;
		photo.setSize(delta + randomGrow, delta + randomGrow);
		photo.setLocation(cx + getRandom(delta / 2) - photo.getWidth() / 2, cy + getRandom(delta / 2) - photo.getHeight() / 2);
		designPanel.repaint();
	}

	public static int getRandom(int range) {
		int r = (int) (Math.random() * range) - range;
		return r;
	}

}


// class ImagePanel to set a background to a image
class ImagePanel extends JPanel{
	private Image img;
	
	public ImagePanel(Image img) {
		this.img=img;
		setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setLayout(null);
		
	}
	
	// get width of image
	public int getWidth() {
		return img.getWidth(null);
	}
	
	//get height of image
	public int getHeight() {
		return img.getHeight(null);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,null);

	}
}
