/**
This class uses a BinarySearchTree to read in data from a CSV file as well as being able to find details for a datetime that the user enters
@author Patrick Marais - modified by @author Mahmoodah Jaffer
@author SJ (via https://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/) - modified by @author Mahmoodah Jaffer
@author Mahmoodah Jaffer - JFFMAH001
@since 28 February 2019
*/

public class BinarySearchTree {

/**
BinarySearchTree class takes in given datetime, power and voltage and stores it in a Binary Tree where the data can later be searched for/retrieved 
using the functions of the BinarySearchTree 
*/
		
		public static  BinaryTreeNode root;		
		public static int opCount = 0;
		public static int insCount = 0;

   	/**
	Method BinarySearchTree sets the root of the Binary Search Tree to null
	*/
	public BinarySearchTree(){
		this.root = null;	
    }

	/**
	Method find class returns object of type Powerstation if the String d is the the same as a datetime node in the BinarySearchTree
	@param d String
	@return PowerStation
	*/
	public PowerStation find ( String d )
	{

		if (root == null)
			return null;
		else
			return find (d, root);
	}

	/**
	Method find returns object of type Powerstation if the String d is the the same as a datetime node in the BinarySearchTree
	@param d String
	@param node BinaryTreeNode
	@return PowerStation
	*/
	public PowerStation find ( String d, BinaryTreeNode node )
	{	

		if (d.compareTo (node.data.getDateTime()) == 0){
			opCount++;
			return node.data;
		}
		else if (d.compareTo (node.data.getDateTime())< 0){
			opCount++;
			return (node.left == null) ? null : find (d, node.left);
		}
		else{
			return (node.right == null) ? null : find (d, node.right);
		}
	}

 	/**
	Method insert takes in  object of type Powerstation and stores it in a node in the BinarySearchTree
	@param d Powerstation
	*/
	public void insert ( PowerStation d )
 	{

 		if (root == null)
 			root = new BinaryTreeNode (d, null, null);
 		else
 			insert (d, root);
 	}

 	/**
	Method insert takes in  object of type Powerstation and stores it in a node in the BinarySearchTree
	@param d Powerstation
	*/
 	public void insert ( PowerStation d, BinaryTreeNode node )
 	{

 		if ((d.getDateTime()).compareTo (node.data.getDateTime()) <= 0)
 		{
 			insCount++;
 			if (node.left == null)
				 node.left = new BinaryTreeNode (d, null, null);
 			else
 				insert (d, node.left);
 		}
 		else
 		{
 			insCount++;
 			if (node.right == null)
 				node.right = new BinaryTreeNode (d, null, null);
			 else
 			insert (d, node.right);
 		}
 	}

 	/**
	Method display calls on display function in the BinarySearchTree class
	*/
 	public void display(){

 		display(root);
 	}

 	/**
	Method display prints left and right subtree until it gets to the root node
	@param root BinaryTreeNode
	*/
	public void display(BinaryTreeNode root){

		if(root!=null){
			display(root.left);
			System.out.println(root.data);
			display(root.right);
		}
	}

	/**
	Method Counter returns value of opCount that is in the class BinaySearchTree
	@return integer temp that is the temporary placekeeper for opCount
	*/
	public int opCounter(){

		int temp = opCount;
		opCount = 0;
		return temp;
	}

	/**
	Method insCounter returns value of insCount that is in the class BinaySearchTree
	@return integer temp that is the temporary placekeeper for insCount
	*/
	public int insCounter(){

		int tempIns = insCount;
		insCount = 0;
		return tempIns;
	}

/**
BinaryTreeNode is an innerclass of the class BinarySearchTree that creates each node in the tree and allows it store type PowerStation
*/
class BinaryTreeNode
{

	PowerStation data;
	BinaryTreeNode left;
	BinaryTreeNode right;
	
	/**
	BinaryTreeNode Method takes in an object of type PowerStation and will store it as the value of a node in the BinaryTree
	@param d PowerStation
	@param l BinaryTreeNode
	@param r BinaryTreeNode
	*/
	public BinaryTreeNode ( PowerStation d, BinaryTreeNode l,BinaryTreeNode r )
	{

		data = d;
		left = l;
		right = r;
	}
}

}