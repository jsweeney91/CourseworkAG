package pack1;


public class EdgeCheck {
	private Node a;
	private Node b;
	private Node c;
	private Node d;
	
	EdgeCheck(Edge a, Edge b){
		this.a = a.getNode1();
		this.b= a.getNode2();
		this.c=b.getNode1();
		this.d=b.getNode2();
	}
	
	public boolean doIntersect(){
		int o1 = getOrientation(this.a,this.b,this.d);
		int o2 = getOrientation(this.a,this.b,this.c);
		int o3 = getOrientation(this.d,this.c,this.a);
		int o4 = getOrientation(this.d,this.c,this.b);
		 
	    if (o1 != o2 && o3 != o4){
	        return true;
	    }
	    
	    return false; 
		
	}
	
	private int getOrientation(Node a, Node b, Node c){
		double val = (b.getY() - a.getY()) * (c.getX() - b.getX())-(b.getX() - a.getX()) * (c.getY() - b.getY());
		if (val == 0){
			return 0;  
		}
		return (val > 0)? 1: 2; 
	}
	
//	public boolean onSegment(Node a,Node b,Node c){
//	    if (b.getX() <= Math.max(a.getX(),c.getX()) && b.getX() >= Math.min(a.getX(), c.getX()) && b.getY() <= Math.max(a.getY(),c.getY())  && b.getY() >= Math.min(a.getY(), c.getY())){
//	           return true;
//	    }
//	    return false;
//	}
}