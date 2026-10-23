# Trese-Combat-Manager
**This project was built to demonstrate core Object-Oriented Programming principles. It manages a roster of characters inspired from the Filipino comic Trese, allowing users to simulate battles, update stats, and view character abilities.**

# Enhancements
‣ Implemented an abstract GameCharacter class with abstract methods (attack(), specialMove()). This forces all subclasses to define their own unique combat logic while sharing a common data structure.

‣ Created a CharacterRepository class that acts as a List ADT. It encapsulates an ArrayList and provides controlled methods for data manipulation (add, remove, search, update), hiding the underlying data structure from the GUI.

‣ Built a fully themed Java Swing interface featuring an animated GIF background, transparent overlapping panels, a custom JList renderer (with image icons and colored fallbacks), and modern button hover states.

‣ Created a custom JDialog (CharacterDialog) for CRUD operations. It includes input validation to prevent empty names, blocks duplicate characters, and uses JSpinner to restrict Level and Health to valid numerical ranges.

# Sample Output
<img width="1457" height="941" alt="sampleOutput" src="https://github.com/user-attachments/assets/a72ff599-f381-44c1-bdc5-6d811658e6d8" />

# How To Run:
1. Java 17+ should be installed.

2. All image assets (bg2.gif, title.png, and character icons like Alexandra.png etc.) remain in the root directory alongside the source code.

3. Run Main.java as a Java Application.




