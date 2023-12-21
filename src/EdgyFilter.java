
public class EdgyFilter implements Filter 
{
	public static final int [][] WEIGHT = {{-1,-1,-1}, 
                                           {-1,9,-1},
                                           {-1,-1,-1}};
	public void filter(PixelImage pi) 
	{
		Pixel[][] data = pi.getData();
		Pixel[][] tempdata = data;
		int h = pi.getHeight();
		int w = pi.getWidth();
		
	    data = pi.Transformations(WEIGHT, tempdata, h, w);
	    
	    pi.setData(data);
		
	}
}
