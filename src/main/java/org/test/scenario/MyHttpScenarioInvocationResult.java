package org.test.scenario;

import java.util.Map;

public class MyHttpScenarioInvocationResult {
    private final Map<String,Double> stepInvocationResults;
    private final String scenarioId;
    private final String scenarioDisplayName;

    public MyHttpScenarioInvocationResult(Map<String,Double> stepInvocationResults, String scenarioId, String scenarioDisplayName) {
        this.stepInvocationResults = stepInvocationResults;
        this.scenarioId = scenarioId;
        this.scenarioDisplayName = scenarioDisplayName;
    }

    public String getScenarioId() {
        return this.scenarioId;
    }

    public String getScenarioDisplayName() {
        return this.scenarioDisplayName;
    }

    public Map<String,Double> getStepInvocationResults() {
        return this.stepInvocationResults;
    }

}
