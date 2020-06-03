package com.example.hotel.bl.hotel;

import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;
import java.util.Map;

public interface HotelService {

    /**
     * 添加酒店
     *
     * @param hotelVO
     * @throws
     */
    void addHotel(HotelVO hotelVO) throws ServiceException;


    /* /**
     * 预订酒店修改剩余客房信息
     * @param Id
     * @param breakfast
     * @param peopleNum
     * @param price
     */
    /*

    void updateRoomInfo(int Id,String breakfast,double price,int peopleNum);*/


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
     * 列表获取酒店信息
     *
     * @return
     */
    List<HotelVO> retrieveHotels();

    /**
     * 获取某家酒店详细信息
     *
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    /**
     * 更改某家酒店的详细信息
     *
     * @param
     * @return
     */
    ResponseVO updateHotelInfo(HotelVO hotelVO);

    /**
     * 更改某家酒店的详细信息
     *
     * @param
     * @return
     */
    ResponseVO updateHotelManager(HotelVO hotelVO);

    /**
     * 获得用户预定过的酒店列表
     *
     * @param userId
     * @return
     */
    List<HotelVO> retrieveUserOrderedHotels(Integer userId);


    /**
     * 根据搜索条件获取酒店列表，没有符合条件的则返回null
     * @param conditions
     * @return
     */
    List<HotelVO> searchHotel(Map<String,String> conditions);

    /**
     * 查看酒店剩余某种房间数量
     *
     * @param hotelId
     * @param roomType
     * @return
     */
    int getRoomCurNum(Integer hotelId, String roomType);


    /**
     * 删除酒店
     *
     * @param hotelId
     * @return
     */
    int deleteHotel(Integer hotelId);


}
