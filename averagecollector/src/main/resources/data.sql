INSERT INTO CARD_YEAR (CARD_YEAR) VALUES  (1982), (1983), (1984),
  (1985), (1986), (1987), (1988), (1989), (1990), (1991), (1992),
  (1993), (1994), (1995), (1996), (1997), (1998), (1999), (2000),
  (2001), (2002), (2003), (2004), (2005), (2006), (2007), (2008),
  (2009), (2010), (2011), (2012), (2013), (2014), (2015), (2016),
  (2017);

INSERT INTO TEAM (CITY, TEAM_NAME) VALUES ('New York', 'Yankees'), ('Cincinnati', 'Reds'),
  ('Seattle', 'Mariners'), ('Washington', 'Nationals'), ('Boston', 'Red Sox'), ('Baltimore', 'Orioles'),
  ('Tampa Bay', 'Rays'), ('Toronto', 'Blue Jays'), ('Chicago', 'White Sox'), ('Cleveland', 'Indians'),
  ('Kansas City', 'Royals'), ('Minnesota', 'Twins'), ('Detroit', 'Tigers'), ('Los Angeles', 'Angels'),
  ('Houston', 'Astros'), ('Texas', 'Rangers'), ('Oakland', 'Athletics'), ('New York', 'Mets'),
  ('Philadelphia', 'Phillies'), ('Miami', 'Marlins'), ('Atlanta', 'Braves'), ('Chicago', 'Cubs'),
  ('Pittsburgh', 'Pirates'), ('Milwaukee', 'Brewers'), ('St. Louis', 'Cardinals'), ('Los Angeles', 'Dodgers'),
  ('San Francisco', 'Giants'), ('San Diego', 'Padres'), ('Arizona', 'Diamondbacks'), ('Colorado', 'Rockies'),
  ('Not a player', 'Not a player'), ('Japan', 'National Team');

INSERT INTO BRAND (BRAND_NAME) VALUES ('Topps'), ('Upper Deck'), ('Fleer'), ('Bowman'), ('TriStar'),
('Donruss'), ('Team Best'), ('Flair'), ('Just Minors');

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, DOB) VALUES ('Derek', 'Jeter', '1974-06-26'),
('Ichiro', 'Suzuki', '1973-10-22'), ('Ryan', 'Zimmerman', '1984-09-28'), ('Alex', 'Rodriguez', '1975-07-27'),
('Giancarlo', 'Stanton', '1989-11-08'), ('George', 'Springer', '1989-09-19'), ('Yoshimoto', 'Tsutsugoh', '1991-11-26'),
('Steve', 'Stone', '1947-07-14'), ('Bo', 'Jackson', '1962-11-30'), ('Rickey', 'Henderson', '1958-12-25'),
('Ozzie', 'Smith', '1954-12-26'), ('Dale', 'Murphy', '1956-03-12'), ('Ryan', 'Rupe', '1975-03-31'),
('Magglio', 'Ordonez', '1974-01-28'), ('Todd', 'Walker', '1973-05-25'), ('Randy', 'Johnson', '1963-09-10'),
('Luis', 'Gonzalez', '1967-09-03'), ('Chipper', 'Jones', '1972-04-24'), ('Jim', 'Thome', '1970-08-27'),
('Juan', 'Gonzalez', '1969-10-20'), ('Roberto', 'Alomar', '1968-02-05'), ('Joe', 'Borchard', '1978-11-25'),
('J.D.', 'Drew', '1975-11-20'), ('Mike', 'Piazza', '1968-09-08'), ('Andruw', 'Jones', '1977-04-23'),
('Francisco', 'Cruceta', '1981-07-04'), ('Frank', 'Thomas', '1968-05-27'), ('Nomar', 'Garciaparra', '1973-07-23'),
('Austin', 'Kearns', '1980-05-20'), ('Jason', 'Giambi', '1971-01-08'), ('Jimmy', 'Journell', '1977-12-29'),
('George', 'Brett', '1953-05-15');

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, SUFFIX, DOB) VALUES ('Ken', 'Griffey', 'Jr.', '1969-11-21'),
                                                               ('Ken', 'Griffey', 'Sr.', '1950-04-10');

