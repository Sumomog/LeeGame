package game.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;

public class Draw {
	public static final void hp(int m, int i, int x, int y, int w, int h, Graphics2D g, Paint p1, Paint p2) {
	    g.setPaint(p1);
		g.fill(new Rectangle2D.Double(x    , y, w*i/m    , h));
	    g.setPaint(p2);
		g.fill(new Rectangle2D.Double(w*i/m, y, w*(m-i)/m, h));
	}
	public static final void txt(int x, int y, Graphics2D g, Paint p, int size) {
	    g.setPaint(p);
	    g.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 12));
	    g.drawString("日本語文字列", x, y);
	}
	public static final void rect(int x, int y, int w, int h, Graphics2D g, Color c, float a) {
        // アルファ値をセット（以後の描画は半透明になる）
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));
        g.setColor(c);
        g.fillRect(x, y, w, h);
	}
}
