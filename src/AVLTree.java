/**
This class uses a AVL Tree to read in data from a CSV file as well as being able to find details for a datetime that the user enters
@author Patrick Marais - modified by @author Mahmoodah Jaffer
@author SJ (via https://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/) - modified by @author Mahmoodah Jaffer
@author Mahmoodah Jaffer - JFFMAH001
@since 11 March 2019
*/

/**
AVLTree class takes in given datetime, power and voltage and stores it in an AVL Tree where the data can later be searched for/retrieved 
using the functions of the AVLTree 
*/
public class AVLTree {
		
		public static  BinaryTreeNode root;		
		public static int opCount = 0;
		public static int insCount = 0;
   
   	/**
	Method AVLTree sets the root of the AVL Tree to null
	*/
	public AVLTree(){
		this.root = null;	
    }

   	/**
	Method height returns the height of the tree/subtree 
	@param node BinaryTreeNode
	@return int 
	*/
    public int height ( BinaryTreeNode node )
	{
		if (node != null)
			return node.height;
		return -1;
	}

	/**
	Method balanceFactor returns the difference between the left and right subtree 
	@param node BinaryTreeNode
	@return int 
	*/
	public int balanceFactor ( BinaryTreeNode node )
	{
		return height (node.right) - height (node.left);
	}

	/**
	Method fixHeight changes the height of each subtree after rotation
	@param node BinaryTreeNode
	*/
	public void fixHeight ( BinaryTreeNode node )
	{
		node.height = Math.max (height(node.left), height (node.right)) + 1;
	}

	/**
	Method rotateRight rotates the left subtree to the right
	@param p BinaryTreeNode
	@return BinaryTreeNode
	*/
	public BinaryTreeNode rotateRight( BinaryTreeNode p )
	{
		BinaryTreeNode q = p.left;
		p.left = q.right;
		q.right = p;
		fixHeight (p);
		fixHeight (q);
		return q;
	}

	/**
	Method rotateLeft rotates the right subtree to the left
	@param q BinaryTreeNode
	@return BinaryTreeNode
	*/
	public BinaryTreeNode rotateLeft( BinaryTreeNode q )
	{
		BinaryTreeNode p = q.right;
		q.right = p.left;
		p.left = q;
		fixHeight (q);
		fixHeight (p);
		return p;
	}

	/**
	Method balance balances the AVL tree when given a node
	@param p BinaryTreeNode
	@return BinaryTreeNode
	*/
	public BinaryTreeNode balance ( BinaryTreeNode p )
	{
		fixHeight (p);
		if (balanceFactor (p) == 2)
		{
			if (balanceFactor (p.right) < 0)
				p.right = rotateRight (p.right);
			return rotateLeft (p);
		}
		if (balanceFactor (p) == -2)
		{
			if (balanceFactor (p.left) > 0)
				p.left = rotateLeft (p.left);
			return rotateRight (p);
		}
		return p;
	}


	/**
	Method insert inserts the node into the AVL tree 
	@param d PowerStation
	*/
	public void insert ( PowerStation d )
	{
		root = insert (d, root);
	}

	/**
	Method insert creates a new node if tree is empty, inserts the node if the tree is still balanced or returns the node in the balanced tree
	@param d PowerStation
	@param node BinaryTreeNode
	@return BinaryTreeNode
	*/
	public BinaryTreeNode insert ( PowerStation d, BinaryTreeNode node )
	{
		if (node == null){
			return new BinaryTreeNode (d, null, null , 0 );
		}
		else if ((d.getDateTime()).compareTo (node.data.getDateTime()) <= 0){
			insCount++;
			node.left = insert (d, node.left);
		}
		else{
			insCount++;
			node.right = insert (d, node.right);
		}
		return balance (node);
	}
	
	/**
	Method find returns object of type Powerstation if the String d is the same as a datetime node in the BinarySearchTree
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
	Method display calls on display function in the BinarySearchTree class
	*/
 	public void display(){
 		display(root);
 	}

 	/**
	Method display returns the entire BinarySearchTree
	@param root of type BinaryTreeNode
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
BinaryTreeNode is an innerclass of the class AVLTree that creates each node in the tree and allows it store type PowerStation
*/
class BinaryTreeNode
{

	PowerStation data;
	BinaryTreeNode left;
	BinaryTreeNode right;
	int height;
	/**
	BinaryTreeNode Method takes in an object of type PowerStation and will store it as the value of a node in the AVLTree
	@param d PowerStation
	@param l BinaryTreeNode
	@param r BinaryTreeNode
	*/
	public BinaryTreeNode ( PowerStation d, BinaryTreeNode l,BinaryTreeNode r, int h )
	{

		data = d;
		left = l;
		right = r;
		height = h;
	}
}

}