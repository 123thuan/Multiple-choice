-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 20 déc. 2019 à 16:49
-- Version du serveur :  10.4.6-MariaDB
-- Version de PHP :  7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `tracnghiem`
--

-- --------------------------------------------------------

--
-- Structure de la table `answer`
--

CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `question_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `answer`
--

INSERT INTO `answer` (`id`, `name`, `question_id`) VALUES
(4, 'Winrar', 1),
(11, 'False Positive', 3),
(14, 'SMTP', 4),
(17, 'Telnet', 5),
(20, '65.535', 6),
(25, 'HTTP', 7),
(28, 'DDOS', 8),
(33, '25', 10),
(35, '162', 10),
(36, '20', 10),
(37, '1', 11),
(38, '2', 11),
(39, '3', 11),
(40, '4', 11),
(41, '5', 12),
(42, '6', 12),
(43, '7', 12),
(44, '8', 12),
(45, '1', 13),
(46, '2', 13),
(47, '-2', 13),
(48, '-1', 13),
(49, '6', 14),
(50, '7', 14),
(51, '8', 14),
(52, '9', 14),
(53, 'không', 15),
(54, 'có', 15),
(55, 'uk', 15),
(56, '12', 16),
(57, '13', 16),
(58, '14', 16),
(59, '15', 16),
(60, '11', 17),
(61, '13', 17),
(62, '15', 17),
(63, '9', 17),
(64, '9', 18),
(65, '10', 18),
(66, '11', 18),
(67, '12', 18),
(68, '6', 19),
(69, '8', 19),
(70, '9', 19),
(71, '10', 19),
(72, '16', 20),
(73, '17', 20),
(74, '18', 20),
(75, '19', 20),
(76, '27', 21),
(77, '46', 21),
(78, '29', 21),
(79, '37', 21),
(80, '76', 22),
(81, '86', 22),
(82, '80', 22),
(83, '67', 22),
(84, '83', 23),
(85, '46', 23),
(86, '84', 23),
(87, '82', 23),
(88, '121', 24),
(89, '123', 24),
(90, '124', 24),
(91, '125', 24),
(92, '57', 25),
(93, '76', 25),
(94, '78', 25),
(95, '75', 25),
(96, '456', 26),
(97, '544', 26),
(98, '546', 26),
(99, '457', 26),
(100, '696', 27),
(101, '633', 27),
(102, '697', 27),
(103, '687', 27),
(104, '775', 28),
(105, '235', 28),
(106, '563', 28),
(107, '674', 28),
(108, '1776', 29),
(109, '2345', 29),
(110, '1434', 29),
(111, '2352', 29),
(112, '1776', 30),
(113, '3214', 30),
(114, '2341', 30),
(115, '1423', 30);

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Toán 1'),
(2, 'Toán 2'),
(3, 'Toán 3'),
(5, 'Toán 4'),
(6, 'Toán 5'),
(7, 'Toán 1'),
(8, 'Toán 2'),
(9, 'Toán 3'),
(11, 'Toán 5');

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `question`
--

INSERT INTO `question` (`id`, `name`, `category_id`) VALUES
(1, 'Các chương trình nào không phải là chương trình diệt virus?', 1),
(3, 'Phương án nào được hiểu không phải là thuật ngữ về Virus', 1),
(4, 'Giao thức nào sau đây  sử dụng cổng 25?', 1),
(5, 'Giao thức nào sau đây KHÔNG được sử dụng cho các kết nối từ xa?', 1),
(6, 'Trong giao thức TCP / IP (Transmission Control Protocol / Internet Protocol) số lượng cổng để được quét, tấn công và khai thác.', 1),
(7, 'Giao thức nào sau đây thực hiện việc truyền tải dữ liệu giữa web browser và web server', 1),
(8, 'Hình thức tấn công nào sau đây sẽ bao gồm nhiều máy tính tấn công một tổ chức duy nhất?', 1),
(10, 'Giao thức SMTP sử dụng cổng nào sao đây?', 1),
(11, '1 + 1 = ?', 2),
(12, '2 + 3 = ?', 2),
(13, '2 + x = ?', 2),
(14, '3!', 2),
(15, 'có muốn chờ vi không', 3),
(16, '5 + 8', 7),
(17, '6 + 5 = ?', 7),
(18, '4 + 6 = ?', 7),
(19, '5+4 = ?', 7),
(20, '9 + 8 =?', 7),
(21, '13+14=', 8),
(22, '56+24', 8),
(23, '36+47=', 8),
(24, '64+57', 8),
(25, '58=17', 8),
(26, '234+223=', 9),
(27, '344+352=', 9),
(28, '343+432=', 9),
(29, '888+888', 9),
(30, '888+888=', 9);

-- --------------------------------------------------------

--
-- Structure de la table `question_answer`
--

CREATE TABLE `question_answer` (
  `question_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `question_answer`
--

INSERT INTO `question_answer` (`question_id`, `answer_id`) VALUES
(1, 4),
(3, 11),
(4, 14),
(5, 17),
(6, 20),
(7, 25),
(8, 28),
(10, 33),
(11, 38),
(12, 41),
(13, 47),
(14, 49),
(15, 54),
(16, 57),
(17, 60),
(18, 65),
(19, 70),
(20, 73),
(21, 76),
(22, 82),
(23, 84),
(24, 88),
(25, 95),
(26, 99),
(27, 100),
(28, 104),
(29, 108),
(30, 112);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_id` (`question_id`);

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Index pour la table `question_answer`
--
ALTER TABLE `question_answer`
  ADD PRIMARY KEY (`question_id`,`answer_id`),
  ADD KEY `question_answer_ibfk_2` (`answer_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;

--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`);

--
-- Contraintes pour la table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Contraintes pour la table `question_answer`
--
ALTER TABLE `question_answer`
  ADD CONSTRAINT `question_answer_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  ADD CONSTRAINT `question_answer_ibfk_2` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
