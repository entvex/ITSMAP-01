# Assignment-2-Weather-or-not-you-like-it
E16 - ITSMAP-01 Smartphone applikationer Assignment 2: "Weather or not you like it"

 <p><b>Assignment 2:</b></p> 
 <p><i>Note: &nbsp;This assignment covers the topics learned in UI, storage/databases, service, asynchronous processing and networking through HTTP – all in alignment with the learning goals of the course. You will be able to use a lot of the knowledge gained from the lab exercises you did in these modules as well.</i></p> 
 <p>In this assignment you will be creating a weather <span><span><span><span>app</span></span></span></span> – specifically we are interested in the weather in Aarhus.</p> 
 <p>The <span><span><span><span>app</span></span></span></span> will use a free <span><span><span><span>online</span></span></span></span> web service called <span><span><span><span>OpenWeatherMap</span></span></span></span> (see instruction below) to download recent weather data in JSON format. Due to limitations for the free version of this API, you can only download the current weather (not historic weather or forecasts). It is nice to see how the weather has been in the past day, so you will need to write a background service that calls the server every 30 minutes and saves the weather data in a database on the device. This service will be running in the background at all times. This way you will have historic weather data available. You also need to create a simple UI that shows the current weather, and the weather for the past 24 hours. The pictures below shows a mock of the UI and an idea for an architecture for your <span><span><span><span>app</span></span></span></span>.</p> 
 <p><a alt="" target="_blank" href="https://bb.au.dk/bbcswebdav/pid-620409-dt-content-rid-1296790_1/xid-1296790_1">
 <img src="https://github.com/entvex/Assignment-2-Weather-or-not-you-like-it/blob/master/ImageForGitHubReadMe/weatherUIwireframe(1).jpg" width="600" height="800"> </a></p> 
 <p><img src="https://github.com/entvex/Assignment-2-Weather-or-not-you-like-it/blob/master/ImageForGitHubReadMe/weatherArchitectureDraft.jpg" width="600" height="450"></p> 
 <p>Your solution must have three main components and talk to an external web server:</p> 
 <ol> 
  <li>An Activity: the Activity will use the Service to get up-to-date data and show it to the user. It should also allow the user to refresh and check for new data manually.</li> 
  <li>A database: a small database and functionality to store weather information entries (hint: create a <span><span><span><span>DatabaseHelper</span></span></span></span> class). The database will be used by the Service.</li> 
  <li>A Service: this should be a Background service that manages the database AND periodically retrieves new weather information from the web service. As such the Service should always have the newest data and the Activity can get it when needed.</li> 
 </ol> 
 <p><strong>Further details on requirements for assignment:</strong></p> 
 <ul> 
  <li>To communicate between the service/activity and service/database you must use a <span><span><span><span>WeatherInfo</span></span></span></span> “model” class that holds AT LEAST the following information: 
   <ul> 
    <li style="margin-left: 72.0pt;">ID (corresponding to database entry)</li> 
    <li style="margin-left: 72.0pt;">Weather description (text)</li> 
    <li style="margin-left: 72.0pt;">Temperature (in <span><span><span><span>celcius</span></span></span></span>)</li> 
    <li style="margin-left: 72.0pt;"><span><span><span><span>Timestamp</span></span></span></span> (when the data is from)</li> 
   </ul> </li> 
  <li>The activity should show the current weather in whatever way you want to present it (above diagram could be a starting point), but it should visualize all the information from the <span><span><span><span>WeatherInfo</span></span></span></span> class.</li> 
  <li>The activity should show the weather data for every 30 minutes from up to 24 hours in the past. You should use a <span><span><span><span>ListView</span></span></span></span> with an <span><span><span><span>Adaptor</span></span></span></span> for this. You can use an existing <span><span><span><span>adaptor</span></span></span></span> or create a custom <span><span><span><span>adaptor</span></span></span></span> that takes a list of <span><span><span><span>WeatherObjects</span></span></span></span> with custom XML layout.</li> 
  <li>The background service should run all the time (from the first time the <span><span><span><span>app</span></span></span></span> is started)</li> 
  <li>The Activity bind to the service when active and retrieve the most up-to-date weather data through two methods, like these: 
   <ul> 
    <li style="margin-left: 72.0pt;"><span><span><span><span>WeatherInfo</span></span></span></span> <span><span><span><span>getCurrentWeather</span></span></span></span>()</li> 
    <li style="margin-left: 72.0pt;">List&lt;<span><span><span><span>WeatherInfo</span></span></span></span>&gt; <span><span><span><span>getPastWeather</span></span></span></span>()</li> 
   </ul> </li> 
  <li>The Service should send out a local broadcast when there is new weather data available. The Activity should register for this, and update the UI if needed.</li> 
  <li>The background service must call the <span><span><span><span>OpenWeatherMap</span></span></span></span> server every 30 minutes and save the results in the database.</li> 
  <li>You must have a custom icon for your <span><span><span><span>app</span></span></span></span></li> 
  <li>You <span><span><span><span>app</span></span></span></span> name should be “Weather Aarhus group XX” where XX is your group number.</li> 
  <li>You should include <span><span><span><span>LogCat</span></span></span></span> outputs / logging to validate that the service is running</li> 
  <li>All resources used should be externalized</li> 
  <li>Style the <span><span><span><span>app</span></span></span></span> with your own colors</li> 
 </ul> 
 <p>&nbsp;</p> 
 <p>&nbsp;</p> 
 <p><strong>Bonus:</strong></p> 
 <ul> 
  <li>Make the service run even if the user reboots the device</li> 
  <li>Additional weather information (check out the API) and <span><span>visualization</span></span></li> 
  <li>Use different icons to represent the weather situation (rain, snow, sun, cloudy, etc.)</li> 
  <li>Different layouts, e.g. for large and small devices</li> 
  <li>Allow the user to select other cities</li> 
  <li>…</li> 
 </ul> 
