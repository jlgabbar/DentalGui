import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.*;

public class Dental extends JFrame implements Serializable{
	private JLabel header;
	private JLabel patientName;
	private JLabel cleaningLabel;
	private JLabel cavityLabel;
	private JLabel xrayLabel, total;
	private JTextField nameTextField, totalTextField;
	private JCheckBox cleaningBox;
	private JCheckBox cavityBox;
	private JCheckBox xrayBox;
	private JButton calcButton;
	private JButton save;
	private JButton exit;
	private final int height = 350;
	private final int length = 325;
	private JPanel top, bottom, topmid, mid, lower, abovelower;
	private JRadioButton insurance;
	private JRadioButton medicare;
	private JRadioButton none;
	private ButtonGroup group;
	FileOutputStream patientFile;
	ObjectOutputStream objectFile;
	String payment="";
	Patient p;
	
	

	public Dental()throws IOException {
		setTitle("Dental Payment");
		setSize(length, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.RIGHT, 25,3));
		top = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topmid = new JPanel(new GridLayout(1, 2));
		mid = new JPanel(new GridLayout(3, 2,75,20));
		bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		abovelower = new JPanel(new GridLayout(3,1));
		lower = new JPanel(new GridLayout(3,1));
		header = new JLabel("Dental Payment Form");
		header.setFont(new Font("Arial", Font.PLAIN, 25));
		patientName = new JLabel("Patient Name");
		cleaningLabel = new JLabel("$35", JLabel.RIGHT);
		cavityLabel = new JLabel("$150", JLabel.RIGHT);
		xrayLabel = new JLabel("$85", JLabel.RIGHT);
		total = new JLabel("Total:");
		nameTextField = new JTextField(10);
		totalTextField = new JTextField(10);
		totalTextField.setEditable(false);
		cleaningBox = new JCheckBox("Cleaning");
		cavityBox = new JCheckBox("Cavity Filling");
		xrayBox = new JCheckBox("X-Ray");
		calcButton = new JButton("Calculate");
		save = new JButton("Save");
		exit = new JButton("Exit");
		calcButton.addActionListener(new CalcButtonListener());
		save.addActionListener(new SaveButtonListener());
		exit.addActionListener(new ExitButtonListener());
		insurance = new JRadioButton("Insurance");
		medicare = new JRadioButton("Medicare");
		none = new JRadioButton("None");
		group = new ButtonGroup();
		group.add(insurance);
		group.add(medicare);
		group.add(none);
		insurance.addActionListener(new RadioButtonListener());
		medicare.addActionListener(new RadioButtonListener());
		none.addActionListener(new RadioButtonListener());
		top.add(header);
		add(top);
		topmid.add(patientName);
		topmid.add(nameTextField);
		add(topmid);
		mid.add(cleaningBox);
		mid.add(cleaningLabel);
		mid.add(cavityBox);
		mid.add(cavityLabel);
		mid.add(xrayBox);
		mid.add(xrayLabel);
		add(mid);
		bottom.add(total);
		bottom.add(totalTextField);
		abovelower.add(insurance);
		abovelower.add(medicare);
		abovelower.add(none);
		lower.add(calcButton);
		lower.add(save);
		lower.add(exit);
		add(bottom);
		add(abovelower);
		add(lower);
		setVisible(true);
		patientFile = new FileOutputStream("Patient.dat");
        objectFile = new ObjectOutputStream(patientFile);

	}
	private class RadioButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			boolean insuranceFlag = false;
			boolean medicareFlag = false;
			boolean noneFlag= false;
			if(e.getSource()==insurance)
			{
				insuranceFlag = true;
			}
			else if(e.getSource()==medicare)
			{
				medicareFlag = true;
			}
			else if(e.getSource()==none);
			{
				noneFlag = true;
			}
			if(insuranceFlag ==true)
			{
				payment = "Insurance";
			}
			else if(medicareFlag == true)
			{
				payment= "Medicare";
			}
			else if(noneFlag== true)
			{
				payment = "None";
			}
		}	
	
		
	}
	private class CalcButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			int totalCost = 0;
			if(cleaningBox.isSelected())
			{
				totalCost += 35;
			}
			if(cavityBox.isSelected())
			{
				totalCost +=150;
			}
			if(xrayBox.isSelected())
			{
				totalCost += 85;
			}
			totalTextField.setText(String.valueOf(totalCost));
			

		}
		
	}
	public class SaveButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			
			p= new Patient(nameTextField.getText(),payment, totalTextField.getText());	
		    try{
					objectFile.writeObject(p);
					nameTextField.setText("");
					totalTextField.setText("");
					group.clearSelection();
					cavityBox.setSelected(false);
					cleaningBox.setSelected(false);
					xrayBox.setSelected(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		       }
		    
		
	}
	
	public class ExitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
			{
			
			FileInputStream patientFile;
			ObjectInputStream inputFile = null; 
			try{
				objectFile.close();
				patientFile = new FileInputStream("Patient.dat");
				inputFile = new ObjectInputStream(patientFile);
					while(true)
					{
						p=(Patient)inputFile.readObject();
						System.out.println(p);
					}
				}
				catch(ClassNotFoundException e1)
					{
						System.out.println("Class not found");
					}
				catch(EOFException e1)
					{
						try {
							inputFile.close();
						} catch (IOException e2) {
							
							e2.printStackTrace();
						}
					} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			System.exit(0);
	}
}
}