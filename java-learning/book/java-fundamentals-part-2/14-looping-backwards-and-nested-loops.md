# Looping Backwards and Nested Loops

## Looping Over an Array Backwards
In this lecture, we explore two useful concepts. First, we loop over an array **backwards**. Second, we create a **nested loop**, which is a loop inside another loop.

Let’s start by working with an array and looping through it in reverse order.

```java
Object[] jonas = {"Jonas", "Schmedtmann", 2037 - 1991, "teacher", true};
```

Previously, we looped from the beginning of the array, starting at index `0`. Now, we want to start at the **last index** and move backwards to the first.

To do this, we initialize the counter to `jonas.length - 1`, which always represents the last valid index.

```java
for (int i = jonas.length - 1; i >= 0; i--) {
    System.out.println(i + ": " + jonas[i]);
}
```

The loop continues running as long as `i` is greater than or equal to `0`. After each iteration, the counter is decremented using `i--`.

This ensures the loop processes elements from the last to the first. If the array changes in size, the loop still works correctly because it dynamically calculates the last index.

Although looping backwards is not very common, it is a great exercise for understanding loop control and counter behavior.

---

## Nested Loops: Loop Inside a Loop
Now, let’s explore **nested loops**, which means placing one loop inside another.

Think about the gym example again. Suppose we have **three exercises**, and for each exercise we perform **five repetitions**.

The outer loop controls the exercises.

```java
for (int exercise = 1; exercise <= 3; exercise++) {
    System.out.println("Starting exercise " + exercise);
}
```

This loop runs three times, once for each exercise.

To add repetitions, we place another loop inside the first one.

```java
for (int exercise = 1; exercise <= 3; exercise++) {
    System.out.println("Starting exercise " + exercise);

    for (int rep = 1; rep <= 5; rep++) {
        System.out.println("Exercise " + exercise + ": lifting weights repetition " + rep + " 💪");
    }
}
```

The outer loop runs once per exercise, and for each exercise, the inner loop runs five times. This results in a total of fifteen repetitions.

The inner loop has access to the counter variable from the outer loop, which allows us to include the exercise number in the output.

This demonstrates how nested loops can model problems that involve multiple layers of repetition.

---

## Conclusion
In this lesson, we learned how to loop over an array backwards by initializing the counter at the last index and decrementing it until zero. We also explored nested loops by creating a loop inside another loop to simulate exercises with repetitions.

These techniques give us more control over program flow and allow us to solve more complex repetition-based problems.

---

## Key Takeaways
- Arrays can be looped backwards by starting at `length - 1` and decrementing the counter.
- Avoid hard-coded values by using dynamic expressions like `array.length - 1`.
- Nested loops allow one loop to run fully inside another loop.
- Inner loops can access variables from outer loops, enabling contextual output.
