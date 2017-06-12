
package ch.hearc.freescale.use.trames;

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
public class TrameReceived implements Trame_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Constructeur "vide" obligatoire
	 */
	public TrameReceived()
		{
		//Rien
		}

	/**
	 * Constructeur "plein" obligatoire
	 */
	public TrameReceived(Float vitesseGauche, Float vitesseDroite, Float accX, Float accY, Float accZ, Float yaw, Float roll, Float pitch, Image camera)
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
		//		builder.append(", camera=");
		//		builder.append(this.camera);
		builder.append("]");
		return builder.toString();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public float getVitesseGauche()
		{
		//TODO: retour float vs Float ?
		return vitesseGauche;
		}

	public float getVitesseDroite()
		{
		return this.vitesseDroite;
		}

	public float getAccX()
		{
		return this.accX;
		}

	public float getAccY()
		{
		return this.accY;
		}

	public float getAccZ()
		{
		return this.accZ;
		}

	public float getRoll()
		{
		return this.roll;
		}

	public float getPitch()
		{
		return this.pitch;
		}

	public float getYaw()
		{
		return this.yaw;
		}

	public Image getCamera()
		{
		return this.camera;
		}

	/**
	 * Le systeme embarquer peut envoyer des trames differentes, par exemple a des frequences differentes avec des attributs differents.
	 * Une trame possédant un meme contenu doit avoir le meme id
	 */
	@Override
	public byte getTrameID()
		{
		return 1;
		}

	/**
	 * Utiliser dans le GUI!
	 *
	 * Changer cette valeur pour ecraser les préférences des positions des graphiques dans le conteneur.
	 * Utile lorsqu'on enleve ou ajoute un attribut dans la classe, et plus globalement lorsque le nombre de graphique change
	 */
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
