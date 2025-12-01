# The while Loop

## Introduction to the While Loop
We have learned all about the for loop and even created a loop inside a loop. However, there is another type of loop in JavaScript: the while loop. Let us explore how it works and how it differs from the for loop.

## Comparing For and While Loops
To understand the while loop, let us revisit our first for loop example, specifically the weightlifting exercise. We will keep the code for comparison. In the for loop, we specify the counter, the condition, and the increment all in one line. For the while loop, we can only specify a condition. The loop will keep running while the condition is true, which is why it is called the while loop.

## Implementing the While Loop
To implement the same logic as the for loop using a while loop, we need to define the counter and increment it manually. We start with a counter variable, set the condition, execute the code block, and then increment the counter at the end of each iteration.

```javascript
let rep = 1;
while (rep <= 10) {
  console.log(`Lifting weights repetition ${rep}`);
  rep++;
}
```

Now, we should get the same result twice: once from the for loop and once from the while loop. The while loop is more versatile than the for loop because it does not require a counter. All it needs is a condition that remains true for the loop to continue. The condition can be anything, not necessarily related to a counter.

## While Loop Without a Counter
Let us create an example where the loop does not depend on a counter. Instead, it will depend on a random variable. We will roll a dice and keep rolling until we get a six. When we roll a six, the loop stops. In this case, we do not know beforehand how many times the loop will run, so we do not need a counter variable.

```javascript
let dice = Math.trunc(Math.random() * 6) + 1;
console.log(dice);
```

The roll of a dice is a number between one and six. We use Math.random() to generate a number between zero and one, multiply by six, truncate the decimal part, and add one to get a number from one to six. Each time we reload the page, we get a different random number.

```javascript
while (dice !== 6) {
  console.log(`You rolled a ${dice}`);
  dice = Math.trunc(Math.random() * 6) + 1;
  if (dice === 6) console.log('Loop is about to end...');
}
```

In this code, we keep running the loop until we roll a six. Each iteration logs the value to the console and then generates a new dice value. If the dice is six, we log that the loop is about to end. If the first dice is six, the loop never starts, resulting in zero iterations.

## Conclusion: When to Use While vs. For Loops
The while loop does not have to depend on any counter variable. Whenever you need a loop without a counter, especially when you do not know beforehand how many iterations are required, the while loop is the right tool. On the other hand, when you know how many times the loop should run, such as when looping over an array, the for loop is usually the better choice.

## Key Takeaways
- The while loop in JavaScript runs as long as its condition remains true, making it more versatile than the for loop.
- Unlike the for loop, the while loop does not require a counter variable; it only needs a condition.
- The while loop is ideal for situations where the number of iterations is unknown in advance, such as rolling a dice until a six appears.
- Infinite loops can occur if the condition never becomes false, so it is important to update variables within the loop appropriately.
