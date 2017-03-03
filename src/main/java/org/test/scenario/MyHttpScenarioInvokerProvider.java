package org.test.scenario;

import com.griddynamics.jagger.engine.e1.Provider;
import com.griddynamics.jagger.invoker.Invoker;

public class MyHttpScenarioInvokerProvider implements Provider<Invoker> {
    public MyHttpScenarioInvokerProvider() {
    }

    public Invoker provide() {
        return new MyHttpScenarioInvoker();
    }
}
