package com.gol;

import processing.core.PApplet;

/**
 * A class that handles the drawing and updating of a pannable coordinate grid system using Processing.
 * The graph consists of:
 * - A fixed border that defines the visible area
 * - Infinite grid lines that can be panned within the border
 * - Customizable dimensions and grid spacing
 * 
 * The coordinate system uses screen pixels for all measurements, with (0,0) at the center.
 * The grid can be panned infinitely within the fixed border using offset coordinates.
 */
public class Graph {
    private final PApplet processing;
    private float graphWidth;          // Width of the graph in pixels
    private float graphHeight;         // Height of the graph in pixels
    private float gridSpacingX;        // Horizontal spacing between grid lines
    private float gridSpacingY;        // Vertical spacing between grid lines
    private float offsetX;             // X offset for panning
    private float offsetY;             // Y offset for panning
    private final float borderX;       // Fixed border X position
    private final float borderY;       // Fixed border Y position
    private float MAX_WIDTH;           // Max width for grid line extension
    private float MAX_HEIGHT;          // Max height for grid line extension

    /**
     * Creates a new graph with the specified dimensions and properties.
     *
     * @param processing    The Processing PApplet instance for drawing
     * @param width        Width of the graph in pixels
     * @param height       Height of the graph in pixels
     * @param gridSpacingX Horizontal spacing between grid lines
     * @param gridSpacingY Vertical spacing between grid lines
     * @param offsetX      Initial X offset for panning
     * @param offsetY      Initial Y offset for panning
     * @throws IllegalArgumentException if any spacing value is negative or zero
     */
    public Graph(PApplet processing, float width, float height, float gridSpacingX, float gridSpacingY, float offsetX, float offsetY) {
        validateGraph(width, height, gridSpacingX, gridSpacingY);
        
        this.processing = processing;
        this.graphWidth = width;
        this.graphHeight = height;
        this.gridSpacingX = gridSpacingX;
        this.gridSpacingY = gridSpacingY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        // Set border position once and keep it fixed
        this.borderX = processing.width/2 - graphWidth/2;
        this.borderY = processing.height/2 - graphHeight/2;
        this.MAX_WIDTH = processing.width * 10;
        this.MAX_HEIGHT = processing.height * 10;

        drawGraph();
    }

    /**
     * Updates the graph's properties and redraws it.
     *
     * @param graphWidth       New width of the graph
     * @param graphHeight      New height of the graph
     * @param gridSpacingX New horizontal grid spacing
     * @param gridSpacingY New vertical grid spacing
     * @param offsetX   New offset X position
     * @param offsetY   New offset Y position
     * @throws IllegalArgumentException if any dimension is negative or zero
     */
    public void updateGraph(float graphWidth, float graphHeight, float gridSpacingX, float gridSpacingY, float offsetX, float offsetY) {
        validateGraph(graphWidth, graphHeight, gridSpacingX, gridSpacingY);
        
        this.graphWidth = graphWidth;
        this.graphHeight = graphHeight;
        this.gridSpacingX = gridSpacingX;
        this.gridSpacingY = gridSpacingY;
        this.offsetX = offsetX;  // Use the offset directly for grid movement
        this.offsetY = offsetY;
        // Don't update borderX/Y here - keep them fixed

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
     * draws the graph.
     */
    public void drawGraph() {
        // Create clipping region using the fixed border position
        processing.clip(borderX, borderY, graphWidth, graphHeight);
        
        // Draw infinite grid lines with offset
        processing.stroke(190, 255);
        processing.strokeWeight(1f);
        
        // Draw vertical grid lines (extend well beyond visible area)
        for(float i = -MAX_WIDTH; i <= MAX_WIDTH; i += gridSpacingX) {
            float x = i + offsetX;
            processing.line(x, -MAX_HEIGHT, x, MAX_HEIGHT);
        }

        // Draw horizontal grid lines (extend well beyond visible area)
        for(float j = -MAX_HEIGHT; j <= MAX_HEIGHT; j += gridSpacingY) {
            float y = j + offsetY;
            processing.line(-MAX_WIDTH, y, MAX_WIDTH, y);
        }

        // Draw the fixed border on top
        processing.noClip();
        processing.stroke(255);
        processing.strokeWeight(4.5f);
        processing.noFill();
        processing.rect(borderX, borderY, graphWidth, graphHeight, 0);
    }

    // Getters for graph properties
    public float getGraphWidth() { return graphWidth; }
    public float getGraphHeight() { return graphHeight; }
    public float getGridSpacingX() { return gridSpacingX; }
    public float getGridSpacingY() { return gridSpacingY; }
    public float getOffsetX() { return offsetX; }
    public float getOffsetY() { return offsetY; }
} 