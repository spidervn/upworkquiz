import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SimplyLinkedList {

	static final String INPUT_FILE = "input.data";
	
	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(INPUT_FILE));
			
			if (lines.size() > 0) {
				// Initialize list
				String[] nums = lines.get(0).split(",");
				
				for (String num01 : nums) {
					try {
						list.append(Integer.parseInt(num01));
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				// 
				for (int i=1;i<lines.size();i++) {
					if (lines.get(i).startsWith("append")) {
						try {
							Integer num01 = Integer.parseInt(lines.get(i).substring("append".length()).trim());
							
							list.append(num01);
							System.out.println("Append " + num01);
							System.out.print("After appending: ");
							list.print();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (lines.get(i).startsWith("removeTail")) {
						list.removeTail();
						System.out.println("removeTail ");
						System.out.print("After removing: ");
						list.print();
						
					} else if (lines.get(i).startsWith("filterLarger")) {
						try {
							Integer num01 = Integer.parseInt(lines.get(i).substring("filterLarger".length()).trim());
							list.filterLarger(num01);
							
							System.out.println("filterlarger than " + num01);
							System.out.print("After filtering: ");
							list.print();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	static class MyLinkedList<T extends Comparable<? super T>> {
		
		NodeList<T> startNode = null;
		
		public MyLinkedList() {
		}
		
		public MyLinkedList(T arr[]) {
			for (int i=0;i<arr.length;i++) {
				append(arr[i]);
			}
		}
		
		public int append(T t) {
			
			if (startNode == null) {
				startNode = new NodeList<T>(t);				
			} else {
				NodeList<T> newNode = new NodeList<>(t);								
				NodeList<T> endList = getEndList();
				
				endList.setNext(newNode);
				newNode.setBefore(endList);
				
			}
			
			return 0;
		}
		
		public int removeTail() {
			NodeList<T> endNode = getEndList();
			
			if (endNode != null) {
				if (endNode.getBefore() != null) {
					endNode.getBefore().setNext(null);					
					endNode = null;
				} else {
					startNode = null;
				}
			}
			return 0;
		}
		
		public int filterLarger(T t) {
			NodeList<T> enumNode = startNode;
			
			while (enumNode != null) {			
				if (enumNode.getValue().compareTo(t) > 0) {
					// remove this node
					
					if (enumNode.before == null) {
						// If the removed node is the begin 
						startNode = enumNode.getNext();
						startNode.setBefore(null);
					} else {
						// Remove enumNode
						enumNode.getBefore().setNext(enumNode.getNext());
						if (enumNode.getNext() != null)  {
							enumNode.getNext().setBefore(enumNode.getBefore());
						}
					}
				} 
					
				enumNode = enumNode.getNext();
			}
			
			return 0;
		}
		
		public NodeList<T> getEndList() {
			NodeList<T> enumNode = startNode;
			while (enumNode != null && enumNode.getNext() != null) {
				enumNode = enumNode.getNext();
			}
			
			return enumNode;
		}
		
		public void print() {
			int i=0;
			
			NodeList<T> enumNode = startNode;
			
			while (enumNode != null) {				
				if (i++>0) {
					System.out.print("," + enumNode.getValue());
				} else {
					System.out.print(enumNode.getValue());
				}
				enumNode = enumNode.getNext();
			}
			
			if (i==0) {
				System.out.println("EmptyList");
			} else {
				System.out.println("");
			}
		}
	}
	
	static class NodeList<T extends Comparable<? super T>> {
		
		private T value;
		private NodeList<T> next;
		private NodeList<T> before;
		
		
		
		public NodeList(T value) {			
			super();
			this.value = value;
			
			next = null;
			before = null;			
		}
		/**
		 * @return the value
		 */
		public T getValue() {
			return value;
		}
		/**
		 * @param value the value to set
		 */
		public void setValue(T value) {
			this.value = value;
		}
		/**
		 * @return the next
		 */
		public NodeList<T> getNext() {
			return next;
		}
		/**
		 * @param next the next to set
		 */
		public void setNext(NodeList<T> next) {
			this.next = next;
		}
		/**
		 * @return the before
		 */
		public NodeList<T> getBefore() {
			return before;
		}
		/**
		 * @param before the before to set
		 */
		public void setBefore(NodeList<T> before) {
			this.before = before;
		}
	}
	
}
