import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AddressBook extends JFrame {
	//Declare instance variables
	private final int FRAME_WIDTH = 630;
	private final int FRAME_HEIGHT = 550;
	private JPanel panelTop;
	private JPanel panelMid;
	private JPanel panelBot;
	private JButton addContact;
	private JButton saveButton;
	private JLabel name;
	private JLabel address;
	private JLabel phone;
	private JLabel email;
	private JTextField nameField;
	private JTextField addField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextArea contactArea;
	
	/**
	 * Create default constructor class for address book
	 */
	public AddressBook() {
		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.panelMid = new JPanel(new FlowLayout());
		this.panelTop = new JPanel(new GridLayout(4,2));
		this.panelBot = new JPanel();
		this.name = new JLabel("Name:       ");
		this.address = new JLabel("Address:  ");
		this.phone = new JLabel("Phone:     ");
		this.email = new JLabel("Email:       ");
		this.nameField = new JTextField(50);
		this.addField = new JTextField(50);
		this.phoneField = new JTextField(50);
		this.emailField = new JTextField(50);
		this.contactArea = new JTextArea(20,50);
		contactArea.setEditable(false);
		JScrollPane scroller = new JScrollPane(contactArea);
		//Add the different GUI elements to panel
		panelTop.add(name);
		panelTop.add(nameField);
		panelTop.add(address);
		panelTop.add(addField);
		panelTop.add(phone);
		panelTop.add(phoneField);
		panelTop.add(email);
		panelTop.add(emailField);
		//Crates the titled border for the top panel
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Enter New Contact Information");
		panelTop.setBorder(title);
		
		

		
		//Create action listener 
		this.addContact = new JButton("Add Contact");
		addContact.addActionListener(new ActionListener() {
			/**
			 *@param ActionEvent e Takes in an action event e
			 *This method takes in the contact information from the various fields and concatenates them into a single string
			 *then adds that string to the contact area then resets the text fields
			 */
			public void actionPerformed(ActionEvent e) {
				String str = nameField.getText();
				str = str.concat(", ");
				str = str.concat(addField.getText());
				str = str.concat(", ");
				str = str.concat(phoneField.getText());
				str = str.concat(", ");
				str = str.concat(emailField.getText());
				str = str.concat("\n");
				contactArea.append(str);
				nameField.setText("");
				addField.setText("");
				phoneField.setText("");
				emailField.setText("");
				
			}
		});
		
		//Create action listener for save to file class
		this.saveButton = new JButton("Save to File");
		saveButton.addActionListener(new ActionListener() {
			/**
			 * @param ActionEvent e, Takes in an action event e
			 * This method calls the writeContactsToFile method which will write all the values in the contact area to
			 * the specified contacts.txt file
			 */
			public void actionPerformed(ActionEvent e) {
				writeContactsToFile();
				}
		});
		
		panelMid.add(addContact);
		panelMid.add(saveButton);
		//Creates the titled border for the middle panel
		TitledBorder title2;
		title2 = BorderFactory.createTitledBorder("Add New Contact or Save to File");
		panelMid.setBorder(title2);
		
		//Creates the titled border for the top panel
		TitledBorder title3;
		title3 = BorderFactory.createTitledBorder("Saved Contacts");
		panelBot.setBorder(title3);
		panelBot.add(scroller);
		
		//Adds the panels to the frame
		frame.setLayout(new BorderLayout());
		frame.add(panelTop, BorderLayout.NORTH);
		frame.add(panelMid, BorderLayout.CENTER);
		frame.add(panelBot, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Address Book Application");
		frame.setResizable(false);
		frame.setVisible(true);
		//Calls readContactsFromFile to automatically show pre-existing contacts when the address book first opens
		readContactsFromFile();		
	}
	
	/**
	 * @return void
	 * This method will create a scanner for the contacts text file and append them to the contact area
	 */
	public void readContactsFromFile() {
		File readFrom = new File("contacts.txt");
		try {
		Scanner reader = new Scanner(readFrom);
		while (reader.hasNextLine()) {
			String str = reader.nextLine();
			String str2 = str.concat("\n");
			contactArea.append(str2);
			}
		reader.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	/**
	 * @return void
	 * This method will take all the contacts in the contacts text area and create a filewriter object to write the string value
	 * of those contacts to update the contacts.txt text file
	 */
	public void writeContactsToFile() {
		String str = contactArea.getText();
		try {
		FileWriter fw = new FileWriter("contacts.txt");
		fw.write(str);
		fw.close();
		} catch (IOException e) {
			
		}
	}
	
	/**
	 * The main method here will create a new address book object to start running the program
	 * @param args
	 */
	public static void main(String[] args) {
		AddressBook ab = new AddressBook();
	}
}
