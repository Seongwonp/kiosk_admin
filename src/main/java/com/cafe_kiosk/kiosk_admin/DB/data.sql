-- 1. 카테고리
INSERT INTO category (name, description) VALUES
                                             ('신메뉴', '새로 출시된 메뉴'),
                                             ('커피', '핫/아이스 커피류'),
                                             ('라떼', '우유가 들어간 커피/음료'),
                                             ('버블티', '타피오카가 들어간 음료'),
                                             ('스무디', '과일, 요거트 기반 음료'),
                                             ('에이드', '상큼한 에이드 음료'),
                                             ('주스', '과일로 만든 신선한 주스'),
                                             ('티', '홍차, 녹차 등 다양한 차 종류'),
                                             ('디저트', '케이크, 쿠키 등 간식류');



-- 2. 메뉴
INSERT INTO menu (category_id, name, price, image_url, stock, is_sold_out, created_at) VALUES
                                                                                           (2, '아메리카노', 1500, 'http://swp2002.dothome.co.kr/img/menu/2/americano.png', 50, false, '2025-07-01'),
                                                                                           (2, '바닐라라떼', 3500, 'http://swp2002.dothome.co.kr/img/menu/2/VanillaLatte.png', 50, false, '2025-07-01'),
                                                                                           (2, '카페라떼', 3300, 'http://swp2002.dothome.co.kr/img/menu/2/CafeLatte.png', 50, false, '2025-07-01'),
                                                                                           (2, '카페모카', 3300, 'http://swp2002.dothome.co.kr/img/menu/2/CafeMocha.png', 50, false, '2025-07-01'),
                                                                                           (2, '카라멜 마끼야또', 3800, 'http://swp2002.dothome.co.kr/img/menu/2/CaramelMacchiato.png', 50, false, '2025-07-01'),
                                                                                           (2, '돌체라떼', 3800, 'http://swp2002.dothome.co.kr/img/menu/2/DolceLatte.png', 50, false, '2025-07-01'),
                                                                                           (3, '초코라떼', 3500, 'http://swp2002.dothome.co.kr/img/menu/3/ChocolateLatte.png', 50, false, '2025-07-01'),
                                                                                           (3, '녹차라떼', 3500, 'http://swp2002.dothome.co.kr/img/menu/3/GreenTeaLatte.png', 50, false, '2025-07-01'),
                                                                                           (3, '고구마라떼', 3500, 'http://swp2002.dothome.co.kr/img/menu/3/SweetPotatoLatte.png', 50, false, '2025-07-01'),
                                                                                           (3, '딸기라떼', 3500, 'http://swp2002.dothome.co.kr/img/menu/3/StrawberryLatte.png', 50, false, '2025-07-01'),
                                                                                           (3, '토피넛라떼', 3800, 'http://swp2002.dothome.co.kr/img/menu/3/ToffeeNutLatte.png', 50, false, '2025-07-01'),
                                                                                           (3, '달고나라떼', 3800, 'http://swp2002.dothome.co.kr/img/menu/3/DalgonaLatte.png', 50, false, now()),
                                                                                           (4, '타로버블티', 4400, 'http://swp2002.dothome.co.kr/img/menu/4/TaroBubbleTea.png', 50, false, '2025-07-01'),
                                                                                           (4, '초코버블티', 4400, 'http://swp2002.dothome.co.kr/img/menu/4/ChocolateBubbleTea.png', 50, false, '2025-07-01'),
                                                                                           (4, '얼그레이버블티', 4400, 'http://swp2002.dothome.co.kr/img/menu/4/EarlGreyBubbleTea.png', 50, false, '2025-07-01'),
                                                                                           (4, '블랙슈가버블티', 4400, 'http://swp2002.dothome.co.kr/img/menu/4/BlackSugarBubbleTea.png', 50, false, '2025-07-01'),
                                                                                           (5, '자몽스무디', 4500, 'http://swp2002.dothome.co.kr/img/menu/5/GrapefruitSmoothie.png', 50, false, '2025-07-01'),
                                                                                           (5, '감귤망고요거트스무디', 4800, 'http://swp2002.dothome.co.kr/img/menu/5/TangerineMangoYogurtSmoothie.png', 50, false, now()),
                                                                                           (5, '딸기스무디', 4500, 'http://swp2002.dothome.co.kr/img/menu/5/StrawberrySmoothie.png', 50, false, '2025-07-01'),
                                                                                           (5, '망고스무디', 4500, 'http://swp2002.dothome.co.kr/img/menu/5/MangoSmoothie.png', 50, false, '2025-07-01'),
                                                                                           (5, '블루베리요거트', 4500, 'http://swp2002.dothome.co.kr/img/menu/5/BlueberryYogurt.png', 50, false, '2025-07-01'),
                                                                                           (5, '딸기요거트', 4500, 'http://swp2002.dothome.co.kr/img/menu/5/StrawberryYogurt.png', 50, false, '2025-07-01'),
                                                                                           (5, '망고요거트', 4500, 'http://swp2002.dothome.co.kr/img/menu/5/MangoYogurt.png', 50, false, '2025-07-01'),
                                                                                           (6, '레몬에이드', 3500, 'http://swp2002.dothome.co.kr/img/menu/6/LemonAde.png', 50, false, '2025-07-01'),
                                                                                           (6, '딸기레몬에이드', 3500, 'http://swp2002.dothome.co.kr/img/menu/6/StrawberryLemonAde.png', 50, false, now()),
                                                                                           (6, '청포도에이드', 3500, 'http://swp2002.dothome.co.kr/img/menu/6/GreenGrapeAde.png', 50, false, '2025-07-01'),
                                                                                           (6, '자몽에이드', 3500, 'http://swp2002.dothome.co.kr/img/menu/6/GrapefruitAde.png', 50, false, '2025-07-01'),
                                                                                           (7, '바나나주스', 4000, 'http://swp2002.dothome.co.kr/img/menu/7/BananaJuice.png', 50, false, '2025-07-01'),
                                                                                           (7, '망고주스', 4000, 'http://swp2002.dothome.co.kr/img/menu/7/MangoJuice.png', 50, false, '2025-07-01'),
                                                                                           (7, '딸기바나나', 4000, 'http://swp2002.dothome.co.kr/img/menu/7/StrawberryBananaJuice.png', 50, false, '2025-07-01'),
                                                                                           (7, '딸기주스', 4000, 'http://swp2002.dothome.co.kr/img/menu/7/StrawberryJuice.png', 50, false, '2025-07-01'),
                                                                                           (8, '복숭아아이스티', 2500, 'http://swp2002.dothome.co.kr/img/menu/8/PeachIcedTea.png', 50, false, '2025-07-01'),
                                                                                           (8, '유자차', 3500, 'http://swp2002.dothome.co.kr/img/menu/8/CitronTea.png', 50, false, '2025-07-01'),
                                                                                           (8, '레몬티', 3500, 'http://swp2002.dothome.co.kr/img/menu/8/LemonTea.png', 50, false, '2025-07-01'),
                                                                                           (8, '꿀/자몽티', 3500, 'http://swp2002.dothome.co.kr/img/menu/8/HoneyGrapefruitTea.png', 50, false, '2025-07-01'),
                                                                                           (8, '레몬생강차', 3900, 'http://swp2002.dothome.co.kr/img/menu/8/LemonGingerTea.png', 50, false, '2025-07-01'),
                                                                                           (8, '레몬얼그레이', 4200, 'http://swp2002.dothome.co.kr/img/menu/8/LemonEarlGrey.png', 50, false, '2025-07-01'),
                                                                                           (8, '허니자몽블랙티', 3900, 'http://swp2002.dothome.co.kr/img/menu/8/HoneyGrapefruitTea.png', 50, false, '2025-07-01'),
                                                                                           (8, '얼그레이', 3000, 'http://swp2002.dothome.co.kr/img/menu/8/EarlGreyTea.png', 50, false, '2025-07-01'),
                                                                                           (9, '햄치즈샌드위치', 3500, 'http://swp2002.dothome.co.kr/img/menu/9/HamCheeseSandwich.png', 50, false, '2025-07-01'),
                                                                                           (9, '땅콩크림샌드위치', 3500, 'http://swp2002.dothome.co.kr/img/menu/9/PeanutSandwich.png', 50, false, '2025-07-01'),
                                                                                           (9, '핫도그', 3500, 'http://swp2002.dothome.co.kr/img/menu/9/HotDog.png', 50, false, '2025-07-01'),
                                                                                           (9, '초코스틱케이크', 4000, 'http://swp2002.dothome.co.kr/img/menu/9/ChocolateStickCake.png', 50, false, '2025-07-01'),
                                                                                           (9, '치즈스틱케이크', 4000, 'http://swp2002.dothome.co.kr/img/menu/9/CheeseStickCake.png', 50, false, '2025-07-01'),
                                                                                           (9, '팥우유크림모찌', 3500, 'http://swp2002.dothome.co.kr/img/menu/9/RedBeanMilkCreamMochi.png', 50, false, now()),
                                                                                           (9, '딸기크림치즈모찌', 3500, 'http://swp2002.dothome.co.kr/img/menu/9/StrawberryCreamCheeseMochi.png', 50, false, now());




