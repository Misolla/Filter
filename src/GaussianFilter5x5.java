
public class GaussianFilter5x5  implements Filter 
{
	public static final int [][] WEIGHT = {{1, 4, 6, 4,1}, 
			                               {4,16,24,16,4},
			                               {6,24,36,24,6},
			                               {4,16,24,16,1},
			                               {1, 4, 6, 4,1}};
	public void filter(PixelImage pi) 
	{
		Pixel[][] data = pi.getData();
		Pixel[][] tempdata = data;
		int h = pi.getHeight();
		int w = pi.getWidth();
		
		data = pi.Transformations(WEIGHT, tempdata, h, w);
	    
	    pi.setData(tempdata);
	}
}
	
