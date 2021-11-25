
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
        //document.getElementById("modal-toggle").close
        //$('#modal-toggle').modal('hide');
    }
    current_user = await login_response.json();
    console.log("deprature: " + current_user['departure'])
    console.log("transferTime: " + current_user['transferTime'])
    console.log("airline: " + current_user['airline'])
    document.getElementById('probootstrap-date-departure').value = new Date(Date.now()).toLocaleDateString();
    document.getElementById('from').value = current_user['departure'];
    document.getElementById('wait_time').value = current_user['transferTime'];
    document.getElementById(current_user['airline']).selected = true;
    //document.getElementById('airline_select').value = document.getElementById(current_user['airline']);
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
    var json = {
        'departure': from,
        'transferTime': wait_time,
        'airline':airline
    }
    console.log("token: "+current_user.token);
    const login_response = await fetch('http://localhost:8080/user', {
        method: 'POST',
        body: JSON.stringify(json),
        headers: {
            'Content-Type': 'application/json',
            'token': current_user.token
        }
    });
    if (login_response.status >= 400) {
        alert("SavePreferences was unsuccessful. Please try again!")
    } else {
        alert("SavePreferences was successful.");
        //document.getElementById("modal-toggle").close
        //$('#modal-toggle').modal('hide');
    }
}



const searchRoutes = async (from, to, departure, wait_time, airline) => {
    savePreferences(from, wait_time, airline);
    var instant = new Date(departure).toISOString();
    //console.log(instant)
    //console.log(from + " " + to + " " + instant + " " + wait_time + " " + airline)
    const response = await fetch('http://localhost:8080/flight/routes?from=' + from + "&to=" + to + "&departure=" + instant + "&maxWait=" + wait_time + "&airline=" + airline);
    if (response.status >= 400) {
        alert("Searching for routes was unsuccessful. Please try again!")
    } else {
        alert("Searching for routes was successful. Please choose an option!");

        routes = await response.json(); //extract JSON from the http response
        var completelist = document.getElementById("routes");
        completelist.innerHTML = "";
        var counter = 0;
        routes.forEach(element => {
            var item = "";
            element.flights.forEach(flight => {
                item = item + flight.fromCity + " => ";
            });
            item += element.flights[element.flights.length - 1].toCity;
            var string1 = "<input type=\"radio\" value=\"Value" + counter + "\" name=\"RadioInputName\" id=\"id" + counter + "\"/>";
            //console.log(string1);
            var string2 = "<label class=\"list-group-item\" onclick=\"select(" + counter + ")\" type=\"radio\" for=\"id" + counter + "\">" + item + "</label>";
            completelist.innerHTML += string1;
            completelist.innerHTML += string2;
            counter++;
        });
        //var jsonString = JSON.stringify(routes).replace("\"","\\\"");
        //console.log(jsonString);
        if(routes.length>0)
            completelist.innerHTML += "<div class=\"col-md\"><input type=\"submit\" value=\"Buy\" onclick=\"buyRoute()\" class=\"btn btn-primary btn-block\"></div>;";
    }


    // do something with routes
}

function select(i) {
    selectedRoute = routes[i];
    // console.log("Routes: "+JSON.stringify(routes, null, 2));
    console.log("selectedRoute: " + JSON.stringify(selectedRoute, null, 2));
    // buyRoute(route);
}

const buyRoute = async () => {
    console.log
    var jsonBody = JSON.stringify(selectedRoute);
    console.log("Route: " + jsonBody);
    //console.log(route);
    const response = await fetch('http://localhost:8080/flight/reserve', {
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
    }
    // const myJson = await response.json(); //extract JSON from the http response
    //alert(myJson);
    // do something with myJson
}