-- 3. 메뉴 옵션

-- 음료 공통옵션: 사이즈
INSERT INTO menu_option (option_name, option_price, option_type, category_id) VALUES
                                                                                  ('S', 0, '음료 공통옵션', 1),
                                                                                  ('S', 0, '음료 공통옵션', 2),
                                                                                  ('S', 0, '음료 공통옵션', 3),
                                                                                  ('S', 0, '음료 공통옵션', 4),
                                                                                  ('S', 0, '음료 공통옵션', 5),
                                                                                  ('S', 0, '음료 공통옵션', 6),
                                                                                  ('S', 0, '음료 공통옵션', 7),
                                                                                  ('S', 0, '음료 공통옵션', 8),
                                                                                  ('L', 500, '음료 공통옵션', 1),
                                                                                  ('L', 500, '음료 공통옵션', 2),
                                                                                  ('L', 500, '음료 공통옵션', 3),
                                                                                  ('L', 500, '음료 공통옵션', 4),
                                                                                  ('L', 500, '음료 공통옵션', 5),
                                                                                  ('L', 500, '음료 공통옵션', 6),
                                                                                  ('L', 500, '음료 공통옵션', 7),
                                                                                  ('L', 500, '음료 공통옵션', 7);

-- 에이드, 스무디, 주스 제외 공통옵션: 온도
INSERT INTO menu_option (option_name, option_price, option_type, category_id) VALUES
                                                                                  ('Hot', 0, '에이드, 스무디, 주스제외 공통옵션', 2),
                                                                                  ('Hot', 0, '에이드, 스무디, 주스제외 공통옵션', 3),
                                                                                  ('Hot', 0, '에이드, 스무디, 주스제외 공통옵션', 4),
                                                                                  ('Hot', 0, '에이드, 스무디, 주스제외 공통옵션', 8),
                                                                                  ('ICE', 0, '에이드, 스무디, 주스제외 공통옵션', 2),
                                                                                  ('ICE', 0, '에이드, 스무디, 주스제외 공통옵션', 3),
                                                                                  ('ICE', 0, '에이드, 스무디, 주스제외 공통옵션', 4),
                                                                                  ('ICE', 0, '에이드, 스무디, 주스제외 공통옵션', 8);

