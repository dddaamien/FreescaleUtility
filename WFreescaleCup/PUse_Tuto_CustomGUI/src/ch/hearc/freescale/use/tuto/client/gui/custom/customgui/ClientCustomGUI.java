
package ch.hearc.freescale.use.tuto.client.gui.custom.customgui;

import ch.hearc.freescale.api.exception.VoitureException;
import ch.hearc.freescale.api.protocol.decoder.registry.TrameRegistry;
import ch.hearc.freescale.api.voiture.VoitureMoo;
import ch.hearc.freescale.api.voiture.Voiture_I;
import ch.hearc.freescale.use.tuto.client.gui.custom.customgui.jframetrame.JFrameTrame;
import ch.hearc.freescale.use.tuto.client.gui.custom.trame.TrameReceivedTuto;

/**
 * Principe :
 *
 * 			(P1) Etape 1 : Register :
 *
 * 					Indiquer à l'API la trame qui va être recue
 *
 * 			(P2) Etape 2 : connection
 *
 * 					Connection à la voiture
 *
 * 			(P3) Etape 3 : Creation d'une JFrame
 *
 * 					Creation d'une JFrame
 *
 *
 */
public class ClientCustomGUI implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public ClientCustomGUI(String serverName, int serverPort)
		{
		this.serverName = serverName;
		this.serverPort = serverPort;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		try
			{
			register();
			connection();
			createJFrame();
			}
		catch (VoitureException e)
			{
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void register() throws VoitureException
		{
		//Register trame. This action indicate to the API witch trame it will receive.
		TrameRegistry.getInstance().registry(new TrameReceivedTuto());
		}

	private void connection() throws VoitureException
		{
		//Create a new Voiture with URL and port name
		voiture = new VoitureMoo(serverName, serverPort);

		//Connect to the voiture
		voiture.connect();
		}

	private void createJFrame()
		{
		frame = new JFrameTrame(voiture);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private String serverName;
	private int serverPort;

	// Tool
	private Voiture_I voiture;
	private JFrameTrame frame;

	}
