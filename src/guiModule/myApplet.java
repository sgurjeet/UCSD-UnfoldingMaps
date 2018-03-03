package guiModule;

import processing.core.*;

public class myApplet extends PApplet{
	PImage img;
	public void setup(){
		size(400,400);
		img =loadImage("http://www.hotelroomsearch.net/im/hotels/it/sun-beach-12.jpg","jpg");
		img.resize(0,height);
		image(img,0,0);

	}
	public void draw(){
		int[] col=getColor(second());
		fill(col[0],col[1],col[2]);
		ellipse(3*width/4,height/5,50,50);
	}
	public int[] getColor(int s){
		int[] col=new int[3];
		float diff=Math.abs(30-s);
		float ratio=diff/30;
		col[0]=(int)(255*ratio);
		col[1]=(int)(255*ratio);
		col[2]=0;
		return col;
	}
}
