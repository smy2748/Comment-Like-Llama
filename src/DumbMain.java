import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Stephen Yingling on 4/25/14.
 */
public class DumbMain {

    public static void main(String args[]) throws FileNotFoundException {
        MChain dumb = new MChain();
        File f = new File("ReadingGaol");
        Scanner sc = new Scanner(f);

        StringBuilder text = new StringBuilder();

        while (sc.hasNext()){
            text.append(sc.nextLine());
            text.append(" ");
        }

        dumb.addContext(text.toString());
        dumb.finalizeChain();
        System.out.println(dumb.genString());
    }
}
