import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Day_01 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("input/01.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int sum_of_values = 0;
        while (scan.hasNext()) {
            String line = scan.nextLine();
            line = line.replaceAll("one", "one1one");
            line = line.replaceAll("two", "two2two");
            line = line.replaceAll("three", "three3three");
            line = line.replaceAll("four", "four4four");
            line = line.replaceAll("five", "five5five");
            line = line.replaceAll("six", "six6six");
            line = line.replaceAll("seven", "seven7seven");
            line = line.replaceAll("eight", "eight8eight");
            line = line.replaceAll("nine", "nine9nine");

            String digits = line.replaceAll("[^0-9]", "");
            sum_of_values += Integer.parseInt("" + digits.charAt(0) + digits.charAt(digits.length() -1));
        }
        scan.close();
        System.out.println(sum_of_values);

    }
}
