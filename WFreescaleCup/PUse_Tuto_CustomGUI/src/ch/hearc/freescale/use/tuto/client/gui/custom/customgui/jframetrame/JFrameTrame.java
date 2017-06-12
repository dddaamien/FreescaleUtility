
package ch.hearc.freescale.use.tuto.client.gui.custom.customgui.jframetrame;

import javax.swing.JFrame;

import ch.hearc.freescale.api.voiture.Voiture_I;
import ch.hearc.freescale.api.voitureInputStream.TrameEvent;
import ch.hearc.freescale.api.voitureInputStream.TrameListener;
import ch.hearc.freescale.use.tuto.client.gui.custom.trame.TrameReceivedTuto;

/**
 * Fenetre dont le nom change lorsqu'un trame est reçue
 */
public class JFrameTrame extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameTrame(Voiture_I voiture)
		{
		this.voiture = voiture;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		this.setSize(500, 100);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}

	private void control()
		{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add listener
		voiture.addTrameListener(new TrameListener()
			{

				@Override
				public void tramePerformed(final TrameEvent event)
					{
					TrameReceivedTuto trame = (TrameReceivedTuto)event.getTrame();
					setTitle("Short : " + trame.getShortField());
					}
			});
		}

	private void appearance()
		{
		this.setTitle("-----");
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private Voiture_I voiture;

	}
