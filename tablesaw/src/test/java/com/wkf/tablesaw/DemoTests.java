package com.wkf.tablesaw;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import javax.annotation.Resource;


/**
 * @author wuKeFan
 * @date 2024-06-13 09:58:00
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TableSawApplication.class)
public class DemoTests {
    private final Logger log = LoggerFactory.getLogger(getClass());
    Table tornadoes;

    @Before
    public void before() {
        log.info("init some data");
        tornadoes = Table.read().csv("D:/gitProject/springboot-demo/tablesaw/src/main/resources/data/tornadoes_1950-2014.csv");
    }
    @After
    public void after(){
        log.info("clean some data");
    }
    @Test
    public void execute()  {
        log.info("your method test Code");
    }

    @Test
    public void columnNames() {
        System.out.println(tornadoes.columnNames());
    }
    @Test
    public void shape() {
        System.out.println(tornadoes.shape());
    }
    @Test
    public void structure() {
        System.out.println(tornadoes.structure().printAll());
    }
    @Test
    public void show() {
        System.out.println(tornadoes);
    }
    @Test
    public void structurefilter() {
        System.out.println( tornadoes
                .structure()
                .where(tornadoes.structure().stringColumn("Column Type").isEqualTo("DOUBLE")));

    }
    @Test
    public void previewdata() {
        System.out.println(tornadoes.first(3));
    }
    @Test
    public void ColumnOperate() {
        StringColumn month = tornadoes.dateColumn("Date").month();
        tornadoes.addColumns(month);
        System.out.println(tornadoes.first(3));
        tornadoes.removeColumns("State No");
        System.out.println(tornadoes.first(3));

    }
    @Test
    public void sort() {
        tornadoes.sortOn("-Fatalities");
        System.out.println(tornadoes.first(20));
    }
    @Test
    public void summary() {
        System.out.println( tornadoes.column("Fatalities").summary().print());
    }
    @Test
    public void filter() {
        Table result = tornadoes.where(tornadoes.intColumn("Fatalities").isGreaterThan(0));
        result = tornadoes.where(result.dateColumn("Date").isInApril());
        result =
                tornadoes.where(
                        result
                                .intColumn("Width")
                                .isGreaterThan(300) // 300 yards
                                .or(result.doubleColumn("Length").isGreaterThan(10))); // 10 miles

        result = result.select("State", "Date");
        System.out.println(result);
    }
    @Test
    public void write() {
        tornadoes.write().csv("rev_tornadoes_1950-2014-test.csv");
    }
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Test
    public void dataFromMySql() {
        Table table = jdbcTemplate.query("SELECT  user_id,username,age from user_info", resultSet -> {
            return Table.read().db(resultSet);
        });
        System.out.println(table);
    }



}
