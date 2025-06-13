package com.gol;

import processing.core.PApplet;

public class FunctionDisplay {

    //import the processing framework as an applet the code 
    //of this class will be run through
    private PApplet processing;

    public FunctionDisplay(PApplet processing) {
        this.processing = processing;

        evaluateFunction();

    }

    //the generic type func is temp until wolfram api is implemented
    //the input type will conform to whatever type the wolfram api consumes
    private void evaluateFunction() {
        //implement wolfram alpha api

        // Draw function
        for(int i = 0; i < 100000000; i++) {
            float x = 0;
            float y = 0;
            processing.pushMatrix();
            processing.translate(processing.width/2, processing.height/2);  // Match graph's coordinate system
            processing.noStroke();
            processing.fill(0, 150, 0);
            x += 0.02f;
            y = -1*((1.0f/60.0f)*x*x);
            processing.ellipse(x, y, 2.5f, 2.5f);
            processing.popMatrix();
        }

    }

}
