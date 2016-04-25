package io.cax.gf.demo;

import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        System.out.println(rc.getResult());

	}
}
