import java.awt.image.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Provides an interface to a picture as an array of Pixels
 */
public class PixelImage
{
  private BufferedImage myImage;
  private int width;
  private int height;
  
  private int MAX_VALUE = 255;
  private int MIN_VALUE = 0;

  /**
   * Map this PixelImage to a real image
   * @param bi The image
   */
  public PixelImage(BufferedImage bi)
  {
    // initialize instance variables
    this.myImage = bi;
    this.width = bi.getWidth();
    this.height = bi.getHeight();
  }

  /**
   * Return the width of the image
   */
  public int getWidth()
  {
    return this.width;
  }

  /**
   * Return the height of the image
   */
  public int getHeight()
  {
    return this.height;
  }

  /**
   * Return the BufferedImage of this PixelImage
   */
  public BufferedImage getImage()
  {
    return this.myImage;
  }

  /**
   * Return the image's pixel data as an array of Pixels.  The
   * first coordinate is the x-coordinate, so the size of the
   * array is [width][height], where width and height are the
   * dimensions of the array
   * @return The array of pixels
   */
  public Pixel[][] getData()
  {
    Raster r = this.myImage.getRaster();
    Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
    int[] samples = new int[3];

    for (int row = 0; row < r.getHeight(); row++)
    {
      for (int col = 0; col < r.getWidth(); col++)
      {
        samples = r.getPixel(col, row, samples);
        Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
        data[row][col] = newPixel;
      }
    }

    return data;
  }

  /**
   * Set the image's pixel data from an array.  This array matches
   * that returned by getData().  It is an error to pass in an
   * array that does not match the image's dimensions or that
   * has pixels with invalid values (not 0-255)
   * @param data The array to pull from
   */
  public void setData(Pixel[][] data)
  {
    int[] pixelValues = new int[3];     // a temporary array to hold r,g,b values
    WritableRaster wr = this.myImage.getRaster();

    if (data.length != wr.getHeight())
    {
      throw new IllegalArgumentException("Array size does not match");
    }
    else if (data[0].length != wr.getWidth())
    {
      throw new IllegalArgumentException("Array size does not match");
    }

    for (int row = 0; row < wr.getHeight(); row++)
    {
      for (int col = 0; col < wr.getWidth(); col++)
      {
    	pixelValues[0] = data[row][col].red;
        pixelValues[1] = data[row][col].green;
        pixelValues[2] = data[row][col].blue;
        wr.setPixel(col, row, pixelValues);
      }
    }
  }
  
  public int getScale (int[][] weight)
  {
	  int scale = 0;
	  for (int row = 0; row < weight.length ; row++)
	  {
	      for (int col = 0; col < weight.length; col++)
	      {
	    	  scale += weight[row][col];
	      }
	  }
	  if (scale == 0) 
	  {
		  scale = 1;
	  }
	  return scale;
  }
  
  public int checkValue (int colorValue) {
	  if (colorValue < MIN_VALUE) 
	  {
		  colorValue = MIN_VALUE;
	  }
	  else if (colorValue > MAX_VALUE)
	  {
		  colorValue = MAX_VALUE;
	  }
	  return colorValue;
  }

public Pixel[][] Transformations (int[][] weights,  Pixel[][] data, int height , int width) {
	  int scale = getScale (weights);
	  Pixel[][] output = new Pixel[height][width];
	  
	  for (int Row = 0; Row < height; Row++)
	  {
			for (int Col = 0; Col < width; Col++)
	        {
				int red= 0;	 
				int green= 0;	
				int blue= 0;	
				for (int row = Math.max(0, Row - 1); row <= Math.min(Row + 1, height - 1); row++)
				{
				      for (int col = Math.max(0, Col - 1); col <= Math.min(Col + 1, width - 1); col++)
				      {
				    	  int weight = weights[Row - row + 1][Col - col + 1];
				    	  red += data[row][col].red * weight;
				    	  green += data[row][col].green * weight;
				    	  blue += data[row][col].blue * weight;
				      }
				}
				red = checkValue(red/scale);
				green = checkValue(green/scale);
				blue = checkValue(blue/scale);
				  
				output[Row][Col] = new Pixel(red, green, blue);
	        }
	  }
	  return output;
  }


}

