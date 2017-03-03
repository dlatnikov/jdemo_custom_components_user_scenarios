package org.test.scenario;

import com.griddynamics.jagger.user.test.configurations.JLoadScenario;
import com.griddynamics.jagger.user.test.configurations.JLoadTest;
import com.griddynamics.jagger.user.test.configurations.JParallelTestsGroup;
import com.griddynamics.jagger.user.test.configurations.JTestDefinition;
import com.griddynamics.jagger.user.test.configurations.auxiliary.Id;
import com.griddynamics.jagger.user.test.configurations.load.JLoadProfile;
import com.griddynamics.jagger.user.test.configurations.load.JLoadProfileInvocation;
import com.griddynamics.jagger.user.test.configurations.load.auxiliary.InvocationCount;
import com.griddynamics.jagger.user.test.configurations.load.auxiliary.ThreadCount;
import com.griddynamics.jagger.user.test.configurations.termination.JTerminationCriteria;
import com.griddynamics.jagger.user.test.configurations.termination.JTerminationCriteriaIterations;
import com.griddynamics.jagger.user.test.configurations.termination.auxiliary.IterationsNumber;
import com.griddynamics.jagger.user.test.configurations.termination.auxiliary.MaxDurationInSeconds;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyHttpScenarioJLoadScenarioProvider {

    @Bean
    public JLoadScenario myHttpScenarioJaggerLoadScenario() {

        JTestDefinition jTestDefinition =
                JTestDefinition.builder(Id.of("my_http_scenario_td_example"), new MyHttpScenarioEndpointsProvider()).
                withInvoker(new MyHttpScenarioInvokerProvider()).
                addListener(new MyHttpScenarioInvocationListener()).
                build();

        JLoadProfile jLoadProfileRps =
                JLoadProfileInvocation.builder(InvocationCount.of(3), ThreadCount.of(1)).
                build();
        
        JTerminationCriteria jTerminationCriteria =
                JTerminationCriteriaIterations.of(IterationsNumber.of(3), MaxDurationInSeconds.of(30));
        
        JLoadTest jLoadTest =
                JLoadTest.builder(Id.of("my_http_scenario_lt_example"), jTestDefinition, jLoadProfileRps, jTerminationCriteria).
                build();
        
        JParallelTestsGroup jParallelTestsGroup =
                JParallelTestsGroup.builder(Id.of("my_http_scenario_ptg_example"), jLoadTest).
                build();
        
        // To launch your load scenario, set 'jagger.load.scenario.id.to.execute' property's value equal to the load scenario id
        // You can do it via system properties or in the 'environment.properties' file
        return JLoadScenario.builder(Id.of("my_http_scenario_ls_example"), jParallelTestsGroup).
                build();
    }
}

