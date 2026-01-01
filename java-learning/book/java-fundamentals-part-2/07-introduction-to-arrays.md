# Introduction to Arrays

Introduction to Arrays  
Let’s now talk about our first data structure: **Arrays**. Suppose I want to store my friends’ names so that I can use them later in my program. With the knowledge we have so far, this is how we would do it.

```java
String friendOne = "Michael";
String friendTwo = "Steven";
String friendThree = "Peter";
```

However, this approach is not very practical. Imagine wanting to represent 10 friends; you would have to create 10 separate variables. Instead, wouldn’t it be better to bundle all these values together into a bigger container? That’s exactly why we use data structures, and **arrays** are one of the most fundamental data structures in Java.

An **array** is like a big container that can hold multiple values of the same type. This is important because programming often involves data: we get data, store it, process it, and then output some data. Data needs to be stored somewhere, and for that, we use data structures like arrays.

Java has many data structures, but arrays are one of the most basic and widely used. Now, let’s learn all about arrays.

---

## Creating Arrays

Instead of creating multiple variables, let’s create a single variable called `friends` and use curly braces to create a new array. Inside the braces, we put values separated by commas.

```java
String[] friends = {"Michael", "Steven", "Peter"};
```

Make sure the commas are **outside the strings**. Each value must be a complete string, followed by a comma, then the next value, and so on.

To print the array contents:

```java
System.out.println(Arrays.toString(friends));
```

There is another way to create arrays using the `new` keyword.

```java
int[] years = new int[]{1991, 1984, 2008, 2020};
```

All elements in a Java array must be of the same type.

---

## Accessing Array Elements

Arrays are **zero-based indexed**.

```java
System.out.println(friends[0]);
System.out.println(friends[2]);
```

Get the number of elements:

```java
System.out.println(friends.length);
```

Get the last element:

```java
System.out.println(friends[friends.length - 1]);
```

---

## Modifying Arrays

```java
friends[2] = "Jay";
System.out.println(Arrays.toString(friends));
```

The array contents can be modified, but the reference itself cannot be reassigned if declared `final`.

---

## Arrays with Multiple Data Types

Java does not support mixed-type arrays directly. To store different types:

```java
Object[] jonas = {
    "Jonas",
    "Schmedtmann",
    2037 - 1991,
    "teacher",
    friends
};
```

---

## Array Exercise: Calculating Ages

```java
static int calcAge(int year) {
    return 2037 - year;
}

int[] years = {1990, 1967, 2002, 2010, 2018};
```

```java
int[] ages = {
    calcAge(years[0]),
    calcAge(years[1]),
    calcAge(years[years.length - 1])
};
```

---

## Conclusion

Arrays are fundamental data structures in Java used to store and process collections of data.

---

## Key Takeaways

- Java arrays store multiple values of the same type
- Arrays are zero-based indexed
- Array size is fixed
- Array elements can be mutated, references cannot be reassigned
