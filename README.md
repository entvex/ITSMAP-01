# ITSMAP-01
This is some of the android apps made in the course ITSMAP-01. 
Some of them are made in IntelliJ IDEA and others in android studio

| Project                                                                                        | Steps                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                | Status                    |
|------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------|
| 1: Hello Android                                                                               | 1a) Create a new project using the "empty activity" template - build the app and run it in the emulator. It should say "Hello World". If you have an Android device at hand, try to run it on the device as well. 1b) Add a Button with the label "Hello" to your GUI - when the user presses this button the TextView should change from "Hello World" to "Hello Android!" 1c) Try to experiment a bit with layout parameters, text properties etc. switching between the design and text/XML mode1d) Add a new Button labelled "Exit" - when the user presses this button the app should exit                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | Done                      |
| 2: Click counter                                                                               | 2a) Create a new project using the "blank activity" template - this should give you a nice material design based UI with a round FAB button 2b) We want to keep track of how many times the button is pressed and display it to the user in a TextView - add this behavior to the activity. Add a variable in the Activity that keeps count and update the text on the screen every time the button is pressed. 2c) What happens when you rotate the device? Why? Can we fix this?                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | Done                      |
| 3: Welcome to Hollywood                                                                        | 1.Create a new blank application with one MainActivity 2. Overwrte ALL the main life-cycle events of your main Activity and add a Log entry that matches the event, e.g.: Log.d("LifeCycle", "onStop() called");, 3. Use LogCat, the integrated logging system, to monitor the life-cycle events of your app as you rotate your device/emulator - what is the sequence of calls? 4.Try using the debugger as well                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    | Done                      |
| 4: Selifie Swap Arnie Edition                                                                  | 1. Create a new application 2. Build a basic user interface with two ImageViews and a button called "swap". 3. Add some pictures of Arnie (or some other iconic images),in the ImageViews 4. Now make the pictures switch every time you press the button 5. Make sure the app remembers the order of pictures,when you rotate the device                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | Done                      |
| 5: There and back Again                                                                        | 1. Create a new application with two activities: MainActivity and SecondActivity 2. Create a Button in you MainActivity user interface that takes the user to SecondActivity. 3. SecondActivity must have a Button where the user returns to the MainActivity                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | Done                      |
| 6. Post-It                                                                                     | 1. Create a new application with two Activities: ViewActivity and EditActivity 2. ViewActivity should have an "edit" Button that starts the EditActivity 3. EditActivity should have an EditText input field where the user can type some text and a button called "OK", which then sends this data back to the first Activity 4. ViewActivity should have a TextView that is initially empty - after having edited something the latest data entry from the EditActivity should be in this TextField 5. EditActivity should also have a "Cancel" button that takes the user back but does not update the TextView in ViewActivity                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | Done                      |
| 7. Good Intentions                                                                             | 1. Create a new application with just one Activity 2. Continue to add new buttons to the interface that invokes various actions on the Android interface (note: someof these might behave different whether you have a physical device or using emulator).For instance:Take a picture with the cameraRecord a VideoSend an emailSend an SMSStart a phone callSet an Alarm                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | Done                      |
| 8. One app to rule them all... plus One place to view them all, and in the ListView bind them! | 1a: "Be Picky" ( Let the user choose a number between 0 and 1000 using a NumberPicker controlAdd an OK and Cancel buttonsIf the user presses OK, return the int value chosen ) 1b: "EditText is mightier than the Sword" ( Create a number of different EditText input fields and set them to specific data types: plain text, email, number and password.Add an OK and Cancel buttonsIf the user presses OK, return the values of all EditTexts ) 1c: "Let it Slide (rainbow edition)" ( Create three Sliders and label them Red, Green and Blue (with TextViews)Configure the sliders to hold values from 0-255Handle the proper events in your Activity when the user changes the values of the slidersOn every update, use the slider values to change the background color of the root layout to the corresponding RGB color (hint: Color.rgb(int, int, int) )Add OK and Cancel buttonsIf user presses OK, return the RGB values as a String ) Plus (Create some type of List holding meta information about your UI demos (e.g. Name, icon, and String names of implicit Intents to start them)Add a listview to your MainActivityCreate a custom Adaptor with a specific Layout for your demosPut it together to get a scrollable list showing all your demosAdd functionality so that when the user presses on a demo in the list it should start that particular Activity ) | Done                      |
| 9. One Menu action to find them                                                                | 1. add a menu item to your app with a search icon or text saying "search" 2. when the user presses this menu, a dialog should be shown where the user can enter a text String and two buttons: "cancel" and "search" 3. if the user presses search, show a Toast saying "now searching for X" where X is the text the user put in the dialog                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         | Done                      |
| 10. Run for the Framework                                                                      | 1. Create a new app 2. This time explore the master-detail template offered by Android Studio 3. Port the model / data objects from part 1 and try to adapt this solution to the master-detail setup                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | Done                      |
| Assignment 1: "I, Android"                                                                     | "A1": An activity for viewing an Android developer profile, with some basic info like (note the different data types),(A) name (String),(B) id number (int),(C) developer status (boolean) "A2": An activity for editing the profile values You need to send data between these Activities so that they are always synched, i.e. update A1 after information has been edited in A2. Also the text edit fields in A2 should be pre-populated with the current values from A1. The user should be able to choose "ok" or "cancel" to the edits made in A2 and then return to A1 Furthermore the user must be able to take a picture/selfie and add it to the profile as a thumbnail (D). This should be done using any Camera app installed on the device already. To take the picture, the user must click the image in the interface (when no picture is added, just show a placeholder)You must use resource externalization throughout the app. You app should support at least two languages for all labels, buttons, etc. English must be one of them. You will need to handle both portrait and landscape modes and optimize the layout of information in accordance with the sketch below. You need to handle orientation changes so the user does not loose any data. You should re-style the app with your own Material Design colors You app must have a custom icon        | Done                      |
| 11. Memento: Who, me?                                                                          | This app will keep track of the users personal details, in case he/she forgets 1. Create a new app 2. Create an activity with a number of input fields for "firstname", "lastname", "age" and "phone number" 3. Used SharedPreferences to save the data that the user puts in - make sure to use the right Activity lifecycle methods so that updated data is saved when the app closes and loaded when theapp is started 4. Does this also solve the "lost state during rotation" problem?                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | Done                      |
| 12. All your database are belong to use                                                        | 1. Create a new app 2. Create an Activity with two text input fields: "task" and "place" and a button that will add a new tasks 3. Create a new DatabaseHelper class for managing your database - extend SQLiteOpenHelper and add appropriate methodsAdd your database schema with one table and three columns (id*, task_name, place_name) to the DatabaseHelper class (serving as contract class, use static final Strings for names and SQL statements) 4. Create a new model class / DAO called "Task" which holds task id (long), name (String) and place (String).  5. Add methods to your DatabaseHelper handling basic operations, and make your app save the task/place when the button is pressed e.g.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | Still needs a little love |
| 13. Background service - "I'll be BACK...ground                                                | 1. create new application with activity MainActivity 2. create a Service that should run as a background service 3. Create two buttons in your Activity that starts and stops the service 4. Create some type of loop or recursion that goes on as long as the service is alive, it should first a) sleep for X miliseconds (e.g. 20000), then b) send a LOCAL broadcast with some String message 5. First try just to Thread.sleep(...) for 20sec in the Service to simulate time consuming work that goes on in the background service,- do you get an error? 6. Then change it to use AsynchTask and Thread.sleep(...) 7. Your activity should register a BroadcastReceiver and print out the messages sent out by your service either through a Toast or other UI mechanism                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | Done                      |
| 14. Bound Service                                                                              | 1. create another Service and add a few state variables, e.g. int count = 42; 2. make this a bound Service, so that your activity can bind and unbind to it (add two new buttons for this) 3. Create a third button that calls the service directly (if bound) and retrieves the state; then prints it out                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Done                      |
| 15. Intent Service                                                                             | 1. Add a new IntentService using Android Studio and add the default example methods for Foo and Baz 2. Create two buttons in your activity that sends new Foo and Baz requests to your IntentService 3. Validate that the requests are put in the queue and executed in the right order (use LogCat printouts and add counters or timestamps)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | Done                      |
| 16. Tank, I Need an Operator                                                                   | 1. create a new application with a blank activity 2. add button that checks for your connectivity status and displays details in a Toast (hint use: ConnectivityManager) 3. Bonus: explore the kinds of data you can get from the ConnectivityManager                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                | Done                      |
| 17. JSON Bourne                                                                                | 1. Extend the app from part 1 with another Button and a Textview. Button should say "Get Aarhus Weather" 2.You should create the functionality to download the current weather for Aarhus using the OpenWeatherMap API (see seperate guide for help) or go to http://openweathermap.org/apiuse 3. HTTPUrlConnection for networking 4. make sure your call is asynchronous (e.g. Asynch Task) 5. Update the TextView to show the result from the call 6. Add another button called "Parse JSON" and a function that parses the last JSON response from the weather server to extract the current temperatur 7. use a tool like http://jsonmate.com/ to explore the JSON result from the server                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | Done                      |
