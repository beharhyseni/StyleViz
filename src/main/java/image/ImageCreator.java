package image;

import json.JsonExctractor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCreator {
    JsonExctractor jsonExctractor;
    private BufferedImage bufferedImage;
    private String imagePath;

    public ImageCreator(JsonExctractor jsonExctractor) {
        this.jsonExctractor = jsonExctractor;
    }

    public void drawImage() {
        imagePath = "background.png";
        try {
            // Load the background image
            bufferedImage = ImageIO.read(new File(imagePath));

            BufferedImage tmp = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);

            int imageWidth = bufferedImage.getWidth();
            int imageHeight = bufferedImage.getHeight();
            Graphics2D g = tmp.createGraphics();
            g.drawImage(bufferedImage, 0, 0, null);
            g.setColor(Color.BLACK);

            int numberOfClasses = jsonExctractor.getClassNames().size();


            drawBottomRectangle(g, imageWidth, imageHeight);
            drawStems(g, imageWidth, imageHeight);
            drawFlowers(g, imageWidth, imageHeight, numberOfClasses);
            drawStrings(g, imageWidth, imageHeight, numberOfClasses);


            g.dispose();

            // Write to the final image using the following file name and format
            ImageIO.write(tmp, "png", new File("final.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void drawStems(Graphics2D g, int imageWidth, int imageHeight) {
        // Draw stems
        int stemWidthChange = imageWidth / 4;
        int stemWidth = imageHeight / 96;
        g.fillRect(imageWidth / 8, (int) (imageHeight / (2.5)), stemWidth, (int) (imageHeight / (1.5)));
        g.fillRect(stemWidthChange + (imageWidth / 8), (int) (imageHeight / (2.5)), stemWidth, (int) (imageHeight / (1.5)));
        g.fillRect(2 * stemWidthChange + (imageWidth / 8), (int) (imageHeight / (2.5)), stemWidth, (int) (imageHeight / (1.5)));
        g.fillRect(3 * stemWidthChange + (imageWidth / 8), (int) (imageHeight / (2.5)), stemWidth, (int) (imageHeight / (1.5)));
    }

    protected void drawFlowers(Graphics2D g, int imageWidth, int imageHeight, int numberOfClasses) {

        drawEllipses(g, imageWidth, imageHeight);
        drawFlowerLines(g, imageWidth, imageHeight, numberOfClasses);
        drawFlowerCircles(g, imageWidth, imageHeight, numberOfClasses);

    }

    protected void drawEllipses(Graphics2D g, int imageWidth, int imageHeight) {
        int stemWidthChange = imageWidth / 4;
        // Draw the ellipses in stems
        int widthChange = imageWidth / 60;
        int heightChange = imageHeight / 70;
        int ellipseWidthStart = imageWidth / 8 - widthChange + (imageHeight / 96) / 2;
        int ellipseHeightStart = (int) (imageHeight / (2.5) - 10);

        g.fill(new Ellipse2D.Double(ellipseWidthStart, ellipseHeightStart, 2 * widthChange, heightChange));
        g.fill(new Ellipse2D.Double(stemWidthChange + ellipseWidthStart, ellipseHeightStart, 2 * widthChange, heightChange));
        g.fill(new Ellipse2D.Double(2 * stemWidthChange + ellipseWidthStart, ellipseHeightStart, 2 * widthChange, heightChange));
        g.fill(new Ellipse2D.Double(3 * stemWidthChange + ellipseWidthStart, ellipseHeightStart, 2 * widthChange, heightChange));

    }

    protected void drawFlowerLines(Graphics2D g, int imageWidth, int imageHeight, int numberOfClasses) {
        int stemWidthChange = imageWidth / 4;
        int stemWidth = imageHeight / 96;
        // Draw Flower Lines
        int lineLength = 135;
        int angledLineDifference = 140;
        // Set Line width
        g.setStroke(new BasicStroke(2));

        switch (numberOfClasses) {
            case 1:
                // TOP VERTICAL LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5) - lineLength), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5) - lineLength), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5) - lineLength), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5) - lineLength), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                break;
            case 2:

                // TOP LEFT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 - angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 30), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                // TOP RIGHT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 + angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 30), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                break;
            case 3:
                // TOP VERTICAL LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5) - lineLength), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5) - lineLength), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5) - lineLength), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5) - lineLength), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                // TOP LEFT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 - angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 30), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                // TOP RIGHT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 + angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 30), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                break;
            case 4:
                // TOP LEFT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 - angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 30), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                // TOP RIGHT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 + angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 30), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                // BOTTOM LEFT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 - angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                // BOTTOM RIGHT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 + angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                break;
            case 5:
                // TOP VERTICAL LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5) - lineLength), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5) - lineLength), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5) - lineLength), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5) - lineLength), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                // TOP LEFT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 - angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 30), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                // TOP RIGHT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 + angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 30), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 30), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                // BOTTOM LEFT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 - angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 - angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));

                // BOTTOM RIGHT LINE (for the 4 stems)
                g.drawLine(imageWidth / 8 + angledLineDifference, (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), imageWidth / 8 + stemWidth / 2, (int) (imageHeight / (2.5)));
                g.drawLine(stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(2 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), 2 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                g.drawLine(3 * stemWidthChange + (imageWidth / 8 + angledLineDifference), (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference), 3 * stemWidthChange + (imageWidth / 8 + stemWidth / 2), (int) (imageHeight / (2.5)));
                break;
            default:
                System.out.println("You should have not reached here! - Flower circle Drawer");
                break;
        }


    }


    protected void drawFlowerCircles(Graphics2D g, int imageWidth, int imageHeight, int numberOfClasses) {
        int stemWidthChange = imageWidth / 4;
        int stemWidth = imageHeight / 96;
        int lineLength = 135;
        int angledLineDifference = 140;
        int circleYPos = 0;
        int positionWidth = 0;
        int circleCorrection = 20;
        // DRAW THE CIRCLES FOR THE FIRST FLOWER
        int diameter;

        g.setColor(new Color(170, 0, 0));


        switch (numberOfClasses) {
            case 1:
                g.setColor(new Color(170, 0, 0));
                // DRAW TOP VERTICAL CIRCLE
                // First Circle
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(0)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);


                // Second Circle
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(0)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = stemWidthChange + imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // Third circle
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(0)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // Fourth circle
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(0)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);


                break;
            case 2:
                g.setColor(new Color(170, 0, 0));
                // DRAW TOP LEFT CIRCLES
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // DRAW TOP RIGHT CIRCLES
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                break;
            case 3:
                g.setColor(new Color(170, 0, 0));
                // DRAW THE TOP 3 CIRCLES
                // DRAW TOP LEFT CIRCLE
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // DRAW TOP VERTICAL CIRCLE
                // First Circle
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(1)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // Second Circle
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(1)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = stemWidthChange + imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // Third circle
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(1)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // Fourth circle
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(1)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // DRAW TOP RIGHT CIRCLES
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(2)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(2)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(2)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(2)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);


                break;
            case 4:
                g.setColor(new Color(170, 0, 0));

                // DRAW BOTTOM LEFT CIRCLES
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = (imageWidth / 8 - angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = stemWidthChange + (imageWidth / 8 - angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = 2 * stemWidthChange + (imageWidth / 8 - angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = 3 * stemWidthChange + (imageWidth / 8 - angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // DRAW TOP LEFT CIRCLE
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // DRAW TOP RIGHT CIRCLES
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(2)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(2)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(2)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(2)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // DRAW BOTTOM RIGHT CIRCLE
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(3)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = (imageWidth / 8 + angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(3)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = stemWidthChange + (imageWidth / 8 + angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(3)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = 2 * stemWidthChange + (imageWidth / 8 + angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(3)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = 3 * stemWidthChange + (imageWidth / 8 + angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                break;
            case 5:
                g.setColor(new Color(170, 0, 0));

                // DRAW BOTTOM LEFT CIRCLES
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = (imageWidth / 8 - angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = stemWidthChange + (imageWidth / 8 - angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = 2 * stemWidthChange + (imageWidth / 8 - angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(0)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = 3 * stemWidthChange + (imageWidth / 8 - angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // DRAW TOP LEFT CIRCLE
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(1)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // DRAW TOP VERTICAL CIRCLE
                // First Circle
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(2)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // Second Circle
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(2)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = stemWidthChange + imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // Third circle
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(2)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // Fourth circle
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(2)) + circleCorrection;
                circleYPos = imageHeight / 5 + (100 - diameter);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // DRAW TOP RIGHT CIRCLES
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(3)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(3)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(3)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 2 * stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(3)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
                positionWidth = 3 * stemWidthChange + imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                // DRAW BOTTOM RIGHT CIRCLE
                // First Flower
                diameter = Integer.parseInt(jsonExctractor.getClassConsistencies().get(4)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = (imageWidth / 8 + angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Second Flower
                diameter = Integer.parseInt(jsonExctractor.getConstantConsistencies().get(4)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = stemWidthChange + (imageWidth / 8 + angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Third Flower
                diameter = Integer.parseInt(jsonExctractor.getVariableConsistencies().get(4)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = 2 * stemWidthChange + (imageWidth / 8 + angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);
                // Fourth Flower
                diameter = Integer.parseInt(jsonExctractor.getMethodConsistencies().get(4)) + circleCorrection;
                circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
                positionWidth = 3 * stemWidthChange + (imageWidth / 8 + angledLineDifference) - diameter / 2;
                g.fillOval(positionWidth, circleYPos, diameter, diameter);

                break;
            default:
                break;
        }

    }

    protected void drawStrings(Graphics2D g, int imageWidth, int imageHeight, int numberOfClasses) {
        int lineLength = 135;
        int angledLineDifference = 140;
        int stemWidthChange = imageWidth / 4;
        int stemWidth = imageHeight / 96;
        int positionHeight = imageHeight / 5 + 100;
        int positionWidth = imageWidth / 8 + stemWidth / 2;
        int widthCorrection = 50;
        int heightCorrection = 130;

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));


        switch (numberOfClasses) {
            case 1:

                // First Flower
                g.drawString("Class 1", positionWidth - widthCorrection, positionHeight - heightCorrection);
                g.drawString(jsonExctractor.getClassConsistencies().get(0) + "% ", positionWidth - widthCorrection, positionHeight - heightCorrection / 4);
                // Second Flower
                g.drawString("Class 1", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection);
                // Third Flower
                g.drawString("Class 1", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection);
                // Fourth Flower
                g.drawString("Class 1", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection);

                break;
            case 2:
                positionWidth = imageWidth / 8 - angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 30);
                // TOP LEFT TEXT
                // First Flower
                g.drawString("Class 1", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Second Flower
                g.drawString("Class 1", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Third Flower
                g.drawString("Class 1", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Fourth Flower
                g.drawString("Class 1", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);

                positionWidth = imageWidth / 8 + angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 30);
                // TOP RIGHT TEXT
                // First Flower
                g.drawString("Class 2", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Second Flower
                g.drawString("Class 2", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Third Flower
                g.drawString("Class 2", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Fourth Flower
                g.drawString("Class 2", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                break;
            case 3:
                positionWidth = imageWidth / 8 - angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 30);
                // TOP LEFT TEXT
                // First Flower
                g.drawString("Class 1", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Second Flower
                g.drawString("Class 1", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Third Flower
                g.drawString("Class 1", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Fourth Flower
                g.drawString("Class 1", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);

                // TOP VERTICAL TEXT
                positionHeight = imageHeight / 5 + 100;
                positionWidth = imageWidth / 8 + stemWidth / 2;
                // First Flower
                g.drawString("Class 2", positionWidth - widthCorrection, positionHeight - heightCorrection);
                // Second Flower
                g.drawString("Class 2", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection);
                // Third Flower
                g.drawString("Class 2", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection);
                // Fourth Flower
                g.drawString("Class 2", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection);

                positionWidth = imageWidth / 8 + angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 30);
                // TOP RIGHT TEXT
                // First Flower
                g.drawString("Class 3", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Second Flower
                g.drawString("Class 3", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Third Flower
                g.drawString("Class 3", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Fourth Flower
                g.drawString("Class 3", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                break;
            case 4:
                // BOTTOM LEFT TEXT
                positionWidth = imageWidth / 8 - angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 2.7 * angledLineDifference);
                // First Flower
                g.drawString("Class 1", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Second Flower
                g.drawString("Class 1", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Third Flower
                g.drawString("Class 1", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Fourth Flower
                g.drawString("Class 1", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);

                // TOP LEFT TEXT
                positionWidth = imageWidth / 8 - angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 30);
                // First Flower
                g.drawString("Class 2", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Second Flower
                g.drawString("Class 2", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Third Flower
                g.drawString("Class 2", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Fourth Flower
                g.drawString("Class 2", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);

                // TOP RIGHT TEXT
                positionWidth = imageWidth / 8 + angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 30);
                // First Flower
                g.drawString("Class 3", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Second Flower
                g.drawString("Class 3", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Third Flower
                g.drawString("Class 3", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Fourth Flower
                g.drawString("Class 3", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);

                // BOTTOM RIGHT TEXT
                positionWidth = imageWidth / 8 + angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 2.7 * angledLineDifference);
                // First Flower
                g.drawString("Class 4", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Second Flower
                g.drawString("Class 4", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Third Flower
                g.drawString("Class 4", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Fourth Flower
                g.drawString("Class 4", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);

                break;

            case 5:

                // BOTTOM LEFT TEXT
                positionWidth = imageWidth / 8 - angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 2.7 * angledLineDifference);
                // First Flower
                g.drawString("Class 1", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                g.drawString(jsonExctractor.getClassConsistencies().get(0) + "% ", positionWidth - widthCorrection, positionHeight - heightCorrection / 4);
                // Second Flower
                g.drawString("Class 1", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                g.drawString(jsonExctractor.getConstantConsistencies().get(0) + "% ", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 4);
                // Third Flower
                g.drawString("Class 1", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                g.drawString(jsonExctractor.getVariableConsistencies().get(0) + "% ", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 4);
                // Fourth Flower
                g.drawString("Class 1", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                g.drawString(jsonExctractor.getMethodConsistencies().get(0) + "% ", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 4);

                // TOP LEFT TEXT
                positionWidth = imageWidth / 8 - angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 30);
                // First Flower
                g.drawString("Class 2", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Second Flower
                g.drawString("Class 2", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Third Flower
                g.drawString("Class 2", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Fourth Flower
                g.drawString("Class 2", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);

                positionHeight = imageHeight / 5 + 100;
                positionWidth = imageWidth / 8 + stemWidth / 2;
                // First Flower
                g.drawString("Class 3", positionWidth - widthCorrection, positionHeight - heightCorrection);

                // Second Flower
                g.drawString("Class 3", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection);
                // Third Flower
                g.drawString("Class 3", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection);
                // Fourth Flower
                g.drawString("Class 3", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection);

                // TOP RIGHT TEXT
                positionWidth = imageWidth / 8 + angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 30);
                // First Flower
                g.drawString("Class 4", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Second Flower
                g.drawString("Class 4", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Third Flower
                g.drawString("Class 4", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                // Fourth Flower
                g.drawString("Class 4", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);

                // BOTTOM RIGHT TEXT
                positionWidth = imageWidth / 8 + angledLineDifference;
                positionHeight = (int) (imageHeight / (2.5) - lineLength + 2.7 * angledLineDifference);
                // First Flower
                g.drawString("Class 5", positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                g.drawString(jsonExctractor.getClassConsistencies().get(0) + "% ", positionWidth - widthCorrection, positionHeight - heightCorrection / 4);
                // Second Flower
                g.drawString("Class 5", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                g.drawString(jsonExctractor.getConstantConsistencies().get(0) + "% ", stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 4);
                // Third Flower
                g.drawString("Class 5", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                g.drawString(jsonExctractor.getVariableConsistencies().get(0) + "% ", 2 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 4);
                // Fourth Flower
                g.drawString("Class 5", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 2);
                g.drawString(jsonExctractor.getMethodConsistencies().get(0) + "% ", 3 * stemWidthChange + positionWidth - widthCorrection, positionHeight - heightCorrection / 4);

                break;
            default:
                break;
        }

    }

    protected void drawBottomRectangle(Graphics2D g, int imageWidth, int imageHeight) {
        // Draw the bottom rectangle
        g.fillRect(0, imageHeight - 80, imageWidth, imageHeight);
    }

}
