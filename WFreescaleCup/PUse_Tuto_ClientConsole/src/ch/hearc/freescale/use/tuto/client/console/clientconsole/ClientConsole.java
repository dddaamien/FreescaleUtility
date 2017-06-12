
package ch.hearc.freescale.use.tuto.client.console.clientconsole;

import ch.hearc.freescale.api.Excel.ExcelLogger;
import ch.hearc.freescale.api.exception.VoitureException;
import ch.hearc.freescale.api.protocol.decoder.registry.TrameRegistry;
import ch.hearc.freescale.api.voiture.VoitureMoo;
import ch.hearc.freescale.api.voiture.Voiture_I;
import ch.hearc.freescale.api.voitureInputStream.TrameEvent;
import ch.hearc.freescale.api.voitureInputStream.TrameListener;
import ch.hearc.freescale.use.tuto.client.console.trames.TrameReceivedTuto;
import ch.hearc.freescale.use.tuto.client.console.trames.TrameSendTuto;

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
public class ClientConsole implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public ClientConsole(String serverName, int serverPort, String workbookName)
		{
		this.serverName = serverName;
		this.serverPort = serverPort;
		this.workbookName = workbookName;
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
			addListener();
			initalizeExcelLogger();
			sendTrame();
			pauseMS(10000);
			saveWorkbook();
			disconnect();
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
		//Register trame. This action indicates to the API witch trame it will receive.
		TrameRegistry.getInstance().registry(new TrameReceivedTuto());
		TrameRegistry.getInstance().registry(new TrameSendTuto());
		}

	private void connection() throws VoitureException
		{
		//Create a new Voiture with URL and port name
		voiture = new VoitureMoo(serverName, serverPort);

		//Connect to the voiture
		voiture.connect();
		}

	private void addListener()
		{
		//Add listener
		voiture.addTrameListener(new TrameListener()
			{

			@Override
			public void tramePerformed(TrameEvent event)
				{
				System.out.println(event.getTrame());
				}
			});
		}

	private void initalizeExcelLogger()
		{
		logger = new ExcelLogger();
		voiture.addTrameListener(logger);
		}

	private void sendTrame() throws VoitureException
		{
		float vitesseGauche = 0;
		float vitesseDroite = 0;
		float posServo = 0;
		float expoCam = 0;
		Byte led = 0;
		voiture.sendTrame(new TrameSendTuto(vitesseGauche, vitesseDroite, posServo, expoCam, led));
		}

	private void saveWorkbook() throws VoitureException
		{
		logger.save(workbookName);
		}

	private void disconnect() throws VoitureException
		{
		//Disconect voiture
		voiture.disconnect();
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static void pauseMS(long delayMS)
		{
		try
			{
			Thread.sleep(delayMS);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private String serverName;
	private int serverPort;
	private String workbookName;

	// Tool
	private Voiture_I voiture;
	private ExcelLogger logger;

	}
