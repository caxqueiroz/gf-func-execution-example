package io.cax.gf.demo;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import io.cax.gf.demo.domain.Tick;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cq on 25/4/16.
 */
@Configuration
public class DemoConfig {

    @Value("${gemfire.locator.address}")
    private String locatorAddress;

    @Value("${gemfire.locator.port}")
    private int locatorPort;

    @Bean
    ClientCache clientCache(){
        ClientCache cache = new ClientCacheFactory()
                .addPoolLocator(locatorAddress, locatorPort)
                .create();
        return cache;
    }

    @Bean
    TickRepository repository(ClientCache cache){
        Region<Long, Tick> region = cache.<Long, Tick>createClientRegionFactory(ClientRegionShortcut.PROXY).create("tick");
        TickRepository repository = new TickRepository();
        repository.setRegion(region);
        return repository;
    }

}