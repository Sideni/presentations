package com.csgames.exodus.controllers;

import com.csgames.exodus.dao.UserDAO;
import com.csgames.exodus.model.User;
import com.csgames.exodus.util.HibernateUtil;
import com.csgames.exodus.util.LoggedInUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.InvalidPropertiesFormatException;

/**
 * Panel page
 */
@Controller
@RequestMapping("/")
public class PanelController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        if (request.getSession().getAttribute(HibernateUtil.STATE) == null || request.getSession().getAttribute(HibernateUtil.STATE).toString().equals(HibernateUtil.LOGGED_OUT)) {
            return "redirect:/";
        }
        if (request.getSession().getAttribute(HibernateUtil.STYLE) == null) {
            AdminController.setTheme(request, "/resources/css/green.css");
        }

        return "panel";
    }

    @RequestMapping(value = "/panel/{page}", method = RequestMethod.GET)
    public String page(HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable String page, @RequestParam(value="prefix", defaultValue="panel/") String prefix) throws UnsupportedEncodingException {
        if (request.getSession().getAttribute(HibernateUtil.STATE) == null || request.getSession().getAttribute(HibernateUtil.STATE).toString().equals(HibernateUtil.LOGGED_OUT)) {
            return "redirect:/";
        }

        // Decoding to avoid weird characters bug
        page = prefix + java.net.URLDecoder.decode(page, StandardCharsets.UTF_8.name()) + ".jsp";
        model.addAttribute("page", page);
        return "panel";
    }

    @RequestMapping(value = "/panel", params={"theme"}, method = RequestMethod.POST)
    public String theme(@RequestParam("theme") String theme, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        if (request.getSession().getAttribute(HibernateUtil.STATE) == null || request.getSession().getAttribute(HibernateUtil.STATE).toString().equals(HibernateUtil.LOGGED_OUT)) {
            return "redirect:/";
        }

        AdminController.setTheme(request, theme);

        return "panel";
    }

    @RequestMapping(value = "/panel", params = {"name", "email"}, method = RequestMethod.POST)
    public String updateUser(@RequestParam("name") String name, @RequestParam("email") String email, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        if (request.getSession().getAttribute(HibernateUtil.STATE) == null || request.getSession().getAttribute(HibernateUtil.STATE).toString().equals(HibernateUtil.LOGGED_OUT)) {
            return "redirect:/";
        }

        try {
            String username = LoggedInUserUtil.getUsername((String) request.getSession().getAttribute(HibernateUtil.TOKEN));
            userDAO.updateUser(username, name, email);
            request.getSession().setAttribute(HibernateUtil.TOKEN, User.genToken(userDAO.getUser(username)));
        } catch (InvalidPropertiesFormatException e) {
            model.addAttribute("update_error", HibernateUtil.INVALID_TOKEN);
        } catch(InvalidParameterException e) {
            model.addAttribute("update_error", HibernateUtil.RESTRICTED_S);
        }

        return "panel";
    }

}
