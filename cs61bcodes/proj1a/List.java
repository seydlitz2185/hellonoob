public interface List<Item>{
    public void addFirst(Item x);
    public void addLast(Item x);
    public Item removeFirst();
    public Item removeLast();
    public Item get(int index);
    public boolean isEmpty();
    public int size();
    public Item getFirst();
    public Item getLast();
    public void printDeque();
}
