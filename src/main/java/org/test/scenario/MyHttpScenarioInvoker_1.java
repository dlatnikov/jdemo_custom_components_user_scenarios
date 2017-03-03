package org.test.scenario;

import com.griddynamics.jagger.invoker.InvocationException;
import com.griddynamics.jagger.invoker.Invoker;
import com.griddynamics.jagger.invoker.v2.JHttpResponse;
import com.griddynamics.jagger.invoker.v2.SpringBasedHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;


public class MyHttpScenarioInvoker_1 implements Invoker<Void, Void, MyHttpScenario> {
    private final SpringBasedHttpClient httpClient = new SpringBasedHttpClient();
    private static Logger log = LoggerFactory.getLogger(MyHttpScenarioInvoker_1.class);

    public MyHttpScenarioInvoker_1() {
    }

    public Void invoke(Void nothing, MyHttpScenario scenario) throws InvocationException {

        for (MyHttpStep userScenarioStep : scenario.getUserScenarioSteps()) {

            log.info("Step: {}", userScenarioStep.getStepId());
            log.info("Endpoint: {}", userScenarioStep.getEndpoint());
            log.info("Query: {}", userScenarioStep.getQuery());

            long requestStartTime1 = System.nanoTime();
            JHttpResponse response = this.httpClient.execute(userScenarioStep.getEndpoint(), userScenarioStep.getQuery());
            Double requestTimeInMilliseconds = Double.valueOf((double)(System.nanoTime() - requestStartTime1) / 1000000.0D);

            log.info("Response: {}", response);

            userScenarioStep.waitAfterExecution();
        }

        return null;
    }
}
