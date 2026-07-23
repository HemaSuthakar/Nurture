-- =====================================================================
-- Hands on 1: Country reference data (ISO 3166-1 alpha-2 codes)
-- =====================================================================
INSERT INTO country (co_code, co_name) VALUES
('AF','Afghanistan'),('AX','Aland Islands'),('AL','Albania'),('DZ','Algeria'),
('AS','American Samoa'),('AD','Andorra'),('AO','Angola'),('AI','Anguilla'),
('AQ','Antarctica'),('AG','Antigua and Barbuda'),('AR','Argentina'),('AM','Armenia'),
('AW','Aruba'),('AU','Australia'),('AT','Austria'),('AZ','Azerbaijan'),
('BS','Bahamas'),('BH','Bahrain'),('BD','Bangladesh'),('BB','Barbados'),
('BY','Belarus'),('BE','Belgium'),('BZ','Belize'),('BJ','Benin'),
('BM','Bermuda'),('BT','Bhutan'),('BO','Bolivia'),('BA','Bosnia and Herzegovina'),
('BW','Botswana'),('BV','Bouvet Island'),('BR','Brazil'),
('IO','British Indian Ocean Territory'),('BN','Brunei Darussalam'),('BG','Bulgaria'),
('BF','Burkina Faso'),('BI','Burundi'),('KH','Cambodia'),('CM','Cameroon'),
('CA','Canada'),('CV','Cape Verde'),('KY','Cayman Islands'),
('CF','Central African Republic'),('TD','Chad'),('CL','Chile'),('CN','China'),
('CX','Christmas Island'),('CC','Cocos Islands'),('CO','Colombia'),
('KM','Comoros'),('CG','Congo'),('CD','Congo, Democratic Republic'),
('CK','Cook Islands'),('CR','Costa Rica'),('CI','Cote D''Ivoire'),('HR','Croatia'),
('CU','Cuba'),('CY','Cyprus'),('CZ','Czech Republic'),('DK','Denmark'),
('DJ','Djibouti'),('DM','Dominica'),('DO','Dominican Republic'),('EC','Ecuador'),
('EG','Egypt'),('SV','El Salvador'),('GQ','Equatorial Guinea'),('ER','Eritrea'),
('EE','Estonia'),('ET','Ethiopia'),('FK','Falkland Islands'),('FO','Faroe Islands'),
('FJ','Fiji'),('FI','Finland'),('FR','France'),('GF','French Guiana'),
('PF','French Polynesia'),('TF','French Southern Territories'),('GA','Gabon'),
('GM','Gambia'),('GE','Georgia'),('DE','Germany'),('GH','Ghana'),
('GI','Gibraltar'),('GR','Greece'),('GL','Greenland'),('GD','Grenada'),
('GP','Guadeloupe'),('GU','Guam'),('GT','Guatemala'),('GG','Guernsey'),
('GN','Guinea'),('GW','Guinea-Bissau'),('GY','Guyana'),('HT','Haiti'),
('HN','Honduras'),('HK','Hong Kong'),('HU','Hungary'),('IS','Iceland'),
('IN','India'),('ID','Indonesia'),('IR','Iran'),('IQ','Iraq'),('IE','Ireland'),
('IM','Isle of Man'),('IL','Israel'),('IT','Italy'),('JM','Jamaica'),
('JP','Japan'),('JE','Jersey'),('JO','Jordan'),('KZ','Kazakhstan'),
('KE','Kenya'),('KI','Kiribati'),('KP','Korea, North'),('KR','Korea, South'),
('KW','Kuwait'),('KG','Kyrgyzstan'),('LA','Laos'),('LV','Latvia'),
('LB','Lebanon'),('LS','Lesotho'),('LR','Liberia'),('LY','Libya'),
('LI','Liechtenstein'),('LT','Lithuania'),('LU','Luxembourg'),('MO','Macao'),
('MK','Macedonia'),('MG','Madagascar'),('MW','Malawi'),('MY','Malaysia'),
('MV','Maldives'),('ML','Mali'),('MT','Malta'),('MH','Marshall Islands'),
('MQ','Martinique'),('MR','Mauritania'),('MU','Mauritius'),('YT','Mayotte'),
('MX','Mexico'),('FM','Micronesia'),('MD','Moldova'),('MC','Monaco'),
('MN','Mongolia'),('ME','Montenegro'),('MS','Montserrat'),('MA','Morocco'),
('MZ','Mozambique'),('MM','Myanmar'),('NA','Namibia'),('NR','Nauru'),
('NP','Nepal'),('NL','Netherlands'),('NC','New Caledonia'),('NZ','New Zealand'),
('NI','Nicaragua'),('NE','Niger'),('NG','Nigeria'),('NU','Niue'),
('NF','Norfolk Island'),('NO','Norway'),('OM','Oman'),('PK','Pakistan'),
('PW','Palau'),('PS','Palestine'),('PA','Panama'),('PG','Papua New Guinea'),
('PY','Paraguay'),('PE','Peru'),('PH','Philippines'),('PN','Pitcairn'),
('PL','Poland'),('PT','Portugal'),('PR','Puerto Rico'),('QA','Qatar'),
('RE','Reunion'),('RO','Romania'),('RU','Russian Federation'),('RW','Rwanda'),
('BL','Saint Barthelemy'),('SH','Saint Helena'),('KN','Saint Kitts and Nevis'),
('LC','Saint Lucia'),('MF','Saint Martin'),('PM','Saint Pierre and Miquelon'),
('VC','Saint Vincent and Grenadines'),('WS','Samoa'),('SM','San Marino'),
('ST','Sao Tome and Principe'),('SA','Saudi Arabia'),('SN','Senegal'),
('RS','Serbia'),('SC','Seychelles'),('SL','Sierra Leone'),('SG','Singapore'),
('SK','Slovakia'),('SI','Slovenia'),('SB','Solomon Islands'),('SO','Somalia'),
('ZA','South Africa'),('GS','South Georgia and the South Sandwich Islands'),
('SS','South Sudan'),('ES','Spain'),('LK','Sri Lanka'),('SD','Sudan'),
('SR','Suriname'),('SJ','Svalbard and Jan Mayen'),('SZ','Swaziland'),
('SE','Sweden'),('CH','Switzerland'),('SY','Syrian Arab Republic'),
('TW','Taiwan'),('TJ','Tajikistan'),('TZ','Tanzania'),('TH','Thailand'),
('TL','Timor-Leste'),('TG','Togo'),('TK','Tokelau'),('TO','Tonga'),
('TT','Trinidad and Tobago'),('TN','Tunisia'),('TR','Turkey'),
('TM','Turkmenistan'),('TC','Turks and Caicos Islands'),('TV','Tuvalu'),
('UG','Uganda'),('UA','Ukraine'),('AE','United Arab Emirates'),
('GB','United Kingdom'),('US','United States'),
('UM','United States Minor Outlying Islands'),('UY','Uruguay'),
('UZ','Uzbekistan'),('VU','Vanuatu'),('VA','Vatican City'),('VE','Venezuela'),
('VN','Vietnam'),('VG','Virgin Islands, British'),('VI','Virgin Islands, U.S.'),
('WF','Wallis and Futuna'),('EH','Western Sahara'),('YE','Yemen'),
('ZM','Zambia'),('ZW','Zimbabwe');

