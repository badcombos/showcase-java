import java.awt.*;
import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.String;

class Process implements Comparable<Process>{
	String name; 
	int priority;  
	int cpu_time; 

	public Process(String name, int priority, int cpu_time){
		this.name = name;
		this.priority = priority;
		this.cpu_time = cpu_time;
	}
	public int getPriority(){
		return this.priority;
	}
	public String getName(){
		return this.name;
	}
	public int getBurst(){
		return this.cpu_time;
	}
	public void setBurst(int i){
		this.cpu_time = i;
	}
	@Override
	public String toString(){
		return "name: " + name + "\npriority: " + priority + "\nCPU burst time: "+ cpu_time;
	}
	public String toMultilineString(){
		return "<html>" + this.toString().replaceAll("\n", "<br>");
	}
	@Override
	public int compareTo(Process p){
		if(this.priority == p.priority)    
	 		return 0;    
	 	else if(this.priority < p.priority)    
	 		return 1;    
	 	else    
	 		return -1;   
	}
}

public class Assignment_1 {

	Process p1 = new Process("process 1", 5, 50);
	Process p2 = new Process("process 2", 3, 50);
	Process p3 = new Process("process 3", 1, 50);
	Process p4 = new Process("process 4", 2, 50);

	ArrayList<Process> a_new = new ArrayList<Process>(Arrays.asList( p1, p2, p3, p4 ));
	ArrayList<Process> a_pro = new ArrayList<Process>(a_new);
	ArrayList<Process> a_term = new ArrayList<Process>();
	ArrayList<Process> a_block = new ArrayList<Process>();
	Process running = null;
	
	JFrame f;
	JLabel[] arr_labs = new JLabel[20];

	private void updateNewQueue(){
		arr_labs[5].setText(returnString(a_new, 0));
		arr_labs[10].setText(returnString(a_new, 1));
		arr_labs[15].setText(returnString(a_new, 2));
	}
	private void updateReadyQueue(){
		//update ready queue
		arr_labs[6].setText(returnString(a_pro, 0));
	    arr_labs[11].setText(returnString(a_pro, 1));
	    arr_labs[16].setText(returnString(a_pro, 2));
	}
	private void removeRunning(){
		arr_labs[7].setText("");
		arr_labs[12].setText("");
	}
	private void updateRunning(){
		arr_labs[7].setText(running.toMultilineString());
	}
	private void updateBlockQueue(){
		//update term queue
		arr_labs[8].setText(returnString(a_block, 0));
	  	arr_labs[13].setText(returnString(a_block, 1));
	   	arr_labs[18].setText(returnString(a_block, 2));
	}
	private void updateTermQueue(){
		//update term queue
		arr_labs[9].setText(returnString(a_term, 0));
	  	arr_labs[14].setText(returnString(a_term, 1));
	   	arr_labs[19].setText(returnString(a_term, 2));
	}
	private void popReady(){
		//move process from ready to running
		running = a_pro.get(0);
		a_pro.remove(0);
	}
				
	private String returnString(ArrayList<Process> arr, int index){
		// System.out.println(arr.size()+"    "+index+"/n");
		if (index >= 0 && index < arr.size()) {
			return arr.get(index).toMultilineString();
		}
		return "";
	}

	private void initLabs(){
		for (int i = 0; i < arr_labs.length; i++) {
			arr_labs[i] = new JLabel("");
		}
	}

	Assignment_1(){
		initLabs();
		f=new JFrame();
		
		JButton b1=new JButton("initilize new");
		JButton b2=new JButton("New to running");
		JButton b3=new JButton("compute");
		JButton b4=new JButton("time slice");
		JButton b5=new JButton("context switch");

		arr_labs[0].setText("new");
		arr_labs[1].setText("ready queue");
		arr_labs[2].setText("computing");
		arr_labs[3].setText("blocked");
		arr_labs[4].setText("terminated");
		
		b1.addActionListener(e -> {
		    updateNewQueue();
		});
		b2.addActionListener(e -> {
			Collections.sort(a_pro);
			a_new = new ArrayList<Process>(); // clear a_new

			updateNewQueue();
		    updateReadyQueue();
		});
		
		//compute
		b3.addActionListener(e -> {
			if(running == null) {
				//no more processes in ready queue
				if (a_pro.size() == 0){
					if (a_block.size() == 0) {
						System.out.println("no more processes left to process");
						System.exit(0);
					}
					else {
						System.out.println("please wait for context switch to finish");
						return;
					}
				}
				popReady();
			}

			//mormal compute
			running.setBurst(running.getBurst() -5);
			updateRunning();
			
			
			//done computing
			if (running.getBurst() == 0){
				a_term.add(running);
				running = null;

				updateTermQueue();
		    	removeRunning();
			}
			
			updateReadyQueue();
		});

		//time slice
		b4.addActionListener(e -> {
		    if(running == null) {
		    	System.out.println("No process to time slice!");
		    	return;
			}
			a_pro.add(running);
			running = null;

			// //current time slice process is not the only one in ready queue
			// if (a_pro.size() > 1){
				popReady();
				Collections.sort(a_pro); //resort array
			//}
			updateRunning();
		    updateReadyQueue();
		});

		//block list
		b5.addActionListener(e -> {
			if (a_block.size() == 0){
				if(running == null) {
		    		System.out.println("No process to block!");
		    		return;
				}
				a_block.add(running);
				running = null;
				// Collections.sort(a_pro); //resort array 

				removeRunning();
				updateBlockQueue();
			    updateReadyQueue();				
			}
			else {
				a_pro.add(a_block.get(0));
				a_block = new ArrayList<Process>(); // clear a_term
				updateBlockQueue();
				updateReadyQueue();	
			}
		});
			
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		
		//add all labels
		for (JLabel l: arr_labs) {
			f.add(l);
		}

		f.setLayout(new GridLayout(5,5));
		f.setSize(1000,1000);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Assignment_1();
	}
}
