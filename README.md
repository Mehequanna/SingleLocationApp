## Project Name
Single Location App

## Description
This is a proof of concept app that records the user's location. It will display the user's longitude, latitude, accuracy, and altitude to the screen on a button push.

## Technologies
Java and Android

## Usage
To use the code, you can clone the repository at [https://github.com/Mehequanna/SingleLocationApp](https://github.com/Mehequanna/SingleLocationApp).

1. Clone repo from github.
2. Import project into Android Studio from the startup screen.
3. For best results, run on a real device.
Note: An emulator running api 25 or higher should also work. The issue is the emulator's GPS doesn't move naturally, so it may not detect a location change. You can try to manually change the emulator's GPS location, just know it will take a little bit for the phone to register the change and update the activity.

## Test
There is a single test class that tests the CustomLocationListener singleton method in LocationActivity.java.
1. To run it, find CustomLocationListenerUnitTest.java in the test package or more specifically
app/src/test/java/com/mehequanna/singlelocationapp/CustomLocationListenerUnitTest.java
2. Right click on the file and choose Run from the drop down menu.

## Author
Stephen Emery

## License
This work can be used under the MIT License.
Copyright (c) 2018 Stephen Emery
