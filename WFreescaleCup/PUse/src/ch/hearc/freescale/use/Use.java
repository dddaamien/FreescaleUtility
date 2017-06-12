
package ch.hearc.freescale.use;

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
 *					(U3) En chageant l'ID. Le placement des fenêtre est reseté
 *
 *
 * 			(P3) Utilisation de la voiture pour recevoir les trames
 * 				 et les afficher en console
 *
 * 					Un listener est ajouté à la voiture
 *
 */
public class Use
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
		jFrameFreescale = new JFrameFreescale();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
			{

			@Override
			public void run()
				{
				System.out.println("Application terminated.");
				//Pour quitter l'application proprement
				jFrameFreescale.stop();
				}
			}));
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

//	private static void monitoringGUI() throws VoitureException
//		{
//		//Creation d'un objet VoitureGUI. L'objet voiture GUI lit les annotations des trames passé en paramètres et affiches les fenêtres demmandées.
//		//L'URL de connection est enregisté dans le fichier "Settings/SettingsConnection.txt"
//		VoitureGUI voitureGUI = new VoitureGUI(AppID, new TrameReceived(), new TrameSend());
//
//		//Il est possible de récuppérer l'objet voiture afin d'ajouter ses propres listeners
//		voitureGUI.getVoiture().addTrameListener(new TrameListener()
//			{
//
//			@Override
//			public void tramePerformed(TrameEvent event)
//				{
//				System.out.println("Trame received : " + event.getTrame().toString());
//				}
//			});
//		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

//	private static final int AppID = 333;
	//tools
	private static JFrameFreescale jFrameFreescale;
	}
