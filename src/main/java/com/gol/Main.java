package com.gol;

import processing.core.PApplet;

public class Main extends PApplet {

    Graph mainGraph;
    float x;
    float y;
    final float frameRate = 400;
    final float backgroundColor = 0;

    public void settings() {
        size(700, 800);
    } 

   public void setup() {
        background(backgroundColor);
        frameRate(frameRate);

        mainGraph = new Graph(this, 500f, 500f, 50f, 50f, 0f, 0f);
        mainGraph.drawGraph();
        x = width/2;
        y = height/2;
   }

    public void draw() {
        noStroke();
        
        fill(0, 150, 0);

        //function drawn
        x+=0.02f;
        y = -1*((1.0f/60.0f)*x*x);

        ellipse(x, y, 2.5f, 2.5f);
    }

    public static void main(String[] args) {
        PApplet.main("com.gol.Main");
    }
} 