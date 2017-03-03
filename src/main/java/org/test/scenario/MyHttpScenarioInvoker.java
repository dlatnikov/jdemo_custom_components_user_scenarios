package org.test.scenario;

import com.griddynamics.jagger.invoker.InvocationException;
import com.griddynamics.jagger.invoker.Invoker;
import com.griddynamics.jagger.invoker.v2.JHttpResponse;
import com.griddynamics.jagger.invoker.v2.SpringBasedHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;


public class MyHttpScenarioInvoker implements Invoker<Void, MyHttpScenarioInvocationResult, MyHttpScenario> {
    private final SpringBasedHttpClient httpClient = new SpringBasedHttpClient();
    private static Logger log = LoggerFactory.getLogger(MyHttpScenarioInvoker.class);

    public MyHttpScenarioInvoker() {
    }

    public MyHttpScenarioInvocationResult invoke(Void nothing, MyHttpScenario scenario) throws InvocationException {

        Map<String,Double> stepExecutionTime = new LinkedHashMap<>();

        for (MyHttpStep userScenarioStep : scenario.getUserScenarioSteps()) {

            log.info("Step: {}", userScenarioStep.getStepId());
            log.info("Endpoint: {}", userScenarioStep.getEndpoint());
            log.info("Query: {}", userScenarioStep.getQuery());

            long requestStartTime1 = System.nanoTime();
            JHttpResponse response = this.httpClient.execute(userScenarioStep.getEndpoint(), userScenarioStep.getQuery());
            Double requestTimeInMilliseconds = Double.valueOf((double)(System.nanoTime() - requestStartTime1) / 1000000.0D);

            stepExecutionTime.put(userScenarioStep.getStepId(),requestTimeInMilliseconds);

            log.info("Response: {}", response);

            userScenarioStep.waitAfterExecution();
        }

        return new MyHttpScenarioInvocationResult(stepExecutionTime,scenario.getScenarioId(),scenario.getScenarioDisplayName());
    }
}
