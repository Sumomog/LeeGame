package game.util;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class Loader {
	public static void load(String fileName, Map<String, Image> data) {
		String name = getFileName(fileName);
		if (name != null && "".equals(name) && data.containsKey(name)) {
			return;
		}
		try {
			data.put(name, ImageIO.read(new File(fileName)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	//	public void load(String fileName, Map<String, MapData[][]> data) {
	//		String name = getFileName(fileName);
	//		if (name != null && "".equals(name) && data.containsKey(name)) {
	//			return;
	//		}
	//		try {
	//			data.put(name, ImageIO.read(new File(fileName)));
	//		} catch (IOException e) {
	//			throw new RuntimeException(e);
	//		}
	//	}
	public static <T> void load(String fileName, Map<String, T[][]> data, Class<T> type) {
		String name = getFileName(fileName);
		List<List<T>> ll = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
			for (String line; (line = br.readLine()) != null;) {
				List<T> l = new ArrayList<>();
				for (String s : line.split(",")) {

					l.add(ObjectText.toObject(s, type));
				}
				ll.add(l);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		@SuppressWarnings("unchecked")
		T[][] d = (T[][]) new Object[ll.size()][ll.get(0).size()];
		for (int i = 0; i < ll.size(); i++) {
			for (int j = 0; j < ll.size(); j++) {
				d[i][j] = ll.get(i).get(j);
			}
		}
		data.put(name, d);
	}

	public static String getFileName(String fileName) {
		File f = new File(fileName);
		return f.isFile() ? f.getName() : "";
	}

	public static String load(String fileName) {
		try {
			return new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
