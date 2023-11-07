import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> revAndDollars = List.of(scanner.nextLine().split(" "));
        int rev = Integer.parseInt(revAndDollars.get(0));
        int dollars = Integer.parseInt(revAndDollars.get(1));
        int canBuy = 0;

        List<Integer> prices = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        for (Integer price : prices) {
            if(price <= dollars && canBuy < price){
                canBuy = price;
            }
        }
        System.out.println(canBuy);
    }
}
