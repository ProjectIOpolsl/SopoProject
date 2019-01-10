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

INSERT INTO user VALUES (1,CURRENT_TIMESTAMP,1,null,null,null,null,"Pawel","11111","STOWSKI",1);
INSERT INTO user VALUES (2,CURRENT_TIMESTAMP,1,null,null,null,null,"Michal","423324","KAMINSKI",2);
INSERT INTO user VALUES (3,CURRENT_TIMESTAMP,1,null,null,null,null,"Tomek","2342342","Wachowski",3);

INSERT INTO account VALUES (1,false,"USER","abc@op.pl","$2a$04$voqBkzyP/ylkz9iVZh4nTuo/rh6ytbYPPv1wiBBj4XROXbNYRgdJm",1);
INSERT INTO account VALUES (2,false,"USER","cde@op.pl","$2a$04$voqBkzyP/ylkz9iVZh4nTuo/rh6ytbYPPv1wiBBj4XROXbNYRgdJm",2);
INSERT INTO account VALUES (3,false,"USER","efg@op.pl","$2a$04$voqBkzyP/ylkz9iVZh4nTuo/rh6ytbYPPv1wiBBj4XROXbNYRgdJm",3);
#HASLO: passwd




INSERT INTO privilige values (1,"USER")
INSERT INTO privilige values (2,"ADMIN")
INSERT INTO privilige values (3,"SUPER_ADMIN")


INSERT INTO project VALUES(1,CURRENT_TIMESTAMP,1,null,null,null,null,'Kozielska',5000.2,'To jest pierwszy project','PARK ZABAW',300,2,2);
INSERT INTO project VALUES(2,CURRENT_TIMESTAMP,1,null,null,null,null,'Kozielska',12412.2,'To jest drugi project','PARK',523,1,2);
INSERT INTO project VALUES(3,CURRENT_TIMESTAMP,1,null,null,null,null,'Kozielska',345.2,'To jest trzeci project','PALMIARNIA',5,3,2);
INSERT INTO project VALUES(4,CURRENT_TIMESTAMP,1,null,null,null,null,'Kozielska',56334.2,'To jest czwarty project','ARENA',656,2,2);
INSERT INTO project VALUES(5,CURRENT_TIMESTAMP,1,null,null,null,null,'Kozielska',43534534.2,'To jest piaty project','NOWA DZIELNICA',6546,2,2);
INSERT INTO project VALUES(6,CURRENT_TIMESTAMP,1,null,null,null,null,'Kozielska',3333.2,'To jest szosty project','ODNOWA KOPERNIKA',4,1,2);