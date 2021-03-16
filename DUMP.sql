-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le :  mar. 16 mars 2021 à 14:03
-- Version du serveur :  5.7.26
-- Version de PHP :  7.4.2

SET time_zone = "+00:00";

--
-- Base de données :  `virtualbookcase`
--

-- --------------------------------------------------------

--
-- Structure de la table `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `available` bit(1) NOT NULL,
  `cond` int(11) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL,
  `local_id` varchar(64) NOT NULL,
  `book_descriptionfk` int(11) NOT NULL
) ;

--
-- Déchargement des données de la table `book`
--

INSERT INTO `book` (`id`, `available`, `cond`, `created`, `local_id`, `book_descriptionfk`) VALUES
(1, b'0', 0, '2021-02-10 01:34:52', 'Z14', 1),
(2, b'0', 0, '2021-02-10 01:34:52', 'Z14', 1),
(3, b'0', 0, '2021-03-09 14:57:10', '3c', 5),
(4, b'0', 0, '2021-02-10 01:34:52', 'Z14', 1),
(5, b'0', 0, '2021-02-10 01:34:52', 'Z14', 1),
(6, b'0', 0, '2021-03-09 14:57:10', '3c', 5),
(7, b'0', 0, '2021-02-10 01:34:52', 'Z14', 1),
(8, b'0', 0, '2021-02-10 01:34:52', 'Z14', 5),
(9, b'0', 0, '2021-03-09 14:57:10', '3c', 5),
(10, b'0', 0, '2021-03-09 15:05:20', '4F', 2),
(11, b'0', 0, '2021-03-09 15:05:20', '4F', 2),
(13, b'0', 0, '2021-03-09 15:05:20', '4F', 2),
(14, b'0', 0, '2021-03-09 15:09:25', '7P', 4),
(15, b'0', 0, '2021-03-09 15:09:25', '7P', 4),
(16, b'0', 0, '2021-03-09 15:09:25', '7P', 4);

-- --------------------------------------------------------

--
-- Structure de la table `bookdescription`
--

CREATE TABLE `bookdescription` (
  `id` int(11) NOT NULL,
  `author_firstname` varchar(128) NOT NULL,
  `author_lastname` varchar(128) NOT NULL,
  `comment` text,
  `created` timestamp NOT NULL,
  `edition_number` int(11) NOT NULL,
  `edition_year` year(4) NOT NULL,
  `editor` varchar(128) NOT NULL,
  `format` varchar(64) NOT NULL,
  `genre` varchar(64) NOT NULL,
  `isbn` varchar(64) NOT NULL,
  `title` varchar(256) NOT NULL
) ;

--
-- Déchargement des données de la table `bookdescription`
--

INSERT INTO `bookdescription` (`id`, `author_firstname`, `author_lastname`, `comment`, `created`, `edition_number`, `edition_year`, `editor`, `format`, `genre`, `isbn`, `title`) VALUES
(1, 'Claude', 'Abromont', NULL, '2021-02-09 21:59:53', 1, 2013, 'Fayard', 'Broché', 'Education', '9782213655727', 'Guide des formes de la musique occidentale'),
(2, 'Eriko', 'Sato', NULL, '2021-03-09 14:28:16', 1, 2008, 'Editions First', 'Livre de poche', 'Langues', '9782754006255', 'Le japonais pour les nuls'),
(3, 'Jean', 'Tulard', 'Association pour la sauvegarde des livres anciens de la bibliothèque de la Cour de cassation', '2021-03-09 14:28:16', 1, 1995, 'Ateliers Gustave Gernez', 'Agrafé', 'Histoire', '1234567891012', 'Ombres et Lumières de la Campagne d\'Egypte'),
(4, 'Gilbert', 'Guilleminault', NULL, '2021-03-09 14:36:48', 1, 1980, 'Julliard', 'broché', 'histoire', '2260001807', 'Le roman vrai de la Vème République. L\'Etat, c\'est lui. 1959-1960.'),
(5, 'Wladyslaw', 'Szpilman', NULL, '2021-03-09 14:40:46', 3, 2003, 'Robert Laffont', 'Poche', 'Témoignage', '9782266130950', 'Le pianiste');

-- --------------------------------------------------------

--
-- Structure de la table `loan`
--

CREATE TABLE `loan` (
  `id` int(11) NOT NULL,
  `bookfk` int(11) NOT NULL,
  `userfk` int(11) NOT NULL,
  `loan_end_date` date DEFAULT NULL,
  `loan_start_date` date NOT NULL,
  `extension_asked` bit(1) NOT NULL,
  `comment` text
) ;

--
-- Déchargement des données de la table `loan`
--

INSERT INTO `loan` (`id`, `bookfk`, `userfk`, `loan_end_date`, `loan_start_date`, `extension_asked`, `comment`) VALUES
(1, 1, 1, NULL, '2021-02-07', b'1', NULL),
(2, 1, 1, NULL, '2021-02-17', b'0', NULL),
(3, 1, 1, '2021-02-10', '2021-02-03', b'0', NULL),
(4, 1, 1, NULL, '2021-01-05', b'0', NULL),
(5, 2, 3, NULL, '2021-03-15', b'0', NULL),
(6, 14, 2, NULL, '2021-03-11', b'0', NULL),
(7, 6, 4, NULL, '2021-02-11', b'0', NULL),
(8, 2, 4, NULL, '2021-03-08', b'0', NULL),
(9, 10, 1, NULL, '2021-02-26', b'0', NULL),
(10, 8, 2, NULL, '2021-03-16', b'0', NULL),
(11, 16, 4, '2021-03-23', '2021-01-04', b'0', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `birthdate` date NOT NULL,
  `city` varchar(256) NOT NULL,
  `country` varchar(128) NOT NULL,
  `created` timestamp NOT NULL,
  `email` varchar(256) NOT NULL,
  `firstname` varchar(256) NOT NULL,
  `lastname` varchar(256) NOT NULL,
  `login` varchar(64) NOT NULL,
  `membership` date NOT NULL,
  `password` varchar(64) NOT NULL,
  `postal_code` int(11) NOT NULL,
  `sex` int(11) NOT NULL DEFAULT '0',
  `street_name` varchar(256) NOT NULL,
  `street_nb` varchar(32) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '2'
) ;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `birthdate`, `city`, `country`, `created`, `email`, `firstname`, `lastname`, `login`, `membership`, `password`, `postal_code`, `sex`, `street_name`, `street_nb`, `role`) VALUES
(1, '1983-02-05', 'Grenoble', 'France', '2021-02-10 14:15:47', 'harry.potter@gmail.com', 'Harry', 'Potter', 'harry', '2021-02-10', '$2a$10$TNl61T4kjJNIgpIDPqtqv.iOybf8K3PQbqK7.NIiHCpBgR/9IdTAS', 38000, 1, 'rue de Poudlard', '1', 1),
(2, '1985-02-09', 'Lyon', 'France', '2021-03-08 13:47:04', 'hermione.granger@gmail.com', 'Hermione', 'Granger', 'hermione', '2021-03-08', '00000', 69007, 0, 'rue de Poudlard', '2', 2),
(3, '1981-05-05', 'Lozann', 'France', '2021-03-08 14:21:51', 'minerva.mccgonagall@poudlard.fr', 'Minerva', 'McGonagall', 'minerva', '2021-01-03', '00000', 69320, 0, 'route du paradis', '3', 2),
(4, '1932-07-31', 'Paris', 'France', '2021-03-08 14:23:57', 'a.dumbledore@poudlard.fr', 'Albus', 'Dumbledore', 'albus', '2020-04-02', '00000', 75016, 1, 'rue du Faubourg Saint Honoré', '12', 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6o5cj6ltoxf4l8ihfdqwe8esx` (`book_descriptionfk`);

--
-- Index pour la table `bookdescription`
--
ALTER TABLE `bookdescription`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm191ke83kca08vfoi50nmn6l0` (`bookfk`),
  ADD KEY `FKd0ejmnk3g8pv2105s6mpxy7vp` (`userfk`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `bookdescription`
--
ALTER TABLE `bookdescription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `loan`
--
ALTER TABLE `loan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FK6o5cj6ltoxf4l8ihfdqwe8esx` FOREIGN KEY (`book_descriptionfk`) REFERENCES `bookdescription` (`id`);

--
-- Contraintes pour la table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `FKd0ejmnk3g8pv2105s6mpxy7vp` FOREIGN KEY (`userfk`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKm191ke83kca08vfoi50nmn6l0` FOREIGN KEY (`bookfk`) REFERENCES `book` (`id`);
