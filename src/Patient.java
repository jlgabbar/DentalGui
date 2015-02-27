import java.io.Serializable;


public class Patient implements Serializable{
	
	String name;
	String methodOfPayment;
	String total;
	Patient p [];
	
	public Patient(String n, String payment, String t)
	{
		name = n;
		methodOfPayment = payment;
		total = t;
	}
	public void setName(String n)
	{
		name = n;
	}
	public void setPayment(String payment)
	{
		methodOfPayment = payment;
	}
	public void setTotal(String t)
	{
		total = t;
	}
	public String getName()
	{
		return name;
	}
	public String getPayment()
	{
		return methodOfPayment;
	}
	public String getTotal()
	{
		return total;
	}
	public String toString()
	{
		return "Name: " + name +"\nInsurance: " + methodOfPayment +"\nTotal: "+ total;
	}
}
//NOTES
// file inputStream and object input stream up before it before the try -- exactly like number 11 on test
//Patient P = (Patient)infile.readObject();
//s.o.p(p);
// when you click exit button- this is whats supposed to be executed-- number 11 on test. -- within action performed of the exit button!!

// save button- write patient data to file- dont open or close file
// you need to open file for output inthe gui constructor
// when you launch the program
// only time you need to open it
// file stream stuff at top before gui constructor
// file output stream s = null
// object output stream ja= null
// s = new file output stream(Patient.dat)
// ja  = new object output

// dont exit until exit button is clicked
// when you click save- execute the the write object method
// do not close the file
/* when exit button is excuted file.close needs to be at top to close before you can read from it
dont need an array list-- just an array
*/
