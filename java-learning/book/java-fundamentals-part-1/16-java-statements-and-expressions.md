# Statements and Expressions in Java

## Introduction to Statements and Expressions

As you grow more comfortable with Java, it's important to understand a core theoretical concept: the difference between **expressions** and **statements**. Java is a strongly typed, structured language, and distinguishing these two ideas helps make sense of how Java code is written and executed.

## What is an Expression?

An **expression** is any piece of code that produces a value.

Examples of expressions:

```java
3 + 4
1991
age > 18
true && false
"Hello" + " World"
```

All of these expressions evaluate to a single value. Expressions can be simple (a literal like `23`) or complex (like `a * (b + c)`), but they always **produce a value**.

Expressions can be used wherever Java expects a value.

## What is a Statement?

A **statement** is a complete instruction that the Java engine executes.  
Statements do *something*, but they do not themselves return a value.

Examples of statements:

```java
int age = 30;
System.out.println("Hello");
if (age > 18) {
    System.out.println("Adult");
}
```

A statement can contain expressions, but the statement itself does not evaluate to a value.

Think of expressions as *parts of a sentence*, while statements are the *whole sentence*.

## Example: If Statement

```java
if (23 > 10) {
    String str = "23 is bigger";
}
```

Here:

- `23 > 10` → **expression**
- `if (23 > 10) { ... }` → **statement**
- `"23 is bigger"` → **expression**
- `String str = "23 is bigger";` → **statement containing an expression**

Statements are used to structure and control your program’s flow.

## Expressions Inside Java Code

Java expects expressions in places where a value is needed.

For example:

```java
int age = 2037 - 1991; // the right side must be an expression
```

The right-hand side (`2037 - 1991`) produces a value, so it is valid.

Java also allows expressions inside output statements:

```java
System.out.println("I am " + age + " years old.");
```

Each expression inside the parentheses produces a value.

## You Cannot Use Statements Where Java Expects Expressions

For example, this is NOT allowed:

```java
System.out.println("Result: " + if (age > 18) { "Adult"; });
```

Java will throw a syntax error because `if` is a statement—not a value-producing expression.

To fix this, use an expression instead:

```java
String result = age > 18 ? "Adult" : "Minor";
System.out.println("Result: " + result);
```

The **ternary operator** (`condition ? expr1 : expr2`) is an expression-based alternative to `if/else`.

## Summary

Expressions produce values.  
Statements perform actions.

A Java program is built from statements, and those statements use expressions to compute values.

Understanding this separation helps you know what Java expects in different parts of your program.

## Key Takeaways

- An **expression** produces a value (e.g., `3 + 4`, `"Hello" + name`, `age > 18`).
- A **statement** performs an action (e.g., `if`, `while`, declarations, assignments).
- Statements may contain expressions, but not vice versa.
- Java expects expressions in contexts requiring values and statements in contexts requiring actions.
- You cannot embed statements where Java expects a value, such as inside string concatenation.

