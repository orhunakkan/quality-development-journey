# Looping Backwards and Nested Loops

## Looping Over an Array Backwards
In this lecture, we will explore two interesting concepts. First, we will loop over an array backwards. Second, we will create a loop inside another loop. Let's have some more fun with arrays now.

Let's retrieve our array again, specifically Jonas' array, and ignore the last element for now. Previously, we looped from the beginning of the array, starting at index zero and moving up to index four. Now, we want to loop backwards, starting at index four and moving down to zero, which is the last index.

To create this backward loop, we start with a counter named i. The initial value of i should be the last index of the array, which is the length of the array minus one. Instead of hardcoding the value four, we write Jonas.length - 1 to make the code adaptable.

The loop should continue running as long as i is greater than or equal to zero. After each iteration, we decrement i by one using i-- instead of incrementing it. This way, the loop moves from the last element to the first.

Let's simulate the loop:

- Start with i = 4. Since 4 >= 0 is true, the loop runs.
- Decrement i to 3. Since 3 >= 0 is true, the loop continues.
- Continue decrementing until i becomes -1.
- When i = -1, the condition -1 >= 0 is false, so the loop stops.

This approach ensures we loop over the array backwards correctly. The rest of the loop's functionality remains the same, such as accessing the current element using the counter i.

We can verify this by printing both the counter variable and the corresponding array value. The output will show the indices from 4 down to 0 and their respective elements, confirming the backward iteration works as intended.

If we modify the array by adding an element, the loop still works correctly because it dynamically calculates the last index using Jonas.length - 1.

Although looping backwards is not something you will do all the time, it is a useful exercise to understand loop control and counter management.

## Nested Loops: Loop Inside a Loop
Now, let's explore how to create a loop inside another loop. This concept is not complicated and can be very useful. Let's revisit our gym example from the first loop lecture, where we had 10 repetitions for a weightlifting exercise.

Suppose we have three different exercises and want to repeat each exercise five times. This results in a total of 15 repetitions: five for each of the three exercises. To achieve this, we need a loop inside a loop.

Let's start by creating a counter named exercise, initialized at one. We want to log exercises one, two, and three. The loop condition is that exercise should be less than or equal to three. Alternatively, we could write exercise < 4 — both are equivalent.

After each iteration, we increment the exercise counter by one using exercise++. Inside this loop, we log the current exercise number using a template literal to include the counter variable.

Currently, this loop logs the exercise numbers but does not include repetitions. To add repetitions, we create another loop inside the exercise loop. This inner loop manages the repetitions for each exercise.

We create a counter named rep starting at one. The condition is that rep should be less than six, representing five repetitions. After each iteration, we increment rep by one using rep++.

Inside the inner loop, we log a message indicating the current repetition of lifting weights. We use a template literal to include the repetition number and a cute emoji for fun.

When we run this nested loop, the output shows:

- Starting exercise 1, followed by repetitions 1 to 5.
- Then exercise 2 with repetitions 1 to 5.
- Finally, exercise 3 with repetitions 1 to 5.

This confirms the nested loop runs as expected, producing a total of 15 repetitions.

We can enhance the repetition log message by including the current exercise number from the outer loop. Since the outer loop variable exercise is accessible inside the inner loop, we can include it in the template literal to provide more context.

The final output will show messages like "Exercise 1: lifting weights repetition 1" through "Exercise 3: lifting weights repetition 5," demonstrating the power and flexibility of nested loops.

Although you might not use nested loops frequently in real-world code, this example illustrates their usefulness and how they can be applied to solve problems involving multiple layers of repetition.

## Conclusion
In this lecture, we learned how to loop over an array backwards by initializing the counter at the last index and decrementing it until zero. We also explored nested loops by creating a loop inside another loop to simulate multiple exercises with repetitions. These concepts enhance our ability to control flow and repetition in programming.

## Key Takeaways
- Learned how to loop over an array backwards by initializing the counter at the last index and decrementing it until zero.
- Understood the importance of avoiding hard-coded values by using array length minus one for the starting index.
- Explored nested loops by creating a loop inside another loop to simulate multiple exercises with repetitions.
- Recognized the accessibility of outer loop variables within inner loops, enabling dynamic and contextual output.
