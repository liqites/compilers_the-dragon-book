package teslalee;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
	// write your code here
        Parser parser = new Parser();
        parser.expr();
        System.out.write('\n');
    }
}
