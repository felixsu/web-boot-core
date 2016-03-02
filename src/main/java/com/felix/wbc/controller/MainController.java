package com.felix.wbc.controller;

import com.felix.wbc.model.Greeting;
import com.felix.wbc.model.UserRepositoryUsersDetails;
import com.felix.wbc.model.Users;
import com.felix.wbc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class.getName());

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private UserRepository userRepository;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    @ResponseBody
    public String homePage(ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to mysite. ");
        Users users = userRepository.findByUsername("felix");
        return "welcome";
    }

    @RequestMapping("/greeting")
    @ResponseBody
    public Greeting greeting() {
        updateAuthentication();
        return new Greeting(counter.incrementAndGet(),
                String.format(template, getPrincipal()));
    }


    public void updateAuthentication() {
        Connection connection = null;
        String clientId = "clientapp";
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT token_id, authentication FROM oauth_access_token WHERE client_id = ?");
            pstmt.setString(1, clientId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String tokenId = rs.getString("token_id");
                OAuth2Authentication oAuth2Authentication = deserialize(rs.getBytes("authentication"));
                System.out.println(oAuth2Authentication.toString());
            }

            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            LOGGER.error("Failed to find access token for client_id " + clientId, ex);
        } catch (RuntimeException ex) {
            LOGGER.error("Error occured.", ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    LOGGER.error("Close connection failed.");
                }
            }
        }
    }

    public static <T> T deserialize(byte[] byteArray) {
        ObjectInputStream oip = null;
        try {
            oip = new ObjectInputStream(new ByteArrayInputStream(byteArray));
            @SuppressWarnings("unchecked")
            T result = (T) oip.readObject();
            return result;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (oip != null) {
                try {
                    oip.close();
                } catch (IOException e) {
                    // eat it
                }
            }
        }
    }
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String adminPage(ModelMap model) {
//        model.addAttribute("user", getPrincipal());
//        return "admin";
//    }
//
//    @RequestMapping(value = "/db", method = RequestMethod.GET)
//    public String dbaPage(ModelMap model) {
//        model.addAttribute("user", getPrincipal());
//        return "dba";
//    }
//
//    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
//    public String accessDeniedPage(ModelMap model) {
//        model.addAttribute("user", getPrincipal());
//        return "accessDenied";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginPage() {
//        return "login";
//    }
//
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
//    }

    private String getPrincipal() {
        OAuth2Authentication h;
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

//    public static void main(String[] args)
//    {
//        Users users = new Users();
//        users.setUsername("felixsu");
//
//        UserRepositoryUsersDetails userRepositoryUsersDetails = new UserRepositoryUsersDetails(users);
//
//        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(userRepositoryUsersDetails, null);
//
//        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(null, userAuthentication);
//
//        {
//            //byte[] byteOfData = SerializationUtils.serialize(oAuth2Authentication);
//
//            //OAuth2Authentication data = SerializationUtils.deserialize(byteOfData);
//            //System.out.println(data);
//        }
//
//        {
//            byte[] byteOfData = SerializationUtils.serialize(userRepositoryUsersDetails);
//
//            UserRepositoryUsersDetails data = SerializationUtils.deserialize(byteOfData);
//            System.out.println(data);
//        }
//    }
}