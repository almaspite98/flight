#!/bin/bash

for j in {20..60}
do
	echo "Loop: $i, Device: $j"
	curl --silent -X POST "http://localhost:8080/flight" -H "accept: */*" -H "api_key: Wizz1234" -H "Content-Type: application/json" --data-raw "{ \"airline\": \"Wizz Air\", \"arrivalTime\": \"2021-11-25T09:14:31.714Z\", \"departureTime\": \"2021-11-25T10:14:31.714Z\", \"flightId\": \"string$j\", \"fromCity\": \"Kairo\", \"numberOfSeats\": 10, \"toCity\": \"Wienna\"}"
	sleep 2
done
