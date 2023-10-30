-- 매장관련 테이블
-- 테이블명 : oct27_restaurant
-- 컬럼명  : r_location(체인점 지점), r_owner(식당 주인), r_seat(좌석수)
-- PK 어디에?
create table oct27_restaurant(
	r_location varchar2(10 char) primary key,
	r_owner varchar2(10 char) not null,
	r_seat number(3) not null
);

-- insert 2개정도? test용으로
insert into oct27_restaurant values('강남', '홍길동', 100);
insert into oct27_restaurant values('성남', '정지희', 80);

select * from oct27_restaurant;

-- 예약테이블 : oct27_reservation
--	r_name(예약자이름), r_time(예약 시간), r_phone(예약자전화번호), r_location(예약지점)
--	FK필요없음!, PK 어디에?

create table oct27_reservation(
	r_no number(3) primary key,
	r_name varchar2(10 char) not null,
	r_time date not null,
	r_phone varchar2(20 char) not null,
	r_location varchar2(10 char) not null
);

create sequence oct27_reservation_seq;
-- insert 세개 정도
insert into oct27_reservation values(oct27_reservation_seq.nextval,'김길동', 
to_date('2023-10-28 18:00', 'YYYY-MM-DD HH24:MI'), '010-6757-1519', '강남');

insert into oct27_reservation values(oct27_reservation_seq.nextval,'박길동', 
to_date('2023-11-01 11:30', 'YYYY-MM-DD HH24:MI'), '010-1234-5678', '판교');

insert into oct27_reservation values(oct27_reservation_seq.nextval,'최길동', 
to_date('2023-10-31 21:00', 'YYYY-MM-DD HH24:MI'), '010-9877-6544', '강남');
insert into oct27_reservation values(oct27_reservation_seq.nextval,'최길동', to_date('2023-10-31 21:00', 'YYYY-MM-DD HH24:MI'), '010-9877-6544', '강남');

select * from oct27_reservation;

SELECT r_name,TO_CHAR(r_time, 'YYYY-MM-DD AM HH12:MI') as r_time2 FROM oct27_reservation;

SELECT * FROM OCT27_RESTAURANT;

SELECT * FROM OCT27_RESTAURANT WHERE r_seat >= 90;

SELECT r_no,r_name,r_phone,r_location,to_char(r_time, 'YYYY-MM-DD AM HH12:MI') as r_time2 FROM oct27_reservation WHERE r_name = '김길동';

UPDATE oct27_reservation SET r_time = '?' WHERE r_no = '?'

DELETE OCT27_RESERVATION WHERE r_location = '신논현점';
