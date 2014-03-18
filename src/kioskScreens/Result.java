package kioskScreens;

import java.awt.*;
import javax.swing.*;

public class Result extends JPanel
{
	private ImageIcon productImage;
	private String imageFile;
	private JLabel image,description,salePrice;
	private JPanel resultPanel;
	private String srcPath;
	private GridBagConstraints gc;

	public Result(String img,String desc,double price)
	{
		gc = new GridBagConstraints();
		imageFile = img;
		
		resultPanel = new JPanel(new GridBagLayout());
		srcPath = "src/resources/kioskFiles/productImages/thumbs/";
		
		productImage = new ImageIcon(srcPath+imageFile);
		image = new JLabel(productImage);
		gc.gridx =0;
		gc.gridy =0;
		gc.weightx=0.0;
		gc.weighty=0.0;
		gc.anchor = GridBagConstraints.SOUTHWEST;
		resultPanel.add(image,gc);
		
		description = new JLabel("  "+desc);
		description.setFont(new Font("Calibri",Font.PLAIN,25));
		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.WEST;
		resultPanel.add(description,gc);
		
		salePrice = new JLabel("  �"+price);
		salePrice.setFont(new Font("Calibri",Font.BOLD|Font.ITALIC,20));
		salePrice.setForeground(new Color(20,120,230));
		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.SOUTHWEST;
		resultPanel.add(salePrice,gc);
	}
	public String getDescription()
	{
		String d = description.getText();
		return d;
	}
	public JPanel getResult()
	{
		return resultPanel;
	}
}