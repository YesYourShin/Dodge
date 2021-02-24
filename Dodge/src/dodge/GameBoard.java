package dodge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements KeyListener {
	PlayerBall playerBall;
	EnemyBall enemyBall1, enemyBall2;
	Random random = new Random();
	ArrayList<EnemyBall> enemyList = new ArrayList<EnemyBall>();
	JFrame frame = new JFrame();
	
	int xSpawnEnemy = 0;
	int ySpawnEnemy = 0;
	int timer = 0;
	int situation = 0;
	int collision = 0;
	double playTime = 0;
	private JLabel label = new JLabel();
	
	public GameBoard() {
		playerBall = new PlayerBall(this, Color.WHITE, 686/2, 663/2);
		this.setBackground(Color.BLACK);
	}
	
	public void collision() {
		for (int i = 0; i < enemyList.size(); i++) {
			int xd = (playerBall.x + playerBall.RADIUS) - (enemyList.get(i).x + enemyList.get(i).RADIUS); // 플레이어와 적의 x좌표 거리
			int yd = (playerBall.y + playerBall.RADIUS) - (enemyList.get(i).y + enemyList.get(i).RADIUS); // 플레이어와 적의 y좌표 거리
			int r = playerBall.RADIUS + enemyList.get(i).RADIUS - 2; // 돌판정 범위
			if (Math.abs(xd) <= r && Math.abs(yd) <= r) { // 만약 원의 거리가 충돌판정 범위보다 가까울 경우 
				collision = 1;
			}
		}
	}
	
	void startGame() {
		timer = 0;  
		collision = 0; 
		Ball.xAddSpeed = 0; 
		Ball.yAddSpeed = 0;
		enemyList.clear();
		if (situation == 0) {
			for (int i = 0; i < 50; i++) {
				xSpawnEnemy = random.nextInt(686 - 4 * Ball.RADIUS) + 2 * Ball.RADIUS;
				ySpawnEnemy = random.nextInt(663 - 4 * Ball.RADIUS) + 2 * Ball.RADIUS;
				enemyList.add(new EnemyBall(this, Color.RED, xSpawnEnemy, ySpawnEnemy));
			}
		}
		situation = 1;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		playerBall.keyReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (situation) {
		case 0:
			if (e.getKeyCode() == 32) {
				startGame();
			}
			break;
		case 1:
			playerBall.keyPressed(e);
			break;
		case 2:
			if (e.getKeyCode() == 32) {
				situation = 0;
				playTime = 0;
			}
			break;
		}
	}

	private void update() {
		label.setText(String.valueOf(Math.floor(playTime*100)/100.0));
		label.setForeground(Color.WHITE);
		this.add(label);
		if (situation == 0) {
			playerBall.x = 686/2;
			playerBall.y = 663/2;
		}
		if (situation == 1) {
			playerBall.movePlayer();
			for (int i = 0; i < enemyList.size(); i++) {
				enemyList.get(i).moveEnemy();	
			}
			collision();
			if (collision == 1) {
				situation = 2;
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		playerBall.draw(g2d);
		if (situation > 0) {
			for (int i = 0; i < enemyList.size(); i++) {	
				enemyList.get(i).draw(g2d);
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("DodgeGame");
		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameBoard game = new GameBoard();
		System.out.println("paint");
		frame.add(game);
		frame.addKeyListener(game);
		
		while (true) {
			game.update();
			game.revalidate();
			game.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (game.situation == 1) {
				game.timer = game.timer + 1;	
			}
			// 스피드 증가
			if (game.timer == 500 || game.timer == 1000) {
				Ball.xAddSpeed = Ball.xAddSpeed + 1;
				Ball.yAddSpeed = Ball.yAddSpeed + 1;
			}
			if (game.timer % 1 == 0 && game.timer != 0) {
				if (game.situation == 1) {System.out.println("yes");
				game.playTime = game.playTime + 0.01;
				}
			}
		}
	}
}
