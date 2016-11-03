package pack1;

import java.awt.geom.Point2D;

public class Edge implements Comparable{
	private Node node1;
	private Node node2;
	private Double weight;
	public static double minDistance=-1;
	public static double maxDistance=0;
	
	Edge(Node n1, Node n2){
		this.node1 = n1;
		this.node2 = n2;
		this.calcWeight();
	}

	
	public void calcWeight(){
		Point2D p1 = new Point2D.Double(this.node1.getX(),this.node1.getY());
		Point2D p2 = new Point2D.Double(this.node2.getX(),this.node2.getY());
		
		this.weight= Point2D.distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		if(this.weight>maxDistance){
			maxDistance = this.weight;
		}
		if(this.weight<minDistance ||minDistance==-1){
			minDistance = this.weight;
		}
	}
	
	public String dbData(){
		return this.weight.toString()+","+this.node1.getId()+","+this.getNode2().getId();
	}
	
	public Node getNode1() {
		return node1;
	}

	public void setNode1(Node node1) {
		this.node1 = node1;
	}

	public Node getNode2() {
		return node2;
	}

	public void setNode2(Node node2) {
		this.node2 = node2;
	}

	public Double getWeight() {
		return weight;
	}
	public int compareTo(Object nextEdge) {
		return Double.compare(this.weight, ((Edge)nextEdge).getWeight());
	}
}
