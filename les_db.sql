-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 27-Abr-2021 às 21:16
-- Versão do servidor: 10.4.13-MariaDB
-- versão do PHP: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `les_db`
--

DELIMITER $$
--
-- Procedimentos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `inativarCarrinhos` ()  NO SQL
UPDATE carrinhos as car SET car.status = 0 WHERE car.status = 1 and DATEDIFF(CURDATE(), dataAlteracao ) > (SELECT valor from configuracoes WHERE id = 3)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `autores`
--

CREATE TABLE `autores` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `resumo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `autores`
--

INSERT INTO `autores` (`id`, `dataCadastro`, `nome`, `resumo`) VALUES
(1, '2021-04-12', 'Autor teste', 'teste');

-- --------------------------------------------------------

--
-- Estrutura da tabela `bairros`
--

CREATE TABLE `bairros` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `idCidade` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `bairros`
--

INSERT INTO `bairros` (`id`, `dataCadastro`, `descricao`, `idCidade`) VALUES
(2, '2021-03-01', 'Perdizes', 5),
(3, '2021-03-01', 'Vila Oliveira', 3),
(4, '2021-03-01', 'Vila Formosa', 5),
(5, '2021-03-01', 'Rodeio', 3),
(6, '2021-03-02', 'Vila Nair', 5),
(7, '2021-03-02', 'Vila Portuguesa', 5),
(8, '2021-03-02', 'Leblon', 6),
(9, '2021-03-02', 'Jardim José Sampaio Júnior', 7),
(10, '2021-03-02', 'Jardim Torrão de Ouro', 8),
(11, '2021-03-02', 'Tucuruvi', 5),
(12, '2021-03-02', 'Vila Mogilar', 3),
(13, '2021-03-02', 'César de Souza', 3),
(14, '2021-03-02', 'Shangai', 3),
(15, '2021-03-02', 'Mogilar', 3),
(16, '2021-03-02', 'Jardim dos Colibris', 9),
(18, '2021-04-13', 'Jardim Armênia', 3),
(19, '2021-04-25', 'Caiçaras', 11),
(20, '2021-04-25', 'Trem', 12);

-- --------------------------------------------------------

--
-- Estrutura da tabela `bandeiras`
--

CREATE TABLE `bandeiras` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `bandeiras`
--

INSERT INTO `bandeiras` (`id`, `dataCadastro`, `nome`) VALUES
(1, '2021-04-13', 'Visa'),
(2, '2021-04-13', 'MasterCard');

-- --------------------------------------------------------

--
-- Estrutura da tabela `bloqueios_produtos`
--

CREATE TABLE `bloqueios_produtos` (
  `id` bigint(20) NOT NULL,
  `dataEntrada` date NOT NULL,
  `quantidade` int(11) NOT NULL,
  `idLivro` bigint(20) NOT NULL,
  `idCarrinho` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `bloqueios_produtos`
--

INSERT INTO `bloqueios_produtos` (`id`, `dataEntrada`, `quantidade`, `idLivro`, `idCarrinho`) VALUES
(94, '2021-04-27', 12, 6, 99),
(98, '2021-04-27', 12, 6, 102);

-- --------------------------------------------------------

--
-- Estrutura da tabela `carrinhos`
--

CREATE TABLE `carrinhos` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `idUsuario` bigint(20) NOT NULL,
  `status` smallint(6) NOT NULL,
  `dataAlteracao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `carrinhos`
--

INSERT INTO `carrinhos` (`id`, `dataCadastro`, `idUsuario`, `status`, `dataAlteracao`) VALUES
(3, '2021-04-13', 34, 0, '2021-04-13'),
(4, '2021-04-13', 34, 2, NULL),
(5, '2021-04-13', 34, 2, NULL),
(6, '2021-04-13', 34, 2, NULL),
(7, '2021-04-13', 34, 2, NULL),
(8, '2021-04-13', 34, 2, NULL),
(9, '2021-04-13', 34, 2, NULL),
(10, '2021-04-13', 34, 2, '2021-04-27'),
(11, '2021-04-13', 34, 2, '2021-04-27'),
(12, '2021-04-13', 34, 2, '2021-04-27'),
(13, '2021-04-13', 34, 2, '2021-04-27'),
(14, '2021-04-13', 34, 2, '2021-04-27'),
(15, '2021-04-13', 34, 2, '2021-04-27'),
(16, '2021-04-13', 34, 2, '2021-04-27'),
(17, '2021-04-13', 34, 2, '2021-04-27'),
(18, '2021-04-13', 34, 2, '2021-04-27'),
(19, '2021-04-13', 34, 2, NULL),
(20, '2021-04-13', 34, 2, '2021-04-27'),
(21, '2021-04-15', 34, 2, '2021-04-27'),
(22, '2021-04-15', 34, 2, '2021-04-27'),
(23, '2021-04-15', 34, 2, '2021-04-27'),
(24, '2021-04-16', 34, 2, '2021-04-27'),
(25, '2021-04-16', 34, 2, '2021-04-27'),
(26, '2021-04-16', 34, 2, NULL),
(27, '2021-04-18', 34, 2, NULL),
(28, '2021-04-18', 34, 2, NULL),
(29, '2021-04-18', 34, 2, '2021-04-27'),
(30, '2021-04-19', 34, 2, '2021-04-27'),
(31, '2021-04-19', 34, 2, '2021-04-27'),
(32, '2021-04-19', 34, 2, '2021-04-27'),
(33, '2021-04-20', 34, 2, '2021-04-26'),
(34, '2021-04-22', 34, 2, '2021-04-27'),
(35, '2021-04-22', 34, 2, '2021-04-27'),
(36, '2021-04-22', 34, 2, '2021-04-27'),
(37, '2021-04-22', 34, 2, '2021-04-27'),
(38, '2021-04-22', 34, 2, '2021-04-27'),
(39, '2021-04-24', 90, 2, '2021-04-27'),
(40, '2021-04-24', 90, 2, '2021-04-26'),
(41, '2021-04-24', 90, 2, '2021-04-27'),
(42, '2021-04-24', 90, 2, '2021-04-27'),
(43, '2021-04-25', 91, 2, '2021-04-27'),
(44, '2021-04-25', 91, 2, '2021-04-26'),
(45, '2021-04-25', 91, 2, '2021-04-26'),
(46, '2021-04-25', 91, 2, '2021-04-26'),
(47, '2021-04-25', 91, 2, NULL),
(48, '2021-04-25', 90, 1, NULL),
(49, '2021-04-25', 91, 2, NULL),
(50, '2021-04-25', 91, 2, NULL),
(51, '2021-04-25', 91, 2, '2021-04-26'),
(52, '2021-04-25', 91, 2, NULL),
(53, '2021-04-25', 91, 2, '2021-04-07'),
(54, '2021-04-25', 91, 2, '2021-04-25'),
(55, '2021-04-25', 91, 2, '2021-04-26'),
(57, '2021-04-26', 91, 2, '2021-04-26'),
(58, '2021-04-26', 91, 2, '2021-04-26'),
(59, '2021-04-26', 91, 2, '2021-04-26'),
(60, '2021-04-26', 91, 2, '2021-04-26'),
(61, '2021-04-26', 91, 2, '2021-04-27'),
(62, '2021-04-26', 91, 2, '2021-04-27'),
(63, '2021-04-26', 91, 2, '2021-04-27'),
(64, '2021-04-26', 91, 2, '2021-04-27'),
(65, '2021-04-26', 91, 2, '2021-04-27'),
(66, '2021-04-26', 91, 2, '2021-04-27'),
(67, '2021-04-26', 91, 2, '2021-04-27'),
(68, '2021-04-26', 91, 2, '2021-04-27'),
(69, '2021-04-26', 91, 2, '2021-04-27'),
(70, '2021-04-26', 91, 2, '2021-04-27'),
(71, '2021-04-26', 91, 2, '2021-04-27'),
(72, '2021-04-26', 91, 2, '2021-04-27'),
(73, '2021-04-26', 91, 2, '2021-04-27'),
(74, '2021-04-26', 91, 2, '2021-04-27'),
(75, '2021-04-26', 91, 2, '2021-04-27'),
(76, '2021-04-26', 91, 2, '2021-04-27'),
(77, '2021-04-26', 91, 2, '2021-04-27'),
(78, '2021-04-26', 91, 2, '2021-04-27'),
(79, '2021-04-26', 91, 2, '2021-04-27'),
(80, '2021-04-26', 91, 2, '2021-04-27'),
(81, '2021-04-26', 91, 2, '2021-04-27'),
(82, '2021-04-26', 91, 2, '2021-04-26'),
(83, '2021-04-26', 91, 2, '2021-04-27'),
(84, '2021-04-26', 91, 2, '2021-04-26'),
(85, '2021-04-26', 91, 2, '2021-04-27'),
(86, '2021-04-26', 91, 2, '2021-04-26'),
(87, '2021-04-26', 91, 2, '2021-04-27'),
(88, '2021-04-26', 91, 2, '2021-04-26'),
(89, '2021-04-26', 91, 2, '2021-04-27'),
(90, '2021-04-26', 91, 2, '2021-04-26'),
(91, '2021-04-26', 91, 2, '2021-04-27'),
(92, '2021-04-26', 91, 2, '2021-04-26'),
(93, '2021-04-26', 91, 2, '2021-04-27'),
(94, '2021-04-26', 91, 2, '2021-04-26'),
(95, '2021-04-26', 91, 2, '2021-04-26'),
(96, '2021-04-27', 91, 2, '2021-04-27'),
(97, '2021-04-27', 91, 2, '2021-04-27'),
(98, '2021-04-27', 91, 2, '2021-04-27'),
(99, '2021-04-27', 91, 2, '2021-04-27'),
(100, '2021-04-27', 91, 2, '2021-04-27'),
(101, '2021-04-27', 91, 2, '2021-04-27'),
(102, '2021-04-27', 91, 2, '2021-04-27'),
(103, '2021-04-27', 91, 2, '2021-04-27'),
(104, '2021-04-27', 91, 2, '2021-04-27');

