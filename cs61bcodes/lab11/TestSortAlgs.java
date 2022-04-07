import org.junit.Assert;
import org.junit.Test;
public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Hmar");
        tas.enqueue("Noe");
        tas.enqueue("Omar");
        tas.enqueue("Ftai");
        tas.enqueue("Itai");
        tas.enqueue("Ooe");
        tas.enqueue("Kmar");
        tas.enqueue("Voe");
        tas.enqueue("Qoe");
        tas.enqueue("Atai");
        tas.enqueue("Roe");
        tas.enqueue("Woe");
        tas.enqueue("Etai");
        tas.enqueue("Uoe");
        tas.enqueue("Lmar");
        tas.enqueue("Yoe");
        tas.enqueue("Gtai");
        tas.enqueue("Dtai");
        tas.enqueue("Xoe");
        tas.enqueue("Zoe");
        tas.enqueue("Soe");
        tas.enqueue("Mmar");
        tas.enqueue("Ctai");
        tas.enqueue("Toe");
        tas.enqueue("Btai");
        int target = tas.size();
        tas= QuickSort.quickSort(tas);
        Assert.assertEquals(target,tas.size());
        Assert.assertEquals(this.isSorted(tas),true);
    }

    @Test
    public void testMergeSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Hmar");
        tas.enqueue("Noe");
        tas.enqueue("Omar");
        tas.enqueue("Ftai");
        tas.enqueue("Itai");
        tas.enqueue("Ooe");
        tas.enqueue("Kmar");
        tas.enqueue("Voe");
        tas.enqueue("Qoe");
        tas.enqueue("Atai");
        tas.enqueue("Roe");
        tas.enqueue("Woe");
        tas.enqueue("Etai");
        tas.enqueue("Uoe");
        tas.enqueue("Lmar");
        tas.enqueue("Yoe");
        tas.enqueue("Gtai");
        tas.enqueue("Dtai");
        tas.enqueue("Xoe");
        tas.enqueue("Zoe");
        tas.enqueue("Soe");
        tas.enqueue("Mmar");
        tas.enqueue("Ctai");
        tas.enqueue("Toe");
        tas.enqueue("Btai");
        int target = tas.size();
        tas= MergeSort.mergeSort(tas);
        Assert.assertEquals(target,tas.size());
        Assert.assertEquals(this.isSorted(tas),true);
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
