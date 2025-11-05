package com.desmosclone.graph;
import java.util.ArrayList;

import com.desmosclone.interfaces.Drawable;

import processing.core.PApplet;

public class Graph {

    /******************************************************************
     * all processing objects must reference the PApplet.
     * @see Main.java
    *******************************************************************/
    final PApplet processing;

    /******************************************************************
     * Properties of the graph
    *******************************************************************/

    /******************************************************************
     * Constants
    *******************************************************************/
    ArrayList<Float> graphPos; //position of the graph on the screen (pixels): x, y
    int MAX_WIDTH;
    int MAX_HEIGHT;

    /******************************************************************
     * MUTABLE
    *******************************************************************/
    ArrayList<Float> graphDimensions;        //dimensions of the graph (pixels): width, height
    ArrayList<Float> graphInterval;          //interval of the graph (pixels): width, height
    ArrayList<Float> graphDisplacement;      //displacement of the graph when moved (pixels): width, height

    /******************************************************************
     * Constructor(s)
    ******************************************************************/

    /***************************************************************************************
     * Basic constructor with all possible arguments
     * 
     * @param processing   The Processing PApplet instance for drawing
     * @param graphDimensions ArrayList (Float): width (pixels), height (pixels)
     * @param graphInterval ArrayList (Float): intervalX (pixels), intervalY (pixels)
     * @param graphDisplacement ArrayList (Float): x (pixels), y (pixels)
     * 
     * @throws IllegalArgumentException if any spacing value is negative or zero
     * @see validateGraph()
     ***************************************************************************************/
    public Graph(PApplet processing, float width, float height, float gridSpacingX, float gridSpacingY) {
        validateGraph(width, height, gridSpacingX, gridSpacingY);

        this.processing = processing;

        // Initialize collections before use
        this.graphDimensions = new ArrayList<>();
        this.graphInterval = new ArrayList<>();
        this.graphDisplacement = new ArrayList<>();
        this.graphPos = new ArrayList<>();

        this.graphDimensions.add(width);
        this.graphDimensions.add(height);

        this.graphInterval.add(gridSpacingX);
        this.graphInterval.add(gridSpacingY);

        // Set border position once and keep it fixed
        this.graphPos.add((processing.width / 2 - this.graphDimensions.get(0) / 2));
        this.graphPos.add((processing.height / 2 - this.graphDimensions.get(1) / 2));

        // Use correct dimensions for max extents
        this.MAX_WIDTH = processing.width * 10;
        this.MAX_HEIGHT = processing.height * 10;
    }

    /*******************************************************************
     * Methods
    ********************************************************************/
    private void validateGraph(float width, float height, float gridSpacingX, float gridSpacingY) {
        if (gridSpacingX <= 0 || gridSpacingY <= 0) {
            throw new IllegalArgumentException("You cannot have negative or zero intervals.");
        } else if ((height % gridSpacingX != 0) || (width % gridSpacingY != 0)) {
            throw new IllegalArgumentException("The graph width and height must be divisible by the grid spacing. \nFeature to support disproportional intervals in relation to min and \nmax height coming soon.\n");
        }
    }

    public void draw() {
        // Create clipping region using the fixed border position
        processing.clip(
            Math.round(graphPos.get(0)),
            Math.round(graphPos.get(1)),
            Math.round(graphDimensions.get(0)),
            Math.round(graphDimensions.get(1))
        );
        
        // Draw infinite grid lines with offset
        processing.stroke(190, 255);
        processing.strokeWeight(1f);
        
        // Draw vertical grid lines (extend well beyond visible area)
        for(float i = -MAX_WIDTH; i <= MAX_WIDTH; i += graphInterval.get(0)) {
            float x = i + graphInterval.get(0);
            processing.line(x, -MAX_HEIGHT, x, MAX_HEIGHT);
        }

        // Draw horizontal grid lines (extend well beyond visible area)
        for(float j = -MAX_HEIGHT; j <= MAX_HEIGHT; j += graphInterval.get(1)) {
            float y = j + graphInterval.get(1);
            processing.line(-MAX_WIDTH, y, MAX_WIDTH, y);
        }

        // Draw the fixed border on top
        processing.noClip();
        processing.stroke(255);
        processing.strokeWeight(4.5f);
        processing.noFill();
        processing.rect(graphPos.get(0), graphPos.get(1), graphDimensions.get(0), graphDimensions.get(1), 0);
    }

    /*
    public float getGraphWidth() { return graphWidth; }
    public float getGraphHeight() { return graphHeight; }
    public float getGridSpacingX() { return gridSpacingX; }
    public float getGridSpacingY() { return gridSpacingY; }
    public float getOffsetX() { return offsetX; }
    public float getOffsetY() { return offsetY; }
    */
}
