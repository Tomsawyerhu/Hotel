package com.example.hotel.bl.hotel;

import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface RoomService {

    /**
     * 获取某个酒店的全部房间信息
     *
     * @param hotelId
     * @return
     */
    List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId);

    /**
     * 添加酒店客房信息
     *
     * @param hotelRoom
     */
    ResponseVO insertRoomInfo(HotelRoom hotelRoom);

    /**
     * 修改酒店房间信息
     * @param hotelRoom
     */
    ResponseVO updateRoomInfo(HotelRoom hotelRoom);

    /**
     * 增加制定酒店剩余客房数量
     *
     * @param hotelId
     * @param roomType
     * @param rooms
     */


    void addRoomNum(Integer hotelId, String roomType, Integer rooms);


    /**
     * 减少制定酒店剩余客房数量
     *
     * @param hotelId
     * @param roomType
     * @param rooms
     */

    void subRoomNum(Integer hotelId, String roomType, Integer rooms);


    /**
     * 获取酒店指定房间剩余数量
     *
     * @param hotelId
     * @param roomType
     * @return
     */
    int getRoomCurNum(Integer hotelId, String roomType);

    /**
     * @param roomId
     */
    void deleteRoom(Integer roomId);
}
