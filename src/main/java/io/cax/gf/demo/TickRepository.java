package io.cax.gf.demo;

import com.gemstone.gemfire.cache.Region;
import com.google.common.collect.Maps;
import io.cax.gf.demo.domain.Tick;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by cq on 25/4/16.
 */
@Component
public class TickRepository {

    private Region<Long, Tick> region;

    public void setRegion(Region<Long, Tick> region) {
        this.region = region;
    }


    public void save(Tick tick){

        region.put(tick.getTime(),tick);

    }

    public void saveAll(List<Tick> tickList){

        Map map = tickList.stream().map(tick -> {
            Map<Long, Tick> m = Maps.newHashMap();
            m.put(tick.getTime(),tick);
            return m;
        }).reduce((m0, m1) -> {
            m0.putAll(m1);
            return m0;
        }).orElse(null);

        if(map!=null) region.putAll(map);

    }

    public Region<Long, Tick> getRegion() {
        return region;
    }
}
