package com.example.hotel.blImpl.hotel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//需要在数据库里1，2，3号酒店并且都有两间及以上大床房，双床房，家庭房
public class RoomServiceImplTest {
    @Autowired
    private RoomServiceImpl roomService;

    @Test
    @Transactional
    public void getRoomCurNumTest(){
        int roomNum1 = roomService.getRoomCurNum(1,"大床房");
        assertEquals(,roomNum1); //这里填1号酒店大床房的数量
        int roomNum2 = roomService.getRoomCurNum(1,"双床房");
        assertEquals(,roomNum2); //这里填1号酒店双床房的数量
        int roomNum3 = roomService.getRoomCurNum(1,"家庭房");
        assertEquals(,roomNum3); //这里填1号酒店家庭房的数量
    }

    @Test
    @Transactional
    public void addRoomNumTest(){
        int curNum1 = roomService.getRoomCurNum(2,"大床房");
        roomService.addRoomNum(2,"大床房",3);
        assertEquals(curNum1+3,roomService.getRoomCurNum(2,"大床房"));
        int curNum2 = roomService.getRoomCurNum(2,"大床房");
        roomService.addRoomNum(2,"家庭房",5);
        assertEquals(curNum2+5,roomService.getRoomCurNum(2,"家庭房"));
    }

    @Test
    @Transactional
    public void subRoomNumTest(){
        int curNum1 = roomService.getRoomCurNum(3,"大床房");
        roomService.subRoomNum(3,"大床房",1);
        assertEquals(curNum1-1,roomService.getRoomCurNum(3,"大床房"));
        int curNum2 = roomService.getRoomCurNum(3,"大床房");
        roomService.subRoomNum(3,"家庭房",1);
        assertEquals(curNum2-1,roomService.getRoomCurNum(3,"家庭房"));
    }

}
