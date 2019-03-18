package my.sdtest.backend2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SomeService {

    @Autowired
    JdbcTemplate jdbc;

    public void someTxProcess(String name) {

        jdbc.execute(String.format("INSERT INTO T1 VALUES (1, '%s')", name));
        /*
        try {
            Thread.sleep(2000L);
        } catch (Exception e){}
        */
        if (name.contains("error")) {
            throw new RuntimeException("rollback confirmation");
        }

        jdbc.execute(String.format("INSERT INTO T2 VALUES (1, '%s')", name));

    }

    public int count(String name) {

       return jdbc.queryForObject(String.format("Select count(*) from T1 where name ='%s'", name), Integer.class);

    }

}
