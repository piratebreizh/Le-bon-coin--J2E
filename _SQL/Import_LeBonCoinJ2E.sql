SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `java2e`
--
CREATE DATABASE IF NOT EXISTS `java2e` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `java2e`;

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

CREATE TABLE IF NOT EXISTS `annonce` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `titre` char(50) DEFAULT NULL,
  `description` char(50) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  `ville` char(50) DEFAULT NULL,
  `categorie` char(50) DEFAULT NULL,
  `photo1` char(50) DEFAULT NULL,
  `photo2` char(50) DEFAULT NULL,
  `photo3` char(50) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `dateCreation` date DEFAULT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `annonce`
--

INSERT INTO `annonce` (`numero`, `titre`, `description`, `user`, `ville`, `categorie`, `photo1`, `photo2`, `photo3`, `prix`, `dateCreation`) VALUES
(1, 'test', 'test', 1, 'idf', 'vehicule', 'offres/1/photo1.jpg', 'offres/1/photo2.jpg', 'offres/1/photo3.jpg', 9, '2014-05-18');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` char(50) DEFAULT NULL,
  `password` char(50) DEFAULT NULL,
  `nom` char(50) DEFAULT NULL,
  `prenom` char(50) DEFAULT NULL,
  `adresseMail` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `nom`, `prenom`, `adresseMail`) VALUES
(1, 'test', 'test', 'test', 'test', NULL);

CREATE USER 'java2e'@'localhost' IDENTIFIED BY  'java2e';

GRANT SELECT , 
INSERT ,

UPDATE ,
DELETE ,
CREATE ,
DROP ,
FILE ,
INDEX ,
ALTER ,
CREATE TEMPORARY TABLES ,
CREATE VIEW ,
EVENT,
TRIGGER,
SHOW VIEW ,
CREATE ROUTINE,
ALTER ROUTINE,
EXECUTE ON * . * TO  'java2e'@'localhost' IDENTIFIED BY  'java2e' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
