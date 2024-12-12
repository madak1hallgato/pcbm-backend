
INSERT INTO categories (name) VALUES ('CPU');
INSERT INTO categories (name) VALUES ('GPU');
INSERT INTO categories (name) VALUES ('RAM');

INSERT INTO components (name, price, category_id)
SELECT 'Intel Core i9-13900K', 599.99, id FROM categories WHERE name = 'CPU';
INSERT INTO components (name, price, category_id)
SELECT 'AMD Ryzen 9 7900X', 449.99, id FROM categories WHERE name = 'CPU';
INSERT INTO components (name, price, category_id)
SELECT 'Intel Core i7-13700K', 409.99, id FROM categories WHERE name = 'CPU';
INSERT INTO components (name, price, category_id)
SELECT 'AMD Ryzen 7 7700X', 349.99, id FROM categories WHERE name = 'CPU';
INSERT INTO components (name, price, category_id)
SELECT 'Intel Core i5-13600K', 319.99, id FROM categories WHERE name = 'CPU';
INSERT INTO components (name, price, category_id)
SELECT 'AMD Ryzen 5 7600X', 279.99, id FROM categories WHERE name = 'CPU';
INSERT INTO components (name, price, category_id)
SELECT 'Intel Core i9-12900K', 599.99, id FROM categories WHERE name = 'CPU';

INSERT INTO components (name, price, category_id)
SELECT 'NVIDIA RTX 4090', 1599.99, id FROM categories WHERE name = 'GPU';
INSERT INTO components (name, price, category_id)
SELECT 'AMD Radeon RX 7900 XTX', 999.99, id FROM categories WHERE name = 'GPU';
INSERT INTO components (name, price, category_id)
SELECT 'NVIDIA RTX 3080', 799.99, id FROM categories WHERE name = 'GPU';
INSERT INTO components (name, price, category_id)
SELECT 'NVIDIA RTX 3070', 499.99, id FROM categories WHERE name = 'GPU';
INSERT INTO components (name, price, category_id)
SELECT 'AMD Radeon RX 6800 XT', 699.99, id FROM categories WHERE name = 'GPU';
INSERT INTO components (name, price, category_id)
SELECT 'NVIDIA RTX 3060 Ti', 399.99, id FROM categories WHERE name = 'GPU';
INSERT INTO components (name, price, category_id)
SELECT 'AMD Radeon RX 5700 XT', 349.99, id FROM categories WHERE name = 'GPU';

INSERT INTO components (name, price, category_id)
SELECT 'Corsair Vengeance DDR5 32GB', 199.99, id FROM categories WHERE name = 'RAM';
INSERT INTO components (name, price, category_id)
SELECT 'G.Skill Trident Z RGB 16GB', 99.99, id FROM categories WHERE name = 'RAM';
INSERT INTO components (name, price, category_id)
SELECT 'Corsair Vengeance DDR5 64GB', 399.99, id FROM categories WHERE name = 'RAM';
INSERT INTO components (name, price, category_id)
SELECT 'G.Skill Trident Z RGB 32GB', 169.99, id FROM categories WHERE name = 'RAM';
INSERT INTO components (name, price, category_id)
SELECT 'Corsair Dominator Platinum 16GB', 159.99, id FROM categories WHERE name = 'RAM';
INSERT INTO components (name, price, category_id)
SELECT 'Kingston Fury Beast 32GB', 129.99, id FROM categories WHERE name = 'RAM';
INSERT INTO components (name, price, category_id)
SELECT 'Crucial Ballistix 8GB', 49.99, id FROM categories WHERE name = 'RAM';
INSERT INTO components (name, price, category_id)
SELECT 'Corsair Vengeance LPX 16GB', 79.99, id FROM categories WHERE name = 'RAM';
INSERT INTO components (name, price, category_id)
SELECT 'G.Skill Ripjaws V 16GB', 74.99, id FROM categories WHERE name = 'RAM';
INSERT INTO components (name, price, category_id)
SELECT 'HyperX Fury 8GB', 45.99, id FROM categories WHERE name = 'RAM';
INSERT INTO components (name, price, category_id)
SELECT 'Teamgroup T-Force Delta RGB 16GB', 99.99, id FROM categories WHERE name = 'RAM';

INSERT INTO "users" (username, password, role) VALUES ('test_admin', 'admin', 'ADMIN');
INSERT INTO "users" (username, password, role) VALUES ('test_user_1', 'asd', 'USER');
INSERT INTO "users" (username, password, role) VALUES ('test_user_2', 'asd', 'USER');

INSERT INTO "builds" (name, user_id)
SELECT 'High-End Gaming PC', id FROM "users" WHERE username = 'test_user_1';
INSERT INTO "builds" (name, user_id)
SELECT 'Budget Workstation', id FROM "users" WHERE username = 'test_user_2';
INSERT INTO builds (name, user_id)
SELECT 'Ultimate Gaming PC', id FROM "users" WHERE username = 'test_admin';
INSERT INTO builds (name, user_id)
SELECT 'Workstation PC', id FROM "users" WHERE username = 'test_admin';
INSERT INTO builds (name, user_id)
SELECT 'Budget Gaming Build', id FROM "users" WHERE username = 'test_admin';
INSERT INTO builds (name, user_id)
SELECT 'Mid-Range Office PC', id FROM "users" WHERE username = 'test_admin';

INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'High-End Gaming PC' AND c.name = 'Intel Core i9-13900K';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'High-End Gaming PC' AND c.name = 'NVIDIA RTX 4090';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'High-End Gaming PC' AND c.name = 'Corsair Vengeance DDR5 32GB';

INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Budget Workstation' AND c.name = 'AMD Ryzen 9 7900X';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Budget Workstation' AND c.name = 'AMD Radeon RX 7900 XTX';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Budget Workstation' AND c.name = 'G.Skill Trident Z RGB 16GB';

INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Ultimate Gaming PC' AND c.name = 'Intel Core i9-13900K';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Ultimate Gaming PC' AND c.name = 'NVIDIA RTX 4090';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Ultimate Gaming PC' AND c.name = 'Corsair Vengeance DDR5 64GB';

INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Workstation PC' AND c.name = 'AMD Ryzen 7 7700X';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Workstation PC' AND c.name = 'AMD Radeon RX 6800 XT';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Workstation PC' AND c.name = 'G.Skill Trident Z RGB 32GB';

INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Budget Gaming Build' AND c.name = 'AMD Ryzen 5 7600X';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Budget Gaming Build' AND c.name = 'NVIDIA RTX 3060 Ti';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Budget Gaming Build' AND c.name = 'Corsair Vengeance LPX 16GB';

INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Mid-Range Office PC' AND c.name = 'Intel Core i5-13600K';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Mid-Range Office PC' AND c.name = 'NVIDIA RTX 3070';
INSERT INTO builds_components (build_id, component_id)
SELECT b.id, c.id FROM builds b, components c WHERE b.name = 'Mid-Range Office PC' AND c.name = 'Kingston Fury Beast 32GB';