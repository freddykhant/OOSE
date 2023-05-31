public class ImageData 
{
    private int[][] image;

    public ImageData(int height, int width)
    {
        image = new int[height][width];
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setPixel(int x, int y, int value)
    {
        image[y][x] = value;
    }

    public int getPixel(int x, int y)
    {
        return image[y][x];
    }

    public ImageData scale(ImageData oldImage)
    {
        int newWidth = oldImage.getWidth()/2;
        int newHeight = oldImage.getHeight()/2;
        ImageData newImage = newImageData(newWidth, newHeight);
        
        for(int y = 0; y < newHeight; y++)
        {
            for(int x = 0; i < newWidth; i++)
            {
                newImage.setPixel(x, y, oldImage.getPixel(x * 2, y * 2));
            }
        }
        return newImage;
    }

    public ImageData rotate(ImageData oldImage)
    {
        int newWidth = oldImage.getHeight();
        int newHeight = oldImage.getWidth();
        ImageData newImage = new ImageData(newWidth, newHeight);
        for(int y = 0; y < newHeight; y++)
        {
            for(int x = 0; x < newWidth; x++)
            {
                newImage.setPixel(x, y, oldImage.getPixel(newHeight - y, x));
            }
        }
        return newImage;
    }

    public ImageData invert(ImageData oldImage)
    {
        int newWidth = oldImage.getWidth();
        int newHeight = oldImage.getHeight();
        ImageData newImage = new ImageData(newWidth, newHeight);
        for(int y = 0; y < newHeight; y++)
        {
            for(int x = 0; x < newWidth; x++)
            {
                newImage.setPixel(x, y, ~oldImage.getPixel(x, y));
            }
        }
        return newImage;
    }
}
