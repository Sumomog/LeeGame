package sanpuru;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/*
<applet code="ThreadTest7.class" codebase="class" width="150" height="150">
</applet>
*/

public class ThreadTest7 extends Applet implements Runnable{
  Thread thread = null;
  int x;
  Dimension size;
  Image back;
  Graphics buffer;

  public void init(){
    x = 10;

    size = getSize();
    back = createImage(size.width, size.height);
    buffer = back.getGraphics();

    thread = new Thread(this);
    thread.start();
  }

  public void update(Graphics g){
    paint(g);
  }

  public void paint(Graphics g){
    buffer.setColor(getBackground());
    buffer.fillRect(0, 0, size.width, size.height);

    for (int j = 0 ; j < 100 ; j++){
      for (int i = 0 ; i < 255 ; i++){
        buffer.setColor(new Color(i, 0, 0));
        buffer.drawLine(x + i, 10, x + i, 265);
      }

      for (int i = 0 ; i < 127 ; i++){
        buffer.setColor(new Color(0, 0, i * 2));
        buffer.drawLine(x, 10 + i * 2, x + 255, 10 + i * 2);
      }
    }

    g.drawImage(back, 0, 0, this);
  }

  public void run(){
    while(true){
      x += 1;
      if (x >= 100){
        x = 10;
      }

      repaint();

     /* 200ミリ秒待機する */
      try{
        Thread.sleep(200);
      }catch (InterruptedException e){
      }
    }
  }
}