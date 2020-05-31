package it.business.utils;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;


@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	/**
	 * Application context.
	 */
	private static ApplicationContext context;

	/**
	 * Lock.
	 */
	private static Object lock = new Object();

	/**
	 * Setter application context.
	 *
	 * @param ctx the new application context
	 */
	@Override
	public final void setApplicationContext(final ApplicationContext ctx) {
		synchronized (lock) {
			context = ctx;
		}
	}
	
	/**
	 * Getter application context.
	 * 
	 * @return	application context
	 */
	public static ApplicationContext getApplicationContext() {
		return context;
	}

	/**
	 * Setter del contesto.
	 * 
	 * @param ctx	contesto
	 */
	public static void setApplicationContextForTestPurpose(final ApplicationContext ctx) {
		context = ctx;
	}

	public static void setApplicationContext(final ServletContextEvent event) {
		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}
	
}