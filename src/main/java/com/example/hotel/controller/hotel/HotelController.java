package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;


    @PostMapping("/addHotel")
    public ResponseVO createHotel(@RequestBody HotelVO hotelVO) throws ServiceException {
        hotelService.addHotel(hotelVO);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/all")
    public ResponseVO retrieveAllHotels(){
        return ResponseVO.buildSuccess(hotelService.retrieveHotels());
    }

    @GetMapping("/{userId}/userOrdered")
    public ResponseVO retrieveUserOrderedHotels(@PathVariable Integer userId){
        return ResponseVO.buildSuccess(hotelService.retrieveUserOrderedHotels());
    }

    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody HotelRoom hotelRoom) {
        //此处应该检查同一酒店相同类型的客房只有一条记录
        int hotelId=hotelRoom.getHotelId();
        RoomType roomType=hotelRoom.getRoomType();
        List<HotelRoom> roomList=roomService.retrieveHotelRoomInfo(hotelId);

        boolean roomTypeExists=false;
        for(HotelRoom hotelroom:roomList){
            if (hotelroom.getRoomType().equals(roomType)) {
                roomTypeExists = true;
                break;
            }
        }

        if(!roomTypeExists){
            roomService.insertRoomInfo(hotelRoom);
            return ResponseVO.buildSuccess();
        }else{
            return ResponseVO.buildFailure("已存在相同类型的客房，不可重复录入");
        }
    }

    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelDetails(hotelId));
    }

    @DeleteMapping("/deleteHotel/{hotelId}")
    public ResponseVO deleteHotel(@PathVariable Integer hotelId){
        hotelService.deleteHotel(hotelId);
        return ResponseVO.buildSuccess();
    }




}
