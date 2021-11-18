package com.stacksimplify.restservices.config;

import com.stacksimplify.restservices.utils.PropertiesReader;
import io.micrometer.core.lang.Nullable;
import io.micrometer.appoptics.AppOpticsConfig;
import io.micrometer.appoptics.AppOpticsMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import java.io.IOException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitoringConfig {

  AppOpticsConfig appopticsConfig = new AppOpticsConfig() {
    @Override
    public String apiToken() {
      PropertiesReader propertiesReader = null;
      try {
        propertiesReader = new PropertiesReader("application.properties");
      } catch (IOException e) {
        e.printStackTrace();
      }
      return propertiesReader.getProperty("management.metrics.export.appoptics.api-token");
    }

    @Override
    @Nullable
    public String get(String k) {
      return null;
    }
  };
  MeterRegistry registry = new AppOpticsMeterRegistry(appopticsConfig, Clock.SYSTEM);

}
