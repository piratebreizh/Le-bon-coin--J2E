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


--
-- Base de données: `java2e`
--

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

CREATE TABLE `annonce` (
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `annonce`
--

INSERT INTO `annonce` (`numero`, `titre`, `description`, `user`, `ville`, `categorie`, `photo1`, `photo2`, `photo3`, `prix`, `dateCreation`) VALUES
(1, 'Mega voiture', 'c''est un très belle voiture', 1, 'idf', 'vehicule', 'offres/1/photo1.jpg', 'offres/1/photo2.jpg', 'offres/1/photo3.jpg', 9000, '2014-05-18'),
(2, 'Enceinte', 'gros volume', 1, 'idf', 'mutlimedia', 'offres/2/photo1.jpg', 'offres/2/photo2.jpg', 'offres/2/photo3.jpg', 400, '2014-07-11'),
(3, 'Vente suberbe studio', 'un studio en pleine cambrousse', 2, 'bretagne', 'immobilier', 'offres/3/photo1.jpg', 'offres/3/photo2.jpg', 'offres/3/photo3.jpg', 200000, '2014-07-01'),
(4, '4 L', 'Authentique 4L', 1, 'idf', 'vehicule', 'offres/4/photo1.jpg', 'offres/4/photo2.jpg', 'offres/4/photo3.jpg', 3000, '2014-07-02');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` char(50) DEFAULT NULL,
  `password` char(50) DEFAULT NULL,
  `nom` char(50) DEFAULT NULL,
  `prenom` char(50) DEFAULT NULL,
  `adresseMail` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `nom`, `prenom`, `adresseMail`) VALUES
(1, 'user1', 'user1', 'nomUser1', 'prenomUser1', 'user1@user.com'),
(2, 'user2', 'user2', 'prenomUser2', 'nomUser2', 'user2@user2.com');


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
