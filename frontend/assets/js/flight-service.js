//function searchRoutes(from, to, departure, wait_time, airline) {
  //  alert(from + " " + to + " " + departure + " " + wait_time + " " + airline)
//}

//const searchRoutes = async (from, to, departure, wait_time, airline) => {
//    console.log(from + " " + to + " " + departure + " " + wait_time + " " + airline)
//    const response = await fetch('localhost:8080/flight/routes?'+from+"/"+to+"/"+departure+"/"+wait_time+"/"+airline);
//    const myJson = await response.json(); //extract JSON from the http response
//    alert(myJson)
//    // do something with myJson
//}

function searchRoute(from, to, departure, wait_time, airline){
    console.log(from + " " + to + " " + departure + " " + wait_time + " " + airline)

   let request = new XMLHttpRequest();
   request.open("GET", "http://localhost:8080/flight/routes?departure=2021-11-17T17%3A08%3A23Z&from=Budapest&maxWait=120&to=Wienna")
   request.send();
   request.onload = () => {
       console.log(request);
       if(request.status == 200){
           console.log(JSON.parse(request.response));
       }else{
           console.log("error")
       }
   }
    alert(myJson)
    // do something with myJson
}

const userAction = async () => {
    const response = await fetch('http://example.com/movies.json', {
        method: 'POST',
        body: myBody, // string or object
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const myJson = await response.json(); //extract JSON from the http response
    // do something with myJson
}