# Apache Spark Homework Assignment 3 	(Mehul Birari)

Apache Spark program to parallelly process Monte Carlo Simulation to 
  
## How to run the project

  - Make sure your device is sbt enabled.
  - Clone this private repository and open the sbt shell with the path as the local repository's path.
  - Type the command "sbt clean assembly" to generate the jar.
  - If the jar takes time to generate, type the command "sbt clean compile run" and execute it.
  - You will get a list of options for which file to run.
  - Select any option.
  - You will get the output generated for the selected stocks file in the project directory.
  - Done.

## How to test the project

  - Type the command "sbt clean compile test" and execute it.
  - Done.

#### Note
  - Run the project in IntelliJ if output doesnt display as shown in the images.
  
 
## OUTPUT
The output shows the various results we obtain after the the simulation. 


### Analysis
- The created DataFrame in Spark for processing.
![Author names](https://bitbucket.org/mehulbirari/mehul_birari_hw2/raw/7f3d32dd0b2635f2044e5b61bb0de7efe49562bb/images/photo1.JPG "Author names")

- The created predicted price values into a DataFrame.
![Author names and count](https://bitbucket.org/mehulbirari/mehul_birari_hw2/raw/7f3d32dd0b2635f2044e5b61bb0de7efe49562bb/images/photo2.JPG "Author names and count")

- CSV file output generated containing the profit calculated according to the algorithm.
![Sorted by publication count](https://bitbucket.org/mehulbirari/mehul_birari_hw2/raw/7f3d32dd0b2635f2044e5b61bb0de7efe49562bb/images/photo3.JPG "Sorted by publication count")

 
 
### Todos

 - Try for more functionalities.
 - Write MORE Tests
  



   