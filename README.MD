1. Understand the Existing Codebase:
    Review the current implementation in the invaders directory to understand the existing classes, their relationships, and the design patterns already in use.
    Identify the areas of the code that will be affected by the new features.

2. Design the New Features:
    Difficulty Level:
        Implement the Singleton pattern to ensure only one instance of each difficulty level exists.
        Use the Factory Method pattern to create the appropriate difficulty level based on user selection.
    Time and Score:
        Implement the Observer pattern to update and display the time and score continually.
        Use the Strategy pattern to change the scoring strategy based on the type of alien/projectile hit.
    Undo and Cheat:
        Implement the Memento pattern to save and restore the game state for the undo feature.
        Use the Command pattern to encapsulate the cheat operations that can be triggered by the user.
3. Implement the Features:
        Add the new classes and methods required for the new features, ensuring they integrate seamlessly with the existing code.
        Refactor the existing code if necessary to support the new features, but keep changes minimal to avoid breaking the working code.
4. Testing:
        Thoroughly test the new features to ensure they work as expected and do not introduce bugs into the existing implementation.
5.  Documentation and Report:
        Update the code documentation to reflect the new features and any significant changes.
        Write the report covering the code review, the use of OOP and design patterns, the rationale behind the changes made, and a discussion on the design and extensibility of the codebase.
        Include UML diagrams to illustrate the use of design patterns and the overall class structure.
6.  UML Diagrams:
        Create UML class diagrams to represent the existing codebase and the new extensions, highlighting the design patterns used.
7.    Reflect and Improve:
        Reflect on the design and implementation of the new features, identifying any potential issues or areas for improvement.
        Consider the impact of the changes on the extensibility of the code and document any insights.