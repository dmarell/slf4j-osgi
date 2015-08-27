# slf4j-osgi

This library is a bridge between slf4j-api and the OSGi LogService.

This code is originating from [http://code.google.com/p/osgi-logging](http://code.google.com/p/osgi-logging). The version of this module reflects
the version of its slf4j implementation.

## Maven usage

```
<dependency>
  <groupId>se.marell</groupId>
  <artifactId>slf4j-osgi</artifactId>
  <version>1.7.6_5</version>
</dependency>
```
----------------------------------------

## Release notes

* Version 1.7.6_5 - 2015-08-27
  * Moved to github
* Version 1.7.6_4 - 2014-02-09
  * Updated for OSGi R5
* Version 1.7.6_3 - 2014-02-08
  * Based on slf4j-api 1.7.6.
  * Java 7
  * Updated website info
* Version 1.6.4_1 - 2012-07-01
 First version. Based on slf4j-api 1.6.4.

## Usage
* In the bundle activation, add a call to

```
  se.marell.slf4josgi.OSGiLogFactory.initOSGI(context.getBundleContext());
```

* In the configuration for maven-bundle-plugin in pom.xml, add

```
  <Private-Package>
    org.slf4j.*,
    se.marell.slf4josgi,
    ...
  </Private-Package>
```

* Use slf4j logging as usual:

```
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
...
private final Logger log = LoggerFactory.getLogger(this.getClass());
...
log.info("slf4j logging is now working!");
```

Code example:

```
import org.apache.felix.scr.annotations.Component;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.marell.slf4josgi.OSGiLogFactory;

@Component
public class TestComponent {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected void activate(ComponentContext context) {
        OSGiLogFactory.initOSGI(context.getBundleContext());
        log.info("activate TestComponent,logback property=" +
                System.getProperty("logback.configurationFile"));
    }

    protected void deactivate(ComponentContext context) {
        log.info("deactivate TestComponent");
    }
}
```
