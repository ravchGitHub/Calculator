import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void calc(String input) {
        // Паттерн для выражения с арабскими операндами:
        Pattern patternExpressionArabic = Pattern.compile(
                "^((\\b[1-9]0\\b)|(\\b[1-9]\\b))\\s+[+\\-*/]\\s+((\\b[1-9]0\\b)|(\\b[1-9]\\b))");

        // Паттерн для выражения с римскими операндами:
        Pattern patternExpressionRoman = Pattern.compile(
                "^((\\b(I{1,3})\\b)|(\\b((I)?V(I){0,3})\\b)|(\\b(I?X)\\b))" +
                        "\\s+[+\\-*/]\\s+" +
                        "((\\b(I{1,3})\\b)|(\\b(I?V(I){0,3})\\b)|(\\b(I?X)\\b))");

        Matcher matcherArabic = patternExpressionArabic.matcher(input);
        Matcher matcherRoman =  patternExpressionRoman.matcher(input);

        Scanner scanner = new Scanner(input);

        String firstOperand =   scanner.next();
        String operator =       scanner.next();
        String secondOperand =  scanner.next();

        // Проверка на правильность выражения и его расчет:
        if (matcherArabic.find() == true) {
            int first =     Integer.valueOf(firstOperand);
            int second =    Integer.valueOf(secondOperand);
            int result =    calculate(first, second, operator);

            System.out.println(result);

        } else if (matcherRoman.find() == true) {
            RomanArabicMap romanArabicMap = new RomanArabicMap();

            int first =     romanArabicMap.get(firstOperand);
            int second =    romanArabicMap.get(secondOperand);
            int result =    calculate(first, second, operator);

            if (result <= 0) {
                throw new NullOrNegativeResultException("There are no zeros or negative numbers in Roman numerals.");
            } else {
                System.out.println(romanArabicMap.get(result));
            }

        } else {
            throw new IncorrectExpressionException("The expression was entered incorrectly.");
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String inputLine = scanner.nextLine();

            Pattern inputPattern = Pattern.compile("^(\\d+|\\D+)(\\s?[+\\-*/]\\s?)(\\d+|\\D+)");
            Matcher matcher = inputPattern.matcher(inputLine);

            String expression = matcher.replaceAll("$1 $2 $3");

            Scanner scannerOfExpression = new Scanner(expression);

            List<String> elements = new ArrayList<>();

            while (scannerOfExpression.hasNext()) {
                elements.add(scannerOfExpression.next());
            }

            // Проверка на недостаточность или избыточность элементов выражения
            if (elements.size() != 3) {
                throw new IncorrectExpressionException("The expression was entered incorrectly.");
            }

            calc(expression);
        } catch (NumberFormatException e) {
            throw new RuntimeException("The expression was entered incorrectly.");
        }
    }

    private static int calculate(int first, int second, String operator) {
        int result;

        switch (operator) {
            case ("+"):
                result = first + second;
                return result;
            case ("-"):
                result = first - second;
                return result;
            case ("*"):
                result = first * second;
                return result;
            case ("/"):
                result = first / second;
                return result;
            default:
                return -1;
        }
    }
}