-- comentarios
-- a linha abaixo cria banco de dados
create database dbinfox2;
-- a linha abaixo escolhe o banco de dados
use dbinfox2;
-- o bloco de instruções abaixo cria uma tabela
create table tbusuarios2(
iduser int primary key,
usuario varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null
);
-- o comando abaixo descreve a tabela
describe tbusuarios2;
-- a linha abaixo insere dados na tabela (CRUD)
-- create -> insert
insert into tbusuarios2(iduser, usuario, fone, login, senha)
values(1, 'José de Assis', '9999-9999', 'joseassis', '123456');
-- a linha abaixo exibe os dados da tabela (CRUD)
-- read -> select
select * from tbusuarios2;

insert into tbusuarios2(iduser, usuario, fone, login, senha)
values(2, 'Administrador', '9999-9999', 'admin', 'admin');
insert into tbusuarios2(iduser, usuario, fone, login, senha)
values(3, 'Françueudes', '9999-9999', 'francueudes', 'senha');

-- a linha abaixo modifica dados na tabela (CRUD)
-- update -> update
update tbusuarios2 set fone='8888-8888' where iduser=2;

-- a linha abaixo apaga um registro da tabela (CRUD)
-- delete -> delete
delete from tbusuarios2 where iduser=3;
select * from tbusuarios2;

create table tbclientes(
idcli int primary key auto_increment,
nomecli varchar(50) not null,
endcli varchar(100),
fonecli varchar(50) not null,
emailcli varchar(50)
);

describe tbclientes;

insert into tbclientes(nomecli, endcli, fonecli, emailcli)
values('Raquel Sousa', 'Rua Domingos', '2222-2222', 'raquel@gmail.com');

select * from tbclientes;

create table tbos(
os int primary key auto_increment,
data_os timestamp default current_timestamp,
equioamenti varchar(150) not null,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar(50),
valor decimal(10,2),
idcli int not null,
foreign key(idcli) references tbclientes(idcli)
);

describe tbos;

insert into tbos(equioamenti, defeito, servico, tecnico, valor, idcli)
values('notebook', 'nao liga', 'troca da fonte', 'zé', 87.50, 1);

select * from tbos;

-- o codigo abaixo traz informações de duas tabelas
select
O.os,equioamenti,defeito,servico,valor,
C.nomecli,fonecli
from tbos as O
inner join tbclientes as C
on (O.idcli = C.idcli);