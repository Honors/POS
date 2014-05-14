#POS User Manual
This project is the Point-of-Sale software for the Eagle Store. The
core of the app is written in Java, with a backend SQL database.

##Table of Contents
[TOC]

##Logging In
The login screen asks for two fields, a *Username* and a *Password*.  If the login is successful, the **Home** screen should appear in a few seconds.  If not, there are a few possible errors:

| Error                           | Description |
| ------------------------------- | ----------- |
|  "Invalid logging destination"  | POS cannot reach the server's logging endpoint. |
| "Invalid Host directory or URL" | POS cannot reach the server's database. |
| "Invalid username or password"  | POS does not recognize the inputed *Username* or *Password* |

> **Note:** In the case of any failed login, please ensure you are connected to the correct network, using the most up to date version of the program, and using correct login credentials.

##Screens and Their Functions
This section will provide a quick description of each screen, its access, and its usage.

###Home Screen
 **Permission:** User

This is the landing screen for the whole POS program.  It provides access to every other screen. 

####Admin vs. User

Once logged in, a you are determined to be either a *Admin* or a *User*:

| Permission | Description |
| ---------- | ----------- |
| Admin      | An admin has access to all aspects of the program and can manipulate anything within the program.  This is primarily done through the access of the *Admin* menu towards the top of the screen in the menu bar, which contains the *Maintenance Screen*, *Manage Logins Screen*, and *Change History Screen*.
| User       | A user has slightly limited access to the program.  The *Transactions Screen*, *Search Screen*,*Report Screen*, and *About Screen* are the only screens accessible to a user.

###Transaction Screen
**Permission:** User

This screen handles all the transactions within the program.  You can scan the *UPC* an item that is *Incoming*, *Outgoing*, or being *Returned*.  You can indicate multiple items *Incoming*, *Outgoing*, and being *Returned* by selecting *Multiple* and adjusting the corresponding number value.  All transactions are logged in the window's text area in the bottom half of the screen.

####Toggle Buttons

While scanning, a toggle button is selected to complete certain actions:

| Toggle Button   | Description                                   |
| --------------- | --------------------------------------------- |
| *Incoming*      | Indicates an item incoming to the inventory   |
| *Outgoing*      | Indicates an item outgoing from the inventory |
| *Return*        | Indicates an item being returned              |
| *Multiple*      | Indicates multiple items                      |

>**Note:** *Multiple* can be used to indicate multiple of any transaction with the inventory.  After a complete transaction the *Multiple* value will be reset to 1 and *Multiple* will be deactivated.

####Returned Items

When *Return* is selected for a transaction, a dialog box will appear prompting for the return item's status.  There are a number of possible selections:

| Status                | Description                                         |
| --------------------- | --------------------------------------------------- |
| "Pending"             | The return status will be determined later          |
| "Return to Vendor "   | The returned item will be returned to the Vendor    |
| "Return to Inventory" | The returned itme will be returned to the Inventory |

>**Note:** When the status "Return to Inventory" is selected, the quantity of the same inventory item in the inventory will be incremented by the number of returned items.

####Scanning errors

While scanning items there are number of scanning errors that may occur:

| Error                                                                                      | Description            |
| ------------------------------------------------------------------------------------------ | ---------------------- |
| "Scanned item does not exist in the inventory"                                             | POS does not recognized the scanned *UPC*                                |
| "Scanned item has duplicates"                                                              | There are multiple items with the same *UPC*                              |
| "Scanned item's quantity cannot decrease below 0.  The quantity for the scanned item is 0" | A item's quantity is already 0, but the item is scanned as *Outgoing* |


###Search Screen
**Permission:** User

This screen allows for a item registered in the *Inventory* to be searched for in a number of ways.  Activate a search field by clicking on the label of the search criteria, and search by inputing the the activated field's information and click *Search*.

###Report Screen
**Permission:** User

This screen displays an *Inventory* and *Transactions* report.  The screen is in development.

###Maintenance Screen
**Permission:** Admin

This screen contains the tabs for **Inventory Maintenance**, **Return Maintenance**, **Readable Log**, and **Console**.  Please read below for further information about each tab.

####Inventory Maintenance Tab
**Permission:** Admin



####Return Maintenance Tab
**Permission:** Admin

####Readable Log Tab
**Permission:** Admin

####Console Tab
**Permission:** Admin

###Manage Logins Screen
**Permission:** Admin

###Change History Screen
**Permission:** Admin

###About Screen
**Permission:** User
