import java.awt.*;
import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.String;

class Process {
	String name; 
	int x, y;

	public Process(String name, int x, int y){
		this.x = x;
		this.y = y;
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public int getMemory(){
		return getY() - getX();
	}

	@Override
	public String toString(){
		return "name: " + name + "\nmemory size: " + getMemory() + "\n from " + getX() + " to " + getY();
	}
}

public class Assignment_2 {
	private final int MEM_SIZE = 20;

	int free_mem = MEM_SIZE;
	JLabel lab_mem = new JLabel("Free Space: 20");

	JFrame f = new JFrame();

	JSplitPane splitPane = new JSplitPane();
	JButton[] memory = new JButton[MEM_SIZE];

	ArrayList<Process> arr = new ArrayList<Process>();

	JPanel topPanel = new JPanel();
	JPanel bottomPanel = new JPanel();

	private void init(){
		for (int i = 0; i < memory.length; i++) {
			memory[i] = new JButton("");
			memory[i].setBackground(Color.white);
			memory[i].setOpaque(true);
			memory[i].setBorderPainted(true);
			memory[i].setBorder(BorderFactory.createEmptyBorder());
			memory[i].setPreferredSize(new Dimension(200, 50));
			memory[i].setEnabled(false);
			memory[i].putClientProperty("alloc", false);
		}
	}
	private void update(){
		//reset all colors, label, border and enabled status 
		for (int i = 0; i < memory.length; i++) {
			memory[i].setText(""+i);
			memory[i].setBackground(Color.white);
			memory[i].setBorder(BorderFactory.createEmptyBorder());
			memory[i].setEnabled(false);
			memory[i].putClientProperty("alloc", false);
		}

		//show the current processes in memory
		for (Process p : arr){
			int start = p.getX();
			int end = p.getY();

			memory[start].setBackground(Color.red);
			memory[start].setEnabled(true);
			memory[start].setText(""+p.getName());
			memory[start].putClientProperty("alloc", true);

			for ( int i = start+1; i < end+1; i++ ){
				memory[i].setText("");
				memory[i].setBorder(BorderFactory.createMatteBorder(0,5,0,5, Color.red));
				memory[i].putClientProperty("alloc", true);
			}
			memory[end].setBorder(BorderFactory.createMatteBorder(0,5,5,5, Color.red));
		}
	}

	private Process getLastProcess(){
		return arr.get(arr.size()-1);
	}
	private void freeMem(int x){
		free_mem += x;
		lab_mem.setText("Free Space: " + free_mem +"   ");
	}

	Assignment_2(){

		//top panel stuff
		//-----------------------------------------------------
		
		//inilize all jbuttons
		init();
		topPanel.setLayout(new GridLayout(MEM_SIZE, 1));
		

	    //add action listener to all buttons
    	for (int i = 0; i < memory.length; i++){

	    	//onclick remove the clicked process from memory
        	memory[i].addActionListener(e -> {
        		JButton button = (JButton) e.getSource();

		    	String title = button.getText();

		    	for (int j = 0; j < arr.size(); j++ ) {
		    		if ( arr.get(j).getName().equals(title) ){
		    			freeMem(arr.get(j).getMemory()+1);
		    			arr.remove(j);
		    			update();
		    		}
		    	}

			}); //end actionlistener
    	} //end for loop

    	//add all buttons to top panel
		for (JButton b: memory) {
			topPanel.add(b);
		}

		//bottom panel stuff
		//-----------------------------------------------------

		bottomPanel.setLayout(new BorderLayout(50, 10));
		
		JPanel innerPanel = new JPanel();

		JLabel lab_err = new JLabel("");

		JTextField tf_1 = new JTextField("enter process name", 20);
		JTextField tf_2 = new JTextField("enter memory size", 20);
		JButton add = new JButton("add process to memory");
		JButton compact = new JButton("compact holes in memory");


		// add button button listener
		add.addActionListener (e -> {
			lab_err.setText("");

    		String temp_name = tf_1.getText();
    		String temp_int_s = tf_2.getText();
    		int temp_int = 0;
    		try{
    			temp_int = Integer.parseInt(temp_int_s);
    		} catch (Exception ex){
    			lab_err.setText("memory size must be an Integer value");
    			return;
    		}

			if (temp_int > free_mem){
				lab_err.setText("OUT OF MEMORY");
				return;
			}

    		Process p;

    		//check if temp_name is already a named process
	    	for (int j = 0; j < arr.size(); j++ ) {
	    		if ( arr.get(j).getName().equals(temp_name) ){
	    			lab_err.setText("<html>There is already a process with that name <br>in memory please pick a different name");
	    			return;
	    		}
	    	}
    		

    		//add logic add in empty spaces
    		if (arr.size() == 0){
    			p = new Process(temp_name, 0, temp_int-1);
    		} else {

    			ArrayList<Integer> a = new ArrayList<Integer>();

    			//get all free spaces
    			for (int i = 0; i < memory.length; i++ ) {
    				if (! ((boolean) memory[i].getClientProperty("alloc"))){
    					a.add(i);
    				}
    			}

    			// a.add(a.get(a.size()-1)); //hack

    			int first = 0, last = 0;
    			// System.out.println(a);
				for (int i = 0; temp_int < a.size()+1; i++ ) { //issues here
					first = a.get(i);
					last = a.get(i+temp_int-1);
					// System.out.println(first +"  "+last);
					if ((last - first) == (temp_int-1)) { //hole is big enough
						break; 
					}
					if (last == a.get(a.size()-1)){ // no hole big enough
							lab_err.setText("No hole advailable for memory");
							return;
					}
				}

    			p = new Process(temp_name, first, last);
    			
    		}// end else

    		freeMem(0-temp_int);
    		arr.add(p);
    		update();
		}); // end add button listener

		compact.addActionListener (e -> {
			if (arr.isEmpty()){
				lab_err.setText("nothing to compact!");
				return;
			}
			ArrayList<Process> temp_arr = (ArrayList)arr.clone();
			arr.clear();

			for (Process p : temp_arr) {
				Process x;
				if (arr.isEmpty()){
					x = new Process(p.getName(), 0, p.getMemory());
				} else {
					int a = getLastProcess().getY(); //gets end of last process in memory
				 	x = new Process(p.getName(), a+1, a + p.getMemory()+1);
				}
				arr.add(x);
			}
			update();
		});

		bottomPanel.add(lab_err, BorderLayout.NORTH);
		
		innerPanel.add(lab_mem, BorderLayout.NORTH);
		innerPanel.add(tf_1);
		innerPanel.add(tf_2);
		innerPanel.add(add);
		innerPanel.add(compact);

		bottomPanel.add(innerPanel, BorderLayout.CENTER);

		//frame stuff
		//-----------------------------------------------------

		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setDividerLocation(200);
		splitPane.setTopComponent(topPanel);
		splitPane.setBottomComponent(bottomPanel);

		f.setLayout(new GridLayout());
		f.getContentPane().add(splitPane);

		f.setSize(500,MEM_SIZE * 40);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Assignment_2();
	}
}