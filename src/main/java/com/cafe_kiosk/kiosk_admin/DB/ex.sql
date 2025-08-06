INSERT INTO category (name, description)
VALUES
    ('커피', '핫/아이스 커피류'),
    ('디저트', '케이크, 쿠키 등 간식류'),
    ('스무디', '과일, 요거트 기반 음료');

INSERT INTO category (name, description) VALUES
                                             ('에이드', '상큼한 에이드 음료'),
                                             ('버블티', '타피오카가 들어간 음료'),
                                             ('주스', '과일로 만든 신선한 주스'),
                                             ('라떼', '우유가 들어간 커피/음료'),
                                             ('차', '홍차, 녹차 등 다양한 차 종류');


INSERT INTO menu (name, price, stock, image_url, is_sold_out, category_id)
VALUES
    ('아메리카노', 3000, 100, 'https://example.com/americano.jpg', FALSE, 1),
    ('초콜릿 케이크', 5500, 50, 'https://example.com/cake.jpg', FALSE, 2);
INSERT INTO menu_option (option_name, option_price, option_type, menu_id)
VALUES
    ('Large Size', 500, 'SIZE', 1),
    ('Whipped Cream', 300, 'TOPPING', 2);
INSERT INTO user (phone, points)
VALUES
    ('010-1234-5678', 1000),
    ('010-9876-5432', 500);
INSERT INTO orders (earned_point, order_method, order_time, phone, status, total_amount, used_point)
VALUES
    (100, 'APP', NOW(), '010-1234-5678', 'COMPLETE', 3800, 200);
INSERT INTO order_item (price, quantity, menu_id, option_id, order_id)
VALUES
    (3500, 1, 1, 1, 1),
    (5800, 1, 2, 2, 1);
INSERT INTO point_history (amount, phone, point_type, order_id, user_id)
VALUES
    (200, '010-1234-5678', '사용', 4, 1),
    (100, '010-1234-5678', '적립', 4, 1);
INSERT INTO cart (phone, quantity, menu_id, option_id)
VALUES
    ('010-1234-5678', 2, 4, 4),
    ('010-9876-5432', 1, 5, 5);
INSERT INTO admin (admin_id, admin_pw, admin_role)
VALUES
    ('admin001', 'pw1234', 'STAFF'),
    ('manager01', 'secure456', 'MANAGER');
INSERT INTO admin_log (action_type, target_table, target_id, admin_id)
VALUES
    ('INSERT', 'menu', 1, 'admin001'),
    ('UPDATE', 'orders', 1, 'manager01');


UPDATE user
SET created_at = NOW(), modified_at = NOW()
WHERE created_at IS NULL OR modified_at IS NULL;