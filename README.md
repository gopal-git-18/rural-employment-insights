About this Project
This is a simple web app that helps people understand the MGNREGA program in their districts. The goal is to make government data easy to read and accessible for everyone, even if they’re not tech-savvy.

What it does
Lets you see details about districts in a state (for now, just one large state).
Shows basic information like district name, ID, and location.

Fetching other MGNREGA data (like monthly performance) is planned for the next version.

How to check it
The app is live here: [Rural Employment Insights](https://rural-employment-insights.onrender.com/)

Currently, the main working endpoint is:
/district


Visiting this in your browser or using Postman will give you a JSON list of districts.

Tech used
Backend: Spring Boot (Java 17)
Frontend: Basic HTML/CSS/JS
Database: H2 (in-memory)
Hosting: Docker on Render.com (free tier)

Future plans
Add more MGNREGA endpoints like /monthly-performance
Auto-detect the user’s district based on location
Store API data locally so the app works even if the government API is slow or down

How to run locally
Clone the repo:
git clone https://github.com/gopal-git-18/rural-employment-insights.git


Go to project folder:
cd rural-employment-insights

Build and run:
./mvnw spring-boot:run


Open your browser at http://localhost:8080/district
