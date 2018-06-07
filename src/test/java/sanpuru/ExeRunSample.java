// https://java-reference.com/java_tech_exerun1.html
package sanpuru;

import java.io.IOException;

public class ExeRunSample {

    public static void main(String args[]) {

        try{
            //ランタイム取得
            Runtime runtime = Runtime.getRuntime();

            //exeを実行する
            //runtime.exec("calc.exe");    //パスが通っていればファイル名だけでOK
            //runtime.exec("C:\\hoge.exe");  //フルパスでの指定もOK
//            runtime.exec("java /game/src/game/GameMain4.java");    //パスが通っていればファイル名だけでOK
//            runtime.exec(new String[] {"C:\\Program Files\\Java\\j2re1.4.2_13\\bin\\java", "-version" });
            runtime.exec(new String[] {"java", "-version" });

        }catch(IOException e){
            //指定したファイルが無い場合の処理
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}