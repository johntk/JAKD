package kioskScreens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Result
{
	private ImageIcon productImage;
	private String imageFile;
	private JLabel image,description,salePrice;
	//private String salePrice;
	private JPanel resultPanel;
	private String srcPath;

	public Result(String img,String desc,double price)
	{
		imageFile = img;
		salePrice = new JLabel("  - €"+price);
		salePrice.setFont(new Font("Calibri",Font.BOLD,25));
		salePrice.setForeground(new Color(143,164,179));
		description = new JLabel("  "+desc);
		description.setFont(new Font("Calibri",Font.PLAIN,25));
		resultPanel = new JPanel(new BorderLayout());
		srcPath = "src/resources/kioskFiles/productImages/thumbs/";
		productImage = new ImageIcon(srcPath+imageFile);
		image = new JLabel(productImage);
		resultPanel.add(image,BorderLayout.WEST);
		resultPanel.add(description,BorderLayout.CENTER);
		resultPanel.add(salePrice,BorderLayout.EAST);
	}
	public JPanel getResult()
	{
		return resultPanel;
	}
}