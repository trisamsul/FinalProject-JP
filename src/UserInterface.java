import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class UserInterface extends JFrame {
	
	private JPanel searchJPanel;
	private JPanel editJPanel;

	private JLabel searchLabel;
	private JLabel titleLabel;
	private JLabel authorLabel;
	private JLabel yearLabel;
	private JLabel shelfLabel;
	
    private JTextField searchTextField;
    private JTextField titleTextField;
    private JTextField authorTextField;
    private JTextField yearTextField;
    private JTextField shelfTextField;
    
    private JButton searchJButton;
    private JButton deleteJButton;
    private JButton editJButton;
    private JButton saveJButton;
    
    private JComboBox idJComboBox;
    private int id;
    private int selectedIndexId;
    private String[] idList;
    
    JTextArea resultJTextArea;
    
    private int statusEdit = 0;
	
	public UserInterface() {
        createUserInterface();
    }

	private void createUserInterface() {
		
		Container contentPane = getContentPane();
		
		contentPane.setLayout(null);
		
		//SEARCH PANEL
		// set up inputDetailJPanel
        searchJPanel = new JPanel();
        searchJPanel.setBounds(16, 16, 346, 250);
        searchJPanel.setBorder(new TitledBorder("Search Book Here"));
        searchJPanel.setLayout(null);
        contentPane.add(searchJPanel);
        
        // set up NameJLabel
        searchLabel = new JLabel();
        searchLabel.setBounds(16, 32, 180, 24);
        searchLabel.setText("Search:");
        searchJPanel.add(searchLabel);
        
        // set up NameJTextField
        searchTextField = new JTextField();
        searchTextField.setBounds(64, 32, 180, 24);
        searchTextField.setHorizontalAlignment(JTextField.LEFT);
        searchJPanel.add(searchTextField);
        
        // set up CreateAccountButton
        searchJButton = new JButton();
        searchJButton.setBounds(248, 32, 80, 24);
        searchJButton.setText("Search");
        searchJPanel.add(searchJButton);
        searchJButton.addActionListener(
    		new ActionListener() {
                // event handler called when CreateAccountJButton
                // is clicked
                public void actionPerformed(ActionEvent event) {
                	searchJButtonActionPerformed(event);
                }
     
            }
         
        ); // end call to addActionListener
        
        // set up displayJTextArea
        resultJTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultJTextArea); 
        scrollPane.setBounds(16,62,314,176); 
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);        
        searchJPanel.add(scrollPane);
        resultJTextArea.setEditable(false);
        
        //EDIT PANEL
        // set up editJPanel
        editJPanel = new JPanel();
        editJPanel.setBounds(364, 16, 202, 250);
        editJPanel.setBorder(new TitledBorder("Input & Edit"));
        editJPanel.setLayout(null);
        contentPane.add(editJPanel);
        
        // set up titleJLabel
        titleLabel = new JLabel();
        titleLabel.setBounds(16, 32, 180, 24);
        titleLabel.setText("Title:");
        editJPanel.add(titleLabel);
        
        // set up authorJLabel
        authorLabel = new JLabel();
        authorLabel.setBounds(16, 62, 180, 24);
        authorLabel.setText("Author:");
        editJPanel.add(authorLabel);
        
        // set up yearJLabel
        yearLabel = new JLabel();
        yearLabel.setBounds(16, 92, 180, 24);
        yearLabel.setText("Year:");
        editJPanel.add(yearLabel);
        
        // set up shelfJLabel
        shelfLabel = new JLabel();
        shelfLabel.setBounds(16, 122, 180, 24);
        shelfLabel.setText("Shelf:");
        editJPanel.add(shelfLabel);
        
        // set up titleJTextField
        titleTextField = new JTextField();
        titleTextField.setBounds(64, 32, 124, 24);
        titleTextField.setHorizontalAlignment(JTextField.LEFT);
        editJPanel.add(titleTextField);
        
        // set up authorJTextField
        authorTextField = new JTextField();
        authorTextField.setBounds(64, 62, 124, 24);
        authorTextField.setHorizontalAlignment(JTextField.LEFT);
        editJPanel.add(authorTextField);
        
        // set up authorJTextField
        yearTextField = new JTextField();
        yearTextField.setBounds(64, 92, 124, 24);
        yearTextField.setHorizontalAlignment(JTextField.LEFT);
        editJPanel.add(yearTextField);
        
        // set up authorJTextField
        shelfTextField = new JTextField();
        shelfTextField.setBounds(64, 122, 124, 24);
        shelfTextField.setHorizontalAlignment(JTextField.LEFT);
        editJPanel.add(shelfTextField);
        
        // set up CreateAccountButton
        saveJButton = new JButton();
        saveJButton.setBounds(64, 152, 123, 24);
        saveJButton.setText("Save");
        editJPanel.add(saveJButton);
        saveJButton.addActionListener(
    		new ActionListener() {
                // event handler called when CreateAccountJButton
                // is clicked
                public void actionPerformed(ActionEvent event) {
                	if(titleTextField.getText().toString().equals("") || authorTextField.getText().toString().equals("") || yearTextField.getText().toString().equals("") || shelfTextField.getText().toString().equals("")) {
            			resultJTextArea.setText("");
            			resultJTextArea.setText("Input error, please check your input");
            		}else {
	                	if(statusEdit == 0) {
	                		addJButtonActionPerformed(event);	                		
	                	}else {
	                		updateJButtonActionPerformed(event);
	                	}
            			
            		}
                }
     
            }
         
        ); // end call to addActionListener
        
        String[] idListTemp = new String[100];
        int n=0;
        
        try {
        	int i=0;
        	DbBooks bookslist = new DbBooks();
        	
			bookslist.selectAllBooks();
			
			while(bookslist.getResult().next()){
				idListTemp[i] = bookslist.getResult().getString(1);
				System.out.println(idListTemp[i]);
				i++;
				n++;
			}
			
        }catch(Exception eDb) {
        	System.out.println(eDb);
        }
        
        idList = new String[n];
        
        for(int i=0;i<n;i++) {
        	idList[i] = idListTemp[i];
        }
        
        idJComboBox = new JComboBox(idList);
        idJComboBox.setEditable(false);
        idJComboBox.setBounds(16, 182, 44, 24);
        editJPanel.add(idJComboBox);
        
        if(idList.length != 0) {
        	idJComboBox.setSelectedItem(idList[0]);
        	id = Integer.parseInt(idList[0]);
        }
        
        
        ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	try {
            		id = Integer.parseInt(idJComboBox.getSelectedItem().toString());//get the selected item
            		selectedIndexId = idJComboBox.getSelectedIndex();
            	}catch(Exception eCast){
            		System.out.println(eCast);
            	}

                //System.out.println(id);
            }
        };
        
        idJComboBox.addActionListener(cbActionListener);
        
        // set up CreateAccountButton
        editJButton = new JButton();
        editJButton.setBounds(64, 182, 56, 24);
        editJButton.setText("Edit");
        editJPanel.add(editJButton);
        editJButton.addActionListener(
    		new ActionListener() {
                // event handler called when CreateAccountJButton
                // is clicked
                public void actionPerformed(ActionEvent event) {
                	editJButtonActionPerformed(event);
                }
     
            }
         
        ); // end call to addActionListener
        
        // set up CreateAccountButton
        deleteJButton = new JButton();
        deleteJButton.setBounds(125, 182, 62, 24);
        deleteJButton.setText("Del");
        editJPanel.add(deleteJButton);
        deleteJButton.addActionListener(
    		new ActionListener() {
                // event handler called when CreateAccountJButton
                // is clicked
                public void actionPerformed(ActionEvent event) {
                	deleteJButtonActionPerformed(event);
                }
     
            }
         
        ); // end call to addActionListener
        
        // set properties of application's window
        setTitle("Books"); // set title bar string
        setSize(600, 320); // set window size
        setVisible(true); // display window
        
        displayAllBooks();
        
	}

	public void displayAllBooks() {
		
		int amount=0;
		resultJTextArea.setText("");
		resultJTextArea.setText("Loading books database...");
		
		try {
			DbBooks bookslist = new DbBooks();
			bookslist.selectAllBooks();
			
			resultJTextArea.setText("");
			
			while(bookslist.getResult().next()){
				String title = bookslist.getResult().getString(2);
				String author = bookslist.getResult().getString(3);
				String year = bookslist.getResult().getString(4);
				String shelf = bookslist.getResult().getString(5);
				
				resultJTextArea.append("Title: "+ title + "\n");
				resultJTextArea.append("Author: "+ author + "\n");
				resultJTextArea.append("Year: "+ year + "\n");
				resultJTextArea.append("Shelf: "+ shelf + "\n");
				resultJTextArea.append("==================================\n");
				
				amount++;
			}
			
			if(amount == 0) {
				resultJTextArea.setText("Books database is empty.");
			}
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	private void searchJButtonActionPerformed(ActionEvent event) {
		
		int amount=0;
		resultJTextArea.setText("");
		resultJTextArea.setText("Searching for result...");
		
		try {
			DbBooks bookslist = new DbBooks();
			bookslist.searchBooks(searchTextField.getText().toString());
			
			resultJTextArea.setText("");
			
			while(bookslist.getResult().next()){
				String title = bookslist.getResult().getString(2);
				String author = bookslist.getResult().getString(3);
				String year = bookslist.getResult().getString(4);
				String shelf = bookslist.getResult().getString(5);
				
				resultJTextArea.append("Title: "+ title + "\n");
				resultJTextArea.append("Author: "+ author + "\n");
				resultJTextArea.append("Year: "+ year + "\n");
				resultJTextArea.append("Shelf: "+ shelf + "\n");
				resultJTextArea.append("==================================\n");
				
				amount++;
			}
			
			if(amount == 0) {
				resultJTextArea.setText("Cannot find any result.");
			}
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
 
    }
	
	public void addJButtonActionPerformed(ActionEvent event) {
		
		try {
			String title = titleTextField.getText().toString();
			String author = authorTextField.getText().toString();
			String year = yearTextField.getText().toString();
			String shelf = shelfTextField.getText().toString();
			
			DbBooks bookslist = new DbBooks();
			bookslist.insertBooks(title, author, year, shelf);
			
			displayAllBooks();
			
			idJComboBox.addItem(bookslist.lastID);
			
			titleTextField.setText("");
			authorTextField.setText("");
			yearTextField.setText("");
			shelfTextField.setText("");
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	public void editJButtonActionPerformed(ActionEvent event) {
		
		try {
			
			DbBooks bookslist = new DbBooks();
        	
			bookslist.selectBooksById(id);
			
			while(bookslist.getResult().next()){
				String title = bookslist.getResult().getString(2);
				String author = bookslist.getResult().getString(3);
				String year = bookslist.getResult().getString(4);
				String shelf = bookslist.getResult().getString(5);
				
				titleTextField.setText(title);
				authorTextField.setText(author);
				yearTextField.setText(year);
				shelfTextField.setText(shelf);
			}
			
			statusEdit = 1;
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public void updateJButtonActionPerformed(ActionEvent event) {
		
		try {
			String title = titleTextField.getText().toString();
			String author = authorTextField.getText().toString();
			String year = yearTextField.getText().toString();
			String shelf = shelfTextField.getText().toString();
			
			DbBooks bookslist = new DbBooks();
        	
			bookslist.updateBooks(id, title, author, year, shelf);
			
			statusEdit = 0;
			
			displayAllBooks();
			
			titleTextField.setText("");
			authorTextField.setText("");
			yearTextField.setText("");
			shelfTextField.setText("");
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public void deleteJButtonActionPerformed(ActionEvent event) {
		
		try {
			//System.out.println("id: "+id);
			//System.out.println("index: "+selectedIndexId);
			
			DbBooks bookslist = new DbBooks();
        				
			bookslist.deleteBooks(id);
			idJComboBox.removeItemAt(selectedIndexId);
			
			displayAllBooks();			
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
}
