package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.HotelMapper;
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
        if (manager == null || !manager.getUserType().equals(UserType.Admin)) {
            throw new ServiceException("管理员不存在或者无权限！创建酒店失败！");
        }
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.insertHotel(hotel);
    }

    /*@Override
    public void updateRoomInfo(int Id,String breakfast,double price,int peopleNum) {
        roomService.updateRoomInfo(Id,breakfast,price,peopleNum);
    }*/

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
    public ResponseVO updateHotelInfo(HotelVO hotelVO) {
        int hotelId = hotelVO.getId();
        List<HotelVO> hotelList = retrieveHotels();
        boolean HotelExists = false;
        for (HotelVO hotelVO1 : hotelList) {
            if (hotelId == hotelVO1.getId()) {
                HotelExists = true;
                break;
            }
        }
        if (HotelExists) {
            hotelMapper.updateHotelInfo(hotelId, hotelVO.getDescription());
            return ResponseVO.buildSuccess();
        } else {
            return ResponseVO.buildFailure("未找到此酒店");
        }
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
        System.out.println(conditions);
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
            System.out.println(hotel);
            String hotelAddr = hotel.getAddress();
            String hotelName = hotel.getName();
            boolean isFit = true;
            //首先判断星级是否符合,星级小于star则不符合
            if (hotel.getHotelStar().getStarAsNumber() < starNum) {
                isFit = false;
            }
            //
            if (name != null) {
                if(name.contains("酒店")){
                    name = name.substring(0,name.length()-2); //去掉酒店两字的影响
                }
                if(hotelName.contains("酒店")){
                    hotelName = hotelName.substring(0,hotelName.length()-2);
                }
                //通过levenshtein算法计算两个酒店名称的相似度，相似度<=0.5则认为不符合
                double similarity = levenshtein(name,hotelName);
                //System.out.println("相似度"+similarity);
                if(similarity<0.5){
                    isFit = false;
                }
            }
            if(hotelAddr!=null){
            //province非空则判断hotelAddr中是否包含province，不包含则不符合
            if (province != null) {
                System.out.println("province is "+province);
                if (!hotelAddr.contains(province)) {
                    isFit = false;
                }
            }
            //city非空则判断hotelAddr中是否包含city，不包含则不符合
            if (city != null) {
                System.out.println("city is "+city);
                if (!hotelAddr.contains(city)) {
                    isFit = false;
                }
            }
            //area非空则判断hotelAddr中是否包含area，不包含则不符合
            if (area != null) {
                System.out.println("area is "+area);
                if (!hotelAddr.contains(area)) {
                    isFit = false;
                }
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

    /**
     * @param hotelId
     * @return
     */

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        Hotel hotel = hotelMapper.selectById(hotelId);
        HotelVO hotelVO = new HotelVO();
        BeanUtils.copyProperties(hotel, hotelVO);
        hotelVO.setBizRegion(hotel.getBizRegion().toString());
        hotelVO.setHotelStar(hotel.getHotelStar().toString());
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(r.getId());
            roomVO.setPrice(r.getPrice());
            roomVO.setRoomType(r.getRoomType().toString());
            roomVO.setCurNum(r.getCurNum());
            roomVO.setTotal(r.getTotal());
            roomVO.setbreakfast(r.getbreakfast());
            roomVO.setpeopleNum(r.getpeopleNum());
            return roomVO;
        }).collect(Collectors.toList());
        hotelVO.setRooms(roomVOS);

        return hotelVO;
    }

    /**
     * 模糊匹配的一个算法，返回两个酒店名字的相似度，若酒店名称中都包含“酒店”两字，则都需要去掉
     * @param str1
     * @param str2
     * @return
     */
    private double levenshtein(String str1, String str2) {
        //计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();
        //建立数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        //赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
        //计算相似度
        float similarity = 1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
        return similarity;
    }

    //得到最小值
    private int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

    @Override
    public ResponseVO updateHotelManager(HotelVO hotelVO){
        int hotelId=hotelVO.getId();
        List<HotelVO> hotelList=retrieveHotels();
        boolean HotelExists=false;
        for(HotelVO hotelVO1:hotelList){
            if(hotelId==hotelVO1.getId()){
                HotelExists=true;
                break;
            }
        }
        if(HotelExists){
            hotelMapper.updateHotelManager(hotelId,hotelVO.getManagerId());
            return ResponseVO.buildSuccess();
        }
        else{
            return ResponseVO.buildFailure("未找到此酒店");
        }
    }
    @Override
    public void deLink(Integer hotelId){
        hotelMapper.deLink(hotelId);
    }
}





