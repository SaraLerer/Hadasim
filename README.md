

# HMO

description:
A system for managing a health insurance fund, this system consists of two applications: one is a Spring Boot Rest API called spring-backend and another is a ReactJS application called react-frontend.
The system allows members to be added, edited and deleted, and each member has the option of managing their corona information

# <h3>  Applications</h3>
- spring-backend.
</br>  A Spring Boot Web Java Backend application that exposes a REST API for add members.
spring-backend stores its data in an H2 database.
spring-backend has the following endpoints.

- react-frontend.
 </br> A ReactJS front-end application in which manager manage the HMOmembers , . All requests coming from react-frontend to secure endpoints at spring-backend
react-frontend uses MUI React as a CSS style framework.

# external dependencies
h2 db</br>
java springboot</br>
spring-boot-starter-validation</br>
react.js

# screenshot
המערכת מציגה את כל חברי הקופה על כל חבר קופה חלות האפשרויות הבאות : הצגת הנתונים , הוספת קורונה - ובמקרה שכבר נוסף כרטיס תיהיה אפשרות של עריכת קורונה , עריכת פרטים אישים , מחיקה
![members](https://github.com/SaraLerer/Hadasim/assets/145717469/593cdb99-e310-4635-ac23-d6477aa218ec)
בלחיצה על כפתור ההוספה תיפתח כרטיסיה ובה יש אפשרות להזנת נתונים

![addMember](https://github.com/SaraLerer/Hadasim/assets/145717469/a1fccb61-4932-4557-982d-40d8ea9f5884)

הטופס כולל בדיקות ולידציה
![addMember3](https://github.com/SaraLerer/Hadasim/assets/145717469/d07e87df-478a-4bf8-aa59-4a733d56836e)

כאשר נלחץ על כפתור deatails יפתח הכרטיס של אותו חבר קופה ובו יוצגו הנתונים
![Details](https://github.com/SaraLerer/Hadasim/assets/145717469/cccab8fc-fd55-4be4-bb4b-617c51892809)

בלחיצה על כפתור add corona תיפתח כרטיסיה ובה יש אפשרות להזנת נתונים 
לאחר שנוסף כרטיס קורונה לחבר הקופה תיעלם האפשרות של add corona במקןם זה יהיה את הכפתור של update corona ובו נוכל לעדכן את תאריך ההחלמה וכן להוסיף חיסונים בעת הצורך
![AddCoronaAndVaccine](https://github.com/SaraLerer/Hadasim/assets/145717469/46f14e3a-08e3-4424-86b0-9d3198847eeb)
כאשר נלחץ על כפתור העריכה תיתפח כרטיסיה ובה אפשרות לערוך את הנתונים השייכים לאותו חבר קופה
![update](https://github.com/SaraLerer/Hadasim/assets/145717469/984db5d2-9746-490d-9ab4-083f10699045)
כאשר נלחץ ב-navBar על corona detail נגיע לדף ובו יוצג גרף המתאר את כמות החולים בכל אחד מימי החודש האחרונים וכן מצוין כמה חברים בקופה לא מחוסנים


![graph](https://github.com/SaraLerer/Hadasim/assets/145717469/4948c9d6-6801-4296-9d26-2cea938ed33d)
כדי לראות את פרטי הקורונה ניתן ללחוץ על הכפתור בתחתית הכרטיס של הפרטיים האישים ואז יוצגו נתוני הקורונה

![detailsPlusCorona](https://github.com/SaraLerer/Hadasim/assets/145717469/7a32c5dc-5629-4c4f-92b1-67d3d85eb56b)


