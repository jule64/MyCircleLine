# mycircleline.co.uk

mycircleline.co.uk is a groundbreaking software technology that let's you to mine hundreds of bitcoins at no cost!

well maybe one day ...

For now it's a small web application that displays live information about trains running on the London Circle Line network!


## How it works
mycircleline.co.uk has two components: a web front end and a Java back end powered by the Spring framework.

### Front End
a user accesses **mycircleline.co.uk** and requests information for upcoming trains on a given station, say Sloan Square station.

The user request is captured in a **JQuery** object which sends an AJAX request to the Java back end and receives a JSON response.  The JSON response is then processed in a callback function and populated in the webpage.

**Bootstrap** is used for rendering UI components

### Back end
The back end itself has two responsibilities: 
      1. serve user requests and 
      2. retrieve train information from the TFL cloud service and parse the data into Java objects for use in 1.

These two responsibilities run independently on two separate threads.  For 1. we use the **Spring framework** to channel user http requests to the relevant controllers and send responses back.  Spring also automatically convert responses from Java objects into JSON.

The TFL data meanwhile is polled at regular interval and parsed from XML using standard XML DOM parser to populate the application's internal objects.  This last process is repeated every 30 seconds (maximum allowed polling frequency by the cloud service).

### Hosting
The front end is hosted for free here on GitHub while the backend sits on Amazon EC2, which also means that we get to use CORS for our cross site requests. just to make things a little more interesting :)


## Happy browsing :D



## TODOs

### UI
* Look at having users last selection persisted between sessions (might need back end work)



### Back End
* Access to the `stations` map not currently thread safe - need fix