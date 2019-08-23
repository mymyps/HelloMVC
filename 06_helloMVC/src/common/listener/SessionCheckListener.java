package common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCheckListener
 *
 */
@WebListener
public class SessionCheckListener implements HttpSessionListener, HttpSessionAttributeListener {

    @Override
	public void attributeAdded(HttpSessionBindingEvent event) {
//    	System.out.println("attr session create");
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
//		System.out.println("attr session remove");
		
	}
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
//		System.out.println("sttr session replace");
		
	}

	/**
     * Default constructor. 
     */
    public SessionCheckListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         System.out.println("session created!");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         System.out.println("session destroyed!");
    }
	
}
