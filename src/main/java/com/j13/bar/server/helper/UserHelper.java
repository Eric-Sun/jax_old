package com.j13.bar.server.helper;

import com.j13.bar.server.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHelper {

    @Autowired
    UserDAO userDAO;


    public List<Integer> loadAllMachineUser(){
        return userDAO.loadAllMachineUser();
    }
}
