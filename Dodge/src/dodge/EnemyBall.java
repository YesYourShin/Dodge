package dodge;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class EnemyBall extends Ball{
	Random random = new Random();

	int xEnemySpeed = 0;
	int yEnemySpeed = 0;
	
	public EnemyBall(GameBoard game, Color color, int x, int y) {
		super(game, color, x, y);
	}

	 public Rectangle getBounds() {
		return new Rectangle(x, y, 2 * RADIUS,  2 * RADIUS);
	}
	
	void moveEnemy() {	
		// 최초 스피드 -1 또는 1 랜덤으로 설정
		while(xSpeed == 0) {
			xSpeed = random.nextInt(3)-1;
		}
		while(ySpeed == 0) {
			ySpeed = random.nextInt(3)-1;
		}
		
		
		// 이동중 화면 밖으로 나가지 않도록 스피드 반전
		if (x + xSpeed < 0) {
			xSpeed = xSpeed * -1;
		}
		if (x + xSpeed > 686 - 2 * RADIUS) {

			xSpeed = xSpeed * -1;
		}
		if (y + ySpeed < 0) {
			ySpeed = ySpeed * -1;
		}
		if (y + ySpeed > 663 - 2 * RADIUS) {

			ySpeed = ySpeed * -1;
		}
		// 스피드를 추가해서 빨라지게
		// 추가 스피드와 적의 공 스피드가 다를 경우 적의 공 스피드에 추가 스피드의 값을 넣기
		// 스피드의 값이 0 미만일 경우 적의 공 스피드를 -로 변환 후 스피드에 더한 후 다시 +로 변환
		if (xAddSpeed != xEnemySpeed) {
			xEnemySpeed = xAddSpeed;
			if (xSpeed < 0) {
				xEnemySpeed = xEnemySpeed * -1;
				xSpeed = xSpeed + xEnemySpeed ;
				xEnemySpeed = xEnemySpeed * -1;
			} 
			else {
				xSpeed = xSpeed + xEnemySpeed;
			}
		}
		if (yAddSpeed != yEnemySpeed) {
			yEnemySpeed = yAddSpeed;
			if (ySpeed < 0) {
				yEnemySpeed = yEnemySpeed * -1;
				ySpeed = ySpeed + yEnemySpeed;
				yEnemySpeed = yEnemySpeed * -1;
			}
			else {
				ySpeed = ySpeed + yEnemySpeed;
			}
		}
		// 스피드의 값만큼 이동
		x = x + xSpeed;
		y = y + ySpeed;
		
//		System.out.println("xAddSpeed : " + xAddSpeed);
//		System.out.println("yAddSpeed : " + yAddSpeed);
//		System.out.println("xSpeed : " + xSpeed);
//		System.out.println("ySpeed : " + ySpeed);
//		System.out.println("timer" + game.timer);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x, y,  2 * RADIUS,  2 * RADIUS);
	}
}