-- =====================================================================
-- Hands on 2: Stock sample data (Facebook, Google, Netflix)
-- Representative sample covering all required Query Method scenarios.
-- (Full one-year CSV is distributed separately per the original exercise;
-- this sample is enough to exercise and demonstrate every query below.)
-- =====================================================================

-- FB - September 2019 (used by "Facebook stocks in September 2019")
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES
('FB','2019-09-03',184.00,182.39,9779400),
('FB','2019-09-04',184.65,187.14,11308000),
('FB','2019-09-05',188.53,190.90,13876700),
('FB','2019-09-06',190.21,187.49,15226800),
('FB','2019-09-09',187.73,188.76,14722400),
('FB','2019-09-10',187.44,186.17,15455900),
('FB','2019-09-11',186.46,188.49,11761700),
('FB','2019-09-12',189.86,187.47,11419800),
('FB','2019-09-13',187.33,187.19,11441100),
('FB','2019-09-16',186.93,186.22,8444800),
('FB','2019-09-17',186.66,188.08,9671100),
('FB','2019-09-18',188.09,188.14,9681900),
('FB','2019-09-19',188.66,190.14,10392700),
('FB','2019-09-20',190.66,189.93,19934200),
('FB','2019-09-23',189.34,186.82,13327600),
('FB','2019-09-24',187.98,181.28,18546600),
('FB','2019-09-25',181.45,182.80,18068300),
('FB','2019-09-26',181.33,180.11,16083300),
('FB','2019-09-27',180.49,177.10,14656200);

