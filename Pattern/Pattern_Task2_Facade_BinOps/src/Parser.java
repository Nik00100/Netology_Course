public class Parser {
    public Integer parseIntFromBinaryString (String str) {
        try {
            return Integer.parseInt(str,2);
        } catch (NumberFormatException e) {
            System.out.println(String.format("Неверно введен формат двоичного числа: %s", str));
            e.printStackTrace();
        }
        return null;
    }

    public String convertIntToBinaryString (int num) {
        return Integer.toBinaryString(num);
    }
}
