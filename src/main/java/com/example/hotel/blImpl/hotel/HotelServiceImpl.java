package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    @Override
    public void addHotel(HotelVO hotelVO) throws ServiceException {
        UserVO manager = accountService.getUserInfo(hotelVO.getManagerId());
        if (manager == null || !manager.getUserType().equals(UserType.HotelManager)) {
            throw new ServiceException("管理员不存在或者无权限！创建酒店失败！");
        }
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.insertHotel(hotel);
    }

    @Override
    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        roomService.updateRoomInfo(hotelId, roomType, rooms);
    }

    @Override
    public void addRoomNum(Integer hotelId, String roomType, Integer rooms) {
        roomService.addRoomNum(hotelId, roomType, rooms);
    }

    @Override
    public void subRoomNum(Integer hotelId, String roomType, Integer rooms) {
        roomService.subRoomNum(hotelId, roomType, rooms);
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return roomService.getRoomCurNum(hotelId, roomType);
    }

    @Override
    public int deleteHotel(Integer hotelId) {
        return hotelMapper.deleteHotel(hotelId);
    }

    @Override
    public List<HotelVO> retrieveHotels() {
        List<HotelVO> res = new ArrayList<>();
        List<Hotel> hotels = hotelMapper.selectAllHotel();
        for (Hotel hotel : hotels) {
            HotelVO hotelVO = new HotelVO();
            BeanUtils.copyProperties(hotel, hotelVO);
            res.add(hotelVO);
        }
        return res;
    }

    @Override
    public List<HotelVO> retrieveUserOrderedHotels(Integer userId) { //返回客户预定过的酒店列表
        List<HotelVO> userOrderedHotels = new ArrayList<>();
        List<OrderVO> userOrders = orderService.getUserOrders(userId);
        List<Integer> userOrderedHotelId = new ArrayList<>();
        for (int i = 0; i < userOrders.size(); i++) {
            boolean flag = true;
            for (int j = 0; j < userOrderedHotelId.size(); j++) {
                if (userOrderedHotelId.get(j).equals(userOrders.get(i).getHotelId())) {
                    flag = false;
                }
            }
            //去重找出所有定过的酒店
            if (flag) {
                userOrderedHotelId.add(userOrders.get(i).getHotelId());
            }
        }
        for (int i = 0; i < userOrderedHotelId.size(); i++) {
            userOrderedHotels.add(retrieveHotelDetails(userOrderedHotelId.get(i)));
        }
        return userOrderedHotels;
    }

    @Override
    public List<HotelVO> searchHotel(Map<String, String> conditions) {
        List<HotelVO> res = new ArrayList<>();
        List<Hotel> allHotels = hotelMapper.selectAllHotel();
        String province = conditions.get("province");
        String city = conditions.get("city");
        String area = conditions.get("area");
        String name = conditions.get("name");
        String star = conditions.get("star");
        int starNum = 0; //没传入star则认为最低星级为0
        if (star != null) {
            starNum = Integer.parseInt(conditions.get("star"));
        }
        for (Hotel hotel : allHotels) {
            String hotelAddr = hotel.getAddress();
            boolean isFit = true;
            //首先判断星级是否符合,星级小于star则不符合
            if (Integer.parseInt(hotel.getHotelStar().toString()) < starNum) {
                isFit = false;
            }
            //名称非空则判断名称的匹配，cnt用来计数搜索条件的名称与hotelName匹配的字符个数，cnt与hotelNameLen绝对值差在2以内则符合
            if(name!=null){
                String hotelName = hotel.getName();
                int hotelNameLen = hotelName.length();
                int cnt = 0;
                for(int i=0;i<name.length();i++){
                    if(hotelName.indexOf(name.charAt(i))!=-1){
                        cnt++;
                    }
                }
                if(Math.abs((cnt-hotelNameLen))>2){
                    isFit = false;
                }
            }
            //province非空则判断hotelAddr中是否包含province，不包含则不符合
            if (province != null) {
                if (!hotelAddr.contains(province)) {
                    isFit = false;
                }
            }
            //city非空则判断hotelAddr中是否包含city，不包含则不符合
            if (city != null) {
                if (!hotelAddr.contains(city)) {
                    isFit = false;
                }
            }
            //area非空则判断hotelAddr中是否包含area，不包含则不符合
            if (area != null) {
                if (!hotelAddr.contains(area)) {
                    isFit = false;
                }
            }
            //isFit为true表示满足条件，把该酒店加入结果
            if (isFit) {
                HotelVO hotelVO = new HotelVO();
                BeanUtils.copyProperties(hotel, hotelVO);
                res.add(hotelVO);
            }
        }
        if (!res.isEmpty()) {
            return res;
        } else {
            return null;
        }
    }


    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        Hotel hotel = hotelMapper.selectById(hotelId);
        HotelVO hotelVO = new HotelVO();
        BeanUtils.copyProperties(hotel, hotelVO);
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(r.getId());
            roomVO.setPrice(r.getPrice());
            roomVO.setRoomType(r.getRoomType().toString());
            roomVO.setCurNum(r.getCurNum());
            roomVO.setTotal(r.getTotal());
            return roomVO;
        }).collect(Collectors.toList());
        hotelVO.setRooms(roomVOS);

        return hotelVO;
    }


    /**
     * @param hotelId
     * @return
     */

}
