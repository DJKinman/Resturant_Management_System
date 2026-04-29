# Restaurant Management System
Final Project for CSCI 2210 - Java Programming

## Team Members
David Kinman, Khushi Amipara, Keerthana Kotha, Kendall Clark

## Summary of Features
- User Login
- Encryption and Decryption
- User Registration
- Add items to current order
- Remove items from current order
- Complete order and display receipt
- Tab switching for different menu items
- View, delete, and edit past orders

## Explanation of Class Design
The project was based off a point-of-sale system that would be used in a restaurant

***PointOfSale*** - The base abstract class of the program.
***LoginScreen*** - The starting point of the program, its purpose is login and register new users. Apon valid login attempt, the user is brought to MainMenuScreen, which acts as the main menu
***PointOfSale*** - The core abstract class of the program. Most of the other screens inherit from it. It owns shared UI features like the current food area and current food order as static members.
***MainMenuScreen*** - Extends PointOfSale and acts as the landing page. It lets the user eithe view and edits all past orders, or create a new order
***ViewAllOrdersMenu** - Extends PointOfSale and allows the user to view, edit, or delete a previous order
***GeneralMenuTab***, ***KidsMenuTab***, ***DrinksMenuTab*** - All extend PointOfSale. They display buttons to add items to the current order of a specific tab.
***OrderArea*** - self-contained UI element that holds a specific FoodItem added to the current order to be displayed along with a delete button
***User*** - Holds the information for each individual user object
***UserList*** - Class that handles all user related activities like logging in, registering a new user, and validating user
***FoodItem*** - Holds the information for each Food Item object
***FoodOrder*** Tracks the items in the current order, and methods to add, remove, and to sort the orders. It also 
***CeasarCipher*** provides methods to encrypt and decrypt user credentials

## Use of Inheritance, polymorphism, abstract classes, interface, and static members
Static members were used for variables that were shared between classes. One example was in PointOfSale, the currentOrder ArrayList is accessed by its children and not a copy of it.
The abstract class was used as the base of the menu GUI to add to different orders. This is because the same base layout of the class was used, but in different ways.
An interface was used because of the same functionality of file writing to and reading from a file, but the implementation is different and needs different files so it is an interface.


## Authentication Approach
For use authentication, we first ask the user to enter their email. The email they entered is case sensitive as specified. The email entered is checked against a basic regex to make sure it ends in @gmail.com. Then the user enters their password. If the email and password entered are both equal to a registered user, the current user is logged in. To register a new user, first the email is checked against all registered emails. If there is a match, then the user cannot register a new user with that email. Both the users email and password are encrypted with a basic Ceasar Cipher inside of "RegisteredUsers.txt"

## File handling approach
If the file requested to be accessed is unable to be found, a runtime exception is thrown

## Challenges faced
One challenge faced was getting the user data encryption to work. It took a while for us to get it right

Another challenge faced during development was getting the GUI to work. It was difficult to get all the classes to work together. One main reason was passing the scene between the classes, so a new window wasn’t created. It was difficult to figure out a way to properly do that.
Another reason why it was hard to develop the GUI was due to the difficulty of adding Tabs and making sure they worked properly. It took a while to make sure the Tabs all displayed their proper menu items and that the buttons worked as they were supposed to.
