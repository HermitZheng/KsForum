package com.zhuqiu.controller.backend;

import com.alibaba.fastjson.JSONObject;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Comment;
import com.zhuqiu.pojo.User;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CommentService;
import com.zhuqiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuqiu
 * @date 2020/2/7
 */

@Controller("backHomeController")
public class BackHomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;


    /**
     * 跳转至后台首页
     * @return
     */
    @RequestMapping("/back/index")
    public String index(Model model) {
        List<Comment> commentList = commentService.findAllComment();
        for (Comment comment : commentList) {
            comment.setArticle(articleService.findArticleNotWithContentById(comment.getArticleId()));
        }
        List<Article> articleList = articleService.listAllNotWithContent(new HashMap<>());
        model.addAttribute("commentList", commentList);
        model.addAttribute("articleList", articleList);

        return "Back/index";
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/back/login")
    public String loginPage() {
        return "Back/login";
    }


    /**
     * 确认登录信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/back/loginVerify", method = RequestMethod.POST)
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.findUserByName(username);
        if (user == null || !user.getUserName().equals("admin")) {
            map.put("status", "error");
            map.put("msg", "用户名或者密码错误！");
        } else if (!user.getUserPass().equals(password)) {
            map.put("status", "error");
            map.put("msg", "用户名或者密码错误！");
        } else {
            map.put("status", "success");
            map.put("msg", "验证成功！");

            request.getSession().setAttribute("user", user);
        }

        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/back/logout")
    public String logout(HttpSession session)  {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/back/login";
    }


}
