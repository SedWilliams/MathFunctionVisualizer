package com.gol;

import processing.core.PApplet;

public class Main extends PApplet {

    Graph mainGraph;
    float x;
    float y;
    final float frameRate = 400;
    final float backgroundColor = 0;
    
    // Variables for panning
    private boolean isDragging = false;
    private float lastMouseX;
    private float lastMouseY;
    private float panOffsetX = 0;
    private float panOffsetY = 0;

    public void settings() {
        size(700, 800);
    } 

    public void setup() {
        surface.setTitle("Graphing Calculator");
        surface.setResizable(true);
        surface.setLocation(100,50);
        background(backgroundColor);
        frameRate(frameRate);

        mainGraph = new Graph(this, 500f, 500f, 50f, 50f, panOffsetX, panOffsetY);
        x = 0;  // Start at origin
        y = 0;
    }

    public void draw() {
        background(backgroundColor);
        
        //logic behind graph panning
        if (isDragging) {
            float dx = mouseX - lastMouseX;
            float dy = mouseY - lastMouseY;
            panOffsetX += dx;
            panOffsetY += dy;
            lastMouseX = mouseX;
            lastMouseY = mouseY;
            mainGraph.updateGraph(500f, 500f, 50f, 50f, panOffsetX, panOffsetY);
        }
        
        mainGraph.drawGraph();

        // Draw function
        pushMatrix();
        translate(width/2, height/2);  // Match graph's coordinate system
        noStroke();
        fill(0, 150, 0);
        x += 0.02f;
        y = -1*((1.0f/60.0f)*x*x);
        float graphX = x + panOffsetX;
        float graphY = y + panOffsetY;
        ellipse(graphX, graphY, 2.5f, 2.5f);
        popMatrix();
    }

    //update 'isDragging' to true when mouse is pressed
    public void mousePressed() {
        isDragging = true;
        lastMouseX = mouseX;
        lastMouseY = mouseY;
    }

    //update 'isDragging' to false when mouse is released
    public void mouseReleased() {
        isDragging = false;
    }

    public static void main(String[] args) {
        PApplet.main("com.gol.Main");
    }
} 