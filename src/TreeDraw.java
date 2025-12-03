import java.util.ArrayList;
import java.util.List;
public class TreeDraw{

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("No length provided (Usage: java -jar TreeDrawer.jar <length>)");
            }

            int n = Integer.parseInt(args[0]);

            if (n < 0) {
                throw new IllegalArgumentException("Length cannot be negative.");
            }

            drawTree(n);

        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please provide an integer.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void drawTree(int n) {

        if (n == 0) {
            System.out.println("*");
            return;
        }
        int distinct = (n + 1) / 2; // ceil(n/2)

        //list of odd numbers
        List<Integer> odds = new ArrayList<>();
        for (int i = 0; i < distinct; i++) {
            odds.add(1 + 2 * i);
        }

        //sequence for star counts --final
        List<Integer> seq = new ArrayList<>();

        if (n % 2 == 1) {
            // odd n: ascend all odds, then mirror except the last (central) element
            seq.addAll(odds);
            for (int i = odds.size() - 2; i >= 0; i--) {
                seq.add(odds.get(i));
            }
        } else {
            // even n: start with 1, then every odd (except first) appears twice, then end with 1
            seq.add(odds.get(0)); // the initial single 1
            for (int i = 1; i < odds.size(); i++) {
                seq.add(odds.get(i));
                seq.add(odds.get(i));
            }
            seq.add(odds.get(0)); // final single 1

        }

        // print the pattern (centered using the largest odd)
        int maxStars = seq.stream().mapToInt(Integer::intValue).max().orElse(1);
        for (int stars : seq) {
            int spaces = (maxStars - stars) / 2;
            System.out.println(" ".repeat(spaces) + "*".repeat(stars));
        }

    }
}
