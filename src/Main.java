import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Калькулятор умеет выполнять арифметические операции," +
                " с двумя числами: a + b, a - b, a * b, a / b." +
                "\nКалькулятор умеет работать как с арабскими (1,2,3,4,5...), так и с римскими числами (I,II,III,IV,V...))." +
                "\nКалькулятор принимает на вход числа от 1 до 10 включительно." +
                "\nВведите мат. выражение одной строкой (пример: 1 + 5 или I + V): ");
        String userInput = scanner.nextLine();
        System.out.println(calc(userInput));
    }
    public static String calc(String expr) throws Exception {
        String result = "";
        String[] tokens = expr.split(" ");
        if (tokens.length != 3){
            throw new Exception("строка не является математической операцией" +
                    "\nформат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String number1 = tokens[0];
        String number2 = tokens[2];
        String operator = tokens[1];
        if (isRoman(number1) && isRoman(number2) && isOperator(operator) && !operator.equals("-")){
            int numFirst = toArabic(number1);
            int numSecond = toArabic(number2);
            return toRoman(calculator(String.valueOf(numFirst), String.valueOf(numSecond), operator));

        } else if (isArabic(number1) && isArabic(number2) && isOperator(operator)){
            if(Integer.parseInt(number1) <= 10 && Integer.parseInt(number2) <= 10){
                return String.valueOf(calculator(number1, number2, operator));
            }
            else{
                throw new Exception("Калькулятор принимает на вход числа от 1 до 10 включительно.");
            }
        } else if(isRoman(number1) && isRoman(number2) && operator.equals("-")){
            throw new Exception("в римской системе нет отрицательных чисел");
        } else if((isRoman(number1) && !isRoman(number2) || (!isRoman(number1) && isRoman(number2))) && isOperator(operator)){
            System.out.println("используются одновременно разные системы счисления");
        }else{
            throw new Exception("строка не является математической операцией");
        }

        return result;
    }

    public static int toArabic(String roman){
        return RomanNumbers.valueOf(roman).getVal();
    }

    public static String toRoman(int number) {
        String result = "";
        if (number < 1 || number > 100)
            return "Invalid Roman Number Value";

        while (number >= 100) {
            result += "C";
            number -= 100;
        }

        while (number >= 90) {
            result += "XC";
            number -= 90;
        }

        while (number >= 50) {
            result += "L";
            number -= 50;
        }

        while (number >= 40) {
            result += "XL";
            number -= 40;
        }

        while (number >= 10) {
            result += "X";
            number -= 10;
        }

        while (number >= 9) {
            result += "IX";
            number -= 9;
        }

        while (number >= 5) {
            result += "V";
            number -= 5;
        }

        while (number >= 4) {
            result += "IV";
            number -= 4;
        }

        while (number >= 1) {
            result += "I";
            number -= 1;
        }

        return result;
    }


    static boolean isArabic(String input){
        try{
            Integer.parseInt(input);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    static boolean isRoman(String input) {
        for (RomanNumbers romNum: RomanNumbers.values()){
            if (input.equals(romNum.name())){
                return true;
            }
        }
        return false;
    }

    static boolean isOperator(String operator){
        String[] operators = {"+", "-", "*", "/"};
        if (Arrays.asList(operators).contains(operator)){
            return true;
        }
        return false;
    }

    static int calculator(String n1, String n2, String oper){
        switch (oper){
            case "+":
                return Integer.parseInt(n1) + Integer.parseInt(n2);
            case "-":
                return Integer.parseInt(n1) - Integer.parseInt(n2);
            case "*":
                return Integer.parseInt(n1) * Integer.parseInt(n2);
            case "/":
                return Integer.parseInt(n1) / Integer.parseInt(n2);

            default:
                throw new IllegalStateException("Unexpected value: " + oper);
        }
    }
}