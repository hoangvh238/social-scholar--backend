package com.social.app.service;

import com.social.app.model.JoinManagement;
import com.social.app.repository.JoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JoinService {
    @Autowired
    JoinRepository  joinRepository;
    public JoinManagement saveJoin(JoinManagement joinManagement){ return joinRepository.save(joinManagement);}
}
