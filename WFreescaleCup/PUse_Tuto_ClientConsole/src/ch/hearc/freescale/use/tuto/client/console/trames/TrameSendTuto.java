
package ch.hearc.freescale.use.tuto.client.console.trames;

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
 *
 * 			Eventuellement
 * 				Une méthode toString
 *
 * But :
 *
 * 			Afficher le contenu de la trame dans une console
 *
 * Note :
 *
 * 			Voire pUse_Tuto_ClientGui pour un affichage graphique
 * 			Voire pUse_Tuto_Server pour un l'utilisation du simulateur
 *
 */
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
		}

	/**
	 * Constructeur "plein" obligatoire
	 */
	public TrameSendTuto(Float vitesseGauche, Float vitesseDroite, Float posServo, Float expoCam, Byte led)
		{
		super();
		this.vitesseGauche = vitesseGauche;
		this.vitesseDroite = vitesseDroite;
		this.posServo = posServo;
		this.expoCam = expoCam;
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
	private Float expoCam;
	private Byte led;
	}
