db.user.drop();
db.led.drop();
db.potenciometro.drop();
db.sequence.drop();

db.user.insert({ "_id" : NumberLong(1), "googleId" : "116397068645948661793", "email": "ceanma@gmail.com",             "password" : "$2a$10$j9Rae1utAPKuTZaK.UYHqeyiqlmXmXuJSmX1AhJrgqM7mj4S31v8O", "enabled" : true,  "authorities" : [ "ROLE_USER", "ROLE_ADMIN" ] });
db.user.insert({ "_id" : NumberLong(2), "googleId" : "101804036736297811052", "email": "daniel.bbb1991@gmail.com",     "password" : "$2a$10$xWt/NK4Uzqv8HR4ghMSOM.8Bhawwjl9QT3ju9cJLTHBrrjb2e0z7O", "enabled" : true,  "authorities" : [ "ROLE_USER" ] });
db.user.insert({ "_id" : NumberLong(3), "googleId" : "114464310684637383259", "email": "wesleyfernandes117@gmail.com", "password" : "$2a$10$rmXzCMIjGaKpeOM8BClySOONA3Jcq6MGew.N/WwGUj4p9BQ9O2ee.", "enabled" : true,  "authorities" : [ ] });
db.user.insert({ "_id" : NumberLong(4), "googleId" : "117434573431637728388", "email": "lelumag86@gmail.com",          "password" : "$2a$10$j9Rae1utAPKuTZaK.UYHqeyiqlmXmXuJSmX1AhJrgqM7mj4S31v8O", "enabled" : false, "authorities" : [ "ROLE_USER", "ROLE_ADMIN" ] });
db.sequence.insert({_id: "br.com.cams7.app.domain.entity.UserEntity", sequence: 4});

db.led.insert({ "_id" : NumberLong(1), "cor" : "VERMELHO", "ativo" : true, "ativadoPorBotao" : false, "pino" : { "tipo" : "DIGITAL", "codigo" : 13 }, "evento" : "PISCA_PISCA",  "alteraEvento" : false, "intervalo" : "INTERVALO_1SEGUNDO",        "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(2), "cor" : "AMARELO",  "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 11 }, "evento" : "ACENDE_APAGA", "alteraEvento" : true,  "intervalo" : "SEM_INTERVALO",             "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(3), "cor" : "VERDE",    "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 10 }, "evento" : "FADE",         "alteraEvento" : true,  "intervalo" : "INTERVALO_10MILISEGUNDOS",  "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(4), "cor" : "VERMELHO", "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 9 },  "evento" : "PISCA_PISCA",  "alteraEvento" : true,  "intervalo" : "INTERVALO_100MILISEGUNDOS", "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(5), "cor" : "AMARELO",  "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 6 },  "evento" : "ACENDE_APAGA", "alteraEvento" : true,  "intervalo" : "SEM_INTERVALO",             "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(7), "cor" : "VERDE",    "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 5 },  "evento" : "FADE",         "alteraEvento" : true,  "intervalo" : "INTERVALO_50MILISEGUNDOS",  "alteraIntervalo" : true });
db.led.insert({ "_id" : NumberLong(6), "cor" : "VERMELHO", "ativo" : true, "ativadoPorBotao" : true,  "pino" : { "tipo" : "DIGITAL", "codigo" : 4 },  "evento" : "PISCA_PISCA",  "alteraEvento" : false, "intervalo" : "INTERVALO_3SEGUNDOS",       "alteraIntervalo" : true });
db.sequence.insert({_id: "br.com.cams7.sisbarc.aal.domain.entity.LEDEntity", sequence: 7});

db.potenciometro.insert({ "_id" : NumberLong(1), "pino" : { "tipo" : "ANALOG", "codigo" : 0 }, "evento" : "NENHUM", "alteraEvento" : true, "intervalo" : "SEM_INTERVALO", "alteraIntervalo" : true });
db.sequence.insert({_id: "br.com.cams7.sisbarc.aal.domain.entity.PotenciometroEntity", sequence: 1});

show collections;