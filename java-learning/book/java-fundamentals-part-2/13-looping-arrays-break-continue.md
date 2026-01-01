# Looping Arrays, Breaking and Continuing

## Looping Through Arrays Using for Loops
One of the most common uses of a for loop is to iterate through an array. In Java, arrays are zero-based, meaning the first element is accessed at index 0.

Suppose we have an array named `jonas` containing multiple values.

```java
Object[] jonas = {"Jonas", "Schmedtmann", 2037 - 1991, "teacher", true};
```

If we want to log each element individually, we can use a for loop.

```java
for (int i = 0; i < jonas.length; i++) {
    System.out.println(jonas[i]);
}
```

The counter variable `i` starts at 0 and increases by one after each iteration. The loop runs as long as `i` is less than the length of the array.

Hard-coding the array length is not recommended. Using `jonas.length` ensures the loop adapts automatically if elements are added or removed.

---

## Logging Values and Their Types
We can also inspect each element and its type.

```java
for (int i = 0; i < jonas.length; i++) {
    System.out.println(jonas[i] + " - " + jonas[i].getClass().getSimpleName());
}
```

This demonstrates that an `Object[]` array can store values of different types.

---

## Creating a New Array Based on an Existing One
We can build a new array based on the contents of another array.

First, create a new array to store the types.

```java
String[] types = new String[jonas.length];
```

Then fill it using a loop.

```java
for (int i = 0; i < jonas.length; i++) {
    types[i] = jonas[i].getClass().getSimpleName();
}
```

---

## Practical Example: Calculating Ages
Let’s calculate ages from an array of birth years.

```java
int[] years = {1991, 2007, 1969, 2020};
int[] ages = new int[years.length];
```

```java
for (int i = 0; i < years.length; i++) {
    ages[i] = 2037 - years[i];
}
```

Each calculation is performed element by element and stored in the `ages` array.

---

## Continue and Break Statements
Java provides `continue` and `break` to control loop execution.

- `continue` skips the current iteration
- `break` exits the loop entirely

### Using continue
Print only string elements from the array.

```java
for (int i = 0; i < jonas.length; i++) {
    if (!(jonas[i] instanceof String)) continue;
    System.out.println(jonas[i]);
}
```

### Using break
Stop processing as soon as a number is encountered.

```java
for (int i = 0; i < jonas.length; i++) {
    if (jonas[i] instanceof Integer) break;
    System.out.println(jonas[i]);
}
```

---

## Summary
Looping through arrays with for loops allows controlled and efficient processing of data. Using dynamic conditions, along with `continue` and `break`, gives precise control over iteration flow.

---

## Key Takeaways
- Arrays are commonly processed using for loops starting at index 0.
- The loop condition should rely on the array length.
- New arrays can be derived from existing arrays using loops.
- `continue` skips an iteration, while `break` stops the loop entirely.
