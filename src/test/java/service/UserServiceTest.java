package service;

import com.zhuqiu.pojo.User;
import com.zhuqiu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhuqiu
 * @date 2020/2/5
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void insertUser(){
        User user = new User();
        user.setUserName("test1");
        user.setUserNickname("Robot1");
        user.setUserPass("wofole");
        user.setUserProfile("爷是测试机器人一号嘻嘻");

        User user1 = userService.insertUser(user);
        System.out.println(user1);
    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚
    public void findName(){
        User user = userService.findUserByName("register1");
        System.out.println(user==null);
    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void findUser(){
//        User user = userService.findUserById(1);
//        List<User> userList = userService.findAllUser();
        User user = userService.findUserById(1);
//        for (User user : userList) {
//            System.out.println(user);
//        }
        System.out.println(user);

    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚`
    public void updateUser(){
        User user = userService.findUserById(1);
        user.setUserNickname("ROBOT1");

        userService.updateUser(user);
        System.out.println(userService.findUserById(1));
    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚`
    public void setFavorite(){
        userService.addFavorite(1, 1);
        System.out.println(userService.countUserFavorite(1));
    }
}
