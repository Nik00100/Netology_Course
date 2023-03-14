public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        Ints calc = new IntsCalculator();
        //демонстрация
        int a = 5;
        int b = 3;
        System.out.println("Result of method " + IntsCalculator.class.getDeclaredMethods()[1].toString() +
                String.format(" for arguments %d and %d equals: ",a,b) + calc.sum(a,b));
        System.out.println("Result of method " + IntsCalculator.class.getDeclaredMethods()[2].toString() +
                String.format(" for arguments %d and %d equals: ",a,b) + calc.mult(a,b));
        System.out.println("Result of method " + IntsCalculator.class.getDeclaredMethods()[0].toString() +
                String.format(" for arguments %d and %d equals: ",a,b) + calc.pow(a,b));
    }
}
