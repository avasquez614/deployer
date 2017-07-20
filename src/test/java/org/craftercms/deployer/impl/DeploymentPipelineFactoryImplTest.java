package org.craftercms.deployer.impl;

import java.io.FileReader;
import java.util.List;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.craftercms.commons.config.YamlConfiguration;
import org.craftercms.commons.spring.ApacheCommonsConfiguration2PropertySource;
import org.craftercms.deployer.api.DeploymentPipeline;
import org.craftercms.deployer.api.DeploymentProcessor;
import org.craftercms.deployer.test.utils.TestDeploymentProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.craftercms.deployer.impl.DeploymentConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DeploymentPipelineFactoryImplTest {

    private DeploymentPipelineFactoryImpl deploymentPipelineFactory;
    private HierarchicalConfiguration config;
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        deploymentPipelineFactory = new DeploymentPipelineFactoryImpl();
        config = createConfiguration();
        applicationContext = createApplicationContext();
    }

    @Test
    public void testGetPipeline() throws Exception {
        DeploymentPipeline pipeline = deploymentPipelineFactory.getPipeline(config, applicationContext,
                                                                            TARGET_DEPLOYMENT_PIPELINE_CONFIG_KEY);

        assertNotNull(pipeline);

        List<DeploymentProcessor> processors = pipeline.getProcessors();

        assertEquals(1, processors.size());
        assertEquals("This is a test", ((TestDeploymentProcessor) processors.get(0)).getText());
    }

    private HierarchicalConfiguration createConfiguration() throws Exception {
        ClassPathResource yamlResource = new ClassPathResource("targets/foobar-test.yaml");
        YamlConfiguration yamlConfig = new YamlConfiguration();

        yamlConfig.read(new FileReader(yamlResource.getFile()));
        yamlConfig.setProperty(TARGET_ID_CONFIG_KEY, "foobar-test");

        return yamlConfig;
    }

    private ApplicationContext createApplicationContext() {
        PropertySource yamlPropertySource = new ApacheCommonsConfiguration2PropertySource("yamlPropertySource", config);
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            new String[] { "targets/foobar-test-context.xml" }, false);

        applicationContext.getEnvironment().getPropertySources().addFirst(yamlPropertySource);
        applicationContext.refresh();

        return applicationContext;
    }

}
