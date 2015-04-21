# MyCircleLine

MyCircleLine is a small web application that provides live information about trains running on the London Circle Line network.

It is a useful tool for those commuters who rely primarily on the Circle Line to travel to and from work.  A typical example would be a City worker waiting for a train at Liverpool Street station and heading to Victoria


## Open the app
The app is accessible at  **[jule64.github.io/MyCircleLine](http://jule64.github.io/MyCircleLine)**


## How it works
MyCircleLine has two components: a web client and a Java back end.

### Web Client
a user accesses the url above and clicks on a station name, say Victoria station, to requests information about upcoming trains for that station.

The user request is captured in a **JQuery** object which sends an AJAX request to the Java back end and receives a JSON response.  The JSON response is then processed in a callback function and populated in the webpage.

**Bootstrap** is used for rendering UI components

### Back End
The back end itself has two responsibilities: 
      1. serve user requests and 
      2. retrieve train information from the TFL cloud service and parse the data into Java objects for use in 1.

These two responsibilities run independently on two separate threads.  For 1. we use the **Spring framework** to channel user http requests to the relevant controllers and send responses back.  Spring also automatically convert responses from Java objects into JSON.

The TFL data meanwhile is polled at regular interval and parsed from XML using standard XML DOM parser to populate the application's internal objects.  This last process is repeated every 30 seconds (maximum allowed polling frequency by the cloud service).

### Hosting
The front end is hosted for free here on GitHub while the backend sits on Amazon EC2, which also means that we get to use **CORS** for our cross site requests. just to make things a little more interesting :)


## Happy browsing :D



## TODOs

### Client
* Look at having users last selection persisted between sessions (might need back end work)
* create mcl icon (done)
* tidy up html (in progress)
* remove trailing dots from stations names - back end work
* if train is 0 min change message to "train at platform" instead of current message "in - min"
* use favico.js to add update animation to mcl favicon (see http://lab.ejci.net/favico.js/)
use textillate to animate text (http://jschr.github.io/textillate/) 


### Back End
* run a concurrency test with several polling clients and parser updates
* save stations list in separate list to avoid hitting the stations map
