package com.csgames.exodus.controllers;

import com.csgames.exodus.dao.UserDAO;
import com.csgames.exodus.model.User;

import com.csgames.exodus.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.InvalidParameterException;

/**
 * Welcome Page
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute(HibernateUtil.STATE) != null && request.getSession().getAttribute(HibernateUtil.STATE).toString().equals(HibernateUtil.LOGGED_IN)) {
            return "redirect:/panel";
        }
        return "index";
    }

    @RequestMapping(value = "/login", params = {"username", "password"}, method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
        boolean logged_in = false;
        try {
            logged_in = userDAO.login(username, password);
        } catch (InvalidParameterException e) {
            model.addAttribute("login_error", HibernateUtil.RESTRICTED_S);
            return "index";
        }

        if(logged_in) {
            User user = userDAO.getUser(username);

            request.getSession().setAttribute(HibernateUtil.STATE, HibernateUtil.LOGGED_IN);
            request.getSession().setAttribute(HibernateUtil.TOKEN, User.genToken(user));
            return "redirect:/panel";
        } else {
            model.addAttribute("login_error", HibernateUtil.INVALID_LOGIN);
            request.getSession().setAttribute(HibernateUtil.STATE, HibernateUtil.LOGGED_OUT);
            request.getSession().invalidate();
            return "index";
        }
    }

    @RequestMapping(value = "/create_account", params = {"username", "password"}, method = RequestMethod.POST)
    public String create(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password,
                         @RequestParam("confirm_password") String confirm_password, @RequestParam("name") String name, @RequestParam("email") String email, ModelMap model) {
        User user = null;
        try {
            user = userDAO.getUser(username);
        } catch (InvalidParameterException e) {
            model.addAttribute("create_error", HibernateUtil.RESTRICTED_S);
            return "index";
        }

        if(user != null) {
            model.addAttribute("create_error", HibernateUtil.EXISTS_ERROR);
            return "index";
        } else if (!password.equals(confirm_password)) {
            model.addAttribute("create_error", HibernateUtil.PASSWORDS_NOT_MATCHING);
            return "index";
        } else {
            user = new User(username, password, name, email);
            userDAO.createUser(user);
            request.getSession().setAttribute(HibernateUtil.TOKEN, User.genToken(user));
            request.getSession().setAttribute(HibernateUtil.STATE, HibernateUtil.LOGGED_IN);
            return "redirect:/panel";
        }
    }

}
