package test;

import cn.qlq.MySpringBootApplication;
import cn.qlq.jooq.tables.COUNTRY;
import cn.qlq.jooq.tables.USER;
import cn.qlq.jooq.tables.USERCOUNTRY;
import cn.qlq.jooq.tables.daos.CountryDao;
import cn.qlq.jooq.tables.daos.UserDao;
import cn.qlq.jooq.tables.daos.UsercountryDao;
import cn.qlq.jooq.tables.pojos.Country;
import cn.qlq.jooq.tables.pojos.User;
import cn.qlq.jooq.tables.pojos.Usercountry;
import cn.qlq.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringBootApplication.class)
@ServletComponentScan("cn.qlq")
public class Jooqtest {

    @Autowired
    @Qualifier("userDao2")
    private UserDao userDao;

    @Autowired
    @Qualifier("countryDao2")
    private CountryDao countryDao;

    @Autowired
    private UsercountryDao userCountryDao;

    @Autowired
    private DSLContext context;

    @PostConstruct//表示在userDao构造完成之后执行
    private void createDao() {
        userDao = new UserDao(context.configuration());
        countryDao = new CountryDao(context.configuration());
        userCountryDao = new UsercountryDao(context.configuration());
    }

    @Test
    public void testAdd() {
        // 添加用户
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan");
        user.setUserfullname("张三");
        user.setAddress("测试地址");
        user.setSex("男");
        userDao.insert(user);

        // 添加城市，添加两个
        Country country = new Country();
        country.setId(1);
        country.setCountryname("中国北京");
        Country country2 = new Country();
        country2.setId(2);
        country2.setCountryname("中国山西");
        countryDao.insert(country, country2);

        // 维护关系
        Usercountry userCountry = new Usercountry();
        userCountry.setUserid(1);
        userCountry.setCountryid(1);
        Usercountry userCountry2 = new Usercountry();
        userCountry2.setUserid(1);
        userCountry2.setCountryid(2);
        userCountryDao.insert(userCountry, userCountry2);

        System.out.println("增加成功");
    }

    @Test
    public void testSelect() {
        List<User> users = userDao.findAll();
        System.out.println(users);
    }

    @Test
    public void testDelete() {
        userDao.deleteById(1);
        System.out.println("删除成功");
    }

    // =====S 使用dslContext操作
    private USER TABLE_USER = USER.USER;
    private COUNTRY TABLE_COUNTRY = COUNTRY.COUNTRY;
    private USERCOUNTRY TABLE_USER_COUNTRY = USERCOUNTRY.USERCOUNTRY;

    /**
     * DSL 查询单个
     */
    @Test
    public void testSelect2() {
        Result<UserRecord> users = context.selectFrom(TABLE_USER).where("id = 1").and(TABLE_USER.ID.equal(1)).orderBy(1).fetch();
        for (UserRecord userRecord : users) {
            System.out.println(userRecord);
        }

        System.out.println("=====");

        List<User> results = context.selectFrom(TABLE_USER).where("id = 1").and(TABLE_USER.ID.equal(1)).orderBy(1).fetchInto(User.class);
        System.out.println(results);
    }

    /**
     * 联合查询
     */
    @Test
    public void testSelect3() {
        // 简单的联合查询
        Result<Record4<Integer, String, String, String>> fetch = context.select(TABLE_USER.ID, TABLE_USER.USERNAME, TABLE_USER.USERFULLNAME, TABLE_COUNTRY.COUNTRYNAME)
                .from(TABLE_USER, TABLE_USER_COUNTRY, TABLE_COUNTRY)
                .where(TABLE_USER.ID.equal(TABLE_USER_COUNTRY.USERID)).and(TABLE_USER_COUNTRY.USERID.equal(TABLE_COUNTRY.ID)).fetch();
        System.out.println(fetch);

        System.out.println("====1====");

        // 处理结果塞到map中
        List<Map<String, Object>> results = new ArrayList<>();
        fetch.forEach(record -> {
            Map<String, Object> map = new HashMap<>();
            Field<?>[] fields = record.fields();
            for (Field field : fields) {
                Object value = record.getValue(field);
                map.put(field.getName(), value);
            }
            results.add(map);
        });
        System.out.println(results);

        System.out.println("====2====");

        // 查询直接映射到VO中
        List<UserCountryVO> userCountryVOS = context.select(TABLE_USER.ID, TABLE_USER.USERNAME, TABLE_USER.USERFULLNAME, TABLE_COUNTRY.COUNTRYNAME)
                .from(TABLE_USER, TABLE_USER_COUNTRY, TABLE_COUNTRY)
                .where(TABLE_USER.ID.equal(TABLE_USER_COUNTRY.USERID)).and(TABLE_USER_COUNTRY.COUNTRYID.equal(TABLE_COUNTRY.ID)).fetchInto(UserCountryVO.class);
        System.out.println(userCountryVOS);
    }
}
