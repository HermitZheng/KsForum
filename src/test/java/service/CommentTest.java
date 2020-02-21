package service;

import com.zhuqiu.pojo.Comment;
import com.zhuqiu.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/6
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
@WebAppConfiguration
public class CommentTest {

    @Autowired
    private CommentService commentService;

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void insertComment(){
        Comment comment = new Comment();
        comment.setArticleId(1);
        comment.setCommentAuthorId(1);
        comment.setCommentCreateTime(new Date());
        comment.setCommentContent("第一篇文章的第一条评论测试。");

        commentService.insertComment(comment);

        List<Comment> comments = commentService.listRecentComment(1);

        System.out.println(comments);
    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void comment(){
        List<Comment> allComment = commentService.findAllComment();
        for (Comment comment : allComment) {
            System.out.println(comment);
        }
    }
}
