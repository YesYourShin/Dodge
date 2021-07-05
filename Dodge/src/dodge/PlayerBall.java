package dodge;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class PlayerBall extends Ball {
	
	int xPlayerSpeed = 0;
	int yPlayerSpeed = 0;
	
	
	
	public PlayerBall(GameBoard game, Color color, int x, int y) {
		super(game, color, x, y);
	}

	void movePlayer() {
		// �̵� �� ȭ�� �ٱ����� ������ ���ϵ��� ���� ����� �� �ٱ� �������� ���ϴ� ���ǵ� 0 ����
		// ȭ�� �ٱ����� ������ ��� ȭ�� ������ �̵�
		if (x < 0) {
			xSpeed = 0;
			x = 0;
		}
		if (x > 686 - 2 * RADIUS) {
			xSpeed = 0;
			x = 686 - 2 * RADIUS;
		}
		if (y < 0) {
			ySpeed = 0;
			y = 0;
		}
		if (y > 663 - 2 * RADIUS) {
			ySpeed = 0;
			y = 663 - 2 * RADIUS;
		}
		// ���ǵ带 �߰��ؼ� �߶�����
		// ���ǵ��� ���� 0 �̸��� ��� �߰� ���ǵ带 -�� ��ȯ �� ���ǵ忡 ���� �� �ٽ� +�� ��ȯ
		if (xSpeed < 0) {
			xAddSpeed = xAddSpeed * -1;
			x = x + xSpeed + xAddSpeed;
			xAddSpeed = xAddSpeed * -1;
		} else if (xSpeed > 0) {
			x = x + xSpeed + xAddSpeed;
		}
		
		if (ySpeed < 0) {
			yAddSpeed = yAddSpeed * -1;
			y = y + ySpeed + yAddSpeed;
			yAddSpeed = yAddSpeed * -1;
		} else if (ySpeed > 0) {
			y = y + ySpeed + yAddSpeed;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				ySpeed = 0;
				break;
			case KeyEvent.VK_DOWN:
				ySpeed = 0;
				break;
			case KeyEvent.VK_LEFT:
				xSpeed = 0;
				break;
			case KeyEvent.VK_RIGHT:
				xSpeed = 0;
				break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				ySpeed = -1;
				break;
			case KeyEvent.VK_DOWN:
				ySpeed = 1;
				break;
			case KeyEvent.VK_LEFT:
				xSpeed = -1;
				break;
			case KeyEvent.VK_RIGHT:
				xSpeed = 1;
				break;
		}
	}
}
