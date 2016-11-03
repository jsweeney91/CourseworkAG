package pack1;

public class Node{
	public static double maxX=0;
	public static double maxY=0;
	public static double minX=-1;
	public static double minY=-1;
	
	private int id;
	private double x;
	private double y;
	
	private Node nodeA;
	private Node nodeB;
	
	public Node getNodeA() {
		return nodeA;
	}
	public void setNodeA(Node nodeA) {
		this.nodeA = nodeA;
	}
	public Node getNodeB() {
		return nodeB;
	}
	public void setNodeB(Node nodeB) {
		this.nodeB = nodeB;
	}

	
	Node(int id, double x, double y){
		this.id = id;
		this.x = x;
		this.y = y;
		if(this.x>maxX){
			maxX = this.x;
		}
		if(this.y>maxY){
			maxY = this.y;
		}
		if(this.x<minX | minX == -1){
			minX = this.x;
		}
		if(this.y<minY | minY == -1){
			minY = this.y;
		}
	}
	
	//used to set the first node
	Node(int id){
		this.id = -1;
	}
	
	Node(){

	}
		
	public void Swap(Node toSwap,Node n2){
		if(this.nodeB==toSwap){
			this.nodeB = n2;
		}
	}
	
	public String toString(){
		String output = "";
		output+=String.valueOf(this.id)+ ": ";
		output+= Double.toString(this.x)+ ", ";
		output+= Double.toString(this.y);
		return output;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public int getId() {
		return id;
	}
	public void setY(double y) {
		this.y = y;
	}


}
