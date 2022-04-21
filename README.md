# The great movie App to Actia

This project consist in two screens, the first show a list of 20 movies obtains from an api.
When we click on a list item, we are taken to the second screen, where movie details are display.

The used API for this project is [TheMovieDatabase](https://developers.themoviedb.org/3). Two endpoint are being used, `discover/movie`, to get a list of movies based on average rating, likes, etc. And `movie/{id}` to get some more details about the movie. 

Also, the App treat errors scenarios, and loading scenarios, given the user the option to try again. 

### Some of the tecnologies used in the App:
* Hilt to depency injection
* Navigation Compents and Safe Args to handle the app Navigation
* Retrofit to handle the api requests.
* Glide to provide a nice image loader. 
* Coroutines to handle the asyncronos tasks.
* Lifecycle librarys
* ViewBinding to access the view elements of screens

### How to run the project
The App was written using the Android Studio Bumblebee Version 2021.1.1 patch 2, with the kotlin in the 1.6.20 version.
Using the built-in 11 Java version SDK.
The used gradle version is also the built-in on the Android Studio Version.

The app can be executed from the master branch.

## The project link
https://github.com/geanbrandao/TheGreatMovieAppActia
