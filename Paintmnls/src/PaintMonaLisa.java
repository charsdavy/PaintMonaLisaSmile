/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * Java画蒙娜丽莎的微笑
 * @author dengw
 * @date   2012年12月19日12:32:54
 */
public class PaintMonaLisa extends JFrame {
	private int x, y, R, G, B, w, h;
	private Color color;
	private int count;
	private int i;
	private List<String> list = new ArrayList<String>();
	private BufferedImage img;
	private Graphics imgG;
	
	public PaintMonaLisa() {
		this.setSize(600, 450);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Mona Lisa's Smile");
		this.setLocationRelativeTo(null);
		img = new BufferedImage(600, 500,BufferedImage.TYPE_INT_ARGB);
		imgG = img.getGraphics();
	}

	@Override
	public void paint(Graphics g) {
		if (i < count){
			changeNextPoint(list.get(i));
		}
		g.drawImage(img, 0, 0, null);
	}

	public void readFileInfo(String path) {
		File file = new File(path);
		try {
			BufferedReader bw = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + path), "UTF-8")); 
			String line = null;
			while ((line = bw.readLine()) != null) {
				list.add(line);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		count = list.size();
	}

	private void changeNextPoint(String temp) {
		String[] s = temp.split("x");
		x = Integer.parseInt(s[0]);
		y = Integer.parseInt(s[1]);
		w = Integer.parseInt(s[2]);
		h = Integer.parseInt(s[3]);
		R = Integer.parseInt(s[4].substring(0, 2), 16);
		G = Integer.parseInt(s[4].substring(2, 4), 16);
		B = Integer.parseInt(s[4].substring(4, 6), 16);
		color = new Color(R, G, B);
		imgG.setColor(color);
		imgG.fillRect(x + 50, y + 50, w, h);
		repaint();
		i++;
		
	}

	public static void main(String[] args) {
		final PaintMonaLisa s = new PaintMonaLisa();
		s.readFileInfo("ml");
		s.open();
	}

	private void open() {
		this.setVisible(true);
	}

}
