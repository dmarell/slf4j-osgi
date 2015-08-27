/*
 * By Joel Binnquist
 */
package se.marell.slf4josgi;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

import java.util.Date;

public class OSGiLogger extends MarkerIgnoringBase {
    private static final long serialVersionUID = 1L;
    private boolean detailed = Boolean.getBoolean("se.marell.slf4josgi.OSGiLogger.detailed");

    /**
     * {@inheritDoc}
     */
    public boolean isTraceEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void trace(String msg) {
        internalLog(LogService.LOG_DEBUG, msg, null);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(String format, Object arg) {
        String msgStr = MessageFormatter.format(format, arg).getMessage();
        internalLog(LogService.LOG_DEBUG, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(String format, Object arg1, Object arg2) {
        String msgStr = MessageFormatter.format(format, arg1, arg2).getMessage();
        internalLog(LogService.LOG_DEBUG, msgStr, null);
    }


    /**
     * {@inheritDoc}
     */
    public void trace(String format, Object[] argArray) {
        String msgStr = MessageFormatter.arrayFormat(format, argArray).getMessage();
        internalLog(LogService.LOG_DEBUG, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(String msg, Throwable t) {
        internalLog(LogService.LOG_DEBUG, msg, t);
    }


    /**
     * {@inheritDoc}
     */
    public boolean isDebugEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String msg) {
        internalLog(LogService.LOG_DEBUG, msg, null);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String format, Object arg) {
        String msgStr = MessageFormatter.format(format, arg).getMessage();
        internalLog(LogService.LOG_DEBUG, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String format, Object arg1, Object arg2) {
        String msgStr = MessageFormatter.format(format, arg1, arg2).getMessage();
        internalLog(LogService.LOG_DEBUG, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String format, Object[] argArray) {
        String msgStr = MessageFormatter.arrayFormat(format, argArray).getMessage();
        internalLog(LogService.LOG_DEBUG, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String msg, Throwable t) {
        internalLog(LogService.LOG_DEBUG, msg, t);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isInfoEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void info(String msg) {
        internalLog(LogService.LOG_INFO, msg, null);
    }

    /**
     * {@inheritDoc}
     */
    public void info(String format, Object arg) {
        String msgStr = MessageFormatter.format(format, arg).getMessage();
        internalLog(LogService.LOG_INFO, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void info(String format, Object arg1, Object arg2) {
        String msgStr = MessageFormatter.format(format, arg1, arg2).getMessage();
        internalLog(LogService.LOG_INFO, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void info(String format, Object[] argArray) {
        String msgStr = MessageFormatter.arrayFormat(format, argArray).getMessage();
        internalLog(LogService.LOG_INFO, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void info(String msg, Throwable t) {
        internalLog(LogService.LOG_INFO, msg, t);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isWarnEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String msg) {
        internalLog(LogService.LOG_WARNING, msg, null);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String format, Object arg) {
        String msgStr = MessageFormatter.format(format, arg).getMessage();
        internalLog(LogService.LOG_WARNING, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String format, Object arg1, Object arg2) {
        String msgStr = MessageFormatter.format(format, arg1, arg2).getMessage();
        internalLog(LogService.LOG_WARNING, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String format, Object[] argArray) {
        String msgStr = MessageFormatter.arrayFormat(format, argArray).getMessage();
        internalLog(LogService.LOG_WARNING, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String msg, Throwable t) {
        internalLog(LogService.LOG_WARNING, msg, t);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isErrorEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void error(String msg) {
        internalLog(LogService.LOG_ERROR, msg, null);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String format, Object arg) {
        String msgStr = MessageFormatter.format(format, arg).getMessage();
        internalLog(LogService.LOG_ERROR, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String format, Object arg1, Object arg2) {
        String msgStr = MessageFormatter.format(format, arg1, arg2).getMessage();
        internalLog(LogService.LOG_ERROR, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String format, Object[] argArray) {
        String msgStr = MessageFormatter.arrayFormat(format, argArray).getMessage();
        internalLog(LogService.LOG_ERROR, msgStr, null);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String msg, Throwable t) {
        internalLog(LogService.LOG_ERROR, msg, t);
    }


    /**
     * Check the availability of the OSGi logging service, and use it is available.
     * Does nothing otherwise.
     *
     * @param level   Log level, one of LogLevel.LOG_...
     * @param message Log message text
     * @param t       Throwable to log or null
     */
    private void internalLog(int level, String message, Throwable t) {
        LogService logservice = OSGiLogFactory.getLogService();
        ServiceReference serviceref = OSGiLogFactory.getServiceReference();

        StackTraceElement callerInfo = null;
        if (detailed) {
            callerInfo = new Exception().getStackTrace()[2];
        }
        if (logservice != null) {
            try {
                if (t != null) {
                    logservice.log(serviceref, level, createMessagePart(level, callerInfo, message + ""), t);
                } else {
                    logservice.log(serviceref, level, createMessagePart(level, callerInfo, message + ""));
                }
            } catch (Exception ignore) {
                // Service may have become invalid, ignore any error until the log service reference is
                // updated by the log factory.
            }
        } else {
            BundleContext bundleContext = OSGiLogFactory.getContext();
            Bundle bundle;
            try {
                bundle = bundleContext.getBundle();
            } catch (Throwable ignore) {
                bundle = null;
            }

            System.out.println(
                    new Date() + " " + getLogLevelString(level) +
                            (bundle != null ? "  #" + bundle.getBundleId() + " " : "") +
                            (bundle != null ? bundle.getSymbolicName() + " " : " ") +
                            createMessagePart(level, callerInfo, message + ""));
            if (t != null) {
                t.printStackTrace();
            }
        }
    }

    private static String createMessagePart(int logLevel, StackTraceElement stackTraceElement, String message) {
        if (logLevel == LogService.LOG_INFO) {
            return message;
        }
        if (stackTraceElement != null) {
            return message + " ;; (" + "{" + Thread.currentThread().getName() + "} " + stackTraceElement.getClassName() + "." +
                    stackTraceElement.getMethodName() + "#" +
                    stackTraceElement.getLineNumber() + ") ";
        }
        return message;
    }

    private static String getLogLevelString(int logLevel) {
        switch (logLevel) {
            case LogService.LOG_ERROR:
                return "ERROR";
            case LogService.LOG_WARNING:
                return "WARNING";
            case LogService.LOG_DEBUG:
                return "DEBUG";
            case LogService.LOG_INFO:
                return "INFO";
            default:
                return "UNKOWN";
        }
    }
}
