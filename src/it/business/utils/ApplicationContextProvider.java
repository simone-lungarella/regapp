package it.business.utils;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Utility class per la gestione del context dell'applicazione.
 * 
 * @author Simone Lungarella
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	/**
	 * Context dell'applicazione.
	 */
	private static ApplicationContext context;

	/**
	 * Lock.
	 */
	private static Object lock = new Object();

	/**
	 * Imposta il context dell'applicazione.
	 *
	 * @param ctx Context da impostare.
	 */
	@Override
	public final void setApplicationContext(final ApplicationContext ctx) {
		synchronized (lock) {
			context = ctx;
		}
	}
	
	/**
	 * Restituisce il context dell'applicazione.
	 * 
	 * @return Application Context.
	 */
	public static ApplicationContext getApplicationContext() {
		return context;
	}

	/**
	 * Imposta il context dell'applicazione.
	 *
	 * @param ctx Context da impostare.
	 */
	public static void setApplicationContextForTestPurpose(final ApplicationContext ctx) {
		context = ctx;
	}

	/**
	 * Imposta il context dell'applicazione.
	 *
	 * @param ctx Context da impostare.
	 */
	public static void setApplicationContext(final ServletContextEvent event) {
		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}
	
}