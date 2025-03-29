import processing.core.*;

public class graph extends PApplet{

    PApplet p;
    private float graphWidth;
    private float graphHeight;
    private float graphIntervalX;
    private float graphIntervalY;
    private float graphLocX;
    private float graphLocY;

    public graph(PApplet p, float graphWidth, float graphHeight, float graphIntervalX, float graphIntervalY, float graphLocX, float graphLocY) {
        this.p = p;
        this.graphWidth = graphWidth;
        this.graphHeight = graphHeight;
        this.graphIntervalX = graphIntervalX;
        this.graphIntervalY = graphIntervalY;
        this.graphLocX = graphLocX;
        this.graphLocY = graphLocY;

        drawGraph();
    }

    void drawGraph() {
        p.stroke(255);
        p.fill(0);

        p.rect(this.graphLocX, this.graphLocY, this.graphWidth, this.graphHeight);

        //draw y lines
        for(float i = this.graphLocX + this.graphIntervalX; i < this.graphLocX + this.graphWidth; i+=this.graphIntervalX) {
            p.noStroke();
            p.fill(190, 190);
            p.line(i, this.graphLocY, i, this.graphLocY+this.graphHeight);
        }

        //draw x lines
        for(float i = this.graphLocY + this.graphIntervalY; i < this.graphLocY + this.graphHeight; i+=this.graphIntervalY) {
            p.noStroke();
            p.fill(190, 190);
            p.line(this.graphLocX, i, this.graphLocX+this.graphWidth, i);
        }
    }

    public void updateGraph(float graphWidth, float graphHeight, float graphIntervalX, float graphIntervalY, float graphLocX, float graphLocY) {
        this.graphWidth = graphWidth;
        this.graphHeight = graphHeight;
        this.graphIntervalX = graphIntervalX;
        this.graphIntervalY = graphIntervalY;
        this.graphLocX = graphLocX;
        this.graphLocY = graphLocY;

        drawGraph();
    }

    public void getOrigin() {
    }
}
