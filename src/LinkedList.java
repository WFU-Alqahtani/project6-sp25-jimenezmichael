import java.util.Random;

// linked list class for a deck of cards
public class LinkedList {

    public Node head;
    public Node tail;
    public int size = 0;

    LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void shuffle(int shuffle_count) {

        Random rand = new Random();
        for(int i = 0; i < shuffle_count; i++) {
            // pick two random integers
            int r1 = rand.nextInt(52);
            int r2 = rand.nextInt(52);

            swap(r1,r2); // swap nodes at these indices
        }
    }

    // remove a card from a specific index
    public Card remove_from_index(int index) {
        // edge case for removing head
        if (index == 0) {
            Node removed = head;
            head.next.prev = null;
            head = head.next;
            size -= 1; // updates size
            return removed.data;
        }
        // edge case for removing tail
        if (index == size - 1) {
            Node removed = tail;
            tail.prev.next = null;
            tail = tail.prev;
            size -= 1; // updates size
            return removed.data;
        }
        // regular case
        Node removed = head;
        int count = 1;
        // while loop will stop once the node at index has been reached
        while (count <= index) {
            removed = removed.next;
            count++;
        }
        removed.prev.next = removed.next;
        removed.next.prev = removed.prev;
        size -= 1; // updates size
        return removed.data;
    }

    // insert a card at a specific index
    public void insert_at_index(Card x, int index) {
        // edge case for inserting at head
        if (index == 0) {
            Node added = new Node(x);
            head.prev = added;
            added.next = head;
            head = added;
            size += 1; // updates size
            return;
        }
        // edge case for inserting at tail
        if (index == size - 1) {
            Node added = new Node(x);
            tail.prev.next = added;
            added.prev = tail.prev;
            added.next = tail;
            tail.prev = added;
            size += 1; // updates size
            return;
        }
        // regular case
        Node added = new Node(x);
        Node curr = head;
        int count = 1;
        // while loop stops once node at target index has been reached
        while (count <= index) {
            curr = curr.next;
            count++;
        }
        // updates links between nodes
        curr.prev.next = added;
        added.prev = curr.prev;
        curr.prev = added;
        added.next = curr;
        size += 1; // updates size
    }

    // swap two cards in the deck at the specific indices
    public void swap(int index1, int index2) {
        // my algorithm only works when index1 < index2 so i update their values if index2 < index1
        if (index2 < index1) {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }
        // if indices are equal no swap is needed
        if (index1 == index2) {
            return;
        }
        // regular case
        Node target1 = new Node(remove_from_index(index1)); // target1 holds the node removed at index1
        insert_at_index(target1.data, index2 - 1); // inserts at index2 -1 because removing a node decreases size and shifts all indices after the removed node down by 1
        Node target2 = new Node(remove_from_index(index2)); // target2 holds the node removed at index2
        insert_at_index(target2.data, index1); // inserts target2 at index1
    }

    // add card at the end of the list
    public void add_at_tail(Card data) {
        Node n = new Node(data);
        // if list is empty, assign a head
        if (tail == null) {
            head = n;
            tail = n;
        }
        else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
    }

    // remove a card from the beginning of the list
    public Card remove_from_head() {
        // if removing from head, just return head data
        if (head.next == null) {
            return head.data;
        }
        return remove_from_index(0);
    }

    // check to make sure the linked list is implemented correctly by iterating forwards and backwards
    // and verifying that the size of the list is the same when counted both ways.
    // 1) if a node is incorrectly removed
    // 2) and head and tail are correctly updated
    // 3) each node's prev and next elements are correctly updated
    public void sanity_check() {
        // count nodes, counting forward
        Node curr = head;
        int count_forward = 0;
        while (curr != null) {
            curr = curr.next;
            count_forward++;
        }

        // count nodes, counting backward
        curr = tail;
        int count_backward = 0;
        while (curr != null) {
            curr = curr.prev;
            count_backward++;
        }

        // check that forward count, backward count, and internal size of the list match
        if (count_backward == count_forward && count_backward == size) {
            System.out.println("Basic sanity Checks passed");
        }
        else {
            // there was an error, here are the stats
            System.out.println("Count forward:  " + count_forward);
            System.out.println("Count backward: " + count_backward);
            System.out.println("Size of LL:     " + size);
            System.out.println("Sanity checks failed");
            System.exit(-1);
        }
    }

    // print the deck
    public void print() {
        Node curr = head;
        int i = 1;
        while(curr != null) {
            curr.data.print_card();
            if(curr.next != null)
                System.out.print(" -->  ");
            else
                System.out.println(" X");

            if (i % 7 == 0) System.out.println("");
            i = i + 1;
            curr = curr.next;
        }
        System.out.println("");
    }
}