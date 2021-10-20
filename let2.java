class Solution {
    
    ListNode retVal ;
    int addIndex = 0;

    public ListNode addTwoNumbersHelper(ListNode l1, ListNode l2) {
        
        if(l1 == null && l2 != null && addIndex + l2.val < 10){
            retVal = new ListNode(addIndex + l2.val, retVal  );
            addIndex = 0;
            return addTwoNumbers(null, l2.next);
        } else if(l1 == null && l2 != null && addIndex + l2.val >= 10){
            retVal = new ListNode(addIndex + l2.val-10, retVal  );
            addIndex = 1;
            return addTwoNumbers(null, l2.next) ;
        }else if(l2 == null  && l1 != null && addIndex + l1.val < 10){
            retVal = new ListNode(addIndex + l1.val, retVal  );
            addIndex = 0;
            return addTwoNumbers(null, l1.next) ;
        } else if(l2 == null  && l1 != null && addIndex + l1.val >= 10){
            retVal = new ListNode(addIndex + l1.val-10, retVal  );
            addIndex = 1;
            return addTwoNumbers(null, l1.next) ;
        } else if (l1 == null && addIndex ==0) {
            return retVal;
        }else if (l1 == null && addIndex ==1) {
            retVal = new ListNode(addIndex, retVal  );
            return retVal;
        }

        if(addIndex + l1.val + l2.val >=10 ){
            retVal = new ListNode(addIndex + l1.val + l2.val-10,retVal);
            addIndex = 1;
        }else {
            retVal = new ListNode ( addIndex+ l1.val + l2.val,retVal);
            addIndex = 0;
        }
        
        return addTwoNumbersHelper(l1.next, l2.next);
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode temp = head.next;
        ListNode newHead = reverse(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        retVal = addTwoNumbersHelper(l1,l2);
        //retVal =reverse(retVal);
        return retVal;
    }

}