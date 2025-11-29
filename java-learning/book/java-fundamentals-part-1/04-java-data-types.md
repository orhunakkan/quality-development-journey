
# Java Data Types and Static Typing

## Introduction to Data Types in Java

In the previous lecture, we discussed values and variables. In Java, every value has a specific data type, and you must define this type when creating variables. Java offers a rich set of data types designed to handle different kinds of data in a predictable and safe way. Unlike JavaScript, Java does not use dynamic typing, and variables always keep the same type once declared.

## Objects and Primitive Data Types

In Java, every value is either a primitive value or an object. Primitive values represent simple, basic data, while objects represent more complex structures. We will explore objects later, but for now, let us focus on primitive data types.

There are eight primitive data types in Java:

- byte  
- short  
- int  
- long  
- float  
- double  
- char  
- boolean  

Let us look at them one by one.

## Number Data Types

Java has multiple number types, each with different ranges and memory requirements. These include byte, short, int, long, float, and double. The most commonly used type for whole numbers is int, and for decimal numbers, double is the standard choice. Unlike JavaScript, Java separates integer and floating‑point numbers into different data types.

## String Data Type

A String in Java is not a primitive data type. It is a full object representing a sequence of characters. Strings must always be wrapped in double quotes. Java treats strings as objects with many useful built‑in methods.

## Boolean Data Type

The boolean type represents a logical value and can only hold true or false. Just like in JavaScript, we use boolean values when making decisions in code.

These are some of the most commonly used types, but Java offers several more primitive types for different needs.

## The char Data Type

Java has a dedicated primitive data type called char used for single characters. It must always be written inside single quotes, such as 'A' or 'z'. This type does not exist in JavaScript.

## Integer and Floating‑Point Types

Java distinguishes between whole numbers and decimal numbers. int and long represent whole numbers, while float and double represent decimal numbers. Because Java does not automatically convert between these types, developers must choose the correct one for each situation.

## Static Typing in Java

Java uses static typing. This means you must specify the data type of a variable when declaring it, and that data type cannot change later. If you attempt to assign a value of a different type to a variable, Java will produce a compile‑time error. This prevents many bugs but also requires more careful planning when writing code.

Variables in Java store values with a fixed type. You cannot, for example, assign a number to a variable that was originally declared as a String.

## Comments in Java

Just like JavaScript, Java supports comments for documenting or disabling code. Java provides single‑line comments using `//`:

```java
// This is a single line comment
```

Java also supports multi‑line comments using `/*` and `*/`:

```java
/*
This is a multi-line comment.
It can span multiple lines.
*/
```

Java completely ignores any text inside comments.

## Examples of Data Types

Here is a simple example of variable declarations using different data types:

```java
int age = 23;
String firstName = "Jonas";
boolean isJavaFun = true;
```

Each variable must explicitly define its type before the equals sign.

## The typeof Operator Equivalent in Java

Java does not have a typeof operator. Java determines data types at compile time, not at runtime. To check the type of an object during execution, you can use:

```java
object.getClass()
```

This works only for objects, not for primitive types.

## Static Typing in Action

Since Java is statically typed, the type of a variable cannot change. For example:

```java
boolean isJavaFun = true;
// isJavaFun = "YES!"; // This will cause a compile-time error
```

Java does not allow assigning a value of a different type to the same variable. This eliminates certain bugs but also removes the flexibility found in dynamically typed languages.

## Null Value

Java supports the null keyword, which represents the absence of a value. However, null can only be assigned to reference types (objects). You cannot assign null to primitive data types.

For example:

```java
String name = null;  // Allowed
int count = null;    // Not allowed
```

Java does not have undefined. If a variable is not initialized, Java will not allow using it until you assign a value.

## Key Takeaways

- Java has eight primitive data types: byte, short, int, long, float, double, char, and boolean.
- Java is statically typed, meaning variables must be declared with a type and cannot change types later.
- Strings are objects, not primitive data types.
- Java does not have undefined, and null can only be assigned to object references.
- Comments in Java can be single‑line (`//`) or multi‑line (`/* ... */`).
