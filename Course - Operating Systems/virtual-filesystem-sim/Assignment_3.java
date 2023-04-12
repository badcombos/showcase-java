import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.String;

import java.util.Enumeration;
import java.util.Random;

class CustomTreeNode extends DefaultMutableTreeNode {
	static final int FOLDER = 0;
	static final int FILE = 1;

	String data;
	int type;
	int ID; 
	CustomTreeNode(String name, int ID, String data){
		super(name);
		this.ID = ID;
		this.data = data;
	}
	CustomTreeNode(String name){
		super(name);
		this.type = -1;
		this.data = null;
	}
	CustomTreeNode(String name, int ID){
		this(name);
		this.ID = ID; 
	}
	public String getType(){
		switch(this.type){
			case FOLDER:
				return "FOLDER";
			case FILE:
				return "FILE";
		}
		return "NULL";
	}
	public String getData(){
		return this.data;
	}
	public int getID(){
		return ID;
	}
	public String getCustomPath(){
		TreePath tp = new TreePath(this.getPath());
		String path = tp.toString().replaceAll("\\]| |\\[|", "").replaceAll(",", "/");
		return path;
	}
}

public class Assignment_3 {
	private final int DISKS = 3;
	private final int PANEL_SIZE = 600;

	int ID_COUNT = 0;

	public static int randomInt(int min, int max){ //min and max are inclusive
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}

