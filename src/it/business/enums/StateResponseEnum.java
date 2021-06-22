package it.business.enums;

/**
 * Enum che identifica tutti i possibili stati di una Response.
 * 
 * @author Simone Lungarella
 */
public enum StateResponseEnum {

	/**
	 * Risposta ad una request di creazione o modifica positiva.
	 */
	SAVED,

	/**
	 * Risposta ad una request di creazione o modifica negativa.
	 */
	NOT_SAVED,

	/**
	 * Risposta ad una request di ricerca positiva.
	 */
	FOUND,

	/**
	 * Risposta ad una request di ricerca negativa.
	 */
	NOT_FOUND;
}
