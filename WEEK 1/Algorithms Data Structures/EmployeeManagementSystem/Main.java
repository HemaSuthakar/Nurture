public class Main {
    private Employee[] employees;
    private int count;

    public Main(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }

    public void addEmployee(Employee e) {
        if (count < employees.length) employees[count++] = e;
    }

    public Employee searchEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == id) return employees[i];
        }
        return null;
    }

    public void traverse() {
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i].getName());
        }
    }

    public void deleteEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == id) {
                employees[i] = employees[count - 1]; // Move last to empty spot
                employees[count - 1] = null;
                count--;
                return;
            }
        }
    }
}