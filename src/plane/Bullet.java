package plane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import util.Constant;

public class Bullet extends GameObject{
	
	double degree;
	
	
	public Bullet(){
		degree = Math.random()*Math.PI*2;
		x = util.Constant.GAME_WIDTH/2;
		y = util.Constant.GAME_HEIGHT/2;
		width = 10;
		height = 10;
	}
	
	public void draw(Graphics e) {
		Color c = e.getColor();
		e.setColor(Color.yellow);
		e.fillOval((int)x, (int)y, width, height);
		
		x += speed*Math.cos(degree);
		
		y += speed*Math.sin(degree);
		
		if(x<=0||x>Constant.GAME_WIDTH-width) degree = Math.PI - degree;
		if(y<30||y>Constant.GAME_HEIGHT-height) degree = -degree;
		e.setColor(c);
	}
}
