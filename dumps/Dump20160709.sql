CREATE DATABASE  IF NOT EXISTS `gcompet` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gcompet`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: gcompet
-- ------------------------------------------------------
-- Server version	5.5.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `áreas_de_pesquisa`
--

DROP TABLE IF EXISTS `áreas_de_pesquisa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `áreas_de_pesquisa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `áreas_de_pesquisa`
--

LOCK TABLES `áreas_de_pesquisa` WRITE;
/*!40000 ALTER TABLE `áreas_de_pesquisa` DISABLE KEYS */;
INSERT INTO `áreas_de_pesquisa` VALUES (1,'Mísseis e Defesa Antimísseis',NULL),(2,'Sistemas de Guerra Eletrônica',NULL),(3,'Cibernética',NULL),(4,'Defesa Química,Biológica, Radiológica e Nuclear (DQBRN)','Trata do conjunto de tecnologias requeridas para a Defesa Nacional em caso de ataques terroristas ou de forças regulares que empreguem armas de destruição em massa de operação química, biológica ou nuclear.'),(5,'Sistemas Autônomos (Robótica)','Trata do conjunto de tecnologias associadas à aquisição e à representação do conhecimento, sistemas especialistas, interface homem-máquina, sensores, atuadores, controladores e dispositivos mecânicos articulados para atuação em tempo real. Esta área tem forte interação com as áreas de Tecnologia da Informação, Inteligência Artificial, e Guiagem Automática de Precisão.'),(6,'Inteligência Artificial (IA)',NULL),(7,'Sistemas de Informações Geográficas (SIG)',NULL),(8,'Criptografia',NULL),(9,'Fusão de Dados','Trata da capacidade de reunir dados oriundos de bancos de memória já estabelecidos e continuamente realimentados e dados oriundos de sensores ativos e passivos distribuídos na cobertura de uma área de interesse para, em tempo real, auxiliar o processo decisório em situações complexas e de conflito, com todas as restrições aplicáveis ao cenário considerado. As tecnologias envolvidas nesta área são interface homem-máquina, sistemas distribuídos de sensoriamento em tempo real, segurança multinível, desenvolvimento de algoritmos e de sistemas especialistas. Tem grandes interfaces com as áreas dos Sistemas de Informação, Ambiente de Sistemas de Armas e Inteligência de Máquinas e Robótica.'),(10,'Sistemas de Informações','Trata do conjunto de tecnologias de hardware e software dedicadas a aplicações em sistemas de defesa. Dentre elas pode-se citar o grupo de arquiteturas de computação paralela (integração de processadores heterogêneos e distribuídos, de sistemas e periféricos dedicados, projeto de arquiteturas, desenvolvimento de algoritmos, ferramentas, linguagens, compiladores, sistemas operacionais e debugadores), confiabilidade de software (software para aplicações em tempo real, software tolerante à falha, ambientes e processos de geração automática de software), e processamento de sinais (desenvolvimento de técnicas de filtragem, processamento multidimensional adaptativo e processamento de sinais de matrizes controladas por fases). Tem grandes interfaces com as áreas de Fusão de Dados, de Ambiente de Sistemas de Armas e de Inteligência de Máquinas e Robótica.'),(11,'Geoposicionamento a partir de estações terrestres georreferenciamento',NULL),(12,'Sensores Ativos e Passivos','Trata do conjunto de tecnologias associadas ao desenvolvimento, qualificação e integração a sistemas de defesa de sensores que recebam sinais de qualquer tipo (passivos) ou que ativamente emitam algum tipo de sinal e recolham respostas das emissões para fins de identificação e/ou coleta de inteligência sobre alvos de interesse (ativos). Inclui sensores que operam nas faixas do ultravioleta, visível e infravermelho do espectro eletromagnético e outros que operam nas faixas de frequências do espectro acústico. Esta área tem forte interação com a área de Controle de Assinaturas.'),(13,'Imageamento de Alta resolução por RF em laser',NULL),(14,'Novos materiais para uso militar','Trata do conjunto de tecnologias necessárias para a pesquisa, desenvolvimento, qualificação, produção e emprego de novos materiais envolvendo matrizes poliméricas, metálicas, cerâmicas, de carbono, ou de outras classes, com diversos tipos de fibras reforçadoras. O objetivo a atingir nesta área é o de obter condições cada vez mais vantajosas em termos de peso, absorção de energia eletromagnética, resistência mecânica estática, dinâmica, a impactos, à fadiga e à cargas térmicas e resistência à erosão pela submissão desses materiais a escoamentos química e eletromagneticamente reativos.'),(15,'Tecnologia anti-jamming',NULL),(16,'Computação de alto desempenho',NULL),(17,'Camuflagem ativa (ou adaptativa)',NULL),(18,'Energia dirigida – RF, laser ou partículas',NULL),(19,'Sistemas incapacitantes de baixa letalidade',NULL),(20,'Tecnologias furtivas',NULL),(21,'Simulação e simuladores',NULL),(22,'Nanotecnologia para uso militar',NULL),(23,'Fontes de energia elétrica e dispositivos de conversão',NULL),(24,'Antenas',NULL),(25,'Potência Pulsada','Trata do conjunto de tecnologias associadas com a concentração e liberação de alta quantidade de energia em local de dimensões reduzidas, em pulsos, envolvendo técnicas de micro-ondas, laser e acústica, ente outras possibilidades. Essas técnicas envolvem genericamente fontes compactas de alta potência, condicionamento e comutação de potência.'),(26,'Munição lead-free',NULL),(27,'Biotecnologia','Trata do conjunto de tecnologias da área biológica que têm aplicação dual e podem ser adaptadas para emprego na Defesa Nacional. Dentre elas citam-se o uso de materiais naturais, fibras, enzimas, catalisadores, lubrificantes, aromáticos, bio-sensores, materiais orgânicos com propriedades eletro-ópticas, resinas orgânicas estruturais, técnicas em biocorrosão, em bioquiímica, processos naturais e ambientais.'),(28,'Propelentes e iniciadores ambientalmente seguros (Green Amunnition)',NULL),(29,'Física de Plasma',NULL);
/*!40000 ALTER TABLE `áreas_de_pesquisa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areas`
--

DROP TABLE IF EXISTS `areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descricao` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas`
--

