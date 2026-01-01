# Object Methods

## Introduction to Object Methods
Let’s continue exploring objects, focusing now on **object methods**. Objects can store different types of data, and they can also contain behavior. In Java, this behavior is implemented using **methods**, which are functions defined inside a class.

While JavaScript allows functions to be stored directly inside objects, Java defines methods as part of a class. These methods operate on the data (fields) of the object.

---

## Defining Methods in a Class
Consider a class representing a person. We include a birth year and a boolean indicating whether the person has a driver’s license. We then define a method to calculate the person’s age.

```java
class Person {
    int birthYear;
    boolean hasDriversLicense;

    int calcAge(int year) {
        return 2037 - year;
    }
}
```

The `calcAge` method belongs to the `Person` class. Every object created from this class will have access to this method.

---

## Calling Methods
Once an object is created, we can call its methods using dot notation.

```java
Person jonas = new Person();
jonas.birthYear = 1991;
jonas.hasDriversLicense = true;

int age = jonas.calcAge(1991);
```

The method is invoked on the object, and the result is returned just like a regular function call.

---

## Using Object Fields Instead of Parameters
Passing the birth year as a parameter is unnecessary because the object already stores this information. In Java, methods can directly access the object’s own fields.

```java
int calcAge() {
    return 2037 - birthYear;
}
```

Now the method uses the object’s internal state instead of relying on external input.

Calling the method becomes simpler:

```java
int age = jonas.calcAge();
```

---

## Storing Computed Values
If a calculation is used multiple times, it can be stored as a field to avoid recalculating it.

```java
int age;

int calcAge() {
    age = 2037 - birthYear;
    return age;
}
```

After calling `calcAge`, the computed age is stored in the object and can be reused directly.

---

## Challenge: Creating a Summary Method
We can define another method that returns a summary string describing the person.

The summary should include:
- Name
- Age
- Job
- Whether the person has a driver’s license

---

## Implementing a Summary Method
```java
String firstName;
String job;

String getSummary() {
    return firstName + " is a " + calcAge() + " year old " + job +
           " and he has " + (hasDriversLicense ? "a" : "no") + " driver's license.";
}
```

Calling `jonas.getSummary()` returns a complete sentence built dynamically from the object’s data and methods.

---

## Arrays and Methods
In Java, arrays themselves have limited behavior, but classes like `ArrayList` provide many built-in methods such as `add`, `remove`, and `size`.

This reinforces the idea that **methods are functions attached to objects**, defining how data can be manipulated.

---

## Key Takeaways
- In Java, methods define behavior and are declared inside classes.
- Methods operate on the object’s fields without needing redundant parameters.
- Storing computed values can improve efficiency and readability.
- Methods help encapsulate logic and data together in a single structure.
- Many built-in Java classes use methods to provide powerful behavior.