-- 커피 옵션: 샷추가
INSERT INTO menu_option (option_name, option_price, option_type, category_id) VALUES
                                                                                  ('샷추가 1', 500, '커피옵션', 2),
                                                                                  ('샷추가 2', 500, '커피옵션', 2),
                                                                                  ('샷추가 3', 500, '커피옵션', 2),
                                                                                  ('샷추가 4', 500, '커피옵션', 2),
                                                                                  ('샷추가 5', 500, '커피옵션', 2);

-- 커피 옵션: 시럽추가
INSERT INTO menu_option (option_name, option_price, option_type, category_id) VALUES
                                                                                  ('바닐라 시럽', 500, '커피옵션', 2),
                                                                                  ('초코 시럽', 500, '커피옵션', 2),
                                                                                  ('카라멜 시럽', 500, '커피옵션', 2),
                                                                                  ('돌체 시럽', 500, '커피옵션', 2),
                                                                                  ('헤이즐넛 시럽', 500, '커피옵션', 2),
                                                                                  ('연유 시럽', 500, '커피옵션', 2);

-- 커피 옵션: 토핑추가
INSERT INTO menu_option (option_name, option_price, option_type, category_id) VALUES
                                                                                  ('Caramel Drizzle', 500, '커피옵션', 2),
                                                                                  ('Cinnamon powder', 500, '커피옵션', 2),
                                                                                  ('Whipped Cream', 500, '커피옵션', 2);

-- 버블티 옵션: 펄추가
INSERT INTO menu_option (option_name, option_price, option_type, category_id) VALUES
                                                                                  ('없음', 0, '버블티 옵션', 4),
                                                                                  ('펄 추가', 500, '버블티 옵션', 4);

-- 버블티 옵션: 당도조절
INSERT INTO menu_option (option_name, option_price, option_type, category_id) VALUES
                                                                                  ('기본', 0, '버블티 옵션', 4),
                                                                                  ('달게', 0, '버블티 옵션', 4),
                                                                                  ('조금만', 0, '버블티 옵션', 4);

-- 에이드 옵션: 탄산조절
INSERT INTO menu_option (option_name, option_price, option_type, category_id) VALUES
                                                                                  ('보통', 0, '에이드 옵션', 6),
                                                                                  ('강하게', 0, '에이드 옵션', 6),
                                                                                  ('약하게', 0, '에이드 옵션', 6);



