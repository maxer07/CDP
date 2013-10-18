-- Дамп данных таблицы cinema.user: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` ( `name`) VALUES
	('max'),
	('dima'),
	('sanya');

-- Дамп данных таблицы cinema.test_user: ~0 rows (приблизительно)
DELETE FROM `test_user`;
/*!40000 ALTER TABLE `test_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_user` ENABLE KEYS */;



-- Дамп данных таблицы cinema.ticket: ~20 rows (приблизительно)
DELETE FROM `ticket`;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` (`id`, `title`, `date`, `category`, `place`, `user_id`) VALUES
	(1, 'Mission Impossible 3', '2013-10-15 00:00:00', 'STANDART', 1, NULL),
	(2, 'Mission Impossible 3', '2013-10-15 00:00:00', 'STANDART', 2, NULL),
	(3, 'Mission Impossible 3', '2013-10-15 00:00:00', 'STANDART', 3, NULL),
	(4, 'Mission Impossible 3', '2013-10-15 00:00:00', 'STANDART', 4, NULL),
	(5, 'Mission Impossible 3', '2013-10-15 00:00:00', 'STANDART', 5, NULL),
	(6, 'Mission Impossible 3', '2013-10-15 00:00:00', 'PREMIUM', 6, NULL),
	(7, 'Mission Impossible 3', '2013-10-15 00:00:00', 'PREMIUM', 7, NULL),
	(8, 'Mission Impossible 3', '2013-10-15 00:00:00', 'PREMIUM', 8, NULL),
	(9, 'Mission Impossible 3', '2013-10-15 00:00:00', 'PREMIUM', 9, NULL),
	(10, 'Mission Impossible 3', '2013-10-15 00:00:00', 'PREMIUM', 10, NULL),
	(11, 'Shaolin football', '2013-10-16 00:00:00', 'STANDART', 1, NULL),
	(12, 'Shaolin football', '2013-10-16 00:00:00', 'STANDART', 2, NULL),
	(13, 'Shaolin football', '2013-10-16 00:00:00', 'STANDART', 3, NULL),
	(14, 'Shaolin football', '2013-10-16 00:00:00', 'STANDART', 4, NULL),
	(15, 'Shaolin football', '2013-10-16 00:00:00', 'STANDART', 5, NULL),
	(16, 'Shaolin football', '2013-10-20 00:00:00', 'BAR', 6, NULL),
	(17, 'Shaolin football', '2013-10-20 00:00:00', 'BAR', 7, NULL),
	(18, 'Shaolin football', '2013-10-20 00:00:00', 'BAR', 8, NULL),
	(19, 'Shaolin football', '2013-10-20 00:00:00', 'BAR', 9, NULL),
	(20, 'Shaolin football', '2013-10-20 00:00:00', 'BAR', 10, NULL);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;



/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
