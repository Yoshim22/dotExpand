import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class dotExpand extends PApplet {

int dotNum = 70;
Dot[] dots = new Dot[dotNum];

public void setup(){
  
  noStroke();
  
  for(int i =0;i<dots.length;i++){
    float x = random(width);
    float y = random(height);
    float diameter = 0;
    dots[i] = new Dot(x,y,diameter);
    dots[i].diameterMax = random(10,60);
    dots[i].startFrame = (int)random(10,300);
  }
}

public void draw(){
  fill(255);
  rect(0,0,width,height);
  for(int i = 0;i<dots.length;i++){
    fill(i*20%255,i*30%255,i*40%255,220);
    dots[i].biggerDia();
    dots[i].display();
  }
}

class Dot{
  float x;
  float y;
  float diameter;
  float expSpeed;
  float diameterMax;
  int startFrame;
  int intervalFrame = 400;
  Dot(float xPos, float yPos, float dia){
    x = xPos;
    y = yPos;
    diameter = dia;
  }
  
  public void biggerDia(){
    if((frameCount > startFrame)&&
       (frameCount < startFrame + intervalFrame)){
      float count = (float)frameCount - (float)startFrame;
      diameter = diameterMax*(log(count+1))*(1+10/(count+1)*sin(count/16));
    }
  }
  
  public void display(){
    ellipse(x,y,diameter,diameter);
  }
}
  public void settings() {  size(1600,900);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "dotExpand" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
