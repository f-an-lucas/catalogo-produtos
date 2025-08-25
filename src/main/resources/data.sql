-- ============================
-- CATEGORIES
-- ============================
INSERT INTO categories (id, name, name_normalized) VALUES
    (1, 'Bebidas', 'bebidas'),
    (2, 'Alimentos', 'alimentos'),
    (3, 'Higiene', 'higiene'),
    (4, 'Limpeza', 'limpeza'),
    (5, 'Laticínios', 'laticinios'),
    (6, 'Eletrônicos', 'eletronicos');

-- ============================
-- PRODUCTS
-- ============================

-- Bebidas
INSERT INTO products (name, name_normalized, description, description_normalized, price, ean, category_id, created_at, updated_at) VALUES
    ('Coca-Cola Lata 350ml', 'coca-cola lata 350ml', 'Refrigerante de cola', 'refrigerante de cola', 4.50, '7894900010015', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Guaraná Antarctica 2L', 'guarana antarctica 2l', 'Refrigerante guaraná', 'refrigerante guarana', 9.90, '7891991000833', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Fanta Laranja 2L', 'fanta laranja 2l', 'Refrigerante sabor laranja', 'refrigerante sabor laranja', 8.90, '7894900011517', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Água Mineral Crystal 500ml', 'agua mineral crystal 500ml', 'Água mineral sem gás', 'agua mineral sem gas', 2.50, '7894900700015', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Heineken Long Neck 330ml', 'heineken long neck 330ml', 'Cerveja Pilsen Premium', 'cerveja pilsen premium', 6.90, '7891149101045', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Skol Pilsen Lata 350ml', 'skol pilsen lata 350ml', 'Cerveja Pilsen', 'cerveja pilsen', 3.99, '7891149100017', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Vinho Miolo Reserva 750ml', 'vinho miolo reserva 750ml', 'Vinho tinto seco', 'vinho tinto seco', 54.90, '7896036090908', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Cerveja Budweiser Lata 350ml', 'cerveja budweiser lata 350ml', 'Cerveja American Lager', 'cerveja american lager', 4.99, '7891991010856', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Alimentos
INSERT INTO products (name, name_normalized, description, description_normalized, price, ean, category_id, created_at, updated_at) VALUES
    ('Arroz Tio João Tipo 1 5kg', 'arroz tio joao tipo 1 5kg', 'Arroz branco longo fino', 'arroz branco longo fino', 24.90, '7896006750010', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Feijão Carioca Camil 1kg', 'feijao carioca camil 1kg', 'Feijão tipo 1', 'feijao tipo 1', 8.50, '7896003701763', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Macarrão Renata Espaguete 500g', 'macarrao renata espaguete 500g', 'Massa de trigo sêmola', 'massa de trigo semola', 5.20, '7896003700018', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Óleo de Soja Soya 900ml', 'oleo de soja soya 900ml', 'Óleo refinado de soja', 'oleo refinado de soja', 7.30, '7896036090113', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Açúcar União Refinado 1kg', 'acucar uniao refinado 1kg', 'Açúcar branco refinado', 'acucar branco refinado', 4.50, '7891910000197', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Café Pilão 500g', 'cafe pilao 500g', 'Café torrado e moído', 'cafe torrado e moido', 15.90, '7891000060305', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Molho de Tomate Heinz 340g', 'molho de tomate heinz 340g', 'Molho de tomate tradicional', 'molho de tomate tradicional', 6.20, '7891991010061', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Chocolate Lacta Ao Leite 90g', 'chocolate lacta ao leite 90g', 'Chocolate ao leite tradicional', 'chocolate ao leite tradicional', 6.20, '7896004000019', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Biscoito Oreo 90g', 'biscoito oreo 90g', 'Biscoito recheado de chocolate', 'biscoito recheado de chocolate', 4.99, '7622210449283', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Batata Palha Elma Chips 105g', 'batata palha elma chips 105g', 'Batata palha crocante', 'batata palha crocante', 8.40, '7892840220013', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Higiene
INSERT INTO products (name, name_normalized, description, description_normalized, price, ean, category_id, created_at, updated_at) VALUES
    ('Colgate Total 12 90g', 'colgate total 12 90g', 'Creme dental proteção completa', 'creme dental protecao completa', 9.80, '7891024120017', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Shampoo Seda 325ml', 'shampoo seda 325ml', 'Shampoo hidratação', 'shampoo hidratacao', 12.90, '7891150022901', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Sabonete Dove 90g', 'sabonete dove 90g', 'Sabonete hidratante', 'sabonete hidratante', 4.50, '7891150021201', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Desodorante Rexona Aerosol 150ml', 'desodorante rexona aerosol 150ml', 'Proteção antitranspirante', 'protecao antitranspirante', 15.90, '7891150021577', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Fralda Pampers Confort Sec XG c/ 20', 'fralda pampers confort sec xg c 20', 'Fraldas descartáveis infantis', 'fraldas descartaveis infantis', 45.90, '7506339393552', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Absorvente Always Noturno c/ 8', 'absorvente always noturno c 8', 'Absorvente com abas', 'absorvente com abas', 8.90, '4015400706019', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Escova de Dentes Oral-B Indicator', 'escova de dentes oral-b indicator', 'Escova dental macia', 'escova dental macia', 6.50, '3014260831026', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Creme de Barbear Bozzano 65g', 'creme de barbear bozzano 65g', 'Espuma de barbear', 'espuma de barbear', 7.90, '7891035600018', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Limpeza
INSERT INTO products (name, name_normalized, description, description_normalized, price, ean, category_id, created_at, updated_at) VALUES
    ('Detergente Ypê 500ml', 'detergente ype 500ml', 'Detergente líquido neutro', 'detergente liquido neutro', 3.20, '7896098900019', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Sabão em Pó Omo 800g', 'sabao em po omo 800g', 'Sabão em pó multiação', 'sabao em po multiacao', 12.90, '7891150020587', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Amaciante Comfort 1L', 'amaciante comfort 1l', 'Amaciante concentrado', 'amaciante concentrado', 9.50, '7891150020990', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Desinfetante Pinho Sol 500ml', 'desinfetante pinho sol 500ml', 'Desinfetante multiuso', 'desinfetante multiuso', 7.80, '7891024130016', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Esponja de Aço Bombril 60g', 'esponja de aço bombril 60g', 'Esponja de aço multiuso', 'esponja de aço multiuso', 2.99, '7896060000013', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Limpador Veja Multiuso 500ml', 'limpador veja multiuso 500ml', 'Limpador multiuso tradicional', 'limpador multiuso tradicional', 6.40, '7891150021973', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Água Sanitária Qboa 1L', 'agua sanitária qboa 1l', 'Água sanitária tradicional', 'agua sanitaria tradicional', 4.20, '7896098900026', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Inseticida Raid Aerosol 420ml', 'inseticida raid aerosol 420ml', 'Inseticida multi-insetos', 'inseticida multi-insetos', 17.90, '7891150021881', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Laticínios
INSERT INTO products (name, name_normalized, description, description_normalized, price, ean, category_id, created_at, updated_at) VALUES
    ('Leite Integral Italac 1L', 'leite integral italac 1l', 'Leite UHT integral', 'leite uht integral', 4.80, '7898080640010', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Queijo Mussarela Sadia 500g', 'queijo mussarela sadia 500g', 'Queijo fatiado', 'queijo fatiado', 28.90, '7891000060077', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Iogurte Nestlé Natural 170g', 'iogurte nestle natural 170g', 'Iogurte natural integral', 'iogurte natural integral', 3.50, '7891000060138', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Manteiga Aviação 200g', 'manteiga aviacao 200g', 'Manteiga tradicional', 'manteiga tradicional', 15.90, '7896036090120', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Requeijão Cremoso Danone 200g', 'requeijao cremoso danone 200g', 'Requeijão cremoso', 'requeijao cremoso', 7.50, '7891024131006', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Leite Condensado Moça 395g', 'leite condensado moca 395g', 'Leite condensado tradicional', 'leite condensado tradicional', 6.90, '7891000060022', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Creme de Leite Nestlé 200g', 'creme de leite nestle 200g', 'Creme de leite tradicional', 'creme de leite tradicional', 4.90, '7891000060039', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Iogurte Activia Morango 170g', 'iogurte activia morango 170g', 'Iogurte probiótico', 'iogurte probiótico', 4.20, '7891024132005', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Eletrônicos
INSERT INTO products (name, name_normalized, description, description_normalized, price, ean, category_id, created_at, updated_at) VALUES
    ('Smartphone Samsung Galaxy A15 128GB', 'smartphone samsung galaxy a15 128gb', 'Tela 6.5" AMOLED, 4GB RAM', 'tela 6.5 amoled, 4gb ram', 1299.00, '8806094931230', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Notebook Dell Inspiron 15 8GB 256GB SSD', 'notebook dell inspiron 15 8gb 256gb ssd', 'Intel i5, Windows 11', 'intel i5, windows 11', 3499.00, '8841161234560', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Smart TV LG 50" 4K UHD', 'smart tv lg 50 4k uhd', 'Smart TV 50 polegadas UHD', 'smart tv 50 polegadas uhd', 2299.00, '8806091156789', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Fone de Ouvido JBL Tune 510BT', 'fone de ouvido jbl tune 510bt', 'Fone Bluetooth com microfone', 'fone bluetooth com microfone', 299.00, '6925281982310', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Mouse Logitech M170 Wireless', 'mouse logitech m170 wireless', 'Mouse sem fio USB', 'mouse sem fio usb', 79.90, '0978551234563', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Teclado Mecânico Redragon Kumara', 'teclado mecanico redragon kumara', 'Teclado gamer ABNT2', 'teclado gamer abnt2', 249.00, '6950376712345', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Monitor Samsung 24" Full HD', 'monitor samsung 24 full hd', 'Monitor LED HDMI', 'monitor led hdmi', 799.00, '8806098765432', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Impressora HP DeskJet Ink Advantage 2776', 'impressora hp deskjet ink advantage 2776', 'Multifuncional Wi-Fi', 'multifuncional wifi', 499.00, '1947212345672', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
