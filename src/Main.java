import processing.core.PApplet;

/*
TODO:
-return origin to start function from, from the mainGraph object
*/

public class Main extends PApplet {

    graph mainGraph;
    float x;
    float y;

    public void settings() {
        size(1400, 800);

    }

   public void setup() {
        background(0);

        //drawFunction();
        frameRate(200);


        scale(1.30f);
        translate(100, height-400);
        mainGraph = new graph(this, 500f, 500f, 50f, 50f, 0f, 0f - 500f);
        mainGraph.drawGraph();
        x = mainGraph.graphLocX;
        y = height;
        scale(1f);
        translate(-100, -1*(height-400));
   }

    public void draw() {
        noStroke();
        fill(0, 150, 0);
        scale(1.30f);
        translate(100, height-400);

        //function drawn
        x+=0.02f;
        y = -1*((1.0f/60.0f)*x*x);

        ellipse(x, y, 5, 5);

        scale(1f);
        translate(-100, -1*(height-400));
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
