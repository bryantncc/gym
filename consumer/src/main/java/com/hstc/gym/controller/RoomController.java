package com.hstc.gym.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hstc.gym.service.RoomService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yz on 2019/10/30.
 */
@RestController
public class RoomController {
   @Reference(version = "1.0.0")
    private RoomService roomService;
}