-- FB - highest volume days (used by "top 3 highest volume" query)
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES
('FB','2019-01-31',165.60,166.69,77233600),
('FB','2018-10-31',155.00,151.79,60101300),
('FB','2018-12-19',141.21,133.24,57404900);

-- GOOGL - close price greater than 1250 (used by "google price > 1250" query)
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES
('GOOGL','2019-04-22',1236.67,1253.76,954200),
('GOOGL','2019-04-23',1256.64,1270.59,1593400),
('GOOGL','2019-04-24',1270.59,1260.05,1169800),
('GOOGL','2019-04-25',1270.30,1267.34,1567200),
('GOOGL','2019-04-26',1273.38,1277.42,1361400),
('GOOGL','2019-04-29',1280.51,1296.20,3618400),
('GOOGL','2019-10-17',1251.40,1252.80,1047900);

-- GOOGL - some lower prices too, so the ">1250" filter is meaningful
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES
('GOOGL','2018-10-18',1174.35,1157.35,1490300),
('GOOGL','2019-01-03',1051.79,1054.68,1966100),
('GOOGL','2019-06-05',1044.22,1044.22,3155600);

-- NFLX - lowest close prices (used by "3 dates Netflix stocks were lowest")
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES
('NFLX','2018-12-24',242.00,233.88,9547600),
('NFLX','2018-12-21',263.83,246.39,21397600),
('NFLX','2018-12-26',233.92,253.67,14402700);

-- NFLX - other data points, so the "lowest" query has a wider pool to sort through
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES
('NFLX','2019-05-13',350.31,335.53,11455300),
('NFLX','2019-08-06',300.60,299.05,9825700),
('NFLX','2019-10-17',280.00,279.16,4707800);

-- =====================================================================
-- Hands on 3/4/5/6: Payroll data (Department, Employee, Skill)
-- =====================================================================
INSERT INTO department (dp_id, dp_name) VALUES
(1,'Engineering'),
(2,'Human Resources'),
(3,'Finance'),
(4,'Sales');

INSERT INTO skill (sk_id, sk_name) VALUES
(1,'Java'),
(2,'Spring Boot'),
(3,'SQL'),
(4,'Communication'),
(5,'Project Management');

-- Department 1 (Engineering) intentionally has more than one employee,
-- so testGetDepartment() can demonstrate the OneToMany LAZY/EAGER behaviour.
INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES
(1,'Alice Johnson',75000.00,true,'1990-04-12',1),
(2,'Bob Smith',68000.00,true,'1988-11-23',1),
(3,'Carla Mendes',72000.00,false,'1992-02-17',1),
(4,'David Lee',54000.00,true,'1995-07-09',2),
(5,'Elena Petrova',61000.00,true,'1991-09-30',3),
(6,'Farhan Ali',49000.00,false,'1997-01-05',4);

-- Employee <-> Skill relationships. Employee 6 / Skill 5 is intentionally
-- left unmapped so testAddSkillToEmployee() has a fresh pair to add.
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES
(1,1),(1,2),(1,3),
(2,1),(2,2),
(3,3),(3,4),
(4,4),
(5,3),(5,5),
(6,1);
