# Functions Calling Other Functions

## Functions Calling Other Functions
Let's now take functions one step further by exploring how one function can call another function inside it. This is a common practice in JavaScript, although many beginners find it challenging to understand this logic. To clarify this concept, we will use a specific example.

We will revisit our initial example of a function called `fruitProcessor`. This function receives a certain number of apples and oranges and then produces and returns juice based on those inputs.

Now, let's consider that the `fruitProcessor` can only make juice with smaller fruit pieces. Therefore, before making the juice, the `fruitProcessor` needs another function that first cuts the fruits into multiple smaller pieces.

Let's start by writing that function which cuts a fruit into multiple pieces. We define a function called `cutFruitPieces` that receives a fruit quantity and returns the fruit cut into four pieces by multiplying the input by four. For example, if we input two apples, it will return eight pieces.

```javascript
function cutFruitPieces(fruit) {
    return fruit * 4;
}
```

Now, inside the `fruitProcessor` function, which receives apples and oranges, we will use our newly created `cutFruitPieces` function to cut the received apples and oranges into smaller pieces.

```javascript
function fruitProcessor(apples, oranges) {
    const applePieces = cutFruitPieces(apples);
    const orangePieces = cutFruitPieces(oranges);
    const juice = `Juice with ${applePieces} pieces of apple and ${orangePieces} pieces of orange.`;
    return juice;
}
```

When we call `fruitProcessor(2, 3)`, the function `fruitProcessor` is invoked with two apples and three oranges. Inside it, the `cutFruitPieces` function is called twice: once with the number of apples and once with the number of oranges. This demonstrates one function calling another function.

Let's analyze how the data flows between these functions. When calling `fruitProcessor(2, 3)`, the parameter `apples` is replaced with 2 and `oranges` with 3. Then, `cutFruitPieces` is called with these values, replacing its parameter `fruit` with the respective numbers. Inside `cutFruitPieces`, the fruit value is multiplied by four, returning the number of pieces.

The result of calling `cutFruitPieces(2)` is 8, which is stored in `applePieces`. Similarly, `cutFruitPieces(3)` returns 12, stored in `orangePieces`. These values are then used to build the juice string returned by `fruitProcessor`.

```javascript
console.log(fruitProcessor(2, 3));
// Output: Juice with 8 pieces of apple and 12 pieces of orange.
```

This example illustrates the mechanics of one function calling another. You might wonder why not simply multiply both input values by four directly inside `fruitProcessor`. While that is possible, using a separate function adheres to the "Don't Repeat Yourself" (DRY) principle.

If the cutting logic changes, for example, cutting fruits into three pieces instead of four, you only need to update the `cutFruitPieces` function. This avoids changing multiple lines of code in different places, reducing the chance of errors and making maintenance easier.

```javascript
function cutFruitPieces(fruit) {
    return fruit * 3;
}
```

Now, calling `fruitProcessor(2, 3)` will return juice with 6 pieces of apple and 9 pieces of orange, reflecting the updated cutting logic.

In summary, with practice, you will learn when to create your own functions and when to have multiple functions calling one another. This approach improves code organization, readability, and maintainability.

Congratulations on your progress with functions. Although this topic can be challenging, your efforts are paying off. In the next video, we will review the fundamental principles about functions that we have just learned.

## Key Takeaways
- Functions can call other functions to perform subtasks, enhancing modularity.
- Using functions within functions helps avoid code repetition, following the DRY principle.
- Passing arguments between functions allows data to flow and be transformed step-by-step.
- Encapsulating functionality in separate functions simplifies maintenance and reduces bugs.
