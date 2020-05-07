insert into estado values (1, 'Minas Gerais'), (2, 'São Paulo'), (3, 'Pernambuco');

insert into cidade values (1, 'Uberlândia', 1), (2, 'Belo Horizonte', 1), (3, 'São Paulo', 2), (4, 'Campinas', 2), (5, 'Recife', 3); 

insert into cozinha values (1, 'Tailandesa'), (2, 'Indiana'), (3, 'Argentina'), (4, 'Brasileira');

insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Java Steakhouse', 12, 3);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Lanchonete do Tio Sam', 11, 4);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Bar da Maria', 6, 4);

insert into forma_pagamento (id, nome) values (1, 'Dinheiro'), (2, 'Cartão de débito'), (3, 'Cartão de crédito');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3); 

insert into permissao values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'), (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');


insert into produto (id, nome, descricao, preco, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90,  1); 
insert into produto (id, nome, descricao, preco, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110,  1); 
insert into produto (id, nome, descricao, preco, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,  2); 
insert into produto (id, nome, descricao, preco, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21,  3); 
insert into produto (id, nome, descricao, preco, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43,  3); 
insert into produto (id, nome, descricao, preco, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79,  4); 
insert into produto (id, nome, descricao, preco, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,  4); 
insert into produto (id, nome, descricao, preco, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19,  5); 
insert into produto (id, nome, descricao, preco, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8,  6);