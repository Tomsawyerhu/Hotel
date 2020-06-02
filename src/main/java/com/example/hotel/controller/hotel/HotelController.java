package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

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
    public ResponseVO retrieveAllHotels() {
        return ResponseVO.buildSuccess(hotelService.retrieveHotels());
    }

    @PostMapping("/UpdateDetail")
    public  ResponseVO updateHotelInfo(@RequestBody HotelVO hotelVO){
        return hotelService.updateHotelInfo(hotelVO);
    }

    @GetMapping("/{userId}/userOrdered")
    public ResponseVO retrieveUserOrderedHotels(@PathVariable Integer userId) {
        return ResponseVO.buildSuccess(hotelService.retrieveUserOrderedHotels(userId));
    }

    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody HotelRoom hotelRoom) {
        return roomService.insertRoomInfo(hotelRoom);
    }

    @PostMapping("/updateRoom")
    public ResponseVO updateRoomInfo(@RequestBody HotelRoom hotelRoom) {
        return roomService.updateRoomInfo(hotelRoom);
    }

    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelDetails(hotelId));
    }

    @PostMapping("/searchHotel")
    public ResponseVO searchHotel(@RequestBody Map<String, String> conditons) {
        return ResponseVO.buildSuccess(hotelService.searchHotel(conditons));
    }

    @DeleteMapping("/deleteHotel/{hotelId}")
    public ResponseVO deleteHotel(@PathVariable Integer hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseVO.buildSuccess();
    }
    @PostMapping("/deleteRoom/{roomId}")
    public ResponseVO deleteRoom(@PathVariable Integer roomId) {
        roomService.deleteRoom(roomId);
        return ResponseVO.buildSuccess("success");
    }

}
