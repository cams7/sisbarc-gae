db.usuario.drop();
db.led.drop();
db.potenciometro.drop();
db.sequence.drop();

db.usuario.insert({ "_id" : NumberLong(1), "login" : "admin",   "senha" : "$2a$10$j9Rae1utAPKuTZaK.UYHqeyiqlmXmXuJSmX1AhJrgqM7mj4S31v8O", "ativo" : true, "autorizacoes" : [ "ROLE_USER", "ROLE_ADMIN" ] });
db.usuario.insert({ "_id" : NumberLong(2), "login" : "sisbarc", "senha" : "$2a$10$xWt/NK4Uzqv8HR4ghMSOM.8Bhawwjl9QT3ju9cJLTHBrrjb2e0z7O", "ativo" : true, "autorizacoes" : [ "ROLE_USER" ] });
db.usuario.insert({ "_id" : NumberLong(3), "login" : "cesar",   "senha" : "$2a$10$rmXzCMIjGaKpeOM8BClySOONA3Jcq6MGew.N/WwGUj4p9BQ9O2ee.", "ativo" : true, "autorizacoes" : [ ] });
db.sequence.insert({_id: "br.com.cams7.sisbarc.aal.domain.entity.UsuarioEntity", sequence: 3});

db.led.insert({ "_id" : NumberLong(1), "cor" : "VERMELHO", "ativo" : true, "ativadoPorBotao" : false, "pino" : { "tipo" : "DIGITAL", "codigo" : 13 }, "evento" : "PISCA_PISCA",  "alteraEvento" : false, "intervalo" : "INTERVALO_1SEGUNDO",        "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(2), "cor" : "AMARELO",  "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 11 }, "evento" : "ACENDE_APAGA", "alteraEvento" : true,  "intervalo" : "SEM_INTERVALO",             "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(3), "cor" : "VERDE",    "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 10 }, "evento" : "PISCA_PISCA",  "alteraEvento" : true,  "intervalo" : "INTERVALO_100MILISEGUNDOS", "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(4), "cor" : "VERMELHO", "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 9 },  "evento" : "FADE",         "alteraEvento" : true,  "intervalo" : "INTERVALO_10MILISEGUNDOS",  "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(5), "cor" : "AMARELO",  "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 6 },  "evento" : "ACENDE_APAGA", "alteraEvento" : true,  "intervalo" : "SEM_INTERVALO",             "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(6), "cor" : "VERMELHO", "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 4 },  "evento" : "PISCA_PISCA",  "alteraEvento" : false, "intervalo" : "INTERVALO_3SEGUNDOS",       "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(7), "cor" : "VERDE",    "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 5 },  "evento" : "FADE",         "alteraEvento" : true,  "intervalo" : "INTERVALO_10MILISEGUNDOS",  "alteraIntervalo" : true });
db.sequence.insert({_id: "br.com.cams7.sisbarc.aal.domain.entity.LEDEntity", sequence: 7});

db.potenciometro.insert({ "_id" : NumberLong(1), "pino" : { "tipo" : "ANALOG", "codigo" : 0 }, "evento" : "NENHUM", "alteraEvento" : true, "intervalo" : "SEM_INTERVALO", "alteraIntervalo" : true });
db.sequence.insert({_id: "br.com.cams7.sisbarc.aal.domain.entity.PotenciometroEntity", sequence: 1});

show collections;