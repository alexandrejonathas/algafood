create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
create table cidade (id bigint not null, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null, nome varchar(255), sigla varchar(2), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB
create table pedido (id bigint not null, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status varchar(255), sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), cliente_id bigint not null, endereco_cidade bigint, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null, descricao varchar(255), inativo Boolean default false, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2), cozinha_id bigint not null, endereco_cidade bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table pedido add constraint FKikshlbu2ogysi8rufcv9qbhn1 foreign key (endereco_cidade) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKa3ii9yjt0c00jbq7pjxi59jfm foreign key (endereco_cidade) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco')
insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3)
insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira')
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4)
insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1)
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2)
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3)
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4)
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5)
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6)
