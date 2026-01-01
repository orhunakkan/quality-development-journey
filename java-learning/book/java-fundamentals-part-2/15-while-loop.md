# The while Loop

## Introduction to the While Loop
We have learned all about the for loop and even created nested loops. However, there is another important type of loop in Java: the **while loop**. Let’s explore how it works and how it differs from the for loop.

---

## Comparing for and while Loops
To understand the while loop, let’s revisit the weightlifting example. In a for loop, the counter, condition, and update are all defined in one place.

With a while loop, only the **condition** is defined. The loop continues running **while the condition remains true**, which is where the loop gets its name.

---

## Implementing the while Loop
To recreate the same behavior as a for loop, we need to manually manage the counter.

```java
int rep = 1;
while (rep <= 10) {
    System.out.println("Lifting weights repetition " + rep);
    rep++;
}
```

This produces the same result as a for loop. The key difference is that the counter initialization and increment are written explicitly.

---

## While Loop Without a Counter
The while loop becomes especially useful when we do not know beforehand how many times the loop should run.

Let’s simulate rolling a dice until we get a six.

```java
int dice = (int) (Math.random() * 6) + 1;
System.out.println(dice);
```

Each roll generates a number between 1 and 6.

```java
while (dice != 6) {
    System.out.println("You rolled a " + dice);
    dice = (int) (Math.random() * 6) + 1;

    if (dice == 6) {
        System.out.println("Loop is about to end...");
    }
}
```

In this example, the loop continues until a six is rolled. If the first roll is already six, the loop never runs, resulting in zero iterations.

---

## When to Use while vs. for Loops
Use a **for loop** when you know in advance how many times the loop should run, such as when iterating over an array.

Use a **while loop** when the number of iterations is unknown and depends on a condition evaluated during runtime.

---

## Key Takeaways
- The while loop runs as long as its condition is true.
- Unlike the for loop, the while loop does not require a counter.
- While loops are ideal when the number of iterations is unknown.
- Always ensure the condition can eventually become false to avoid infinite loops.
