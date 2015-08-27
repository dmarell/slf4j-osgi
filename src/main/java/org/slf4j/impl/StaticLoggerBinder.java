/*
 * By Joel Binnquist
 */
package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import se.marell.slf4josgi.OSGiLogFactory;

/**
 * This is a static binder for SLF4J. It must have this name and package, so that SLF4J can pick it up.
 */
public class StaticLoggerBinder {
    /**
     * Declare the version of the SLF4J API this implementation is compiled against.
     * The value of this field is usually modified with each release.
     */
    // to avoid constant folding by the compiler, this field must *not* be final
    public static String REQUESTED_API_VERSION = "1.6.2";  // !final

    private static OSGiLogFactory factory = new OSGiLogFactory();

    /**
     * The unique instance of this class.
     *
     * @deprecated Please use the {@link #getSingleton()} method instead of
     *             accessing this field directly. In future versions, this field
     *             will become private.
     */
    public static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

    /**
     * Return the singleton of this class.
     *
     * @return the StaticLoggerBinder singleton
     */
    public static final StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    private StaticLoggerBinder() {
    }

    public ILoggerFactory getLoggerFactory() {
        return factory;
    }

    public String getLoggerFactoryClassStr() {
        return factory.getClass().getName();
    }
}
