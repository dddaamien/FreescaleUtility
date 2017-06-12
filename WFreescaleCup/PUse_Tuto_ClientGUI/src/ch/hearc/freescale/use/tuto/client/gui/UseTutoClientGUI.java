package ch.hearc.freescale.use.tuto.client.gui;


import ch.hearc.freescale.api.exception.VoitureException;
import ch.hearc.freescale.api.gui.VoitureGUI;
import ch.hearc.freescale.api.voitureInputStream.TrameEvent;
import ch.hearc.freescale.api.voitureInputStream.TrameListener;
import ch.hearc.freescale.use.tuto.client.gui.trames.TrameReceivedTuto;
import ch.hearc.freescale.use.tuto.client.gui.trames.TrameSendTuto;

/**
 * Principe :
 *
 * 			(P1) Création d'une VoitureGUI pour l'affichage graphique.
 *
 *					1. Pour recevoir une trame TrameSendTuto
 *					2. Pour envoier des trame TrameSendTuto
 *
 *
 * 			(P1) Utilisation de l'appID
 *
 *					(U1) Est utilisé pour enregistrer les placement des fenêtre
 *					(U2) Deux ID différents permettent de faire tourner deux instance
 *						 de l'application sans conflit
 *					(U3) En chageant l'ID. Le placement des fenêtre est resete
 *
 *
 * 			(P3) Utilisation de la voiture pour recevoire les trame
 * 				 et les afficher en console
 *
 * 					Un listener est ajouter à la voiuter
 *
 *
 *
 *
 */
public class UseTutoClientGUI
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
		try
			{
			//Creation d'un objet VoitureGUI. L'objet voiture GUI lit les annotations des trames passé en paramètres et affiches les fenêtres demmandées.
			//L'URL de connection est enregisté dans le fichier "Settings/SettingsConnection.txt"
			VoitureGUI voitureGUI = new VoitureGUI(APP_ID, new TrameReceivedTuto(), new TrameSendTuto());

			//Il est possible de récuppérer l'objet voiture afin d'ajouter ses propres listeners
			voitureGUI.getVoiture().addTrameListener(new TrameListener()
				{

					@Override
					public void tramePerformed(TrameEvent event)
						{
						System.out.println("Trame received : " + event.getTrame().toString());
						}
				});
			}
		catch (VoitureException e)
			{
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final int APP_ID = 333;
	}
