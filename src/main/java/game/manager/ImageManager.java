package game.manager;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ImageManager extends Manager<Image> {
	private static final ImageManager manager = new ImageManager();
	private ImageManager() {}
	public static ImageManager getManager() {
		return manager;
	}
	@Override
	protected Image load(String fileName) {
		try {
			return ImageIO.read(new File(fileName));
		} catch (IOException e) {
			throw new RuntimeException("Imageロード失敗", e);
		}
	}
}
