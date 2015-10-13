# MyCircleLine

MyCircleLine is a small web application that provides live information about trains running on the London Circle Line network.

It is a useful tool for those commuters who rely primarily on the Circle Line to travel to and from work.  A typical example would be a City worker waiting for a train at Liverpool Street station and heading to Victoria


## Open the app
The app is accessible at  **[julienmonnier.com/MyCircleLine](http://julienmonnier.com/MyCircleLine)**


## How it works
MyCircleLine has two components: a web client and a Java back end.

### Web Client
A user accesses the url above and clicks on a station name, say Victoria station, to requests information about upcoming trains for that station.

Under the hood the user request is captured in a **JQuery** object which sends an **AJAX** GET request to the Java back end and receives a **JSON** response containing the upcoming train times.  The train times are then populated in the webpage using a callback function.

**[Bootstrap](http://getbootstrap.com/)** is used for rendering the UI components which allows the app to work nicely on mobile devices.

### Back End
The back end is build in Java and has two responsibilities:
      1. serve http requests coming from the client
      2. retrieve train information from the TFL cloud service and create the trains and stations objects.

These two responsibilities run independently on two separate threads.  We use the **Spring MVC framework** is used to channel the user requests to the relevant controllers and send responses back.  Spring also automatically converts responses from Java objects into JSON.

The second thread meanwhile parses the TFL data at regular interval intervals using an XML DOM parser and populates the data into the application's object model.  This last process is repeated every 30 seconds (maximum allowed polling frequency by the TFL cloud service).

### Hosting
The front end is hosted for free [GitHub](https://github.com/) while the backend sits on an Amazon EC2 server (well actually on my Macbook for now..).  The separation of client hosting and server means that we use **CORS** to do our cross site requests, just to make things a little more interesting :)






## TODOs

### Client
* Look at having users last selection persisted between sessions (might need back end work)
* create mcl icon (done)
* tidy up html (in progress)
* remove trailing dots from stations names - back end work
* if train is 0 min change message to "train at platform" instead of current message "in - min"
* use favico.js to add update animation to mcl favicon (see http://lab.ejci.net/favico.js/)
use textillate to animate text (http://jschr.github.io/textillate/) 
* turn client app into an AngularJS app


### Back End
* run a concurrency test with several polling clients and parser updates
* save stations list in separate list to avoid hitting the stations map
