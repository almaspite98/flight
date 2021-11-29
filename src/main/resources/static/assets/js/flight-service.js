
var routes;
var selectedRoute;
var current_user;

const login = async (email, password) => {
    var json = {
        'email': email,
        'password': password
    }
    const login_response = await fetch('http://localhost:8080/user/login', {
        method: 'POST',
        body: JSON.stringify(json),
        headers: {
            'Content-Type': 'application/json'
        }
    });
    if (login_response.status >= 400) {
        alert("Login was unsuccessful. Please try again!")
    } else {
        alert("Login was successful. Please proceed with finding the right flights for you!");
    }

    current_user = await login_response.json();
    if (current_user['departure'] != null) {
        console.log("deprature: " + current_user['departure'])
        document.getElementById('from').value = current_user['departure'];
    }
    if (current_user['departure'] != null) {
        console.log("transferTime: " + current_user['transferTime'])
        document.getElementById('wait_time').value = current_user['transferTime'];
    }
    if (current_user['airline'] != null) {
        console.log("airline: " + current_user['airline'])
        document.getElementById(current_user['airline']).selected = true;
    }
}

const register = async (email, password, confirm_password) => {
    if (password == confirm_password) {
        var json = {
            'email': email,
            'password': password
        }
        const response = await fetch('http://localhost:8080/user/register', {
            method: 'POST',
            body: JSON.stringify(json),
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.status >= 400) {
            alert("Signing up was unsuccessful. Please try again!")
        } else {
            alert("Signing up was successful. Please login!");
        }
    } else alert("Password and confirm password dont match, please try again!")
}

const savePreferences = async (from, wait_time, airline) => {
    if (current_user != null && current_user.token != null) {
        var json = {
            'departure': from,
            'transferTime': wait_time,
            'airline': airline
        }
        console.log("token: " + current_user.token);
        const login_response = await fetch('http://localhost:8080/user', {
            method: 'POST',
            body: JSON.stringify(json),
            headers: {
                'Content-Type': 'application/json',
                'token': current_user.token
            }
        });
        if (login_response.status >= 400) {
            alert("SavePreferences was unsuccessful.")
        } else {
            //alert("SavePreferences was successful.");
            //document.getElementById("modal-toggle").close
            //$('#modal-toggle').modal('hide');
        }
    }

}

function addHoursToDate(date, hours) {
    return new Date(new Date(date).setHours(date.getHours() + hours));
}

//$('#infoTable').on('click', 'tbody tr', function (event) {
//    $(this).addClass('highlight').siblings().removeClass('highlight');
//});


//$("#infoTable tr").click(function() {
//
//    $(".clickableRow").on("click",function(){
//     $(".highlight").removeClass("highlight");
//    $(this).addClass("highlight");
//    
//    });
//});




const searchRoutes = async (from, to, departure, wait_time, airline) => {
    selectedRoute=null;
    if (from == null || from.length == 0 || to == null || to.length == 0 || departure == null || departure.length == 0)
        alert("Please fill From and To cities and Departure time in order to search for routes.")
    else {
        savePreferences(from, wait_time, airline);
        var date2 = addHoursToDate(new Date(departure), 1); //central europe time
        var instant = date2.toISOString();
        var url = 'http://localhost:8080/user/routes?from=' + from + "&to=" + to + "&departure=" + instant
        if (wait_time != null && wait_time.length != 0)
            url += "&maxWait=" + wait_time
        if (airline != "All" && airline != null && airline.length > 0)
            url += "&airline=" + airline
        const response = await fetch(url);
        if (response.status >= 400) {
            alert("Searching for routes was unsuccessful. Please try again!")
        } else {
            //alert("Searching for routes was successful. Please choose an option!");

            routes = await response.json(); //extract JSON from the http response
            var results = document.getElementById("search-results");
            var table = "<h2 class=\"display-4 border-bottom probootstrap-section-heading\"><strong>Search Results</strong></h2><table id=\"infoTable\" class=\"table table-fixed table-condensed\"><thead><tr><th class=\"col-xs-3\">Path</th><th class=\"col-xs-3\">Airline</th><th class=\"col-xs-6\">FlightID</th><th class=\"col-xs-6\">Departure</th><th class=\"col-xs-6\">Arrival</th></tr></thead><tbody id=\"tbody\"></tbody></table>"
            results.innerHTML=table
            var completelist = document.getElementById("tbody");
            completelist.innerHTML = ""
            // completelist.innerHTML = "<h2 class=\"heading mb-2 display-4 font-light probootstrap-animate\">Search results</h2>";
            var counter = 0;

            routes.forEach(element => {
                var path = "";
                var departureTime = ""
                var arrivalTime = ""
                var airline = ""
                var flightId = ""
                element.flights.forEach(flight => {
                    path += flight.fromCity + " -> " + flight.toCity + "</br>"
                    airline += flight.airline + "</br>"
                    flightId += flight.flightId + "</br>"
                    departureTime += flight.departureTime + "</br>"
                    arrivalTime += flight.arrivalTime + "</br>"
                    //onclick=\"select(" + counter + ", \"" + id + "\")\"

                });
                var id = "id" + counter;
                var arrivalTime;
                //if (element.flights.length > 0) {
                //    var lastFlight = element.flights[element.flights.length - 1];
                //    path += lastFlight.toCity
                //    arrivalTime = lastFlight.arrivalTime
                //}
                var string = "<tr id=\"" + id + "\" class=\"clickableRow\" onClick='select(" + counter + ",\"" + id + "\")' ><td class=\"col-xs-3\">" + path + "</td> <td class=\"col-xs-3\">" + airline + "</td> <td class=\"col-xs-6\">" + flightId + "</td> <td class=\"col-xs-6\">" + departureTime + "</td> <td class=\"col-xs-6\">" + arrivalTime + "</td> </tr>";
                console.log("string: " + string)
                completelist.innerHTML += string;
                counter++;

            });
            if (routes.length > 0) {
                results.innerHTML += "<div class=\"col-md\"><input type=\"submit\" value=\"Buy\" onclick=\"buyRoute()\" class=\"btn btn-primary btn-block\" /></div>"

            }
        }


    }


    // do something with routes
}

function select(i, id) {
    console.log("id: " + id)
    var row = document.getElementById(id);
    console.log(row.id)
    $(".highlight").removeClass("highlight");
    row.classList.add("highlight");
    selectedRoute = routes[i];
    //console.log("Hello")


    // console.log("Routes: "+JSON.stringify(routes, null, 2));
    console.log("selectedRoute: " + JSON.stringify(selectedRoute, null, 2));
    // buyRoute(route);
}

const buyRoute = async () => {
    var jsonBody = JSON.stringify(selectedRoute);
    console.log("Route: " + jsonBody);
    //console.log(route);
    if (current_user == null || current_user.token == null)
        alert("You need to login to reserve a flight")
    else {
        const response = await fetch('http://localhost:8080/user/reserve', {
            method: 'POST',
            body: jsonBody, // string or object
            headers: {
                'Content-Type': 'application/json',
                'token': current_user.token
            }
        });
        if (response.status >= 400) {
            alert("Reserving the route was unsuccessful. Please try again!")
        } else {
            alert("Reserving the route was successful. You'll be redirected to the site where you can finish your purchase!");
            window.location.assign("https://www.example.com");
        }
    }

    // const myJson = await response.json(); //extract JSON from the http response
    //alert(myJson);
    // do something with myJson
}