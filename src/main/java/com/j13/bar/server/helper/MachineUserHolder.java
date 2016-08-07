package com.j13.bar.server.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Service
public class MachineUserHolder {
    private static Logger LOG = LoggerFactory.getLogger(MachineUserHolder.class);

    private List<Integer> machineUserList = null;
    private Random random = new Random();

    @Autowired
    UserHelper userHelper;


    @PostConstruct
    public void init() {
        machineUserList = userHelper.loadAllMachineUser();
        LOG.info("load all machine user. size={}", machineUserList.size());
    }


    public Integer randomOne() {
        int size = machineUserList.size();
        int randomIndex = random.nextInt(size);
        return machineUserList.get(randomIndex);
    }


}
