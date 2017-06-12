
package ch.hearc.freescale.use.tuto.client.console;

import ch.hearc.freescale.use.tuto.client.console.clientconsole.ClientConsole;

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
 * 			(P3) Etape 3 : Listener
 *
 * 					Ajout des listeners permettant de récupérer les trame reçues
 *
 * 			(P4) Etape 4 : Envoie d'une trame
 *
 * 					Envoie une trame au server
 *
 * 			(P5) Etape 5 : Excel Logger
 *
 * 					Enregistrement des trame recu dans un fichier excel
 *
 * 			(P6) Etape 6 : Deconnection
 *
 * 					Déconnection de la voiture
 *
 *
 */
public class UseTutoClientConsole
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
		ClientConsole client = new ClientConsole(SERVER_NAME, SERVER_PORT, WORKBOOK_NAME);
		client.run();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final String SERVER_NAME = "127.0.0.1";//"192.168.1.176";
	private static final int SERVER_PORT = 5001;
	private static final String WORKBOOK_NAME = "Tuto.xls";

	}
