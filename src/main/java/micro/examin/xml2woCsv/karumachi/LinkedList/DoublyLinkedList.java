package micro.examin.xml2woCsv.karumachi.LinkedList;

public class DoublyLinkedList {
    public static void main(String[] args) {

    }
}

class DDL {
    DDL prev;
    DDL next;
    int val;

    DDL(int val) {
        this.val = val;
    }
}

class DoublyList {
    DDL head;
    DDL end;

    DoublyList(int val) {
        head = new DDL(val);
        end = head;
    }

    public boolean push(int val) {
        if (head == null || end == null) {
            head = new DDL(val);
        } else {
            end.next = new DDL(val);
            end = end.next;
        }
        return true;
    }

    public int pop() {
        if (head != null && end != null) {
            int val = end.val;
            end = end.prev;
            end.next = null;
            return val;
        }else throw new NullPointerException();
    }

}
