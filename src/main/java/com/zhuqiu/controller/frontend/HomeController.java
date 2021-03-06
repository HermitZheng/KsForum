package com.zhuqiu.controller.frontend;

import com.alibaba.fastjson.JSONObject;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.pojo.User;
import com.zhuqiu.service.ArticleFunctionService;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CategoryService;
import com.zhuqiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuqiu
 * @date 2020/2/19
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleFunctionService functionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("")
    public String home(Model model) {
        HashMap<String, Object> top = new HashMap<>();
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(2);
        top.put("articleStatus", statusList);

        List<Article> topList;
        if (redisTemplate.opsForValue().get("topList") != null) {
            topList = (List<Article>) redisTemplate.opsForValue().get("topList");
        } else {
            topList = articleService.listAllNotWithContent(top);
            redisTemplate.opsForValue().set("topList", topList, 1, TimeUnit.HOURS);
        }
        model.addAttribute("topList", topList);

        List<Article> articleByComment;
        if (redisTemplate.opsForValue().get("articleByC") != null) {
            articleByComment = (List<Article>) redisTemplate.opsForValue().get("articleByC");
        } else {
            articleByComment = functionService.listArticleByCommentCount(10);
            redisTemplate.opsForValue().set("articleByC", articleByComment, 1, TimeUnit.HOURS);
        }
        model.addAttribute("articleByComment", articleByComment);

        List<Article> articleByUpdate;
        if (redisTemplate.opsForValue().get("articleByUp") != null) {
            articleByUpdate = (List<Article>) redisTemplate.opsForValue().get("articleByUp");
        } else {
            articleByUpdate = functionService.getLastUpdateArticle(10);
            redisTemplate.opsForValue().set("articleByUp", articleByUpdate, 1, TimeUnit.HOURS);
        }
        model.addAttribute("articleByUpdate", articleByUpdate);

        sideItem(model);

        return "/Home/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "/Home/login";
    }

    @ResponseBody
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    public String loginVerify(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("userName");
        String password = request.getParameter("userPass");
        System.out.println(username + password);

        User user = userService.findUserByName(username);
        if (user == null) {
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


    @RequestMapping("/register")
    public String register() {
        return "/Home/reg";
    }

    @ResponseBody
    @RequestMapping(value = "/regVerify", method = RequestMethod.POST)
    public String regVerify(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String userName = request.getParameter("userName");
        String userNickname = request.getParameter("userNickname");
        String userPass = request.getParameter("userPass");
        String rePass = request.getParameter("rePass");

        User user = userService.findUserByName(userName);
        if (user != null) {
            map.put("status", "error");
            map.put("msg", "用户名已存在！");
//        }else if (userService.findUserByNickName(userNickname) != null) {
//            map.put("status", "error");
//            map.put("msg", "昵称已存在！");
        } else if (!userPass.equals(rePass)) {
            map.put("status", "error");
            map.put("msg", "密码输入有误！");
        } else {
            user = new User();
            user.setUserName(userName);
            user.setUserNickname(userNickname);
            user.setUserPass(userPass);
            user.setUserRegisterTime(new Date());
            String avatar = File.separator + "uploads" + File.separator + "default" + File.separator + "default.jpg";
            user.setUserAvatar(avatar);
            userService.insertUser(user);

            map.put("status", "success");
            map.put("msg", "注册成功！");

            request.getSession().setAttribute("user", user);
        }

        String result = new JSONObject(map).toString();
        return result;
    }


    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/home/login";
    }

    public void sideItem(Model model) {
        HashMap<String, Object> notice = new HashMap<>();
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(3);
        notice.put("articleStatus", statusList);

        List<Article> noticeList;
        if (redisTemplate.opsForValue().get("noticeList") != null) {
            noticeList = (List<Article>) redisTemplate.opsForValue().get("noticeList");
        } else {
            noticeList = articleService.listAllNotWithContent(notice);
            redisTemplate.opsForValue().set("noticeList", noticeList, 1, TimeUnit.HOURS);
        }
        model.addAttribute("noticeList", noticeList);

        List<Category> allCategory;
        if (redisTemplate.opsForValue().get("allCategory") != null) {
            allCategory = (List<Category>) redisTemplate.opsForValue().get("allCategory");
        } else {
            allCategory = categoryService.listCategoryWithArticleCount();
            redisTemplate.opsForValue().set("allCategory", allCategory, 1, TimeUnit.HOURS);
        }
        model.addAttribute("allCategory", allCategory);

    }
}
