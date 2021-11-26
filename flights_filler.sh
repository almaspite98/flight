#!/bin/bash

for j in {33..60}
do
	echo "Loop: $i, Device: $j"
	curl --silent -X POST "http://localhost:8080/flight" -H "accept: */*" -H "api_key: Wizz1234" -H "Content-Type: application/json" --data-raw "{ \"airline\": \"Wizz Air\", \"arrivalTime\": \"2021-11-26T12:14:31.714Z\", \"departureTime\": \"2021-11-26T11:14:31.714Z\", \"flightId\": \"string$j\", \"fromCity\": \"Budapest\", \"numberOfSeats\": 10, \"toCity\": \"Wienna\"}"
	sleep 2
done
