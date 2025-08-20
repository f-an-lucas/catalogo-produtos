INSERT INTO categories (name, name_normalized) VALUES
    ('Eletrônicos', 'eletronicos'),
    ('Livros', 'livros'),
    ('Vestuário', 'vestuario');

INSERT INTO products (name, name_normalized, description, description_normalized, price, stock, category_id, created_at, updated_at) VALUES
    ('Fone Bluetooth', 'fone bluetooth', 'Fone sem fio com cancelamento de ruído', 'fone sem fio com cancelamento de ruido', 299.90, 50, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Smartphone X200', 'smartphone x200', 'Celular com tela AMOLED de 6.5 polegadas', 'celular com tela amoled de 6.5 polegadas', 2499.00, 30, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Notebook Ultra', 'notebook ultra', 'Notebook leve e rápido com SSD de 512GB', 'notebook leve e rapido com ssd de 512gb', 4599.90, 15, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Caixa de Som Portátil', 'caixa de som portatil', 'Caixa de som Bluetooth resistente à água', 'caixa de som bluetooth resistente a agua', 399.00, 80, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Monitor 27'' 4K', 'monitor 27'' 4k', 'Monitor UHD com cores vivas e HDR', 'monitor uhd com cores vivas e hdr', 1899.00, 20, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Smartwatch Fit', 'smartwatch fit', 'Relógio inteligente com monitor de batimentos', 'relogio inteligente com monitor de batimentos', 899.00, 40, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Câmera Digital Pro', 'camera digital pro', 'Câmera DSLR com lente 18-55mm', 'camera dslr com lente 18-55mm', 3500.00, 10, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Teclado Mecânico Gamer', 'teclado mecanico gamer', 'Teclado com switches azuis e iluminação RGB', 'teclado com switches azuis e iluminacao rgb', 599.90, 60, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Mouse Sem Fio', 'mouse sem fio', 'Mouse ergonômico com sensor de alta precisão', 'mouse ergonomico com sensor de alta precisao', 199.00, 100, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Carregador Rápido 65W', 'carregador rapido 65w', 'Carregador com suporte a carregamento turbo', 'carregador com suporte a carregamento turbo', 149.00, 120, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('Dom Casmurro', 'dom casmurro', 'Clássico da literatura brasileira de Machado de Assis', 'clássico da literatura brasileira de machado de assis', 39.90, 70, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('O Senhor dos Anéis', 'o senhor dos aneis', 'Trilogia completa em edição de luxo', 'trilogia completa em edicao de luxo', 199.00, 25, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('1984', '1984', 'Obra distópica de George Orwell', 'obra distopica de george orwell', 49.90, 80, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('A Revolução dos Bichos', 'a revolucao dos bichos', 'Sátira política de George Orwell', 'satira politica de george orwell', 34.90, 90, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Harry Potter e a Pedra Filosofal', 'harry potter e a pedra filosofal', 'Primeiro livro da saga Harry Potter', 'primeiro livro da saga harry potter', 59.90, 60, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('O Pequeno Príncipe', 'o pequeno principe', 'Clássico da literatura mundial', 'classico da literatura mundial', 29.90, 100, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Orgulho e Preconceito', 'orgulho e preconceito', 'Romance clássico de Jane Austen', 'romance classico de jane austen', 45.00, 40, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Mitologia Nórdica', 'mitologia nordica', 'Coletânea de lendas e mitos vikings', 'coletanea de lendas e mitos vikings', 69.90, 35, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Sapiens', 'sapiens', 'História da humanidade por Yuval Noah Harari', 'historia da humanidade por yuval noah harari', 89.90, 50, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('A Arte da Guerra', 'a arte da guerra', 'Clássico de estratégia militar de Sun Tzu', 'classico de estrategia militar de sun tzu', 39.90, 65, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('Camiseta Básica', 'camiseta basica', 'Camiseta de algodão confortável', 'camiseta de algodao confortavel', 59.90, 200, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Calça Jeans Slim', 'calca jeans slim', 'Calça jeans com caimento moderno', 'calca jeans com caimento moderno', 149.00, 120, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Jaqueta de Couro', 'jaqueta de couro', 'Jaqueta estilosa de couro sintético', 'jaqueta estilosa de couro sintetico', 399.00, 35, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Vestido Floral', 'vestido floral', 'Vestido leve e confortável para o verão', 'vestido leve e confortavel para o verao', 189.00, 70, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Tênis Esportivo', 'tenis esportivo', 'Tênis leve para corrida e caminhada', 'tenis leve para corrida e caminhada', 299.00, 90, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Moletom com Capuz', 'moletom com capuz', 'Moletom unissex com bolso frontal', 'moletom unissex com bolso frontal', 159.00, 110, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Shorts Jeans', 'short jeans', 'Shorts jeans feminino estilo moderno', 'shorts jeans feminino estilo moderno', 99.00, 85, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Camisa Social', 'camisa social', 'Camisa de algodão manga longa', 'camisa de algodao manga longa', 139.00, 75, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Blusa de Lã','blusa de la', 'Blusa quente para inverno', 'blusa quente para inverno', 179.00, 55, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Saia Midi', 'saia midi', 'Saia feminina elegante até o joelho', 'Saia feminina elegante ate o joelho', 129.00, 65, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
