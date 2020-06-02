package com.example.hotel.blImpl.hotel;

import com.example.hotel.po.Hotel;
import com.example.hotel.vo.HotelVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServicieImplTest {
    @Autowired
    private HotelServiceImpl hotelService;

    @Test
    @Transactional
    public void retriveUserOrderedHotelsTest(){
        List<HotelVO> res = hotelService.retrieveUserOrderedHotels(1);//查看用户id为1的用户定过的酒店
        assertEquals(1,(int)res.get(0).getId()); //这里用户预定的那个酒店的id，方便起见让这个用户定一个酒店就可以
    }

    @Test
    @Transactional
    public void searchHotelTest(){
        //在数据库里有一条江苏省无锡市的五星酒店67大酒店即可
        Map<String,String> conditions = new HashMap<String, String>();
        conditions.put("province","江苏省");
        conditions.put("city","无锡");
        conditions.put("area","梁溪区");
        conditions.put("star","3");
        conditions.put("name","67大酒店");
        List<HotelVO> res = hotelService.searchHotel(conditions);
        assertEquals(4,(int)res.get(0).getId()); //这里填67大酒店的id，方便起见就这个酒店就可以
    }

    @Test
    @Transactional
    public void retrieveHotelDetailsTest(){
        HotelVO hotelVO1 = hotelService.retrieveHotelDetails(1);
        assertEquals(1,(int)hotelVO1.getId());
        HotelVO hotelVO2 = hotelService.retrieveHotelDetails(2);
        assertEquals(2,(int)hotelVO2.getId());
    }

}