INSERT INTO CARD_SET (YEAR_ID, BRAND_ID) VALUES ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 1982), 6),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 1986), 1), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 1990), 4),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 1993), 2), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2006), 1),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2007), 1), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2008), 1),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2001), 7);

INSERT INTO CARD_SET (YEAR_ID, BRAND_ID, SET_NAME) VALUES ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 1992), 3, 'Ultra'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2000), 3, 'Ultra'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2001), 3, 'Legacy'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2001), 3, 'Platinum'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2001), 2, 'Gold Glove'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2007), 2, 'Masterpieces'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2008), 2, 'Masterpieces'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2017), 1, 'Allen & Ginter'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2001), 2, 'SP Game Used Edition'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2001), 2, 'SPX'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2002), 8, 'Base Set'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2002), 3, 'Maximum'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2002), 2, 'Diamond Connection'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2002), 9, 'Just Rookies'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2003), 1, 'Tribute Contemporary Collection'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2003), 2, 'Sweet Spot'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2003), 4, 'Draft Picks & Prospects'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2004), 1, 'Cracker Jack'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2004), 1, 'Total'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2004), 3, 'Greats');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, IMAGE_FRONT) VALUES (14, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Derek' AND LAST_NAME = 'Jeter'),
                                                                                (SELECT id FROM TEAM WHERE TEAM_NAME = 'Yankees'), '30', '2007_ud_masterpieces_derek_jeter.jpg'),
                                                                            (5, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Ken' AND LAST_NAME = 'Griffey' AND SUFFIX = 'Jr.'),
                                                                                (SELECT id FROM TEAM WHERE TEAM_NAME = 'Reds'), '387', '2006_topps_ken_griffey_jr.jpg');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM) VALUES (2, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Bo' AND LAST_NAME = 'Jackson'),
                                                                   (SELECT id FROM TEAM WHERE TEAM_NAME = 'Royals'), '50T'),
                                                               (3, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Rickey' AND LAST_NAME = 'Henderson'),
                                                                   (SELECT id FROM TEAM WHERE CITY = 'Oakland'), '457'),
                                                               (9, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Ozzie' AND LAST_NAME = 'Smith'),
                                                                   (SELECT id FROM TEAM WHERE TEAM_NAME = 'Cardinals'), '271'),
                                                               (4, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Dale' AND LAST_NAME = 'Murphy'),
                                                                   (SELECT id FROM TEAM WHERE TEAM_NAME = 'Phillies'), '32');


INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, MEM_TYPE) VALUES (1, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Steve' AND LAST_NAME = 'Stone'),
                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Orioles'), '357', 'Autograph');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, INSERT_TYPE, MEM_TYPE) VALUES (16, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Giancarlo' AND LAST_NAME = 'Stanton'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Marlins'), 'GS', 'Full-Size Relic Design A', 'Relic'),
                                                                                      (16, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'George' AND LAST_NAME = 'Springer'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Astros'), 'GSP', 'Full-Size Relic Design B', 'Relic'),
                                                                                      (11, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Magglio'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'White Sox'), '23', 'Hit Kings', 'Bat'),
                                                                                      (12, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Todd' AND LAST_NAME = 'Walker'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Rockies'), 'TOWA', 'National Patch Time', 'Relic'),
                                                                                      (13, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Randy' AND LAST_NAME = 'Johnson'),
                                                                                          (SELECT id FROM TEAM WHERE CITY = 'Arizona'), 'JG', 'Official Issue Game Ball', 'Relic'),
                                                                                      (13, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Luis' AND LAST_NAME = 'Gonzalez'),
                                                                                          (SELECT id FROM TEAM WHERE CITY = 'Arizona'), 'JG', 'Official Issue Game Ball', 'Relic'),
                                                                                      (17, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Chipper'),
                                                                                          (SELECT id FROM TEAM WHERE CITY = 'Atlanta'), 'CJ', 'Authentic Fabric', 'Relic'),
                                                                                      (18, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Thome'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Indians'), 'TGA', 'Winning Materials', 'Triple Relic'),
                                                                                      (18, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Gonzalez' AND FIRST_NAME = 'Juan'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Indians'), 'TGA', 'Winning Materials', 'Triple Relic'),
                                                                                      (18, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Alomar'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Indians'), 'TGA', 'Winning Materials', 'Triple Relic'),
                                                                                      (20, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Piazza'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Mets'), 'MIPI', 'Americas'' Game', 'Relic'),
                                                                                      (23, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Frank' AND LAST_NAME = 'Thomas'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'White Sox'), 'FT', 'Tribute to the Stars', 'Dual Relic'),
                                                                                      (19, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'J.D.'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Cardinals'), 'JDDR', 'Jersey Heights', 'Relic');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, INSERT_TYPE, SERIAL_NUM, MEM_TYPE) VALUES (16, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Yoshimoto' AND LAST_NAME = 'Tsutsugoh'),
                                                                                                      (SELECT id FROM TEAM WHERE CITY = 'Japan'), 'YT', 'World Baseball Classic Relic', '92/99', 'Relic'),
                                                                                                  (10, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Ryan' AND LAST_NAME = 'Rupe'),
                                                                                                      (SELECT id FROM TEAM WHERE TEAM_NAME = 'Rays'), '51', 'Fresh Ink', '613/1000', 'Autograph'),
                                                                                                  (21, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Andruw'),
                                                                                                      (SELECT id FROM TEAM WHERE TEAM_NAME = 'Braves'), '559', 'Diamond Connection', '429/775', 'Relic');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, SERIAL_NUM, MEM_TYPE) VALUES (8, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Borchard'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'White Sox'), '2', '20/150', 'Autograph'),
                                                                                     (22, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Cruceta'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Dodgers'), '5', '281/400', 'Autograph');

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, DOB) VALUES ('Jamie', 'Moyer', '1962-11-18'), ('Jeff', 'Baker', '1981-06-21'), ('Chad', 'Orvella', '1980-10-01'),
('Carlos', 'Beltran', '1977-04-24'), ('Tim', 'Hudson', '1974-04-14'), ('Bret', 'Boone', '1969-04-06');

