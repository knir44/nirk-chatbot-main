# Project README

## Overview
This project provides a simple API to interact with movie and joke databases. It includes two main functionalities:

1. **Getting Movies by Name**: You can search for movies by name, and the API will return a list of matching movies from IMDb.
2. **Getting Jokes by Category**: You can search for jokes by category, and the API will return jokes related to the specified category from the Chuck Norris Joke API.

### Prerequisites
Before you can run the application, make sure you have the following tools installed:

- **Java Development Kit (JDK)**: Version 8 or higher.
- **IDE**: IntelliJ IDEA, Eclipse, or any other Java-compatible IDE.
- **Maven**: To manage project dependencies (if not included in your IDE setup).
- **Swagger UI**: To interact with the API easily (should be available out of the box).

## Running the Application

### Step 1: Clone the repository
Clone this repository to your local machine using the following command:
```
git clone <repository_url>
```

### Step 2: Install dependencies
Navigate to the project directory and use Maven (or your IDE) to install the necessary dependencies. If you're using Maven, you can run the following command:
```
mvn install
```

### Step 3: Run the Application
Run the application in your preferred Java IDE (e.g., IntelliJ IDEA or Eclipse). You can also run it from the terminal using Maven:
```
mvn spring-boot:run
```

Once the application is running, it will be accessible at `http://localhost:8080`.

## API Endpoints

### 1. **Getting Movies by Name**
   **Endpoint**: `GET /bot/imdb`

   **Description**: This endpoint allows you to search for movies by their name. You can enter a movie name, and the API will return all the movies found with that name from the IMDb website.

   **Request Parameters**:
   - **movieName** (String): The name of the movie you're searching for.

   **Example Request**:
   ```
   GET http://localhost:8080/bot/imdb?movieName=batman
   ```

   **Response**: A list of movies matching the search query. Each movie will include the following details:
   - Movie name
   - Release date
   - Top credits (actors/crew)
   - A link to the IMDb page for the movie

   **Example Response**:
   ```json
   {
     "Total results": 10,
     "Movies": [
       {
         "Movie name": "Batman Begins",
         "Release date": "2005",
         "Top credits": ["Christian Bale", "Michael Caine"],
         "Link": "https://www.imdb.com/title/tt0372784/"
       },
       {
         "Movie name": "The Dark Knight",
         "Release date": "2008",
         "Top credits": ["Christian Bale", "Heath Ledger"],
         "Link": "https://www.imdb.com/title/tt0468569/"
       }
     ]
   }
   ```

---

### 2. **Getting Jokes by Category**
   **Endpoint**: `GET /bot/jokes`

   **Description**: This endpoint allows you to search for jokes by category. You can specify a category (e.g., "animal", "dev") and retrieve jokes from the Chuck Norris Joke API related to that category.

   **Request Parameters**:
   - **subject** (String): The joke category you're interested in. For example, `dev` or `animal`.

   **Example Request**:
   ```
   GET http://localhost:8080/bot/jokes?subject=dev
   ```

   **Response**: A list of jokes related to the specified category. Each joke will include:
   - The joke's content
   - A URL to an image related to the joke (if available)

   **Example Response**:
   ```json
   {
     "Total results": 5,
     "Jokes": [
       {
         "Joke": "Why do programmers prefer dark mode? Because the light attracts bugs.",
         "Image": "https://some-url-to-joke-image.com"
       },
       {
         "Joke": "Why do Java developers wear glasses? Because they can't C#.",
         "Image": "https://some-other-joke-image.com"
       }
     ]
   }
   ```

---

## Swagger UI

After the application is up and running, you can interact with the API through **Swagger UI**.

1. **Navigate to Swagger UI**:
   Open your browser and go to the following URL:
   ```
   http://localhost:8080/swagger-ui.html
   ```

2. **Using the API**:
   - In Swagger UI, you will see two available endpoints:
     - **GET /bot/imdb**: To search for movies by name.
     - **GET /bot/jokes**: To search for jokes by category.
   - Select the endpoint you wish to interact with, and click **Try it out**.
   - Enter the required parameters (e.g., movie name or joke category) and click **Execute** to see the results.

## Notes

- The movie data is fetched from IMDb based on a name search and parsed using regular expressions.
- The joke data is fetched from the Chuck Norris Joke API based on a category search.
- Ensure you have a stable internet connection, as the data is fetched from external sources (IMDb and Chuck Norris Joke API).

## Troubleshooting

- **If you receive an empty response or "Not Found" message**:
   - Ensure that you are passing valid search parameters (e.g., a valid movie name or joke category).
   - If using Swagger UI, make sure you've entered the parameters correctly.

- **Error 404 - Not Found**:
   - This could happen if the server is not running. Ensure that the application is up and running on `http://localhost:8080`.

## Conclusion

This API is designed to provide an easy way to get movie and joke information programmatically. With Swagger UI, you can test and interact with the API seamlessly without needing any additional setup.
