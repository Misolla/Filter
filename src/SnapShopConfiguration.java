/**
 * A class to configure the SnapShop application
 *
 * @author Minh Bao Quach
 * @version June 18th,2021
 */
public class SnapShopConfiguration
{
  /**
   * Method to configure the SnapShop.  Call methods like addFilter
   * and setDefaultFilename here.
   * @param theShop A pointer to the application
   */
  public static void configure(SnapShop theShop)
  {
//	  theShop.setDefaultFilename("C:/Users/Sun/eclipse-workspace/SnapShop/billg.jpg");
	  theShop.setDefaultFilename("billg.jpg");
	  //theShop.setDefaultFilename("c:/users/cd/desktop/jpegs/billg.jpg");
      theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
      theShop.addFilter(new FlipVerticalFilter(), "Filp Vertical");
      theShop.addFilter(new NegativeFilter(), "Negative");
      theShop.addFilter(new GaussianFilter(), "Gaussian blur");
      theShop.addFilter(new LaplacianFilter(), "Laplacian");
      theShop.addFilter(new UnsharpMaskingFilter(), "Unsharp Masking");
      theShop.addFilter(new EdgyFilter(), "Edgy");
      theShop.addFilter(new GaussianFilter5x5(), "Gaussian blur 5x5");
  }
}
