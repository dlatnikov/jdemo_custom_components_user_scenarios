package org.test.scenario;

import com.griddynamics.jagger.invoker.v2.JHttpEndpoint;
import com.griddynamics.jagger.invoker.v2.JHttpQuery;
import com.griddynamics.jagger.invoker.v2.JHttpResponse;

import java.util.concurrent.TimeUnit;

public class MyHttpStep {

    private final String stepId;
    private JHttpEndpoint endpoint;
    private JHttpQuery query;
    private JHttpResponse response;
    private long waitAfterExecutionInSeconds;
    private String stepDisplayName;

    public void waitAfterExecution() {
        if(this.waitAfterExecutionInSeconds > 0L) {
            try {
                TimeUnit.SECONDS.sleep(this.waitAfterExecutionInSeconds);
            } catch (InterruptedException var2) {
                throw new RuntimeException("Error occurred while waiting after execution", var2);
            }
        }

    }

    public MyHttpStep(String id, JHttpEndpoint endpoint) {
        this.stepId = id;
        this.endpoint = endpoint;
    }

    public String getStepId() {
        return stepId;
    }

    public JHttpEndpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(JHttpEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    public JHttpQuery getQuery() {
        return query;
    }

    public void setQuery(JHttpQuery query) {
        this.query = query;
    }

    public JHttpResponse getResponse() {
        return response;
    }

    public void setResponse(JHttpResponse response) {
        this.response = response;
    }

    public long getWaitAfterExecutionInSeconds() {
        return waitAfterExecutionInSeconds;
    }

    public void setWaitAfterExecutionInSeconds(long waitAfterExecutionInSeconds) {
        this.waitAfterExecutionInSeconds = waitAfterExecutionInSeconds;
    }

    public String getStepDisplayName() {
        return stepDisplayName;
    }

    public void setStepDisplayName(String stepDisplayName) {
        this.stepDisplayName = stepDisplayName;
    }

}
