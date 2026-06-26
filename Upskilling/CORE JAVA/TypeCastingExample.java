public class TypeCastingExample {
    public static void main(String[] args) {
        double originalDouble = 9.78;
        int castedToInt = (int) originalDouble; 
        System.out.println("Narrowing Casting ");
        System.out.println("Original double: " + originalDouble);
        System.out.println("Casted to int: " + castedToInt); /
        System.out.println(); 
        int originalInt = 50;
        double castedToDouble = originalInt; 
        System.out.println("Widening Casting ");
        System.out.println("Original int: " + originalInt);
        System.out.println("Casted to double: " + castedToDouble); 
    }
}