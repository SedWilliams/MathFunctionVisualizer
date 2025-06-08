package com.gol;

import processing.core.PApplet;

/**
 * A class that handles the initial drawing and updating of a grid-based graph using Processing.
 * The graph is essentially a rectangular grid with customizable dimensions and intervals.
 */
public class Graph {
    private final PApplet processing;
    private float graphWidth;          // Width of the graph in pixels
    private float graphHeight;         // Height of the graph in pixels
    private float gridSpacingX;   // Horizontal spacing between grid lines
    private float gridSpacingY;   // Vertical spacing between grid lines
    private float positionX;      // X coordinate of the graph's top-left corner
    private float positionY;      // Y coordinate of the graph's top-left corner

    /**
     * Creates a new graph with the specified dimensions and properties.
     *
     * @param processing   The Processing PApplet instance for drawing
     * @param graphWidth       Width of the graph in pixels
     * @param graphHeight      Height of the graph in pixels
     * @param gridSpacingX Horizontal spacing between grid lines
     * @param gridSpacingY Vertical spacing between grid lines
     * @param positionX   X coordinate of the graph's top-left corner
     * @param positionY   Y coordinate of the graph's top-left corner
     * @throws IllegalArgumentException if any dimension is negative or zero
     * 
     * 
     * Regarding displaying of the graph:
     * The position of the graph is always translated to the middle of the canvas,
     * and then moved up and left by half of it's height and width.
     *  * The translation to the middle is done before each time a part of the graph is drawn.
     *  * The movement up and to the left is calculated in the constructor.
     * 
     * The draw methods can possibly be refactored by using a single method that draws both the
     * the border and the grid lines.
     */
    public Graph(PApplet processing, float width, float height, float gridSpacingX, float gridSpacingY, float positionX, float positionY) {
        validateGraph(width, height, gridSpacingX, gridSpacingY);
        
        this.processing = processing;
        this.graphWidth = width;
        this.graphHeight = height;
        this.gridSpacingX = gridSpacingX;
        this.gridSpacingY = gridSpacingY;
        //automatically centers the graph on the screen
        this.positionX = positionX - graphWidth/2;
        this.positionY = positionY - graphHeight/2;
    }

    /**
     * Draws the graph with its current properties.
     * This includes the outer rectangle border and the internal grid lines.
     */
    public void drawGraph() {
        drawBorder();
        drawGridLines();
    }

    /**
     * Updates the graph's properties and redraws it.
     *
     * @param graphWidth       New width of the graph
     * @param graphHeight      New height of the graph
     * @param gridSpacingX New horizontal grid spacing
     * @param gridSpacingY New vertical grid spacing
     * @param positionX   New X position
     * @param positionY   New Y position
     * @throws IllegalArgumentException if any dimension is negative or zero
     */
    public void updateGraph(float graphWidth, float graphHeight, float gridSpacingX, float gridSpacingY, float positionX, float positionY) {
        validateGraph(graphWidth, graphHeight, gridSpacingX, gridSpacingY);
        
        this.graphWidth = graphWidth;
        this.graphHeight = graphHeight;
        this.gridSpacingX = gridSpacingX;
        this.gridSpacingY = gridSpacingY;
        this.positionX = positionX - graphWidth/2;
        this.positionY = positionY - graphHeight/2;

        drawGraph();
    }

    /*
     * Validates that all dimensions are positive numbers.
     */
    private void validateGraph(float width, float height, float gridSpacingX, float gridSpacingY) {
        if (gridSpacingX <= 0 || gridSpacingY <= 0) {
            throw new IllegalArgumentException("You cannot have negative or zero intervals.");
        } else if ((graphWidth % gridSpacingX != 0) || (graphHeight % gridSpacingY != 0)) {
            throw new IllegalArgumentException("The graph width and height must be divisible by the grid spacing. \nFeature to support disproportional intervals in relation to min and \nmax height coming soon.\n");
        }
    }

    /*
     * draws the outer border of the graph.
     */
    private void drawBorder() {
        processing.stroke(255);
        processing.strokeWeight(4.5f);
        processing.fill(0, 0);

        processing.pushMatrix();
        processing.translate(processing.width/2, processing.height/2);
        processing.rect(positionX, positionY, graphWidth, graphHeight, 0);
        processing.strokeWeight(1f);
        processing.popMatrix();
    }

    /*
     * draws the internal grid lines.
     */
    private void drawGridLines() {
        processing.fill(190, 255); // Set fill for grid lines
        processing.pushMatrix();
        processing.translate(processing.width/2, processing.height/2);

        // draw vertical grid lines
        for(float i = positionY + gridSpacingX; i < positionY + graphHeight; i += gridSpacingX) {
            processing.line(positionX, i, positionX + graphWidth, i);
        }

        // draw horizontal grid lines
        for(float j = positionX + gridSpacingY; j < positionX + graphWidth; j += gridSpacingY) {
            processing.line(j, positionY, j, positionY + graphHeight); // Fixed coordinates for horizontal lines
        }
        
        processing.popMatrix();
    }

    // Getters for graph properties
    public float getGraphWidth() { return graphWidth; }
    public float getGraphHeight() { return graphHeight; }
    public float getGridSpacingX() { return gridSpacingX; }
    public float getGridSpacingY() { return gridSpacingY; }
    public float getPositionX() { return positionX; }
    public float getPositionY() { return positionY; }
} 