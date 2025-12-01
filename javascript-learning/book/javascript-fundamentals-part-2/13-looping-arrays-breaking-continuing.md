# Looping Arrays, Breaking and Continuing

## Looping Through Arrays Using For Loops
Let's explore some additional features of the for loop and create a somewhat more useful example. One of the most common applications of for loops is to iterate through an array.

We will use the array named Jonas that was introduced in the lecture about objects. If you did not write it down then, you can write it now.

We can use a for loop to iterate through this array. Suppose we want to individually log every element of the array to the console. This is a simple task.

All we want is to log these five elements.

```javascript
for (let i = 0; i < 5; i++) {
  console.log(Jonas[i]);
}
```

We start with the counter variable i, which has traditionally been used for this purpose. We initialize it at zero because arrays are zero-based when accessing elements.

The tricky part is the loop condition. We want the loop to run while i is zero, one, two, three, and four, because these are valid indices for the array elements. When i becomes five, the loop should stop because Jonas[5] does not exist.

Therefore, the condition is that i should always be less than five.

However, hard-coding the length of the array as five is problematic. If we add another element, such as true, it will not be logged because the loop condition still restricts i to be less than five.

To solve this, we replace the hard-coded value with the dynamic length of the array using Jonas.length.

```javascript
for (let i = 0; i < Jonas.length; i++) {
  console.log(Jonas[i]);
}
```

This way, the loop automatically adjusts to the array's length. If we add true to the array, Jonas.length becomes six, and the loop will log all six elements.

Let's also log the type of each element using the typeof operator.

```javascript
for (let i = 0; i < Jonas.length; i++) {
  console.log(Jonas[i], typeof Jonas[i]);
}
```

The output will show the elements along with their types: string, number, object, boolean, etc. This demonstrates that arrays can contain elements of different types, and that arrays themselves are objects.

## Creating a New Array Based on an Existing One
Now, let's create a new array that contains the types of each element from the Jonas array. This is an educational exercise to demonstrate how to create a new array based on the values of an existing array.

First, we create an empty array types outside the loop.

```javascript
const types = [];
```

Then, we use the same for loop to fill the types array with the types of the elements from the Jonas array.

```javascript
for (let i = 0; i < Jonas.length; i++) {
  types[i] = typeof Jonas[i];
}
```

Alternatively, we can use the push method to add elements to the end of the types array inside the loop, which is often cleaner.

```javascript
const types = [];
for (let i = 0; i < Jonas.length; i++) {
  types.push(typeof Jonas[i]);
}
```

Logging the types array will show the types of all elements in the Jonas array.

```javascript
console.log(types);
```

## Practical Example: Calculating Ages from Birth Years
Let's consider a more practical example. We have an array of birth years and want to calculate the ages for all these years, storing the results in a new array.

We define the years array and create an empty ages array to hold the results.

```javascript
const years = [1991, 2007, 1969, 2020];
const ages = [];
```

We loop through the years array, calculate the age for each birth year by subtracting it from the current year (2037 in this example), and push the result into the ages array.

```javascript
for (let i = 0; i < years.length; i++) {
  ages.push(2037 - years[i]);
}
```

Logging the ages array will show the calculated ages corresponding to each birth year.

```javascript
console.log(ages);
```

This example illustrates that operations between simple values and arrays are not directly possible. Instead, we perform calculations element by element within the loop.

## Continue and Break Statements in For Loops
Two important statements for loops are continue and break.

- `continue` exits the current iteration and proceeds to the next iteration.
- `break` completely terminates the entire loop immediately.

Let's see examples of both.

Suppose we want to print only the string elements from the Jonas array. We can use continue to skip non-string elements.

```javascript
for (let i = 0; i < Jonas.length; i++) {
  if (typeof Jonas[i] !== 'string') continue;
  console.log(Jonas[i]);
}
```

In this loop, if the current element is not a string, the continue statement skips the rest of the loop body and proceeds to the next iteration. Thus, only strings are logged.

Now, let's see how break works. Suppose we want to stop logging elements as soon as we find a number.

```javascript
for (let i = 0; i < Jonas.length; i++) {
  if (typeof Jonas[i] === 'number') break;
  console.log(Jonas[i]);
}
```

Here, when the loop encounters the first number, it executes the break statement, which terminates the entire loop immediately. No further elements are processed or logged.

While these examples may seem simple, continue and break are powerful tools for controlling loop execution in practical scenarios.

## Summary
Understanding how to loop through arrays using for loops is essential. The key points are:

- Initialize the counter variable at zero because arrays are zero-indexed.
- Use the loop condition to ensure the counter stays below the array's length.
- Access elements using the counter variable.
- Use continue to skip iterations and break to terminate loops early when needed.

## Key Takeaways
- For loops are commonly used to iterate through arrays, starting with a counter at zero because arrays are zero-indexed.
- The loop condition should ensure the counter variable stays below the array's length to avoid accessing undefined elements.
- Arrays can be dynamically processed to create new arrays, such as extracting the types of elements.
- The continue statement skips the current iteration and proceeds to the next, while break terminates the entire loop immediately.
