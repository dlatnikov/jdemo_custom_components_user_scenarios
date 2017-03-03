package org.test.scenario;

import com.griddynamics.jagger.engine.e1.Provider;
import com.griddynamics.jagger.engine.e1.collector.MetricDescription;
import com.griddynamics.jagger.engine.e1.collector.invocation.InvocationInfo;
import com.griddynamics.jagger.engine.e1.collector.invocation.InvocationListener;
import com.griddynamics.jagger.engine.e1.services.ServicesAware;
import com.griddynamics.jagger.invoker.InvocationException;
import com.griddynamics.jagger.invoker.scenario.DefaultAggregatorsProvider;
import com.griddynamics.jagger.util.StandardMetricsNamesUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import static com.griddynamics.jagger.util.StandardMetricsNamesUtil.LATENCY_ID;
import static com.griddynamics.jagger.util.StandardMetricsNamesUtil.LATENCY_SEC;

public class MyHttpScenarioInvocationListener extends ServicesAware implements Provider<InvocationListener> {
    private final Set<String> createdMetrics;

    public MyHttpScenarioInvocationListener() {
        this.createdMetrics = new ConcurrentSkipListSet();
    }

    protected void init() {
    }

    public InvocationListener provide() {
        return new InvocationListener() {
            public void onStart(InvocationInfo invocationInfo) {
            }

            public void onSuccess(InvocationInfo invocationInfo) {
                if(invocationInfo.getResult() != null) {
                    int stepIndex = 0;
                    MyHttpScenarioInvocationResult invocationResult = (MyHttpScenarioInvocationResult)invocationInfo.getResult();
                    Map<String,Double> stepInvocationResults = invocationResult.getStepInvocationResults();

                    for(Map.Entry<String,Double> entry : stepInvocationResults.entrySet()) {
                        stepIndex++;
                        String scenarioStepId = StandardMetricsNamesUtil.generateScenarioStepId(invocationResult.getScenarioId(), entry.getKey(), stepIndex);
                        if(!createdMetrics.contains(scenarioStepId)) {
                            createdMetrics.add(scenarioStepId);
                            this.createScenarioStepMetricDescriptions(scenarioStepId, String.format("%02d. %s ", stepIndex, entry.getKey()));
                        }

                        getMetricService().saveValue(StandardMetricsNamesUtil.generateMetricId(scenarioStepId, LATENCY_ID), entry.getValue() / 1000.0D);
                    }
                }

            }

            private void createScenarioStepMetricDescriptions(String scenarioStepId, String scenarioStepDisplayName) {
                MetricDescription metricDescription =
                        (new MetricDescription(StandardMetricsNamesUtil.generateMetricId(scenarioStepId, LATENCY_ID))).
                        displayName(StandardMetricsNamesUtil.generateMetricDisplayName(scenarioStepDisplayName, LATENCY_SEC)).
                        plotData(true).
                        addAggregator(DefaultAggregatorsProvider.AVG_AGGREGATOR);
                getMetricService().createMetric(metricDescription);
            }

            public void onFail(InvocationInfo invocationInfo, InvocationException e) {
                // do nothing
            }

            public void onError(InvocationInfo invocationInfo, Throwable error) {
                // do nothing
            }
        };
    }
}
