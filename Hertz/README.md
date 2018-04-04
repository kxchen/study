#Hertz(在线租车系统)

##一、确定实体属性

###用户：

> 必填项

1.手机号 2.密码 3.姓名 4.性别  5.电话号码  6.身份证号码  7.身份证正反面照  8.驾驶证照片 9.注册时间 10. 是否激活 11.是否实名认证 12.激活码 13.id 14.余额 15.优惠券

> 选填

1.居住地址 2.紧急联系人电话 3.紧急联系人姓名 4.紧急联系人关系
 

###管理员

1.用户名 2.密码 3.id

###车辆信息(CarInfo)

1.品牌(brand)) 2.车系(model) 3.车型(type) 4.购买时间(buyDate) 5.公里数(km) 6.年审凭证照片(carefulImg) 7.车辆照片(carImg) 8.排挡(gears) 9.排量(cc) 10.颜色(color) 11.租赁条件(criteria) 12.出租价格(price) 13. 座位数( Seats) 14.是否通过审核(isCheck) 15.是否被租赁(isRent) 16.发布时间(relTime) 17.id(carId) 18.车主(UserInfo) 19.车辆用途(purpose)  20.备注（remarks）

###订单(OrderInfo    IOrderDAO     OrderDAOImpl    )

1.id （orderId）2.user(user) 3.提车时间(takeTime) 4.下单时间 (buyTime)5.实际还车时间(ReaRetTime) 6.还车时间(retTime) 7.是否发车(isSend) 8.是否收车isReceive 9.是否还车isReturn 10.应付款(pay) 11.实付款(reaPay) 

###订单详情(OrderDetailInfo    )

1.id(orderDetailId) 2.订单(OrderInfo orderInfo) 3.价格(reaPrice) 4.数量(quantity) 5.车辆(carInfo)

###购物车

1.id(shopCatId) 2.车辆(carInfo) 3.数量(amount) 4.用户(UserInfo)

###套餐类（comboInfo）

>管理员可发布套餐类，婚庆系列的车显示套餐类列表

品牌(brand)) 车系(model) 车型(type) 车辆照片(carImg) 排挡(gears) 公里数(km) 排量(cc) 颜色(color) 租赁条件(criteria) 出租价格(price) 座位数( Seats) 是否通过审核(isCheck) 是否被租赁(isRent) 发布时间(relTime) id(comboId)  数量(count) 套餐名(comboName)   备注（remarks）

##二、功能

###一、前台功能

* 注册
* 登录
* 发布租车信息
* 把车辆加入购物车
* 确认预定车辆
* 查看订单

###二、后台功能

* 
