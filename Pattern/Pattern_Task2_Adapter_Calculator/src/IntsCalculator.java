public class IntsCalculator implements Ints {
    protected final Calculator target;

    public IntsCalculator() {
        this.target = new Calculator();
    }

    @Override
    public int sum(int arg0, int arg1) {
        Calculator.Formula sum = target.newFormula();
        sum.addOperand(arg0);
        sum.addOperand(arg1);
        return (int) sum.calculate(Calculator.Operation.SUM).result();
    }

    @Override
    public int mult(int arg0, int arg1) {
        Calculator.Formula mult = target.newFormula();
        mult.addOperand(arg0);
        mult.addOperand(arg1);
        return (int) mult.calculate(Calculator.Operation.MULT).result();
    }

    @Override
    public int pow(int a, int b) {
        Calculator.Formula pow = target.newFormula();
        pow.addOperand(a);
        pow.addOperand(b);
        return (int) pow.calculate(Calculator.Operation.POW).result();
    }
}