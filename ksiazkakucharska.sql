-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 02, 2026 at 06:45 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ksiazkakucharska`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `recipes`
--

CREATE TABLE `recipes` (
  `recipe_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `category` enum('Śniadanie','Obiad','Deser','Kolacja','Inne') NOT NULL,
  `ingredients` text NOT NULL,
  `instructions` text NOT NULL,
  `prep_time` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `recipes`
--

INSERT INTO `recipes` (`recipe_id`, `title`, `category`, `ingredients`, `instructions`, `prep_time`, `user_id`) VALUES
(14, 'Puszyste Naleśniki', 'Śniadanie', 'Mąka, mleko, jajka, cukier', 'Wymieszaj składniki i smaż na złoty kolor.', 20, 1),
(15, 'Spaghetti Bolognese', 'Obiad', 'Makaron, mięso mielone, sos pomidorowy', 'Ugotuj makaron, podsmaż mięso z sosem.', 40, 1),
(16, 'Sernik na zimno', 'Deser', 'Twaróg, galaretka, herbatniki', 'Wyłóż spód herbatnikami, nałóż masę serową.', 60, 1),
(17, 'Owsianka z borówkami', 'Śniadanie', 'Płatki owsiane, mleko migdałowe, borówki, miod', 'Gotuj płatki na mleku przez 5 minut. Dodaj owoce i miód.', 10, 1),
(18, 'Szakszuka', 'Śniadanie', 'Jajka, pomidory w puszce, cebula, czosnek, kumin', 'Podsmaż cebulę i czosnek, dodaj pomidory. Wbij jajka i duś pod przykryciem.', 15, 1),
(19, 'Gofry belgijskie', 'Śniadanie', 'Mąka, masło, cukier perlisty, drożdże', 'Przygotuj ciasto drożdżowe, piecz w gofrownicy na złoty kolor.', 30, 1),
(20, 'Pierogi z jagodami', 'Obiad', 'Mąka, jagody, cukier, śmietana', 'Zagnieć ciasto, uformuj pierogi z owocami i gotuj we wrzątku.', 50, 1),
(21, 'Kurczak Curry', 'Obiad', 'Pierś z kurczaka, mleczko kokosowe, pasta curry, ryż', 'Podsmaż kurczaka, dodaj pastę i mleczko. Podawaj z ryżem.', 35, 1),
(22, 'Zupa Krem z Pomidorów', 'Obiad', 'Pomidory, bulion, bazylia, mozzarella', 'Zmiksuj pomidory z bulionem i przyprawami. Podawaj z serem.', 25, 1),
(23, 'Łosoś z pieca', 'Obiad', 'Dzwonko łososia, cytryna, koper, ziemniaki', 'Piecz rybę w 180°C przez 20 minut z dodatkiem ziół.', 30, 1),
(24, 'Brownie czekoladowe', 'Deser', 'Czekolada gorzka, masło, jajka, mąka', 'Rozpuść czekoladę z masłem, wymieszaj z resztą i piecz 25 min.', 40, 1),
(25, 'Tarta z owocami', 'Deser', 'Kruche ciasto, budyń, truskawki, borówki', 'Upiecz spód, nałóż budyń i udekoruj świeżymi owocami.', 45, 1),
(26, 'Mus truskawkowy', 'Deser', 'Truskawki, śmietanka 30%, cukier puder', 'Zmiksuj owoce, wymieszaj z ubitą śmietaną i schłódź.', 15, 1),
(27, 'awfawf', 'Inne', 'skladniki waf segdrgdr ', 'instrukcjawawf seg drg awf', 15, 1),
(31, 'aowdij? aowhfin', 'Obiad', 'awdo ijoaif oiesjf;oi joseif ', 'waod8 oaef oaiejofi haoiuhfw i;ohsgrn ', 15, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(254) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `name`, `email`, `password`) VALUES
(1, 'admin', 'karon@dw.pl', 'admin');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `recipes`
--
ALTER TABLE `recipes`
  ADD PRIMARY KEY (`recipe_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `recipes`
--
ALTER TABLE `recipes`
  MODIFY `recipe_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `recipes`
--
ALTER TABLE `recipes`
  ADD CONSTRAINT `recipes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