LOCK TABLES `areas` WRITE;
/*!40000 ALTER TABLE `areas` DISABLE KEYS */;
INSERT INTO `areas` VALUES (1,'Fusão de Dados','Trata da capacidade de reunir dados oriundos de bancos de memória já estabelecidos e continuamente realimentados e dados oriundos de sensores ativos e passivos distribuídos na cobertura de uma área de interesse para, em tempo real, auxiliar o processo decisório em situações complexas e de conflito, com todas as restrições aplicáveis ao cenário considerado. As tecnologias envolvidas nesta área são interface homem-máquina, sistemas distribuídos de sensoriamento em tempo real, segurança multinível, desenvolvimento de algoritmos e de sistemas especialistas. Tem grandes interfaces com as áreas dos Sistemas de Informação, Ambiente de Sistemas de Armas e Inteligência de Máquinas e Robótica.'),(2,'Microeletrônica','Trata da concepção, desenvolvimento, teste, produção e utilização de componentes e circuitos microeletrônicos. As principais tecnologias envolvidas são a produção de materiais semicondutores em escala de micrômetros, processos de preparação e fabricação de wafers, encapsulamento, montagem e teste de componentes, ferramentas de projeto de circuitos complexos de microeletrônica auxiliada por computador (Computer-Aided Design - CAD) e robustez de circuitos perante interferências e radiações.'),(3,'Sistemas de Informação','Trata do conjunto de tecnologias de hardware e software dedicadas a aplicações em sistemas de defesa. Dentre elas pode-se citar o grupo de arquiteturas de computação paralela (integração de processadores heterogêneos e distribuídos, de sistemas e periféricos dedicados, projeto de arquiteturas, desenvolvimento de algoritmos, ferramentas, linguagens, compiladores, sistemas operacionais e debugadores), confiabilidade de software (software para aplicações em tempo real, software tolerante à falha, ambientes e processos de geração automática de software), e processamento de sinais (desenvolvimento de técnicas de filtragem, processamento multidimensional adaptativo e processamento de sinais de matrizes controladas por fases). Tem grandes interfaces com as áreas de Fusão de Dados, de Ambiente de Sistemas de Armas e de Inteligência de Máquinas e Robótica.'),(4,'Radares de Alta Sensibilidade','Trata do conjunto de tecnologias associadas a radares de banda larga, radares a laser, sensores para identificação não-cooperativa e radares de abertura sintética (SAR) de pequeno tamanho. Tem grande interação com as áreas de Sistemas de Informação e Controle de Assinaturas.'),(5,'Ambientes de Sistemas de Armas','Trata do conjunto de tecnologias associadas a sensoriamento remoto ambiental de alta resolução, previsão de características ambientais com alta acurácia (meteorologia de precisão) e modelos de cenário para projeto e avaliação de sistemas de armas. Tem grande interação com as áreas de Fusão de Dados, Sistemas de Informação e Sensores Ativos e Passivos.'),(6,'Materiais de Alta Densidade Energética','Trata do conjunto de tecnologias associadas a materiais energéticos de alto desempenho, baixa vulnerabilidade e baixa sensibilidade à ativação, propelentes não-tóxicos e de alto desempenho para aplicações espaciais e explosivos de alto desempenho, alta energia de fragmentação e alto poder de penetração.'),(7,'Hipervelocidade','Trata do conjunto de tecnologias associadas a escoamentos de alta velocidade, no regime hipersônico, ou outros meios propulsivos em conexão com o projeto, propulsão e interação de projéteis hipervelozes e seus alvos em potencial.'),(8,'Potência Pulsada','Trata do conjunto de tecnologias associadas com a concentração e liberação de alta quantidade de energia em local de dimensões reduzidas, em pulsos, envolvendo técnicas de micro-ondas, laser e acústica, ente outras possibilidades. Essas técnicas envolvem genericamente fontes compactas de alta potência, condicionamento e comutação de potência.'),(9,'Navegação Automática de Precisão','Trata do conjunto de tecnologias requeridas para identificar posição geo-referenciada e velocidade em tempo real, de forma a permitir a navegação, segundo controle humano do solo, ou navegação autônoma, segundo programação prévia, incluindo os sensores, computadores embarcados, atuadores e interfaces analógicas e/ou digitais requeridas. Tem forte interação com as áreas de Tecnologia da Informação e Inteligência de Máquinas e Robótica.'),(10,'Materiais Compostos','Trata do conjunto de tecnologias necessárias para a pesquisa, desenvolvimento, qualificação, produção e emprego de novos materiais envolvendo matrizes poliméricas, metálicas, cerâmicas, de carbono, ou de outras classes, com diversos tipos de fibras reforçadoras. O objetivo a atingir nesta área é o de obter condições cada vez mais vantajosas em termos de peso, absorção de energia eletromagnética, resistência mecânica estática, dinâmica, a impactos, à fadiga e à cargas térmicas e resistência à erosão pela submissão desses materiais a escoamentos química e eletromagneticamente reativos.'),(11,'Dinâmica dos Fluídos Computacional','Trata do conjunto de conhecimentos e tecnologias associadas à modelagem e solução em computador das equações da Mecânica dos Fluidos envolvendo variáveis como massa específica, pressão, temperatura e três componentes da velocidade do fluido. Essas seis variáveis aparecem em forma não-linear e intricada nas equações de Navier-Stokes: conservação da massa, momentum, energia e gás perfeito. No contexto de escoamentos hipersônicos, vêm se juntar a esse conjunto equações de cinética química, interações eletromagnéticas e outras relações constitutivas.'),(12,'Sensores Ativos e Passivos','Trata do conjunto de tecnologias associadas ao desenvolvimento, qualificação e integração a sistemas de defesa de sensores que recebam sinais de qualquer tipo (passivos) ou que ativamente emitam algum tipo de sinal e recolham respostas das emissões para fins de identificação e/ou coleta de inteligência sobre alvos de interesse (ativos). Inclui sensores que operam nas faixas do ultravioleta, visível e infravermelho do espectro eletromagnético e outros que operam nas faixas de freqüências do espectro acústico. Esta área tem forte interação com a área de Controle de Assinaturas.'),(13,'Fotônica','Trata do conjunto de tecnologias associadas a dispositivos que empreguem a luz como elemento sensor, condutor de informações de qualquer tipo ou condutor de potência. Dentre esses dispositivos, citam-se aqueles baseados em fibra óptica, como sensores e redes de comunicação e de potência, dispositivos ópticos como diodos laser, moduladores, comutadores e interconectores, e circuitos de óptica integrada.'),(14,'Inteligência de Máquinas e Robótica','Trata do conjunto de tecnologias associadas à aquisição e à representação do conhecimento, inteligência artificial, sistemas especialistas, interface homem-máquina, sensores, atuadores, controladores e dispositivos mecânicos articulados para atuação em tempo real. Esta área tem forte interação com as áreas de Tecnologia da Informação e Guiagem Automática de Precisão.'),(15,'Controle de Assinaturas','Trata do conjunto de tecnologias associadas ao controle de assinaturas de alvos em todo o espectro de freqüências conhecido. Incluem-se aqui as assinaturas de sinal radar, termal, visual e acústico, bem como as formas de propagação desses sinais, particularmente das ondas acústicas em meio ao ar e à água. Esta área tem grande interação com a área de Sensores Ativos e Passivos.'),(16,'Reatores Nucleares','Trata do conjunto de tecnologias requeridas para o domínio do conhecimento, da capacidade de concepção, desenvolvimento, qualificação, produção, operação e manutenção de reatores nucleares para aplicações de interesse da Defesa Nacional.'),(17,'Sistemas Espaciais','Trata do conjunto de tecnologias requeridas para o domínio do conhecimento, da capacidade de concepção, desenvolvimento, qualificação, produção, operação e acompanhamento de foguetes de sondagem, veículos lançadores de satélites, satélites e outros sistemas espaciais para aplicações de interesse da Defesa Nacional. Trata-se de área bastante verticalizada, com forte interação com muitas outras Áreas Estratégicas.'),(18,'Propulsão com Ar Aspirado','Trata do conjunto de tecnologias associadas a escoamentos quimicamente reativos da aerotermodinâmica, sistemas com alta razão de compressão, câmaras de combustão e turbinas de alta temperatura, escapamentos de geometria variável, de baixa assinatura, e materiais leves e resistentes, estrutural e termicamente.'),(19,'Materiais e Processo em Biotecnologia','Trata do conjunto de tecnologias da área biológica que têm aplicação dual e podem ser adaptadas para emprego na Defesa Nacional. Dentre elas citam-se o uso de materiais naturais, fibras, enzimas, catalisadores, lubrificantes, aromáticos, bio-sensores, materiais orgânicos com propriedades eletro-ópticas, resinas orgânicas estruturais, técnicas em biocorrosão, em bioquiímica, processos naturais e ambientais.'),(20,'Defesa Química, Biológica e Nuclear','Trata do conjunto de tecnologias requeridas para a Defesa Nacional em caso de ataques terroristas ou de forças regulares que empreguem armas de destruição em massa de operação química, biológica ou nuclear.'),(21,'Integração de Sistemas','Trata do conjunto de tecnologias requeridas para integração de áreas tecnológicas, de forma a produzir macro-sistemas de operação em tempo real. Fala-se aqui da integração das áreas de Fusão de Dados, Sistemas de Informação, Ambiente de Sistemas de Armas, Controle de Assinaturas e outras, dependendo do tipo de aplicação.'),(22,'Supercondutividade','Trata do conjunto de tecnologias associadas ao desenvolvimento, processamento e aplicação de materiais de classe HTS (com temperatura de transição menor que 23 K) e LTS (com temperatura de transição maior que 23 K), bem como a integração dos mesmos com dispositivos semicondutores. Sem dúvida, tecnologias de criogenia fazem parte desta área.'),(23,'Fontes Renováveis de Energia','Trata do conjunto de tecnologias que permitam o funcionamento por longo período de unidades móveis autônomas ou que apoiem as necessidades de energia de soldados, individualmente considerados, a qualquer hora do dia ou da noite. Dentre as possibilidades conhecidas hoje destacam-se as células combustível à base de hidrogênio e fontes nucleares móveis. Não estão descartadas outras opções, como a energia solar, desde que elas venham a demonstrar praticabilidade operacional.');
/*!40000 ALTER TABLE `areas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areas_empresas`
--

DROP TABLE IF EXISTS `areas_empresas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areas_empresas` (
  `id` int(11) NOT NULL,
  `empresas_id` int(11) NOT NULL,
  `areas_id` int(11) NOT NULL,
  `usuarios_id` int(11) DEFAULT NULL,
  `avaliacao` tinyint(6) DEFAULT NULL,
  `avaliada` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_areas_empresas_empresas_id` (`empresas_id`),
  KEY `FK_areas_empresas_areas_id` (`areas_id`),
  CONSTRAINT `FK_areas_empresas_areas_id` FOREIGN KEY (`areas_id`) REFERENCES `areas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_areas_empresas_empresas_id` FOREIGN KEY (`empresas_id`) REFERENCES `empresas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas_empresas`
--

LOCK TABLES `areas_empresas` WRITE;
/*!40000 ALTER TABLE `areas_empresas` DISABLE KEYS */;
/*!40000 ALTER TABLE `areas_empresas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areas_projetos`
--

DROP TABLE IF EXISTS `areas_projetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areas_projetos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PROJETO_id` int(11) NOT NULL,
  `AREA_id` int(11) NOT NULL,
  `USUARIO_id` int(11) DEFAULT NULL,
  `avaliacao` tinyint(6) DEFAULT NULL,
  `avaliada` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_areas_projetos_areas_id` (`AREA_id`),
  KEY `FK_areas_projetos_usuarios_id_idx` (`USUARIO_id`),
  KEY `FK_areas_projetos_projetos_id` (`PROJETO_id`),
  CONSTRAINT `FK_areas_projetos_areas_id` FOREIGN KEY (`AREA_id`) REFERENCES `areas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_areas_projetos_projetos_id` FOREIGN KEY (`PROJETO_id`) REFERENCES `projetos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_areas_projetos_usuarios_id` FOREIGN KEY (`USUARIO_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas_projetos`
--

LOCK TABLES `areas_projetos` WRITE;
/*!40000 ALTER TABLE `areas_projetos` DISABLE KEYS */;
INSERT INTO `areas_projetos` VALUES (1,1,4,2,1,1),(2,2,4,2,1,1),(3,3,4,2,1,1),(4,4,4,2,0,1),(5,5,4,2,0,1),(6,6,4,2,0,1),(7,14,4,2,1,1);
/*!40000 ALTER TABLE `areas_projetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capacidades`
--

DROP TABLE IF EXISTS `capacidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capacidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descricao` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capacidades`
--

LOCK TABLES `capacidades` WRITE;
/*!40000 ALTER TABLE `capacidades` DISABLE KEYS */;
INSERT INTO `capacidades` VALUES (1,'Mobilidade Estratégica','Ser capaz de transportar uma força em grandes distâncias, proporcionando velocidade de intervenção e flexibilidade de emprego entre áreas estratégicas diferentes do território nacional e do entorno estratégico.'),(2,'Suporte à Projeção de Força','Ser capaz de planejar, gerir e executar eficazmente o movimento, o transporte e a distribuição de recursos a partir de suas bases até o seu destino final inclui todas as atividades relacionadas ao manejo, desde bases em território nacional até pontos de embarque e destes até a região onde a força irá cumprir sua missão.'),(3,'Prontidão','Ser capaz de, em curto espaço de tempo, estar em condições de empregar uma força no cumprimento de missões, valendo-se de seus próprios recursos orgânicos e meios disponibilizados'),(4,'Combate Individual','Ser capaz de permitir ao combatente terrestre sobreviver, deslocar-se e combater em todos os ambientes operacionais e sob quaisquer condições climáticas'),(5,'Operações Especiais','Ser capaz de realizar operações militares que, por sua natureza, técnicas ou características, não podem ser realizadas por forças convencionais'),(6,'Ação Terrestre','Ser capaz de executar ações com o objetivo de dissuadir, prevenir ou enfrentar uma ameaça, impondo a sua vontade.'),(7,'Manobra','Ser capaz de empregar forças no campo de batalha por meio do movimento (incluindo o emprego de plataformas aeromóveis) e fogo direto, buscando alcançar uma posição de vantagem sobre forças terrestres inimigas, enfrentando-as e cumprindo a sua missão.'),(8,'Apoio de Fogo','Ser capaz de apoiar as operações das forças amigas com fogos potentes, profundos e precisos, buscando a destruição, neutralização ou supressão de objetivos e das forças inimigas.'),(9,'Mobilidade e Contramobilidade','Ser capaz de modificar o terreno, eliminando ou reduzindo às dificuldades naturais ou artificiais que surgem durante o movimento de forças ou dificultando o movimento do adversário.'),(10,'Preparação da Força','Ser capaz de atingir os níveis de adestramento desejados para a força, incluindo a avaliação, apoiados em modernos sistemas de simulação viva, construtiva e virtual para o treinamento de tropa, que permitam realizar com eficácia e segurança as missões e tarefas atribuídas.'),(11,'Proteção Integrada','Ser capaz de proteger a sociedade, realizando a garantia dos Poderes Constitucionais, a Garantia da Lei e da Ordem, a proteção de Estruturas Estratégicas, a prevenção e o combate às ações terroristas e a participação da Força Terrestre em ações na Faixa de Fonteira, com ampla colaboração do setor de segurança pública.'),(12,'Atribuições Subsidiárias','Ser capaz de cooperar para o desenvolvimento nacional e o bem-estar social e para o apoio ao desenvolvimento econômico e de infraestrutura.'),(13,'Emprego em Apoio à Política Externa em Tempo de Paz ou de Crise','Ser capaz de empregar força no nível aquém da violência, concentrando meios, realizando exercícios de adestramento nas fronteiras com países lindeiros, enquanto se desenvolvem as ações diplomáticas para a solução de um conflito, dentre outras ações.'),(14,'Ações sob a égide de Organismos Internacionais','Ser capaz de empregar força com defesa dos interesses nacionais, por meio da combinação de ações coercitivas limitadas e de ações construtivas. As coercitivas podem ser aplicadas em situações de crise para restaurar ou manter um ambiente seguro e estável e proteger civis e organizações de ajuda humanitárias sob a ameaça iminente de violência física. As ações construtivas, normalmente na paz instável, são utilizadas para apoiar um governo local ou de nação anfitriã em seus esforços de estabilização, de reconstrução, de restauração e de consolidação da paz.'),(15,'Planejamento e Condução','Ser capaz de realizar planejamento, preparação, execução e avaliação contínua de operações militares no amplo espectro, empregando meios e armamentos modernos, baseados em Tecnologias de Informações e Comunicações, com adequada proteção.'),(16,'Sistemas de Comunicações','Ser capaz de estabelecer estruturas de comunicações para suportar toda a necessidade de transmissão para a condução dos processos de apoio à decisão, as informações para a consciência situacional do Comandante nos diversos níveis e as ações para a busca da superioridade da informação.'),(17,'Consciência Situacional','Ser capaz de proporcionar em todos os níveis de decisão, do estratégico ou tático, em tempo real, a compreensão e a integração do ambiente operacional e a percepção atualizada sobre a situação das tropas amigas e dos oponentes. É propiciada pela integração dos dados provenientes de sensores, sistemas de armas e satélites (civis, militares, nacionais e multinacionais), apoiados em infraestrutura de informação e comunicações com nível de proteção adequada.'),(18,'Gestão do Conhecimento e das Informações','Ser capaz de gerir e compartilhar o fluxo de conhecimentos coletados ou produzidos por instituições militares e civis, nacionais e internacionais, em uma infraestrutura adequada, visando a dar suporte aos Comandantes, em todos os níveis de decisão, para o emprego dos meios e forças militares terrestres.'),(19,'Digitalização do Espaço de Batalha','Ser capas de apresentar a representação digital de aspectos do Espaço de Batalha obtida pela integração entre sensores, vetores e radares (civis, militares, nacionais e internacionais), apoiada em uma infraestrutura de informação e comunicações (IIC), permitindo disponibilizar informações aos diferentes níveis de decisão, independente do lugar em que se encontra, com nível de proteção adequada.'),(20,'Modelagem, Simulação e Prevenção','Ser capaz de realizar a modelagem, a imitação e/ou a representação de procedimentos de combate e de operações de nossas forças e das forças adversárias para ampliar a visão dos Comandantes nos diversos níveis de decisão. Utiliza recursos humanos, instalações e meios de tecnologia da informação.'),(21,'Apoio Logístico para Forças Desdobradas','Ser capaz de sustentar as forças desdobradas, com os recursos necessários para manter a capacidade operativa.'),(22,'Infraestrutura da Área de Operações','Ser capaz de construir, adaptar ou reabilitar infraestruturas essenciais para a força desdobrada.'),(23,'Gestão e Coordenação Logística','Ser capaz de planejar, monitorar controlar o apoio logístico direta ou indiretamente relacionado com a sustentação da força desdobrada, permitindo a identificação antecipada e solução das necessidades logísticas desta.'),(24,'Saúde nas Operações','Ser capaz de realizar assistência médica adequada e oportuna. Inclui triagem, estabilização de pacientes, evacuação, diagnóstico, tratamento, hospitalização em campanha e medicina preventiva.'),(25,'Gestão de Recursos Financeiros','Ser capaz de executar a gestão dos recursos financeiros da força empregada.'),(26,'Interoperabilidade Conjunta','Ser capaz de atuar com força constituída de maneira integrada, coordenada, harmônica e complementar com as demais Forças Armadas envolvidas em operações conjuntas.'),(27,'Interoperabilidade Combinada','Ser capaz de atuar com força constituída de maneira integrada, coordenada, harmônica e complementar com as demais Forças Armadas envolvidas e forças de outras Nações, sob a égide de organismo internacional.'),(28,'Interoperabilidade Interagência','Ser capaz de atuar com força constituída de maneira integrada, coordenada, harmônica e complementar, em ambientes interagências, para o cumprimento das missões estabelecidas em apoio a órgãos governamentais.'),(29,'Proteção ao Pessoal','Ser capaz de proteger o pessoal (militar e civil) contra os efeitos das ações próprias inimigas e naturais.'),(30,'Proteção Física','Ser capaz de proteger o material, as instalações e o território de qualquer ameaça à sua integridade em áreas definidas.'),(31,'Segurança das Informações e Comunicações','Ser capaz de fornecer proteção adequada, mantendo a integridade e a disponibilidade dos sistemas e das informações armazenadas, processadas ou transmitidas, por meio da implementação de medidas adequadas para viabilizar e assegurar a disponibilidade, a integridade, a confidencialidade e a autenticidade de dados e informações.'),(32,'Guerra Eletrônica','Ser capaz de desempenhar atividades que visam a desenvolver e assegurar o emprego eficiente das emissões eletromagnéticas próprias, ao mesmo tempo em que buscam impedir, dificultar ou tirar proveito das emissões inimigas.'),(33,'Operações de Apoio à Informação','Ser capaz de apoiar ou desenvolver processos e ações, em tempo de paz, crise ou conflito, para informar e influenciar os diversos públicos existentes (hostil, amigo ou neutro), a fim de obter uma atitude positiva de nossas ações e inibir as percepções contrárias a nossa atuação.'),(34,'Comunicação Social','Ser capaz de proporcionar ao Comandante, em todos os níveis de decisão, do estratégico ao tático, melhores condições de interatividade com as autoridades, a sociedade, a imprensa e o público interno para obter liberdade de ação no emprego dos seus meios, enquanto atrais, motiva e mantém capital humano para a Força Terrestre.'),(35,'Inteligência','Ser capaz de realizar um conjunto de atividade integradas por sistemas inter-relacionados e obter dados não disponíveis e protegidos por medidas de segurança estabelecidas (incluindo o espaço cibernético), para assegurar a compreensão sobre o ambiente operacional, as ameaças, o adversário, o terreno e as condições civis.'),(36,'Exploração Cibernética','Ser capaz de conduzir ações de busca ou coleta, nos Sistemas de Tecnologia da Informação de interesse, a fim de obter dados. Essas ações devem preferencialmente evitar o rastreamento e servir para a produção de conhecimento ou identificar as vulnerabilidades desses sistemas.'),(37,'Proteção Cibernética','Ser capaz de de conduzir ações para neutralizar ataques e exploração cibernética contra os nossos dispositivos computacionais, redes de computadores e de comunicações, incrementando as ações de Segurança, defesa e Guerra Cibernética em face de uma situação de crise e conflito. É uma atividade de caráter permanente.'),(38,'Ataque Cibernético','Ser capaz de conduzir ações para interromper, negar, degradar, corromper ou destruir informações ou sistemas computacionais armazenados em dispositivos e redes de computadores e de comunicações do oponente.');
/*!40000 ALTER TABLE `capacidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capacidades_areas`
--

DROP TABLE IF EXISTS `capacidades_areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capacidades_areas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USUARIO_id` int(11) NOT NULL,
  `AREA_id` int(11) NOT NULL,
  `CAPACIDADE_id` int(11) NOT NULL,
  `avaliacao` smallint(6) NOT NULL,
  `avaliada` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_capacidades_areas_USUARIO_id` (`USUARIO_id`),
  KEY `FK_capacidades_areas_AREA_id` (`AREA_id`),
  KEY `FK_capacidades_areas_CAPACIDADE_id` (`CAPACIDADE_id`),
  CONSTRAINT `fk_capacidades_areas_USUARIO_id` FOREIGN KEY (`USUARIO_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_capacidades_areas_AREA_id` FOREIGN KEY (`AREA_id`) REFERENCES `areas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_capacidades_areas_CAPACIDADE_id` FOREIGN KEY (`CAPACIDADE_id`) REFERENCES `capacidades` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capacidades_areas`
--

LOCK TABLES `capacidades_areas` WRITE;
/*!40000 ALTER TABLE `capacidades_areas` DISABLE KEYS */;
INSERT INTO `capacidades_areas` VALUES (1,1,1,1,1,1),(2,1,2,1,1,1),(3,1,3,1,1,1),(4,1,4,1,1,1),(5,1,5,1,0,1),(6,1,6,1,0,1),(7,1,7,1,1,1),(8,1,8,1,1,1),(9,1,9,1,1,1),(10,1,10,1,1,1),(11,1,11,1,0,1),(12,1,12,1,1,1),(13,1,13,1,0,1),(14,1,14,1,0,1),(15,1,17,1,1,1),(16,1,18,1,0,1),(17,1,19,1,0,1),(18,1,20,1,0,1),(19,1,21,1,0,1),(20,1,22,1,0,1),(21,1,23,1,0,1),(24,1,1,2,1,1),(25,1,2,2,1,1),(26,1,1,4,1,1),(27,1,2,4,1,1),(28,1,3,4,0,1),(29,1,4,4,1,1),(30,2,1,1,1,1),(31,2,2,1,0,1),(32,2,3,1,0,1),(33,2,4,1,1,1),(34,2,5,1,1,1),(35,2,6,1,1,1),(36,2,1,2,1,1),(37,2,2,2,1,1),(38,2,3,2,1,1),(39,2,4,2,1,1),(40,2,1,3,0,1),(41,2,2,3,0,1),(42,2,3,3,0,1),(43,2,4,3,1,1),(44,2,5,3,1,1),(45,2,6,3,1,1),(46,2,7,3,1,1),(47,2,1,4,1,1),(48,2,2,4,1,1),(49,2,3,4,0,1),(50,2,4,4,0,1),(51,1,4,5,1,1),(52,1,9,5,1,1),(53,1,1,8,1,1),(54,1,2,8,1,1),(55,1,3,8,1,1),(56,1,4,8,0,1),(57,1,5,8,0,1),(58,1,6,8,1,1),(59,3,1,1,1,1),(60,3,2,1,1,1),(61,3,3,1,0,1),(62,3,4,1,1,1),(63,3,5,1,1,1),(64,3,6,1,0,1);
/*!40000 ALTER TABLE `capacidades_areas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresas`
--

DROP TABLE IF EXISTS `empresas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cnpj` varchar(25) DEFAULT NULL,
  `numportaria` int(11) NOT NULL,
  `datadou` datetime DEFAULT NULL,
  `reucmid` int(11) NOT NULL,
  `classificacao` varchar(5) DEFAULT NULL,
  `hiperlink` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `numportaria_index` (`numportaria`),
  KEY `reucmid_index` (`reucmid`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresas`
--

LOCK TABLES `empresas` WRITE;
/*!40000 ALTER TABLE `empresas` DISABLE KEYS */;
INSERT INTO `empresas` VALUES (1,'AEL SISTEMAS','88.031.539/0001-59',2056,'2014-08-15 00:00:00',10,'ED','DOU'),(2,'AEQ','03.535.330/0002-50',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(3,'AGDS','11.218.949/0001-89',1346,'2014-05-28 00:00:00',9,'EED','DOU'),(4,'AGRALE','88.610.324/0001-92',1346,'2014-05-28 00:00:00',9,'EED','DOU'),(5,'AKAER','65.047.250/0001-22',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(6,'AMAZUL','18.910.028/0001-21',1635,'2014-06-27 00:00:00',10,'EED','DOU'),(7,'AMS','10.834.525/0001-86',1346,'2014-05-28 00:00:00',9,'EED','DOU'),(8,'ARES','33.966.391/0001-52',250,'2015-01-30 00:00:00',14,'ED','DOU'),(9,'ARMTEC','06.941.284/0001-05',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(10,'ATECH','11.262.624/0001-01',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(11,'ATMOS','06.109.916/0001-50',1346,'2014-05-28 00:00:00',9,'EED','DOU'),(12,'AVIBRÁS - Divisão Aérea e Naval S.A.','00.435.091/0001-98',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(13,'AVIBRÁS - Indústria Aeroespacial S.A.','60.181.468/0005-85',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(14,'AVIO DO BRASIL','11.267.488/0001-34',2056,'2014-08-15 00:00:00',10,'ED','DOU'),(15,'AVIONICS SERVICES','01.137.391/0001-53',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(16,'AXUR','10.318.969/0001-69',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(17,'BCA','03.452.655/0001-99',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(18,'BLUEPEX SECURITY SOLUTIONS','02.227.843/0001-50',2638,'2014-10-08 00:00:00',12,'EED','DOU'),(19,'BOMBAS TRIGLAU','83.082.867/0001-60',1635,'2014-06-27 00:00:00',10,'EED','DOU'),(20,'BRADAR','02.807.737/0001-46',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(21,'CBC','57.494.031/0001-63',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(22,'CODECIPHERS','09.375.422/0001-16',1116,'2015-05-19 00:00:00',15,'ED','DOU'),(23,'COLUMBUS','69.122.240/0001-65',1360,'2015-06-18 00:00:00',16,'ED','DOU'),(24,'CONDOR','30.092.431/0001-96',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(25,'DATA COM','02.820.966/0001-09',1360,'2015-06-18 00:00:00',16,'ED','DOU'),(26,'DÍGITRO','83.472.803/0001-76',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(27,'DSG DEFENCE','03.222.543/0001-41',1116,'2014-05-19 00:00:00',15,'ED','DOU'),(28,'EMBRAER','07.689.002/0001-89',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(29,'EMGEPRON','27.816.487/0001-31',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(30,'ENGEVIX','00.103.582/0001-31',1635,'2014-06-27 00:00:00',10,'EED','DOU'),(31,'EQUIPAER','55.366.991/0001-12',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(32,'FT','07.498.381/0001-20',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(33,'FUNDAÇÃO CPqD','02.641.663/0001-10',249,'2015-01-30 00:00:00',11,'EED','DOU'),(34,'FUNDAÇÃO EZUTE','01.710.917/0001-42',2028,'2014-08-13 00:00:00',10,'EED','DOU'),(35,'GEOCONTROL','04.967.131/0001-01',2028,'2014-08-13 00:00:00',11,'EED','DOU'),(36,'GESPI AERONÁUTICA','45.218.484/0001-88',249,'2015-01-30 00:00:00',14,'EED','DOU'),(37,'GLAGIO','66.260.415/0001-02',249,'2015-01-30 00:00:00',14,'EED','DOU'),(38,'HARPIA SISTEMAS','14.926.698/0002-66',2638,'2014-10-08 00:00:00',12,'EED','DOU'),(39,'HERSA','01.376.473/0001-50',2638,'2014-10-08 00:00:00',12,'EED','DOU'),(40,'IACIT','56.035.876/0001-28',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(41,'IAS','05.116.872/0001-33',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(42,'IMBEL','00.444.232/0001-39',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(43,'INBRA','12.887.936/0001-65',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(44,'INDIOS','00.784.848/0001-59',2638,'2014-10-08 00:00:00',12,'EED','DOU'),(45,'KRYPTUS','05.761.098/0001-13',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(46,'MECTRON','65.481.012/0001-20',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(47,'MIRABILIS','14.371.565/0001-90',2028,'2014-08-13 00:00:00',11,'EED','DOU'),(48,'MÓDULO','28.712.123/0001-74',1346,'2014-05-28 00:00:00',9,'EED','DOU'),(49,'NG METALÚRGICA','01.939.979/0001-20',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(50,'NITROQUÍMICA','61.150.348/0001-50',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(51,'NOVAER CRAFT','02.447.516/0001-04',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(52,'NUCLEP','42.515.882/0003-30',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(53,'OAS DEFESA','15.806.518/0001-94',1346,'2014-05-28 00:00:00',9,'EED','DOU'),(54,'ODEBRECHT','13.688.755/0001-72',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(55,'OMNYSYS','01.773.463/0001-59',2638,'2014-10-08 00:00:00',12,'ED','DOU'),(56,'OPTO ELETRÔNICA','54.253.661/0001-58',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(57,'ORBITAL','04.318.188/0001-71',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(58,'QUEIROZ GALVÃO','13.259.186/0001-40',1346,'2014-05-28 00:00:00',9,'EED','DOU'),(59,'RADIX','11.677.441/0001-49',1346,'2014-05-28 00:00:00',9,'EED','DOU'),(60,'RF COM','00.259.055/0001-10',1346,'2014-05-28 00:00:00',9,'EED','DOU'),(61,'ROCKWELL COLLINS DO BRASIL','02.048.100/0001-13',2056,'2014-08-15 00:00:00',10,'ED','DOU'),(62,'RUSTCON','17.516.926/0001-37',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(63,'SAIPHER ATC','00.628.447/0001-00',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(64,'SANTOS LAB','08.884.097/0001-54',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(65,'SAVIS','15.675.599/0001-30',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(66,'SKM','00.064.702/0001-39',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(67,'SPECTRA','59.933.705/0001-04',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(68,'STEFANINI','58.069.360/0001-20',1116,'2014-05-19 00:00:00',15,'ED','DOU'),(69,'SYNERGY','10.977.298/0001-48',1635,'2014-06-27 00:00:00',10,'EED','DOU'),(70,'TAURUS','92.781.335/0001-02',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(71,'VERTICAL DO PONTO','36.111.755/0001-00',1346,'2014-05-28 00:00:00',6,'EED','DOU'),(72,'VISIONA','13.955.554/0001-99',1346,'2014-05-28 00:00:00',8,'EED','DOU'),(73,'Z TECNOLOGIA','37.112.752/0001-54',2638,'2014-10-08 00:00:00',12,'EED','DOU');
/*!40000 ALTER TABLE `empresas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (1,'ADMIN'),(2,'RESP'),(3,'GEST');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projetos`
--

DROP TABLE IF EXISTS `projetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projetos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `status_index` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projetos`
--

LOCK TABLES `projetos` WRITE;
/*!40000 ALTER TABLE `projetos` DISABLE KEYS */;
INSERT INTO `projetos` VALUES (1,'Simulador Armas Leves - Módulo Fuzil',1),(2,'Reparo de Metralhadora Automatizado X (REMAX)',1),(3,'Torre Operada Remotamente e Estabilizada para Canhão 30 mm (TORC30)',1),(4,'Morteiro Leve 60 mm.',1),(5,'Viatura Leve de Emprego Geral Aerotransportável (VLEGA) – GAÚCHO',1),(6,'Viatura Leve de Emprego Geral Aerotransportável (VLEGA) – CHIVUNK',1),(7,'Míssil Superfície-Superfície 1.2 Anticarro (MSS 1.2 AC)',1),(8,'Monóculo de Imagem Térmica OLHAR VDN-X1',1),(9,'Radar de Vigilância Aérea de Longo Alcance SABER M200',1),(10,'Radar de Vigilância Terrestre SENTIR M20',1),(11,'Rádio Definido por Software (RDS) – Versão Veicular',1),(12,'Rádio definido por Software (RDS) - versões Manpack (3º ciclo)',0),(13,'Rádio definido por Software (RDS) - versões Handheld (2º ciclo)',0),(14,'Míssil AV-TM 300',1),(15,'Foguete Guiado AV-SS-40G',1),(16,'Radar SABER M200 VIGILANTE',1),(17,'Sistema de Aeronaves Remotamente Pilotadas Categoria 0 – Asas Rotativas (SARP Cat 0 – Asas Rotativas)',1),(18,'Homologar o Simulador para Helicópteros Fennec e Esquilo (SHEFE)',2),(19,'Programa de Sensores Optrônicos – Sistema de Visão multiespectral para motorista de veículos blindados',0),(20,'Sistema de Veículos Terrestres Remotamente Pilotados (SVTRP – Catg 0)',0),(21,'Sistema de Veículos Terrestres Remotamente Pilotados (SVTRP – Catg 1)',0),(22,'Sistema de Veículos Terrestres Remotamente Pilotados (SVTRP – Catg 2)',0),(23,'Implantar  Laboratório Pesquisas e Avaliações Técnicas em Motores a Combustão Interna (P&D em Motores a diesel e ciclo Otto – acionamentos automotivo e estacionário)',3),(24,'Pesquisar e Desenvolver Tecnologia Integradora para Sistema de Visão Assistida Multi-Espectral (SVAM)',3),(25,'Desenvolver Tecnologia para as Pilhas Térmicas Híbridas e de 3a Geração',3),(26,'Otimizar o emprego de propelentes de base dupla para a propulsão de vetores balísticos (MATEST)',3),(27,'Desenvolver Materiais Especiais de Carbono',3),(28,'Desenvolver Materiais Compósitos para Uso em Veículos Militares',3),(29,'Sistema de Aeronaves Remotamente Pilotadas Categoria 0 – Asas Fixas (SARP Cat 0 – Asas Fixas)',4),(30,'Pesquisar e desenvolver uma plataforma de Stewart com 6 graus de liberdade',4),(31,'Simulador Armas Leves - Módulo Pistola',4),(32,'Arma Leve Anticarro (ALAC)',4),(33,'Morteiro Médio 81 mm',4),(34,'Munição AE para o Morteiro Médio 81 mm',4),(35,'Munição Fumígena para o Morteiro Médio 81 mm',4),(36,'Munição Iluminativa para o Morteiro Médio 81 mm',4),(37,'Munição AE para o Morteiro Leve 60 mm',4),(38,'Munição Fumígena para o Morteiro Leve 60 mm',4),(39,'Munição Iluminativa para o Morteiro Leve 60 mm',4),(40,'Munição 120 mm AE PRPA',4),(41,'Blindagens balísticas leves para os helicópteros da Aviação do Exército',4),(42,'Vtr Reboque 1,5 Ton Especializada de Engenharia',4),(43,'Viatura Especial de Patrulhamento versão 2 (VEsPa 2)',4),(44,'Viatura Blindada de Patrulhamento de Emprego Dual (VBPED)',4),(45,'Pesquisar e desenvolver biocombustíveis destinados à geração de energia elétrica (Projeto Biodiesel)',4),(46,'Implantar o Laboratório de Automação Veicular e de Equipamentos (LAVE)',4),(47,'Módulo de Telemática de Bda e DE do Sistema C2 em Combate (Obs: corresponde à expansão do MTO para Bda e DE)',4),(48,'Implantar infraestrutura laboratorial de Defesa Química, Biológica, Radiológica e Nuclear',4),(49,'Implantar o Laboratório de Análise Química (LAQ)',4),(50,'Implantar o Laboratório de Identificação de Agentes Químicos (LIAQ)',4),(51,'Implantar o Laboratório de Defesa Biológica (LDB)',4),(52,'MAGE Com Veicular',4),(53,'Medidas de Apoio à Guerra Eletrônica Estratégico (AURORA)',4),(54,'Unidade Autônoma de Guiamento e Controle (UAGC) para Míssil de Defesa Antiaérea',4),(55,'Veículo Aéreo Não Tripulado com alcance de utilização de 15 km (VANT-VT 15)',4),(56,'Desenvolvimento de Sistema de Pouso e Decolagem Automático do VANT MD',4),(57,'Sistema de Navegação, Controle e Guiamento Automático Através de Câmera Giroestabilizada e Georreferenciada para Veículos Aéreos Não Tripulados',4),(58,'Monóculo de Visão Noturna MOVIN X1',4),(59,'Óculos de Visão Noturna',4),(60,'Periscópio de Visão Noturna',4),(61,'Luneta de Visão Termal',4),(62,'Materiais Resistentes a Impactos Balísticos (MARIMBA)',4),(63,'Implantar um Centro de Excelência em Canhoneio nas Instalações do CTEx',4),(64,'Radar de Defesa Antiaérea de Baixa Altura SABER M60',4),(65,'Centro de Operações Antiaéreas Eletrônico de Seção (COAAe Elt Seç)',4);
/*!40000 ALTER TABLE `projetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCompleto` varchar(255) DEFAULT NULL,
  `nomeUsuario` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `PERFIL_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_usuarios_PERFIL_id` (`PERFIL_id`),
  CONSTRAINT `FK_usuarios_PERFIL_id` FOREIGN KEY (`PERFIL_id`) REFERENCES `perfil` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Ralfh','aralfh','37706e7432144deb5dfdbbb3e48f8bcb7474c0a2380eb6ab0e47813aa483f98f',1),(2,'Fulano','fulano','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',2),(3,'Cicrano','cicrano','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',3);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-09 15:50:03
