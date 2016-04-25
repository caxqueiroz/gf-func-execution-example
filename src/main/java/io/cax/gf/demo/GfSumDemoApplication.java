package io.cax.gf.demo;

import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


import static java.util.stream.Collectors.summingDouble;

@SpringBootApplication
public class GfSumDemoApplication implements CommandLineRunner{


    @Autowired
    TickRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(GfSumDemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {


        ResultCollector rc = FunctionService.onRegion(repository.getRegion()).withArgs(new String[]{"ask"}).execute("local-sum");
        List<Double> results = (List<Double>) rc.getResult();

        System.out.println("results per server: " + results);
        System.out.println("total result: " + results.stream().collect(summingDouble(d-> d.doubleValue())));

        System.out.println("custom collector: " +
                FunctionService.onRegion(repository.getRegion()).withArgs(new String[]{"ask"}).withCollector(new CustomResultCollector()).execute("local-sum"));

	}
}
