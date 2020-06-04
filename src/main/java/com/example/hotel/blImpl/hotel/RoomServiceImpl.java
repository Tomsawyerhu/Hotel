package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId) {
        return roomMapper.selectRoomsByHotelId(hotelId);
    }

    @Override
    public ResponseVO insertRoomInfo(HotelRoom hotelRoom) {
        int hotelId = hotelRoom.getHotelId();
        String roomType = hotelRoom.getRoomType();
        List<HotelRoom> roomList = retrieveHotelRoomInfo(hotelId);

        boolean roomTypeExists = false;
        for (HotelRoom hotelroom : roomList) {
            if (hotelroom.getRoomType().equals(roomType)) {
                roomTypeExists = true;
                break;
            }
        }

        if (!roomTypeExists) {
            roomMapper.insertRoom(hotelRoom);
            return ResponseVO.buildSuccess("添加成功");
        } else {
            return ResponseVO.buildFailure("已存在相同类型的客房，不可重复录入");
        }
    }
    @Override
    public HotelRoom retrieveRoomById(Integer roomId){return roomMapper.getRoomById(roomId);}
    @Override
    public ResponseVO updateRoomInfo(HotelRoom hotelRoom) {
        int hotelId = hotelRoom.getHotelId();
        List<HotelRoom> roomList = retrieveHotelRoomInfo(hotelId);

        boolean Exists = false;
        HotelRoom old_one=null;
        for (HotelRoom hotelroom : roomList) {
            if (hotelroom.getId().equals(hotelRoom.getId())) {
                old_one=hotelroom;
                Exists = true;
                break;
            }
        }

        if (Exists) {
            if((old_one.getTotal()-old_one.getCurNum())>hotelRoom.getTotal()){return ResponseVO.buildFailure("录入房间总量不正确");}
            hotelRoom.setCurNum(hotelRoom.getTotal()-old_one.getTotal()+old_one.getCurNum());
            roomMapper.updateRoomInfo(hotelRoom.getId(),hotelRoom.getbreakfast(),hotelRoom.getPrice(),hotelRoom.getpeopleNum(),hotelRoom.getTotal(),hotelRoom.getCurNum());
            return ResponseVO.buildSuccess("更新成功");
        } else {
            return ResponseVO.buildFailure("未找到当前客房");
        }
    }

    @Override
    public void addRoomNum(Integer hotelId, String roomType, Integer rooms){
        roomMapper.addRoomNum(hotelId,roomType,rooms);
    }

    @Override
    public void subRoomNum(Integer hotelId, String roomType, Integer rooms){
        roomMapper.subRoomNum(hotelId,roomType,rooms);
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return roomMapper.getRoomCurNum(hotelId, roomType);
    }

    @Override
    public void deleteRoom(Integer roomId){
        roomMapper.deleteRoom(roomId);
    }

}