INSERT INTO CARD_SET (YEAR_ID, BRAND_ID) VALUES ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2004), 2), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2005), 2);
INSERT INTO CARD_SET (YEAR_ID, BRAND_ID, SET_NAME) VALUES ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2004), 6, 'Diamond Kings'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2005), 1, 'Pristine');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, INSERT_TYPE, MEM_TYPE) VALUES (24, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Garciaparra'),
                                                                                          (SELECT id FROM TEAM WHERE CITY = 'Boston'), 'NG1', 'Sweet Spot Swatches', 'Relic'),
                                                                                      (25, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Kearns'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Reds'), 'AK', 'Prospect Premiums', 'Relic'),
                                                                                      (26, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Giambi'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Yankees'), 'JGA', 'Take Me Out To The Ball Game', 'Relic'),
                                                                                      (27, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Journell'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Cardinals'), 'JJ', 'Total Signatures', 'Autograph'),
                                                                                      (28, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'George' AND LAST_NAME = 'Brett'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Royals'), 'GB', 'The Glory Of Their Time', 'Relic'),
                                                                                      (29, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Jamie' AND LAST_NAME = 'Moyer'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Mariners'), 'JM', 'Awesome Honors', 'Relic'),
                                                                                      (30, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Jeter'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Yankees'), 'DJ', 'Matinee Idols', 'Relic'),
                                                                                      (30, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Beltran'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Astros'), 'CB', 'Origins', 'Relic'),
                                                                                      (30, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Tim' AND LAST_NAME = 'Hudson'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Athletics'), 'TH', 'Origins', 'Relic'),
                                                                                      (30, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Bret' AND LAST_NAME = 'Boone'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Mariners'), 'BB', 'Signature Stars', 'Autograph');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, INSERT_TYPE, SERIAL_NUM, MEM_TYPE) VALUES (32, (SELECT id FROM PLAYER WHERE LAST_NAME = 'Orvella'),
                                                                                                      (SELECT id FROM TEAM WHERE TEAM_NAME = 'Rays'), 'CO', 'Personal Endorsements', '363/497', 'Autograph');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, INSERT_TYPE, PARALLEL_TYPE, SERIAL_NUM, MEM_TYPE) VALUES (31, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Jeff' AND LAST_NAME = 'Baker'),
                                                                                                      (SELECT id FROM TEAM WHERE TEAM_NAME = 'Rockies'), '87', 'DK Materials', 'Framed Bronze', '25/100', 'Dual Relic');

