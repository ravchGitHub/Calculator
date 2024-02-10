import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class RomanArabicMap {
    Scanner scanner;
    Map<String, Integer> romanNumerals = new HashMap<>();
    Map<Integer, String> arabicNumbers = new HashMap<>();

    {
        try {
            File file = new File(".\\src\\resources\\RomanArabicNums.txt");

            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                int arabicNum =     scanner.nextInt();
                String romanNum =   scanner.next();

                romanNumerals.put(romanNum, arabicNum);
                arabicNumbers.put(arabicNum, romanNum);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int get(String key) {
        return romanNumerals.get(key);
    }

    public String get(Integer key) {
        return arabicNumbers.get(key);
    }
}