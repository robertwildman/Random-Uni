-- phpMyAdmin SQL Dump
-- version 4.7.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 25, 2018 at 04:23 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `musiccollection`
--
CREATE DATABASE IF NOT EXISTS `musiccollection` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `musiccollection`;

-- --------------------------------------------------------

--
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
CREATE TABLE IF NOT EXISTS `album` (
  `AlbumId` int(11) NOT NULL,
  `ArtistId` int(11) NOT NULL,
  `AlbumTitle` varchar(30) NOT NULL,
  `ReleaseDate` date DEFAULT NULL,
  PRIMARY KEY (`AlbumId`),
  KEY `ArtistId` (`ArtistId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `album`:
--   `ArtistId`
--       `artist` -> `ArtistId`
--

--
-- Dumping data for table `album`
--

INSERT INTO `album` (`AlbumId`, `ArtistId`, `AlbumTitle`, `ReleaseDate`) VALUES
(1, 1, 'Picaresque', '2005-03-25'),
(2, 1, 'The Crane Wife', '2006-10-03'),
(3, 2, '1972', '2003-08-26'),
(4, 3, 'Arrival', '1976-10-11'),
(5, 1, 'The Hazards Of Love', '2009-03-24'),
(6, 3, 'Super Trouper', '1980-11-03'),
(7, 1, 'The King Is Dead', '2011-01-14'),
(8, 4, 'Foxtrot', '1972-10-06'),
(9, 2, 'Nashville', '2005-02-22'),
(10, 2, 'El Turista', '2010-02-01'),
(11, 5, 'Portraits', '2015-03-16'),
(12, 4, 'A Trick Of The Tail', '1976-02-02'),
(13, 4, 'Genesis', '1983-10-03'),
(14, 6, 'Tangram', '1980-05-31');

-- --------------------------------------------------------

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
CREATE TABLE IF NOT EXISTS `artist` (
  `ArtistId` int(11) NOT NULL,
  `ArtistName` varchar(20) NOT NULL,
  `Category` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ArtistId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `artist`:
--

--
-- Dumping data for table `artist`
--

INSERT INTO `artist` (`ArtistId`, `ArtistName`, `Category`) VALUES
(1, 'The Decemberists', 'Folk Rock'),
(2, 'Josh Rouse', 'Songwriter'),
(3, 'ABBA', 'Pop'),
(4, 'Genesis', 'Prog Rock'),
(5, 'Hannah Rose Platt', 'Country'),
(6, 'Tangerine Dream', 'Electronic'),
(7, 'Twelfth Night', 'Prog Rock');

-- --------------------------------------------------------

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
CREATE TABLE IF NOT EXISTS `track` (
  `TrackNo` int(11) NOT NULL,
  `AlbumId` int(11) NOT NULL,
  `TrackTitle` varchar(40) NOT NULL,
  `TrackLength` int(4) DEFAULT NULL,
  PRIMARY KEY (`AlbumId`,`TrackNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `track`:
--   `AlbumId`
--       `album` -> `AlbumId`
--

--
-- Dumping data for table `track`
--

INSERT INTO `track` (`TrackNo`, `AlbumId`, `TrackTitle`, `TrackLength`) VALUES
(1, 1, 'The Infanta', 308),
(2, 1, 'We Both Go Down Together', 184),
(3, 1, 'Eli, The Barrow Boy', 191),
(1, 2, 'The Crane Wife 3', 258),
(2, 2, 'The Island', 746),
(3, 2, 'Yankee Bayonet (I Will Be Home', 259),
(1, 3, '1972', 228),
(2, 3, 'Love Vibration', 291),
(1, 4, 'When I Kissed The Teacher', 180),
(2, 4, 'Dancing Queen', 230),
(3, 4, 'My Love, My Life', 232),
(4, 4, 'Dum Dum Diddle', 170),
(1, 5, 'Prelude', 184),
(1, 6, 'Super Trouper', 253),
(2, 6, 'The Winner Takes It All', 295),
(3, 6, 'On And On And On', 221),
(1, 7, 'Don\'t Carry It All', 257),
(2, 7, 'Calamity Song', 230),
(3, 7, 'Rise To Me', 299),
(4, 7, 'Rox In The Box', 190),
(1, 8, 'Watcher Of The Skies', 441),
(2, 8, 'Time Table', 287),
(3, 8, 'Get \'Em Out By Friday', 515),
(4, 8, 'Can-Utility And The Coastliner', 345),
(5, 8, 'Horizons', 99),
(6, 8, 'Supper\'s Ready', 1377),
(1, 9, 'It\'s The Nighttime', 244),
(2, 9, 'Winter In The Hamptons', 188),
(1, 10, 'Bienvenido', 138),
(2, 10, 'Duerme', 251),
(3, 10, 'Lemon Tree', 185),
(1, 11, 'Little Screws', 286),
(2, 11, 'Temptress', 285),
(3, 11, '1954', 270),
(1, 12, 'Dance On A Volcano', 353),
(2, 12, 'Entangled', 388),
(1, 13, 'Mama', 406),
(1, 14, 'Tangram Set 1', 1187),
(2, 14, 'Tangram Set 2', 1228);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`ArtistId`) REFERENCES `artist` (`ArtistId`);

--
-- Constraints for table `track`
--
ALTER TABLE `track`
  ADD CONSTRAINT `track_ibfk_1` FOREIGN KEY (`AlbumId`) REFERENCES `album` (`AlbumId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