	private JTree createTree(){
		CustomTreeNode root = new CustomTreeNode("Root");

		JTree tree = new JTree(root);
		tree.setRootVisible(true);
		tree.setEditable(true);

		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				CustomTreeNode selectedNode = getSelectedNodes(tree);
				
				// if (selectedNode != null){ //to prevent triggering when removing nodes
				// 	System.out.println(e.getPath().toString());
				// 	System.out.println(selectedNode.getUserObject().toString());
				// }
			}
		});

		return tree;
	}
	private void expandTree(JTree tree){
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
	}
	private JScrollPane treeToPane(JTree tree){
		JScrollPane p = new JScrollPane(tree);
		p.setSize(PANEL_SIZE/2, 100);
		return p;
	}

	private CustomTreeNode createNode(String name, String data){
		CustomTreeNode node = new CustomTreeNode(name, ID_COUNT, data);
		return node;
	}
	private CustomTreeNode getRoot(DefaultTreeModel model){
		return (CustomTreeNode) model.getRoot();
	}
	private DefaultTreeModel getTreeModel(JTree tree){
		return (DefaultTreeModel) tree.getModel();
	}
	private CustomTreeNode getSelectedNodes(JTree tree){
		return (CustomTreeNode) tree.getLastSelectedPathComponent();
	}
	private void removeSelectedNodes(JTree tree){
		DefaultTreeModel model = getTreeModel(tree);
		TreePath[] paths = tree.getSelectionPaths();
		if (paths != null) {
			for (TreePath tp : paths) {
				// System.out.println(tp);
				CustomTreeNode node = (CustomTreeNode) tp.getLastPathComponent();
				if (node.getParent() != null) {
					model.removeNodeFromParent(node);
				}
			}
		}
		else System.out.println("NOTHING SELECTED");
	}
	private void addNode(DefaultTreeModel tree, CustomTreeNode newNode, CustomTreeNode selectedNode){
		tree.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
	}
	private TreePath Nodefind(CustomTreeNode root, int ID) {
		@SuppressWarnings("unchecked")
		Enumeration e = root.depthFirstEnumeration();
		while (e.hasMoreElements()) {
			CustomTreeNode node = (CustomTreeNode)e.nextElement();
			if (node.getID() == ID) {
				return new TreePath(node.getPath());
			}
		}
		return null;
	}

	private void addtoBoth(DefaultTreeModel left_model, DefaultTreeModel right_model, String node_name, String node_data, CustomTreeNode selectedNode){
		ID_COUNT++;

		// add left
		addNode(left_model, new CustomTreeNode(node_name, ID_COUNT, node_data), selectedNode);

		//add right
		CustomTreeNode right_root = getRoot(right_model);
		addNode(right_model, new CustomTreeNode(node_name, ID_COUNT, node_data), (CustomTreeNode)right_root.getChildAt(randomInt(0,DISKS-1)));
	}

	private void setup(JTree leftTree, JTree rightTree){
		DefaultTreeModel left_model = getTreeModel(leftTree);
		DefaultTreeModel right_model = getTreeModel(rightTree);
		
		CustomTreeNode left_root = getRoot(left_model);
		CustomTreeNode right_root = getRoot(right_model);

		for (int i = 0; i < DISKS; i++) {
			// right_root.add(new CustomTreeNode("Disk "+i));
			addNode(right_model, new CustomTreeNode("Disk "+i), right_root);
		}

		addtoBoth(left_model, right_model, "Vegetables", "", left_root);
		addtoBoth(left_model, right_model, "Lettuce", "This is a Lettuce", (CustomTreeNode)left_root.getChildAt(0));
		addtoBoth(left_model, right_model, "Carrot", "This is a Carrot", (CustomTreeNode)left_root.getChildAt(0));
		addtoBoth(left_model, right_model, "Tomato", "This is a Tomato", (CustomTreeNode)left_root.getChildAt(0));
		addtoBoth(left_model, right_model, "Potato", "This is a Potato", (CustomTreeNode)left_root.getChildAt(0));

		addtoBoth(left_model, right_model, "Fruits", "", left_root);
		addtoBoth(left_model, right_model, "Banana", "This is a Banana", (CustomTreeNode)left_root.getChildAt(1));
		addtoBoth(left_model, right_model, "Mango", "This is a Mango", (CustomTreeNode)left_root.getChildAt(1));
		addtoBoth(left_model, right_model, "Apple", "This is a Apple", (CustomTreeNode)left_root.getChildAt(1));
		addtoBoth(left_model, right_model, "Grapes", "This is a Grapes", (CustomTreeNode)left_root.getChildAt(1));
		addtoBoth(left_model, right_model, "Orange", "This is a Orange", (CustomTreeNode)left_root.getChildAt(1));
			
		expandTree(leftTree);
		expandTree(rightTree);
	}

	Assignment_3(){

		JFrame f = new JFrame();

		JSplitPane topPanel = new JSplitPane();
		JPanel bottomPanel = new JPanel();

		JTree leftTree = createTree();
		JTree rightTree = createTree();

		//top panel stuff
		//-----------------------------------------------------
		
		JPanel leftPanel = new JPanel();		
		JPanel rightPanel = new JPanel();

		setup(leftTree, rightTree);

		leftPanel.setLayout(new BorderLayout(50, 10));
		leftPanel.add(treeToPane(leftTree), BorderLayout.CENTER);

		rightPanel.setLayout(new BorderLayout(50, 10));
		rightPanel.add(treeToPane(rightTree), BorderLayout.CENTER);

		topPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		topPanel.setDividerLocation(PANEL_SIZE/2);
		topPanel.setLeftComponent(leftPanel);
		topPanel.setRightComponent(rightPanel);

		//bottom panel stuff
		//-----------------------------------------------------

		bottomPanel.setLayout(new BorderLayout(50, 10));
		
		JPanel innerPanel = new JPanel();

		JLabel text_label = new JLabel("test");

		JButton add_button = new JButton("add node");
		JButton delete_button = new JButton("delete node");
		JButton show_button = new JButton("show data in file");

		add_button.addActionListener ( e -> { 
			text_label.setText("add button pressed");

			DefaultTreeModel left_model = getTreeModel(leftTree);
			DefaultTreeModel right_model = getTreeModel(rightTree);
			CustomTreeNode left_root = getRoot(left_model);
			CustomTreeNode right_root = getRoot(right_model);

			CustomTreeNode selectedNode = getSelectedNodes(leftTree);
			String input_name = JOptionPane.showInputDialog("Enter Name for New Node:");

			if (selectedNode == null )
				selectedNode = left_root;
			if (input_name == null || input_name.equals("") ){
				text_label.setText("node name cannot be blank");
				return;
			}

			addtoBoth(left_model, right_model, input_name, "", selectedNode);
			expandTree(leftTree);
			expandTree(rightTree);
		});
		delete_button.addActionListener ( e -> { 
			text_label.setText("delete button pressed");
			CustomTreeNode selectedNode = getSelectedNodes(leftTree);
			int IDtoRemove = selectedNode.getID();

			removeSelectedNodes(leftTree);

			DefaultTreeModel right_model = getTreeModel(rightTree);
			CustomTreeNode right_root = getRoot(right_model);
			
			TreePath tp = Nodefind(right_root, IDtoRemove);
			rightTree.setSelectionPath(tp);
			// System.out.println(tp);
			
			removeSelectedNodes(rightTree);

		});
		show_button.addActionListener ( e -> { 
			text_label.setText("print button pressed");

			CustomTreeNode selectedNode = getSelectedNodes(leftTree);
			String name = selectedNode.getUserObject().toString();
			JOptionPane.showMessageDialog(null, "name:" + name 
														// + "\nfile type:" + selectedNode.getType() 
														+ "\ndata:" + selectedNode.getData()
														+ "\npath:" + selectedNode.getCustomPath()
														+ "\nID: " + selectedNode.getID()
													);
		});

		innerPanel.add(add_button);
		innerPanel.add(delete_button);
		innerPanel.add(show_button);

		bottomPanel.add(text_label, BorderLayout.NORTH);

		bottomPanel.add(innerPanel, BorderLayout.CENTER);

		//frame stuff
		//-----------------------------------------------------

		JSplitPane splitPane = new JSplitPane();

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(2*(PANEL_SIZE/3));
		splitPane.setTopComponent(topPanel);
		splitPane.setBottomComponent(bottomPanel);

		f.setLayout(new GridLayout());
		f.getContentPane().add(splitPane);

		f.setSize(PANEL_SIZE, PANEL_SIZE);
		f.setTitle("Assignment 3");
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Assignment_3();
	}
	// public static void main(String[] args) {
	// 	SwingUtilities.invokeLater(new Runnable() {
	// 		@Override
	// 		public void run() {
	// 			new Assignment_3();
	// 		}
	// 	});
	// }
}