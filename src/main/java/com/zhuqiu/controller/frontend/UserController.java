package com.zhuqiu.controller.frontend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuqiu.dto.PasswordParam;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Comment;
import com.zhuqiu.pojo.User;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CommentService;
import com.zhuqiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuqiu
 * @date 2020/2/22
 */

@Controller
@RequestMapping("/home/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;



    @RequestMapping("/{userId}")
    public String homePage(@PathVariable("userId") Integer userId,
                           Model model) {

        User user = userService.findUserById(userId);
        model.addAttribute("user", user);

        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("articleStatus", 1);
        criteria.put("userId", userId);
        List<Article> articleList = articleService.listAllNotWithContent(criteria);
        model.addAttribute("articleList", articleList);

        List<Comment> commentList = commentService.listCommentByUserId(userId);
        model.addAttribute("commentList", commentList);

        return "/Home/User/home";
    }

    @RequestMapping("/index")
    public String userIndex(HttpSession session, Model model){
        if (session.getAttribute("user") == null){
            return "redirect:/home/login";
        }

        User user = (User) session.getAttribute("user");
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("userId", user.getUserId());
        criteria.put("articleStatus", 1);
        List<Article> articleList = articleService.listAllNotWithContent(criteria);
        model.addAttribute("articleList", articleList);

//        user.setUserFavoriteCount();
        List<Article> favoriteList = userService.listFavoriteArticle(user.getUserId());
        model.addAttribute("favoriteList", favoriteList);

        user.setUserArticleCount(userService.countUserArticle(user.getUserId()));
        user.setUserFavoriteCount(userService.countUserFavorite(user.getUserId()));
        model.addAttribute("user", user);

        return "/Home/User/index";
    }

    @RequestMapping("/comment")
    public String userComment(HttpSession session, Model model){
        if (session.getAttribute("user") == null){
            return "redirect:/home/login";
        }
        User user = (User) session.getAttribute("user");
        List<Comment> commentList = commentService.listCommentByUserId(user.getUserId());
        model.addAttribute("commentList", commentList);

        user.setUserCommentCount(userService.countUserComment(user.getUserId()));
        model.addAttribute("user", user);

        return "/Home/User/comment";
    }

    @RequestMapping("/setting")
    public String userSetting(HttpSession session, Model model) {
        if (session.getAttribute("user") == null){
            return "redirect:/home/login";
        }
        User user = (User) session.getAttribute("user");
        user = userService.findUserById(user.getUserId());
        model.addAttribute("user", user);

        return "/Home/User/setting";
    }

    @RequestMapping(value = "/profileEdit", method = RequestMethod.POST)
    public String profileEdit(HttpServletRequest request, User user) {
        userService.updateUser(user);
        user = userService.findUserById(user.getUserId());
        request.getSession().setAttribute("user", user);
        return "redirect:/home/user/setting";
    }

    @RequestMapping(value = "/passEdit", method = RequestMethod.POST)
    @ResponseBody
    public String passEdit(PasswordParam param) {
        User user = userService.findUserById(param.getUserId());

        Map<String, Object> map = new HashMap<>();
        if (!user.getUserPass().equals(param.getOldPass())){
            map.put("status", "error");
            map.put("msg", "当前密码错误！");
        }else if (!param.getNewPass().equals(param.getRePass())) {
            map.put("status", "error");
            map.put("msg", "新密码输入有误！");
        }else {
            user.setUserPass(param.getNewPass());
            userService.updateUser(user);
            map.put("status", "success");
            map.put("msg", "密码更改成功！");
        }

        String result = JSON.toJSONString(new JSONObject(map));
        return result;
    }

    @ResponseBody
    @RequestMapping("/addFav/{articleId}")
    public String addFavorite(@PathVariable("articleId") Integer articleId,
                              HttpSession session){
        HashMap<String, Object> map = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user == null){
            map.put("status", "login");
            map.put("msg", "请先登录！");
            return JSON.toJSONString(new JSONObject(map));
        }
        try {
            userService.addFavorite(user.getUserId(), articleId);
            map.put("msg", "收藏成功！");
            map.put("status", "success");
        }catch (RuntimeException e){
            map.put("status", "error");
            map.put("msg", "收藏失败！");
        } finally {
//            System.out.println(map.toString());
//            String result = JSON.toJSONString(new JSONObject(map));
//            System.out.println(result);
            return JSON.toJSONString(new JSONObject(map));
        }
    }


    @RequestMapping("/deleteFav/{articleId}")
    public String deleteFavorite(@PathVariable("articleId") Integer articleId,
                                 HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null){
            return "redirect:/home/login";
        }
        userService.deleteFavorite(user.getUserId(), articleId);

        return "redirect:/home/article/" + articleId;
    }
}
