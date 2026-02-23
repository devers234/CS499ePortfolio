# CS499ePortfolio

Self-Reflection

Completing the Computer Science program and developing my professional ePortfolio has allowed me to display my technical strengths, professional values and career goals. The ePortfolio also displays my growth across the core areas of computer science. Throughout the Computer Science program, I progressed from focusing on and completing isolated tasks like coding “Hello World” in Python to designing, evaluating, and communicating by completing projects like full-stack development. The capstone experience in the course CS499 brought all the skills I learned over the last four years together and helped reinforce my readiness to enter the computer science career field as a capable and adaptable professional.


My coursework emphasized both technical and professional practices. Courses like software development lifecycle and mobile architecture strengthened my ability to work effectively in team environments. Coordination with peers, mock daily scrums, and incorporating feedback were all experiences that strengthened my teamwork skills. These experiences taught me how to contribute as part of a team while also taking ownership of parts of a project I completed. 

	
Clear communication has been a recurring focus throughout the Computer Science program. I regularly had to communicate technical ideas to diverse audiences, including my peers and instructors. Tasks like explaining system designs, justifying technical decisions, and presenting course outcomes through essays and videos are all ones that improved my communication skills. The capstone ePortfolio demonstrates my ability to present technical work in a structured, accessible format that emphasizes clarity, professionalism, and audience awareness. 

	
My work with data structures and algorithms strengthened my ability to design efficient and scalable solutions. Through coursework in data structures, algorithms, and advanced programming concepts, I implemented and analyzed searching, sorting, indexing, and data organization techniques. These skills enabled me to evaluate trade-offs between performance and complexity and apply algorithmic thinking to real-world problems.
The program also reinforced strong software engineering and database skills. I gained experience designing modular, maintainable software using object-oriented principles, layered architecture, and structured validation techniques. Database-focused coursework like SQL and full-stack development expanded my understanding of relational and non-relational data models, CRUD operations, and data integrity considerations. By integrating databases into applications and courses, I developed an appreciation for how data storage, business logic, and user interfaces must work. 

	
Security awareness has been a consistent theme throughout my coursework and capstone experience. I developed a security mindset by considering potential vulnerabilities early in the design process, validating inputs, managing access controls, and anticipating how systems could be misused or exploited. Rather than treating security as an afterthought, I learned to incorporate it into architectural decisions and development practices to protect data, ensure privacy, and improve system resilience.

	
The artifact included in my ePortfolio collectively demonstrates this integrated skill set. Each artifact represents a different aspect of my growth while contributing to a cohesive narrative of problem-solving, technical refinement, and professional development. Together, they show my ability to analyze existing systems, enhance functionality, apply algorithmic improvements, integrate database solutions, and communicate technical decisions effectively. This portfolio is designed to give viewers a clear understanding of both my technical capabilities and my approach to building thoughtful, well-engineered computing solutions.	

	
Overall, the Computer Science program and the capstone experience have prepared me to transition confidently into the professional computing field. My ePortfolio reflects not only what I have built, but how I think, communicate, and continuously improve as a computer scientist. I am well-positioned to contribute value in a professional environment that requires technical competence, collaboration, adaptability, and a strong commitment to quality and security.


## Code Review Video
A code review is a process in which developers examine code to make sure that it meets a certain standard, functions well, and is readable. After looking at the resources, I learned that code reviews are an important tool to developers because it allows them to look at code after completing it and point out any errors or issues. Through my own experience, looking at code again multiple times helps when finding bugs or reasons why the code may not work. Code reviews are important to practice for computer science professionals because it helps them identify good code and bad code. 

Below is a link to my code review on YouTube



https://youtu.be/r_uqmAmkn2w


## Software Design and Engineering Enhancement

The artifact I selected for this milestone is an Android-based inventory app from the course CS360: Mobile Architecture and Design. This application allows users to create and manage their inventory of collectible cards like Magic: The Gathering cards. Users can add items, remove items, and specify quantities using a local SQLite database. The original version of the application supported basic inventory functionality like item names and quantities. This artifact was selected because it provides a strong foundation in software design and engineering principles like object-oriented design, database integration, input validation and user interface improvements. 
	This artifact was selected because it had good base code that I knew I could improve and enhance. The original version lacked depth in terms of input validation and data richness. This first enhancement focused on expanding the application’s data model and improving the overall software design. These improvements were made, 
