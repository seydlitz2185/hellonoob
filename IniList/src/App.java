public class App {
    public static void main(String[] args) throws Exception {
        IntList l = new IntList(-1, null);
        l = new IntList(12, l);
        l = new IntList(11, l);
        l = new IntList(10, l);
        l = new IntList(9, l);
        l = new IntList(8, l);
        l = new IntList(7, l);
        l = new IntList(6, l);
        l = new IntList(5, l);
        l = new IntList(4, l);
        l = new IntList(3, l);
        l = new IntList(2, l);
        l = new IntList(1, l);
        System.out.println((l.get(13)).first);

    }
}
