package io.cax.gf.demo.domain;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.cax.gf.demo.TickRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by cq on 25/4/16.
 */
@Component
public class TickLoader {

    Logger logger = LoggerFactory.getLogger(TickLoader.class);

    @Autowired
    private TickRepository repository;



    @PostConstruct
    public void init(){

        try{
            CsvMapper mapper = new CsvMapper();

            CsvSchema schema = CsvSchema.builder()
                    .addColumn("time", CsvSchema.ColumnType.NUMBER)
                    .addColumn("ask", CsvSchema.ColumnType.NUMBER)
                    .addColumn("bid", CsvSchema.ColumnType.NUMBER)
                    .addColumn("instrument", CsvSchema.ColumnType.STRING)
                    .build();

            MappingIterator<Tick> mappingIterator = mapper.readerFor(Tick.class).with(schema).readValues(TickLoader.class.getResourceAsStream("/ticks_1000.csv"));

            repository.saveAll(mappingIterator.readAll());

        }catch(Exception e){
            logger.error("Error loading ticks",e);
        }
        ;
    }

}
