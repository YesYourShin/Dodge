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
		// ���� ���ǵ� -1 �Ǵ� 1 �������� ����
		while(xSpeed == 0) {
			xSpeed = random.nextInt(3)-1;
		}
		while(ySpeed == 0) {
			ySpeed = random.nextInt(3)-1;
		}
		
		
		// �̵��� ȭ�� ������ ������ �ʵ��� ���ǵ� ����
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
		// ���ǵ带 �߰��ؼ� ��������
		// �߰� ���ǵ�� ���� �� ���ǵ尡 �ٸ� ��� ���� �� ���ǵ忡 �߰� ���ǵ��� ���� �ֱ�
		// ���ǵ��� ���� 0 �̸��� ��� ���� �� ���ǵ带 -�� ��ȯ �� ���ǵ忡 ���� �� �ٽ� +�� ��ȯ
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
		// ���ǵ��� ����ŭ �̵�
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
