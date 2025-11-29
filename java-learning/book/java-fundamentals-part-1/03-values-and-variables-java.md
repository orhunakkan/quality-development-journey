# Values and Variables (Java 21)

## Introduction to Values and Variables

In this section, we begin learning the Java language by understanding values and variables. These concepts form the foundation of every Java program. We will use examples such as a person's name, age, or job to illustrate how values and variables work.

## Understanding Values

A value is a piece of data — the most fundamental unit of information in a program. For example, the text `"Jonas"` is a value, and the number `23` is also a value. Java can perform mathematical operations on numeric values to produce new values, such as `40 + 8 + 23 - 10`, which results in `61`.

### Java Code Samples

```java
System.out.println("Jonas");
```

```java
System.out.println(23);
```

```java
System.out.println(40 + 8 + 23 - 10);
```

## Storing Values in Variables

One of the most useful things we can do with values is store them in variables so they can be reused throughout the program.

In Java, we declare a variable by specifying its type followed by its name:

```java
String firstName = "Jonas";
```

This line creates a variable called `firstName` and stores the value `"Jonas"` inside it.

Another example:

```java
String status = "amazing";
```

Variables act like labeled boxes — the label is the variable name, and the value is the content stored inside. To use the stored value, you reference the variable name:

```java
System.out.println(firstName);
```

If you change the value of the variable later, all usages reflect the updated value:

```java
firstName = "Matilda";
```

## Variable Naming Conventions and Rules

Now that we understand what variables are, let’s look at how to name them correctly. Java uses *camelCase* for variable names. This means the first word is lowercase, and each following word starts with an uppercase letter.

```java
String firstNamePerson;
```

Other languages use styles like underscores, but in Java, camelCase is the standard.

### Rules for Variable Names

Java variable names must follow these rules:

- They **cannot start with a number**:

```java
// Invalid
int 3years = 3;
```

- They may contain **letters, numbers, underscores, or dollar signs**, but no other symbols:

```java
String jonas_and_matilda = "JM";
```

- They **cannot use reserved Java keywords**, such as `class`, `new`, or `public`:

```java
// Invalid
int new = 27;
int class = 1;
```

If necessary, an underscore or dollar sign may prefix the name:

```java
int _class = 1;
int $value = 5;
```

The word `name` is allowed but not very descriptive:

```java
String name = "Jonas";
```

### More Naming Conventions

In Java, variable names should **not start with an uppercase letter** — by convention, uppercase names are reserved for **class names**.

```java
String Person = "Jonas"; // Not recommended
```

Constants that never change should be written in **uppercase with underscores**:

```java
final double PI = 3.1415;
```

Using meaningful, descriptive names makes your code easier to read:

```java
String myFirstJob = "programmer";
String myCurrentJob = "teacher";
```

Avoid vague names like:

```java
String job1 = "programmer";
String job2 = "teacher";
```

## Recap: What is a Variable?

A variable is a box that stores a value. You give the box a name (like `firstName` or `myFirstJob`) and put a value inside it. Later in the program, you can reference that variable as many times as you want.

```java
System.out.println(myFirstJob);
```

If you change the value:

```java
myFirstJob = "coder";
```

The output changes accordingly. Variables are one of the most fundamental building blocks of programming, so take the time to understand them well before moving on.

---

## Key Takeaways

- Values are the smallest units of information in programming and can be stored in variables.
- Variables are named containers that hold values and allow easy reuse.
- Java uses camelCase naming conventions and has strict rules for valid identifiers.
- Descriptive variable names improve readability and make programs easier to maintain.
