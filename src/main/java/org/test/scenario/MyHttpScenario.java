package org.test.scenario;

import java.util.ArrayList;
import java.util.List;

public class MyHttpScenario {
    private final String scenarioId;
    private final String scenarioDisplayName;
    private List<MyHttpStep> userScenarioSteps = new ArrayList();

    public MyHttpScenario(String scenarioId, String scenarioDisplayName) {
        this.scenarioId = scenarioId;
        this.scenarioDisplayName = scenarioDisplayName;
    }

    public MyHttpScenario addStep(MyHttpStep userScenarioStep) {
        if(!this.isStepIdUnique(userScenarioStep.getStepId())) {
            throw new IllegalArgumentException(String.format("Step id \'%s\' is not unique!", new Object[]{userScenarioStep.getStepId()}));
        } else {
            this.userScenarioSteps.add(userScenarioStep);
            return this;
        }
    }

    private boolean isStepIdUnique(String id) {
        return this.userScenarioSteps.stream().map(MyHttpStep::getStepId).noneMatch((stepId) -> {
            return stepId.equals(id);
        });
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public String getScenarioDisplayName() {
        return scenarioDisplayName;
    }

    public List<MyHttpStep> getUserScenarioSteps() {
        return userScenarioSteps;
    }

}
