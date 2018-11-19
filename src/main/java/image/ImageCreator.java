package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCreator {
    private BufferedImage bufferedImage;
    private String imagePath;

    public ImageCreator() {
    }

    public void drawImage() {
        imagePath = "background.png";
        try {
            // Load the background image
            bufferedImage = ImageIO.read(new File(imagePath));
            // getFinalImage();
            // Write to the final image to the background image
            ImageIO.write(getFinalImage(), "png", new File("final.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void drawStem() {

    }

    protected void drawFlower() {

    }

    protected BufferedImage getFinalImage() {
        BufferedImage tmp = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);


        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();
        int stemWidthChange = imageWidth / 4;
        Graphics2D g = tmp.createGraphics();
        g.drawImage(bufferedImage, 0, 0, null);
        g.setColor(Color.BLACK);

        // Draw stems
        int stemWidth = imageHeight / 96;
        g.fillRect(imageWidth / 8, (int) (imageHeight / (2.5)), stemWidth, (int) (imageHeight / (1.5)));
        g.fillRect(stemWidthChange + (imageWidth / 8), (int) (imageHeight / (2.5)), stemWidth, (int) (imageHeight / (1.5)));
        g.fillRect(2 * stemWidthChange + (imageWidth / 8), (int) (imageHeight / (2.5)), stemWidth, (int) (imageHeight / (1.5)));
        g.fillRect(3 * stemWidthChange + (imageWidth / 8), (int) (imageHeight / (2.5)), stemWidth, (int) (imageHeight / (1.5)));

        // Draw the bottom rectangle
        g.fillRect(0, imageHeight - 80, imageWidth, imageHeight);


        // Draw the ellipses in stems
        int widthChange = imageWidth / 60;
        int heightChange = imageHeight / 70;
        int ellipseWidthStart = imageWidth / 8 - widthChange + (imageHeight / 96) / 2;
        int ellipseHeightStart = (int) (imageHeight / (2.5) - 10);

        g.fill(new Ellipse2D.Double(ellipseWidthStart, ellipseHeightStart, 2 * widthChange, heightChange));
        g.fill(new Ellipse2D.Double(stemWidthChange + ellipseWidthStart, ellipseHeightStart, 2 * widthChange, heightChange));
        g.fill(new Ellipse2D.Double(2 * stemWidthChange + ellipseWidthStart, ellipseHeightStart, 2 * widthChange, heightChange));
        g.fill(new Ellipse2D.Double(3 * stemWidthChange + ellipseWidthStart, ellipseHeightStart, 2 * widthChange, heightChange));


        // Draw Flower Lines
        int lineLength = 135;
        int angledLineDifference = 140;
        // Set Line width
        g.setStroke(new BasicStroke(2));

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


        // DRAW THE CIRCLES FOR THE FIRST FLOWER

        int diameter;

        g.setColor(new Color(170, 0, 0));

        // DRAW TOP VERTICAL CIRCLE
        diameter = 120;
        int circleYPos = imageHeight / 5 + (100 - diameter);
        int positionWidth = imageWidth / 8 + stemWidth / 2 - diameter / 2;
        g.fillOval(positionWidth, circleYPos, diameter, diameter);

        // DRAW TOP RIGHT CIRCLE
        diameter = 120;
        circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
        positionWidth = imageWidth / 8 + angledLineDifference + stemWidth / 2 - diameter / 2;
        g.fillOval(positionWidth, circleYPos, diameter, diameter);

        // DRAW TOP LEFT CIRCLE
        diameter = 120;
        circleYPos = (int) (imageHeight / (2.5) - lineLength + 30 - diameter / 2);
        positionWidth = imageWidth / 8 - angledLineDifference - stemWidth / 2 - diameter / 2;
        g.fillOval(positionWidth, circleYPos, diameter, diameter);

        // DRAW BOTTOM LEFT CIRCLE
        diameter = 120;
        circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
        positionWidth = (imageWidth / 8 - angledLineDifference) - diameter / 2;
        g.fillOval(positionWidth, circleYPos, diameter, diameter);


        // DRAW BOTTOM RIGHT CIRCLE
        diameter = 120;
        circleYPos = (int) (imageHeight / (2.5) - lineLength + 1.5 * angledLineDifference) - diameter / 2;
        positionWidth = (imageWidth / 8 + angledLineDifference) - diameter / 2;
        g.fillOval(positionWidth, circleYPos, diameter, diameter);


        g.dispose();

        return tmp;
    }


}
