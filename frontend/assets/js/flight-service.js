const login = async (email, password) => {
	const login_response = await fetch('http://localhost:8080/user/login', {
        method: 'POST',
        body: {
			user: {
				'email': email,
				'password': password
			}
		},
        headers: {
            'Content-Type': 'application/json'
        }
    });
	var user = await login_response.json();
	// extract token and preferences
	var token = user['token'];
    const preferences_response = await fetch('http://localhost:8080/user', {
        method: 'GET',
        body: {},
        headers: {
            'Content-Type': 'application/json',
            'token': token
        }
    });
    var preferences = await preferences_response.json();
    document.getElementById('from').value = preferences['departure'];
    document.getElementById('wait_time').value = preferences['transferTime'];
    document.getElementById('airline_select').value = document.getElementById(preferences['airline']);
}

const register = async (email, password, confirm_password) => {
	if (password == confirm_password){
		const response = await fetch('http://localhost:8080/user/register', {
			method: 'POST',
			body: {
				user: {
					'email': email,
					'password': password
				}
			},
			headers: {
				'Content-Type': 'application/json'
			}
		});
	}
}

//function searchRoutes(from, to, departure, wait_time, airline) {
//  //  alert(from + " " + to + " " + departure + " " + wait_time + " " + airline)
//  var date = new Date(departure);
//  var instant = date.toISOString();
//    console.log(instant)
//    console.log(from + " " + to + " " + departure + " " + wait_time + " " + airline)
//}
//var id;
var routes;
var selectedRoute;

const searchRoutes = async (from, to, departure, wait_time, airline) => {
var instant = new Date(departure).toISOString();
    //console.log(instant)
    //console.log(from + " " + to + " " + instant + " " + wait_time + " " + airline)
    const response = await fetch('http://localhost:8080/flight/routes?from='+from+"&to="+to+"&departure="+instant+"&maxWait="+wait_time+"&airline="+airline);
    routes = await response.json(); //extract JSON from the http response
    var completelist= document.getElementById("routes");
    var counter = 0;
    routes.forEach(element => {
        var item = "";
        element.flights.forEach(flight => {
            item = item + flight.fromCity+" => ";
        });
        item+= element.flights[element.flights.length - 1].fromCity;
        var string1 = "<input type=\"radio\" value=\"Value"+counter+"\" name=\"RadioInputName\" id=\"id"+counter+"\"/>";
        //console.log(string1);
        var string2 = "<label class=\"list-group-item\" onclick=\"select("+counter+")\" type=\"radio\" for=\"id"+counter+"\">"+ item + "</label>";
        completelist.innerHTML += string1;
        completelist.innerHTML += string2;
        counter++;
    });
    //var jsonString = JSON.stringify(routes).replace("\"","\\\"");
    //console.log(jsonString);
    completelist.innerHTML += "<div class=\"col-md\"><input type=\"submit\" value=\"Buy\" onclick=\"buyRoute()\" class=\"btn btn-primary btn-block\"></div>;";
    

    // do something with routes
}

function select(i){
    selectedRoute = routes[i];
   // console.log("Routes: "+JSON.stringify(routes, null, 2));
    console.log("selectedRoute: "+JSON.stringify(selectedRoute, null, 2));
   // buyRoute(route);
}

const buyRoute = async () => {
    var jsonBody = JSON.stringify(selectedRoute);
    console.log("Route: "+jsonBody);
   //console.log(route);
    const response = await fetch('http://localhost:8080/flight/reserve', {
        method: 'POST',
        body: jsonBody, // string or object
        headers: {
            'Content-Type': 'application/json',
            'token':'admin' //TODO
        }
    });
   // const myJson = await response.json(); //extract JSON from the http response
    //alert(myJson);
    // do something with myJson
}