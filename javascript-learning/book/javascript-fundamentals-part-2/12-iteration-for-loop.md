# Iteration: The for Loop

## Introduction to Loops
When we talked about the if-else statement, I mentioned that it is a control structure, and also that there are more control structures. One of the other control structures is the loop. Loops are our final big topic in this JavaScript fundamentals section.

Loops are a fundamental aspect of every programming language because they allow us to automate repetitive tasks—tasks that we have to perform over and over again.

## Analogy: Gym Repetitions
To illustrate, consider the analogy of a gym. When you go to a gym, you might lift weights. Let's say you do 10 repetitions of a certain weightlifting exercise. We could represent that by writing something like "lifting weights repetition one" and so on.

However, if we had to write this line 10 times, it would not be a best practice. Imagine if we had 30 repetitions instead of 10. If we wanted to change a word in the string, we would have to change it in all instances. This violates the "don't repeat yourself" principle because we are repeating the same code multiple times, only changing the number.

## Introducing the For Loop
Instead of repeating code, we can create a loop and put one line of code inside it. The loop will run that code repeatedly until we tell it to stop. Let's implement a so-called for loop now. This loop has a counter.

```javascript
for (let rep = 1; rep <= 10; rep++) {
  console.log(`Lifting weights repetition ${rep} 💪`);
}
```

## Structure of the For Loop
The for statement has three parts inside the parentheses:

- **Initialization:** We create a variable called `rep` (short for repetition) and start it at 1, which represents the first repetition.
- **Condition:** The loop will continue running as long as `rep` is less than or equal to 10. This condition is evaluated before each iteration.
- **Counter Update:** After each iteration, the counter `rep` is increased by one using the `rep++` syntax.

The loop keeps running while the condition is true. Once the condition becomes false, the loop stops.

## Explanation of Loop Execution
Before each iteration, the condition `rep <= 10` is checked. If true, the loop executes the code block. After executing, the counter `rep` is incremented by one. This process repeats until `rep` becomes 11, at which point the condition is false and the loop stops.

Thus, the string `"Lifting weights repetition X 💪"` is printed 10 times, with X ranging from 1 to 10.

## Using the Counter Variable in the Loop
We can dynamically insert the current value of the counter `rep` into the string using a template string. This way, the number updates automatically each iteration without manually changing the code.

```javascript
console.log(`Lifting weights repetition ${rep} 💪`);
```

## Recap of the For Loop Usage
- We wanted to print a string 10 times, but it could be any repetitive operation.
- We used `console.log` to output the result in the developer console.
- To avoid repeating code, we used a for loop.
- We initialized the counter `rep` at 1, representing the first repetition.
- After each iteration, the counter increases by one.
- The loop runs while `rep` is less than or equal to 10, achieving exactly 10 repetitions.
- The loop checks the condition before each repetition and only continues if it is true.

## Modifying the Starting Point
We can start the counter at any number. For example, starting at 5 will print repetitions beginning from 5.

```javascript
for (let rep = 5; rep <= 10; rep++) {
  console.log(`Lifting weights repetition ${rep} 💪`);
}
```

## Changing the Number of Repetitions
We can also change the upper limit to 30 to print 30 repetitions.

```javascript
for (let rep = 1; rep <= 30; rep++) {
  console.log(`Lifting weights repetition ${rep} 💪`);
}
```

## Summary
In summary, the for loop in JavaScript allows us to automate repetitive tasks efficiently by controlling the number of iterations with a counter variable, a condition, and an update expression. This avoids code duplication and makes programs easier to maintain.

## Key Takeaways
- Loops are fundamental control structures that automate repetitive tasks in programming.
- The for loop in JavaScript consists of three parts: initialization, condition, and counter update.
- The loop runs while the condition is true and stops when it becomes false.
- Using loops avoids code repetition and adheres to the "don't repeat yourself" principle.
