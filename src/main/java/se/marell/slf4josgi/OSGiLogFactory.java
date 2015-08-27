/*
 * By Joel Binnquist
 */
package se.marell.slf4josgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * Implementation of {@link ILoggerFactory} over {@link LogService}.
 */
public class OSGiLogFactory implements ILoggerFactory {
    private static OSGiLogger logger = new OSGiLogger();
    private static ServiceReference serviceRef;
    private static ServiceTracker logServiceTracker;
    private static BundleContext context;

    public static void initOSGI(BundleContext context) {
        initOSGI(context, null);
    }

    public static void initOSGI(BundleContext context, ServiceReference serviceRef) {
        OSGiLogFactory.context = context;
        OSGiLogFactory.serviceRef = serviceRef;
        if (context != null) {
            logServiceTracker = new ServiceTracker(context, LogService.class.getName(), null);
            logServiceTracker.open();
        }
    }


    static LogService getLogService() {
        if (logServiceTracker != null) {
            return (LogService) logServiceTracker.getService();
        }
        return null;
    }

    static ServiceReference getServiceReference() {
        return serviceRef;
    }

    static BundleContext getContext() {
        return context;
    }

    /**
     * {@inheritDoc}
     */
    public Logger getLogger(String name) {
        return logger;
    }
}
