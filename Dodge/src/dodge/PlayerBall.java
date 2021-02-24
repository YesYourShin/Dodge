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
		// 이동 중 화면 바깥으로 나가지 못하도록 벽에 닿았을 때 바깥 방향으로 향하는 스피드 0 지정
		// 화면 바깥으로 나갔을 경우 화면 안으로 이동
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
		// 스피드를 추가해서 발라지게
		// 스피드의 값이 0 미만일 경우 추가 스피드를 -로 변환 후 스피드에 더한 후 다시 +로 변환
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
