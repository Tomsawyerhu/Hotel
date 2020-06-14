const getters = {
    //user
    token: state => state.user.token,
    userId: state => state.user.userId,
    userInfo: state => state.user.userInfo,
    userOrderList: state => state.user.userOrderList,
    userHotelList: state => state.user.userHotelList,
    hotelListLoading: state => state.hotel.hotelListLoading,
    hotelList: state => state.hotel.hotelList,
    currentHotelInfo: state => state.hotel.currentHotelInfo,
    currentHotelId: state => state.hotel.currentHotelId,
    orderModalVisible: state => state.hotel.orderModalVisible,
    currentOrderRoom: state => state.hotel.currentOrderRoom,
    roomEditVisible: state => state.hotel.roomEditVisible,
    orderMatchCouponList: state => state.hotel.orderMatchCouponList,
    addRoomVisible: state => state.hotel.addRoomVisible,//自己加的
    creditList: state => state.user.creditList,
    currentAccountId: state => state.user.currentAccountId,
    registMemberVisible: state => state.user.registMemberVisible,
    registMemberParams: state => state.user.registMemberParams,
    /*currentCreditInfo:state=>state.user.currentCreditInfo,*/

    //admin
    managerList: state => state.admin.managerList,
    clientList: state => state.admin.clientList,//自己加的
    staffList: state => state.admin.staffList,//自己加的
    targetAccount: state => state.admin.targetAccount,//自己加的
    addManagerModalVisible: state => state.admin.addManagerModalVisible,
    addManagerParams: state => state.admin.addManagerParams,
    addStaffModalVisible: state => state.admin.addStaffModalVisible,//自己加的
    UserInfoEditVisible: state => state.admin.UserInfoEditVisible,//自己加的,

    //manager
    orderList: state => state.hotelManager.orderList,
    addHotelModalVisible: state => state.hotelManager.addHotelModalVisible,
    addRoomModalVisible: state => state.hotelManager.addRoomModalVisible,
    couponVisible: state => state.hotelManager.couponVisible,
    addCouponVisible: state => state.hotelManager.addCouponVisible,
    activeHotelId: state => state.hotelManager.activeHotelId,
    couponList: state => state.hotelManager.couponList,
    currentRoom: state => state.hotelManager.currentRoom,//自己加的

    //order
    userOrdersInCertainHotel: state => state.order.userOrdersInCertainHotel,
    currentOrderId: state => state.order.currentOrderId,     //自己加的
    currentOrderInfo: state => state.order.currentOrderInfo,    //自己加的

    //marketingStaff
    abnormalOrderList: state => state.marketingStaff.abnormalOrderList,
    websiteCouponList: state => state.marketingStaff.websiteCouponList,
    addWebCouponVisible: state => state.marketingStaff.addWebCouponVisible,
    currentCouponId: state => state.marketingStaff.currentCouponId,
    currentCouponInfo: state => state.marketingStaff.currentCouponInfo,
    couponDetailVisible: state => state.marketingStaff.couponDetailVisible,

    //comment
    hotelCommentList: state => state.comment.hotelCommentList,
    currentComment: state => state.comment.currentComment,
    addCommentVisible: state => state.comment.addCommentVisible,
}

export default getters

