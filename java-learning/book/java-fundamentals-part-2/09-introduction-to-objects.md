# Introduction to Objects

## Introduction to Objects
After learning about arrays, it is now time to explore another important data structure in Java: **objects**.

Up until this point, we have been using arrays as a data structure to store multiple related values within the same variable. Let’s quickly recap that idea.

An array can store values such as a first name, last name, age calculation, job, and even another array of friends. All these values belong to the same entity, but they are stored purely by position.

Intuitively, the first element represents the first name, the second the last name, then age, job, and friends. However, arrays do not allow us to assign **names** to these elements. We can only access them by their index, which can become confusing and error-prone.

To solve this problem, Java uses **objects**, which allow us to group related data together and give each value a meaningful name.

---

## Defining an Object
In Java, objects are created from **classes**. A class acts as a blueprint that defines which properties an object should have.

Instead of square brackets used for arrays, we define a class using curly braces. These curly braces are used to define the structure of the object.

For example, we can define a class to represent a person.

```java
class Person {
    String firstName;
    String lastName;
    int age;
    String job;
    String[] friends;
}
```

Each variable inside the class is called a **field**. These fields represent the data that belongs to the object.

---

## Creating an Object Instance
Once a class is defined, we can create an object from it. This object is also called an **instance** of the class.

```java
Person jonas = new Person();
jonas.firstName = "Jonas";
jonas.lastName = "Schmedtmann";
jonas.age = 2037 - 1991;
jonas.job = "teacher";
jonas.friends = new String[]{"Michael", "Peter", "Steven"};
```

Here, the object `jonas` has five properties that belong together. Each property has a name and a value.

---

## Objects and Properties
We say that the object `jonas` has properties such as `firstName`, `lastName`, `age`, `job`, and `friends`. Unlike arrays, we do not rely on the order of values.

Instead, we access data directly by name:

```java
System.out.println(jonas.firstName);
System.out.println(jonas.job);
```

The order in which properties are defined does not matter when retrieving them.

---

## Objects vs Arrays
Arrays are best suited for **ordered data**, where the position of each element matters.

Objects are better suited for **structured data**, where each value has a clear meaning and should be accessed by name rather than by position.

In Java, objects are one of the most fundamental concepts and are used extensively throughout the language.

---

## Key Takeaways
- Objects in Java group related data together using named properties.
- Objects are created from classes, which define their structure.
- Arrays store values by position, while objects store values by name.
- The order of properties in an object does not affect how data is accessed.
- Objects are a core concept in Java and are essential for building structured applications.
