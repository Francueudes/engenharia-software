use engsoft;

create table tbfeedback(
nome varchar (100),
email varchar (100),
opiniao varchar(10000)
);

alter table tbpacient add nasci varchar (10);

select * from tbpacient;

describe tbpacient;

create table tbpacient(
id int primary key auto_increment,
nome varchar (100),
ende varchar (100),
fone varchar (15),
idade varchar (15),
email varchar (50),
sexo varchar (50),
nasci varchar (15)
);

create table tbreferencia(
id int primary key auto_increment,
resumo varchar (1000),
diagnostico varchar(100),
prioridade varchar (100),
pedexame varchar (100),
resulexame varchar (100),
medicamento varchar (100),
alergia varchar (100),
medico varchar (100),
foreign key (id) references tbpacient (id)
);

select * from tbpacient;

select * from tbreferencia;



