/**
 *
 */
package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;

import game.manager.MapManager;
import game.map.MapData;
import game.map.data.CellObjectB;
import game.util.Constant;
import game.util.Util;

/**
 * @author Lee
 *
 */
public class Map extends JFrame implements Constant {

	/** マップデータ */
//	private MapData<CellObjectB> mapData = MapManager.getManager().getData("../map/map01.map");
	private MapData<CellObjectB> mapData = MapManager.getManager().getData("../map/map02.map");

	public int getW() {
		return SX *  2 + mapData.getX() * mapData.getW();
	}
	public int getH() {
		return SX + SY + mapData.getY() * mapData.getH();
	}
	public int getX(int x) {
		return SX + x * mapData.getW();
	}
	public int getY(int y) {
		return SY + y * mapData.getH();
	}
	public int getx(int n) {
		return (n % mapData.getIW()) * mapData.getW();
	}
	public int gety(int n) {
		return (n / mapData.getIW()) * mapData.getH();
	}
	public Point getP(int x, int y) {
		int dx = SX + x * mapData.getW();
		int dy = SY + y * mapData.getH();
		return new Point(dx, dy);
	}
	public Point getP(int n) {
		int sx = (n % mapData.getIW()) * mapData.getW();
		int sy = (n / mapData.getIW()) * mapData.getH();
		return new Point(sx, sy);
	}
	@Override
	public void paint(Graphics g) {
		Util.forEach2(mapData.getY(), mapData.getX(), (y, x) -> {
			int dx, dy, dw, dh;
			int sx, sy, sw, sh;
			int no = mapData.getCell(x, y, 0);
			Image img = mapData.getImage();
			if (no >= mapData.getIW() * mapData.getIH()) {
				throw new RuntimeException("Sprite Number Error" + no);
			}
			dx = SX + x * mapData.getW();
			dy = SY + y * mapData.getH();
			dw = dx + mapData.getW();
			dh = dy + mapData.getH();
			sx = (no % mapData.getIW()) * mapData.getW();
			sy = (no / mapData.getIW()) * mapData.getH();
			sw = sx + mapData.getW();
			sh = sy + mapData.getH();
			g.drawImage(img, dx, dy, dw, dh, sx, sy, sw, sh, this);
		});
	}
}
