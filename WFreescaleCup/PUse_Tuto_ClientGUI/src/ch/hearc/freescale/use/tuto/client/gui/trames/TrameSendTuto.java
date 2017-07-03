
package ch.hearc.freescale.use.tuto.client.gui.trames;

import ch.hearc.freescale.api.gui.sender.annotation.SendableTrame;
import ch.hearc.freescale.api.protocol.Trame_I;

/**
 * Hypothese :
 *
 * 			La software envoie une trame contenant les valeures ordonnées suivantes :
 * 					Byte : AppID 	(constant pour une même trame)
 * 					Byte : TrameID 	(constant pour une même trame)
 * 					Integer [-2^31, 2^31-1]
 *
 *
 * Contrainte :
 *
 * 			Implementer une classe :
 * 				(C1) avec les attribus ci-dessus
 * 				(C2) Avec un constructeur vide
 * 				(C3) Avec un contructeur plein
 * 				(C4) Implemantant Trame_I
 * 				(C5) Ajouter l'annotaion @SendableTrame à la classe
 *
 * 			Eventuellement
 * 				Une méthode toString
 *
 * But :
 *
 * 			Pouvoir modifier et envoyer la trame à la voiure depuis une fenêtre
 *
 * Note :
 *
 * 			Voire pUse_Tuto_ClientConsole pour un affichage console
 * 			Voire pUse_Tuto_Server pour un l'utilisation du simulateur
 *
 */
@SendableTrame // @SendableTrame: ouvre la fenetre permettant de configurer/envoyer la trame
public class TrameSendTuto implements Trame_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Constructeur "vide" obligatoire
	 */
	public TrameSendTuto()
		{
		//example of default value
		this.vitesseGauche=(float)0;
		this.vitesseDroite=(float)0;
		this.expoTime=(float)0;
		this.led=0;
		this.posServo=(float)0;
		}

	/**
	 * Constructeur "plein" obligatoire
	 */
	public TrameSendTuto(Float vitesseGauche, Float vitesseDroite, Float posServo, Float expoTime, Short led)
		{
		super();
		this.vitesseGauche = vitesseGauche;
		this.vitesseDroite = vitesseDroite;
		this.posServo = posServo;
		this.expoTime = expoTime;
		this.led = led;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/


	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	@Override
	public byte getTrameID()
		{
		return 2;
		}

	@Override
	public byte getAppID()
		{
		return 10;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private Float vitesseGauche;
	private Float vitesseDroite;
	private Float posServo;
	private Float expoTime;
	private Short led;
	}
