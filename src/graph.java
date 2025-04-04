import processing.core.PApplet;

public class graph {

    PApplet p;
    float graphWidth;
    float graphHeight;
    float graphIntervalX;
    float graphIntervalY;
    float graphLocX;
    float graphLocY;

    public graph(PApplet p, float graphWidth, float graphHeight, float graphIntervalX, float graphIntervalY, float graphLocX, float graphLocY) {
        this.p = p;
        this.graphWidth = graphWidth;
        this.graphHeight = graphHeight;
        this.graphIntervalX = graphIntervalX;
        this.graphIntervalY = graphIntervalY;
        this.graphLocX = graphLocX;
        this.graphLocY = graphLocY;
    }

    void drawGraph() {
        p.stroke(255);
        p.fill(0, 0);

        p.rect(this.graphLocX, this.graphLocY, this.graphWidth, this.graphHeight);

        //draw y lines
        for(float i = this.graphLocX + this.graphIntervalX; i < this.graphLocX + this.graphWidth; i+=this.graphIntervalX) {
            p.fill(190, 255);
            p.line(i, this.graphLocY, i, this.graphLocY+this.graphHeight);
        }

        //draw x lines
        for(float i = this.graphLocY + this.graphIntervalY; i < this.graphLocY + this.graphHeight; i+=this.graphIntervalY) {
            p.fill(190, 255);
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
