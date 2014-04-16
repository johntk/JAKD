package Popups;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import model.Product;

public class ProdDialog extends JDialog {

	private JPanel cardPanel, cd, dvd, game, phono, dock, console;
	private JDialog cdDialog;
	private Product p;
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	public ProdDialog(String popUp, JFrame jFrame, Product p) {

		this.p = p;
		
		JPanel popupPanels[] = new JPanel[] { cd = new CDPopup(p),
				dvd = new DVDPopup(), game = new GamePopup(),
				phono = new HeadphonePopup(), dock = new DockPopup(),
				console = new ConsolePopup("console") };

		cdDialog = new JDialog(jFrame, true);
		cdDialog.setLocationRelativeTo(null);

		for (int i = 0; i < popupPanels.length; i++) {
			if (popUp.equals(popupPanels[i].getName())) {
				cdDialog.add(popupPanels[i]);
				cdDialog.setSize(popupPanels[i].getSize());
			}
		}

		cdDialog.setVisible(true);

	}
}
