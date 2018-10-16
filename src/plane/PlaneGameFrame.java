package plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import util.Constant;
import util.GameUtil;
import util.MyFrame;

public class PlaneGameFrame extends MyFrame{
	Image bg = GameUtil.getImage("src/images/bg.jpg");
	
	Plane p = new Plane("src/images/plane.png",50,50);
	
	ArrayList bulletList = new ArrayList();
	
	
	
	Date startTime;
	Date endTime;
	Explode bao;
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		p.draw(g);
		
		for(int i=0;i<bulletList.size();i++) {
			Bullet b = (Bullet) bulletList.get(i);
			b.draw(g);
			
			boolean peng=b.getRect().intersects(p.getRect());
			
			if(peng) {
				p.setLive(false);
				
				if(bao==null){
					endTime = new Date();
					bao = new Explode(p.x,p.y);
				}
				bao.draw(g);
				break;
			}
			
		}
		
		if(!p.isLive()) {
			printInfo(g, "GAME OVER", 50,250,300,Color.white);
			int period =(int) (endTime.getTime()-startTime.getTime())/1000;
			printInfo(g, "时间"+period+"秒", 20, 120, 260, Color.YELLOW);
			
			switch (period/10) {
			case 0:
				printInfo(g, "菜鸡", 50, 100, 200, Color.YELLOW);
				break;
			case 1:
				printInfo(g, "大菜鸡", 50, 100, 200, Color.YELLOW);
				break;
			case 2:
				printInfo(g, "大菜鸡", 50, 100, 200, Color.YELLOW);
				break;
			case 3:
				printInfo(g, "大菜鸡", 50, 100, 200, Color.YELLOW);
				break;
			case 4:
				printInfo(g, "大菜鸡", 50, 100, 200, Color.YELLOW);
				break;

			default:
				break;
			}
		}
		
		
	} 
	
	public void printInfo(Graphics g,String str,int size,int x,int y,Color color) {
		Color c = g.getColor();
		g.setColor(color);
		Font f = new Font("宋体",Font.BOLD,size);
		g.setFont(f);
		g.drawString(str, (int)x, (int)y);
		g.setColor(c);
	}
	public static void main(String[] args) {
		new PlaneGameFrame().launchFrame();
	}
	
	public void launchFrame() {
		super.launchFrame();
		addKeyListener(new KeyMonitor());
		
		for(int i=0;i<30;i++) {
			Bullet b = new Bullet();
			bulletList.add(b);
		}
		
		startTime = new Date();
	}
	
	//定义为内部类可以直接使用外部类的属性
	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent arg0) {
			//System.out.println("按下:"+ arg0.getKeyCode());
			p.addDirection(arg0);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			//System.out.println("释放:"+arg0.getKeyCode());
			p.minusDiretion(arg0); 
		}
		
	}
}
