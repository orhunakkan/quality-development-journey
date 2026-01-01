# Iteration: The for Loop

## Introduction to Loops
When we talked about conditional logic, we mentioned that it is a control structure. Another important control structure is the **loop**. Loops are a fundamental concept in Java and allow us to automate repetitive tasks.

Repetition is very common in programming. Instead of writing the same code multiple times, we can use loops to execute a block of code repeatedly in a controlled way.

---

## Analogy: Gym Repetitions
To understand loops, think about a gym workout. When lifting weights, you might perform a certain exercise for 10 repetitions.

If we had to write a separate line of code for each repetition, it would quickly become impractical. If the number of repetitions changes, we would have to update many lines of code. This clearly violates the **don’t repeat yourself** principle.

---

## Introducing the for Loop
Instead of repeating code manually, we can use a **for loop**. A for loop runs a block of code multiple times while keeping track of repetitions using a counter variable.

```java
for (int rep = 1; rep <= 10; rep++) {
    System.out.println("Lifting weights repetition " + rep + " 💪");
}
```

---

## Structure of the for Loop
The `for` statement consists of three parts inside the parentheses:

- **Initialization:** A counter variable is created and initialized. Here, `rep` starts at `1`.
- **Condition:** The loop continues to run as long as this condition is true. In this case, `rep <= 10`.
- **Counter Update:** After each iteration, the counter is updated. Here, `rep++` increases the value by one.

---

## Explanation of Loop Execution
Before each iteration, Java checks the condition. If the condition is true, the loop body executes.

After the code inside the loop runs, the counter is incremented. This process repeats until the condition becomes false. When `rep` reaches `11`, the condition fails and the loop stops.

As a result, the message is printed exactly 10 times, with the repetition number updating automatically.

---

## Using the Counter Variable
The counter variable can be used directly inside the loop to customize the output dynamically.

```java
System.out.println("Lifting weights repetition " + rep + " 💪");
```

This ensures the correct repetition number is printed without manually changing the code.

---

## Modifying the Starting Point
We can start the loop counter at any value. For example, starting at 5:

```java
for (int rep = 5; rep <= 10; rep++) {
    System.out.println("Lifting weights repetition " + rep + " 💪");
}
```

This prints repetitions from 5 through 10.

---

## Changing the Number of Repetitions
We can also adjust the condition to control how many times the loop runs.

```java
for (int rep = 1; rep <= 30; rep++) {
    System.out.println("Lifting weights repetition " + rep + " 💪");
}
```

This executes the loop 30 times.

---

## Summary
The for loop in Java allows us to automate repetitive tasks efficiently. By controlling the starting value, condition, and counter update, we can precisely define how many times a block of code should execute.

---

## Key Takeaways
- Loops are essential control structures used to repeat tasks.
- The for loop consists of initialization, condition, and update expressions.
- The loop executes while the condition remains true.
- Using loops avoids code duplication and improves maintainability.
