package org.test.scenario;


import com.griddynamics.jagger.invoker.v2.JHttpEndpoint;
import com.griddynamics.jagger.invoker.v2.JHttpQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyHttpScenarioEndpointsProvider implements Iterable {
    private static Logger log = LoggerFactory.getLogger(MyHttpScenarioEndpointsProvider.class);

    private List<MyHttpScenario> userScenarios = new ArrayList<>();

    public MyHttpScenarioEndpointsProvider() {

        MyHttpScenario userScenario = new MyHttpScenario("scenario_1", "My User Scenario");

        userScenario
                .addStep(new MyHttpStep("step1",new JHttpEndpoint("https://httpbin.org/")) {{
                    setWaitAfterExecutionInSeconds(1);
                    setQuery(new JHttpQuery().get().path("/get"));
                }})
                .addStep(new MyHttpStep("step2",new JHttpEndpoint("https://httpbin.org/")){{
                    setWaitAfterExecutionInSeconds(1);
                    setQuery(new JHttpQuery().get().path("/html"));
                }})
                .addStep(new MyHttpStep("step3",new JHttpEndpoint("https://httpbin.org/")){{
                    setWaitAfterExecutionInSeconds(1);
                    setQuery(new JHttpQuery().get().path("/xml"));
                }});


        userScenarios.add(userScenario);
    }

    @Override
    public Iterator<MyHttpScenario> iterator() {
        return userScenarios.iterator();
    }
}
