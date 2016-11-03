package pack1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {
	public static ArrayList<Edge> edges = new ArrayList<>();
	public static ArrayList<Edge> outputEdges = new ArrayList();
	public static HashMap<Integer, Node> nodes = new HashMap<>();
	public static double totalSize = 0;

	public static void main(String[] args) {
		JFileChooser fc = new JFileChooser();
		String filename = new String();
		int stat = fc.showOpenDialog(null);

		if (stat == JFileChooser.APPROVE_OPTION) {
			filename = fc.getSelectedFile().getAbsolutePath();
			Reader r = new Reader(filename);
			System.out.println(r.readFile());
			getEdges();
			edges.sort(null);
			shortestDistanceCalc();
			outputData();
			test();

//			int counter = 0;
//			while (getCrossovers()) {
//				outputData();
//				counter++;
//				System.out.println(counter);
//			}

			//test();

		}

	}

	public static void outputData() {
		Visualiser vis = new Visualiser(1000, 1000);
		double totalDistance = 0;
		ArrayList<Node> completed = new ArrayList<Node>();
		for (Node n : Reader.coordinates) {
			if (n.getNodeA() != null && !completed.contains(n.getNodeA()) && n.getNodeA().getId() != -1) {
				Edge newEdge = new Edge(n, n.getNodeA());
				vis.addLine(newEdge);
				totalDistance += newEdge.getWeight();
			}
			if (n.getNodeB() != null && !completed.contains(n.getNodeB())) {
				Edge newEdge = new Edge(n, n.getNodeB());
				vis.addLine(newEdge);
				totalDistance += newEdge.getWeight();
			}
			if (n.getNodeA() == null) {
				n.setNodeA(edges.get(0).getNode1());
			}
			if (n.getNodeB() == null) {
				n.setNodeB(edges.get(0).getNode1());
			}
			completed.add(n);
		}
		System.out.println("total distance:" + totalDistance);
		totalSize = totalDistance;
		JOptionPane.showMessageDialog(null, vis);
	}

	public static void outputDataLines(Edge e1, Edge e2) {
		Visualiser vis = new Visualiser(1000, 1000);
		double totalDistance = 0;
		ArrayList<Node> completed = new ArrayList<Node>();
		vis.addLine(e1);
		vis.addLine(e2);
		JOptionPane.showMessageDialog(null, vis);
	}

	public static boolean getCrossovers() {
		boolean hasImproved = false;
		for (Node n : Reader.coordinates) {
			for (Node k : Reader.coordinates) {
				if (n.getNodeA().getNodeB() != null && n.getNodeB() != null && k.getNodeA() != null
						&& k.getNodeB() != null && n.getNodeB() != k.getNodeB()) {
					if (n != k && n.getNodeA() != k && n.getNodeB() != k) {
						Edge ed1 = new Edge(n, n.getNodeB());
						Edge ed2 = new Edge(k, k.getNodeB());
						double tempSize = totalSize - ed1.getWeight();
						tempSize = tempSize - ed2.getWeight();
						EdgeCheck edgck = new EdgeCheck(ed1, ed2);
						if (edgck.doIntersect()) {
							if (tempSize + (new Edge(n, k.getNodeB()).getWeight())
									+ (new Edge(k, n.getNodeB()).getWeight()) < totalSize) {
								
								Node temp = n;
								Node temp2 = k;
								n=k.getNodeB();
								k=temp.getNodeB();
								hasImproved = true;

							}
						}
					}

				}
			}
		}
		return hasImproved;
	}

	public static void getEdges() {
		int maxVal = Reader.coordinates.size();
		System.out.println(maxVal);
		for (int i = 0; i < maxVal; i++) {
			Node outside = Reader.coordinates.get(i);
			nodes.put(outside.getId(), outside);
			for (int j = i + 1; j < maxVal; j++) {
				Edge ed = new Edge(outside, Reader.coordinates.get(j));
				edges.add(ed);
			}
		}
		System.out.println("edges found");
	}

	public static void shortestDistanceCalc() {
		edges.get(0).getNode1().setNodeA(new Node());
		edges.get(0).getNode1().setNodeB(edges.get(0).getNode2());
		edges.get(0).getNode2().setNodeA(edges.get(0).getNode1());
		Edge lastVal = null;
		for (Edge e : edges) {
			if (iterateNodes(e.getNode1(), e.getNode2()) && iterateNodes(e.getNode2(), e.getNode1())) {
				if (e.getNode1().getNodeA() == null) {
					outputEdges.add(new Edge(e.getNode1(), e.getNode2()));
					e.getNode1().setNodeA(e.getNode2());
				} else {
					e.getNode1().setNodeB(e.getNode2());
				}
				if (e.getNode2().getNodeA() == null) {
					e.getNode2().setNodeA(e.getNode1());
				} else {
					e.getNode2().setNodeB(e.getNode1());
				}
			}
			lastVal = e;
		}

	}

	public static boolean iterateNodes(Node nIN, Node nIN2) {
		Node lv = new Node();
		Node cn = nIN;
		if (cn.getNodeA() == null && cn.getNodeB() == null && (nIN2.getNodeA() == null || nIN2.getNodeB() == null)) {
			return true;
		}
		if (cn.getNodeA() == null || cn.getNodeB() == null) {
			lv = cn;

			if (cn.getNodeA() != null) {
				cn = cn.getNodeA();
			} else {
				cn = cn.getNodeB();
			}
			while (cn != null) {
				if (cn != nIN && cn != nIN2) {

					if (cn.getNodeA() == lv) {
						if (cn.getNodeB() == null) {
							return true;
						} else {
							lv = cn;
							cn = cn.getNodeB();
						}
					} else {
						if (cn.getNodeA() == null) {
							return true;
						} else {
							lv = cn;
							cn = cn.getNodeA();
						}
					}
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
		return false;

	}

	public static void test() {
		HashMap<Node, Integer> testData = new HashMap<>();
		for (Node n : Reader.coordinates) {
			if (testData.containsKey(n.getNodeA())) {
				int val = testData.get(n.getNodeA());
				val += 1;
				testData.replace(n.getNodeA(), val);
			} else {
				testData.put(n.getNodeA(), 0);
			}
			if (testData.containsKey(n.getNodeB())) {
				int val = testData.get(n.getNodeB());
				val += 1;
				testData.replace(n.getNodeB(), val);
			} else {
				testData.put(n.getNodeB(), 0);
			}
		}
		for (Node n : testData.keySet()) {
			if (!Reader.coordinates.contains(n)) {
				System.out.println(n);
			}
			if (testData.get(n) > 1) {
				System.out.println("----------------");
				System.out.println(n + ": " + testData.get(n));
				System.out.println("----------------");
			}

		}
	}

}
