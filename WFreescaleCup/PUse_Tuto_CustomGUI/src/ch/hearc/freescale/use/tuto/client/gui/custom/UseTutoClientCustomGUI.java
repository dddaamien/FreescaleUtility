
package ch.hearc.freescale.use.tuto.client.gui.custom;

import ch.hearc.freescale.use.tuto.client.gui.custom.customgui.ClientCustomGUI;

/**
 * Principe :
 *
 * 			(P1) Etape 1 : Register :
 *
 * 					Indiquer � l'API la trame qui va �tre recue
 *
 * 			(P2) Etape 2 : connection
 *
 * 					Connection � la voiture
 *
 * 			(P3) Etape 3 : Creation d'une JFrame
 *
 * 					Creation d'une JFrameTrame
 *
 */
public class UseTutoClientCustomGUI
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Static							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		ClientCustomGUI client = new ClientCustomGUI(SERVER_NAME, SERVER_PORT);
		client.run();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final String SERVER_NAME = "192.168.1.176";
	private static final int SERVER_PORT = 5001;

	}
