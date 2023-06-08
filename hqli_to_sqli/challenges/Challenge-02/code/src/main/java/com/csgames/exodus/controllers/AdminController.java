package com.csgames.exodus.controllers;

import com.csgames.exodus.dao.RebelLocationDAO;
import com.csgames.exodus.model.RebelLocation;
import com.csgames.exodus.util.HibernateUtil;
import com.csgames.exodus.util.LoggedInUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.InvalidParameterException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

/**
 * Admin page
 */
@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    RebelLocationDAO rebelLocationDAO;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(@RequestParam(value="country", defaultValue="Canada") String country, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        boolean isAdmin = false;
        try {
            isAdmin = LoggedInUserUtil.isAdmin((String) request.getSession().getAttribute(HibernateUtil.TOKEN));
        } catch (Exception e) {
            model.addAttribute("admin_login_error", HibernateUtil.INVALID_TOKEN);
        }
        if (request.getSession().getAttribute(HibernateUtil.STATE) == null || request.getSession().getAttribute(HibernateUtil.STATE).toString().equals(HibernateUtil.LOGGED_OUT) || !isAdmin) {
            model.addAttribute("admin_login_error", HibernateUtil.NOT_ADMIN);
            return "redirect:/";
        }
        if (request.getSession().getAttribute(HibernateUtil.STYLE) == null) {
            setTheme(request, "/resources/css/green.css");
        }

        try {
            List<RebelLocation> rebelLocations = rebelLocationDAO.getRebelLocation(country);
            model.addAttribute("rebelLocations", rebelLocations);
        } catch (InvalidParameterException e) {
            model.addAttribute("country_error", HibernateUtil.RESTRICTED_S);
        }

        Process proc = Runtime.getRuntime().exec("/admin_secret");

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        String adminSecret = stdInput.readLine();
        model.addAttribute("admin_secret", adminSecret);
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String theme(@RequestParam("theme") String theme, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        boolean isAdmin = false;
        try {
            isAdmin = LoggedInUserUtil.isAdmin((String) request.getSession().getAttribute(HibernateUtil.TOKEN));
        } catch (InvalidPropertiesFormatException e) {
            model.addAttribute("admin_login_error", HibernateUtil.INVALID_TOKEN);
        }
        if (request.getSession().getAttribute(HibernateUtil.STATE) == null || request.getSession().getAttribute(HibernateUtil.STATE).toString().equals(HibernateUtil.LOGGED_OUT) || !isAdmin) {
            model.addAttribute("admin_login_error", HibernateUtil.NOT_ADMIN);
            return "redirect:/";
        }

        setTheme(request, theme);

        return "admin";
    }

    public static boolean isAsciiPrintable(int ch) {
        return ch >= 9 && ch < 127;
    }

    public static void setTheme(HttpServletRequest request, String theme) {
        try {
            File file = new File("./webapps/ROOT/" + theme);

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));


            byte[] content = FileCopyUtils.copyToByteArray(inputStream);

            StringBuilder builder = new StringBuilder();
            for (byte c : content) {
                int i = c & 0xff;
                if (isAsciiPrintable(c) == false) {
                    builder.append("\\x");
                    String s = Integer.toHexString(i).toUpperCase();
                    if (s.length() % 2 != 0)
                        s = "0" + s;
                    builder.append(s);
                } else {
                    byte[] new_c = { c };
                    String character = new String(new_c);
                    if (character.equals("\\"))
                        character += "\\";
                    builder.append(character);
                }
            }

            request.getSession().setAttribute(HibernateUtil.THEME, theme);
            request.getSession().setAttribute(HibernateUtil.STYLE, builder.toString());
        } catch (IOException e) {
            // Theme won't change
        }
    }

}