-- 세로운 데이터
INSERT INTO user (created_at, modified_at, phone, points)
VALUES
    ('2025-07-15 08:00:00.000000', '2025-08-01 08:00:00.000000', '010-1000-0001', 320),
    ('2025-07-20 09:00:00.000000', '2025-08-01 08:20:00.000000', '010-1000-0002', 150),
    ('2025-07-25 10:00:00.000000', '2025-08-01 08:30:00.000000', '010-1000-0003', 420),
    ('2025-07-30 11:00:00.000000', '2025-08-01 08:45:00.000000', '010-1000-0004', 230);


INSERT INTO orders (earned_point, order_method, order_time, phone, status, toss_order_id, total_amount, used_point)
VALUES
    (20, 'KIOSK', '2025-08-01 09:15:00.000000', '010-1000-0001', 'COMPLETE', 'TOSS20250801-001', 8300, 300),
    (30, 'APP', '2025-08-01 09:20:00.000000', '010-1000-0002', 'COMPLETE', 'TOSS20250801-002', 10800, 150),
    (15, 'KIOSK', '2025-08-01 09:25:00.000000', '010-1000-0003', 'COMPLETE', 'TOSS20250801-003', 4500, 0),
    (25, 'APP', '2025-08-01 09:30:00.000000', '010-1000-0004', 'COMPLETE', 'TOSS20250801-004', 7300, 200);

-- 주문 1
INSERT INTO order_item (price, quantity, menu_id, option_id, order_id) VALUES
                                                                           (1500, 1, 1, 2, 1),     -- 아메리카노 (S, option_id: 2)
                                                                           (4800, 1, 18, 13, 1),   -- 감귤망고요거트스무디 (L, option_id: 13)
                                                                           (4000, 1, 43, 7, 1);    -- 딸기바나나주스 (S, option_id: 7)

-- 주문 2
INSERT INTO order_item (price, quantity, menu_id, option_id, order_id) VALUES
                                                                           (3800, 1, 6, 10, 2),    -- 돌체라떼 (menu_id: 6, option_id: 10 = L)
                                                                           (4400, 1, 14, 40, 2),   -- 초코버블티 (menu_id: 14, option_id: 40 = 펄추가)
                                                                           (3500, 1, 24, 14, 2);   -- 레몬에이드 (menu_id: 24, option_id: 14 = L)

-- 주문 3
INSERT INTO order_item (price, quantity, menu_id, option_id, order_id) VALUES
    (3500, 1, 9, 3, 3);     -- 고구마라떼 (menu_id: 9, option_id: 3 = S)

-- 주문 4
INSERT INTO order_item (price, quantity, menu_id, option_id, order_id) VALUES
                                                                           (3800, 1, 5, 2, 4),     -- 카라멜 마끼야또 (menu_id: 5, option_id: 2 = S)
                                                                           (3500, 1, 26, 6, 4);    -- 청포도에이드 (menu_id: 26, option_id: 6 = S)
-- 주문 1
INSERT INTO point_history (created_at, modified_at, amount, phone, point_type, order_id, user_id)
VALUES
    ('2025-08-01 09:15:00.000000', '2025-08-01 09:15:10.000000', -300, '010-1000-0001', '사용', 1, 1),
    ('2025-08-01 09:15:10.000000', '2025-08-01 09:15:10.000000', 20, '010-1000-0001', '적립', 1, 1);

-- 주문 2
INSERT INTO point_history (created_at, modified_at, amount, phone, point_type, order_id, user_id)
VALUES
    ('2025-08-01 09:20:00.000000', '2025-08-01 09:20:10.000000', -150, '010-1000-0002', '사용', 2, 2),
    ('2025-08-01 09:20:10.000000', '2025-08-01 09:20:10.000000', 30, '010-1000-0002', '적립', 2, 2);

-- 주문 3
INSERT INTO point_history (created_at, modified_at, amount, phone, point_type, order_id, user_id)
VALUES
    ('2025-08-01 09:25:10.000000', '2025-08-01 09:25:10.000000', 15, '010-1000-0003', '적립', 3, 3);

-- 주문 4
INSERT INTO point_history (created_at, modified_at, amount, phone, point_type, order_id, user_id)
VALUES
    ('2025-08-01 09:30:00.000000', '2025-08-01 09:30:10.000000', -200, '010-1000-0004', '사용', 4, 4),
    ('2025-08-01 09:30:10.000000', '2025-08-01 09:30:10.000000', 25, '010-1000-0004', '적립', 4, 4);



