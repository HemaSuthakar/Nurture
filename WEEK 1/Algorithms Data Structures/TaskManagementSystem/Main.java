public class Main {
    private Node head;

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) head = newNode;
        else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
    }

    public void traverse() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.task.getTaskName());
            temp = temp.next;
        }
    }

    public void deleteTask(int id) {
        if (head == null) return;
        if (head.task.getTaskId() == id) { head = head.next; return; }
        
        Node temp = head;
        while (temp.next != null && temp.next.task.getTaskId() != id) {
            temp = temp.next;
        }
        if (temp.next != null) temp.next = temp.next.next;
    }
}