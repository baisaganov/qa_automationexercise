import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> str = new ArrayList<>(List.of(scanner.nextLine().split("")));

        List<String> base = List.of("s", "h", "e", "r", "i", "f", "f");
        int count = 0;

        for( int i = 0; i < str.size(); i++){
            for (int j = 0; j < base.size(); j++) {
                if (str.remove(base.get(j))){
                    count += 1;
                }
            }
            if (count%7 != 0) break;
        }
        if (count <=7){
            System.out.println(0);
        }else {
            System.out.println(count/7);

        }
    }
 }
