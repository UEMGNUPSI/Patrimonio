CREATE SCHEMA IF NOT EXISTS `patrimonio` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `patrimonio` ;

-- -----------------------------------------------------
-- Table `Unidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Unidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL UNIQUE,
  `telefone` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) not NULL,
  `email` VARCHAR(45) not NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Bloco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bloco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `id_unidade` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id_unidade) references Unidade(id)
  );


-- -----------------------------------------------------
-- Table `Piso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Piso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `id_bloco` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id_bloco) references Bloco(id))
;


-- -----------------------------------------------------
-- Table `Sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Sala` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `id_piso` INT NOT NULL,
  `inventario` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id_piso) references Piso(id))
;


-- -----------------------------------------------------
-- Table `Entidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Entidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL UNIQUE,
  `cnpj` VARCHAR(45) NOT NULL,
  `contato` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Subtipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Subtipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL UNIQUE,
  `id_tipo` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id_tipo) references Tipo(id));
  
  
-- -----------------------------------------------------
-- Table `Grau_conservacao`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `Grau_conservacao`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `descricao` VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
    );

-- -----------------------------------------------------
-- Table `Patrimonio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Patrimonio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(90) NOT NULL,
  `codigo` VARCHAR(60) NOT NULL,
  `id_subtipo` INT NOT NULL,
  `id_grau_conservacao` INT NOT NULL,
  `id_status` INT NOT NULL,
  `id_sala` INT NOT NULL,
  `nota_fiscal` VARCHAR(45) NOT NULL,
  `id_entidade` INT NOT NULL,
  `kit` boolean NOT NULL,
  `inventario` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id_subtipo) references Subtipo(id),
  FOREIGN KEY (id_status) references `Status`(id),
  FOREIGN KEY (id_sala) references Sala(id),
  FOREIGN KEY (id_entidade) references Entidade(id),
  FOREIGN KEY (id_grau_conservacao) references Grau_conservacao(id)
  );
  

CREATE TABLE IF NOT EXISTS `Usuario`(
 `id` INT NOT NULL AUTO_INCREMENT,
 `usuario` VARCHAR(20) NOT NULL UNIQUE,
 `senha` VARCHAR(20) NOT NULL,
 `admin` boolean NOT NULL,
 `nome` VARCHAR(45) NOT NULL,
 `masp` VARCHAR(45) NOT NULL,
 `contato` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);


insert into Usuario values (0,'root','root',true,'Administrador','adm','adm');

CREATE TABLE IF NOT EXISTS `Patrimonio_composto`(
 `id` INT NOT NULL AUTO_INCREMENT,
 `descricao` VARCHAR(45) NOT NULL,
 `id_grau_conservacao` INT NOT NULL,
 `id_status` INT NOT NULL,
 `id_patrimonio` INT NOT NULL,
 PRIMARY KEY(`id`),
 FOREIGN KEY (id_grau_conservacao) references Grau_conservacao(id),
 FOREIGN KEY(id_status) references `Status`(id),
 FOREIGN KEY(id_patrimonio) references Patrimonio(id)
);

CREATE TABLE IF NOT EXISTS `HistoricoAcoes` (
  `idAcao` INT NOT NULL,
  `idObjeto` INT NOT NULL,
  `tipoObjeto` VARCHAR(45) NOT NULL,
  `acao` VARCHAR(45) NOT NULL,
  `dataAcao` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAcao`)
  );
  
  

