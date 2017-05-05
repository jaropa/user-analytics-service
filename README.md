In order to run service execute following from command line:
1. mvn compile test
2. mvn exec:java -Dexec.mainClass="com.hotels.user.analytics.RunUserAnalyticsService"

Once service is running you can call below endpoints:

http://localhost:4567/user/{userId}/avgStayLength
http://localhost:4567/user/{userId}/bookingsTotalValue
http://localhost:4567/user/{userId}/bookingsNumber


Task: 
HCOM CDS Coding Test

Build a web service for retrieving statistics on the bookings of a particular user.

Requirements:
- At start up, the service loads user_features.txt into memory
- Design and implement endpoint(s) so that the service will be able to answer queries like:
-- What is the number of bookings for a user
-- What is the total booking value for a user
-- What is the average length of stay for a user
- Make sure your code is well tested

Consider that the file may be larger than the sample given and keep in mind that we love elegant, clean and testable code.

Once you’re done:
- write a simple README.txt on how to run and execute it
- compress your code and the README
- send it back

Enjoy!