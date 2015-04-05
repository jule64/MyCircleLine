# MyCircleLine.co.uk

mycircleline.co.uk is a small web application that provides live information about trains running on the London Circle Line network!

It is a useful tool for those commuters who rely primarily on the Circle Line to travel to and from work.


## How it works
mycircleline.co.uk has two components: a web front end and a Java back end powered by the Spring framework.

### Front End
a user accesses **[mycircleline.co.uk](http://mycircleline.co.uk)** and requests information about upcoming trains on a given station, say Sloan Square station.

The user request is captured in a **JQuery** object which sends an AJAX request to the Java back end and receives a JSON response.  The JSON response is then processed in a callback function and populated in the webpage.

**Bootstrap** is used for rendering UI components

### Back end
The back end itself has two responsibilities: 
      1. serve user requests and 
      2. retrieve train information from the TFL cloud service and parse the data into Java objects for use in 1.

These two responsibilities run independently on two separate threads.  For 1. we use the **Spring framework** to channel user http requests to the relevant controllers and send responses back.  Spring also automatically convert responses from Java objects into JSON.

The TFL data meanwhile is polled at regular interval and parsed from XML using standard XML DOM parser to populate the application's internal objects.  This last process is repeated every 30 seconds (maximum allowed polling frequency by the cloud service).

### Hosting
The front end is hosted for free here on GitHub while the backend sits on Amazon EC2, which also means that we get to use **CORS** for our cross site requests. just to make things a little more interesting :)


## Happy browsing :D



## TODOs

### git
* put client project in own project for git page hosting


### Client
* Look at having users last selection persisted between sessions (might need back end work)
* create mcl icon
* tidy up html
* remove trailing dots from stations names


### Back End
* Access to the `stations` map not currently thread safe - need fix
* save stations list as own list instead of hitting the stations map
