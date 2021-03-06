package org.test.simple.examples;

import com.griddynamics.jagger.engine.e1.Provider;
import com.griddynamics.jagger.invoker.InvocationException;
import com.griddynamics.jagger.invoker.Invoker;
import com.griddynamics.jagger.invoker.v2.DefaultHttpInvoker;
import com.griddynamics.jagger.invoker.v2.JHttpEndpoint;
import com.griddynamics.jagger.invoker.v2.JHttpQuery;
import com.griddynamics.jagger.invoker.v2.JHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example of {@link Provider<Invoker>} implementation
 * through which custom data can be passed to new instances of a custom {@link Invoker} at runtime.
 * @n
 * Created by Andrey Badaev
 * Date: 06/12/16
 */
public class CustomHttpInvokerProvider implements Provider<Invoker>  {
    
    private static final Logger log = LoggerFactory.getLogger(CustomHttpInvokerProvider.class);
    
    private final boolean verbose;
    
    public CustomHttpInvokerProvider(final boolean verbose) {this.verbose = verbose;}
    
    public static CustomHttpInvokerProvider verbose() {
        return new CustomHttpInvokerProvider(true);
    }
    
    
    public static CustomHttpInvokerProvider nonVerbose() {
        return new CustomHttpInvokerProvider(false);
    }
    
    @Override
    public Invoker provide() {
        return new DefaultHttpInvoker() {
            @Override
            public JHttpResponse invoke(JHttpQuery query, JHttpEndpoint endpoint) throws InvocationException {
                
                if (verbose) {
                    log.info("in invoke method of custom http invoker...");
                }
                
                return super.invoke(query, endpoint);
            }
        };
    }
}