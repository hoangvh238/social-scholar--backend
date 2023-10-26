package com.social.app.controller;

import com.social.app.dto.GroupDTO;
import com.social.app.dto.UserDTO;
import com.social.app.entity.ResponseObject;
import com.social.app.entity.UserResponse;
import com.social.app.model.Bill;
import com.social.app.model.Groups;
import com.social.app.model.JoinManagement;
import com.social.app.model.User;
import com.social.app.repository.BillRepository;
import com.social.app.service.BillService;
import com.social.app.service.GroupServices;
import com.social.app.service.ResponseConvertService;
import com.social.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    ResponseConvertService responseConvertService;

    @Autowired
    BillService billService;
    @Autowired
    GroupServices groupServices;
    @GetMapping("/getalluser")
    @PreAuthorize("hasRole('ADMIN')")
    public ArrayList<UserDTO> getalluser(){
        ArrayList<User> listUser = userService.findAll();
        return  userService.userResponses(listUser);
    }
    @GetMapping("/getallgroup/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ArrayList<GroupDTO> getAllGroupOfUser(@PathVariable int userId){
       User user= userService.loadUserById(userId);
       ArrayList<Groups> groups  = new ArrayList<>();
        for (JoinManagement join: user.getJoins()
             ) {
            groups.add(join.getGroup());
        }
       return  groupServices.groupsResponses(groups);
    }

    @GetMapping("/count-users")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> countDonePayment(){
        long result = userService.countUser();
        if(result==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Empty", "No User found", ""));
        }else  return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Count Success", result));
    }

    @GetMapping("/getAllBill")
    public ResponseEntity<ResponseObject> getAllBill(){
        ArrayList<Bill> result = billService.allBuill();
        if(result== null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Empty", "No Bill found", ""));
        }else  return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Get all bill done", billService.ArrayListPostDTO(result)));
    }
}
