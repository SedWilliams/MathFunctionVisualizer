package com.gol;

import processing.core.PApplet;

/**
 * A class that handles the initial drawing and updating of a grid-based graph using Processing.
 * The graph is essentially a rectangular grid with customizable dimensions and intervals.
 */
public class Graph {
    private final PApplet processing;
    private float width;          // Width of the graph in pixels
    private float height;         // Height of the graph in pixels
    private float gridSpacingX;   // Horizontal spacing between grid lines
    private float gridSpacingY;   // Vertical spacing between grid lines
    private float positionX;      // X coordinate of the graph's top-left corner
    private float positionY;      // Y coordinate of the graph's top-left corner

    /**
     * Creates a new graph with the specified dimensions and properties.
     *
     * @param processing   The Processing PApplet instance for drawing
     * @param width       Width of the graph in pixels
     * @param height      Height of the graph in pixels
     * @param gridSpacingX Horizontal spacing between grid lines
     * @param gridSpacingY Vertical spacing between grid lines
     * @param positionX   X coordinate of the graph's top-left corner
     * @param positionY   Y coordinate of the graph's top-left corner
     * @throws IllegalArgumentException if any dimension is negative or zero
     */
    public Graph(PApplet processing, float width, float height, float gridSpacingX, float gridSpacingY, float positionX, float positionY) {
        validateDimensions(width, height, gridSpacingX, gridSpacingY);
        
        this.processing = processing;
        this.width = width;
        this.height = height;
        this.gridSpacingX = gridSpacingX;
        this.gridSpacingY = gridSpacingY;
        this.positionX = positionX;
        this.positionY = positionY;
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
     * @param width       New width of the graph
     * @param height      New height of the graph
     * @param gridSpacingX New horizontal grid spacing
     * @param gridSpacingY New vertical grid spacing
     * @param positionX   New X position
     * @param positionY   New Y position
     * @throws IllegalArgumentException if any dimension is negative or zero
     */
    public void updateGraph(float width, float height, float gridSpacingX, float gridSpacingY, float positionX, float positionY) {
        validateDimensions(width, height, gridSpacingX, gridSpacingY);
        
        this.width = width;
        this.height = height;
        this.gridSpacingX = gridSpacingX;
        this.gridSpacingY = gridSpacingY;
        this.positionX = positionX;
        this.positionY = positionY;

        drawGraph();
    }

    /*
     * Validates that all dimensions are positive numbers.
     */
    private void validateDimensions(float width, float height, float gridSpacingX, float gridSpacingY) {
        if (width <= 0 || height <= 0 || gridSpacingX <= 0 || gridSpacingY <= 0) {
            throw new IllegalArgumentException("All dimensions must be positive numbers");
        }
    }

    /*
     * draws the outer border of the graph.
     */
    private void drawBorder() {
        processing.stroke(255);
        processing.fill(0, 0);
        processing.rect(positionX, positionY, width, height);
    }

    /*
     * draws the internal grid lines.
     */
    private void drawGridLines() {
        processing.fill(190, 255); // Set fill for grid lines

        // draw vertical grid lines
        for (float x = positionX + gridSpacingX; x < positionX + width; x += gridSpacingX) {
            processing.line(x, positionY, x, positionY + height);
        }

        // draw horizontal grid lines
        for (float y = positionY + gridSpacingY; y < positionY + height; y += gridSpacingY) {
            processing.line(positionX, y, positionX + width, y);
        }
    }

    // Getters for graph properties
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getGridSpacingX() { return gridSpacingX; }
    public float getGridSpacingY() { return gridSpacingY; }
    public float getPositionX() { return positionX; }
    public float getPositionY() { return positionY; }
} 