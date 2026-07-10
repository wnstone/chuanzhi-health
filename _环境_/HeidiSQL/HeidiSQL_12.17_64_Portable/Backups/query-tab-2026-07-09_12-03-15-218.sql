-- 把未来日期的可预约人数从 1 改成 50
UPDATE t_ordersetting SET number = 50 WHERE orderDate >= CURDATE();

-- 同时把已预约人数重置为 0，避免旧数据干扰
UPDATE t_ordersetting SET reservations = 0 WHERE orderDate >= CURDATE();