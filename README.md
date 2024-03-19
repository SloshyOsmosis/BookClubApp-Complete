This is the ReadMe file for my Book Club App created in Android Studio.

DISCLAIMER – This application was developed in Android Studio Jellyfish using a Kotlin Gradle. May be out of date for older Android Studio builds!
TargetSDK/CompileSDK – 34
Gradle Version – 8.6
Android Gradle Plugin Version – 8.4.0-alpha12 (got too far into the project to change(sorry))

##Overview
This project is a mobile application developed using Android Studio, it is a platform for users to upload and categorise their books based on whether they are reading, finished reading and want to read.

##Features
1. Users can create accounts and login, with the option to delete their account and update password.
2. Users have access to the “shelves” page. Here they can categorise their books as they wish based on reading or want to read.
3. Search page allows the users to filter through their submitted books with a search bar.
4. Profile customisation such as the ability to change profile picture to stand out.
5. The ability to delete books from their lists.
6. Update books, the user can change any of the book values and move books between their shelves.
7. Access to a chatroom.

##Installation
1. Clone the repository to your local machine.
2. Open the project in android studio.
3. Connect to a physical android device or emulator.
4. Build and run the application.

##Technologies used
- Android Studio
- Android Layout Inspector
- Java
- XML
- Firebase Test Lab
- Material Design Components
- SQLite (Database handling)
- Espresso

##Layout validation criteria
- Alignment of user interface elements to ensure consistent user experience across devices.
- Margins and paddings.
- Text size, text colour and font consistency.
- Colour schemes compatible with readable text.
- Handling of different screen sizes and orientation to improve overall usability of the book club app.

##FireBase Test Lab results




##Challenges

One of the first challenges I encountered during the development of my application was updating the books in my database. I would make it as far as the update book activity without the changes made to the books being applied. This was because I was missing the “book” class in my project. The aim of this class was to encapsulate the book attributes and to provide methods to access and modify them in the form of setters and getters. Thanks to this new book class that was added later down the line I was able to update books in the database with ease. This challenge was one of the main reasons I had to take an extension for this project as I truly felt like I hit a brick wall at the time. Thankfully I persevered!

Another challenge I was faced with was displaying the recycler views in the shelves section. I wanted to categorise the books into different recycler views based on their “Status”. The statuses are Read, Reading and want to read. The challenge was figuring out how I could categorise the books into 3 different recycler views without having the books being displayed on all 3. To tackle this challenge I implemented an if statement depending on the status of the book before displaying the data on the recycler view. On each shelf fragment I implement this if statement for all 3 statuses which fixed my problem.

Another challenge I encountered was implementing a functional search view for the search fragment. That aim of this search view was to display all the books stored in the database in a list and to be able to search the list and filter by Title characters. To address this issue I used the same method to display the books I used on the shelves fragment without the status filter. This meant that all books would be displayed no matter the status. Implementing the search view was a bigger challenge because I had to implement a new method in my DBHelper that allows for searching the library database based on a partial match of the title.

##Acknowledgements

Special thanks to ‘N’ for drawing the custom logo and app icon. Also known as “little fella”. Thank you for being there for me since day 1.

##References

Icons - https://www.flaticon.com/

GfG. (2022, August 17). SearchView in Android with RecyclerView. GeeksforGeeks. https://www.geeksforgeeks.org/searchview-in-android-with-recyclerview/
How to Read Data from SQLite Database in Android? - GeeksforGeeks. (n.d.). https://www.geeksforgeeks.org/how-to-read-data-from-sqlite-database-in-android/amp/


