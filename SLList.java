public class SLList{
	private static class IntNode {
		int item;
		IntNode next;

		public IntNode(int i,IntNode n) {
			this.item = i;
			this.next = n;
		}
	}
	private IntNode sentinal;
	private int num;
	/**constructors*/
	public SLList(){
		sentinal = new IntNode(-1,sentinal);
		num =1;
	}

	public SLList(int i , IntNode n){
		sentinal = new IntNode(i,n);
		num = 1;
	}

	public void addLast(int i){
		num+=1;
		sentinal  = new IntNode(i,sentinal.next);
	}
	
	public void addFirst(int i){
		num+=1;
		sentinal = new IntNode(i,sentinal);
		sentinal = sentinal.next;
	}
	
	/**public void print(){
		
			System.out.println(sentinal.item);
			sentinal.next.print();
		}
	}*/
	public void size(){ System.out.println(num);}

	public static void main(String[] args){
		SLList s = new SLList();
		s.addLast(20);
		s.addFirst(4);
		s.size();

	}
}
