package com.sys.user.web.controller;

import com.sys.user.dto.UserDTO;
import com.sys.user.web.exeption.UserNotFoundException;
import com.sys.user.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/current")
    public Principal getUser(OAuth2Authentication principal) {

        return principal.getUserAuthentication();
    }

    @CrossOrigin
    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDTO user) {
        UserDTO userSave = userService.createUser(user);
        if (userSave == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }


    @CrossOrigin
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        UserDTO user = userService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("Invalid userID");
        }
        return user;
    }

    @CrossOrigin
    @GetMapping(value = "/establish/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserDTO> getUsersByEstablishmentId(@PathVariable("id") Long id) {
        List<UserDTO> users = userService.usersByEstablishmentId(id);
        if (users == null) {
            throw new UserNotFoundException("Invalid userID");
        }
        return users;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public void logMeOut(HttpServletRequest request) {
        String token = (String) request.getAttribute("OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE");
        if (token != null) {
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
            if (oAuth2AccessToken != null) {
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }
            tokenStore.readAccessToken(token);
        }
    }

}