-- --------------------------------------------------------

--
-- Estrutura da tabela `carrinhos_produtos`
--

CREATE TABLE `carrinhos_produtos` (
  `idCarrinhoProduto` bigint(20) NOT NULL,
  `idCarrinho` bigint(20) NOT NULL,
  `idProduto` bigint(20) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `quantidadeItensTrocados` int(11) DEFAULT NULL,
  `precoMomentoCompra` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `carrinhos_produtos`
--

INSERT INTO `carrinhos_produtos` (`idCarrinhoProduto`, `idCarrinho`, `idProduto`, `quantidade`, `quantidadeItensTrocados`, `precoMomentoCompra`) VALUES
(1, 1, 6, 15, NULL, 0),
(6, 1, 7, 9, NULL, 0),
(7, 2, 6, 1, NULL, 0),
(8, 3, 6, 10, NULL, 0),
(9, 4, 6, 5, 1, 0),
(10, 5, 6, 2, NULL, 0),
(11, 6, 6, 2, NULL, 0),
(12, 7, 6, 3, NULL, 0),
(13, 8, 6, 1, NULL, 0),
(14, 9, 6, 1, NULL, 0),
(15, 10, 6, 2, NULL, 0),
(16, 11, 6, 2, NULL, 0),
(20, 12, 6, 12, NULL, 0),
(26, 13, 6, 12, NULL, 0),
(28, 14, 6, 12, NULL, 0),
(30, 15, 6, 12, NULL, 0),
(32, 16, 6, 12, NULL, 0),
(34, 17, 6, 12, NULL, 0),
(36, 18, 6, 12, NULL, 0),
(38, 19, 6, 11, 0, 0),
(40, 20, 6, 2, NULL, 0),
(41, 20, 7, 2, NULL, 0),
(42, 21, 6, 2, NULL, 0),
(43, 22, 6, 2, NULL, 0),
(44, 23, 6, 4, NULL, 0),
(45, 24, 7, 10, NULL, 0),
(48, 25, 7, 24, NULL, 0),
(49, 26, 7, 0, 3, 0),
(50, 27, 7, 1, 0, NULL),
(51, 28, 7, 1, 0, NULL),
(52, 29, 7, 3, 0, NULL),
(53, 30, 7, 6, 0, NULL),
(54, 31, 7, 6, 0, NULL),
(55, 32, 7, 6, 0, NULL),
(56, 33, 6, 2, 0, 1),
(57, 33, 7, 2, 1, 5),
(58, 34, 6, 5, 0, 52),
(59, 34, 7, 1, 0, 260),
(60, 35, 6, 1, 0, 52),
(61, 36, 6, 1, 0, 52),
(62, 37, 6, 1, 0, 52),
(63, 38, 6, 1, 0, 52),
(64, 39, 6, 1, 0, 52),
(65, 40, 6, 1, 0, 52),
(66, 41, 6, 7, 0, 52),
(67, 42, 6, 1, 0, 52),
(68, 43, 7, 4, 0, 260),
(69, 43, 6, 1, 0, 52),
(70, 44, 6, 4, 0, 52),
(71, 45, 6, 2, 0, 52),
(72, 46, 6, 2, 0, 52),
(84, 48, 6, 2, 0, NULL),
(86, 47, 6, 2, 0, 52),
(87, 49, 6, 1, 0, 52),
(88, 50, 6, 1, 0, 52),
(89, 51, 6, 1, 0, 52),
(90, 52, 6, 1, 0, 52),
(91, 53, 6, 1, 0, 52),
(92, 54, 6, 1, 0, 52),
(93, 55, 6, 1, 0, 52),
(94, 56, 6, 1, 0, NULL),
(95, 57, 6, 2, 0, 52),
(96, 58, 6, 1, 0, 52),
(97, 59, 6, 1, 0, 52),
(98, 60, 6, 1, 0, 52),
(99, 61, 6, 12, 0, 52),
(105, 62, 6, 12, 0, 52),
(107, 63, 6, 12, 0, 52),
(109, 64, 6, 12, 0, 52),
(113, 65, 6, 12, 0, 52),
(115, 66, 6, 12, 0, 52),
(117, 67, 6, 12, 0, 52),
(119, 68, 6, 12, 0, 52),
(121, 69, 6, 12, 0, 52),
(123, 70, 6, 12, 0, 52),
(127, 71, 6, 12, 0, 52),
(129, 72, 6, 12, 0, 52),
(131, 73, 6, 12, 0, 52),
(133, 74, 6, 12, 0, 52),
(135, 75, 6, 5, 0, 52),
(136, 76, 6, 12, 0, 52),
(139, 77, 6, 5, 0, 52),
(140, 78, 6, 12, 0, 52),
(143, 79, 6, 5, 0, 52),
(144, 80, 6, 12, 0, 52),
(146, 81, 6, 5, 0, 52),
(147, 82, 6, 12, 0, 52),
(149, 83, 6, 12, 0, 52),
(152, 84, 6, 5, 0, 52),
(153, 85, 6, 12, 0, 52),
(155, 86, 6, 5, 0, 52),
(156, 87, 6, 12, 0, 52),
(158, 88, 6, 5, 0, 52),
(159, 89, 6, 12, 0, 52),
(162, 90, 6, 5, 0, 52),
(163, 91, 6, 12, 0, 52),
(165, 92, 6, 5, 0, 52),
(166, 93, 6, 12, 0, 52),
(168, 94, 6, 5, 0, 52),
(169, 95, 6, 2, 0, 52),
(170, 96, 6, 12, 0, 52),
(172, 97, 6, 5, 0, 52),
(173, 98, 6, 2, 0, 52),
(174, 99, 6, 12, 0, 52),
(176, 100, 6, 5, 0, 52),
(177, 101, 6, 2, 0, 52),
(178, 102, 6, 12, 0, 52),
(180, 103, 6, 5, 0, 52),
(181, 104, 6, 2, 0, 52);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cartoes_aprovados`
--

CREATE TABLE `cartoes_aprovados` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `dataExpiracao` date NOT NULL,
  `cvv` varchar(255) NOT NULL,
  `idBandeira` bigint(20) NOT NULL,
  `limiteDisponivel` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cartoes_aprovados`
--

INSERT INTO `cartoes_aprovados` (`id`, `nome`, `numero`, `dataExpiracao`, `cvv`, `idBandeira`, `limiteDisponivel`) VALUES
(1, 'teste', '1111222233334444', '2022-11-11', '111', 2, 18361.47),
(2, 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, 7740);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cartoes_credito`
--

CREATE TABLE `cartoes_credito` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `dataExpiracao` date NOT NULL,
  `cvv` varchar(255) NOT NULL,
  `idBandeira` bigint(20) NOT NULL,
  `idUsuario` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cartoes_credito`
--

INSERT INTO `cartoes_credito` (`id`, `dataCadastro`, `nome`, `numero`, `dataExpiracao`, `cvv`, `idBandeira`, `idUsuario`) VALUES
(1, '2021-04-13', 'teste', '1111111', '1999-11-11', '120', 1, 34),
(2, '2021-04-13', 'dfgd', '454654', '2028-02-22', '124', 2, 34),
(5, '2021-04-15', 'e', '4', '1999-11-11', '2', 1, 34),
(6, '2021-04-15', 'ed', '55', '2060-02-22', '5', 2, 34),
(7, '2021-04-15', '1df', 'dsfdsdfs', '1111-11-11', '124', 2, NULL),
(8, '2021-04-15', 'gfd', 'dgg', '4545-04-13', 'rwer', 1, NULL),
(9, '2021-04-15', 'tete', 'tete', '1111-11-11', '345', 1, NULL),
(11, '2021-04-23', 'teste', '4444555566667777', '1999-02-22', '235', 1, 88),
(12, '2021-04-23', 'teste', '1111222233334444', '1999-11-11', '111', 1, 89),
(13, '2021-04-23', 'testes', '1111222233335555', '2002-02-22', '112', 2, 90),
(14, '2021-04-24', 'teste', '1111222233334444', '2022-11-11', '111', 2, 91),
(15, '2021-04-25', 's', '1234567890123456', '1999-11-11', '111', 2, 91),
(16, '2021-04-25', 'teste', '1234567890123456', '1999-11-11', '111', 1, 92),
(17, '2021-04-25', 'teste', '111122223333444', '1999-11-11', '123', 1, 93),
(18, '2021-04-25', 'teste', '1111222233334444', '1999-11-11', '123', 1, 94),
(19, '2021-04-25', 'testesd', '1234123412341234', '1999-11-11', '123', 2, 94),
(20, '2021-04-25', 'fg', '9876987698769876', '2009-02-22', '124', 1, 94),
(21, '2021-04-26', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(22, '2021-04-26', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(23, '2021-04-26', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(24, '2021-04-26', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(25, '2021-04-26', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(26, '2021-04-26', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(27, '2021-04-26', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(28, '2021-04-26', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(29, '2021-04-26', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(30, '2021-04-26', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(31, '2021-04-27', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(32, '2021-04-27', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL),
(33, '2021-04-27', 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `categorias`
--

CREATE TABLE `categorias` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidades`
--

CREATE TABLE `cidades` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `idEstado` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cidades`
--

INSERT INTO `cidades` (`id`, `dataCadastro`, `descricao`, `idEstado`) VALUES
(3, '2021-03-01', 'Mogi das Cruzes', 1),
(5, '2021-03-02', 'São Paulo', 1),
(6, '2021-03-02', 'Rio de Janeiro', 2),
(7, '2021-03-02', 'Ribeirão Preto', 1),
(8, '2021-03-02', 'São José dos Campos', 1),
(9, '2021-03-02', 'Indaiatuba', 1),
(11, '2021-04-25', 'Barbacena', 4),
(12, '2021-04-25', 'Macapá', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `sexo` tinyint(4) NOT NULL,
  `dataNascimento` date NOT NULL,
  `idTipoCliente` bigint(20) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `idCartaoPreferencial` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`id`, `dataCadastro`, `nome`, `sexo`, `dataNascimento`, `idTipoCliente`, `status`, `email`, `senha`, `idCartaoPreferencial`) VALUES
(34, '2021-03-02', 'teste', 1, '1988-03-12', 1, 1, 'teste@teste.com.br', 'aca34ae0387518017fa5ff1990ed288e', 0),
(35, '2021-03-02', 'Téstândö', 1, '1999-11-11', 1, 1, '', '', 0),
(36, '2021-03-02', 'as', 1, '1999-11-11', 2, 1, '', '', 0),
(37, '2021-03-02', 'teste', 1, '1990-10-22', 1, 1, '', '', 0),
(38, '2021-03-02', 'Teste 2', 1, '1999-12-12', 2, 0, '', '', 0),
(86, '2021-04-13', 'teste2e', 1, '1999-11-11', 1, 1, '', '', 0),
(88, '2021-04-23', 'teste teste', 1, '2021-04-06', 2, 1, 'teste@ydsdsdst.com', '21047592397a8008198e033434ae8764', 0),
(89, '2021-04-23', 'fgfdgdser', 1, '1999-11-11', 1, 1, 'dfgfdx@gdfg.com', '482bbb67b697aeda87f533c21953ef24', 0),
(90, '2021-04-23', 't', 1, '1999-11-11', 1, 1, 'testelogin@teste.com', 'c21dbbbef3e8ffadd4c28555741dce7a', 0),
(91, '2021-04-24', 'Teste loginho', 1, '1999-11-11', 1, 1, 'teste@loginhio.com', '482bbb67b697aeda87f533c21953ef24', 0),
(92, '2021-04-25', 'testesss', 1, '1999-11-11', 1, 1, 'tes@tes.com', '482bbb67b697aeda87f533c21953ef24', NULL),
(93, '2021-04-25', 'testes', 1, '1999-11-11', 1, 1, 'tes@teste.com', '482bbb67b697aeda87f533c21953ef24', NULL),
(94, '2021-04-25', 'testes', 1, '1999-11-11', 1, 1, 'te@tgest.com', '482bbb67b697aeda87f533c21953ef24', 20);

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes_documentos`
--

CREATE TABLE `clientes_documentos` (
  `idCliente` bigint(20) NOT NULL,
  `idDocumento` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes_enderecos`
--

CREATE TABLE `clientes_enderecos` (
  `idCliente` bigint(20) NOT NULL,
  `idEndereco` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `configuracoes`
--

CREATE TABLE `configuracoes` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `valor` varchar(255) NOT NULL,
  `dataAlteracao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `configuracoes`
--

INSERT INTO `configuracoes` (`id`, `descricao`, `valor`, `dataAlteracao`) VALUES
(1, 'numerosVendaInativacaoAutomatica', '10', '2021-04-26'),
(2, 'diasInativacaoAutomatica', '10', '2021-04-26'),
(3, 'diasPermanenciaCarrinho', '7', '2021-04-26'),
(4, 'diasPermanenciaBloqueioItemCarrinho', '10', '2021-04-26'),
(5, 'cepOrigem', '08780220', '2021-04-26');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cupons_desconto`
--

CREATE TABLE `cupons_desconto` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `valor` double NOT NULL,
  `status` tinyint(4) NOT NULL,
  `dataInicio` date NOT NULL,
  `dataFim` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cupons_desconto`
--

INSERT INTO `cupons_desconto` (`id`, `dataCadastro`, `nome`, `valor`, `status`, `dataInicio`, `dataFim`) VALUES
(1, '2021-04-12', 'DESCONTOEH10!!!', 10, 1, '2021-04-12', '2021-04-30'),
(2, '2021-04-27', 'aaa9', 12, 1, '1999-11-11', '2024-12-12');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cupons_troca`
--

CREATE TABLE `cupons_troca` (
  `id` bigint(20) NOT NULL,
  `dataEntrada` date NOT NULL,
  `idUsuario` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `valor` double NOT NULL,
  `status` tinyint(4) NOT NULL,
  `idPedido` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cupons_troca`
--

INSERT INTO `cupons_troca` (`id`, `dataEntrada`, `idUsuario`, `nome`, `valor`, `status`, `idPedido`) VALUES
(1, '2021-04-12', 34, 'CUPOMTROCA10', 10, 1, NULL),
(2, '2021-04-12', 34, 'CUPOMTROCA20', 20, 1, NULL),
(4, '2021-04-18', 34, 'TESTE20214018084051', 1, 1, NULL),
(5, '2021-04-18', 34, 'TESTE20215118085104', 5, 1, 36),
(6, '2021-04-18', 34, 'TESTE20215218085237', 10, 1, 36),
(7, '2021-04-20', 90, 'TESTE20213120073158', 1, 1, 45),
(8, '2021-04-20', 34, 'TESTE20215220075234', 1, 1, NULL),
(9, '2021-04-21', 34, 'TESTE20215821085818', 5, 1, NULL),
(10, '2021-04-25', 91, 'TESTELOGINHO20213825053846', 52, 1, NULL),
(11, '2021-04-25', 34, 'TESTE20215225055217', 0, 1, NULL),
(12, '2021-04-25', 91, 'TESTELOGINHO20215225055239', 52, 1, 51),
(13, '2021-04-25', 91, 'CUPOMTROCA9120214125074130', 17.7, 1, NULL),
(24, '2021-04-27', 91, 'CUPOMTROCA9120210427020459', 0.01, 1, NULL),
(25, '2021-04-27', 91, 'CUPOMTROCA9120215027035048', 0.01, 1, NULL),
(26, '2021-04-27', 91, 'CUPOMTROCA9120211127041107', 0.01, 1, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `documentos`
--

CREATE TABLE `documentos` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `validade` date NOT NULL,
  `idTipoDocumento` bigint(20) NOT NULL,
  `idCliente` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `documentos`
--

INSERT INTO `documentos` (`id`, `dataCadastro`, `codigo`, `validade`, `idTipoDocumento`, `idCliente`) VALUES
(14, '2021-03-01', '98673997810', '2024-04-04', 1, 30),
(15, '2021-03-01', '81180969200', '2000-02-01', 1, 31),
(16, '2021-03-02', '52564030793', '2070-12-01', 1, 32),
(18, '2021-03-02', '12240565586', '2070-12-01', 1, 33),
(19, '2021-03-02', '32817765745', '2022-02-22', 1, 34),
(20, '2021-03-02', '38723040203', '2070-01-01', 1, 35),
(21, '2021-03-02', '68218240616', '1999-11-11', 1, 36),
(22, '2021-03-02', '11111111111111', '2060-12-12', 2, 36),
(23, '2021-03-02', '171108566', '2040-01-01', 3, 33),
(24, '2021-03-02', '36630153863', '2060-01-01', 1, 37),
(25, '2021-03-02', '02478564000160', '2050-01-01', 2, 37),
(26, '2021-03-02', '35355855547', '2070-11-11', 1, 38),
(27, '2021-03-02', '80363624000102', '2060-11-11', 2, 38),
(30, '2021-03-02', '45263437498', '2030-01-01', 1, 39),
(31, '2021-03-02', '12323609000180', '2040-01-01', 2, 39),
(32, '2021-03-02', '487809444', '2040-01-01', 3, 39),
(33, '2021-03-02', '33864624720', '1999-11-11', 1, 40),
(34, '2021-03-02', '03598852000110', '1999-11-11', 2, 40),
(35, '2021-03-02', '95051674828', '1999-11-11', 1, 41),
(37, '2021-03-02', '487509766', '2040-01-01', 3, 41),
(100, '2021-04-06', '83993121503', '2022-03-31', 1, 83),
(101, '2021-04-06', '43210987654321', '2022-03-31', 2, 34),
(102, '2021-04-06', '33369361795', '2022-03-31', 1, 84),
(103, '2021-04-06', '43250987654321', '2022-03-31', 2, 34),
(105, '2021-04-13', '29328828139', '1999-11-11', 1, 86),
(107, '2021-04-23', '56748262500', '2021-04-29', 1, 88),
(108, '2021-04-23', '47348296246', '1999-11-11', 1, 89),
(109, '2021-04-23', '43122482304', '1999-12-11', 1, 90),
(110, '2021-04-24', '38965015936', '1999-11-11', 1, 91),
(111, '2021-04-25', '48661250390', '1999-11-11', 1, 92),
(112, '2021-04-25', '64607094757', '1999-11-11', 1, 93),
(113, '2021-04-25', '25766975503', '1999-11-11', 1, 94);

-- --------------------------------------------------------

--
-- Estrutura da tabela `editoras`
--

CREATE TABLE `editoras` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `editoras`
--

INSERT INTO `editoras` (`id`, `dataCadastro`, `nome`, `descricao`) VALUES
(1, '2021-04-12', 'Editora teste', 'teste');

-- --------------------------------------------------------

--
-- Estrutura da tabela `enderecos`
--

CREATE TABLE `enderecos` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `logradouro` varchar(255) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `complemento` varchar(255) NOT NULL,
  `cep` varchar(255) NOT NULL,
  `idBairro` bigint(20) NOT NULL,
  `idTipoEndereco` bigint(20) NOT NULL,
  `idCliente` bigint(20) NOT NULL,
  `idtipoResidencia` bigint(20) NOT NULL,
  `idFuncaoEndereco` bigint(20) NOT NULL,
  `idTipoLogradouro` bigint(20) NOT NULL,
  `observacoes` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `enderecos`
--

INSERT INTO `enderecos` (`id`, `dataCadastro`, `nome`, `logradouro`, `numero`, `complemento`, `cep`, `idBairro`, `idTipoEndereco`, `idCliente`, `idtipoResidencia`, `idFuncaoEndereco`, `idTipoLogradouro`, `observacoes`) VALUES
(7, '2021-03-01', 'placeholder', 'rua dos testes', '122', 'as', '11122-245', 4, 1, 30, 0, 0, 0, NULL),
(8, '2021-03-01', 'Teste do nome do endereço', 'Rua dos testes', '12', '', '11111-111', 5, 1, 31, 0, 0, 0, NULL),
(9, '2021-03-01', 'casa da minha mãe', 'rua 1', '12', '', '11111-111', 5, 2, 31, 0, 0, 0, NULL),
(10, '2021-03-02', 'casa da minha vó', 'Rua do Lago', '12', '', '04280-000', 6, 1, 32, 0, 0, 0, NULL),
(11, '2021-03-02', 'meu trabalho', 'Rua Rodrigo Alonso', '554', '', '03925-080', 7, 2, 32, 0, 0, 0, NULL),
(12, '2021-03-02', 'serviço', 'Rua José Urbano Sanches', '6012', '', '08780-220', 3, 2, 30, 0, 0, 0, NULL),
(13, '2021-03-02', 'aaa', 'a', '12', '', '11111-111', 8, 1, 33, 0, 0, 0, NULL),
(14, '2021-03-02', 'asas', 'Rua José Urbano Sanches', '12', '', '08780-220', 3, 2, 34, 0, 0, 0, NULL),
(15, '2021-03-02', 'aaaa', 'Rua João Sanchez Parra', 'sa', 'bloc 10', '14065-200', 9, 1, 35, 0, 0, 0, NULL),
(16, '2021-03-02', 'asa', 'Rua José Urbano Sanches', '12', '', '08780-220', 3, 2, 36, 0, 0, 0, NULL),
(17, '2021-03-02', 'Minha casa', 'Rua Antônio Boarini', '12', '', '12229-130', 10, 1, 37, 0, 0, 0, NULL),
(18, '2021-03-02', 'Minha casa', 'Rua José Urbano Sanches', '12', '', '08780-220', 3, 1, 38, 0, 0, 0, NULL),
(19, '2021-03-02', 'Serviço', 'rua teste', '9', 'bloco e', '98182-120', 11, 2, 38, 0, 0, 0, NULL),
(20, '2021-03-02', 'asas', 'Rua José Urbano Sanches', '12', '', '08780-220', 3, 2, 38, 0, 0, 0, NULL),
(21, '2021-03-02', 'minha casa', 'Rua José Urbano Sanches', '12', '', '08780-220', 3, 1, 39, 0, 0, 0, NULL),
(23, '2021-03-02', 'serviço novo', 'rua sérgio plaza', '40', '20', '44444-444', 13, 2, 39, 0, 0, 0, NULL),
(24, '2021-03-02', 'minha casa', 'Rua José Urbano Sanches', '12', '', '08780-220', 3, 1, 40, 0, 0, 0, NULL),
(25, '2021-03-02', 'serviço', 'rua sérgio plaza', '12', '', '11111-110', 14, 2, 40, 0, 0, 0, NULL),
(26, '2021-03-02', 'minha casa', 'Rua José Urbano Sanches', '12', '', '08780-220', 3, 1, 41, 0, 0, 0, NULL),
(28, '2021-03-02', 'serviço novo', 'Rua Juarez Rodrigues de Lima', '20', '201', '13349-108', 16, 2, 41, 0, 0, 0, NULL),
(96, '2021-04-13', 'teste', 'Avenida São Paulo', '12', '', '08780-570', 18, 1, 86, 0, 0, 0, NULL),
(97, '2021-04-23', 'testy', 'Avenida São Paulo', '45', 'te', '08780-570', 18, 1, 88, 2, 1, 2, 'testsc'),
(98, '2021-04-23', 'teste', 'Rua José Urbano Sanches', '12', '', '08780-220', 3, 1, 89, 1, 1, 1, ''),
(99, '2021-04-23', 'teste', 'Rua José Urbano Sanches', '12', '', '08780-220', 3, 1, 90, 1, 1, 1, ''),
(100, '2021-04-24', 'teste', 'Rua José Urbano Sanches', '1', '', '08780-220', 3, 1, 91, 1, 1, 1, ''),
(101, '2021-04-24', 'aas teste endereco novo na compra', 'Rua José Urbano Sanches', '1', '', '08780-220', 3, 1, 90, 1, 2, 1, ''),
(102, '2021-04-25', 'endereco teste entrega', 'Rua Rio Negro', '1', '', '36205-378', 19, 1, 91, 1, 2, 1, ''),
(103, '2021-04-25', 't', 'Avenida Felipe Camarão', '4', '', '68901-111', 20, 1, 91, 1, 3, 2, ''),
(104, '2021-04-25', 'tes', 'Rua José Urbano Sanches', '12', '', '08780-220', 3, 1, 92, 1, 3, 1, ''),
(105, '2021-04-25', 'ters', 'Rua José Urbano Sanches', '1', '', '08780-220', 3, 2, 93, 1, 3, 1, ''),
(106, '2021-04-25', 'a', 'Rua José Urbano Sanches', '1', '', '08780-220', 3, 1, 94, 1, 3, 1, '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `estados`
--

CREATE TABLE `estados` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `idPais` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `estados`
--

INSERT INTO `estados` (`id`, `dataCadastro`, `descricao`, `idPais`) VALUES
(1, '2021-03-01', 'SP', 1),
(2, '2021-03-02', 'RJ', 1),
(4, '2021-04-25', 'MG', 1),
(5, '2021-04-25', 'AP', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedores`
--

CREATE TABLE `fornecedores` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `idDocumento` bigint(20) NOT NULL,
  `idEndereco` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcoes_enderecos`
--

CREATE TABLE `funcoes_enderecos` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `funcoes_enderecos`
--

INSERT INTO `funcoes_enderecos` (`id`, `dataCadastro`, `nome`, `descricao`) VALUES
(1, '2021-04-21', 'Endereço de Cobrança', 'Endereço de Cobrança'),
(2, '2021-04-23', 'Endereço de Entrega', 'Endereço de Entrega'),
(3, '2021-04-23', 'Endereço de Cobrança e Entrega', 'Endereço de Cobrança e Entrega');

-- --------------------------------------------------------

--
-- Estrutura da tabela `generos`
--

CREATE TABLE `generos` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `grupos_precificacao`
--

CREATE TABLE `grupos_precificacao` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `porcentagem` double NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `grupos_precificacao`
--

INSERT INTO `grupos_precificacao` (`id`, `dataCadastro`, `nome`, `porcentagem`, `status`) VALUES
(1, '2021-04-15', 'Basic', 15, 1),
(2, '2021-04-15', 'Standard', 30, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `livros`
--

CREATE TABLE `livros` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `isbn` varchar(255) NOT NULL,
  `sinopse` varchar(255) NOT NULL,
  `autorId` bigint(20) NOT NULL,
  `ano` varchar(255) NOT NULL,
  `idEditora` bigint(20) NOT NULL,
  `edicao` varchar(255) NOT NULL,
  `numeroPaginas` int(11) NOT NULL,
  `altura` double NOT NULL,
  `largura` double NOT NULL,
  `peso` double NOT NULL,
  `profundidade` double NOT NULL,
  `codigoBarras` varchar(255) NOT NULL,
  `preco` double NOT NULL,
  `capa` varchar(2000) DEFAULT NULL,
  `idGrupoPrecificacao` bigint(20) NOT NULL,
  `status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `livros`
--

INSERT INTO `livros` (`id`, `dataCadastro`, `titulo`, `isbn`, `sinopse`, `autorId`, `ano`, `idEditora`, `edicao`, `numeroPaginas`, `altura`, `largura`, `peso`, `profundidade`, `codigoBarras`, `preco`, `capa`, `idGrupoPrecificacao`, `status`) VALUES
(6, '2021-04-09', 't', '1', '1', 1, '1', 1, 'e', 1, 2, 10, 2, 15, '1', 52, NULL, 2, 1),
(7, '2021-04-09', 'teste livro 2', '1', '1', 1, '1', 1, 'ee', 1, 4, 10, 1, 8, '1', 260, NULL, 2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `livros_categorias`
--

CREATE TABLE `livros_categorias` (
  `id` bigint(20) NOT NULL,
  `idLivro` bigint(20) NOT NULL,
  `idCategoria` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `livros_estoque`
--

CREATE TABLE `livros_estoque` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `quantidade` int(11) NOT NULL,
  `custo` double NOT NULL,
  `dataEntrada` date NOT NULL,
  `fornecedorId` bigint(20) DEFAULT NULL,
  `livroId` bigint(20) NOT NULL,
  `tipoMovimentacao` int(11) NOT NULL,
  `idCliente` bigint(20) DEFAULT NULL,
  `idUsuarioAdmin` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `livros_estoque`
--

INSERT INTO `livros_estoque` (`id`, `dataCadastro`, `quantidade`, `custo`, `dataEntrada`, `fornecedorId`, `livroId`, `tipoMovimentacao`, `idCliente`, `idUsuarioAdmin`) VALUES
(1, '2021-04-15', 1, 3, '2009-11-11', 1, 7, 1, NULL, NULL),
(2, '2021-04-15', 40, 8, '2020-02-11', 2, 7, 1, NULL, NULL),
(3, '2021-04-28', 2, 2, '2021-04-28', 1, 7, 2, NULL, NULL),
(4, '2021-04-16', 5, 1, '2021-04-13', 2, 7, 4, NULL, NULL),
(5, '2021-04-16', 10, 0, '2021-04-16', 0, 7, 2, NULL, NULL),
(6, '2021-04-16', 40, 8, '2008-08-08', 1, 7, 1, NULL, NULL),
(7, '2021-04-16', 24, 0, '2021-04-16', 0, 7, 2, NULL, NULL),
(8, '2021-04-16', 3, 0, '2021-04-16', 0, 7, 2, NULL, NULL),
(9, '2021-04-16', 12, 1, '2002-02-22', 1, 6, 1, NULL, NULL),
(10, '2021-04-18', 1, 0, '2021-04-18', 0, 7, 2, NULL, NULL),
(11, '2021-04-18', 1, 0, '2021-04-18', 0, 7, 2, NULL, NULL),
(12, '2021-04-18', 3, 0, '2021-04-18', 0, 7, 2, NULL, NULL),
(13, '2021-04-18', 1, 0, '2021-04-18', 0, 7, 3, 34, NULL),
(14, '2021-04-18', 2, 0, '2021-04-18', 0, 7, 3, 34, NULL),
(15, '2021-04-19', 6, 0, '2021-04-19', 0, 7, 2, NULL, NULL),
(16, '2021-04-19', 6, 0, '2021-04-19', 0, 7, 2, NULL, NULL),
(17, '2021-04-19', 6, 0, '2021-04-19', 0, 7, 2, NULL, NULL),
(18, '2021-04-20', 3, 1, '1999-11-11', 1, 6, 1, NULL, NULL),
(19, '2021-04-20', 20, 1, '1999-11-11', 1, 6, 1, NULL, NULL),
(20, '2021-04-20', 2, 0, '2021-04-20', 0, 6, 2, NULL, NULL),
(21, '2021-04-20', 3, 0, '2021-04-20', 0, 7, 2, NULL, NULL),
(22, '2021-04-20', 2, 0, '2021-04-20', 0, 6, 2, NULL, NULL),
(23, '2021-04-20', 3, 0, '2021-04-20', 0, 7, 2, NULL, NULL),
(24, '2021-04-21', 4, 2.5, '1999-11-11', 1, 6, 1, NULL, NULL),
(25, '2021-04-21', 10, 13, '2000-02-20', 1, 6, 1, NULL, NULL),
(26, '2021-04-21', 1, 10, '1999-11-11', 2, 6, 1, NULL, NULL),
(29, '2021-04-21', 1, 10, '1999-11-11', 1, 7, 1, NULL, NULL),
(30, '2021-04-21', 10, 100, '1999-11-11', 1, 7, 1, NULL, NULL),
(31, '2021-04-21', 10, 120, '1999-11-11', 1, 7, 1, NULL, NULL),
(32, '2021-04-21', 10, 15, '1999-11-11', 1, 6, 1, NULL, NULL),
(33, '2021-04-21', 1, 30, '1999-11-11', 1, 6, 1, NULL, NULL),
(34, '2021-04-21', 2, 40, '1999-11-11', 2, 6, 1, NULL, NULL),
(35, '2021-04-21', 2, 200, '1999-11-11', 2, 7, 1, NULL, NULL),
(36, '2021-04-21', 1, 0, '2021-04-21', 0, 7, 3, 34, NULL),
(37, '2021-04-22', 5, 0, '2021-04-22', 0, 6, 2, NULL, NULL),
(38, '2021-04-22', 1, 0, '2021-04-22', 0, 7, 2, NULL, NULL),
(39, '2021-04-22', 1, 0, '2021-04-22', 0, 6, 2, NULL, NULL),
(40, '2021-04-22', 1, 0, '2021-04-22', 0, 6, 2, NULL, NULL),
(41, '2021-04-22', 1, 0, '2021-04-22', 0, 6, 2, NULL, NULL),
(42, '2021-04-22', 1, 0, '2021-04-22', 0, 6, 2, NULL, NULL),
(43, '2021-04-24', 1, 0, '2021-04-24', 0, 6, 2, NULL, NULL),
(44, '2021-04-24', 1, 0, '2021-04-24', 0, 6, 2, NULL, NULL),
(45, '2021-04-24', 7, 0, '2021-04-24', 0, 6, 2, NULL, NULL),
(46, '2021-04-24', 2, 2, '2005-05-22', 1, 7, 1, NULL, 60),
(47, '2021-04-24', 1, 0, '2021-04-24', 0, 6, 2, NULL, NULL),
(48, '2021-04-25', 4, 0, '2021-04-25', 0, 7, 2, NULL, NULL),
(49, '2021-04-25', 1, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(50, '2021-04-25', 4, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(51, '2021-04-25', 2, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(52, '2021-04-25', 2, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(53, '2021-04-25', 1, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(54, '2021-04-25', 1, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(55, '2021-04-25', 1, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(56, '2021-04-25', 1, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(57, '2021-04-25', 1, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(58, '2021-04-25', 1, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(59, '2021-04-25', 1, 0, '2021-04-25', 0, 6, 2, NULL, NULL),
(60, '2021-04-26', 2, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(61, '2021-04-26', 1, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(62, '2021-04-26', 1, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(63, '2021-04-26', 1, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(64, '2021-04-26', 1, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(65, '2021-04-26', 1, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(66, '2021-04-26', 1, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(67, '2021-04-26', 1, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(68, '2021-04-26', 100, 10, '1999-11-11', 1, 6, 1, NULL, 60),
(69, '2021-04-26', 500, 12, '1999-11-11', 1, 6, 1, NULL, 60),
(70, '2021-04-26', 5, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(71, '2021-04-26', 5, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(72, '2021-04-26', 5, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(73, '2021-04-26', 5, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(74, '2021-04-26', 5, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(75, '2021-04-26', 5, 0, '2021-04-26', 0, 6, 2, NULL, NULL),
(76, '2021-04-27', 5, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(77, '2021-04-27', 12, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(78, '2021-04-27', 5, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(79, '2021-04-27', 12, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(80, '2021-04-27', 12, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(81, '2021-04-27', 12, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(82, '2021-04-27', 12, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(83, '2021-04-27', 5, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(84, '2021-04-27', 5, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(85, '2021-04-27', 5, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(86, '2021-04-27', 5, 0, '2021-04-27', 0, 6, 2, NULL, NULL),
(87, '2021-04-27', 5, 0, '2021-04-27', 0, 6, 2, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `logs`
--

CREATE TABLE `logs` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `notificacoes`
--

CREATE TABLE `notificacoes` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `idCliente` bigint(20) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `cor` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `paises`
--

CREATE TABLE `paises` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `paises`
--

INSERT INTO `paises` (`id`, `dataCadastro`, `descricao`) VALUES
(1, '2021-02-24', 'Brasil'),
(2, '2021-02-24', 'EUA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidos`
--

CREATE TABLE `pedidos` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `idUsuario` bigint(20) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `idEndereco` bigint(20) NOT NULL,
  `valorFrete` double NOT NULL,
  `idCupomDesconto` bigint(20) DEFAULT NULL,
  `idCarrinho` bigint(20) NOT NULL,
  `valorTotal` double NOT NULL,
  `valorDescontos` double DEFAULT NULL,
  `prazo` int(11) NOT NULL,
  `tipoServico` varchar(10) NOT NULL,
  `dataAlteracao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pedidos`
--

INSERT INTO `pedidos` (`id`, `dataCadastro`, `idUsuario`, `status`, `idEndereco`, `valorFrete`, `idCupomDesconto`, `idCarrinho`, `valorTotal`, `valorDescontos`, `prazo`, `tipoServico`, `dataAlteracao`) VALUES
(6, '2021-04-13', 34, 4, 14, 0, NULL, 3, 0, NULL, 0, '', NULL),
(7, '2021-04-13', 34, 5, 14, 0, NULL, 4, 0, NULL, 0, '', NULL),
(12, '2021-04-13', 34, 4, 14, 0, NULL, 9, 0, NULL, 0, '', NULL),
(13, '2021-04-13', 34, 8, 14, 0, 1, 10, 0, NULL, 0, '', NULL),
(14, '2021-04-13', 34, 8, 14, 0, 1, 11, 2, 10, 0, '', NULL),
(15, '2021-04-13', 34, 8, 14, 0, 1, 12, 12, 10, 0, '', NULL),
(16, '2021-04-13', 34, 8, 14, 0, 1, 13, 12, 10, 0, '', NULL),
(17, '2021-04-13', 34, 8, 14, 0, 1, 14, 12, 10, 0, '', NULL),
(18, '2021-04-13', 34, 8, 14, 0, 1, 15, 12, 10, 0, '', NULL),
(19, '2021-04-13', 34, 8, 14, 0, 1, 16, 12, 10, 0, '', NULL),
(20, '2021-04-13', 34, 8, 14, 0, 1, 17, 12, 10, 0, '', NULL),
(21, '2021-04-13', 34, 8, 14, 0, 1, 18, 12, 10, 0, '', NULL),
(22, '2021-04-13', 34, 4, 14, 0, 1, 19, 12, 10, 0, '', NULL),
(24, '2021-04-15', 34, 8, 14, 0, NULL, 20, 12, NULL, 0, '', NULL),
(25, '2021-04-15', 34, 8, 14, 0, NULL, 21, 2, NULL, 0, '', NULL),
(26, '2021-04-15', 34, 8, 14, 0, NULL, 22, 2, NULL, 0, '', NULL),
(27, '2021-04-15', 34, 8, 14, 0, NULL, 23, 4, NULL, 0, '', NULL),
(28, '2021-04-16', 34, 8, 14, 0, NULL, 24, 50, NULL, 0, '', NULL),
(29, '2021-04-16', 34, 8, 14, 0, NULL, 25, 120, NULL, 0, '', NULL),
(30, '2021-04-16', 34, 4, 14, 0, NULL, 26, 15, NULL, 0, '', NULL),
(33, '2021-04-18', 34, 8, 14, 0, 1, 29, 15, 10, 0, '', NULL),
(34, '2021-04-19', 34, 8, 14, 0, 1, 30, 30, 10, 0, '', NULL),
(35, '2021-04-19', 34, 8, 14, 0, 1, 31, 30, 10, 0, '', NULL),
(36, '2021-04-19', 34, 8, 14, 0, 1, 32, 30, 10, 0, '', NULL),
(38, '2021-04-20', 34, 5, 14, 0, NULL, 33, 17, NULL, 0, '', NULL),
(39, '2021-04-22', 34, 8, 14, 342.2, NULL, 34, 520, NULL, 0, '', NULL),
(40, '2021-04-22', 34, 8, 14, 114.4, NULL, 35, 52, NULL, 4, '', NULL),
(41, '2021-04-22', 34, 8, 0, 101.9, NULL, 36, 52, NULL, 13, '', NULL),
(42, '2021-04-22', 34, 8, 14, 48.9, NULL, 37, 52, NULL, 12, '04510', NULL),
(43, '2021-04-22', 34, 8, 14, 114.4, NULL, 38, 52, NULL, 4, '04014', NULL),
(44, '2021-04-24', 90, 8, 99, 114.4, NULL, 39, 52, NULL, 4, '04014', NULL),
(45, '2021-04-24', 90, 4, 99, 114.4, NULL, 40, 52, NULL, 4, '04014', NULL),
(46, '2021-04-24', 90, 8, 99, 455.9, NULL, 41, 364, NULL, 4, '04014', NULL),
(47, '2021-04-24', 90, 8, 101, 114.4, NULL, 42, 52, NULL, 4, '04014', NULL),
(48, '2021-04-25', 91, 8, 103, 74.1, NULL, 43, 1092, NULL, 12, '04014', NULL),
(49, '2021-04-25', 91, 2, 102, 180.2, NULL, 44, 208, NULL, 13, '04510', NULL),
(50, '2021-04-25', 91, 4, 102, 219.5, NULL, 45, 104, NULL, 4, '04014', NULL),
(51, '2021-04-25', 91, 2, 102, 124.3, NULL, 46, 104, NULL, 13, '04510', NULL),
(52, '2021-04-25', 91, 2, 102, 219.5, NULL, 47, 104, NULL, 4, '04014', NULL),
(53, '2021-04-25', 91, 2, 102, 149.6, NULL, 49, 52, NULL, 4, '04014', NULL),
(54, '2021-04-25', 91, 2, 102, 101.9, NULL, 50, 52, NULL, 13, '04510', NULL),
(55, '2021-04-25', 91, 2, 102, 101.9, NULL, 51, 52, NULL, 13, '04510', NULL),
(56, '2021-04-25', 91, 2, 102, 149.6, NULL, 52, 52, NULL, 4, '04014', NULL),
(57, '2021-04-25', 91, 2, 102, 149.6, NULL, 53, 52, NULL, 4, '04014', NULL),
(58, '2021-04-25', 91, 2, 102, 149.6, NULL, 54, 52, NULL, 4, '04014', NULL),
(59, '2021-04-25', 91, 2, 103, 45.8, NULL, 55, 52, NULL, 12, '04014', NULL),
(60, '2021-04-26', 91, 8, 102, 219.5, NULL, 57, 104, NULL, 4, '04014', NULL),
(61, '2021-04-26', 91, 2, 103, 45.8, NULL, 58, 52, NULL, 12, '04014', NULL),
(62, '2021-04-26', 91, 8, 102, 149.6, NULL, 59, 52, NULL, 4, '04014', NULL),
(63, '2021-04-26', 91, 2, 102, 149.6, NULL, 60, 52, NULL, 4, '04014', NULL),
(64, '2021-04-26', 91, 8, 102, 440.9, NULL, 61, 624, NULL, 2, '04014', NULL),
(65, '2021-04-26', 91, 8, 102, 440.9, NULL, 62, 624, NULL, 2, '04014', NULL),
(66, '2021-04-26', 91, 8, 102, 440.9, NULL, 63, 624, NULL, 2, '04014', NULL),
(67, '2021-04-26', 91, 8, 102, 440.9, NULL, 64, 624, NULL, 2, '04014', NULL),
(68, '2021-04-26', 91, 8, 102, 440.9, NULL, 65, 624, NULL, 2, '04014', NULL),
(69, '2021-04-26', 91, 8, 102, 440.9, NULL, 66, 624, NULL, 2, '04014', NULL),
(70, '2021-04-26', 91, 8, 102, 440.9, NULL, 67, 624, NULL, 2, '04014', NULL),
(71, '2021-04-26', 91, 8, 102, 440.9, NULL, 68, 624, NULL, 2, '04014', NULL),
(72, '2021-04-26', 91, 8, 102, 440.9, NULL, 69, 624, NULL, 2, '04014', NULL),
(73, '2021-04-26', 91, 8, 102, 440.9, NULL, 70, 624, NULL, 2, '04014', NULL),
(74, '2021-04-26', 91, 8, 102, 440.9, NULL, 71, 624, NULL, 2, '04014', NULL),
(75, '2021-04-26', 91, 8, 102, 440.9, NULL, 72, 624, NULL, 2, '04014', NULL),
(76, '2021-04-26', 91, 8, 102, 440.9, NULL, 73, 624, NULL, 2, '04014', NULL),
(77, '2021-04-26', 91, 8, 102, 440.9, NULL, 74, 624, NULL, 2, '04014', NULL),
(78, '2021-04-26', 91, 2, 102, 159.5, 1, 75, 260, 10, 2, '04014', NULL),
(79, '2021-04-26', 91, 8, 102, 440.9, NULL, 76, 624, NULL, 2, '04014', NULL),
(80, '2021-04-26', 91, 2, 102, 159.5, 1, 77, 260, 10, 2, '04014', NULL),
(81, '2021-04-26', 91, 8, 102, 440.9, NULL, 78, 624, NULL, 2, '04014', NULL),
(82, '2021-04-26', 91, 2, 102, 159.5, 1, 79, 260, 10, 2, '04014', NULL),
(83, '2021-04-26', 91, 8, 102, 440.9, NULL, 80, 624, NULL, 2, '04014', NULL),
(84, '2021-04-26', 91, 2, 102, 159.5, 1, 81, 260, 10, 2, '04014', NULL),
(85, '2021-04-26', 91, 8, 102, 440.9, NULL, 82, 624, NULL, 2, '04014', NULL),
(86, '2021-04-26', 91, 8, 102, 440.9, NULL, 83, 624, NULL, 2, '04014', NULL),
(87, '2021-04-26', 91, 2, 102, 159.5, 1, 84, 260, 10, 2, '04014', NULL),
(88, '2021-04-26', 91, 8, 102, 440.9, NULL, 85, 624, NULL, 2, '04014', NULL),
(89, '2021-04-26', 91, 4, 102, 159.5, 1, 86, 260, 10, 2, '04014', NULL),
(90, '2021-04-26', 91, 2, 102, 440.9, NULL, 87, 624, NULL, 2, '04014', NULL),
(91, '2021-04-26', 91, 4, 102, 159.5, 1, 88, 260, 10, 2, '04014', NULL),
(92, '2021-04-26', 91, 2, 102, 440.9, NULL, 89, 624, NULL, 2, '04014', NULL),
(93, '2021-04-26', 91, 4, 102, 159.5, 1, 90, 260, 10, 2, '04014', NULL),
(94, '2021-04-26', 91, 2, 102, 440.9, NULL, 91, 624, NULL, 2, '04014', NULL),
(95, '2021-04-26', 91, 4, 102, 159.5, 1, 92, 260, 10, 2, '04014', NULL),
(96, '2021-04-26', 91, 2, 102, 440.9, NULL, 93, 624, NULL, 2, '04014', NULL),
(97, '2021-04-26', 91, 4, 102, 159.5, 1, 94, 260, 10, 2, '04014', NULL),
(98, '2021-04-26', 91, 8, 102, 95.3, NULL, 95, 104, NULL, 2, '04014', NULL),
(99, '2021-04-27', 91, 2, 102, 440.9, NULL, 96, 624, NULL, 2, '04014', NULL),
(100, '2021-04-27', 91, 4, 102, 159.5, 1, 97, 260, 10, 2, '04014', NULL),
(101, '2021-04-27', 91, 8, 102, 95.3, NULL, 98, 104, NULL, 2, '04014', NULL),
(102, '2021-04-27', 91, 1, 102, 440.9, NULL, 99, 624, NULL, 2, '04014', NULL),
(103, '2021-04-27', 91, 4, 102, 159.5, 1, 100, 260, 10, 2, '04014', NULL),
(104, '2021-04-27', 91, 8, 102, 95.3, NULL, 101, 104, NULL, 2, '04014', NULL),
(105, '2021-04-27', 91, 1, 102, 440.9, NULL, 102, 624, NULL, 2, '04014', NULL),
(106, '2021-04-27', 91, 4, 102, 159.5, 1, 103, 260, 10, 2, '04014', NULL),
(107, '2021-04-27', 91, 8, 102, 95.3, NULL, 104, 104, NULL, 2, '04014', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidos_cartoes`
--

CREATE TABLE `pedidos_cartoes` (
  `idPedidoProduto` bigint(20) NOT NULL,
  `idPedido` bigint(20) NOT NULL,
  `idCartao` bigint(20) NOT NULL,
  `valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pedidos_cartoes`
--

INSERT INTO `pedidos_cartoes` (`idPedidoProduto`, `idPedido`, `idCartao`, `valor`) VALUES
(1, 1, 1, 5),
(2, 1, 1, 4),
(3, 1, 1, 3),
(4, 1, 1, 2),
(5, 26, 1, 2),
(6, 27, 6, 3),
(7, 27, 9, 1),
(8, 28, 1, 50),
(9, 29, 1, 120),
(10, 30, 1, 15),
(11, 31, 1, 4),
(12, 32, 1, 2),
(13, 33, 1, 5),
(14, 34, 1, 5),
(15, 35, 1, 5),
(16, 36, 1, 5),
(18, 38, 1, 17),
(19, 39, 1, 862.2),
(20, 40, 1, 166.4),
(21, 41, 1, 153.9),
(22, 42, 1, 100.9),
(23, 43, 1, 166.4),
(24, 44, 13, 166.4),
(25, 45, 13, 165.4),
(26, 46, 13, 819.9),
(27, 47, 13, 166.4),
(28, 48, 14, 1166.1),
(29, 49, 14, 388.2),
(30, 50, 14, 323.5),
(31, 51, 14, 142),
(32, 52, 14, 323.5),
(33, 53, 14, 201.6),
(34, 54, 14, 153.9),
(35, 55, 14, 153.9),
(36, 56, 14, 201.6),
(37, 57, 15, 201.6),
(38, 58, 14, 201.6),
(39, 59, 14, 97.8),
(40, 60, 14, 323.5),
(41, 61, 14, 97.8),
(42, 62, 14, 181.6),
(43, 62, 15, 20),
(44, 63, 14, 201.6),
(45, 64, 14, 1064.9),
(46, 65, 14, 1064.9),
(47, 66, 14, 1064.9),
(48, 67, 14, 1064.9),
(49, 68, 14, 1064.9),
(50, 69, 14, 1064.9),
(51, 70, 14, 1064.9),
(52, 71, 14, 1064.9),
(53, 72, 14, 1064.9),
(54, 73, 14, 1064.9),
(55, 74, 14, 1064.9),
(56, 75, 14, 1064.9),
(57, 76, 14, 1064.9),
(58, 77, 14, 1064.9),
(59, 78, 14, 319.8),
(60, 78, 21, 20),
(61, 79, 14, 1064.9),
(62, 80, 14, 319.8),
(63, 80, 22, 20),
(64, 81, 14, 1064.9),
(65, 82, 14, 319.8),
(66, 82, 23, 20),
(67, 83, 14, 1064.9),
(68, 84, 14, 319.8),
(69, 84, 24, 20),
(70, 85, 14, 1064.9),
(71, 86, 14, 1064.9),
(72, 87, 14, 319.8),
(73, 87, 25, 20),
(74, 88, 14, 1064.9),
(75, 89, 14, 319.8),
(76, 89, 26, 20),
(77, 90, 14, 1064.9),
(78, 91, 14, 319.8),
(79, 91, 27, 20),
(80, 92, 14, 1064.9),
(81, 93, 14, 319.8),
(82, 93, 28, 20),
(83, 94, 14, 1064.9),
(84, 95, 14, 319.8),
(85, 95, 29, 20),
(86, 96, 14, 1064.9),
(87, 97, 14, 319.8),
(88, 97, 30, 20),
(89, 98, 15, 199.3),
(90, 99, 14, 1064.9),
(91, 100, 14, 319.8),
(92, 100, 31, 20),
(93, 101, 15, 199.3),
(94, 102, 14, 1064.9),
(95, 103, 14, 319.8),
(96, 103, 32, 20),
(97, 104, 15, 199.3),
(98, 105, 14, 1064.9),
(99, 106, 14, 319.8),
(100, 106, 33, 20),
(101, 107, 15, 199.3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `solicitacoes_troca`
--

CREATE TABLE `solicitacoes_troca` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `idItemCarrinho` bigint(20) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `solicitacoes_troca`
--

INSERT INTO `solicitacoes_troca` (`id`, `dataCadastro`, `idItemCarrinho`, `quantidade`, `status`) VALUES
(4, '2021-04-12', 49, 1, 3),
(5, '2021-04-13', 49, 2, 3),
(6, '2021-04-14', 38, 1, 2),
(7, '2021-04-15', 38, 5, 4),
(9, '2021-04-20', 9, 1, 2),
(10, '2021-04-20', 9, 1, 2),
(11, '2021-04-20', 9, 1, 2),
(12, '2021-04-21', 57, 1, 3),
(13, '2021-04-25', 71, 1, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `telefones`
--

CREATE TABLE `telefones` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `idTipoTelefone` bigint(20) NOT NULL,
  `idUsuario` bigint(20) NOT NULL,
  `ddd` varchar(16) NOT NULL,
  `numero` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `telefones`
--

INSERT INTO `telefones` (`id`, `dataCadastro`, `idTipoTelefone`, `idUsuario`, `ddd`, `numero`) VALUES
(2, '2021-04-23', 2, 88, '11', '24578521'),
(4, '2021-04-23', 1, 88, '66', '12345678'),
(5, '2021-04-23', 1, 89, '11', '47969919'),
(6, '2021-04-23', 1, 90, '11', '12345678'),
(7, '2021-04-23', 1, 34, '11', '78945236'),
(8, '2021-04-24', 1, 91, '11', '12345678'),
(9, '2021-04-25', 1, 92, '11', '12345678'),
(10, '2021-04-25', 1, 93, '11', '12345678'),
(11, '2021-04-25', 1, 94, '11', '12345678');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipos_clientes`
--

CREATE TABLE `tipos_clientes` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tipos_clientes`
--

INSERT INTO `tipos_clientes` (`id`, `dataCadastro`, `nome`, `descricao`) VALUES
(1, '2021-02-23', 'Comprador', 'Clientes convencionais.'),
(2, '2021-02-23', 'Revendedor', 'Clientes que compram para revender. Eles podem ter algum desconto.'),
(3, '2021-02-23', 'Parceiro', 'Clientes que possuem parceria com a loja. Eles podem ter algum desconto.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipos_documentos`
--

CREATE TABLE `tipos_documentos` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tipos_documentos`
--

INSERT INTO `tipos_documentos` (`id`, `dataCadastro`, `nome`, `descricao`) VALUES
(1, '2021-02-23', 'CPF', 'Cadastro de Pessoa Física'),
(2, '2021-02-23', 'CNPJ', 'Cadastro Nacional de Pessoa Jurídica'),
(3, '2021-02-23', 'RG', 'Registro Geral'),
(4, '2021-02-23', 'SSN', 'Social Security Number');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipos_enderecos`
--

CREATE TABLE `tipos_enderecos` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tipos_enderecos`
--

INSERT INTO `tipos_enderecos` (`id`, `dataCadastro`, `nome`, `descricao`) VALUES
(1, '2021-02-23', 'Residencial', 'Casa, apartamento etc.'),
(2, '2021-02-23', 'Comercial', 'Empresa');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipos_logradouros`
--

CREATE TABLE `tipos_logradouros` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tipos_logradouros`
--

INSERT INTO `tipos_logradouros` (`id`, `dataCadastro`, `nome`, `descricao`) VALUES
(1, '2021-04-23', 'Rua', 'Rua'),
(2, '2021-04-23', 'Avenida', 'Avenida'),
(3, '2021-04-23', 'Viela', 'Viela'),
(4, '2021-04-23', 'Outro...', 'Outro tipo de logradouro');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipos_residencias`
--

CREATE TABLE `tipos_residencias` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tipos_residencias`
--

INSERT INTO `tipos_residencias` (`id`, `dataCadastro`, `nome`, `descricao`) VALUES
(1, '2021-04-23', 'Casa', 'Casa'),
(2, '2021-04-23', 'Apartamento', 'Apartamento'),
(3, '2021-04-23', 'Outro...', 'Outro tipo de endereço');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipos_telefones`
--

CREATE TABLE `tipos_telefones` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tipos_telefones`
--

INSERT INTO `tipos_telefones` (`id`, `dataCadastro`, `nome`, `descricao`) VALUES
(1, '2021-04-23', 'Residencial', 'Telefone residencial'),
(2, '2021-04-23', 'Celular', 'Telefone celular');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipos_usuarios`
--

CREATE TABLE `tipos_usuarios` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tipos_usuarios`
--

INSERT INTO `tipos_usuarios` (`id`, `dataCadastro`, `nome`, `descricao`) VALUES
(1, '2021-04-23', 'Funcionário', 'Tem acesso limitado às funções do admin'),
(2, '2021-04-23', 'Administrador', 'Tem acesso total às funções do admin');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `status` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `idTipoUsuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `dataCadastro`, `email`, `senha`, `status`, `nome`, `idTipoUsuario`) VALUES
(1, '2021-03-16', 'teste@teste.com', 'f7421e7a9f7ae15212d967531e7216fa', 1, 'teste', 2),
(2, '2021-03-16', 'saoraphael@globo.com', 'cd137e5443e4e970c93cee9e208da17b', 1, 'aline', 2),
(60, '2021-04-23', 'teste@adminnovo.com', '482bbb67b697aeda87f533c21953ef24', 1, 'Teste tipo admin', 1),
(61, '2021-04-24', 'teste@admin3novo.com', '482bbb67b697aeda87f533c21953ef24', 1, 'as', 1);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `autores`
--
ALTER TABLE `autores`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `bairros`
--
ALTER TABLE `bairros`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `bandeiras`
--
ALTER TABLE `bandeiras`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `bloqueios_produtos`
--
ALTER TABLE `bloqueios_produtos`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `carrinhos`
--
ALTER TABLE `carrinhos`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `carrinhos_produtos`
--
ALTER TABLE `carrinhos_produtos`
  ADD PRIMARY KEY (`idCarrinhoProduto`);

--
-- Índices para tabela `cartoes_aprovados`
--
ALTER TABLE `cartoes_aprovados`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `cartoes_credito`
--
ALTER TABLE `cartoes_credito`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `cidades`
--
ALTER TABLE `cidades`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `configuracoes`
--
ALTER TABLE `configuracoes`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `cupons_desconto`
--
ALTER TABLE `cupons_desconto`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `cupons_troca`
--
ALTER TABLE `cupons_troca`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `documentos`
--
ALTER TABLE `documentos`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `editoras`
--
ALTER TABLE `editoras`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `enderecos`
--
ALTER TABLE `enderecos`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `fornecedores`
--
ALTER TABLE `fornecedores`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `funcoes_enderecos`
--
ALTER TABLE `funcoes_enderecos`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `generos`
--
ALTER TABLE `generos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Índices para tabela `grupos_precificacao`
--
ALTER TABLE `grupos_precificacao`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `livros`
--
ALTER TABLE `livros`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `livros_categorias`
--
ALTER TABLE `livros_categorias`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `livros_estoque`
--
ALTER TABLE `livros_estoque`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `notificacoes`
--
ALTER TABLE `notificacoes`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `paises`
--
ALTER TABLE `paises`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `pedidos_cartoes`
--
ALTER TABLE `pedidos_cartoes`
  ADD PRIMARY KEY (`idPedidoProduto`);

--
-- Índices para tabela `solicitacoes_troca`
--
ALTER TABLE `solicitacoes_troca`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `telefones`
--
ALTER TABLE `telefones`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tipos_clientes`
--
ALTER TABLE `tipos_clientes`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tipos_documentos`
--
ALTER TABLE `tipos_documentos`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tipos_enderecos`
--
ALTER TABLE `tipos_enderecos`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tipos_logradouros`
--
ALTER TABLE `tipos_logradouros`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tipos_residencias`
--
ALTER TABLE `tipos_residencias`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tipos_telefones`
--
ALTER TABLE `tipos_telefones`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tipos_usuarios`
--
ALTER TABLE `tipos_usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `autores`
--
ALTER TABLE `autores`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `bairros`
--
ALTER TABLE `bairros`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de tabela `bandeiras`
--
ALTER TABLE `bandeiras`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `bloqueios_produtos`
--
ALTER TABLE `bloqueios_produtos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT de tabela `carrinhos`
--
ALTER TABLE `carrinhos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT de tabela `carrinhos_produtos`
--
ALTER TABLE `carrinhos_produtos`
  MODIFY `idCarrinhoProduto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=182;

--
-- AUTO_INCREMENT de tabela `cartoes_aprovados`
--
ALTER TABLE `cartoes_aprovados`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `cartoes_credito`
--
ALTER TABLE `cartoes_credito`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de tabela `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cidades`
--
ALTER TABLE `cidades`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT de tabela `configuracoes`
--
ALTER TABLE `configuracoes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `cupons_desconto`
--
ALTER TABLE `cupons_desconto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `cupons_troca`
--
ALTER TABLE `cupons_troca`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de tabela `documentos`
--
ALTER TABLE `documentos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;

--
-- AUTO_INCREMENT de tabela `editoras`
--
ALTER TABLE `editoras`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `enderecos`
--
ALTER TABLE `enderecos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT de tabela `estados`
--
ALTER TABLE `estados`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `fornecedores`
--
ALTER TABLE `fornecedores`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `funcoes_enderecos`
--
ALTER TABLE `funcoes_enderecos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `generos`
--
ALTER TABLE `generos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `grupos_precificacao`
--
ALTER TABLE `grupos_precificacao`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `livros`
--
ALTER TABLE `livros`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `livros_categorias`
--
ALTER TABLE `livros_categorias`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `livros_estoque`
--
ALTER TABLE `livros_estoque`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- AUTO_INCREMENT de tabela `logs`
--
ALTER TABLE `logs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `notificacoes`
--
ALTER TABLE `notificacoes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `paises`
--
ALTER TABLE `paises`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT de tabela `pedidos_cartoes`
--
ALTER TABLE `pedidos_cartoes`
  MODIFY `idPedidoProduto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT de tabela `solicitacoes_troca`
--
ALTER TABLE `solicitacoes_troca`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `telefones`
--
ALTER TABLE `telefones`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `tipos_clientes`
--
ALTER TABLE `tipos_clientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `tipos_documentos`
--
ALTER TABLE `tipos_documentos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `tipos_enderecos`
--
ALTER TABLE `tipos_enderecos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tipos_logradouros`
--
ALTER TABLE `tipos_logradouros`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `tipos_residencias`
--
ALTER TABLE `tipos_residencias`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `tipos_telefones`
--
ALTER TABLE `tipos_telefones`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tipos_usuarios`
--
ALTER TABLE `tipos_usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `inativarCarrinhosMeiaNoite` ON SCHEDULE EVERY 1 MINUTE STARTS '2021-04-26 00:41:17' ON COMPLETION PRESERVE ENABLE DO CALL `inativarCarrinhos`()$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
