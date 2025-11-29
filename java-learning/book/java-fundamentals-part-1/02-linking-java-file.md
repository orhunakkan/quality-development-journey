# Linking a Java File (Java 21)

## Introduction

In this lecture, we will learn how to write Java code in a separate file, organize it properly, and run it using standard Java tools. This mirrors how real Java development is done.

## Downloading the Starter Code

To get started, download the starter project from the provided repository for this course. You will receive a ZIP file that contains folders for each section. Inside each section, you will find both a **starter** folder and a **final** folder.

The **final** folder contains the completed version of the code as it should look when you finish the section. If something goes wrong, you can compare your work with the final version.

The **starter** folder is your working folder. Use this folder as your project directory.

## Extracting and Organizing the Starter Code

After downloading the ZIP file, extract it. Move the starter folder for the current section to your desktop. This folder contains your Java source files, project structure, and configuration files.

Inside the starter folder, you will typically find a `src` directory containing your Java files.

## Opening the Project in VS Code

Open the starter folder in VS Code. Java is not tied to HTML files, so you work directly with `.java` files inside the `src` folder.

When you open the project, you will see at least one Java file, commonly named `Main.java`. This is the entry point of your Java application.

## Writing Java in a File

Java code must live inside a `.java` file and inside a class. You cannot execute Java directly in a browser console or HTML file.

Let’s write a simple example in `Main.java`.

### Java Code Sample

```java
public class Main {
    public static void main(String[] args) {
        String status = "amazing";

        if (status.equals("amazing")) {
            System.out.println("Java is fun!");
        }
    }
}
```

To run this program, you must compile it and then execute the compiled bytecode.

## Running Java Code Using the Terminal

Java does not run in the browser, so you use the terminal.

To compile:

```
javac Main.java
```

To run:

```
java Main
```

This will print `"Java is fun!"` to the terminal.

## Using System.out.println

If you write a mathematical expression in Java without printing it, nothing appears in the output. Just like JavaScript requires `console.log`, Java requires `System.out.println`.

### Java Code Sample

```java
System.out.println(40 + 8 + 23 - 10);
```

This will print the result to your terminal when you run the program.

## Recap: Console vs Java Files

Unlike JavaScript, Java has **no script tag**, and it does not run inside HTML. Everything must be written in `.java` files and executed through the JVM.

Outputs appear only when you explicitly print them using `System.out.println`.

## Moving Code to Another Java File

If you want to cleanly separate pieces of logic, you can create additional Java files.

Create a new file named `Helper.java` in the same folder as `Main.java`.

### Java Code Sample

```java
public class Helper {
    public static void showMessage() {
        System.out.println("Hello from another file!");
    }
}
```

Now call it from Main:

```java
public class Main {
    public static void main(String[] args) {
        Helper.showMessage();
    }
}
```

## Linking Java Files Together

Java files in the same folder automatically work together as long as:

- They are in the same package (or have no package statements)
- They are compiled together or exist in the same compiled output directory

When you compile:

```
javac Main.java Helper.java
```

Running `java Main` will execute your program and print the messages.

## Conclusion

You now know how to organize Java files, separate logic into multiple files, and run Java code correctly from the command line or VS Code. This is the foundation of all Java application development.

---

## Key Takeaways

- Java code must be written in `.java` files inside classes and run through the JVM.
- Use `System.out.println` to output results to the terminal.
- Separate logic by creating additional Java files and calling them from your main class.
- Compile multiple files together to build your application correctly.
