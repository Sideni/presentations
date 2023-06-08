package com.montrehack.app.controllers;

import com.montrehack.app.dao.UserDAO;
import com.montrehack.app.model.User;

import com.montrehack.app.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        return "index";
    }

    @RequestMapping(value = "/login", params = {"username", "password"}, method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
        boolean logged_in = false;

        try {
            logged_in = userDAO.login(username, password, model);
        } catch (InvalidParameterException e) {
            model.addAttribute("login_error", HibernateUtil.RESTRICTED_S);
            return "index";
        }

        if(logged_in) {
            request.getSession().setAttribute(HibernateUtil.STATE, HibernateUtil.LOGGED_IN);
            return "redirect:/panel";
        } else {
            model.addAttribute("login_error", HibernateUtil.INVALID_LOGIN);
            request.getSession().setAttribute(HibernateUtil.STATE, HibernateUtil.LOGGED_OUT);
            request.getSession().invalidate();
            return "index";
        }
    }

}
