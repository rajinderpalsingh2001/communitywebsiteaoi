package com.nagarro.community.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.community.dto.AuthenticationDto;
import com.nagarro.community.dto.UserDto;
import com.nagarro.community.entity.User;
import com.nagarro.community.service.UserService;
import com.nagarro.community.service.jwt.UserDetailsServiceImpl;
import com.nagarro.community.util.JwtUtil;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.jdbc.Expectations;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    UserDto dto;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationDto authenticationDTO) throws JSONException {
     
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),
                    authenticationDTO.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getEmail());
        User user = service.findByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), user.getRoles());

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Expose-Headers", "Authorization");
        responseHeaders.set("Access-Control-Allow-Headers",
                "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
        responseHeaders.set(HEADER_STRING, TOKEN_PREFIX + jwt);
        return new ResponseEntity<>(new JSONObject()
                .put("id", user.getId())
                .put("role", user.getRoles())
                .toString(), responseHeaders, HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserDto> addUser(@RequestBody User user) {
        try {
          
            if (service.findByEmail(user.getEmail()) != null) {
                return new ResponseEntity<UserDto>(null, null, HttpStatus.ALREADY_REPORTED);
            } else {
                UserDto userDto = service.addUser(user);
                return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<UserDto>(null,null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public User getUserById(int id) {
        return service.findUserById(id);
    }

}
