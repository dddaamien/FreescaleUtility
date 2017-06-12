
package ch.hearc.freescale.use.tuto.server.simulator;

import ch.hearc.freescale.api.serveur.simulateur.VoitureSimulateur;
import ch.hearc.freescale.api.serveur.simulateur.generator.SinusTrameGenerator;
import ch.hearc.freescale.api.serveur.simulateur.generator.TrameGenerator_I;
import ch.hearc.freescale.use.tuto.server.simulator.trame.TrameReceivedTuto;

/**
 * Principe :
 *
 * 			(P1) Creation d'un generateur de trame.
 *
 * 					Le generateur de trame fourni une trame periodiquement
 * 					au simulateur.
 *
 * 					Le SinusTrameGenerator modifie les differents parametres
 * 					de la trame selon un fonction sinus
 *
 * 					Il est possible de creer son propore generateur en implemantant
 * 					l'interface TrameGenerator_I
 *
 * 			(P2) Creation d'un simulateur
 *
 * 					Le simulateur a besoin de :
 * 						Un generateur pour creer les trames
 * 						Un port de connection
 * 						La periode d'envoie de trame
 *
 * 			(P3) Quitter le simulateur
 *
 * 				Pour quitter le simulateur, il faut quitter le programme
 *
 *
 */
public class UseTutoServerSimulator
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
		/*
		 * Creation du trame generator. le SinusTrameGenerator modifie la trame en paramètre
		 *	en utlisant des sinus sur ses paramètres
		 */
		TrameGenerator_I generator = new SinusTrameGenerator(new TrameReceivedTuto());

		//Crée un serveur utilisant le SinusTrameGenerator. Le serveur envoie une trame toutes les 100mS
		VoitureSimulateur simulateur = new VoitureSimulateur(SERVER_PORT, generator, PERIODE);

		//Démarre le serveur dans un thread séparé
		Thread t = new Thread(simulateur);
		t.start();

		System.out.println("Server is running...");

		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final int SERVER_PORT = 5001;
	private static final int PERIODE = 1000;

	}
