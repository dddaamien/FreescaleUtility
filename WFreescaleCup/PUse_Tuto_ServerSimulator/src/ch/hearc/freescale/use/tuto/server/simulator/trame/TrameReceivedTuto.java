
package ch.hearc.freescale.use.tuto.server.simulator.trame;

import ch.hearc.freescale.api.image.Image;
import ch.hearc.freescale.api.protocol.Trame_I;

/**
 * Hypothese :
 *
 * 			La voiture envoie une trame contenant les valeures ordonnées suivantes :
 * 					Byte : AppID 	(constant pour une même trame)
 * 					Byte : TrameID 	(constant pour une même trame)
 * 					Float
 * 					Integer [-2^31, 2^31-1]
 * 					Short 	[-2^15, 2^15-1]
 * 					Byte	[-128, 127]
 * 					Image	Byte[145]
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
public class TrameReceivedTuto implements Trame_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public TrameReceivedTuto()
		{
		//Rien
		}

	public TrameReceivedTuto(Float vitesseGauche, Float vitesseDroite, Float accX, Float accY, Float accZ, Float yaw, Float roll, Float pitch, Image camera)
		{
		super();
		this.vitesseGauche = vitesseGauche;
		this.vitesseDroite = vitesseDroite;
		this.accX = accX;
		this.accY = accY;
		this.accZ = accZ;
		this.yaw = yaw;
		this.roll = roll;
		this.pitch = pitch;
		this.camera = camera;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("TrameReceivedTuto [vitesseGauche=");
		builder.append(this.vitesseGauche);
		builder.append(", vitesseDroite=");
		builder.append(this.vitesseDroite);
		builder.append(", accX=");
		builder.append(this.accX);
		builder.append(", accY=");
		builder.append(this.accY);
		builder.append(", accZ=");
		builder.append(this.accZ);
		builder.append(", yaw=");
		builder.append(this.yaw);
		builder.append(", roll=");
		builder.append(this.roll);
		builder.append(", pitch=");
		builder.append(this.pitch);
		builder.append(", camera=");
		builder.append(this.camera);
		builder.append("]");
		return builder.toString();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	//L'id indentifie chaque trame. Il doit être différent pour chaque trame
	@Override
	public byte getTrameID()
		{
		return 1;
		}

	//L'app ID indique à quel application appartient la trame
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

	//Meme ordre que les parametres envoyé par la voiture, sans ni AppID ni TrameID
	//Vitesse des moteurs
	private Float vitesseGauche;
	private Float vitesseDroite;
	//Accéléromètre
	private Float accX;
	private Float accY;
	private Float accZ;
	//Gyroscope
	private Float yaw;
	private Float roll;
	private Float pitch;
	//Image
	private Image camera;
	}
