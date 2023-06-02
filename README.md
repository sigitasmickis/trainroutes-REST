# trainroutes-codeacademy
Final work to graduate the Java I &amp; 2 course

Codeacademy.lt final assignment, REST API part.

The REST API manages MySQL database tables for two groups of data:

     For a list of train routes
     For the purchase of customer tickets.

Launching the program
	To run: TrainroutesRestApp - main method

	For generating initial MySQL data:
		in the main method class run CommandLineRunner run() initDao() (in this commit under the comment, after using it once again disable, otherwise data duplicate in records of cities and tickets. Thus no protection is implemented like is for trains)

REST API

Management of information about train routes

Data format:
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

Operations

	Get a complete list of train routes:

     	GET /v1/trains

     Get one train route by route train number:

     	GET /v1/trains/{train_number}

     Get a list of all final route cities:

     	GET /v1/trains/cities

     Query whether a direct route between cities exists:

     	GET /v1/trains/route?from={city}&to={city}

     Updating route data:

     	PUT /v1/trains/{train_number} // Note: if the number does not exist in the db, a new route is created

     Deleting a route:

     	DELETE /v1/trains/{train_number}

     Creating a route:

     	POST /v1/trains



Management of information about tickets for routes

Data format:
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

	Get all tickets purchased by the customer:

     	GET /v1/users/{customer_id}/get_tickets

    Buy a new ticket for the route:

     	POST /v1/users/{client_id}/buy_tickets?trainNo={train_number}&(optional)quantity={ticket_quantity}

