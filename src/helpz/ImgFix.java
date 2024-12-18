package helpz;

import java.awt.image.BufferedImage;

public class ImgFix {

    public static BufferedImage getRotImg(BufferedImage img, int rotAngle) {

        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage newImg = new BufferedImage(w, h, h);
        return newImg;
    }
}
