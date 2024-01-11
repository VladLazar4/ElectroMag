CREATE DATABASE  IF NOT EXISTS electroMag;
USE electroMag;

CREATE TABLE electroMag.users (
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  name VARCHAR(45) NOT NULL,
  type VARCHAR(45) NOT NULL,
  PRIMARY KEY (username));

CREATE TABLE electroMag.chat (
  id INT NOT NULL AUTO_INCREMENT,
  user1 VARCHAR(45) NULL,
  user2 VARCHAR(45) NULL,
  message VARCHAR(200) NULL,
  PRIMARY KEY (id),
  INDEX user1_idx (user1 ASC) VISIBLE,
  INDEX user2_idx (user2 ASC) VISIBLE,
  CONSTRAINT user1
    FOREIGN KEY (user1)
    REFERENCES electroMag.users (username)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT user2
    FOREIGN KEY (user2)
    REFERENCES electroMag.users (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE electroMag.productsData (
  id INT NOT NULL AUTO_INCREMENT,
  category VARCHAR(45) NOT NULL,
  name VARCHAR(45) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  description VARCHAR(200) NULL,
  stock INT NOT NULL,
  postedBy VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  INDEX username_idx (postedBy ASC) VISIBLE,
  CONSTRAINT username
    FOREIGN KEY (postedBy)
    REFERENCES electroMag.users (username)
    ON DELETE CASCADE
    ON UPDATE CASCADE);



CREATE TABLE electroMag.orders (
  id INT NOT NULL AUTO_INCREMENT,
  orderedBy VARCHAR(45) NOT NULL,
  productId INT NOT NULL,
  amount INT NULL,
  cost DECIMAL(10,2) NULL,
  orderNumber INT NOT NULL,
  PRIMARY KEY (id),
  INDEX username_idx (orderedBy ASC) VISIBLE,
  INDEX productId_idx (productId ASC) VISIBLE,
  CONSTRAINT clientId
    FOREIGN KEY (orderedBy)
    REFERENCES electroMag.users (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT productId
    FOREIGN KEY (productId)
    REFERENCES electroMag.productsData (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO users (username, password, name, type)
VALUES 
    ('ana_popescu', 'parola123', 'Ana Popescu', 'admin'),
    ('ion_mihai', 'parolasecure', 'Ion Mihai', 'buyer'),
    ('elena_stoica', 'parola456', 'Elena Stoica', 'seller'),
    ('alexandra_nicolae', 'securepass', 'Alexandra Nicolae', 'buyer'),
    ('cristian_dumitru', 'pass123', 'Cristian Dumitru', 'seller'),
    ('laura_georgescu', 'password456', 'Laura Georgescu', 'buyer'),
    ('stefan_vasile', 'securepassword', 'Stefan Vasile', 'seller'),
    ('diana_iliescu', 'diana123', 'Diana Iliescu', 'buyer'),
    ('florin_gheorghe', 'passflorin', 'Florin Gheorghe', 'seller'),
    ('roxana_petre', 'roxpass', 'Roxana Petre', 'admin');

INSERT INTO chat (user1, user2, message)
VALUES 
    ('ana_popescu', 'ion_mihai', 'Hi Ion! Have you checked out the latest deals on electroMag?'),
    ('ion_mihai', 'ana_popescu', 'Hi Ana! Yes, I saw the discounts. Planning to buy something?'),
    ('elena_stoica', 'alexandra_nicolae', 'Hey Alexandra! Did you see the new electronics on electroMag?'),
    ('alexandra_nicolae', 'elena_stoica', 'Hi Elena! Yes, I am thinking of getting a new laptop.'),
    ('cristian_dumitru', 'laura_georgescu', 'Hello Laura! Have you explored the electroMag fashion section?'),
    ('laura_georgescu', 'cristian_dumitru', 'Hi Cristian! Not yet, but I will check it out.'),
    ('stefan_vasile', 'diana_iliescu', 'Hey Diana! electroMag has a sale on home appliances. Need anything?'),
    ('diana_iliescu', 'stefan_vasile', 'Hi Stefan! Thanks for letting me know. I might grab a coffee machine.'),
    ('florin_gheorghe', 'roxana_petre', 'Hello Roxana! Have you seen the electroMag daily deals?'),
    ('roxana_petre', 'florin_gheorghe', 'Hi Florin! Yes, they have some great offers. Considering a purchase.'),
    ('ana_popescu', 'ion_mihai', 'Ion, did you read the electroMag reviews for that product?'),
    ('ion_mihai', 'ana_popescu', 'No, not yet. Are they helpful?'),
    ('elena_stoica', 'alexandra_nicolae', 'Alexandra, have you added items to your electroMag wishlist?'),
    ('alexandra_nicolae', 'elena_stoica', 'Not yet, but I will create one. Any recommendations?'),
    ('cristian_dumitru', 'laura_georgescu', 'Laura, electroMag has a tech expo next week. Interested in attending?'),
    ('laura_georgescu', 'cristian_dumitru', 'Sure, I would love to check out the latest gadgets.'),
    ('stefan_vasile', 'diana_iliescu', 'Diana, electroMag is offering free shipping this weekend.'),
    ('diana_iliescu', 'stefan_vasile', 'Thanks for letting me know, Stefan! I will order something.'),
    ('florin_gheorghe', 'roxana_petre', 'Roxana, did you see the electroMag newsletter about upcoming sales?'),
    ('roxana_petre', 'florin_gheorghe', 'Yes, I got it. Let s plan our purchases accordingly.'),
    ('ana_popescu', 'ion_mihai', 'Ion, let s explore the electroMag Black Friday deals together.'),
    ('ion_mihai', 'ana_popescu', 'Sure, Ana! We can find some great discounts.');


INSERT INTO productsData (category, name, price, description, stock, postedBy)
VALUES 
    ('TV', 'Samsung 55-inch 4K Smart TV', 799.99, 'Experience stunning visuals with this 4K Smart TV from Samsung.', 10, 'ana_popescu'),
    ('Phone', 'iPhone 13', 999.99, 'The latest iPhone with a powerful camera and A15 Bionic chip.', 20, 'ion_mihai'),
    ('Laptop', 'Dell XPS 13', 1299.99, 'Ultra-thin and powerful laptop for professionals and creators.', 15, 'elena_stoica'),
    ('TV', 'LG 65-inch OLED TV', 1499.99, 'Enjoy vibrant colors and deep blacks with this OLED TV from LG.', 12, 'alexandra_nicolae'),
    ('Phone', 'Samsung Galaxy S21', 899.99, 'A flagship phone with a stunning display and versatile camera system.', 18, 'cristian_dumitru'),
    ('Laptop', 'HP Spectre x360', 1199.99, 'Convertible laptop with a sleek design and impressive performance.', 14, 'laura_georgescu'),
    ('TV', 'Sony 75-inch 8K LED TV', 2499.99, 'Immerse yourself in 8K resolution with this Sony LED TV.', 8, 'stefan_vasile'),
    ('Phone', 'Google Pixel 6', 799.99, 'A camera-centric phone with advanced AI features and a smooth experience.', 22, 'diana_iliescu'),
    ('Laptop', 'Lenovo ThinkPad X1 Carbon', 1699.99, 'Business laptop with a durable build and high-end specifications.', 10, 'florin_gheorghe'),
    ('TV', 'Vizio 50-inch 4K Smart TV', 599.99, 'Affordable 4K Smart TV with Vizio s SmartCast platform.', 15, 'roxana_petre'),
    ('Phone', 'OnePlus 9', 699.99, 'Flagship killer with a 120Hz Fluid AMOLED display and powerful performance.', 25, 'ana_popescu'),
    ('Laptop', 'Asus ROG Zephyrus G14', 1499.99, 'Gaming laptop with AMD Ryzen 9 processor and RTX 3060 graphics.', 12, 'ion_mihai'),
    ('TV', 'TCL 55-inch QLED TV', 899.99, 'QLED technology for vibrant colors and Dolby Vision support.', 17, 'elena_stoica'),
    ('Phone', 'Xiaomi Mi 11', 749.99, 'Feature-packed smartphone with a high-resolution camera and Snapdragon 888.', 20, 'alexandra_nicolae'),
    ('Laptop', 'Acer Swift 5', 899.99, 'Ultra-lightweight laptop with a powerful Intel Core i7 processor.', 18, 'cristian_dumitru'),
    ('TV', 'Hisense 65-inch ULED TV', 799.99, 'ULED technology for enhanced picture quality and HDR support.', 14, 'laura_georgescu'),
    ('Phone', 'Huawei P40 Pro', 899.99, 'Leica quad-camera system and powerful Kirin 990 processor.', 16, 'stefan_vasile'),
    ('Laptop', 'Microsoft Surface Laptop 4', 1299.99, 'Premium laptop with a high-resolution PixelSense touchscreen.', 10, 'diana_iliescu'),
    ('TV', 'Panasonic 58-inch 4K Smart TV', 699.99, 'Smart TV with Dolby Vision and Dolby Atmos support.', 20, 'florin_gheorghe'),
    ('Phone', 'Sony Xperia 1 III', 1099.99, 'Cinematic 4K HDR display and pro-level camera features.', 15, 'roxana_petre');


INSERT INTO orders (orderedBy, productId, amount, cost, orderNumber)
VALUES 
    ('ana_popescu', 1, 2, 1599.98, 1001),
    ('ion_mihai', 3, 1, 1299.99, 1002),
    ('elena_stoica', 5, 3, 2699.97, 1003),
    ('alexandra_nicolae', 7, 1, 899.99, 1004),
    ('cristian_dumitru', 9, 2, 3399.98, 1005),
    ('laura_georgescu', 11, 1, 699.99, 1006),
    ('stefan_vasile', 13, 2, 1799.98, 1007),
    ('diana_iliescu', 15, 1, 899.99, 1008),
    ('florin_gheorghe', 17, 3, 2399.97, 1009),
    ('roxana_petre', 19, 2, 2199.98, 1010),
    ('ana_popescu', 2, 1, 799.99, 1001), -- Same orderNumber as ana_popescu's previous order
    ('ion_mihai', 4, 2, 2899.98, 1002), -- Same orderNumber as ion_mihai's previous order
    ('elena_stoica', 6, 1, 1199.99, 1003), -- Same orderNumber as elena_stoica's previous order
    ('alexandra_nicolae', 8, 3, 2699.97, 1004), -- Same orderNumber as alexandra_nicolae's previous order
    ('cristian_dumitru', 10, 1, 599.99, 1005), -- Same orderNumber as cristian_dumitru's previous order
    ('laura_georgescu', 12, 2, 2999.98, 1006), -- Same orderNumber as laura_georgescu's previous order
    ('stefan_vasile', 14, 1, 749.99, 1007), -- Same orderNumber as stefan_vasile's previous order
    ('diana_iliescu', 16, 3, 2699.97, 1008), -- Same orderNumber as diana_iliescu's previous order
    ('florin_gheorghe', 18, 1, 799.99, 1009), -- Same orderNumber as florin_gheorghe's previous order
    ('roxana_petre', 20, 2, 2199.98, 1010); -- Same orderNumber as roxana_petre's previous order