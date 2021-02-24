package dodge;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

public class Ball {
	GameBoard game;
	Color color;
	
	static final int RADIUS = 5;
	int x = 0;
	int y = 0;
	int xSpeed = 0;
	int ySpeed = 0;
	public static int xAddSpeed = 0;
	public static int yAddSpeed = 0;
	
	public Ball(GameBoard game, Color color, int x, int y) {
		this.game = game;
		this.color = color;
		this.x = x;
		this.y = y;
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x, y,  2 * RADIUS,  2 * RADIUS);
	}
}
