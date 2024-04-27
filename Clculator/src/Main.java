
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static String[] operators = new String[]{"\\*", "/", "\\+", "-"};
    static Scanner scanner = new Scanner(System.in);
    static String input;

    public static void main(String[] args) {
        System.out.println("Welcome! Type 'quit' to quit.");
        do {
            System.out.println("Your expression here:");
            input = scanner.nextLine();
            if (!input.equals("quit")) {
                try {
                    System.out.println(calc(input));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("program terminated");
            }
        } while (!input.equals("quit")) ;
    }



   static String calc(String input) throws Exception {
        String[] parsed = new String[]{};
        String operator = null;
        String num1 = null;
        String num2 = null;
        for (String op : operators) {
            parsed = input.split(op);
           if (!parsed[0].equals(input)) {
               num1 = parsed[0].trim();
               num2 = parsed[1].trim();
               operator = op;
               break;
           }
        }
        if (parsed.length > 2) {
            throw new Exception ("only 1 operator allowed");
        }
        if (operator == null) {
            throw new Exception("operator missing");
        }
        if (!isArabian(num1) && !isRoman(num1)) {
            throw new Exception("num1 [at least] is not a valid number");
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
            return intToRom(performCalc(operator, romToInt(num1), romToInt(num2)));
        }
        else {
            return performCalc(operator, arbToInt(num1), arbToInt(num2)).toString();
        }
    }

    static Integer performCalc(String operator, Integer n1, Integer n2) throws Exception {
        return switch (operator) {
            case "\\*" -> n1 * n2;
            case "/" -> n1 / n2;
            case "\\+" -> n1 + n2;
            case "-" -> n1 - n2;
            default -> throw new Exception("something went wrong..");
        };
    }

    static Boolean isArabian(String input) {
        String[] nums = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        return (Arrays.asList(nums).contains(input));
    }

    static Boolean isRoman(String input) {
        String[] romans = new String[] {"I", "II", "III", "IIII", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return (Arrays.asList(romans).contains(input));
    }

    static Integer romToInt(String input) {
        //too much hardcoded(?) See romToInt2
        return switch (input) {
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IIII", "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" -> 8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> 0;
        };
    }

    static String intToRom(Integer input) {
        Integer num = input;
        List<Pair<Integer, String>> pairs = Arrays.asList(
                new Pair (100, "C"),
                new Pair (90, "XC"),
                new Pair (50, "L"),
                new Pair (40, "XL"),
                new Pair (10, "X"),
                new Pair (9, "IX"),
                new Pair (5, "V"),
                new Pair (4, "IV"),
                new Pair (1, "I"));
                                                                                ;
        String result = "";
        for (Pair<Integer, String> pair : pairs) {
            while (num >= pair.getKey()) {
                num = num - pair.getKey();
                result += pair.getValue();
            }
        }
        return result;}

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