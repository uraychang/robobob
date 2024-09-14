# Robobob

## Introduction
**Robobob** is a fun and interactive command-line-based math robot designed for children. It helps solve arithmetic problems and can also answer basic questions about itself, such as its name.
## Get started
### Build the Docker Image
To build the Docker image, navigate to the directory containing the Dockerfile and run the following command:

    docker build -t robobob .

This command creates a Docker image named robobob. Since some AI libraries need to be downloaded, the process may take a few minutes.

### Run the Game
Once the image is built, start the container in interactive mode by running:

    docker run -it robobob

-it: Enables interactive terminal mode, allowing you to interact with the game directly in the terminal.

### Local Build
If you’d like to build and run the project locally, make sure your system has the following prerequisites installed:
- **Java 17**
- **Maven 3.8.4**
- **Python3**
- **spaCy** (for Python NLP capabilities)

## Design
### Core Entities
- ***AnswerProvidable***: This interface defines how questions are answered. Once a question is asked, an appropriate implementation of this interface is selected to provide an answer.
  - ***answer(String question)***: Provides the answer to the question.
  - ***belongsToThis(String question)***: Determines whether the current question can be handled by this answer provider.
- ***AnswerProviderFactory***: Implements the Factory Pattern. It takes the user question as input and returns the most suitable AnswerProvidable implementation.
- ***SimilarityService***: Calculates the semantic similarity between user-asked questions and predefined questions to find the closest match.
- ***PredefinedQuestion***: Stores predefined questions and answers. These questions are persisted in a JSON file and loaded into memory when the game starts.

### Workflow
1. Before the game starts, all predefined questions are loaded via the ***PredefinedQuestion*** class.
2. When a question is asked, the ***AnswerProviderFactory*** selects the appropriate answer provider.
3. Two answer providers are currently supported:
   - ***ArithmeticAnswerProvider***: Handles mathematical questions.
   - ***PredefinedAnswerProvider***: Finds the most semantically similar predefined question and provides the corresponding answer. The similarity is calculated using a Python script powered by the spaCy **NLP** library. If no match is found, it responds with “Unable to answer.”
4. The game is then ready to process the next question.

### Extensibility
#### Database access
Currently, predefined questions are stored in a JSON file. In the future, if we want to store these questions in a database, it can be done easily by implementing a ***PredefinedQuestionRepository*** interface for database access.
#### Adding a New Predefined Question
Predefined questions are stored in a JSON file and can be easily updated. To add a new question, simply append a new JSON object with the `question` and `answer` fields.
#### Adding a New Answer Provider
To add a new answer provider:
1. Implement the AnswerProvidable interface in a new class.
2. Mark it as a Spring-managed bean. 

Once completed, the new provider will be automatically detected by AnswerProviderFactory and ready to answer questions.

## Challenges
- Finding the optimal threshold for determining question similarity has been challenging.
- Performance improvements are needed to handle and process user questions more efficiently.
- Implementing machine learning models, along with a feedback loop, can help improve accuracy by allowing the system to better understand user queries and continuously refine its responses based on user feedback.



    