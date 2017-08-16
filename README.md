# SmartDrugBox
Create an Android Mobile Apps application that serve as a controller for a smart drug box. 
It can also serve as a platform between user and pharmacy to do purchasing and selling medicine.

<h2>Introduction</h2>
The proportion of older generation in Singapore is increasing every year and soon, a lot of the 
elderlies will be staying alone. Hence, we see this as a serious problem that currently has no 
effective solution yet. My proposed solution is to develop a smart drugbox that will help the 
elderlies to take their medication easily.

<h2>Hardware</h2>
<h3>ESP8266</h3>
The ESP8266 Wi-Fi Module is a self-contained System on Chip with integrated TCP/IP protocol stack 
that can give any microcontroller the capability to access to the Wi-Fi. We are using this component
to fetch information from the cloud server and perform the task accordingly. It will trigger the alarm
when it is time for the patients to take medicine. Besides that, it will connect to various sensors
to detect the presence of the pills in order to better notify the users about the condition of the 
drugbox.

<h2>Software</h2>
<h3>Android</h3>
I have designed an Android mobile application in this project to control the Smart Drugbox. It has
functions like setting up reminder, add drugbox for different patients, edit the details of the medicine
in the drugbox and etc. I will introduce the feature in the following section.

<h2>Features</h2>
Basically, when users run the application, they can choose to log in as pharmacy or normal user, 
and there are different functions that they can perform as different roles. 
<ol>
<h3>Pharmacy</h3>
<li>
    <p><strong>Adding,Updating and Deleting Medicine</strong></p>
    <p>The pharmacy can add new medicine details into the database. They  could specify the details
     and upload an image for the medicine that they are selling. Editing and deleting the medicine 
     is also possible if they find any mistakes in the medicine details or if the pharmacy is no 
     longer selling the medicine. Besides that, they could also temporarily hide the medicine so as 
     not to display to the users if it is out of stock for a short period</p>
</li>
<li>
    <p><strong>Accepting order</strong></p>
        <p>There are two ways that the pharmacy can access to the order. Firstly, a pharmacy will 
        receive notification when there is a new order added into the public pool of the medicine 
        order. After viewing the order details and if they can fulfill the order, they can accept the 
        order and if the process is successful they will see an alert dialog box and the order will 
        be transferred to their own personal order queue. Secondly, the pharmacy will receive the 
        order notification if the users choose to direct the order specifically to 
        the pharmacy. The order will be added into the order queue of the pharmacy and 
        the pharmacy will need to attend to the order as soon as possible.                                                                              
</p>
</li>
<li>
   <p><strong>Message Queue</strong></p>
       <p>Message Queue is a page that records all the notifications that comes
       in. The message queue is basically a history page where the pharmacy can trace back all the 
       notifications that they received.</p>
</li>
</ol>

<ol>
<h3>User</h3>
<li>
    <p><strong>Set Reminder</strong></p>
    <p>User can set reminder, and the smart drugbox will pick up the alarm that has been set and trigger 
    the buzzer at the smart drugbox accordingly. User can set up the alarm 
    and all the alarm will be stored in the cloud database. The user can view, activate or deactivate 
    the alarm in the alarm settings page.</p>
</li>
<li>
    <p><strong>Adding medicine box</strong></p>
        <p>One caretaker may have multiple drugboxes and he/she can assign each drugbox to a patient
        that he/she wants to keep track of. The caretaker can include the patient’s 
        details, emergency number and address in this page. Hence, the pharmacy will know more 
        details about the patient when doing medicine delivery.                                                                              
</p>
</li>
<li>
   <p><strong>View Compartment Details</strong></p>
       <p>The caretaker can press on the medicine box that he/she is interested in at the page and 
       this will bring him/her to a page that displays the fill-up status of the compartments
       of that drugbox. There are two colors, green and red,
       which indicate the fill-up status of the compartment. Green indicates that the compartment 
       of the drugbox is filled up and red indicates that the compartment is empty. Moreover, the
       caretaker can go a step further by pressing on the compartment and it will bring them to 
       the page where we can see the details of the medicine.</p>
</li>
<li>
   <p><strong>Receive notification when medicine is running out</strong></p>
       <p>When the medicine is running out, the smart drugbox will trigger the run-out alert and 
       send a notification to the Android application. The user receives the 
       notification about the running out status of the compartment, so that he can click on the 
       notification and go to the page where he can see the details of the medicine that has run 
       out.</p>
</li>
<li>
   <p><strong>Send out order</strong></p>
       <p>When the user reaches the order medicine page, he/she can look at the details to see which 
       medicines that is running out and he/she will be prompted with an alert dialog, 
       which provides him/her the options of choosing between self-manage and 
       system-manage modes.</p>
      <ol>
      <li><strong>Self Manage</strong>
      <p>
      Self-Manage mode will allow user to find out all the pharmacies selling the medicine before 
      the user decides on the purchase. The system will search for all the pharmacies that are 
      selling the medicine and display these pharmacies to the user. The advantage of this is that 
      the user can compare the prices offered by different pharmacies before he/she places his/her 
      order.
      </p>
      </li>
      <li>
      <strong>System Manage</strong>
      <p>
      System-Manage mode will help the user to manage the order systematically and send his/her 
      order to a public pool. All the pharmacies that subscribe to this public pool will receive 
      a notification about this order. They can press the notification tab and view the order before
      they accept it. And once the pharmacy accepted the order, a notification will be sent to the 
      users that their orders have been accepted.
      </p>
      </li>
      </ol>
</li>
<li>
   <p><strong>Message Queue</strong></p>
       <p>All the notifications that come in will be stored in the message queue so the user can 
       review it and trace back all the notifications that he/she has received in the message queue 
       page.</p>
</li>
</ol>
<h3>Firebase</h3>
Firebase is a mobile backend service that is provided by Google and it provides a lot of 
powerful functionalities such as analytics, real-time database, messaging and 
cloud functions to allow developers to move quickly and focus on their users. 

<h5>Firebase Realtime Database</h5>
Firebase Real-Time Database is a cloud-hosted database. The database stored data in a JSON format
and synchronized in real-time with the client.

<h5>Firebase Cloud Function</h5>
Cloud function for Firebase lets user automatically runs backend code in response to events triggered 
by Firebase features and HTTP request. All the backend codes are stored in Google’s cloud environment
and there is no need to manage and scale your own servers. It provides different triggers to allow 
developers to write different functions for different event types. 