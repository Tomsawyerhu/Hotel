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
//需要在数据库里1，2，3号酒店并且都有两间及以上BigBed，DoubleBed，Family
public class RoomServiceImplTest {
    @Autowired
    private RoomServiceImpl roomService;

    @Test
    @Transactional
    public void getRoomCurNumTest(){
        int roomNum1 = roomService.getRoomCurNum(1,"BigBed");
        assertEquals(20,roomNum1); //这里填1号酒店BigBed的数量
        int roomNum2 = roomService.getRoomCurNum(1,"DoubleBed");
        assertEquals(30,roomNum2); //这里填1号酒店DoubleBed的数量
        int roomNum3 = roomService.getRoomCurNum(1,"Family");
        assertEquals(10,roomNum3); //这里填1号酒店Family的数量
    }

    @Test
    @Transactional
    public void addRoomNumTest(){
        int curNum1 = roomService.getRoomCurNum(2,"BigBed");
        roomService.addRoomNum(2,"BigBed",3);
        assertEquals(curNum1+3,roomService.getRoomCurNum(2,"BigBed"));
        int curNum2 = roomService.getRoomCurNum(2,"BigBed");
        roomService.addRoomNum(2,"Family",5);
        assertEquals(curNum2+5,roomService.getRoomCurNum(2,"Family"));
    }

    @Test
    @Transactional
    public void subRoomNumTest(){
        int curNum1 = roomService.getRoomCurNum(1,"BigBed");
        roomService.subRoomNum(1,"BigBed",1);
        assertEquals(curNum1-1,roomService.getRoomCurNum(1,"BigBed"));
        int curNum2 = roomService.getRoomCurNum(1,"Family");
        roomService.subRoomNum(1,"Family",1);
        assertEquals(curNum2-1,roomService.getRoomCurNum(1,"Family"));
    }

}
