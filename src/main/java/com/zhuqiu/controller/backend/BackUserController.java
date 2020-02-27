package com.zhuqiu.controller.backend;

import com.alibaba.fastjson.JSONObject;
import com.zhuqiu.pojo.User;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuqiu
 * @date 2020/2/10
 */

@Controller("backUserController")
@RequestMapping("/back/user")
public class BackUserController {

    @Autowired
    private UserService userService;


    /**
     * 跳转至用户列表界面
     *
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(Model model) {
        List<User> userList = userService.findAllUser();
        model.addAttribute("userList", userList);

        return "/Back/User/index";
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @RequestMapping("/delete/{userId}")
    public String delete(@PathVariable("userId") Integer userId) {
        userService.deleteUserById(userId);
        return "redirect:/back/user";
    }

    /**
     * 跳转至编辑用户界面
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Integer userId, Model model) {
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "/Back/User/edit";
    }

    /**
     * 跳转至新增用户界面
     *
     * @return
     */
    @RequestMapping("/insert")
    public String insert() {
        return "/Back/User/insert";
    }

    /**
     * 提交新增用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertSubmit(User user) {
        User _user = userService.findUserByName(user.getUserName());
        if (_user == null) {
            user.setUserRegisterTime(new Date());
            userService.insertUser(user);
        }
        return "redirect:/back/user";
    }

    /**
     * 检查用户名是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("username");
        User user = userService.findUserByName(username);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if (user != null) {
            if (user.getUserId() != id) {
                map.put("code", 1);
                map.put("msg", "用户名已存在！");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    @RequestMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("admin");
        User user = userService.findUserById(sessionUser.getUserId());

        model.addAttribute("user", user);
        return "Back/User/profile";
    }

    /**
     * 编辑用户提交
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editUserSubmit(HttpServletRequest request, User user) {
        userService.updateUser(user);
        request.getSession().setAttribute("admin", user);
        return "redirect:/back/user";
    }
}
