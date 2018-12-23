INSERT INTO neighbourhood VALUES (1,CURRENT_TIMESTAMP,1,null,null,null,null,"Śródmieście");
INSERT INTO neighbourhood VALUES (2,CURRENT_TIMESTAMP,1,null,null,null,null,"Ostropa");
INSERT INTO neighbourhood VALUES (3,CURRENT_TIMESTAMP,1,null,null,null,null,"Wojska Polskiego");
INSERT INTO neighbourhood VALUES (4,CURRENT_TIMESTAMP,1,null,null,null,null,"śródmieście");
INSERT INTO neighbourhood VALUES (5,CURRENT_TIMESTAMP,1,null,null,null,null,"Politechnika");
INSERT INTO neighbourhood VALUES (6,CURRENT_TIMESTAMP,1,null,null,null,null,"Zatorze");
INSERT INTO neighbourhood VALUES (7,CURRENT_TIMESTAMP,1,null,null,null,null,"Trójkąt");
INSERT INTO neighbourhood VALUES (8,CURRENT_TIMESTAMP,1,null,null,null,null,"Łabędy");
INSERT INTO neighbourhood VALUES (9,CURRENT_TIMESTAMP,1,null,null,null,null,"Żerniki");


INSERT INTO address VALUES(1,CURRENT_TIMESTAMP,1,null,null,null,null,"GLIWICE","22","KOZIELSKA",1);
INSERT INTO address VALUES(2,CURRENT_TIMESTAMP,1,null,null,null,null,"GLIWICE","1","PULAWSKA",1);
INSERT INTO address VALUES(3,CURRENT_TIMESTAMP,1,null,null,null,null,"GLIWICE","232","GAGARINA",1);
INSERT INTO address VALUES(4,CURRENT_TIMESTAMP,1,null,null,null,null,"GLIWICE","99","LUZYCKA",1);

INSERT INTO user VALUES (1,CURRENT_TIMESTAMP,1,null,null,null,null,"Pawel","241241242","STOWSKI",1);
INSERT INTO user VALUES (2,CURRENT_TIMESTAMP,1,null,null,null,null,"Michal","sdrq3refwd","KAMINSKI",2);
INSERT INTO user VALUES (3,CURRENT_TIMESTAMP,1,null,null,null,null,"Tomek","23432edasfa","Wachowski",3);

INSERT INTO account VALUES (1,false,"USER","abc@op.pl","$2a$04$voqBkzyP/ylkz9iVZh4nTuo/rh6ytbYPPv1wiBBj4XROXbNYRgdJm",1);
INSERT INTO account VALUES (2,false,"USER","cde@op.pl","$2a$04$voqBkzyP/ylkz9iVZh4nTuo/rh6ytbYPPv1wiBBj4XROXbNYRgdJm",2);
INSERT INTO account VALUES (3,false,"USER","efg@op.pl","$2a$04$voqBkzyP/ylkz9iVZh4nTuo/rh6ytbYPPv1wiBBj4XROXbNYRgdJm",3);
HASLO: passwd

INSERT INTO privilige values (1,"USER")
INSERT INTO privilige values (2,"ADMIN")
INSERT INTO privilige values (3,"SUPER_ADMIN")