import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        LCG lcg = new LCG();
        lcg.setSeed(102);
        System.out.println(Arrays.toString(lcg.generateMany(100, -1, 74)));
        System.out.println(lcg.generateOne(-1, 74));
    }
}
