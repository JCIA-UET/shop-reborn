insert into `mydb`.`category`
(
`name`,
`description`
)
values
(
"Điện thoại đi động",
"Mục điện thoại đi động"
),

(
"Máy tính xách tay",
"Mục máy tính xách tay"
);

insert into `mydb`.`product`
(`name`, `price`, `quantity`, `categoryId`, `description`, `imgLink`) 
values
(
"Samsung Galaxy S7 Edge - Vàng",
18490000,
100,
1,
"Thiết kế hiện đại Điện thoại Samsung Galaxy S7 Edge thiết kế khung viền bằng kim loại và hai mặt trước sau phủ kính cường lực cùng màn hình cong mang lại vẻ đẹp hoàn mỹ cho sản phẩm",
"img/product/1/medium_196782_samsung-galaxy-s7-edge-gold.jpg"
),
(
"OPPO F1",
5990000, 
100,
1,
"Thiết kế sang trọng Điện thoại di động OPPO F1 có thiết kế từ kim loại nguyên khối, màu vàng gold sang trọng. Các góc cạnh bo tròn tạo  nên sự mềm mại. Mặt lưng phủ lớp cát Zirco tạo cảm giác mèm mượt, không bám vân tay.",
"img/product/2/medium_194225_oppo-f1_2.jpg"
),
(
"Apple iPhone 6s Gold 64GB",
20688000,
50,
1,
"Thiết kế siêu mỏng Apple iPhone 6s 64GB vẫn duy trì những thiết kế cơ bản của thế hệ iphone 6 với bộ khung bo tròn cùng độ mỏng ấn tượng chỉ 7.1mm giúp người dùng cầm máy thoải mái hơn. Chất liệu nhôm nguyên khối làm tăng vẻ sang trọng và cứng cáp cho sản phẩm",
"img/product/3/medium_174435_apple-iphone-6s-gold-64gb.jpg"
),
(
"Asus F454LA-WX390D",
7999000,
20,
2,
"Thiết kế đẹp mắt Asus F454LA-WX390D có lớp vỏ ngoài được hoàn thiện với các đường vân đồng tâm tỏa ra từ logo Asus đem đến vẻ đẹp tinh tế và trang nhã cho sản phẩm. Trọng lượng khá nhẹ dễ dàng mang theo",
"img/product/4/medium_181377_asus-f454la-wx390d.jpg"
),
(
"Dell Inspiron N3543A",
9499000,
30,
2,
"Thiết kế cứng cáp, sang trọng Dell Inspiron N3543A-P40F001 sở hữu một thiết kế vuông vức, tạo nên sự mạnh mẽ, chắc chắn. Mặt phía trên sử dụng chất liệu nhựa với hoa văn kim cương giúp hạn giúp hạn chế triệt để việc bám mồ hôi, vân tay và bụi bẩn. Trọng lượng của máy khoảng 2.4kg.",
"img/product/5/medium_119433_dell-inspiron-n3543a-p40f001.jpg"
)
;