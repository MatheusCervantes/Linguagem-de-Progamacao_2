CREATE TABLE Pessoa (
    idPessoa INTEGER PRIMARY KEY AUTOINCREMENT,
    nomePessoa CHAR(255), 
    cpfPessoa CHAR(15),  
    enderecoPessoa CHAR(255), 
    cidadePessoa CHAR(255),   
    estadoPessoa CHAR(2),     
    numeroPessoa CHAR(5),
    telefonePessoa CHAR(20)   
);

CREATE TABLE Medico (
    idMedico INTEGER PRIMARY KEY AUTOINCREMENT,
    nomeMedico CHAR(255), 
    cpfMedico CHAR(15),  
    enderecoMedico CHAR(255), 
    cidadeMedico CHAR(255),   
    estadoMedico CHAR(2),     
    numeroMedico CHAR(5),
    telefoneMedico CHAR(20),
    crmMedico CHAR(15)
);

CREATE TABLE Login (
    idLogin INTEGER PRIMARY KEY AUTOINCREMENT,
    usuarioLogin CHAR(100) NOT NULL UNIQUE, 
    senhaLogin CHAR(100) NOT NULL,
    nomeLogin CHAR(255),
    cpfLogin CHAR(15),  
    tipousuarioLogin CHAR(20), 
    dtanascLogin CHAR(20)     
);

CREATE TABLE Consulta (
    idConsulta INTEGER PRIMARY KEY AUTOINCREMENT,
    dataConsulta CHAR(10),
    horaConsulta CHAR(8),
    descricaoConsulta CHAR(400),
    idMedico INTEGER,
    idPessoa INTEGER,
    CONSTRAINT idPessoa_FK FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa),
    CONSTRAINT idMedico_FK FOREIGN KEY (idMedico) REFERENCES Medico(idMedico)
);