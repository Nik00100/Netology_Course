public class BinOps {
    private Parser parser = new Parser();

    public String sum(String a, String b) {
    /*Переведите числа из двоичной записи в
      строках в int, сложите и переведите
      обратно в двоичную запись*/
        int aInt = parser.parseIntFromBinaryString(a);
        int bInt = parser.parseIntFromBinaryString(b);

        return parser.convertIntToBinaryString(aInt+bInt);
    }

    public String mult(String a, String b) {
    /*Переведите числа из двоичной записи в
      строках в int, перемножьте и переведите
      обратно в двоичную запись*/
        int aInt = parser.parseIntFromBinaryString(a);
        int bInt = parser.parseIntFromBinaryString(b);

        return parser.convertIntToBinaryString(aInt*bInt);
    }


}