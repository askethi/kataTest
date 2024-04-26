
import java.util.Arrays;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static String[] operators = new String[]{"\\*", "/", "\\+", "-"};

    public static void main(String[] args) {
        try {
            System.out.println(calc("V / III"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



   static Integer calc(String input) throws Exception {
        String[] parsed = new String[]{};
        //String[] result = new String[3];
        String operator = null;
        String num1 = null;
        String num2 = null;
        for (String op : operators) {
            parsed = input.split(op);
            /* for (String s : parsed) {
               System.out.println(s);}
            */
           if (!parsed[0].equals(input)) {
               num1 = parsed[0].trim();
               num2 = parsed[1].trim();
               operator = op;
               break;
           }
        }
        if (parsed.length > 3) {
            throw new Exception ("only 1 operator allowed");
        }
        if (operator == null) {
            throw new Exception("operator missing");
        }
        if (!isArabian(num1) && !isRoman(num1)) {
            throw new Exception("num1 is not a valid number");
        }
        if (!isArabian(num2) && !isRoman(num2)) {
           throw new Exception("num2 is not a valid number");
        }
        if (isArabian(num1) && isRoman(num2) || isRoman(num1) && isArabian(num2)) {
            throw new Exception("numbers are of different types");
        }
        if (romToInt(num1) < romToInt(num2) && operator.equals("-")) {
            throw new Exception("roman numbers cannot be negative");
        }
        if (isRoman(num1)) {
            return performCalc(operator, romToInt(num1), romToInt(num2));
        }
        else {
            return performCalc(operator, arbToInt(num1), arbToInt(num2));
        }
    }

    static Integer performCalc(String operator, Integer n1, Integer n2) throws Exception {
        Integer result;
        switch (operator) {
            case "\\*":
                return n1 * n2;
            case "/":
                return n1 / n2;
            case "\\+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            default:
                throw new Exception("something went wrong..");

        }
    }

    static Boolean isArabian(String input) {
        String[] nums = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        if (Arrays.asList(nums).contains(input)) {
            return true;
        } else {
            return false;
        }
    }

    static Boolean isRoman(String input) {
        String[] romans = new String[] {"I", "II", "III", "IIII", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return (Arrays.asList(romans).contains(input));
    }

    static Integer romToInt(String input) {
        //too much hardcoded(?) See romToInt2
        Integer out;
        out = 0;
        switch (input) {
            case "I" -> out = 1;
            case "II" -> out = 2;
            case "III" -> out = 3;
            case "IIII" -> out = 4;
            case "IV" -> out = 4;
            case "V" -> out = 5;
            case "VI" -> out = 6;
            case "VII" -> out = 7;
            case "VIII" -> out = 8;
            case "IX" -> out = 9;
            case "X" -> out = 10;
        }
        return out;}

    static Integer romToInt2(String input) {
        Integer result = 0;
        Enum[] values = Romans.values();
        for (Enum val : values) {
            if (val.toString().equals(input)) {
                result = val.ordinal();
                break;
            }
        }
        if (result > 4) result -= 1;
        return result;
    }
    static Integer arbToInt(String input) {
        return Integer.valueOf(input);
    }
}