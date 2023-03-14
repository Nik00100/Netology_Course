public class Main {
    public static void main(String[] args) {
        BinOps bins = new BinOps();
        //демонстрация

        String a= "110110"; //54
        String b="1010"; //10

        System.out.println(bins.mult(a,b)); //540 -> 1000011100
        System.out.println(bins.sum(a,b)); //64 -> 1000000
    }
}