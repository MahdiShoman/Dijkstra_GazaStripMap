public class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    // Add an element to the end of the linked list
    public void add(Vertex data) {
        Node newNode = new Node();
        newNode.vertex=data;
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Print the elements of the linked list
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.vertex + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Check if the linked list is empty
    public boolean isEmpty() {
        return head == null;
    }
}