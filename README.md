Codeacademy.lt baigiamoji užduotis, REST dalis.

REST API valdo MySQL duomenų bazės lenteles dviem duomenų grupėms:
    Traukinių maršrutų sąrašui
    Kliento bilietų pirkimui.


Programos paleidimas

    Paleidimui TrainroutesRestApp - main metodas

	MySQL pradinis duomenų sukūrimui
		main metodo klasėje - CommandLineRunner run() initDao() (šiame commite po komentaru, neveiksnus)

    Jar package - trainroutes-0.0.1-SNAPSHOT-spring-boot.jar, paleidus inicijuoja db.


REST API

Informacijos apie traukinių maršrutus valdymas

Duomenų formatas:

{
	"trainsDTO": [
		{
			"trainNumber": "Tr-065",
			"cityFrom": "Vilnius",
			"cityTo": "Kaunas",
			"departTime": "09:15",
			"arrivalTime": "10:20"
		},
		{
			"trainNumber": "Tr-066",
			"cityFrom": "Kaunas",
			"cityTo": "Vilnius",
			"departTime": "11:23",
			"arrivalTime": "12:45"
		}
    ]
}

Operacijos

    Gauti visą traukinių maršrutų sąrašą:

    GET /v1/trains

    Gauti vieną traukinio maršrutą pagal maršruto traukinio numerį

    GET /v1/trains/{traukinio_numeris}

    Gauti visų galinių maršrutų miestų sarašą:

    GET /v1/trains/cities

    Užklausti, ar tiesioginis reisas tarp miestų egzistuoja:

    GET /v1/trains/route?from={miestas}&to={miestas}


    Maršruto duomenų atnaujinimas:

    PUT /v1/trains/{traukinio_numeris} // Pastaba: jeigu numerio db nėra, sukuriamas naujas maršrutas

    Maršruto ištrynimas:

    DELETE /v1/trains/{traukinio_numeris}

    Maršruto sukūrimas:

    POST /v1/trains


Informacijos apie bilietus važiuoti maršrutais valdymas

Duomenų formatas:

{
	"ticketsDTO": [
		{
			"userId": 2,
			"trainNumber": "Tr-055",
			"cityFrom": "Vilnius",
			"cityTo": "Klaipėda",
			"departTime": "06:40",
			"arrivalTime": "11:05",
			"userPrice": 1000
		},
		{
			"userId": 2,
			"trainNumber": "Tr-082",
			"cityFrom": "Vilnius",
			"cityTo": "Trakai",
			"departTime": "08:10",
			"arrivalTime": "09:00",
			"userPrice": 1000
		},
	]
}

    Gauti visus kliento pirktus bilietus

    GET /v1/users/{kliento_id}/get_tickets

    Sukurti naują bilietą maršrutui

    /v1/users/{kliento_id}/buy_tickets?trainNo={traukinio_numeris}&(optional)quantity={bilietu_kiekis}
    



