package sanpuru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PipeJava {

    /**
     * @param args
     */
    public static void main(String[] args) {
        PipeJava pj = new PipeJava();
        try {
            if (System.in.available() != 0) {
                pj.printInputStream(System.in, "UTF-8");
            } else {
                System.out.println("Not Found: pipied input");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printInputStream(InputStream is, String charsetName) throws IOException {
        InputStreamReader isr = new InputStreamReader(is, charsetName);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null ) {
            System.out.println(line);
        }
    }
}