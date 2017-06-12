
package ch.hearc.freescale.use;

import java.util.concurrent.atomic.AtomicBoolean;

import ch.hearc.freescale.api.Excel.ExcelLogger;
import ch.hearc.freescale.api.exception.VoitureException;
import ch.hearc.freescale.api.protocol.decoder.registry.TrameRegistry;
import ch.hearc.freescale.api.voiture.VoitureMoo;
import ch.hearc.freescale.api.voiture.Voiture_I;
import ch.hearc.freescale.api.voitureInputStream.TrameListener;
import ch.hearc.freescale.use.trames.TrameReceived;
import ch.hearc.freescale.use.trames.TrameSend;

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
public class Client
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Client(TrameListener listener)
		{
		this.listener = listener;
		this.isActive = new AtomicBoolean(false);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public synchronized void start()
		{
		if (!isActive.get())
			{
			isActive.set(true);
			Thread tReception = new Thread(new Runnable()
				{

				@Override
				public void run()
					{
					try
						{
						register();
						connection();
						addListener();
						if (workbookName != null)
							{
							initalizeExcelLogger();
							}
						while(isActive.get())
							{
							}
						if (workbookName != null)
							{
							System.out.println("Enregistrement de: " + workbookName);
							saveWorkbook();
							}
						disconnect();
						}
					catch (VoitureException e)
						{
						// TODO Auto-generated catch block
						stop();
						e.printStackTrace();
						}
					}
				});
			System.out.println("Start du thread: " + tReception.getName());
			tReception.start();
			}
		}

	public synchronized void stop()
		{
		isActive.set(false);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public String getServerName()
		{
		return this.serverName;
		}

	public int getServerPort()
		{
		return this.serverPort;
		}

	public String getWorkbookName()
		{
		return this.workbookName;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setServerName(String serverName)
		{
		this.serverName = serverName;
		}

	public void setServerPort(int serverPort)
		{
		this.serverPort = serverPort;
		}

	public void setWorkbookName(String workbookName)
		{
		this.workbookName = workbookName;
		}

	public void setVitesseGauche(float vitesseGauche)
		{
		this.vitesseGauche = vitesseGauche;
		}

	public void setVitesseDroite(float vitesseDroite)
		{
		this.vitesseDroite = vitesseDroite;
		}

	public void setExpoCam(float expoCam)
		{
		this.expoCam = expoCam;
		}

	public void setLed(boolean led)
		{
		if (led == true)
			{
			this.led = 1;
			}
		else
			{
			this.led = 0;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void register() throws VoitureException
		{
		//Register trame. This action indicates to the API witch trame it will receive.
		TrameRegistry.getInstance().registry(new TrameReceived());
		TrameRegistry.getInstance().registry(new TrameSend());
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
		voiture.addTrameListener(listener);
		}

	private void initalizeExcelLogger()
		{
		logger = new ExcelLogger();
		voiture.addTrameListener(logger);
		}

	private void sendTrame() throws VoitureException
		{
		voiture.sendTrame(new TrameSend(vitesseGauche, vitesseDroite, posServo, expoCam, led));
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

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	// Input
	private TrameListener listener;

	private String serverName;
	private int serverPort;
	private String workbookName;

	// Tools
	private Voiture_I voiture;
	private ExcelLogger logger;
	private AtomicBoolean isActive;

	private float vitesseGauche = 0;
	private float vitesseDroite = 0;
	private float posServo = 0;
	private float expoCam = 0;
	private Byte led = 0;
	}
