package ch.hearc.freescale.use.tuto.client.gui.trames;

import ch.hearc.freescale.api.gui.presenter.annotation.container.DisplayDeskop;
import ch.hearc.freescale.api.gui.presenter.annotation.jcomponent.DisplayCurveRelatif;
import ch.hearc.freescale.api.gui.presenter.annotation.jcomponent.DisplayDigit;
import ch.hearc.freescale.api.gui.presenter.annotation.jcomponent.DisplayGauge;
import ch.hearc.freescale.api.gui.presenter.annotation.jcomponent.DisplayImageRoll;
import ch.hearc.freescale.api.gui.presenter.annotation.jcomponent.DisplayLevel;
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
 *				(C5) Choisir le layout avec l'une des annotations de classe suivantes :
 *						- @DisplayDeskop,
 *						- @DisplaySplit
 *						- @DisplayGrid
 *				(C6) Pour chaque paramètres choisir dnas quelle vue l'afficher avec une des annotatrion suivantes
 *						Pour un nombre :
 *							- @DisplayCurveAbsolute
 * 							- @DisplayCurveRelatif
 * 							- @DisplayDigit
 * 							- @DisplayGauge
 * 							- @DisplayLevel
 * 						Pour une image :
 * 							- @DisplayImageRoll
 * 							- @DisplayImageCurve
 *
 *
 * But :
 *
 * 			Afficher le contenu de la trame dans une fenêtre avec différentes vues
 *
 * Note :
 *
 * 			Voire pUse_Tuto_ClientConsole pour un affichage console
 * 			Voire pUse_Tuto_Server pour un l'utilisation du simulateur
 *
 */
@DisplayDeskop
public class TrameReceivedTuto implements Trame_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Constructeur "vide" obligatoire
	 */
	public TrameReceivedTuto()
		{
		//Rien
		}

	/**
	 * Constructeur "plein" obligatoire
	 */
	public TrameReceivedTuto(Float vitesseGauche, Float vitesseDroite, Float accX, Float accY, Float accZ, Float magX, Float magY, Float magZ, Float yaw, Float roll, Float pitch, Image camera)
		{
		super();
		this.vitesseGauche = vitesseGauche;
		this.vitesseDroite = vitesseDroite;
		this.accX = accX;
		this.accY = accY;
		this.accZ = accZ;
		this.magX = magX;
		this.magY = magY;
		this.magZ = magZ;
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
		builder.append(", magX=");
		builder.append(this.magX);
		builder.append(", magY=");
		builder.append(this.magY);
		builder.append(", magZ=");
		builder.append(this.magZ);
		builder.append(", yaw=");
		builder.append(this.yaw);
		builder.append(", roll=");
		builder.append(this.roll);
		builder.append(", pitch=");
		builder.append(this.pitch);
		builder.append("]");
		return builder.toString();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/


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

	/*
	 * Les champs de la classe representent les paramète de la trame.
	 * Ils doivent être d'un des types suivants :
	 * 		-Float
	 * 		-Integer
	 * 		-Short
	 * 		-Byte
	 * 		-Image
	 * L'ordre des champs est importants
	 */

	/*
	 * Les annotations permettent d'afficher les valeures dans différents graphiques.
	 * Les annotations sont les suivantes :
	 * 		-@DisplayCurveAbsolute
	 * 		-@DisplayCurveRelatif
	 * 		-@DisplayDigit
	 * 		-@DisplayGauge
	 * 		-@DisplayLevel
	 * 		-@DisplayImageR
	 * 		-@DisplayImageCurve
	 */

//	@DisplayCurveAbsolute(pointsToDisplay= 100, min=-1000, max= 1000)
//	private Float floatField;
//
//	@DisplayCurveRelatif(pointsToDisplay= 150)
//	private Integer integerField;
//
//	@DisplayDigit(title="Short Field",decimalCount=0)
//	private Short shortField;
//
//	//les a annotion sont cumulable. Il est donc possible d'afficher les même valeures dans plusieures view
//	@DisplayGauge(min=-100, max = 100)
//	@DisplayLevel(min=-100, max = 100)
//	private Byte byteField;
//
//	@DisplayImageRoll
//	@DisplayImageCurve
//	private Image imageField;

	@DisplayGauge(min = -300, max = 300)
	private Float vitesseGauche;
	@DisplayGauge(min = -300, max = 300)
	private Float vitesseDroite;

	@DisplayLevel(min = -100, max = 100)
	private Float accX;
	@DisplayLevel(min = -100, max = 100)
	private Float accY;
	@DisplayLevel(min = -100, max = 100)
	private Float accZ;

	@DisplayCurveRelatif
	private Float magX;
	@DisplayCurveRelatif
	private Float magY;
	@DisplayCurveRelatif
	private Float magZ;

	@DisplayDigit
	private Float yaw;
	@DisplayDigit
	private Float roll;
	@DisplayDigit
	private Float pitch;

	@DisplayImageRoll
	private Image camera;
	}

