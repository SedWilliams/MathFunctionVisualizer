package com.desmosclone.main;
import com.desmosclone.graph.Graph;

import processing.core.PApplet;

public class Main extends PApplet {

    Graph mainGraph;
    float x;
    float y;
    final float FRAME_RATE = 400;
    final float BACKGROUND_COLOR = 0;
    
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
        surface.setTitle("Function Visualizer");
        surface.setResizable(true);
        surface.setLocation(100,50);
        background(BACKGROUND_COLOR);
        frameRate(FRAME_RATE);

        x = 0;  // Start at origin
        y = 0;

        // Instantiate graph after Processing is initialized
        mainGraph = new Graph(this, 500f, 500f, 50f, 50f);
    }

    public void draw() {
        
        //logic behind graph panning
        if (isDragging) {
            float dx = mouseX - lastMouseX;
            float dy = mouseY - lastMouseY;
            panOffsetX += dx;
            panOffsetY += dy;
            lastMouseX = mouseX;
            lastMouseY = mouseY;
            // background(backgroundColor);
            //mainGraph.updateGraph(500f, 500f, 50f, 50f, panOffsetX, panOffsetY);
        }

        //FunctionDisplay functionDisplay = new FunctionDisplay(this);
        
        if (mainGraph != null) {
            mainGraph.draw();
        }
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
        PApplet.main("Main");
    }
} 
