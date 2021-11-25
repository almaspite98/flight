#!/bin/bash

for j in {1..60}
do
	echo "Loop: $i, Device: $j"
	curl --silent -X POST "http://localhost:8080/flight" -H "accept: */*" -H "api_key: RyanAir_1234" -H "Content-Type: application/json" --data-raw "{ \"airline\": \"RyanAir\", \"arrivalTime\": \"2021-11-25T09:14:31.714Z\", \"departureTime\": \"2021-11-25T09:14:31.714Z\", \"flightId\": \"string$j\", \"fromCity\": \"Budapest\", \"numberOfSeats\": 10, \"toCity\": \"Wienna\"}"
	sleep 5
done
