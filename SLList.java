public class SLList{
	private static class IntNode {
		int item;
		IntNode next;

		public IntNode(int i,IntNode n) {
			this.item = i;
			this.next = n;
		}
	}
	private IntNode sentinel;
	private int size;
	/**constructors*/
	public SLList(){
		sentinel = new IntNode(-1,null);
		size =0;
	}

	public SLList(int i ){
		sentinel = new IntNode(-1,null);
		sentinel.next = new IntNode(i,null);
		size = 1;
	}

	public void addLast(int i){
		size+=1;
		IntNode p = sentinel;
		while(p.next !=null){
			p = p.next;
		}
		p.next = new IntNode(i,null);
	}
	
	public void addFirst(int i){
		size+=1;
		sentinel.next = new IntNode(i,sentinel.next);
	}
	
	public int getFirst(){
		return sentinel.next.item;
	}

	public IntNode getIth(int x){
		IntNode p =  sentinel;
		for(int i = 0; i<x;i++){
			p = p.next;
		}
		return p;
	}
	public int size(){ return size;}

	public static void main(String[] args){
		SLList s = new SLList();
		s.addLast(4);
		s.addFirst(3);
		s.addFirst(2);
		s.addFirst(1);
		System.out.println(s.getFirst());
		System.out.println(s.getIth(1).item);
		System.out.println(s.size());

	}
	
}
