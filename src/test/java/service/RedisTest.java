package service;

/**
 * @author zhuqiu
 * @date 2020/3/20
 */
import com.zhuqiu.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
@WebAppConfiguration
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

//    @Before
//    public void before(){
//        ApplicationContext ctx = new ClassPathXmlApplicationContext();
//        redisTemplate = ctx.getBean(RedisTemplate.class);
//    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void Test01() {
        User user = new User();
        user.setUserName("redisTest");
        user.setUserNickname("redis");
        user.setUserPass("wofole");
        user.setUserProfile("爷是redis机器人一号嘻嘻");

        //存储到到内存中的不是map而是string，进行了序列化
        redisTemplate.opsForValue().set("user_1", user);
        User user1 = (User) redisTemplate.opsForValue().get("user_1");
        //上面两步不能保证每次使用RedisTemplate是操作同一个对Redis的连接

        System.out.println(user1.toString());
    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void Test02(){
        User user = new User();
        user.setUserName("redisTest");
        user.setUserNickname("redis");
        user.setUserPass("wofole");
        user.setUserProfile("爷是redis机器人一号嘻嘻");

        SessionCallback callback = new SessionCallback<User>(){
            public User execute(RedisOperations ops) throws DataAccessException {
                ops.boundValueOps("role_1").set(user);
                return (User) ops.boundValueOps("role_1").get();
            }
        };
        User savedRole = (User) redisTemplate.execute(callback);
        System.out.println(savedRole.getUserName());
    }


}