•	Added two new attributes (condition and willingToTrade) to the item model
•	Updated the database schema to support new attributes
•	Implemented input validation logic
•	Enhanced the user interface by adding toggle and dropdown controls
•	Refactored the code to improve maintenance and readability

The enhancements made to this artifact demonstrate progress towards multiple Computer Science program outcomes. One is Algorithms and data structure. The updated design reflects improved object-oriented structure through the expanse of the Item model and the addition of two new methods. Refactoring the database logic and UI demonstrates effective separation of concerns and clean architecture. This project required me to identify weak areas in my code and then improve them. Things like data validation, consistency, and scalability were all areas I wanted to improve. I used SQLite schema, input validation, Modular java classes and improved UX/UI to enhance the application. 
	Enhancing this artifact reinforced the importance of planning in software development. The original application functioned fine and was efficient. Enhancing it not only helps the user but the developers as well. One major challenge I encountered was fixing all the issues. When adding in methods or changing things, I had to go to different code and fix them there as well. This took a while to make sure the syntax was right. One spelling mistake cost me at least 30 minutes of troubleshooting. Through this process, I learned that when changing code, it affects the whole application, not just one part. Overall, this enhancement taught me how to code review, improve code, and implement features that align with software development standards. 

Below are links to my source code and reflection.


https://github.com/devers234/CS499ePortfolio/blob/main/Daniel%20Evers%20milestone%202%20reflection.docx

https://github.com/devers234/CS499ePortfolio/tree/main/Daniel%20Evers%20Inventory%20app%20with%20enhancments/DanielEversInventoryapp2


## Algorithms and Data Structures Enhancement

The artifact I selected for this milestone is an Android-based inventory app from the course CS360: Mobile Architecture and Design. This application allows users to create and manage their inventory of collectible cards like Magic: The Gathering cards. Users can add items, remove items, and specify quantities using a local SQLite database. The original version of the application supported basic inventory functionality like item names and quantities. This artifact was selected because it provides a strong foundation in software design and engineering principles like object-oriented design, database integration, input validation and user interface improvements. 
	This artifact was selected because it had good base code that I knew I could improve and enhance. The original version lacked depth in terms of input validation and data richness. This second  enhancement focused on improving the application's performance, scalability, and data organization. These improvements were made, 
•	Added a search bar at the top of the data table so users can find cards quickly
•	Implemented sorting by using ORDER BY.
•	Added an in-memory HashMap index(HashMap<String, Item>)
Sorting using ORDER BY ensures that the inventory is always alphabetized, meaning if a user adds a card that begins with A, it will go to the top of the list. The HashMap index helps index the inventory by name. Lastly, I added a search by name bar for users to easily search for a card in their inventory. 
	These enhancements demonstrate multiple computer science program outcomes. Through the implementation of my enhancements, I strengthened my ability to design and evaluate computing solutions that solve a given problem using algorithm principles. The database-level sorting combined with an in-memory HashMap for searching required evaluating performance, scalability, and memory usage to determine the most effective approach. This work also reflects progress towards using well-founded and innovative technique skills and tools in computing practices to implement computer solutions that deliver value and accomplish industry goals. Enhancing the application’s data model, validation logic, and internal data structures improves the maintainability of the project. A third outcome this enhancement meets is the process of documenting design decisions, implementing structured enhancements, and preparing the artifact for ePortfolio. This enhancement did not focus on collaborative development but it supports the progress by building on my skills. Finally, this enhancement contributes to developing a security-aware mindset by reinforcing the importance of input validation and controlled data handling. Validation rules were implemented to prevent invalid data from entering the system. 
Below are links to my reflection and source code.

https://github.com/devers234/CS499ePortfolio/tree/main/Daniel%20Evers%20Inventory%20app%20enhancments2
https://github.com/devers234/CS499ePortfolio/blob/main/Daniel%20Evers%204-2%20reflection.docx
	
## Database Enhancement

