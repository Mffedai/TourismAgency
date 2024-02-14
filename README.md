# Tourism Agency Program

This project is the 2nd completion project of the Patika+ Full-Stack Developer Program. The technologies used in this project include:
- PostgreSQL
- Java
- JDBC
- Swing GUI

The main purpose of this project is to efficiently manage the daily operations of a business operating in the hotel sector and optimize customer reservation processes.

The Tourism Agency collaborates with various hotels to reserve rooms for customers. The person with admin authority, usually the developer, is initially recorded in the database by the programmer.

The person with admin authority can log into the system and add both admin and agency employees. Agency employees can record hotels and rooms in the system, perform room searches based on customer requests, and make reservation transactions. Customers do not have access to the system; they make reservations via phone or in person through agency employees.

Screenshots presentation of the project are provided below.

## Installation

1. Clone the project to your computer:

    ```bash
    git clone https://github.com/Mffedai/TourismAgency.git
    cd TourismAgency
    ```

2. Create the PostgreSQL database:

   - Use the `tourismAgency.sql` file in the `DB SQL` folder of the project to create a PostgreSQL database. You can enter the following command in the terminal or command prompt:

       ```bash
       psql -U admin -d tourismAgency -a -f "DB SQL/tourismAgency.sql"
       ```

     Here, `admin` is the PostgreSQL database user, and `tourismAgency` is the name of the database you want to create.

3. Compile and run the Java project:

    ```bash
    javac App.java
    java App
    ```

After following these steps, the application will run, and you can start by accessing the admin page. In this section, you can create new users for your admin and agency employees.

    Admin:
    Username: aa
    Password: aa
    
    Employee : 
    Username : Fatih Fedai
    Password : aa

## Screenshots

#### Shema:
![tourismShema](images/tourismShema.png)

#### Admin Page:
![AdminPage](images/AdminSayfası.png)

#### Hotel Management:
![HotelManagement](images/OtelYonetimi.png)

#### Room Management:
![RoomManagement](images/OdaYonetimi.png)

#### Reservation Management:
![ReservationManagement](images/RezervasyonYönetimi.png)

#### Report Management:
![RaporManagement](images/RaporYonetimi.png)

#### Line Chart:
![LineChart](images/CizgiGrafigi.png)

#### Pie Chart:
![PieChart](images/PastaGrafigi.png)

#### Candle Chart:
![CandleChart](images/MumGrafigi.png)

#### Hotel Adding Page:
![AddHotel](images/OtelEkle.png)

#### Pension Adding Page:
![AddPension](images/PansiyonEkle.png)

#### Season Adding Page:
![AddPeriod](images/DönemEkle.png)

#### Room Adding Page:
![AddRoom](images/OdaEkle.png)

#### Reservation Adding Page:
![MakeReservation](images/RezervasyonYap.png)

## 🔗 Links

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mehmet-fatih-fedai-ab011019a/)
