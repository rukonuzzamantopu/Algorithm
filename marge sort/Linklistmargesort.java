
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}

class Linklistmargesort {
    static Node split(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast != null) {
                slow = slow.next;
            }
        }

        Node temp = slow.next;
        slow.next = null;
        return temp;
    }

    static Node merge(Node first, Node second) {

        if (first == null)
            return second;
        if (second == null)
            return first;

        if (first.data > second.data) {

            first.next = merge(first.next, second);
            return first;
        } else {
            second.next = merge(first, second.next);
            return second;
        }
    }

    static Node mergeSort(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        Node second = split(head);

        head = mergeSort(head);
        second = mergeSort(second);

        return merge(head, second);
    }

    static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(9);
        head.next = new Node(8);
        head.next.next = new Node(5);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);

        head = mergeSort(head);
        printList(head);
    }
}
