package sanpuru;
//★ AWT で 終了を設定して Gif 画像を表示    前田 稔
import java.awt.*;
import java.awt.event.*;

class AWTGif extends Frame
{   static  Image   img;

  // Main
  public static void main(String args[])
  {   new AWTGif();
  }

  // Constructor
  public AWTGif()
  {   super("AWT GIF");
      img = getToolkit().getImage("c:\\data\\test\\dog.gif");
      addWindowListener(new WindowAdapter()
      {   public void windowClosing(WindowEvent e)
          {   FileExit();
          }
      });
      setSize(240, 200);
      setVisible(true);
  }

  // Paint Method
  public void paint(Graphics g)
  {   super.paint(g);
      if (img!=null)
      {   g.drawImage(img,16,40,this);  }
  }

  // Close Event
  public static void FileExit()
  {   System.exit(0);
  }
}

