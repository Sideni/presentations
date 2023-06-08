package com.montrehack.app.controllers;

import com.montrehack.app.dao.UserDAO;
import com.montrehack.app.model.User;
import com.montrehack.app.util.HibernateUtil;
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

    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        if (request.getSession().getAttribute(HibernateUtil.STATE) == null || request.getSession().getAttribute(HibernateUtil.STATE).toString().equals(HibernateUtil.LOGGED_OUT)) {
            return "redirect:/";
        }

        return "panel";
    }

}
