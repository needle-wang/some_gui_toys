import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author needle
 * 2014年 04月 29日 星期二 15:30:02 CST
 * 大学时的小玩意儿，现在加个快捷键，禁用最大化。
 */
class Clock2 extends JPanel {
	private static final long serialVersionUID = 1L;
	private Timer t;
	private int x, y, r, month, day;
	private Calendar c;
	private double angelx, angely, angelz;
	private boolean sun;

	public Clock2(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
		c = Calendar.getInstance();
		month = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH);
		angelx = 6 * c.get(Calendar.SECOND);
		angely = 6 * c.get(Calendar.MINUTE) + 0.1 * (c.get(Calendar.SECOND));
		angelz = 30 * c.get(Calendar.HOUR_OF_DAY) + 1.0 / 120
				* (c.get(Calendar.MINUTE) * 60 + c.get(Calendar.SECOND));
		if (c.get(Calendar.HOUR_OF_DAY) > 6 && c.get(Calendar.HOUR_OF_DAY) < 18) {
			sun = true;
		} else {
			sun = false;
		}
		t = new Timer();
		t.schedule(new ClockTask(), 1000, 1000);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillOval(x - 20, y - 20, 2 * (r + 20), 2 * (r + 20));
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x, y, 2 * r, 2 * r);
		g.setColor(Color.GRAY);
		g.drawOval(x, y, 2 * r, 2 * r);

		g.setColor(Color.BLACK);
		g.drawLine(x + r, y + r, x + r, y + r);
		// Font f = new Font("华文隶书", Font.ITALIC, 20);
		Font f = new Font("微软雅黑", Font.ITALIC, 20);
		g.setFont(f);
		g.drawString("Made by", x + r - 40, y + 90);
		g.drawString("needle wang", x + 140, y + 110);
		if (sun) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillOval(x + r - 10, y + 120, 20, 20);

		} else {
			g.setColor(Color.BLACK);
			g.fillOval(x + r - 10, y + 120, 20, 20);
			g.setColor(Color.DARK_GRAY);
			g.fillOval(x + r - 10, y + 120, 15, 15);
		}

		// 数字
		for (int i = 1; i <= 12; i++) {
			g.setColor(Color.GRAY);
			g.drawString(
					String.valueOf(i),
					x
							+ 32
							+ (int) ((r - 35) * (1 + Math.cos((90 - i * 30)
									* Math.PI / 180))),
					y
							+ 47
							+ (int) ((r - 35) * (1 - Math.sin((90 - i * 30)
									* Math.PI / 180))));
			g.setColor(Color.BLACK);
			g.drawString(
					String.valueOf(i),
					x
							+ 30
							+ (int) ((r - 35) * (1 + Math.cos((90 - i * 30)
									* Math.PI / 180))),
					y
							+ 45
							+ (int) ((r - 35) * (1 - Math.sin((90 - i * 30)
									* Math.PI / 180))));
		}
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x + r + 60, y + r - 11, 90, 20);
		g.setColor(Color.BLACK);
		g.drawRect(x + r + 60, y + r - 11, 90, 20);
		g.drawString(month + "月" + day + "日", x + r + 65, y + r + 6);
		g.drawString("12", x + r - 10, y + 40);
		// 框架
		// for (int a = 1; a < 3; a++) {
		for (int i = 0; i < 60; i++) {
			int r1 = r - 10;
			int x1 = x + 10;
			int y1 = y + 10;
			g.setColor(Color.BLACK);
			g.drawLine(x + (int) (r * (1 - Math.sin(i * 6 * Math.PI / 180))), y
					+ r - (int) (r * Math.cos(i * 6 * Math.PI / 180)), x1
					+ (int) (r1 * (1 - Math.sin(i * 6 * Math.PI / 180))), y1
					+ r1 - (int) (r1 * Math.cos(i * 6 * Math.PI / 180)));
			int r2 = r - 20;
			int x2 = x + 20;
			int y2 = y + 20;
			g.setColor(Color.BLACK);
			g.drawLine(
					x
							+ (int) (r * (1 - Math.sin((i / 5) * 30 * Math.PI
									/ 180))),
					y + r - (int) (r * Math.cos((i / 5) * 30 * Math.PI / 180)),
					x2
							+ (int) (r2 * (1 - Math.sin((i / 5) * 30 * Math.PI
									/ 180))),
					y2
							+ r2
							- (int) (r2 * Math
									.cos((i / 5) * 30 * Math.PI / 180)));
			// 圆心
			g.fillOval(x + r - 5, y + r - 5, 10, 10);
			// 指针圆
			// 秒针
			g.fillOval(
					x + r - 5 + (int) (170 * Math.sin(angelx * Math.PI / 180)),
					y + r - 5 - (int) (170 * Math.cos(angelx * Math.PI / 180)),
					10, 10);
			// 分针
			g.fillOval(x + r - 5
					+ (int) (40 * Math.sin(angely * Math.PI / 180)), y + r - 5
					- (int) (40 * Math.cos(angely * Math.PI / 180)), 10, 10);
			g.drawLine(
					x + r,
					y + r,
					x
							+ r
							+ (int) (40 * Math.sin((7.5 + angely) * Math.PI
									/ 180)),
					y
							+ r
							- (int) (40 * Math.cos((7.5 + angely) * Math.PI
									/ 180)));
			g.drawLine(
					x
							+ r
							+ (int) (40 * Math.sin((7.5 + angely) * Math.PI
									/ 180)),
					y
							+ r
							- (int) (40 * Math.cos((7.5 + angely) * Math.PI
									/ 180)),
					x + r + (int) (140 * Math.sin(angely * Math.PI / 180)), y
							+ r
							- (int) (140 * Math.cos(angely * Math.PI / 180)));
			g.drawLine(
					x + r,
					y + r,
					x
							+ r
							+ (int) (40 * Math.sin((-7.5 + angely) * Math.PI
									/ 180)),
					y
							+ r
							- (int) (40 * Math.cos((-7.5 + angely) * Math.PI
									/ 180)));
			g.drawLine(
					x
							+ r
							+ (int) (40 * Math.sin((-7.5 + angely) * Math.PI
									/ 180)),
					y
							+ r
							- (int) (40 * Math.cos((-7.5 + angely) * Math.PI
									/ 180)),
					x + r + (int) (140 * Math.sin(angely * Math.PI / 180)), y
							+ r
							- (int) (140 * Math.cos(angely * Math.PI / 180)));
			// 时针
			g.fillOval(x + r - 7
					+ (int) (25 * Math.sin(angelz * Math.PI / 180)), y + r - 7
					- (int) (25 * Math.cos(angelz * Math.PI / 180)), 15, 15);
			g.drawLine(
					x + r,
					y + r,
					x
							+ r
							+ (int) (25 * Math.sin((14 + angelz) * Math.PI
									/ 180)),
					y
							+ r
							- (int) (25 * Math.cos((14 + angelz) * Math.PI
									/ 180)));
			g.drawLine(
					x
							+ r
							+ (int) (26 * Math.sin((15 + angelz) * Math.PI
									/ 180)),
					y
							+ r
							- (int) (26 * Math.cos((15 + angelz) * Math.PI
									/ 180)),
					x + r + (int) (100 * Math.sin(angelz * Math.PI / 180)), y
							+ r
							- (int) (100 * Math.cos(angelz * Math.PI / 180)));
			g.drawLine(
					x + r,
					y + r,
					x
							+ r
							+ (int) (25 * Math.sin((-14 + angelz) * Math.PI
									/ 180)),
					y
							+ r
							- (int) (25 * Math.cos((-14 + angelz) * Math.PI
									/ 180)));
			g.drawLine(
					x
							+ r
							+ (int) (26 * Math.sin((-15 + angelz) * Math.PI
									/ 180)),
					y
							+ r
							- (int) (26 * Math.cos((-15 + angelz) * Math.PI
									/ 180)),
					x + r + (int) (100 * Math.sin(angelz * Math.PI / 180)), y
							+ r
							- (int) (100 * Math.cos(angelz * Math.PI / 180)));

		}
		// }
		// 指针
		g.setColor(Color.BLACK);
		g.drawLine(x + r, y + r,
				x + r + (int) (100 * Math.sin(angelz * Math.PI / 180)), y + r
						- (int) (100 * Math.cos(angelz * Math.PI / 180)));
		g.setColor(Color.BLACK);
		g.drawLine(x + r, y + r,
				x + r + (int) (140 * Math.sin(angely * Math.PI / 180)), y + r
						- (int) (140 * Math.cos(angely * Math.PI / 180)));
		// g.setColor(Color.BLACK);
		g.drawLine(x + r, y + r,
				x + r + (int) (170 * Math.sin(angelx * Math.PI / 180)), y + r
						- (int) (170 * Math.cos(angelx * Math.PI / 180)));

	}

	private class ClockTask extends TimerTask {
		public void run() {
			if (angelx == 360) {
				angelx = 0;
			}
			angelx = angelx + 6;
			if (angely == 360) {
				angely = 0;
			}
			angely = angely + 0.1;
			if (angelz == 360) {
				angelz = 0;
			}// 没用，double型``````
			angelz = angelz + 1.0 / 120;
			c = Calendar.getInstance();
			month = c.get(Calendar.MONTH) + 1;
			day = c.get(Calendar.DAY_OF_MONTH);
			if (c.get(Calendar.HOUR_OF_DAY) == 6) {
				sun = true;
			}
			if (c.get(Calendar.HOUR_OF_DAY) == 18) {
				sun = false;
			}

			repaint();
		}
	}

}

public class DynamicClock {
	public static void main(String[] args) {
		final JFrame f = new JFrame("DynamicClock");
		Clock2 c = new Clock2(50, 50, 200);
		f.add(c);
		f.setSize(500, 520);
		DynamicClock.setRight(f);
		// f.setLocation(280, 160);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setAlwaysOnTop(true);
		f.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == 'q') {
					System.exit(0);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		f.setVisible(true);
	}

	private static void setRight(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width),
				(screenSize.height - frameSize.height) / 2);
	}

}
