# Strings and String Formatting in Java

## Introduction to Strings and String Formatting

Strings are an essential part of Java programming. In this section, we will explore how to build and format strings effectively in Java. While JavaScript uses template literals with backticks, Java uses different mechanisms for combining text and inserting variable values.

Let’s begin by creating some variables about a person, such as first name, last name, job, and birth year, and then combine them into a descriptive sentence.

## String Concatenation with the Plus Operator

Java allows string concatenation using the plus operator `+`. When combining strings with numbers, Java automatically converts numbers to strings internally.

Example:

```java
String firstName = "Jonas";
String lastName = "Schmedtmann";
String job = "teacher";
int birthYear = 1991;

int age = 2031 - birthYear;

String description = "I'm " + firstName + " " + lastName + ", a " + age + " year old " + job + "!";
System.out.println(description);
```

Just like in JavaScript, concatenation requires careful spacing and ordering. Parentheses are used for calculations to ensure they occur before concatenation.

## Handling Quotes in Strings

Java supports only double quotes `"` for string literals. To include quotes inside a string, you must escape them with a backslash:

```java
String text = "He said, \"I'm learning Java!\"";
```

This ensures the string is interpreted correctly.

## String Formatting: A Better Way to Build Strings

Java provides a cleaner and more powerful alternative to manual concatenation: **formatted strings**. These work similarly to template literals in JavaScript but use a different syntax.

### Using String.format()

Java’s `String.format()` allows embedding placeholders inside the string:

```java
String formatted = String.format("I'm %s %s, a %d year old %s!",
                                 firstName, lastName, age, job);
System.out.println(formatted);
```

Here:
- `%s` inserts a string  
- `%d` inserts an integer  

This approach improves readability, avoids scattered plus signs, and keeps formatting clean.

### Using Text Blocks (Multiline Strings)

Java also supports **multiline strings**, introduced in Java 15, known as **text blocks**. These are written using triple quotes:

```java
String info = """
    Name: %s %s
    Job: %s
    Age: %d
    """.formatted(firstName, lastName, job, age);
System.out.println(info);
```

Text blocks automatically preserve line breaks and formatting, similar to how template literals allow multiline strings in JavaScript.

## Using Text Blocks for All Strings

Text blocks can be used even when no variables are inserted, making them useful for long or structured strings:

```java
String message = """
    Welcome to the system!
    Please follow the instructions below.
    """;
```

Many developers prefer text blocks for readability, especially when producing long messages or structured output such as HTML or JSON.

## Embedding Expressions

Java does not support expression embedding directly inside string literals like JavaScript's `${}`. Instead, you compute expressions beforehand or use formatting:

```java
String details = String.format("Next year, I'll be %d years old.", age + 1);
```

The expression `age + 1` must be calculated before or inside the formatting call.

## Multiline Strings with Text Blocks

Before text blocks existed, multiline strings required concatenation and newline characters:

```java
String oldWay = "Line one\n" +
                "Line two\n" +
                "Line three\n";
```

This was harder to read and maintain. Text blocks now allow writing multiline strings naturally:

```java
String newWay = """
    Line one
    Line two
    Line three
    """;
```

This makes it much easier to create clean, readable multiline content.

## Conclusion

In this lecture, we explored how Java handles strings and formatting. Java does not have template literals like JavaScript, but it provides powerful tools such as `String.format()` and text blocks that offer clean and readable alternatives.

## Key Takeaways

- Java uses double quotes for strings and the `+` operator for concatenation.
- `String.format()` allows embedding variables and expressions using placeholders.
- Java does not have template literals, but text blocks provide multiline string support.
- Text blocks and formatted strings significantly improve readability and convenience.
- Java automatically converts numbers to strings during concatenation.
